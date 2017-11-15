package com.mensubiqua.intravita.model;

import org.springframework.stereotype.Component;

/**
 * Solicitud - Clase de dominio que contiene toda la información de una solicitud,
 * necesaria para el correcto funcionamiento del software
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José María Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.7
 * @version 2.0
 */
@Component
public class Solicitud {
	
	private String id;
	private String solicitante;
	private String solicitado;
	private boolean aceptado;
	
	public Solicitud() {}
	public Solicitud(String solicitante, String solicitado, boolean aceptado) {
		this.id = null;
		this.solicitante = solicitante;
		this.solicitado = solicitado;
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
