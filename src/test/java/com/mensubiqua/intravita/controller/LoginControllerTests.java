/*package com.mensubiqua.intravita.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;


public class LoginControllerTests {

    @Test
    public void loginWith() throws Exception{
        LoginController controller = new LoginController();
        ModelAndView modelAndView = controller.login(null,null);
        assertEquals("login", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
    }

    @Test
    public void testErrorView() throws Exception{
        LoginController controller = new LoginController();
        ModelAndView modelAndView = controller.login("true",null);
        assertEquals("Usuario y/o contraseña inválido", modelAndView.getModel().get("error"));
        assertNull(modelAndView.getModel().get("msg"));
        assertNotNull(modelAndView.getModel());
    }

    @Test
    public void testLogoutView() throws Exception{
        LoginController controller = new LoginController();
        ModelAndView modelAndView = controller.login(null,"true");
        assertEquals("Has cerrado sesión correctamente", modelAndView.getModel().get("msg"));
        assertNull(modelAndView.getModel().get("error"));
        assertNotNull(modelAndView.getModel());
    }



}*/