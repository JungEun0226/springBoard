package com.board.body.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.board.body.dto.BoardWriteDto;
import com.board.body.dto.MemberDto;

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
	public BoardWriteDto getBoardDetailWriteNumber(String wn) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getBoardDetailWriteNumber", wn);
	}

	@Override
	public void updateViews(String wn) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+"updateViews", wn);
	}


}
