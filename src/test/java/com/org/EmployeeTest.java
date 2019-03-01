package com.org;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class EmployeeTest {
	/**
	 * This will test parameterized constructor of Employee as well as getters also
	 */
	@Test
	public void testEmployeeParameterizedConstructor() {
		Employee employee1 = new Employee(101, "Sachin", 46000);
		// testing employee variables are initialized through getters which in-turn a test to getters
		// to know whether its returning a correct property value
		assertEquals(101, employee1.getId());
		assertEquals("Sachin", employee1.getName());
		assertEquals(46000.0, employee1.getSalary(), 0.5);
	}
	
	/**
	 * This will test setName of Employee class as well as getters
	 */
	@Test
	public void testEmployeeSetName() {
		Employee employee1 = new Employee(103, "Dhoni", 38000);
		// testing setName whether the parameter you pass here is correctly initialized to the
		// instance variable name of Employee class
		employee1.setName("Kholi");
		assertEquals("Kholi", employee1.getName());
	}

	/**
	 * This will test a method listAll() inside a Employee class by mocking dao() method
	 * @param dao
	 */
	@Test
	public void testListAll(@Mocked EmployeeDao dao) {
		new Expectations() {
			{
				List<Employee> mockedEmployees = new ArrayList<Employee>();
				mockedEmployees.add(new Employee(100, "Alex", 10000));
				mockedEmployees.add(new Employee(200, "Bob", 10000));
				mockedEmployees.add(new Employee(300, "Charles", 10000));
				// dao.getAllEmployees() will return these mock employees when you 
				// call it inside this testListAll method
				dao.getAllEmployees();
				result = mockedEmployees;
			}
		};
		new MockUp<Employee>() {
			// A mock DAO instance is used instead of the real DAO instance when Employee.listAll()
			// is invoked 
			@Mock
			EmployeeDao dao() {
				return dao;
			}
		};
		Employee[] employeeArray = Employee.listAll();
		assertEquals(3, employeeArray.length);
		List<Employee> employees = dao.getAllEmployees();
		assertEquals(3, employees.size());
	}
}
