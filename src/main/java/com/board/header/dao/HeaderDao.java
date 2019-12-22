package com.board.header.dao;

import java.util.HashMap;
import java.util.List;

import com.board.body.dto.BoardWriteDto;

public interface HeaderDao {

	List<BoardWriteDto> getListAll(HashMap<String, Object> map);

	int getListAllCount();

	int getListCategoryNumCount(int categorynumber);

	List<BoardWriteDto> getListCategoryNum(HashMap<String, Object> map);

}
