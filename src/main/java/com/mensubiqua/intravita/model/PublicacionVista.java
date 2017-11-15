package com.mensubiqua.intravita.model;

import java.util.Date;

import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.LikeDAOImpl;
import com.mensubiqua.intravita.dao.PublicacionDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;

/**
 * PublicacionVista - Clase de dominio que contiene toda la información de la vista de una publicación
 * necesaria para el correcto funcionamiento del software.
 * Esta clase relaciona una publicación con su autor.
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José María Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.5
 * @version 1.8
 */
public class PublicacionVista {

	private int limite = 3;
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
    private long contCompartidas;
    private long contLikes = 0;
    private String liker;
    
	public PublicacionVista(Publicacion p, User u, User sesioner) throws Exception {
		this.texto = p.getTexto();
		this.privacidad = p.getPrivacidad();
		this.fecha = p.getFecha();
		this.nickname = u.getNickname();
		this.id = p.getId();
		this.unombre = u.getNombre() + " " + u.getApellido();
		this.ufoto = u.getFoto();
		
		PublicacionDAOImpl dao = new PublicacionDAOImpl();
		LikeDAOImpl likeDAO = new LikeDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		Like l = null;
		
		//Publicaciones compartidas
		try {
		
			if(Funciones.validarCompartir(p.getTexto()))
			{
				
				UserDAOImpl daoUser = new UserDAOImpl();
				this.idCompartido = texto.substring(limite, texto.length());
				Publicacion pc = dao.find(this.idCompartido);
				this.nickCompartido = pc.getNickname();
				User uc = daoUser.find(Funciones.encrypt(this.nickCompartido));
				this.nombreCompartido = uc.getNombre() + " " + uc.getApellido();
				this.fechaCompartida = pc.getFecha();
				this.textoCompartido = pc.getTexto();
				
			} else {
				
				this.contCompartidas = dao.contCompartida(this.id);
				this.contLikes = likeDAO.contLikes(dao.find(this.id));
				l = likeDAO.find(p, sesioner);
				if (l != null) this.liker = l.getUsuario().getNickname();
			}
		} catch(Exception e) {
			this.contCompartidas = dao.contCompartida(this.id);
			this.contLikes = likeDAO.contLikes(dao.find(this.id));
			System.out.println(e.getMessage());
		}
	}

	public String getLiker() {
		return liker;
	}

	public void setLiker(String liker) {
		this.liker = liker;
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

	public long getContCompartidas() {
		return contCompartidas;
	}

	public void setContCompartidas(long contCompartidas) {
		this.contCompartidas = contCompartidas;
	}

	public long getContLikes() {
		return contLikes;
	}

	public void setContLikes(long contLikes) {
		this.contLikes = contLikes;
	}
	
	
    
}
