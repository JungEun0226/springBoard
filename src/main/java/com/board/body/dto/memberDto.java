package com.board.body.dto;

/**
 * @author choi jung eun
 * @date 2019. 12. 17.
 * @description 회원정보 dto
 */
public class memberDto {
	private int membernumber;
	private String memberid;
	private String memberpass;
	private String memberemail;
	
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
	public String getMemberpass() {
		return memberpass;
	}
	public void setMemberpass(String memberpass) {
		this.memberpass = memberpass;
	}
	public String getMemberemail() {
		return memberemail;
	}
	public void setMemberemail(String memberemail) {
		this.memberemail = memberemail;
	}
	@Override
	public String toString() {
		return "memberDto [membernumber=" + membernumber + ", memberid=" + memberid + ", memberpass=" + memberpass
				+ ", memberemail=" + memberemail + "]";
	}
	
	
}
