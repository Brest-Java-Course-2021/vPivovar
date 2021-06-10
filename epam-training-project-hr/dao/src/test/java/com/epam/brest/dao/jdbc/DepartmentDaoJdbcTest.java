package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.DepartmentDao;
import com.epam.brest.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-dao.xml", "classpath*:test-db.xml"})
public class DepartmentDaoJdbcTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJdbcTest.class);


    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void findAllTest() {

        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

    }

    @Test
    public void findByIdTest() {

        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Integer departmentId = departments.get(0).getDepartmentId();

        // 'Optional.get()' without 'isPresent()' check:
//      Department expectedDepartment = departmentDao.findById(departmentId).get();
        Department expectedDepartment = departmentDao.findById(departmentId).orElse(null);

        // Method invocation 'getDepartmentId' may produce 'NullPointerException':
//      Assert.assertEquals(expectedDepartment.getDepartmentId(), departmentId);

        Assert.assertEquals(Objects.requireNonNull(expectedDepartment).getDepartmentId(), departmentId);
        Assert.assertEquals(expectedDepartment.getDepartmentName(), departments.get(0).getDepartmentName());
        Assert.assertEquals(expectedDepartment, departments.get(0));

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByIdExceptionalTest() {

        // departmentDao.findById(999).get();

        Department expectedDepartment = departmentDao.findById(999).orElse(null);

    }

    @Test
    public void createDepartmentTest() {

        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Department department = new Department("HR");
        Integer result = departmentDao.create(department);

//      Integer result = departmentDao.create(department);

        List<Department> realDepartments = departmentDao.findAll();
        Assert.assertEquals(departments.size() + 1, realDepartments.size());

        System.out.println("Result:" + result);
        realDepartments.forEach(System.out::println);

    }

    @Test
    public void testLogging(){
        LOGGER.trace("Hello trace");
        LOGGER.debug("Hello debug");
        LOGGER.info("Hello info");
        LOGGER.warn("Hello warn");
        LOGGER.error("Hello error");
    }

}