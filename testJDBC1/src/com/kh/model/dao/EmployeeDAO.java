package com.kh.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Employee;

public class EmployeeDAO {

	public ArrayList<Employee> selectAll() {     //dbms 연결, 계정연결, 쿼리 작성 및 보내기, 트랜젝션, 자원 반환
												// db에 보낼 쿼리문 작성하고 보내고, 결과값 받아오는 역할이 주요 역할
												// 나머지 작업은 DAO에서 하는 역할이 아니므로 분리

		
		Connection conn = null;   //연결정보를 담은 객체
		Statement stmt = null;  // connection객체를 통해 db에 sql문을 전달하여 실행시키고 결과 값을 반환받는 역할
		ResultSet rset = null;  // SELECT문을 사용한 질의 성공시 반환되는 객체
		ArrayList<Employee> empList = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "SCOTT");
			//jdbc:oracle:thin		JDBC 드라이버가 thin 타입
			//@localhost 			오라클이 설치된 서버의 ip가 자신의 컴퓨터임  == @127.0.0.1
			//1521 					오라클 listener 포트번호
			//xe					접속 할 오라클 명
			
//			System.out.println(conn);  //접속 실패 시 null
			
			String query = "SELECT * FROM EMP";
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			empList = new ArrayList<Employee>();     //조회 결과 저장할 arraylist
			Employee emp = null;		//조회 결과의 한 행의 값을 저장할 임시 vo선언
			
			while(rset.next()) {
				int empNo = rset.getInt("empNo");
				String empName = rset.getString("eName");
				String job = rset.getString("job");
				int mgr = rset.getInt("mgr");
				Date hireDate = rset.getDate("hireDate");
				int sal = rset.getInt("sal");
				int comm = rset.getInt("comm");
				int deptNo = rset.getInt("deptno");
				
				emp = new Employee(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
				empList.add(emp);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empList;
		
	}

	public Employee selectEmployee(int empNo) {
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Employee emp = null;
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "SCOTT");
			
//			String query = "SELECT * FROM EMP WHERE EMPNO = " + empNo;
//			String query = "SELECT * FROM EMP WHERE EMPNAME = '" + empName + "'";
			
			String query = "SELECT * FROM EMP WHERE EMPNO = ?" ;		//? 위치홀더가 쓰이는 순간 ''가 포함되어있으므로 문자열에''필요 없음
			
			
//			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empNo);
			
//			rset = stmt.executeQuery(query);
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				String empName = rset.getString("ename");
				String job = rset.getString("job");
				int mgr = rset.getInt("mgr");
				Date hireDate = rset.getDate("hiredate");
				int sal = rset.getInt("sal");
				int comm = rset.getInt("comm");
				int deptNo = rset.getInt("deptNo");
				
				emp = new Employee(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return emp;
		}

	public int insertEmployee(Employee emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","SCOTT");
			
			String query = "INSERT INTO EMP VALUES(?,?,?,?,sysdate,?,?,?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setInt(5, emp.getSal());
			pstmt.setInt(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptNo());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {	//insert 성공적으로 수행했을 경우 1행이 삽입되므로 1이 반환됨
				conn.commit();
			}else {
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}

	public int updateEmployee(Employee emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "SCOTT");
			
			String query = "UPDATE EMP SET JOB = ?, SAL = ?, COMM = ? WHERE EMPNO = ?";
			
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, emp.getJob());
			 pstmt.setInt(2, emp.getSal());
			 pstmt.setInt(3, emp.getComm());
			 pstmt.setInt(4, emp.getEmpNo());
			 
			 result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
	}

	public int deleteEmployee(int empNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "SCOTT");
			
			String query = "DELETE FROM EMP WHERE EMPNO = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empNo);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
