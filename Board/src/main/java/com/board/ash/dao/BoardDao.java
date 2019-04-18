package com.board.ash.dao;

import java.util.List;

import com.board.ash.domain.BoardDomain;
import com.board.ash.domain.ReplyDomain;
import com.board.ash.domain.SearchCriteria;

public interface BoardDao {
	public List<BoardDomain> list(SearchCriteria scri);
	public BoardDomain contentView(int num);
	public void write(BoardDomain boardDomain);
	public void delete(int num);
	public void modify(BoardDomain boardDomain);
	public void hit(int num);
	public int totalList(SearchCriteria scri);
	
	//댓글불러오기
	public List<ReplyDomain> replyList(int num);
	//댓글쓰기
	public void writeReply(ReplyDomain replyDomain);
	//댓글삭제
	public void replyDelete(int num);
}
