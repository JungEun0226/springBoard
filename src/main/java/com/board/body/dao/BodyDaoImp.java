package com.board.body.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.board.body.dto.BoardWriteDto;
import com.board.body.dto.MemberDto;
import com.board.body.dto.ReplyDto;

/**
 * @author choi jung eun
 * @date 2019. 12. 17.
 * @description 바디관련 데이터베이스 
 */
@Component
public class BodyDaoImp implements BodyDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String namespace = "com.board.mapper.BodyMapper.";

	@Override
	public int getMemberIdCheck(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getMemberIdCheck", id);
	}

	@Override
	public int getEmailCheck(String email) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getEmailCheck", email);
	}

	@Override
	public void setSignUp(MemberDto dto) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+"setSignUp", dto);
	}

	@Override
	public String login(MemberDto dto) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"login", dto);
	}

	@Override
	public int getCategoryNumber(String categoryname) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getCategoryNumber", categoryname);
	}

	@Override
	public void setBoardWrite(BoardWriteDto dto) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+"setBoardWrite", dto);
	}

	@Override
	public BoardWriteDto getBoardDetailWriteNumber(String writenumber) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getBoardDetailWriteNumber", writenumber);
	}

	@Override
	public void updateViews(String writenumber) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+"updateViews", writenumber);
	}

	@Override
	public void updateWrite(BoardWriteDto dto) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+"updateWrite", dto);
	}

	@Override
	public void deleteWrite(String writenumber) {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+"deleteWrite", writenumber);
	}

	@Override
	public String getFilePath(String writenumber) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getFilePath", writenumber);
	}

	@Override
	public int getReplyCount(String writenumber) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getReplyCount", writenumber);
	}

	@Override
	public List<ReplyDto> getReplyList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+"getReplyList", map);
	}

	@Override
	public String getMemberId(int membernumber) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getMemberId", membernumber);
	}

	@Override
	public void insertReplyWrite(ReplyDto rDto) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+"insertReplyWrite", rDto);
	}

	@Override
	public void passwordUpdate(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+"passwordUpdate",map);
	}

	@Override
	public void deleteMember(String membernumber) {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+"deleteMember",membernumber);
	}



}
