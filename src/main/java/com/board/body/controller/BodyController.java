package com.board.body.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	//회원가입
	@RequestMapping(value = "/signup.com", method = RequestMethod.GET)
	public String signUp() {
		LogAspect.info(LogAspect.logMsg+"회원가입진입");
		
		return "body/signUp.main";
	}
	
	//글쓰기 페이지
	@RequestMapping(value = "/boardWrite.com", method = RequestMethod.GET)
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) {
		//로그인 정보 가지고 와서 아이디 가져가야함
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		//LogAspect.info(LogAspect.logMsg+"글쓰기진입");
		bodyService.boardWrite(mav);
		
		
		return mav;
	}
}
