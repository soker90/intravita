package com.mensubiqua.intravita.model;

public class UserCode {
	private String nickname;
	private String code;
	//private double time;
	
	public UserCode(String nickname, String code) {
		this.nickname=nickname;
		this.code=code;
		//this.time=time;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	 
}
