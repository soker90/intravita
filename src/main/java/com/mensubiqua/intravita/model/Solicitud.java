package com.mensubiqua.intravita.model;

import org.springframework.stereotype.Component;

@Component
public class Solicitud {
	
	private String solicitante;
	private String solicitado;
	private boolean aceptado;
	
	public Solicitud() {}
	public Solicitud(String solicitante, String solicutado, boolean aceptado) {
		this.solicitante = solicitante;
		this.solicitado = solicutado;
		this.aceptado = aceptado;
	}
	
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	public String getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(String solicitado) {
		this.solicitado = solicitado;
	}
	public boolean isAceptado() {
		return aceptado;
	}
	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}
	
	

}
