package tests.controlador;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


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



}