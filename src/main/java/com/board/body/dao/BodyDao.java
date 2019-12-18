package com.board.body.dao;

import com.board.body.dto.MemberDto;

public interface BodyDao {

	int getMemberIdCheck(String id);

	int getEmailCheck(String email);

	void setSignUp(MemberDto dto);

}
