package com.mensubiqua.intravita.model;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Publicación - Clase de dominio que contiene toda la información de una publicación
 * necesaria para el correcto funcionamiento del software
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José María Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.4
 * @version 1.7
 */
public class Publicacion {

	private String id;
    private String nickname;
    private String texto;
    private String privacidad;
    private String fecha;
    
	public Publicacion(String nickname, String texto, String privacidad, String fecha) {
		this.nickname = nickname;
		this.texto = texto;
		this.privacidad = privacidad;
		this.fecha = fecha;
	}
	
	public Publicacion() {}
	
	public String getId() {
		return id;
	}


	public void setId(String _id) {
		this.id = _id;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
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
