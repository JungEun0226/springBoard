package com.board.body.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.aop.LogAspect;
import com.board.body.dao.BodyDao;
import com.board.body.dto.BoardWriteDto;
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
	
	//로그인 시도
	@Override
	public void loginOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		HttpServletResponse response=(HttpServletResponse) mav.getModel().get("response");
		String id=request.getParameter("id");
		String password=request.getParameter("pass");
		
		MemberDto dto=new MemberDto();
		dto.setMemberid(id);
		dto.setMemberpass(password);
		
		String membernumber=bodyDao.login(dto);
		//LogAspect.info(LogAspect.logMsg+"회원 번호"+membernumber);
		
		if(membernumber!=null) {	//로그인성공시
			request.getSession().setAttribute("membernumber", membernumber);	//아이디랑 회원번호 세션에 넣어줌
			request.getSession().setAttribute("memberid", id);
			request.getSession().setMaxInactiveInterval(60*30);	//세션 유지 시간 30분.
		}
		
		response.setContentType("application/text;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(membernumber);
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//글쓰기 등록
	@Override
	public void boardWriteOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		String writenumber=request.getParameter("writenumber");
		
		LogAspect.info(LogAspect.logMsg+"글번호: "+writenumber);
		
		Date date=new Date();
		
		//카테고리 이름으로 카테고리 번호 찾기
		BoardWriteDto dto=new BoardWriteDto();
		dto.setMembernumber(Integer.valueOf((String) request.getSession().getAttribute("membernumber")));
		dto.setCategorynumber(bodyDao.getCategoryNumber(request.getParameter("categoryname")));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content").replaceAll("\\r\\n", "<br>"));	//줄바꿈 처리
		dto.setWritedate(date);
		
		//파일 업로드 처리
		MultipartHttpServletRequest mpRequest=(MultipartHttpServletRequest) request;
		Iterator<String> iter=mpRequest.getFileNames();
		MultipartFile file=null;
		String path="C:\\Users\\choi\\Desktop\\";
		
		try {
			while(iter.hasNext()) {
				file=mpRequest.getFile(iter.next());
				
				if(file.isEmpty()==false) {
					if(writenumber!=null) {		//원래 있던 파일 제거
						String deletePath=bodyDao.getFilePath(writenumber);
						File df=new File(deletePath);
						if(df.exists())	df.delete();
					}
					
					String name=System.currentTimeMillis()+"_"+file.getOriginalFilename();
					dto.setFilename(name);
					dto.setFilepath(path+name);
					
					File f=new File(path+name);
					file.transferTo(f);
				}
				
				LogAspect.info(LogAspect.logMsg+dto.toString());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//데이터베이스 등록
		if(writenumber!=null) {	//글 수정, 파일수정시 원본파일삭제하고 수정된파일 넣어야함.
			dto.setWritenumber(Integer.parseInt(writenumber));
			bodyDao.updateWrite(dto);
			mav.addObject("dto", dto);
			mav.setViewName("body/boardDetail.main");
			
		}else {
			bodyDao.setBoardWrite(dto);	//글 쓰기
			mav.addObject("categorynumber",dto.getCategorynumber());
			mav.setViewName("body/body.main");	
		}
		
		
	}
	
	//글 상세화면 보기
	@Override
	public void boardDetail(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		BoardWriteDto dto=null;
		String wn=request.getParameter("writenumber");
		
		//조회수 증가 처리
		bodyDao.updateViews(wn);
		
		//글 상세목록 dto로 받아오기
		dto=bodyDao.getBoardDetailWriteNumber(wn);
		dto.setContent(dto.getContent().replaceAll("<br>", "\r\n"));	//줄바꿈 처리
		
		if(dto.getFilename()!=null) {
			int index=dto.getFilename().indexOf("_");	//파일 이름 처리
			dto.setFilename(dto.getFilename().substring(index));
		}
		
		
		mav.addObject("dto", dto);
		mav.setViewName("body/boardDetail.main");
	}
	
	//글 삭제 후 메인화면으로 이동
	@Override
	public void deleteWrite(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		String writenumber=request.getParameter("writenumber");
		
		bodyDao.deleteWrite(writenumber);
		
		mav.setViewName("body/body.main");
	}


}
