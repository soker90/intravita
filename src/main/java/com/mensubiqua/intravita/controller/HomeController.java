package com.mensubiqua.intravita.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;

import java.awt.List;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
    UserDAOImpl userDAO;
	
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpSession sesion) {
    	User user = (User) sesion.getAttribute("user");
    	
    	try {
    		
	    	if(user.getRol() == null)
	    		return "redirect:/login";
	    	
	        if (user.getRol().equalsIgnoreCase("ROLE_ADMIN")) {
	            return "redirect:/admin/index";
	        }
	
	        if (user.getRol().equalsIgnoreCase("ROLE_USER")) {
	            return "redirect:/user/index";
	        }
    	} catch (Exception e) {
            return "redirect:/login";
    	}
    	
        return "redirect:/login";
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
