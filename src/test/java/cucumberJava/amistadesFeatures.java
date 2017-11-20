package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mensubiqua.intravita.dao.SolicitudDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;

//Cucumber imports

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.runtime.*;

import cucumber.api.PendingException;

public class amistadesFeatures {
	UserDAOImpl userD = new UserDAOImpl();
	SolicitudDAOImpl solD = new SolicitudDAOImpl();
	
	//Usuario 1 pepe
	
	private String nombre1 = "Pepe";
	private String apellido1 = "ampuero";
	private String email1 = "user@email.com";
	private String pass1 = "password";
	User pepe = new User();
	
	//usuario 2 juan
	
	private String nombre2 = "Juan";
	private String apellido2 = "ampuero";
	private String email2 = "user@email.com";
	private String pass2 = "password";
	User juan = new User();
	
	@Given("^usuario(\\d+) \"([^\"]*)\"$")
	public void usuario(String arg1, String arg2) throws Throwable {
	  pepe.setNombre(nombre1);
	  pepe.setApellido(apellido1);
	  pepe.setEmail(email1);
	  pepe.setPassword(pass1);
	  pepe.setNickname(arg1);
	  
	  juan.setNombre(nombre2);
	  juan.setApellido(apellido2);
	  juan.setEmail(email2);
	  juan.setPassword(pass2);
	  juan.setNickname(arg2);
	  
	  userD.insert(pepe);
	  userD.insert(juan);
	}

	@When("^pepe envia solicitud a juan$")
	public void pepe_envia_solicitud_a_juan() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^juan tiene solicitud pendiente$")
	public void juan_tiene_solicitud_pendiente() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^solicitud de amistad$")
	public void solicitud_de_amistad() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^pepe revoca la solicitud$")
	public void pepe_revoca_la_solicitud() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^solicitud no existe$")
	public void solicitud_no_existe() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^juan rechaza la solicitud$")
	public void juan_rechaza_la_solicitud() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^juan acepta la solicitud$")
	public void juan_acepta_la_solicitud() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^solicitud existe$")
	public void solicitud_existe() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}