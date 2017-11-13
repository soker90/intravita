package com.mensubiqua.intravita.auxiliar;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mensubiqua.intravita.dao.PublicacionDAOImpl;

/**
 * Variables - Clase encargada que guardará información esencial que debe comunicarse 
 * a las vistas para su correcto funcionamiento. Desde la URL que se debe usar hasta 
 * los mensajes de error que deben aparecer
 * 
 *
 * @author Ulises Ceca, Ignacio Dones, José María Simón, Miguel Ampuero, Eduardo Parra
 * @since 1.7
 * @version 2.0
 */

@Component
public class Variables {
	
    private String url;
    private String mensaje;
    private String tipo;
    private int cont;
    
	public Variables() {
		cont = 0;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(boolean local) {
		if(local)
			this.url = "/intravita"; //localhost
		else
			this.url = ""; //heroku
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		if(cont == 0)
			this.cont = 0;
		else
			if(this.cont < 3)
				this.cont++;
	}

	public String getTipo() {
		if(this.tipo.isEmpty())
			return "info";
		
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
	
    
}
