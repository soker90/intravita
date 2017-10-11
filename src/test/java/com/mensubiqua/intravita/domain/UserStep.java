package com.mensubiqua.intravita.domain;

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
        user.setApellidos(arg1);
    }

    @Then("^El apellido es \"([^\"]*)\"$")
    public void el_apellido_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getApellidos());
    }


    @When("^Cambio de username a \"([^\"]*)\"$")
    public void cambio_de_username_a(String arg1) throws Throwable {
        user.setUsername(arg1);
    }

    @Then("^El username es \"([^\"]*)\"$")
    public void el_username_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getUsername());
    }

    @When("^Cambio de contraseña a \"([^\"]*)\"$")
    public void cambio_de_contraseña_a(String arg1) throws Throwable {
        user.setPassword_checksum(arg1);
    }

    @Then("^La contraseña es \"([^\"]*)\"$")
    public void la_contraseña_es(String arg1) throws Throwable {
        Assert.assertEquals(arg1, user.getPassword_checksum());
    }


}
