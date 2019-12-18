package com.board.body.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
