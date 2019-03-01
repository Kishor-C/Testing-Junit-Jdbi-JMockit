package com.org;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

public class EmployeeMapper implements ResultSetMapper<Employee> {
	  public final Employee map(final int idx, final ResultSet rs, final StatementContext ctx) throws SQLException 			{
  	return new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3));
  	}
}