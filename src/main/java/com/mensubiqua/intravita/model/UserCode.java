package com.mensubiqua.intravita.model;

/**
 * UserCode - Clase de dominio que contiene toda la información relacionada 
 * con la validacion de una cuenta (codigo y usuario)
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José María Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.4
 * @version 1.8
 */
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
