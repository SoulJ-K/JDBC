package com.kh.board.model.vo;

import java.sql.Date;

public class Board {
/* -bNo : int
	-title : Stirng
	-content :String
	-createDate : Date
	-writer : String
	
	+기본생성자
	+Board(int bNo, String title, String content, Date createDate, String writer)
	+Board(int bNo, String title, String content)
	+Board(String title, String content, String writer)
	+Board(String title, String content)
	
	+getter/setter
	+toString():String
	반환값 : 글 번호, 제목, 내용, 날짜, 작성자 */
	
	private int bNo;
	private String title;
	private String content;
	private Date createDate;
	private String writer;
	
	public Board() {}
	public Board(int bNo, String title, String content, Date createDate, String writer) {
		this.bNo = bNo;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.writer = writer;
	}
	
	public Board(int bNo, String title, String content) {
		this.bNo = bNo;
		this.title = title;
		this.content = content;
	}
	
	public Board(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public int getBNo() {
		return bNo;
	}
	
	public void setBNo(int bNo) {
		this.bNo = bNo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Override
		public String toString() {
			return bNo + ", " + title + ", " + content + ", " + createDate + ", " + writer;
		}

}