package com.kh.board.controller;

import java.util.ArrayList;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.model.vo.Member;
import com.kh.view.View;

public class BoardController {
	private BoardService bser = new BoardService();
	private View view = new View();

	public void selectAll() {
		ArrayList<Board> bList = bser.selectAll();
		
		if(!bList.isEmpty()) {
			view.selectAll(bList);
		}else {
			view.displayError("조회 결과가 없습니다.");
		}
	}

	public void selectOne() {
		int no = view.inputBNo();
		Board board = bser.selectOne(no);
		
		if(board!=null) {
			view.selectOne(board);
		}else {
			view.displayError("조회 결과가 없습니다.");
		}
	}

	public void insertBoard() {
		Board board = view.insertBoard();
		
		int result = bser.insertBoard(board);
		
		if(result > 0) {
			view.displaySuccess(result + "개의 행이 추가되었습니다.");
		}else {
			view.displayError("데이터 삽입 과정 중 오류 발생");
		}	
	}

	public void updateBoard() {
//		String bNo = view.getMemberId();
		int bNo = view.inputBNo();
		int check = bser.checkBoard(bNo);
		
		if(check != 1) {
			view.displayError("입력한 글번호가 존재하지 않습니다.");
		} else {
			int sel = view.updateMenu();
			
			if(sel == 0) return;
			
			
		}
		
		
	}

	public void deleteBoard() {
		int bNo = view.inputBNo();
		
		int check = bser.checkBoard(bNo);
		
		if(check != 1) {
			view.displayError("입력한 글번호가 존재하지 않습니다.");
		} else {
			int re = bser.deleteBoard(bNo);
			
			if(re > 0) {
				view.displaySuccess(re + "개의 게시글이 삭제되었습니다.");
			}else {
				view.displayError("글 삭제 과정 중 오류 발생");
			}
		}
	
	}

}
