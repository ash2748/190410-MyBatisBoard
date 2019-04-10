package com.board.ash.service;

import java.util.List;

import com.board.ash.domain.BoardDomain;
import com.board.ash.domain.PageMaker;
import com.board.ash.domain.SearchCriteria;

public interface BoardService {

	public List<BoardDomain> list(PageMaker pageMaker,SearchCriteria scri);
	public BoardDomain contentView(int num);
	public void write(BoardDomain boardDomain);
	public void delete(int num);
	public void modify(BoardDomain boardDomain);
	public int totalList();
}
