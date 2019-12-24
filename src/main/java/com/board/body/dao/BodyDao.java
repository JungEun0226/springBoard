package com.board.body.dao;

import com.board.body.dto.BoardWriteDto;
import com.board.body.dto.MemberDto;

public interface BodyDao {

	int getMemberIdCheck(String id);

	int getEmailCheck(String email);

	void setSignUp(MemberDto dto);

	String login(MemberDto dto);

	int getCategoryNumber(String categoryname);

	void setBoardWrite(BoardWriteDto dto);

	BoardWriteDto getBoardDetailWriteNumber(String wn);

	void updateViews(String wn);

	void updateWrite(BoardWriteDto dto);

	void deleteWrite(String writenumber);

	String getFilePath(String writenumber);



}
