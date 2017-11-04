package com.mensubiqua.intravita.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.PublicacionDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;

public class PublicacionVista {

	private String id;
    private String nickname;
    private String texto;
    private String privacidad;
    private String fecha;
    private String unombre;
    private String ufoto;
    private String nombreCompartido;
    private String fechaCompartida;
    private String textoCompartido;
    private String nickCompartido;
    private String idCompartido;
    
	public PublicacionVista(Publicacion p, User u) {
		this.texto = p.getTexto();
		this.privacidad = p.getPrivacidad();
		this.fecha = p.getFecha();
		this.nickname = p.getNickname();
		this.id = p.getId();
		this.unombre = u.getNombre() + " " + u.getApellido();
		this.ufoto = u.getFoto();
		
		//Publicaciones compartidas
		
		if(Funciones.validarCompartir(p.getTexto()))
		{
			PublicacionDAOImpl dao = new PublicacionDAOImpl();
			UserDAOImpl daoUser = new UserDAOImpl();
			this.idCompartido = texto.substring(3, texto.length());
			Publicacion pc = dao.find(this.idCompartido);
			this.nickCompartido = pc.getNickname();
			User uc = daoUser.find(Funciones.encrypt(this.nickCompartido));
			this.nombreCompartido = uc.getNombre() + " " + uc.getApellido();
			this.fechaCompartida = pc.getFecha();
			this.textoCompartido = pc.getTexto();
		}
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

	public String getNombreCompartido() {
		return nombreCompartido;
	}

	public void setNombreCompartido(String nombreCompartido) {
		this.nombreCompartido = nombreCompartido;
	}

	public String getFechaCompartida() {
		return fechaCompartida;
	}

	public void setFechaCompartida(String fechaCompartida) {
		this.fechaCompartida = fechaCompartida;
	}

	public String getTextoCompartido() {
		return textoCompartido;
	}

	public void setTextoCompartido(String textoCompartido) {
		this.textoCompartido = textoCompartido;
	}

	public String getNickCompartido() {
		return nickCompartido;
	}

	public void setNickCompartido(String nickCompartido) {
		this.nickCompartido = nickCompartido;
	}

	public String getIdCompartido() {
		return idCompartido;
	}

	public void setIdCompartido(String idCompartido) {
		this.idCompartido = idCompartido;
	}
	
    
}
