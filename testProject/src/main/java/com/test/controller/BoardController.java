package com.test.controller;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.PageMaker;
import com.test.domain.ReplyVO;
import com.test.domain.SearchCriteria;
import com.test.service.BoardService;
import com.test.service.ReplyService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	@Inject
	ReplyService RepService;
	
	//글목록
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception{
		logger.info("get list");
		
		List<BoardVO> list = service.list();
		
		model.addAttribute("list",list);
	}
	
	//글목록+페이징
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception{
		logger.info("get list page");
		
		List<BoardVO> list = service.listPage(cri);
		model.addAttribute("list",list);

		PageMaker pageMaker = new PageMaker(); //페이지메이커 인스턴스 생성
		pageMaker.setCri(cri); //Criteria 셋
		pageMaker.setTotalCount(service.listCount()); //게시물총개수 구한 걸 페이지메이커에 넣는다
		model.addAttribute("pageMaker",pageMaker);
	}
	
	//글목록 + 페이징 + 검색
	@RequestMapping(value="/listSearch",method=RequestMethod.GET)
	public void listSearch(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("get list search");
		
		List<BoardVO> list = service.listSearch(scri);
		model.addAttribute("list",list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		//pageMaker.setTotalCount(service.listCount());
		pageMaker.setTotalCount(service.countSearch(scri));
		model.addAttribute("pageMaker",pageMaker);
	
		System.out.println("검색타입:"+scri.getSearchType()+"  키워드:"+scri.getKeyword());
	}
	
	
	//글 조회
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void getRead(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("get read");
		
		BoardVO vo = service.read(bno);
		model.addAttribute("read",vo);
		model.addAttribute("scri",scri);
		
		List<ReplyVO> repList = RepService.readReply(bno);
		model.addAttribute("repList", repList);
	}
	
	//글작성 get
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void getWrite(HttpSession session, Model model) throws Exception{
		logger.info("get write");
		
		Object loginInfo = session.getAttribute("member");
		
		if(loginInfo == null) {
			model.addAttribute("msg",false);
		}
	}
	
	//글작성 post
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String postWrite(BoardVO vo) throws Exception{
		logger.info("post write");
		
		service.write(vo);
		return "redirect:/";
	}
	
	//글 수정
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("get modify");
		
		BoardVO vo = service.read(bno);
		
		model.addAttribute("modify",vo);
		model.addAttribute("scri",scri);
	}
	
	//글 수정 post
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String postModify(BoardVO vo, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("post modify");
		
		service.update(vo);

		rttr.addAttribute("page",scri.getPage());
		rttr.addAttribute("perPageNum",scri.getPerPageNum());
		rttr.addAttribute("searchType",scri.getSearchType());
		rttr.addAttribute("keyword",scri.getKeyword());
		
		return "redirect:/board/listSearch";
	}
		
	//글 삭제 
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public void getDelete(@RequestParam("bno") int bno, SearchCriteria scri, Model model) throws Exception{
		logger.info("get delete");
		
		model.addAttribute("delete",bno);
		model.addAttribute("scri",scri);
	}
	
	//글 삭제 post
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String postDelete(@RequestParam("bno") int bno,SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("post delete");
			
		service.delete(bno);
		
		rttr.addAttribute("page",scri.getPage());
		rttr.addAttribute("perPageNum",scri.getPerPageNum());
		rttr.addAttribute("searchType",scri.getSearchType());
		rttr.addAttribute("keyword",scri.getKeyword());

		return "redirect:/board/listSearch";
	}
	
	//댓글 작성
	@RequestMapping(value="/replyWrite",method = RequestMethod.POST)
	public String replyWrite(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("reply write");
		
		RepService.writeReply(vo);
		
		rttr.addAttribute("bno",vo.getBno());
		rttr.addAttribute("page",scri.getPage());
		rttr.addAttribute("perPageNum",scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword",scri.getKeyword());
		
		return "redirect:/board/read";
	}
	
	//댓글 수정 POST
	@RequestMapping(value="/replyUpdate",method=RequestMethod.POST)
	public String replyUpdate(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("reply update");
		
		RepService.replyUpdate(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/read";
	}
	
	//댓글 삭제 POST
	@RequestMapping(value="/replyDelete",method=RequestMethod.POST)
	public String replyDelete(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("reply delete");
		
		RepService.replyDelete(vo);
		
		rttr.addAttribute("bno",vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum",scri.getPerPageNum());
		rttr.addAttribute("searchType",scri.getSearchType());
		rttr.addAttribute("keyword",scri.getKeyword());
		
		return "redirect:/board/read";
	}
	
	//댓글 수정 GET
	@RequestMapping(value="/replyUpdate", method = RequestMethod.GET)
	public void getReplyUpdate(@RequestParam("rno") int rno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("reply delete");
		
		ReplyVO vo = null;
		
		vo = RepService.readReplySelect(rno);
		
		model.addAttribute("readReply",vo);
		model.addAttribute("scri",scri);
	}
	
	//댓글 삭제 GET
	@RequestMapping(value="/replyDelete", method = RequestMethod.GET)
	public void getReplyDelete(@RequestParam("rno") int rno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("reply delete");
		
		ReplyVO vo = null;
		
		vo = RepService.readReplySelect(rno);
		
		model.addAttribute("readReply",vo);
		model.addAttribute("scri",scri);
	}
}
