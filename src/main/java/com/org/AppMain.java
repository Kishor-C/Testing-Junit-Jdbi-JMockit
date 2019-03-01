package com.org;

public class AppMain {

	public static void main(String[] args) {
		Employee[] employeeArray = Employee.listAll();
		System.out.println(employeeArray.length);
		
		Employee employee = Employee.find(101);
		if(employee!=null) {
			System.out.println(employee.getId()+ " "+employee.getName()+" "+employee.getSalary());
		}
	}

}
