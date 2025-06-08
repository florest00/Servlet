package com.kh.app.member;

public class MemberVo {
	
	private String no;
	private String userId;
	private String userPwd;
	private String userNick;
	private String createdDate;
	private String delYn;
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVo(String no, String userId, String userPwd, String userNick, String createdDate, String delYn) {
		super();
		this.no = no;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userNick = userNick;
		this.createdDate = createdDate;
		this.delYn = delYn;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", userId=" + userId + ", userPwd=" + userPwd + ", userNick=" + userNick
				+ ", createdDate=" + createdDate + ", delYn=" + delYn + "]";
	}
	
	

}
