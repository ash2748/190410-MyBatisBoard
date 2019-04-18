package com.board.ash.domain;

import java.util.Date;

public class ReplyDomain {
	
	private int reply_num;
	private String reply_name;
	private String reply_content;
	private int num;
	private Date reply_date;
	
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public String getReply_name() {
		return reply_name;
	}
	public void setReply_name(String reply_name) {
		this.reply_name = reply_name;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	
	@Override
	public String toString() {
		return "ReplyDomain [reply_num=" + reply_num + ", reply_name=" + reply_name + ", reply_content=" + reply_content
				+ ", num=" + num + ", reply_date=" + reply_date + "]";
	}
}
