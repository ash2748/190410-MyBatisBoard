package com.board.ash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.ash.dao.BoardDao;
import com.board.ash.domain.BoardDomain;
import com.board.ash.domain.PageMaker;
import com.board.ash.domain.ReplyDomain;
import com.board.ash.domain.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<BoardDomain> list(SearchCriteria scri) {

		return boardDao.list(scri);
	}
	
	@Override
	public BoardDomain contentView(int num){
		hit(num);
		return boardDao.contentView(num);
	}

	@Override
	public void write(BoardDomain boardDomain) {

		boardDao.write(boardDomain);
	}

	@Override
	public void delete(int num) {
		boardDao.delete(num);
	}

	@Override
	public void modify(BoardDomain boardDomain) {
		boardDao.modify(boardDomain);
	}
	
	public void hit(int num){
		boardDao.hit(num);
	}

	@Override
	public int totalList(SearchCriteria scri) {
		return boardDao.totalList(scri);
	}

	@Override
	public List<ReplyDomain> replyList(int num) {
		return boardDao.replyList(num);
	}

	@Override
	public void writeReply(ReplyDomain replyDomain) {
		boardDao.writeReply(replyDomain);
	}

	@Override
	public void replyDelete(int num) {
		boardDao.replyDelete(num);
	}
	
	
}
