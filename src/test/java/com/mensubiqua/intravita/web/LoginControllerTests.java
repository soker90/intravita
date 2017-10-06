package com.mensubiqua.intravita.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


public class LoginControllerTests {

    @Test
    public void testHandleRequestView() throws Exception{
        LoginController controller = new LoginController();
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("login", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        @SuppressWarnings("unchecked")
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("now");
        assertNotNull(nowValue);
    }

}