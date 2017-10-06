package com.mensubiqua.intravita.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;


public class LoginControllerTests {

    @Test
    public void testHandleRequestView() throws Exception{
        LoginController controller = new LoginController();
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("login", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        String nowValue = (String) modelAndView.getModel().get("now");
        assertNotNull(nowValue);
    }

}