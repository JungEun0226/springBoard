package com.board.header.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.board.aop.LogAspect;
import com.board.header.service.MainService;

/**
 * @author choi jung eun
 * @date 2019. 12. 13.
 * @description 헤더 관련 컨트롤러
 */

@Controller
public class HeaderController {
	
	@Autowired
	private MainService mainService;
	
	/**
	 * 메인화면과 전체글보기화면
	 */
	@RequestMapping(value = {"/","/main.com"}, method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request",request);
		
		mainService.main(mav);
		
		return mav;
	}
	
}
