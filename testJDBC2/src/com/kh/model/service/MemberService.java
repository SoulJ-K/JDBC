package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

public class MemberService {//jdbctemplate에서 만든 getconnection을 호출하는 역할

	public int insertMember(Member member) {
//		Connection conn = JDBCTemplate.getConnection();
		Connection conn = getConnection();    
//		import로 이미 jdbctemplate를 넣어줘서 안넣어도 됨

		MemberDAO mDAO = new MemberDAO();		//dao가 수정될때마다 새로운 dao가 생기는데 필드에 적으면 한번만 생성되므로 메소드영역에 넣음
		
		int result = mDAO.insertMember(conn, member);    //DAO에서 커넥션을 사용하기 위해 보냄.
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();
		
		MemberDAO mDAO = new MemberDAO();
		
		ArrayList<Member> mList =  mDAO.selectAll(conn);
		
		return mList;
	}

	public ArrayList<Member> selectMemberId(String id) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		
		ArrayList<Member> mList = mDAO.selectMemberId(conn, id);
		
		return mList;
		
	}

	public ArrayList<Member> selectGender(char gender) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		
		ArrayList<Member> mList = mDAO.selectGender(conn, gender);
		
		return mList;
		
	}

	public int checkMember(String memberId) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		int check = mDAO.checkMember(conn, memberId);
		
		return check;

		
	}

	public int updateMember(String memberId, int sel, String input) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		
		int result = mDAO.updateMember(conn, memberId, sel, input);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		
		int result = mDAO.deleteMember(conn, memberId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	
	}  
	

}
