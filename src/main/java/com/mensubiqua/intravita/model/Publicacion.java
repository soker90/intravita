package com.mensubiqua.intravita.model;

import java.util.Date;

import org.springframework.stereotype.Component;

public class Publicacion {

    private String nickname;
    private String texto;
    private String privacidad;
    private Date fecha;
    
	public Publicacion(String nickname, String texto, String privacidad, Date fecha) {
		this.nickname = nickname;
		this.texto = texto;
		this.privacidad = privacidad;
		this.fecha = fecha;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getPrivacidad() {
		return privacidad;
	}
	public void setPrivacidad(String privacidad) {
		this.privacidad = privacidad;
	}
    
}
