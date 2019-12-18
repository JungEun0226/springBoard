package com.board.body.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.board.aop.LogAspect;
import com.board.body.dao.BodyDao;
import com.board.body.dto.MemberDto;

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
	public void memberIdCheck(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		HttpServletResponse response=(HttpServletResponse) mav.getModel().get("response");
		
		int result=bodyDao.getMemberIdCheck(request.getParameter("memberid"));
		//LogAspect.info(LogAspect.logMsg+ "아이디체크"+result+"원래아이디"+request.getParameter("memberid"));
		
		response.setContentType("application/text;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//이메일 중복체크
	@Override
	public void emailCheck(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		HttpServletResponse response=(HttpServletResponse) mav.getModel().get("response");
		
		int result=bodyDao.getEmailCheck(request.getParameter("memberEmail"));
		//LogAspect.info(LogAspect.logMsg+ "아이디체크"+result+"원래아이디"+request.getParameter("memberid"));
		
		response.setContentType("application/text;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//회원가입
	@Override
	public void signupOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		String id=request.getParameter("memberID");
		String password=request.getParameter("memberPassword");
		String email=request.getParameter("memberEmail");
		
		MemberDto dto=new MemberDto();
		dto.setMemberid(id);
		dto.setMemberpass(password);
		dto.setMemberemail(email);
		
		bodyDao.setSignUp(dto);
			
		
	}

}
