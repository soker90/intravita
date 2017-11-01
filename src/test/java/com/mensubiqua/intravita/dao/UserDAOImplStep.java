package com.mensubiqua.intravita.dao;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;
import java.io.File;
import java.util.ArrayList;

public class UserDAOImplStep {
	private UserDAOImpl ud = null;
	private User user = null;
	private User user2 = null;
	private ArrayList<User> users = null;
	private String nickname;
	private String rol;
	private String contrasena;
	private String validacion;
	
	@Given("^Tengo un UserDAOImpl inicializado$")
	public void tengo_un_UserDAOImpl_inicializado() throws Throwable {
	    ud = new UserDAOImpl();
	}

	@When("^Inserto un usuario$")
	public void inserto_un_usuario() throws Throwable {
		
	    user = new User(Funciones.encrypt("Miguel"), Funciones.encrypt("Ampu"), Funciones.encrypt("ampu@gmail.com"), Funciones.encrypt_md5("1234"), "ROLE_USER",Funciones.encrypt("miguel.ampu"), false);
	    ud.insert(user);
	}

	@Then("^Usuario insertado$")
	public void usuario_insertado() throws Throwable {
	    Assert.assertNotNull(ud.find(user.getNickname()));
	}

	@When("^Borro un usuario$")
	public void borro_un_usuario() throws Throwable {
		user = new User(Funciones.encrypt("Miguel"), Funciones.encrypt("Ampu"), Funciones.encrypt("ampu@gmail.com"), Funciones.encrypt_md5("1234"), "ROLE_USER",Funciones.encrypt("miguel.ampu"), false);
	    ud.delete(user.getNickname(), new File(""));
	}

	@Then("^El usuario es null$")
	public void el_usuario_es_null() throws Throwable {
	    Assert.assertNull(ud.find(user.getNickname()));
	}
	
	@When("^busco un usuario$")
	public void busco_un_usuario() throws Throwable {
		user = new User(Funciones.encrypt("Miguel"), Funciones.encrypt("Ampu"), Funciones.encrypt("ampu@gmail.com"), Funciones.encrypt_md5("1234"), "ROLE_USER",Funciones.encrypt("miguel.ampu"), false);
	    ud.insert(user);
	    user2 = ud.find(user.getNickname());
	    
	}

	@Then("^Tengo un usuario$")
	public void tengo_un_usuario() throws Throwable {
	   Assert.assertNotNull(user2);
	  
	    
	}

	@When("^Busco todos los usuarios en la base de datos$")
	public void busco_todos_los_usuarios_en_la_base_de_datos() throws Throwable {
		users = ud.selectAll();
	    
	}

	@Then("^Tengo todos los usuarios$")
	public void tengo_todos_los_usuarios() throws Throwable {
	    for(User u: users) {
	    	 Assert.assertNotNull(u);
	    }
	    
	}

	@When("^Actualizo un usuario$")
	public void actualizo_un_usuario() throws Throwable {
		user2 = ud.find(user.getNickname());
		user2.setEmail("miguelampuero@email.com");
	    ud.update(user2, "");
	    
	    
	}

	@Then("^El usuario actualizado$")
	public void el_usuario_actualizado() throws Throwable {
	    Assert.assertNotEquals(user2.getEmail(), Funciones.decrypt(user.getEmail()));
	}

	@When("^Actualizo rol$")
	public void actualizo_rol() throws Throwable {
		user = ud.find(user2.getNickname());
		ud.updateRole(user2.getNickname(), "ROLE_ADMIN");
	   	    
	}

	@Then("^Rol actualizado$")
	public void rol_actualizado() throws Throwable {
	    Assert.assertEquals(user.getRol(), user2.getRol());
	    
	}

	@When("^Actualizo contrasena de un usuario$")
	public void actualizo_contrasena_de_un_usuario() throws Throwable {
	    user2.setPassword(Funciones.encrypt_md5("1234565789"));
		ud.updatePassword(user2);
	    
	}

	@Then("^Contrasena actualizada$")
	public void contrasena_actualizada() throws Throwable {
		Assert.assertEquals(user.getPassword(), user2.getPassword());
	    
	}


	@When("^Actualizo validacion de usuario$")
	public void actualizo_validacion_de_usuario() throws Throwable {
	    ud.updateValidacion(user2);
	    
	}

	@Then("^Validacion de usuario actualizada$")
	public void validacion_de_usuario_actualizada() throws Throwable {
		Assert.assertEquals(user.isValidado(), user2.isValidado());
		ud.delete(user.getNickname(), new File(""));
		ud.delete(user2.getNickname(), new File(""));
	    
	}


}
