package com.test.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.MemberVO;
import com.test.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	//회원 가입 get
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void getRegister() throws Exception{
		logger.info("get register");
	}
	
	//회원 가입 post
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception{
		logger.info("post register");
		
		String inputPass = vo.getUserPass();
		String pass = passEncoder.encode(inputPass);
		vo.setUserPass(pass);
		
		service.register(vo);
		
		return "redirect:/";
	}
	
	//로그인
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		HttpSession session = req.getSession(); //세션객체 생성
		MemberVO login = service.login(vo);
		
		boolean passMatch = passEncoder.matches(vo.getUserPass(), login.getUserPass());
		
		if(login!=null && passMatch) {
			session.setAttribute("member", login);
		}else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg",false);
		}
		
		/*
		 * if(login == null) { session.setAttribute("member", null); //로그인 입력 값이 데이터베이스
		 * 없으면 세션에 null rttr.addFlashAttribute("msg",false); } else {
		 * session.setAttribute("member", login); //로그인 입력 값이 데이터베이스에 있으면 세션에 MemberVO
		 * 객체 }
		 */
		
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		logger.info("get logout");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원정보 수정 get
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void getModify() throws Exception{
		logger.info("get modify");
	}
	
	//회원정보 수정 post
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String postModify(HttpSession session,MemberVO vo) throws Exception{
		logger.info("post modify");
		
		service.modify(vo);
		
		session.invalidate();
		
		return "redirect:/";	
	}
	
	//회원 탈퇴 get
	@RequestMapping(value="/withdrawal", method= RequestMethod.GET)
	public void getWithdrawal() throws Exception{
		logger.info("get withdrawal");
	}
	
	//회원 탈퇴 post
	@RequestMapping(value="/withdrawal", method= RequestMethod.POST)
	public String postWithdrawal(HttpSession session, MemberVO vo, RedirectAttributes rttr) throws Exception{
		logger.info("post withdrawal");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		String oldPass = member.getUserPass();
		String newPass = vo.getUserPass();
	
		if(!(oldPass.equals(newPass))) {
			rttr.addFlashAttribute("msg",false);
			return "redirect:/member/withdrawal";
		}
		
		service.withdrawal(vo);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원 확인
	@ResponseBody
	@RequestMapping(value="/idCheck", method=RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) throws Exception{
		logger.info("post idCheck");
		
		String userId = req.getParameter("userId");
		MemberVO idCheck = service.idCheck(userId);
		
		int result = 0;
		
		if(idCheck != null) {
			result = 1; //이미 가입된 아이디가 있으면 1 반환
		}
		
		return result;
	}
}
