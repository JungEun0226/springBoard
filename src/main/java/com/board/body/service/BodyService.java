package com.board.body.service;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author choi jung eun
 * @date 2019. 12. 15.
 * @description 바디 관련
 */

public interface BodyService {

	void boardWriteOk(ModelAndView mav);

	void memberIdCheck(ModelAndView mav);

	void emailCheck(ModelAndView mav);

	void signupOk(ModelAndView mav);

	void loginOk(ModelAndView mav);

	void boardDetail(ModelAndView mav);

	void deleteWrite(ModelAndView mav);

	void downloadFile(ModelAndView mav) throws Exception;
	
}
