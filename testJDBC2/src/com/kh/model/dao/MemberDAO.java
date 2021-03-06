package com.kh.model.dao;
import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Member;

public class MemberDAO {
	
	private Properties prop = null;
	
	public MemberDAO() {		//dao가 만들어질때마다 새로운 파일 생성
		prop = new Properties();
		try {
			prop.load(new FileReader("query.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int insertMember(Connection conn, Member member) {
		/*
		testJDBC1 프로젝트에서 DAO가 했던 업무
		1) JDBC 드라이버 등록
		2) DB 연결(Connection 객체 생성)
		3) SQL 실행
		4) 트랜잭션 처리
		5) 자원 반납
		
		실제로 DAO가 처리해야하는 업무 : 3번(SQL문을 DB로 전달하여 실행하고 반환 값 받아오기)
		--> 1, 2, 4, 5번 업무 분담 : com.kh.common.JDBCTemplate 만들어서 분담
		
		*/
	 	
		String query = prop.getProperty("insertMember");
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender() + "");
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getAge());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Member> selectAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null;
		
		String query = prop.getProperty("selectAll");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			mList = new ArrayList<Member>();
			Member member = null;
			while(rset.next()) {
				String memberId = rset.getString("member_id");  // developer에 있는 순서를 알고 있다면 숫자로 작성해도 됨. 하지만 정확히 컬럼명을 넣는게 더 나음
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");
				
				member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address, enrollDate);
				
				mList.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		
		return mList;
	}
		//  입력된 아이디가 포함된 모든 회원 정보 조회
	public ArrayList<Member> selectMemberId(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null;
		
		String query = prop.getProperty("selectMemberId");
		
		try {
			stmt = conn.createStatement();
			query += " '%" + id + "%'";
			rset = stmt.executeQuery(query);
			
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");
				
				Member member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address, enrollDate);
				
				mList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return mList;
	}

	public ArrayList<Member> selectGender(Connection conn, char gender) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null;
		
		String query = prop.getProperty("selectGender");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gender+"");
			
			rset = pstmt.executeQuery();
			
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gen = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");
				
				Member member = new Member(memberId, memberPwd, memberName, gen, email, phone, age, address, enrollDate);
				
				mList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mList;
		
	}

	public int checkMember(Connection conn, String memberId) {
		//아이디 존재여부 확인
		//select ~ from member where member_id = ?
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int check = 0;  //rset으로 받아온 0 아니면 1의 값을 담을 곳
		
		String query = prop.getProperty("checkMember");
//		SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID = ?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			//1개 or 0개
			
			if(rset.next()) {
//				check = rset.getInt(columnIndex) : 가져올 컬럼의 위치를 넣는것
//				check = rset.getInt(columnLabel) : 가져올 컬럼의 이름을 넣는것
				check = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return check;
		
	}

	public int updateMember(Connection conn, String memberId, int sel, String input) {   //int sel은 무엇을 바꿀지에 대한 구분자
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMember" + sel);
		// 비밀번호 변경 : updateMember1
		// 이메일 변경    : updateMember2
		// 전화번호 변경 : updateMember3
		// 주소 변경 	: updateMember4
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, input);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	

}
