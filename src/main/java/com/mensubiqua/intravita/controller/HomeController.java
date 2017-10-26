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

    @RequestMapping(value = "/user/index**")
    public ModelAndView homePage(HttpSession sesion) {
    	User user = (User) sesion.getAttribute("user");
    	
    	if(user.getRol() != null)
    	{
	    	if(user.getRol().equals("ROLE_USER") | user.getRol().equals("ROLE_ADMIN"))
	    	{
	    		ModelAndView model = new ModelAndView();
	            model.addObject("title", "Web para usuarios");
	            model.addObject("message", "Pagina para usuarios corrientes");
	            model.setViewName("user/index");
	            return model;
	    	}
    	}
    	
    	return new ModelAndView("redirect:/default");

    }

    @RequestMapping(value = "/admin/index**", method = RequestMethod.GET)
    public ModelAndView adminPage(HttpSession sesion) {
    	User user = (User) sesion.getAttribute("user");
    	
    	if(user.getRol() != null)
    	{
	    	if(user.getRol().equals("ROLE_ADMIN"))
	    	{
		        ModelAndView model = new ModelAndView();
		        model.addObject("title", "Página para admins");
		        model.addObject("message", "Página para administradores");
		        ArrayList<String[]> listVar = new ArrayList<String[]>();
		        
		        ArrayList<User> users = userDAO.selectAll();
		        
		        for(User user1: users)
		        {
		        	String boton = "superadmin";
		        	//TODO poner excepcion para superusuario cuando se cree
		        	if(user1.getRol().equals("ROLE_ADMIN"))
		        		boton="Sí";
		        	else if (user1.getRol().equals("ROLE_USER")) {
						
							boton = "No";
					}
		        	String[] u = {user1.getNombre(),user1.getApellido(), boton, "#"};
			        
			        listVar.add(u);
		        }
		        
		        model.addObject("listName", listVar.iterator());
		        model.setViewName("admin/index");
		
		        return model;
	    	}
    	}
    	
    	return new ModelAndView("redirect:/default");

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
