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
        LOGGER.debug("Create department: {}", department);
        // TO DO: Check if exist a department with the same name
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("DEPARTMENT_NAME", department.getDepartmentName());
//      return namedParameterJdbcTemplate.update(SQL_CREATE_DEPARTMENT, sqlParameterSource);
        namedParameterJdbcTemplate.update(SQL_CREATE_DEPARTMENT, sqlParameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().intValue());

    }

    @Override
    public Integer update(Department department) {
        LOGGER.debug("Update department: {}", department);
        return null;
    }

    @Override
    public Integer delete(Integer departmentId) {
        LOGGER.debug("Delete department by id: {}", departmentId);
        return null;
    }


}
