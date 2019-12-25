package com.board.body.dao;

import java.util.HashMap;
import java.util.List;

import com.board.body.dto.BoardWriteDto;
import com.board.body.dto.MemberDto;
import com.board.body.dto.ReplyDto;

public interface BodyDao {

	int getMemberIdCheck(String id);

	int getEmailCheck(String email);

	void setSignUp(MemberDto dto);

	String login(MemberDto dto);

	int getCategoryNumber(String categoryname);

	void setBoardWrite(BoardWriteDto dto);

	BoardWriteDto getBoardDetailWriteNumber(String writenumber);

	void updateViews(String writenumber);

	void updateWrite(BoardWriteDto dto);

	void deleteWrite(String writenumber);

	String getFilePath(String writenumber);

	int getReplyCount(String writenumber);

	List<ReplyDto> getReplyList(HashMap<String, Object> map);



}
