package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;


//Cucumber imports

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.*;

import cucumber.api.PendingException;

//import de otros paquetes
import com.mensubiqua.intravita.model.User;
import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.UserDAOImpl;

public class userEditar {
	
	@Given("^Un usuario registrado en la aplicacion$")
	public void Un_usuario_registrado_en_la_aplicacion() {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@When("^borra cuenta$")
	public void borra_cuenta() {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@Then("^usuario no existe en la base de datos$")
	public void usuario_no_existe_en_la_base_de_datos() {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}
	
	@When("^editar informacion$")
	public void editar_informacion() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^usuario con datos actualizados$")
	public void usuario_con_datos_actualizados() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^inserta nueva password$")
	public void inserta_nueva_password() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^password actualizada$")
	public void password_actualizada() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}



}
