package com.board.body.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.board.aop.LogAspect;
import com.board.body.dao.BodyDao;

/**
 * @author choi jung eun
 * @date 2019. 12. 15.
 * @description 바디 관련 서비스
 */

@Component
public class BodyServiceImp implements BodyService {
	@Autowired
	private BodyDao bodyDao;
	
	@Override
	public void boardWrite(ModelAndView mav) {
		// TODO Auto-generated method stub
		//글쓰기 필요
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		LogAspect.info(LogAspect.logMsg+ "글쓰기");
		
		mav.setViewName("body/boardWrite.main");
		
	}
	
	//아이디 중복체크
	@Override
	public Integer memberIdCheck(String id) {
		// TODO Auto-generated method stub
		
		int result=bodyDao.getMemberIdCheck("id");
		LogAspect.info(LogAspect.logMsg+ "아이디체크"+result);
		
		return result;
	}

}
