# Testing-Junit-Jdbi-JMockit
Junit and Jmockit with JDBI

# Project Structure
Just for demo purpose all the classes are kept in the same package i.e., com.org
1. src/main/java/com/org has classes like Employee, EmployeeDao, DBConnection, EmployeeMapper, AppMain

DBConnection: Has method that returns DBI instance which connects to MySQL
EmployeeDao: It is an interface which has SQL queries on top of methods these methods are automatically implemented by 
  JDBI onDemand method of DBI instance when you pass the EmployeeDao.class into it.

Calling the methods of EmployeeDao will help JDBI to execute the query that has written on them
Select queries returns object or list of object whereas DML queries return int telling how many row(s) affected


EmployeeMapper: Has method that converts SQL Result to Java Object i.e., Employee object

Employee: It is a java bean class with three variables id, name and salary each has setters & getters, a default constructor, a parameterized
constructor to initialize the variables and also some static methods like dao(), listAll() and find()

dao(): it will get you the instance of EmployeeDao, from it you can call the methods of EmployeeDAO
listAll(): it calls dao().getAllEmployees() that produces List<Employee> through EmployeeMapper
find(): it calls dao().findById(id) that produces an Employee matching to the id passed or null if there are no records matching to the id

# Testing

src/test/java/com/org has only one class named EmployeeTest to test the methods present inside Employee class

# Testing setters, constructors and getters
testEmployeeParameterizedConstructor() tests the parameterized constructor and getters 
testEmployeeSetName() tests the setName() and getName()


# JMockit while testing Employee.listAll()
This method is tested using testListAll(), however listAll() method internal implementation calls dao.getAllEmployees() so we mock the
implementation of dao() itself.
testListAll method mocks the original dao() and returns the instance of DAO given by JMockit so that dao.getAllEmployees() don't connects 
to database instead returns the Employees mentioned in the <b>new Expectations(){{....}} </b>, which is an anonymous class which has a 
Instance Initialization block that creates some Employee objects and adds to the list, the result would be the returned value when a 
method under test calls dao.getAllEmployees(), the method that is tested is 


# Other test cases
For demo purpose this project only tests constructor, setName, getName, getId, getSalary and listAll methods of Employee class, there
must be few more test cases written for find().

