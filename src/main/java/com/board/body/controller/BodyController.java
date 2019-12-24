package com.board.body.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.board.aop.LogAspect;
import com.board.body.service.BodyService;

/**
 * @author choi jung eun
 * @date 2019. 12. 15.
 * @description 바디 컨트롤러
 */

@Controller
public class BodyController {
	
	@Autowired
	private BodyService bodyService;
	
	//회원가입 페이지
	@RequestMapping(value = "/signup.com", method = RequestMethod.GET)
	public String signUp() {
		//LogAspect.info(LogAspect.logMsg+"회원가입진입");
		
		return "body/signUp.main";
	}
	
	//아이디중복체크
	@RequestMapping(value = "/memberidcheck.com", method = RequestMethod.GET)
	public ModelAndView memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		//로그인 정보 가지고 와서 아이디 가져가야함
		//LogAspect.info(LogAspect.logMsg+"아이디");
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);
		
		bodyService.memberIdCheck(mav);
		
		return null;
	}
	
	//이메일 중복 체크
	@RequestMapping(value = "/emailCheck.com", method = RequestMethod.GET)
	public ModelAndView emailCheck(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);
		
		bodyService.emailCheck(mav);
		
		return null;
	}
	
	//회원가입 처리 insert
	@RequestMapping(value = "/signupOk.com", method = RequestMethod.POST)
	public ModelAndView signupOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		bodyService.signupOk(mav);
		
		return null;
	}
	
	//로그인 페이지 
	@RequestMapping(value = "/login.com", method = RequestMethod.GET)
	public String login() {
		
		return "body/login.main";
	}
	
	//로그인 처리
	@RequestMapping(value = "/loginOk.com", method = RequestMethod.POST)
	public ModelAndView loginOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);
		//LogAspect.info(LogAspect.logMsg+"아이디"+request.getParameter("id")+"비밀번호"+request.getParameter("pass"));
		
		bodyService.loginOk(mav);
		
		return null;
	}
	
	//로그아웃 처리
	@RequestMapping(value = "/logout.com", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//세션 초기화
		session.invalidate();
		
		return "body/body.main";
	}
	
	//글쓰기 페이지
	@RequestMapping(value = "/boardWrite.com", method = RequestMethod.GET)
	public String boardWrite() {
	
		return "body/boardWrite.main";
	}
	
	//글쓰기 등록
	@RequestMapping(value = "/boardWriteOk.com", method = RequestMethod.POST)
	public ModelAndView boardWriteOk(HttpServletRequest request, HttpServletResponse response) {
		//로그인 정보 가지고 와서 아이디 가져가야함
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		//String c=request.getParameter("categorySelect");
		//LogAspect.info(LogAspect.logMsg+"글쓰기등록"+c);
		
		bodyService.boardWriteOk(mav);
		
		
		return mav;
	}
	
	//글 상세화면 이동 
	@RequestMapping(value = "/boardDetail.com", method = RequestMethod.GET)
	public ModelAndView boardDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		//LogAspect.info(LogAspect.logMsg+"글 상세화면");
		
		bodyService.boardDetail(mav);
		
		
		return mav;
	}
	
	//글 수정화면 이동 updateWrite.com
	@RequestMapping(value = "/updateWrite.com", method = RequestMethod.GET)
	public ModelAndView updateWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("writenumber", request.getParameter("writenumber"));		
		mav.setViewName("body/updateWrite.main");
		
		return mav;
	}
	
	//글 삭제 & 메인화면으로 돌아가기
	@RequestMapping(value = "/deleteWrite.com", method = RequestMethod.GET)
	public ModelAndView deleteWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		bodyService.deleteWrite(mav);
		
		return mav;
	}
}
