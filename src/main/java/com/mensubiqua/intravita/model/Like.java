package com.mensubiqua.intravita.model;

public class Like {
	private Publicacion publicacion;
	private User usuario;
	
	public Like(Publicacion publicacion, User usuario) {
		this.publicacion = publicacion;
		this.usuario = usuario;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}	

}
