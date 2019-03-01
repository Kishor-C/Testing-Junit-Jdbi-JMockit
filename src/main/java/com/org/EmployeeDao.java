package com.org;
import java.util.*;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface EmployeeDao {
	@SqlQuery("select * from employee")
	@Mapper(EmployeeMapper.class)
	public List<Employee> getAllEmployees();
	
	@SqlUpdate("insert into employee values(:id,:name,:salary)")
	public int addEmpDao(@Bind("id") int id, @Bind("name") String name,@Bind("salary") float salary);

	@SqlUpdate("delete from employee where id = :id")
	public int deleteEmpDao(@Bind("id") int id);

	@SqlUpdate("update employee set salary = salary + :salary where id = :id")
	public int incrementEmpSalary(@Bind("id") int id, @Bind("salary") float salary);


	@SqlUpdate("update employee set salary = salary - :salary where id = :id")
	public int decrementEmpSalary(@Bind("id") int id, @Bind("salary") float salary);

	@SqlQuery("select * from employee where id = :empId")
	@Mapper(EmployeeMapper.class)
	public Employee findById(@Bind("empId") int id);
}