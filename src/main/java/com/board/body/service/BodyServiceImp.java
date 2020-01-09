package com.board.body.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.aop.LogAspect;
import com.board.body.dao.BodyDao;
import com.board.body.dto.BoardWriteDto;
import com.board.body.dto.MemberDto;
import com.board.body.dto.ReplyDto;
import com.board.header.service.MainServiceImp;

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
	
	//로그인 - 아이디찾기
	@Override
	public void findId(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		HttpServletResponse response=(HttpServletResponse) mav.getModel().get("response");
		String memberemail=request.getParameter("memberemail");
		
		String memberid=bodyDao.getFindId(memberemail);
		
		response.setContentType("application/text;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(memberid);
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//로그인 - 비밀번호 찾기
	@Override
	public void findPass(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		HttpServletResponse response=(HttpServletResponse) mav.getModel().get("response");
		String memberemail=request.getParameter("memberemail");
		
		String membernumber=bodyDao.getFindPass(memberemail);
		
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
		//String cate=request.getParameter("categoryname");
		
		//LogAspect.info(LogAspect.logMsg+"글번호: "+writenumber+"카테-"+cate);
		
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
			if(writenumber!=null) {		//원래 있던 파일 제거
				String deletePath=bodyDao.getFilePath(writenumber);
				if(deletePath!=null) {	//첨부파일삭제
					File df=new File(deletePath);
					if(df.exists())	df.delete();
				}
			}
			
			while(iter.hasNext()) {
				file=mpRequest.getFile(iter.next());
				
				if(file.isEmpty()==false) {
					
					String name=System.currentTimeMillis()+"_"+file.getOriginalFilename();
					dto.setFilename(name);
					dto.setFilepath(path+name);
					
					File f=new File(path+name);
					file.transferTo(f);
				}
				
				//LogAspect.info(LogAspect.logMsg+dto.toString());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//데이터베이스 등록
		if(writenumber!=null) {	//글 수정, 파일수정시 원본파일삭제하고 수정된파일 넣어야함.
			dto.setWritenumber(Integer.parseInt(writenumber));
			bodyDao.updateWrite(dto);
		}else {
			bodyDao.setBoardWrite(dto);	//글 쓰기
			dto.setWritenumber(bodyDao.getWriteNumber());
		}
		
		if(dto.getFilename()!=null) {
			int index=dto.getFilename().indexOf("_");
			dto.setFilename(dto.getFilename().substring(index+1));			
		}
		
		mav.addObject("boardDto", dto);
		mav.setViewName("body/boardDetail.main");
	}
	
	//글 상세화면 보기
	@Override
	public void boardDetail(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		BoardWriteDto boardDto=null;
		String writenumber=request.getParameter("writenumber");
		String downFilePath="";
		
		//조회수 증가 처리
		bodyDao.updateViews(writenumber);
		
		//글 상세목록 dto로 받아오기
		boardDto=bodyDao.getBoardDetailWriteNumber(writenumber);
		boardDto.setContent(boardDto.getContent().replaceAll("<br>", "\r\n"));	//줄바꿈 처리
		
		if(boardDto.getFilename()!=null) {
			int index=boardDto.getFilename().indexOf("_");	//파일 이름 처리
			downFilePath=boardDto.getFilename();
			boardDto.setFilename(boardDto.getFilename().substring(index+1));
		}

		mav.addObject("downFilePath", downFilePath);
		mav.addObject("boardDto", boardDto);
		
		mav.setViewName("body/boardDetail.main");
	}
	
	//글 삭제 후 메인화면으로 이동
	@Override
	public void deleteWrite(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		HttpServletResponse response=(HttpServletResponse)mav.getModel().get("response");
		String writenumber=request.getParameter("writenumber");
		
		String deletePath=bodyDao.getFilePath(writenumber);
		if(deletePath!=null) {	//첨부파일삭제
			File df=new File(deletePath);
			if(df.exists())	df.delete();
		}
		
		bodyDao.deleteWrite(writenumber);
		
		mav.setViewName("redirect:main.com");
	}
	
	//파일 다운로드
	@Override
	public void downloadFile(ModelAndView mav) throws Exception{
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		HttpServletResponse response=(HttpServletResponse)mav.getModel().get("response");
		
		String writenumber=request.getParameter("writenumber");
		String downFilePath=request.getParameter("downFilePath");
		int index=downFilePath.indexOf("_");
		String path="C:\\Users\\choi\\Desktop\\";
		//LogAspect.info(LogAspect.logMsg+downFilePath);
		
		try {
			byte fileByte[]=FileUtils.readFileToByteArray(new File(path+downFilePath));	//파일 저장경로에서 읽어와 바이트 배열 형태로 변환해주는 함수		
			
			response.setContentType("application/octet-stream");
			response.setContentLength(fileByte.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\""+URLEncoder.encode(downFilePath.substring(index+1), "UTF-8")+"\";"); //attachment는 첨부파일을 의미, URLEncoder는 첨부파일의 이름지정
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}catch (Exception e) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('파일이 존재하지 않습니다'); location.href='/spring/boardDetail.com?writenumber="+writenumber+"';</script>");
			out.close();
		}

	}
	
	//댓글 리스트
	@Override
	public void replyList(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		HttpServletResponse response=(HttpServletResponse) mav.getModel().get("response");
		String writenumber=request.getParameter("writenumber");
		String pn=request.getParameter("pageNumber");
		
		if(pn==null || pn=="")	pn="1";
		int pageNumber=Integer.parseInt(pn);
		int boardSize=10;
		int startRow=(pageNumber-1)*boardSize+1;
		int endRow=pageNumber*boardSize;
		int count=0;
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("writenumber", writenumber);
		
		List<ReplyDto> list=null;

		count=bodyDao.getReplyCount(writenumber);
		ArrayList<Object> arr=new ArrayList<Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(count>0) {
			list=bodyDao.getReplyList(map);

			for(int i=0;i<list.size();i++) {	//줄바꿈 처리
				String content=list.get(i).getReplycontent().replaceAll("<br>", "\r\n");
				list.get(i).setReplycontent(content);
				
				
				HashMap<String,Object> h=new HashMap<String,Object>();
				h.put("membernumber", list.get(i).getMembernumber());
				h.put("memberid", list.get(i).getMemberid());
				h.put("writenumber", list.get(i).getWritenumber());
				h.put("replynumber", list.get(i).getReplynumber());
				h.put("replycontent", list.get(i).getReplycontent());
				h.put("replydate", sdf.format(list.get(i).getReplydate()));
				
				arr.add(h);
			}
		}

		HashMap<String, Object> json=new HashMap<String, Object>();
		json.put("pageNumber", pageNumber);
		json.put("boardSize", boardSize);
		json.put("startRow", startRow);
		json.put("endRow", endRow);
		json.put("count", count);
		json.put("list", arr);
		
		String text=JSONValue.toJSONString(json);
		
		response.setContentType("application/x-json; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(text);
			out.flush();
			//LogAspect.info(LogAspect.logMsg+text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//댓글 등록
	@Override
	public void replyWrite(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		String writenumber=request.getParameter("writenumber");
		
		ReplyDto rDto=new ReplyDto();
		Date date=new Date();
				
		rDto.setReplycontent(request.getParameter("reply").replaceAll("<br>", "\r\n"));
		rDto.setMembernumber(Integer.parseInt(request.getParameter("membernumber")));
		rDto.setWritenumber(Integer.parseInt(writenumber));
		rDto.setMemberid(bodyDao.getMemberId(rDto.getMembernumber()));
		rDto.setReplydate(date);
		
		bodyDao.insertReplyWrite(rDto);
	}
	
	//댓글 수정
	@Override
	public void replyUpdate(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		String replynumber=request.getParameter("replynumber");
		String reply=request.getParameter("reply");
		//LogAspect.info(LogAspect.logMsg+replynumber+"   "+reply);
		
		ReplyDto dto=new ReplyDto();
		Date date=new Date();
		
		dto.setReplycontent(reply);
		dto.setReplynumber(Integer.parseInt(replynumber));
		dto.setReplydate(date);
		
		bodyDao.updateReply(dto);
		
	}
	
	//댓글 삭제
	@Override
	public void replyDelete(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		String replynumber=request.getParameter("replynumber");
		
		bodyDao.deleteReply(replynumber);
		
	}
	
	//마이페이지-비밀번호 수정
	@Override
	public void passwordUpdate(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		String membernumber=request.getParameter("membernumber");
		String memberPassword=request.getParameter("memberPassword");
		
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("membernumber", membernumber);
		map.put("memberPassword", memberPassword);
		
		bodyDao.passwordUpdate(map);
		
	}
	
	//마이페이지-회원탈퇴
	@Override
	public void deleteMember(HttpSession session) {
		// TODO Auto-generated method stub
		String membernumber=(String) session.getAttribute("membernumber");
		LogAspect.info(LogAspect.logMsg+membernumber);
		
		session.invalidate();
		
		bodyDao.deleteMember(membernumber);
	}
	
	//마이페이지 - 내 글 관리, 내 댓글 관리
	@Override
	public void myPostsManage(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		String membernumber=request.getParameter("membernumber");
		String key=request.getParameter("key");
		String pn=request.getParameter("pageNumber");
		
		int pageNumber=Integer.parseInt(pn);
		int boardSize=10;
		int startRow=(pageNumber-1)*boardSize+1;
		int endRow=pageNumber*boardSize;
		int count=0;
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("membernumber", membernumber);
		
		if(key.equals("posts")) {
			count=bodyDao.getBoardWriteCount(membernumber);
		}else {
			count=bodyDao.getReplyCountMemberNum(membernumber);
		}
		
		List<Object> list=null;
		
		if(key.equals("posts")) {	//글 관리
			key="내 글 관리";
			if(count>0) {
				list=bodyDao.getBoardWriteList(map);

				for(int i=0;i<list.size();i++) {
					((BoardWriteDto) list.get(i)).setContent(((BoardWriteDto) list.get(i)).getContent().replaceAll("<br>", "\r\n"));
				}
			}
			
		}else {	//댓글 관리
			key="내 댓글 관리";
			if(count>0) {
				list=bodyDao.getReplyListMemberNum(map);
				
				for(int i=0;i<list.size();i++) {
					((ReplyDto) list.get(i)).setReplycontent(((ReplyDto) list.get(i)).getReplycontent().replaceAll("<br>", "\r\n"));
				}
			}
		}

		
		
		mav.addObject("key", key);
		mav.addObject("membernumber", membernumber);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardSize", boardSize);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", count);
		mav.addObject("list", list);
		
		mav.setViewName("body/myPostsManage.main");
	}

	@Override
	public void myPageDelete(ModelAndView mav) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) mav.getModel().get("request");
		
		String checkValue=request.getParameter("check");
		String key=request.getParameter("key");
		String membernumber=request.getParameter("membernumber");
		
		//LogAspect.info(LogAspect.logMsg+"checkvalue="+checkValue+"     key="+key+"   membernumber="+membernumber);
		
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("key", key);
		
		if(membernumber!=null) {	//전체삭제
			map.put("membernumber", membernumber);
			bodyDao.myPageAllDelete(map);
			
		}else {							//선택삭제
			String[] check=checkValue.split(",");
			
			for(int i=0;i<check.length;i++) {
				map.put("check", check[i]);
				bodyDao.myPageSelectDelete(map);
			}
		}
	}

}
