package com.board.ash.domain;

public class Criteria {
	private int page; //보여줄 페이지
	private int perPageNum; //페이지당 보여줄 페이지 수
	private int rowStart; //게시물 보여줄 행개수 첫번째 행
	private int rowEnd; //게시물 보여줄 행개수 마지막 행
	
	public int getPageStart() {
		return (this.page-1)*this.perPageNum;
	}
	
	public Criteria() { //최초 default 생성자로 기본 객체 생성시 초기값을 지정한다(1페이지 10개씩)
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page<=0) {
			//페이지는 1페이지부터임으로 0보다 작거나 같은값일 경우 무조건 첫번째 페이지로 설정되도록 함
			this.page = 1;
		}else {
			this.page = page;
		}
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum<=0 || perPageNum>100) {
			this.perPageNum = 10;
		}else {
			this.perPageNum = perPageNum;
		}
	}
	
	public int getRowStart() {
		rowStart = ((page-1) * perPageNum) + 1;
		return rowStart;
	}
	
	public int getRowEnd() {
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}
	
}
