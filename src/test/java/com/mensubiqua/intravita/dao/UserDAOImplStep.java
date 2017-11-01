package com.mensubiqua.intravita.dao;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;
import java.io.File;

public class UserDAOImplStep {
	private UserDAOImpl ud = null;
	private User user = null;
	
	@Given("^Tengo un UserDAOImpl inicializado$")
	public void tengo_un_UserDAOImpl_inicializado() throws Throwable {
	    ud = new UserDAOImpl();
	}

	@When("^Inserto un usuario$")
	public void inserto_un_usuario() throws Throwable {
	    user = new User("Miguel", "Ampu", "ampu@gmail.com", "1234", "ROLE_USER","miguel.ampu", false);
	    ud.insert(user);
	}

	@Then("^Usuario insertado$")
	public void usuario_insertado() throws Throwable {
	    Assert.assertNotNull(ud.find(user.getNickname()));
	}

	@When("^Borro un usuario$")
	public void borro_un_usuario() throws Throwable {
		user = new User("Miguel", "Ampu", "ampu@gmail.com", "1234", "ROLE_USER","miguel.ampu", false);
	    ud.delete(user.getNickname(), new File(""));
	}

	@Then("^El usuario es null$")
	public void el_usuario_es_null() throws Throwable {
	    Assert.assertNull(ud.find(user.getNickname()));
	}


}
