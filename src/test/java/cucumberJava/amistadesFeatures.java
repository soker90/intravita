package cucumberJava;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.SolicitudDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.Solicitud;
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
	
	private String rol = "ROLE_USER";
	private String nombre1 = "Pepe";
	private String apellido1 = "ampuero";
	private String email1 = "user@email.com";
	private String pass1 = "password";
	User pepe = null;
	//usuario 2 juan
	
	private String nombre2 = "Juan";
	private String apellido2 = "ampuero";
	private String email2 = "user@email.com";
	private String pass2 = "password";
	User juan = null;
	
	@Given("^primer usuario \"([^\"]*)\"$")
	public void primer_usuario(String arg1) throws Throwable {
		String nombre = Funciones.encrypt(nombre1);
    	String apellido = Funciones.encrypt(apellido1);
    	String email = Funciones.encrypt(email1);
    	String password = Funciones.encrypt_md5(pass1);
    	String nick = Funciones.encrypt(arg1);
	    userD.insert(new User(nombre, apellido, email, password, rol, nick, true));
	    pepe = userD.find(Funciones.encrypt(arg1));
	}

	@Given("^segundo usuario \"([^\"]*)\"$")
	public void segundo_usuario(String arg1) throws Throwable {
		String nombre = Funciones.encrypt(nombre2);
    	String apellido = Funciones.encrypt(apellido2);
    	String email = Funciones.encrypt(email2);
    	String password = Funciones.encrypt_md5(pass2);
    	String nick = Funciones.encrypt(arg1);
	    userD.insert(new User(nombre, apellido, email, password, rol, nick, true));
	    juan =  userD.find(Funciones.encrypt(arg1));
	}

	@When("^\"([^\"]*)\" envia solicitud a \"([^\"]*)\"$")
	public void envia_solicitud_a(String arg1, String arg2) throws Throwable {
	    solD.insert(new Solicitud(arg1, arg2, false));
	}
	
	@Then("^\"([^\"]*)\" tiene solicitud pendiente$")
	public void tiene_solicitud_pendiente(String arg1) throws Throwable {
	    ArrayList<User> solicitudes_pendientes = solD.solicitudesPendientes(arg1);
	    assertTrue(!solicitudes_pendientes.isEmpty());
	    
	    userD.delete(juan.getNickname(), new File(""));
	    userD.delete(pepe.getNickname(), new File(""));
	    solD.delete(pepe.getNickname(), juan.getNickname());

	}
	
	@When("^\"([^\"]*)\" revoca la solicitud a \"([^\"]*)\"$")
	public void revoca_la_solicitud_a(String arg1, String arg2) throws Throwable {
	    solD.delete(arg1, arg2);
	}

	@Then("^\"([^\"]*)\" no tiene solicitudes pendientes$")
	public void no_tiene_solicitudes_pendientes(String arg1) throws Throwable {
		ArrayList<User> solicitudes_pendientes = solD.solicitudesPendientes(arg1);
	    assertTrue(solicitudes_pendientes.isEmpty());
	    
	    userD.delete(juan.getNickname(), new File(""));
	    userD.delete(pepe.getNickname(), new File(""));
	}

	@When("^\"([^\"]*)\" rechaza la solicitud$")
	public void rechaza_la_solicitud(String arg1) throws Throwable {
	    solD.delete(pepe.getNickname(), arg1);
	}

	@When("^\"([^\"]*)\" acepta la solicitud$")
	public void acepta_la_solicitud(String arg1) throws Throwable {
	    solD.update(pepe.getNickname(), arg1, true);
	}

	@Then("^\"([^\"]*)\" y \"([^\"]*)\" son amigos$")
	public void y_son_amigos(String arg1, String arg2) throws Throwable {
	    assertTrue(solD.isAmigo(arg1, arg2));
	    
	    userD.delete(juan.getNickname(), new File(""));
	    userD.delete(pepe.getNickname(), new File(""));
	    solD.delete(arg1, arg2);
	}


	
}