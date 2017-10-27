package com.mensubiqua.intravita.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;

import java.awt.List;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GeneralController {

	@Autowired
    UserDAOImpl userDAO;
	
    @RequestMapping({"/default", "/"})
    public String defaultAfterLogin(HttpSession sesion) {
    	User user = (User) sesion.getAttribute("user");
    	
    	try {
    		
	    	if(user.getRol() == null)
	    		return "redirect:/login";
	    	
	        if (user.getRol().equalsIgnoreCase("ROLE_ADMIN")) {
	            return "redirect:/admin";
	        }
	
	        if (user.getRol().equalsIgnoreCase("ROLE_USER")) {
	            return "redirect:/user";
	        }
    	} catch (Exception e) {
            return "redirect:/login";
    	}
    	
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {    	
        return "login";
    }

    @RequestMapping(value = "registro", method = RequestMethod.POST)
    public ModelAndView registrar(HttpServletRequest request)  {

    	User user = null;
    	ModelAndView model = new ModelAndView();
        model.setViewName("login");
    	
        if (!request.getParameter("password").equals(request.getParameter("password2"))) 
        	model.addObject("mensaje", "Las contraseñas no coinciden");

        else if (userDAO.find(Funciones.encrypt(request.getParameter("nombre") + "." + request.getParameter("apellido"))) != null) 
        	model.addObject("mensaje", "Este usuario ya existe");

        else {
            user = new User(Funciones.encrypt(request.getParameter("nombre")), Funciones.encrypt(request.getParameter("apellido")),
            		Funciones.encrypt(request.getParameter("email")), Funciones.encrypt_md5(request.getParameter("password")),
            		"ROLE_USER", Funciones.encrypt(request.getParameter("nombre") + "." + request.getParameter("apellido")));
            userDAO.insert(user);
            model.addObject("mensaje", "Usuario creado con exito");
        }

        return model;
    }




    @RequestMapping(value = "logear", method = RequestMethod.POST)
    public ModelAndView logear(HttpServletRequest request)  {
        ModelAndView model = new ModelAndView();
        User user = userDAO.find(Funciones.encrypt(request.getParameter("username")));
        
        model.setViewName("redirect:/default");
    	
        if (user == null) model.addObject("mensaje2", "Este usuario no existe");

        else if (!Funciones.encrypt_md5(request.getParameter("password")).equals(user.getPassword())) 
        	model.addObject("mensaje2", "Las contraseñas no coinciden");

        else {
            request.getSession().setAttribute("user", user);
        }

        return model;
        
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession sesion) {
    	sesion.invalidate();
        return "redirect:/default";
    }



    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error403(Principal user){

        ModelAndView model = new ModelAndView();
        model.addObject("head", "Error 403");
        model.addObject("title", "Error 403 - Acceso denegado");
        if (user != null){
            model.addObject("msg", "Hola "+user.getName()+", no tienes permiso para acceder a esta página");
        }else{
            model.addObject("msg", "No tienes permiso para acceder a esta página");
        }

        model.setViewName("error");
        return model;

    }

}
