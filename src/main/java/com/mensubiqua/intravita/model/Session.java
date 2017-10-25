package com.mensubiqua.intravita.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Session {
	@Autowired
	private User user;
	
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
	
	
	
}