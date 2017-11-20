package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.dao.PublicacionDAOImpl;

//Cucumber imports

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.runtime.*;

import cucumber.api.PendingException;



public class publicacionesFeatures {
	
	private User usuario;
	private UserDAOImpl dao = new UserDAOImpl();
	private PublicacionDAOImpl daoP = new PublicacionDAOImpl();
	private Publicacion p = new Publicacion(); 
	
	private String nombre = "Miguel";
	private String apellido = "Ampuero";
	private String email = "user@email.com";
	private String pass = "password";
	
	@Given("^Un usuario$")
	public void un_usuario() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^escribe publicacion$")
	public void escribe_publicacion() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^inserta publicacion$")
	public void inserta_publicacion() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^publicacion creada$")
	public void publicacion_creada() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^borra publicacion$")
	public void borra_publicacion() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^publicacion no existe en bd$")
	public void publicacion_no_existe_en_bd() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^edita publicacion$")
	public void edita_publicacion() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^publicacion modificada$")
	public void publicacion_modificada() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^Un usuario \"([^\"]*)\"$")
	public void un_usuario(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^publicaciones creadas$")
	public void publicaciones_creadas() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Borra usuario \"([^\"]*)\"$")
	public void borra_usuario(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^no hay publicaciones de usuario \"([^\"]*)\"$")
	public void no_hay_publicaciones_de_usuario(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


}
