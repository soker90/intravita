package com.mensubiqua.intravita.model;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class UserStep {

    private User user = null;

    @Given("^Tengo un usuario inicializado$")
    public void tengo_un_usuario_inicializado() throws Throwable {
        user = new User();
    }

    @When("^Cambio el nombre a \"([^\"]*)\"$")
    public void cambio_el_nombre_a(String arg1) throws Throwable {
        user.setNombre(arg1);
    }

    @Then("^El nombre es \"([^\"]*)\"$")
    public void el_nombre_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getNombre());
    }

    @When("^Cambio de apellido a \"([^\"]*)\"$")
    public void cambio_de_apellido_a(String arg1) throws Throwable {
        user.setApellido(arg1);
    }

    @Then("^El apellido es \"([^\"]*)\"$")
    public void el_apellido_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getApellido());
    }


    @When("^Cambio de username a \"([^\"]*)\"$")
    public void cambio_de_username_a(String arg1) throws Throwable {
        user.setNickname(arg1);
    }

    @Then("^El username es \"([^\"]*)\"$")
    public void el_username_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getNickname());
    }

    @When("^Cambio de contrasena a \"([^\"]*)\"$")
    public void cambio_de_contrasena_a(String arg1) throws Throwable {
        user.setPassword(arg1);
    }

    @Then("^La contrasena es \"([^\"]*)\"$")
    public void la_contrasena_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getPassword());
    }
    @When("^Cambio de email a \"([^\"]*)\"$")
    public void cambio_de_email_a(String arg1) throws Throwable {
        user.setEmail(arg1);
    }

    @Then("^El email es \"([^\"]*)\"$")
    public void el_email_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getEmail());
    }
    
    @When("^Cambio de nick a \"([^\"]*)\"$")
    public void cambio_de_nick_a(String arg1) throws Throwable {
        user.setNickname(arg1);
    }

    @Then("^El nick es \"([^\"]*)\"$")
    public void el_nick_es(String arg1) throws Throwable {
    	Assert.assertEquals(arg1, user.getNickname());
    }

    @When("^Cambio de foto a \"([^\"]*)\"$")
    public void cambio_de_foto_a(String arg1) throws Throwable {
        user.setFoto(arg1);
    }

    @Then("^La ruta de la foto es \"([^\"]*)\"$")
    public void la_ruta_de_la_foto_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getFoto());
    }

    @When("^Cambio de rol a \"([^\"]*)\"$")
    public void cambio_de_rol_a(String arg1) throws Throwable {
        user.setRol(arg1);
    }

    @Then("^El rol es \"([^\"]*)\"$")
    public void el_rol_es(String arg1) throws Throwable {
    	Assert.assertEquals(arg1, user.getRol());
    }

    @When("^Cambio de validado a \"([^\"]*)\"$")
    public void cambio_de_validado_a(Boolean arg1) throws Throwable {
        user.setValidado(arg1);
    }

    @Then("^Validado es \"([^\"]*)\"$")
    public void validado_es(Boolean arg1) throws Throwable {
    	Assert.assertEquals(arg1, user.isValidado());
    }

}
