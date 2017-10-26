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
public class AdminController {

	@Autowired
    UserDAOImpl userDAO;
	
	@RequestMapping(value = "/admin/index**")
    public ModelAndView adminPage(HttpSession sesion) {
    	User user = (User) sesion.getAttribute("user");
    	
    	try {
    	
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
			        	String[] u = {user1.getNombre(),user1.getApellido(), boton, user1.getNickname()};
				        
				        listVar.add(u);
			        }
			        
			        model.addObject("listName", listVar.iterator());
			        model.setViewName("admin/index");
			
			        return model;
		    	}
	    	}
    	} catch (Exception e) {
            return new ModelAndView("redirect:/default");
    	}
    	
    	return new ModelAndView("redirect:/default");

    }
	
    @RequestMapping(value = "/admin/borrarUsuario", method = RequestMethod.POST)
    public ModelAndView deleteUser(HttpServletRequest request) {
    	String username = request.getParameter("username");
    	System.out.println(username);
    	if(!username.equals("super.admin"))
    		userDAO.delete(Funciones.encrypt(username));

        return new ModelAndView("redirect:/default");
    }
    
    @RequestMapping(value = "/admin/editarUsuario", method = RequestMethod.POST)
    public ModelAndView perfil(HttpSession sesion, HttpServletRequest request) {
    	User user = (User) sesion.getAttribute("user");
    	
    	try {
    	
	    	if(user.getRol() != null)
	    	{
		    	if(user.getRol().equals("ROLE_ADMIN"))
		    	{
		    		ModelAndView model = new ModelAndView();
		    		User user_edit = userDAO.find(Funciones.encrypt(request.getParameter("username")));
		    		model.addObject("user_edit", user_edit);
		            model.setViewName("admin/editarUsuario");
		            return model;
		    	}
	    	}
    	} catch (Exception e) {
            return new ModelAndView("redirect:/default");
    	}
    	
    	return new ModelAndView("redirect:/default");

    }
    

}
