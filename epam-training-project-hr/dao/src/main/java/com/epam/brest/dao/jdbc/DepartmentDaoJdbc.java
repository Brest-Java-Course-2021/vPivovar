package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.DepartmentDao;
import com.epam.brest.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DepartmentDaoJdbc implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJdbc.class);

    private static final String SQL_GET_ALL_DEPARTMENTS =
            "SELECT D.DEPARTMENT_ID, D.DEPARTMENT_NAME FROM DEPARTMENT AS D ORDER BY D.DEPARTMENT_NAME";

    private static final String SQL_GET_ALL_DEPARTMENT_BY_ID =
            "SELECT D.DEPARTMENT_ID, D.DEPARTMENT_NAME FROM DEPARTMENT AS D WHERE D.DEPARTMENT_ID = :DEPARTMENT_ID";

    private static final String SQL_CREATE_DEPARTMENT =
            "INSERT INTO DEPARTMENT (DEPARTMENT_NAME) VALUES ( :DEPARTMENT_NAME );";

    private static final String SQL_CHECK_DEPARTMENT_NAME =
            "SELECT COUNT(DEPARTMENT_ID) FROM DEPARTMENT WHERE lower(DEPARTMENT_NAME) = lower(:DEPARTMENT_NAME)";

    private static final String SQL_UPDATE_DEPARTMENT =
            "UPDATE DEPARTMENT SET DEPARTMENT_NAME = :DEPARTMENT_NAME WHERE DEPARTMENT_ID = :DEPARTMENT_ID;";

    private static final String SQL_CHECK_DEPARTMENT_EXISTS =
            "SELECT COUNT(DEPARTMENT_ID) FROM DEPARTMENT WHERE DEPARTMENT_ID = :DEPARTMENT_ID;";

    private static final String SQL_DELETE_DEPARTMENT =
            "DELETE FROM DEPARTMENT WHERE DEPARTMENT_ID = :DEPARTMENT_ID;";

    // Allows you to create dynamic parameterized queries:
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Department.class);

    /*
    public DepartmentDaoJdbc(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    */

    public DepartmentDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List findAll() {
        LOGGER.debug("Find all departments");
//      return namedParameterJdbcTemplate.query(SQL_GET_ALL_DEPARTMENTS, new DepartmentRowMapper());
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_DEPARTMENTS, rowMapper);
    }


    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
            department.setDepartmentName(resultSet.getString("DEPARTMENT_NAME"));
            return department;
        }
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        LOGGER.debug("Find department by id: {}", departmentId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("DEPARTMENT_ID", departmentId);
        return Optional.ofNullable((Department) namedParameterJdbcTemplate.queryForObject(SQL_GET_ALL_DEPARTMENT_BY_ID, sqlParameterSource, rowMapper));
    }

    @Override
    public Integer create(Department department) {
        long startTime = System.nanoTime();
        LOGGER.debug("Create department: {}", department);
        // It makes sense to save(protect) our system from unwanted exceptions
        // We saved our system from sending extra packets over the network
        if (!isDepartmentNameUnique(department)){
            LOGGER.warn("Department with the same name already exists in DB.", department);
            throw new IllegalArgumentException("Department with the same name already exists in DB.");
        }
        // TO DO: Check if exist a department with the same name
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("DEPARTMENT_NAME", department.getDepartmentName());
//      return namedParameterJdbcTemplate.update(SQL_CREATE_DEPARTMENT, sqlParameterSource);
        namedParameterJdbcTemplate.update(SQL_CREATE_DEPARTMENT, sqlParameterSource, keyHolder);
        Integer departmentId = Objects.requireNonNull(keyHolder.getKey().intValue());
        department.setDepartmentId(departmentId);
        long stopTime = System.nanoTime();
        LOGGER.debug("Execution time: {}", stopTime - startTime);
        return departmentId;

    }

    private boolean isDepartmentNameUnique(Department department){
    return      namedParameterJdbcTemplate.queryForObject(SQL_CHECK_DEPARTMENT_NAME,
                new MapSqlParameterSource("DEPARTMENT_NAME", department.getDepartmentName()), Integer.class) == 0;
    }

    @Override
    public Integer update(Department department) {
        LOGGER.debug("Update department: {}", department);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("DEPARTMENT_NAME", department.getDepartmentName())
                .addValue("DEPARTMENT_ID", department.getDepartmentId());

        return namedParameterJdbcTemplate.update(SQL_UPDATE_DEPARTMENT, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer departmentId) {
        LOGGER.debug("Delete department by id: {}", departmentId);
        if (!isDepartmentRecordByIdExists(departmentId)){
            LOGGER.warn("Department with such departmentId is not exists in DB.", departmentId);
            throw new IllegalArgumentException("Department with such departmentId is not exists in DB.");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("DEPARTMENT_ID", departmentId);

        //      Integer returnedDepartmentId = Objects.requireNonNull(keyHolder.getKey().intValue());
//      return  returnedDepartmentId;

        return namedParameterJdbcTemplate.update(SQL_DELETE_DEPARTMENT, sqlParameterSource );

    }

    private boolean isDepartmentRecordByIdExists(Integer id){
        return      namedParameterJdbcTemplate.queryForObject(SQL_CHECK_DEPARTMENT_EXISTS,
                new MapSqlParameterSource("DEPARTMENT_ID", id), Integer.class) > 0;
    }


}
