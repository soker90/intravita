package com.mensubiqua.intravita.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.auxiliar.MailSender;
import com.mensubiqua.intravita.auxiliar.Variables;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;

import java.awt.List;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RecuperarPassController {

	@Autowired
    UserDAOImpl userDAO;

	@RequestMapping(value = "/recuperar_pass", method = RequestMethod.GET)
	    public String login() {    	
	        return "recuperar_pass";
	    }
	
	@RequestMapping(value = "recuperarpass", method = RequestMethod.POST)
    public ModelAndView logear(HttpServletRequest request)  {
        ModelAndView model = new ModelAndView();
        User user = userDAO.find(Funciones.encrypt(request.getParameter("username").toLowerCase()));
        String password;
        
        model.setViewName("recuperar_pass");
    	
        if (user == null) model.addObject("mensaje2", "Este usuario no existe");

        else {
        	model.addObject("mensaje2", "Se ha mandado un nueva contraseña a su correo");
        	password = Funciones.generarStringAleatorio();
        	MailSender EnviadorMail = new MailSender(user.getEmail(),
                    "Nueva contraseña", "Buenas " + user.getNombre() + " " + user.getApellido() + " su nueva contraseña es: " + password);
        	password = Funciones.encrypt_md5(password);
        	user.setPassword(password);
            userDAO.updatePassword(user);
        }

        return model;
        
    }

}
