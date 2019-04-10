package com.board.ash.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.ash.domain.BoardDomain;
import com.board.ash.domain.Criteria;
import com.board.ash.domain.PageMaker;
import com.board.ash.domain.SearchCriteria;
import com.board.ash.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired 
	BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//메인페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	//게시판
	@RequestMapping("/intoBoard") 
	public String intoBoard(Model model, BoardDomain boardDomain, SearchCriteria scri) {
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardService.totalList());
		pageMaker.setStartCount((scri.getPage()-1)*10+1);
		pageMaker.setEndCount((scri.getPage()-1)*10+1+pageMaker.getDisplayPageNum());
	
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", boardService.list(pageMaker,scri));
		
		System.out.println(scri.getKeyword());
		System.out.println(scri.getSearchType());
		return "board"; 
	}
	//글내용보기
	@RequestMapping("/contentView")
	public String contentView(Model model, @RequestParam("num") int num,Criteria cri) {
		model.addAttribute("page",cri);
		model.addAttribute("view", boardService.contentView(num));
		//타이틀을 누르면, hit메서드 발동.
		//hit메서드에 num을 넘긴후, hit를 +1업데이트한다.
		return "contentView";
	}
	//글쓰기페이지
	@RequestMapping("/writePage")
	public String writePage(Model model, Locale locale){
		return "writePage";
	}
	//글쓰기
	@RequestMapping("/write")
	public String write(BoardDomain boardDomain) {
		boardService.write(boardDomain);
		return "redirect:/intoBoard";
	}
	//삭제
	@RequestMapping("/delete")
	public String delete(Model model, @RequestParam int num) {
		boardService.delete(num);
		System.out.println("넘버:"+num);
		return "redirect:/intoBoard";
	}
	//수정페이지
	@RequestMapping("/modifyPage")
	public String modifyPage(Model model, @RequestParam int num) {
		model.addAttribute("view", boardService.contentView(num));
		return "modifyPage";
	}
	//수정
	@RequestMapping("/modify")
	public String modify(BoardDomain boardDomain) {
		boardService.modify(boardDomain);
		return "redirect:/contentView?num="+boardDomain.getNum();
	}
}
