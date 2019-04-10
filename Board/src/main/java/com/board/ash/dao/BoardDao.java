package com.board.ash.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.board.ash.domain.BoardDomain;
import com.board.ash.domain.PageMaker;
import com.board.ash.domain.SearchCriteria;

public interface BoardDao {
	public List<BoardDomain> list(@Param("pm") PageMaker pageMaker, @Param("sc") SearchCriteria scri);
	public BoardDomain contentView(int num);
	public void write(BoardDomain boardDomain);
	public void delete(int num);
	public void modify(BoardDomain boardDomain);
	public void hit(int num);
	public int totalList();
}
