package com.mensubiqua.intravita.auxiliar;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mensubiqua.intravita.dao.PublicacionDAOImpl;

@Component
public class Variables {
	
    private String url;
    private String mensaje;
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
			this.cont++;
	}
    
    
}
