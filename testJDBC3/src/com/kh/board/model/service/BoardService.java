package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.common.JDBCTemplate.*;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.vo.Board;
import com.kh.model.dao.MemberDAO;

public class BoardService {

	public ArrayList<Board> selectAll() {
		Connection conn =  getConnection();
		BoardDAO bDAO = new BoardDAO();
		
		ArrayList<Board> bList = bDAO.selectAll(conn);
				
		return bList;
	}

	public Board selectOne(int no) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		
		Board result = bDAO.selectOne(conn, no);
		
		return result;
	}

	public int insertBoard(Board board) {
		Connection conn = getConnection();  
		BoardDAO bDAO = new BoardDAO();

		int result = bDAO.insertBoard(conn, board);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public int checkBoard(int bNo) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		
		int result = bDAO.checkBoard(conn, bNo);
		
		return result;
	}

	public int deleteBoard(int bNo) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		
		int re = bDAO.deleteBoard(conn, bNo);
		
		if(re > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return re;
	}

}
