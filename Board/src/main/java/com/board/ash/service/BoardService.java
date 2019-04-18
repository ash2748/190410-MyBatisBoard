package com.board.ash.service;

import java.util.List;

import com.board.ash.domain.BoardDomain;
import com.board.ash.domain.ReplyDomain;
import com.board.ash.domain.SearchCriteria;

public interface BoardService {

	public List<BoardDomain> list(SearchCriteria scri);
	public BoardDomain contentView(int num);
	public void write(BoardDomain boardDomain);
	public void delete(int num);
	public void modify(BoardDomain boardDomain);
	public int totalList(SearchCriteria scri);
	
	public List<ReplyDomain> replyList(int num);
	public void writeReply(ReplyDomain replyDomain);
	public void replyDelete(int num);
	
}
