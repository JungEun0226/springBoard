package com.board.header.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.board.body.dto.BoardWriteDto;

/**
 * @author choi jung eun
 * @date 2019. 12. 22.
 * @description 헤더와 카테고리 관련 
 */
@Component
public class HeaderDaoImp implements HeaderDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String namespace = "com.board.mapper.HeaderMapper.";
	
	@Override
	public List<BoardWriteDto> getListAll(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+"getListAll", map);
	}

	@Override
	public int getListAllCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getListAllCount");
	}

	@Override
	public int getListCategoryNumCount(int categorynumber) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getListCategoryNumCount", categorynumber);
	}

	@Override
	public List<BoardWriteDto> getListCategoryNum(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+"getListCategoryNum", map);
	}

	@Override
	public String getCategoryName(int categorynumber) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"getCategoryName", categorynumber);
	}

	

}
