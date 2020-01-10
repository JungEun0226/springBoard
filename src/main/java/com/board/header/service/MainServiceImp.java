package com.board.header.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.board.aop.LogAspect;
import com.board.body.dto.BoardWriteDto;
import com.board.header.dao.HeaderDao;

/**
 * @author choi jung eun
 * @date 2019. 12. 13.
 * @description 헤더 관련 서비스
 */

@Component
public class MainServiceImp implements MainService {
	@Autowired
	private HeaderDao headerDao;

	@Override
	public void main(ModelAndView mav) {
		// TODO Auto-generated method stub
		//글목록과 페이징 처리
		HttpServletRequest request=(HttpServletRequest)mav.getModel().get("request");
		
		String pn=request.getParameter("pageNumber");	//페이지기능
		if(pn==null)	pn="1";
		int pageNumber=Integer.parseInt(pn);
		
		String cateNum=request.getParameter("categorynumber");
		if(cateNum==null)	cateNum="0";
		int categorynumber=Integer.parseInt(cateNum);
		
		int boardSize=5;
		int startRow=(pageNumber-1)*boardSize+1;
		int endRow=pageNumber*boardSize;
		int count=0;
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("categorynumber", categorynumber);
		
		List<BoardWriteDto> list=null;
		if(categorynumber==0) {
			count=headerDao.getListAllCount();
			list=headerDao.getListAll(map);			
		}else {
			count=headerDao.getListCategoryNumCount(categorynumber);
			list=headerDao.getListCategoryNum(map);
		}
		
		
		for(int i=0;i<list.size();i++) {	//줄바꿈 처리
			String content=list.get(i).getContent().replaceAll("<br>", "<span>");
			list.get(i).setContent(content);
		}
		
		//카테고리 이름 가져오기
		String categoryname=headerDao.getCategoryName(categorynumber);
		
		mav.addObject("categoryname", categoryname);
		mav.addObject("categorynumber", categorynumber);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardSize", boardSize);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("count", count);
		mav.addObject("list", list);
		
		mav.setViewName("body/body.main");
	}
	
}
