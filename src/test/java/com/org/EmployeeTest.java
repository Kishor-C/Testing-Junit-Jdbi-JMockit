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
	
	@Test
	public void testListAll(@Mocked EmployeeDao dao) {
		new Expectations() {
			{
				List<Employee> mockedEmployees = new ArrayList<Employee>();
				mockedEmployees.add(new Employee(100, "Alia", 10000));
				mockedEmployees.add(new Employee(200, "Rajinikanth", 10000));
				mockedEmployees.add(new Employee(300, "Katrina", 10000));
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
