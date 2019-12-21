package com.board.body.dto;

import java.util.Date;

/**
 * @author choi jung eun
 * @date 2019. 12. 22.
 * @description 글쓰기 dto
 */

public class BoardWriteDto {
	private int membernumber;
	private int categorynumber;
	private int writenumber;
	private String title;
	private String content;
	private int views;
	private Date writedate;
	private String filename;
	private String filepath;
	
	public int getMembernumber() {
		return membernumber;
	}
	public void setMembernumber(int membernumber) {
		this.membernumber = membernumber;
	}
	public int getCategorynumber() {
		return categorynumber;
	}
	public void setCategorynumber(int categorynumber) {
		this.categorynumber = categorynumber;
	}
	public int getWritenumber() {
		return writenumber;
	}
	public void setWritenumber(int writenumber) {
		this.writenumber = writenumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@Override
	public String toString() {
		return "BoardWrire [membernumber=" + membernumber + ", categorynumber=" + categorynumber + ", writenumber="
				+ writenumber + ", title=" + title + ", content=" + content + ", views=" + views + ", writedate="
				+ writedate + ", filename=" + filename + ", filepath=" + filepath + "]";
	}
	
	
}
