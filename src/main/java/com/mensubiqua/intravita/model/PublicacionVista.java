package com.mensubiqua.intravita.model;

import java.util.Date;

import org.springframework.stereotype.Component;

public class PublicacionVista {

	private String id;
    private String nickname;
    private String texto;
    private String privacidad;
    private String fecha;
    private String unombre;
    private String ufoto;
    
	public PublicacionVista(Publicacion p, User u) {
		this.texto = p.getTexto();
		this.privacidad = p.getPrivacidad();
		this.fecha = p.getFecha();
		this.nickname = p.getNickname();
		this.id = p.getId();
		this.unombre = u.getNombre() + " " + u.getApellido();
		this.ufoto = u.getFoto();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUnombre() {
		return unombre;
	}

	public void setUnombre(String unombre) {
		this.unombre = unombre;
	}

	public String getUfoto() {
		return ufoto;
	}

	public void setUfoto(String ufoto) {
		this.ufoto = ufoto;
	}

	
	
	
    
}
