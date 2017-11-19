package cucumberJava;

//Junit imports
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

//Cucumber imports

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

//import de otros paquetes
import com.mensubiqua.intravita.model.User;
import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.UserDAOImpl;



public class loginRegistro {

	private User usuario;
	private UserDAOImpl dao = new UserDAOImpl();;
	
	private String nombre = "Miguel";
	private String apellido = "Ampuero";
	private String email = "user@email.com";
	private String pass = "password";
	private String nick = "nickMiguel";
	private String pass2 = "password2";
	
	@Given("^Un nuevo usuario inserta sus datos$")
	public void Un_nuevo_usuario_inserta_sus_datos() {
		
		
		 usuario = new User(Funciones.encrypt(nombre), Funciones.encrypt(apellido), Funciones.encrypt(email), Funciones.encrypt_md5(pass),
         		"ROLE_USER", Funciones.encrypt(nick) ,false);
		 
	    
	}

	@When("^se comprueba en la bbdd$")
	public void se_comprueba_en_la_bbdd() {
		User u = null;
		dao.find(Funciones.encrypt(nick));
		if(u == null){
			dao.insert(usuario);
		}
	    
	}

	@Then("^usuario creado$")
	public void usuario_creado() {
		usuario = null;
	    usuario = dao.find(Funciones.encrypt(nick));
	    if(usuario!=null)
	    	assertTrue(usuario != null);
	    //dao.delete(nick, new File(""));
	    usuario = null;
	}
	
	@Given("^Un usuario inserta sus datos$")
	public void Un_usuario_inserta_sus_datos() {
	    usuario = new User(nombre, apellido, email, pass, "ROLE_USER", nick, false);
	}

	@When("^buscar que no exista$")
	public void buscar_que_no_exista() {
		usuario= null;
		usuario = dao.find(Funciones.encrypt(nick));		
	}

	@Then("^usuario no registrado$")
	public void usuario_no_registrado() {
		if(usuario==null) {
	    	assertTrue(usuario==null);	    	
		}
}
	    
	@When("^password no coinciden$")
	public void password_no_coinciden() {
	    if(usuario.getPassword() != pass2) {
	    	usuario = null;
	    }
	}


	@Given("^nickname y pass$")
	public void nickname_y_pass() {
	    usuario = null;
	}

	@When("^ya esta registrado$")
	public void ya_esta_registrado() {
	    usuario = dao.find(Funciones.encrypt(nick));
	}

	@Then("^acceso permitido$")
	public void acceso_permitido() {
		if(usuario!=null)
	    	assertTrue(usuario != null);
	}

	@Given("^nickname y password$")
	public void nickname_y_password() {
	    usuario =null;
	    
	}

	@When("^password incorrecta$")
	public void password_incorrecta() {
		usuario = dao.find(Funciones.encrypt(nick));
	    if(usuario.getPassword() != Funciones.encrypt_md5(pass2)){
	    	usuario = null;
	    }
	    dao.delete(nick, new File(""));
	}

	@Then("^acceso denegado$")
	public void acceso_denegado() {
		if(usuario==null) {
	    	assertTrue(usuario==null);	    	
		}
	}

	@When("^usuario no registro$")
	public void usuario_no_registro() {
		usuario = dao.find(Funciones.encrypt(nick));
	}



}
