package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Employee;

public class EmployeeDAO {  
	public ArrayList<Employee> all() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Employee> empList = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "SCOTT");
			
			String query = "SELECT * FROM EMP";
			
			stmt = conn.createStatement();
			rset =  stmt.executeQuery(query);
			
			empList = new ArrayList<Employee>();
			
			while(rset.next()) {
			 int empNo = rset.getInt("empNo");
			 String empName = rset.getString("empName");
			 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
