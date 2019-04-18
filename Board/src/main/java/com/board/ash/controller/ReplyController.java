package com.board.ash.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.ash.domain.ReplyDomain;
import com.board.ash.service.BoardService;

@Controller
public class ReplyController {

	@Autowired 
	BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value="/reply")
	@ResponseBody
	public List<ReplyDomain> reply(Model model, @RequestBody ReplyDomain replyDomain){
		System.out.println("댓글내용:"+ replyDomain.getReply_content());
		//현재 글 번호를 조건으로 댓글달아야함
		System.out.println("현재글번호:"+replyDomain.getNum());
		int num = replyDomain.getNum();
		boardService.writeReply(replyDomain);
		
		List<ReplyDomain> bs = boardService.replyList(num);
		return bs;
	}
	
	@RequestMapping(value="/replyDelete")
	public String replyDelete(Model model,ReplyDomain rd) {
		int num = rd.getNum();

		boardService.replyDelete(num);
		
		return "redirect:/contentView?num="+num;
	}

}
