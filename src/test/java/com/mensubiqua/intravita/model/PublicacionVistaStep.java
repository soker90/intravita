package com.mensubiqua.intravita.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.expression.ParseException;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PublicacionVistaStep {
	private PublicacionVista pv = null;
	
	@Given("^Tengo una publicacionVista inicializada$")
	public void tengo_una_publicacionVista_inicializada() throws Throwable {
		pv = new PublicacionVista(new Publicacion(), new User());
	}
	
	@When("^Cambio su id a \"([^\"]*)\"$")
	public void cambio_su_id_a(String arg1) throws Throwable {
		pv.setId(arg1);
	}

	@Then("^El id devuelto es \"([^\"]*)\"$")
	public void el_id_devuelto_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, pv.getId());
	}
	
	@When("^Cambio el nickname a el nuevo \"([^\"]*)\"$")
	public void cambio_el_nickname_a_el_nuevo(String arg1) throws Throwable {
		pv.setNickname(arg1);
	}

	@Then("^El nickname devuelto es \"([^\"]*)\"$")
	public void el_nickname_devuelto_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, pv.getNickname());
	}
	
	@When("^Cambio su texto a \"([^\"]*)\"$")
	public void cambio_su_texto_a(String arg1) throws Throwable {
		pv.setTexto(arg1);
	}

	@Then("^El texto devuelto es \"([^\"]*)\"$")
	public void el_texto_devuelto_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, pv.getTexto());
	}
	
	@When("^Cambio su fecha a \"([^\"]*)\"$")
	public void cambio_su_fecha_a(String arg1) throws Throwable {
		pv.setFecha(arg1);
	}

	@Then("^La fecha devuelta es \"([^\"]*)\"$")
	public void la_fecha_devuelta_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, pv.getFecha());
	}

	@When("^Cambio su nombre a \"([^\"]*)\"$")
	public void cambio_su_nombre_a(String arg1) throws Throwable {
		pv.setUnombre(arg1);
	}

	@Then("^El nombre devuelto es \"([^\"]*)\"$")
	public void el_nombre_devuelto_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, pv.getUnombre());
	}
	
	@When("^Cambio su foto a \"([^\"]*)\"$")
	public void cambio_su_foto_a(String arg1) throws Throwable {
	    pv.setUfoto(arg1);
	}

	@Then("^La foto devuelta es \"([^\"]*)\"$")
	public void la_foto_devuelta_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, pv.getUfoto());
	}
	
	@When("^Cambio su privacidad a \"([^\"]*)\"$")
	public void cambio_su_privacidad_a(String arg1) throws Throwable {
		pv.setPrivacidad(arg1);
	}

	@Then("^La privacidad devuelta es \"([^\"]*)\"$")
	public void la_privacidad_devuelta_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, pv.getPrivacidad());
	}


}
