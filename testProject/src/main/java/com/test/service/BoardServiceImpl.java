package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.SearchCriteria;
import com.test.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	//목록
	@Override
	public List<BoardVO> list() throws Exception{
		return dao.list();
	}
	
	//글목록 + 페이징
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		return dao.listPage(cri);
	}
	
	//목록 + 페이징 + 검색
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception{
		return dao.listSearch(scri);
	}
	
	//검색 결과 갯수
	@Override
	public int countSearch(SearchCriteria scri) throws Exception{
		return dao.countSearch(scri);
	}
	
	//게시물 총 개수
	public int listCount() throws Exception{
		return dao.listCount();
	}

	//쓰기
	@Override
	public void write(BoardVO vo) throws Exception {
		dao.write(vo);
	}

	//읽기
	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	//수정
	@Override
	public void update(BoardVO vo) throws Exception {
		dao.update(vo);
	}

	//삭제
	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	
}
