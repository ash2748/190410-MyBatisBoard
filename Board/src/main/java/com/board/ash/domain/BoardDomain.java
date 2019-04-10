package com.board.ash.domain;

import java.util.Date;

public class BoardDomain {
	
	private int num;
	private String name;
	private String title;
	private String content;
	private int count;
	private Date write_date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	@Override
	public String toString() {
		return "BoardDomain [num=" + num + ", name=" + name + ", title=" + title + ", content=" + content + ", count="
				+ count + ", write_date=" + write_date + "]";
	}
}
