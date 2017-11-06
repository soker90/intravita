package com.mensubiqua.intravita.model;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SolicitudStep {
	private Solicitud solicitud = null;
	
	@Given("^Tengo una solicitud inicializada$")
	public void tengo_una_solicitud_inicializada() throws Throwable {
	    solicitud = new Solicitud();
	}

	@When("^Cambio el solicitante a \"([^\"]*)\"$")
	public void cambio_el_solicitante_a(String arg1) throws Throwable {
	    solicitud.setSolicitante(arg1);
	}

	@Then("^El solicitante es \"([^\"]*)\"$")
	public void el_solicitante_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, solicitud.getSolicitante());
	}

	@When("^Cambio el solicitado a \"([^\"]*)\"$")
	public void cambio_el_solicitado_a(String arg1) throws Throwable {
	    solicitud.setSolicitado(arg1);
	}

	@Then("^El solicitado es \"([^\"]*)\"$")
	public void el_solicitado_es(String arg1) throws Throwable {
		Assert.assertEquals(arg1, solicitud.getSolicitado());
	}

	@When("^Cambio de aceptado a \"([^\"]*)\"$")
	public void cambio_de_aceptado_a(boolean arg1) throws Throwable {
	    solicitud.setAceptado(arg1);
	}

	@Then("^El aceptado es \"([^\"]*)\"$")
	public void el_aceptado_es(boolean arg1) throws Throwable {
		 Assert.assertEquals(arg1, solicitud.isAceptado());
	}

}
