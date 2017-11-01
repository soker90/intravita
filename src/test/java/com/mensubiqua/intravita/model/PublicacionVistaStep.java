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

	@When("^Cambio el id a \"([^\"]*)\"$")
	public void cambio_el_id_a(String arg1) throws Throwable {
		pv.setId(arg1);
	}

	@Then("^El id es \"([^\"]*)\"$")
	public void el_id_es(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(arg1, pv.getId());
	}
	
	/*@When("^Cambio el nickname a \"([^\"]*)\"$")
	public void cambio_el_nickname_a(String arg1) throws Throwable {
	    pv.setNickname(arg1);
	}

	@Then("^El nickname es \"([^\"]*)\"$")
	public void el_nickname_es(String arg1) throws Throwable {
	    Assert.assertEquals(arg1, pv.getNickname());
	}

	@When("^Cambio el texto a \"([^\"]*)\"$")
	public void cambio_el_texto_a(String arg1) throws Throwable {
	    pv.setTexto(arg1);
	}

	@Then("^El text es \"([^\"]*)\"$")
	public void el_text_es(String arg1) throws Throwable {
	    Assert.assertEquals(arg1, pv.getTexto());
	}

	@When("^Cambio la privacidad a \"([^\"]*)\"$")
	public void cambio_la_privacidad_a(String arg1) throws Throwable {
	    pv.setPrivacidad(arg1);
	}

	@Then("^La privacidad es \"([^\"]*)\"$")
	public void la_privacidad_es(String arg1) throws Throwable {
	    Assert.assertEquals(arg1, pv.getPrivacidad());
	}

	@When("^Cambio la fecha a \"([^\"]*)\"$")
	public void cambio_la_fecha_a(String arg1) throws Throwable {
		pv.setFecha(arg1);
	}

	@Then("^La fecha es \"([^\"]*)\"$")
	public void la_fecha_es(String arg1) throws Throwable {
	    Assert.assertEquals(arg1, pv.getFecha());
	}*/


}
