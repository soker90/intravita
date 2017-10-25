package com.mensubiqua.intravita.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Session {
	@Autowired
	private User user;
	
	private boolean active = false;
	
	public String getName(){
		return user.getNombre();
	}
	
	public void setName(String name)
	{
		user.setNombre(name);
	}
	
	public String getRol()
	{
		return user.getRol();
	}
	
	public void setRol(String rol)
	{
		user.setRol(rol);
	}
	
	public String getEmail() {return user.getEmail();}
    public void setEmail(String email) {user.setEmail(email);}
    public String getNombre() {return user.getNombre();}
    public String getApellido() {return user.getApellido();}
    public String getNickname() {return user.getNickname();}
    public String getPassword() {return user.getPassword();}
    public String getFoto() {return user.getFoto();}
    public void setNickname(String nickname) {user.setNickname(nickname);}
    public void setNombre(String nombre) {user.setNombre(nombre);}
    public void setApellido(String apellido) {user.setApellido(apellido);}
    public void setPassword(String password) {user.setPassword(password);}
    public void setFoto(String foto) {user.setFoto(foto);}
	
	
	
}