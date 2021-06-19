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
import java.util.Optional;


// We need to make sure that the data we send to the database matches the reference

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


    //  Since the tests are not executed in order - the data in the table may be different
    @Test(expected = IllegalArgumentException.class)
    public void createDepartmentTest() {

        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Integer result = departmentDao.create(new Department("HR"));

        List<Department> realDepartments = departmentDao.findAll();
        Assert.assertEquals(departments.size() + 1, realDepartments.size());

        System.out.println("Result:" + result);
        realDepartments.forEach(System.out::println);

    }

    @Test(expected = IllegalArgumentException.class)
    public void createDepartmentWithTheSameTest() {

        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Integer result = departmentDao.create(new Department("HR"));
        result = departmentDao.create(new Department("HR"));

//      Integer result = departmentDao.create(department);

        List<Department> realDepartments = departmentDao.findAll();
        Assert.assertEquals(departments.size() + 1, realDepartments.size());

        System.out.println("Result:" + result);
        realDepartments.forEach(System.out::println);

    }

    @Test(expected = IllegalArgumentException.class)
    public void createDepartmentWithTheSameDiffCaseTest() {

        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Integer result = departmentDao.create(new Department("HR"));
        result = departmentDao.create(new Department("hr"));

        List<Department> realDepartments = departmentDao.findAll();
        Assert.assertEquals(departments.size() + 1, realDepartments.size());

        System.out.println("Result:" + result);
        realDepartments.forEach(System.out::println);

    }

    @Test
    public void updateDepartmentTest(){

        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Department department = departments.get(0);
        department.setDepartmentName("TEST_DEPARTMENT");
        departmentDao.update(department);

        Optional<Department> realDepartment = departmentDao.findById(department.getDepartmentId());
        Assert.assertEquals("TEST_DEPARTMENT", realDepartment.get().getDepartmentName());

    }

    @Test
    public void deleteDepartmentTest(){

        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Integer returnedDeletedId = departmentDao.delete(1);

        List<Department> realDepartments = departmentDao.findAll();
        Assert.assertEquals(departments.size(), realDepartments.size() + 1);

    }

//    @Test
//    public void updateDepartmentNotUniqueNameTest(){
//
//        List<Department> departments = departmentDao.findAll();
//        Assert.assertNotNull(departments);
//        Assert.assertTrue(departments.size() > 0);
//
//        Department department = departments.get(0);
//        department.setDepartmentName(departments.get(1).getDepartmentName());
//        departmentDao.update(department);
//    }

    @Test
    public void testLogging(){
        LOGGER.trace("Hello trace");
        LOGGER.debug("Hello debug");
        LOGGER.info("Hello info");
        LOGGER.warn("Hello warn");
        LOGGER.error("Hello error");
    }

}