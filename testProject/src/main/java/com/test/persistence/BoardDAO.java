package com.test.persistence;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.SearchCriteria;

public interface BoardDAO {
	//목록
	public List<BoardVO> list() throws Exception;
	
	//목록 + 페이징
	public List<BoardVO> listPage(Criteria cri) throws Exception;
	
	//목록 + 페이징 + 검색
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception;

	//검색 결과 갯수
	public int countSearch(SearchCriteria scri) throws Exception;
	
	//게시물 총 갯수
	public int listCount() throws Exception;
	
	//작성
	public void write(BoardVO vo) throws Exception;
	
	//조회
	public BoardVO read(int bno) throws Exception;
	
	//수정
	public void update(BoardVO vo) throws Exception;
	
	//삭제
	public void delete(int bno) throws Exception;
}
