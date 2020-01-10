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

	String getMemberId(int membernumber);

	void insertReplyWrite(ReplyDto rDto);

	void passwordUpdate(HashMap<String, String> map);

	void deleteMember(String membernumber);

	int getWriteNumber();

	void updateReply(ReplyDto dto);

	void deleteReply(String replynumber);

	String getFindId(String memberemail);

	String getFindPass(String memberemail);

	int getBoardWriteCount(String membernumber);

	int getReplyCountMemberNum(String membernumber);

	List<Object> getBoardWriteList(HashMap<String, Object> map);

	List<Object> getReplyListMemberNum(HashMap<String, Object> map);

	void myPageSelectDelete(HashMap<String, Object> map);

	void myPageAllDelete(HashMap<String, Object> map);

	String getFileName(String writenumber);




}
