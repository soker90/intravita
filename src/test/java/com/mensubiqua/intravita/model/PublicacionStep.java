package com.mensubiqua.intravita.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.expression.ParseException;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PublicacionStep {
	private Publicacion p = null;

	@Given("^Tengo una publicacion inicializada$")
	public void tengo_una_publicacion_inicializada() throws Throwable {
	    p = new Publicacion();
	}

	@When("^Cambio el _id a \"([^\"]*)\"$")
	public void cambio_el__id_a(String arg1) throws Throwable {
	    p.setId(arg1);
	}

	@Then("^El _id es \"([^\"]*)\"$")
	public void el__id_es(String arg1) throws Throwable {
	    Assert.assertEquals(arg1, p.getId());
	}

	@When("^Cambio el nickname a \"([^\"]*)\"$")
	public void cambio_el_nickname_a(String arg1) throws Throwable {
	    p.setNickname(arg1);
	}

	@Then("^El nickname es \"([^\"]*)\"$")
	public void el_nickname_es(String arg1) throws Throwable {
	    Assert.assertEquals(arg1, p.getNickname());
	}

	@When("^Cambio el texto a \"([^\"]*)\"$")
	public void cambio_el_texto_a(String arg1) throws Throwable {
	    p.setTexto(arg1);
	}

	@Then("^El text es \"([^\"]*)\"$")
	public void el_text_es(String arg1) throws Throwable {
	    Assert.assertEquals(arg1, p.getTexto());
	}

	@When("^Cambio la privacidad a \"([^\"]*)\"$")
	public void cambio_la_privacidad_a(String arg1) throws Throwable {
	    p.setPrivacidad(arg1);
	}

	@Then("^La privacidad es \"([^\"]*)\"$")
	public void la_privacidad_es(String arg1) throws Throwable {
	    Assert.assertEquals(arg1, p.getPrivacidad());
	}

	@When("^Cambio la fecha a \"([^\"]*)\"$")
	public void cambio_la_fecha_a(String arg1) throws Throwable {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    Date fecha = null;
	    try {
	    	fecha = formato.parse(arg1);
	    }catch(ParseException ex) {
	    	System.out.println(ex.getMessage());
	    }
	    p.setFecha(fecha);
	}

	@Then("^La fecha es \"([^\"]*)\"$")
	public void la_fecha_es(String arg1) throws Throwable {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    Date fecha = null;
	    try {
	    	fecha = formato.parse(arg1);
	    }catch(ParseException ex) {
	    	System.out.println(ex.getMessage());
	    }
	    Assert.assertEquals(fecha, p.getFecha());
	}
}
