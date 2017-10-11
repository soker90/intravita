package com.mensubiqua.intravita.service;

import com.mensubiqua.intravita.domain.User;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class UserManagerStep {
    private UserManager um = null;

    @Given("^Tengo un userManager inicializado$")
    public void tengo_un_userManager_inicializado() throws Throwable {
        um = new UserManager();
    }


    @When("^Cambio introduzco los usuarios$")
    public void cambio_introduzco_los_usuarios(DataTable arg1) throws Throwable {
        ArrayList<User> usuarios = this.convert_list(arg1);
        um.setUsers(usuarios);

    }

    @Then("^Se guardan los usarios$")
    public void se_guardan_los_usarios(DataTable arg1) throws Throwable {
        ArrayList<User> usuarios = this.convert_list(arg1);
        boolean equal = true;
        int cont = 0;

        ArrayList<User> actual = um.getUsers();
        for (User u : usuarios)
        {

            if(!u.toString().equals(actual.get(cont++).toString()))
            {
                equal = false;
                break;
            }
        }

        Assert.assertTrue(equal);
    }

    private ArrayList<User> convert_list(DataTable arg1)
    {
        List<List<String>> lista = arg1.cells(1);
        ArrayList<User> usuarios = new ArrayList<User>();
        for (List<String> i : lista)
        {
            User user = new User();
            user.setNombre(i.get(0));
            user.setApellidos(i.get(1));
            user.setUsername(i.get(2));
            user.setPassword_checksum(i.get(3));
            usuarios.add(user);
        }

        return usuarios;
    }
}
