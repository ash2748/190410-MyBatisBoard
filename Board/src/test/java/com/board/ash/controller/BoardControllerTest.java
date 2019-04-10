package com.board.ash.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.ash.dao.BoardDao;
import com.board.ash.domain.BoardDomain;

@RunWith(SpringJUnit4ClassRunner.class)
public class BoardControllerTest {

	@Autowired
	BoardDao boardDao;

	@Test
	public void insertTest() {
		BoardDomain bd = new BoardDomain();
		bd.setName("제이유닛네임");
		bd.setTitle("제이유닛타이틀");
		bd.setContent("제이유닛컨텐츠");
		
		boardDao.write(bd);
	}
}
