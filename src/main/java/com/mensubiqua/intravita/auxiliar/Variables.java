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
    
	public Variables() {
		//this.url = ""; //heroku
		this.url = "/intravita"; //localhost
		
		/*try {
			String ip = InetAddress.getLocalHost().toString();
			if(ip.equals("127.0.0.1"))
				this.url = "/intravita";
		} catch (UnknownHostException e1) {

			this.url = "";
		}*/
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
    
    
}
