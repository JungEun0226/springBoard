package com.board.body.dto;

import java.util.Date;

/**
 * @author choi jung eun
 * @date 2019. 12. 25.
 * @description 댓글 dto
 */

public class ReplyDto {
	private int membernumber;
	private String memberid;
	private int writenumber;
	private int replynumber;
	private String replycontent;
	private Date replydate;
	
	public int getMembernumber() {
		return membernumber;
	}
	public void setMembernumber(int membernumber) {
		this.membernumber = membernumber;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public int getWritenumber() {
		return writenumber;
	}
	public void setWritenumber(int writenumber) {
		this.writenumber = writenumber;
	}
	public int getReplynumber() {
		return replynumber;
	}
	public void setReplynumber(int replynumber) {
		this.replynumber = replynumber;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public Date getReplydate() {
		return replydate;
	}
	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	@Override
	public String toString() {
		return "ReplyDto [membernumber=" + membernumber + ", memberid=" + memberid + ", writenumber=" + writenumber
				+ ", replynumber=" + replynumber + ", replycontent=" + replycontent + ", replydate=" + replydate + "]";
	}
	
	
}
