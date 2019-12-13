package com.board.header.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author choi jung eun
 * @date 2019. 12. 13.
 * @description 헤더 관련 서비스
 */

@Component
public class MainServiceImp implements MainService {

	@Override
	public void main(ModelAndView mav) {
		// TODO Auto-generated method stub
		//메인화면에 가져갈 글목록과 페이징 처리필요. 
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		
		mav.setViewName("header/header.main");
	}
	
}
