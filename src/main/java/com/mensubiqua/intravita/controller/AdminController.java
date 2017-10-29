package com.mensubiqua.intravita.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.auxiliar.Variables;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;

@Controller
public class AdminController {

	@Autowired
    UserDAOImpl userDAO;
	
	@Autowired
	Variables var;
	
	@RequestMapping(value = "/admin**")
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
	
    @RequestMapping(value = "/admin/borrarUsuario**", method = RequestMethod.POST)
    public ModelAndView deleteUser(HttpServletRequest request) {
    	String username = request.getParameter("username");
    	if(!username.equals("super.admin"))
    		userDAO.delete(Funciones.encrypt(username));

        return new ModelAndView("redirect:/default");
    }
    
    @RequestMapping(value = "/admin/editarUsuario**", method = RequestMethod.POST)
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
    
    @RequestMapping(value = "/admin/editarCuenta**", method = RequestMethod.POST)
    public ModelAndView editAccount(HttpSession session, HttpServletRequest request) {
        User user = new User();
        user.setNickname(request.getParameter("nick"));
        user.setNombre(request.getParameter("nombre"));
        user.setApellido(request.getParameter("apellidos"));
        user.setFoto(request.getParameter("foto"));
        user.setEmail(request.getParameter("email"));
        System.out.println(user.toString());
        
        userDAO.update(user);
        
        return new ModelAndView("redirect:/default");
    }
    
    @RequestMapping(value = "/admin/cambiarPassword**", method = RequestMethod.POST)
    public ModelAndView updatePassword(HttpSession session, HttpServletRequest request) {
    	if(!request.getParameter("nick").equals("super.admin"))
    	{
    		User user = userDAO.find(Funciones.encrypt(request.getParameter("nick")));
            if(Funciones.encrypt_md5(request.getParameter("password_old")).equals(user.getPassword()))
            {
            	if(request.getParameter("password").equals(request.getParameter("password2")))
            	{
            		user.setPassword(Funciones.encrypt_md5(request.getParameter("password")));
            		userDAO.update(user);
            	} else {
            		//TODO enviar un mensaje a una variable de sesion
            	}
            } else {
            	//TODO enviar un mensaje a una variable de sesion
            }
            
    	}
    	
    	return new ModelAndView("redirect:/default");
        
    }
    
    @RequestMapping(value = "/admin/updateRol**", method = RequestMethod.POST)
    public ModelAndView updateRol(HttpServletRequest request) {
    	String rol = request.getParameter("rol");
    	if(rol.equals("No")) rol="ROLE_ADMIN"; else rol="ROLE_USER"; 
    	
    	userDAO.updateRole(request.getParameter("username"), rol);
        
        return new ModelAndView("redirect:/default");
    }

}
