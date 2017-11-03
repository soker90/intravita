package com.mensubiqua.intravita.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.auxiliar.MailSender;
import com.mensubiqua.intravita.auxiliar.Variables;
import com.mensubiqua.intravita.dao.PublicacionDAOImpl;
import com.mensubiqua.intravita.dao.UserCodeDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.PublicacionVista;
import com.mensubiqua.intravita.model.User;

@Controller
public class AdminController {

	@Autowired
    UserDAOImpl userDAO;
	
	@Autowired
	PublicacionDAOImpl publicacionDAO;
	
	@Autowired
    UserCodeDAOImpl userCodeDAO;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/admin**")
    public ModelAndView adminPage(HttpSession sesion) {
    	
    	return new ModelAndView("redirect:/admin/usuarios");

    }
	
	@RequestMapping(value = "/admin/usuarios**")
    public ModelAndView adminUsuarios(HttpSession sesion) {
    	User user = (User) sesion.getAttribute("user");
    	
    	try {
    	
	    	if(user.getRol() != null)
	    	{
		    	if(user.getRol().equals("ROLE_ADMIN"))
		    	{
			        ModelAndView model = new ModelAndView();
			        ArrayList<String[]> listVar = new ArrayList<String[]>();
			        
			        ArrayList<User> users = userDAO.selectAll();
			        
			        for(User user1: users)
			        {
			        	String validar = "";
			        	String boton = "superadmin";
			        	if(user1.getRol().equals("ROLE_ADMIN"))
			        		boton="Sí";
			        	else if (user1.getRol().equals("ROLE_USER")) {
							
								boton = "No";
						}
			        	if(!user1.isValidado()) validar ="validar";
			        	String[] u = {user1.getNombre(),user1.getApellido(), boton, user1.getNickname(), validar};
				        
				        listVar.add(u);
			        }
			        
			        model.addObject("listName", listVar.iterator());
					
					Variables v = (Variables) sesion.getAttribute("var");
					v.setCont(1);
					
					
			        model.setViewName("admin/usuarios");
			
			        return model;
		    	}
	    	}
    	} catch (Exception e) {
            return new ModelAndView("redirect:/user");
    	}
    	
    	return new ModelAndView("redirect:/default");

    }
	
	@RequestMapping(value = "/admin/publicaciones**")
    public ModelAndView adminPublicaciones(HttpSession sesion) {
    	User user = (User) sesion.getAttribute("user");
    	
    	try {
    	
	    	if(user.getRol() != null)
	    	{
		    	if(user.getRol().equals("ROLE_ADMIN"))
		    	{
			        ModelAndView model = new ModelAndView();

			        
			        ArrayList<PublicacionVista> publicaciones = new ArrayList<PublicacionVista>(); 
					for (Publicacion p : publicacionDAO.selectAll()) {
						User u = userDAO.find(Funciones.encrypt(p.getNickname()));
						
						File f = new File(servletContext.getRealPath("/resources/img/"+u.getNickname()+".jpg"));
			            if(f.exists() && !f.isDirectory()) { 
			                u.setFoto(u.getNickname());
			            } else {
			            	u.setFoto("user");
			            }
			            
						publicaciones.add(new PublicacionVista(p, u));
					}

					model.addObject("publicaciones", publicaciones);
					
					Variables v = (Variables) sesion.getAttribute("var");
					v.setCont(1);
					
					
			        model.setViewName("admin/publicaciones");
			
			        return model;
		    	}
	    	}
    	} catch (Exception e) {
            return new ModelAndView("redirect:/user");
    	}
    	
    	return new ModelAndView("redirect:/default");

    }
	
    @RequestMapping(value = "/admin/borrarUsuario**", method = RequestMethod.POST)
    public ModelAndView deleteUser(HttpSession sesion, HttpServletRequest request) {
    	String username = request.getParameter("username");
    	if(!username.equals("super.admin"))
    	{
    		File foto = new File(servletContext.getRealPath("/resources/img/"+username+".jpg"));
    		userDAO.delete(username, foto);
    		Variables v = (Variables) sesion.getAttribute("var");
    		v.setCont(0);
    		v.setMensaje("Usuario borrado correctamente");
    	}
    		
    	
    	

        return new ModelAndView("redirect:/default");
    }
    
    @RequestMapping(value = "/admin/editarUsuario**", method = RequestMethod.POST)
    public ModelAndView perfil(HttpSession sesion, HttpServletRequest request) {
    	User user = (User) sesion.getAttribute("user");
    	Variables v = (Variables) sesion.getAttribute("var");
		v.setCont(1);
    	
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
        
        String rutaFoto = servletContext.getRealPath("/resources/img/");
        
        userDAO.update(user, rutaFoto,user.getNickname());
        
        Variables v = (Variables) session.getAttribute("var");
		v.setCont(0);
		v.setMensaje("Usuario actualizado correctamente");
        
        return new ModelAndView("redirect:/default");
    }
    
    @RequestMapping(value = "/admin/cambiarPassword**", method = RequestMethod.POST)
    public ModelAndView updatePassword(HttpSession session, HttpServletRequest request) {
    	Variables v = (Variables) session.getAttribute("var");
    	if(!request.getParameter("nick").equals("super.admin"))
    	{
    		User user = userDAO.find(Funciones.encrypt(request.getParameter("nick")));
    		if (request.getParameter("password").equals(request.getParameter("password2"))) {
				user.setPassword(Funciones.encrypt_md5(request.getParameter("password")));
				userDAO.updatePassword(user);
				v.setMensaje("Contraseña cambiada correctamente.");
			} else {
				v.setMensaje("Las contraseñas no coinciden.");
			}
    		v.setCont(0);
            
    	}
    	
    	return new ModelAndView("redirect:/default");
        
    }
    
    @RequestMapping(value = "/admin/updateRol**", method = RequestMethod.POST)
    public ModelAndView updateRol(HttpSession session, HttpServletRequest request) {
    	String rol = request.getParameter("rol");
    	if(rol.equals("No")) rol="ROLE_ADMIN"; else rol="ROLE_USER"; 
    	
    	userDAO.updateRole(request.getParameter("username"), rol);
    	
    	Variables v = (Variables) session.getAttribute("var");
		v.setCont(0);
		v.setMensaje("Rol actualizado correctamente");
        
        return new ModelAndView("redirect:/default");
    }
    
    @RequestMapping(value = "/admin/validar**", method = RequestMethod.POST)
    public ModelAndView validar(HttpSession session, HttpServletRequest request) {
    	User user = userDAO.find(Funciones.encrypt(request.getParameter("username")));
    	boolean validar = true;
    	Variables v = (Variables) session.getAttribute("var");
    	
    	if(user != null) {
    		user.setValidado(validar);
    		userDAO.updateValidacion(user);
    		userCodeDAO.delete(user.getNickname());
    		MailSender ms = new MailSender(user.getEmail(), "Cuenta Validada", "Hola "+user.getNickname()+", su cuenta ha sido validada por un administrador");
    		v.setMensaje("Cuenta validada correctamente");
    	}else {
    		v.setMensaje("Error al validar cuenta");
    	}
    	
    	v.setCont(0);

    	return new ModelAndView("redirect:/default");
    }
    
	@RequestMapping(value = "/admin/borrarPublicacion**", method = RequestMethod.POST)
	public String removePublicacion(HttpSession session, HttpServletRequest request) {
		
		publicacionDAO.delete(request.getParameter("id"));
		
		Variables v = (Variables) session.getAttribute("var");
		v.setCont(0);
		v.setMensaje("Pubicación borrada correctamente");

		return "redirect:/admin/publicaciones";
	}
	
	@RequestMapping(value = "/admin/editarPublicacion**", method = RequestMethod.POST)
    public ModelAndView editarPublicacion(HttpSession sesion, HttpServletRequest request) {
    	User user = (User) sesion.getAttribute("user");
    	Variables v = (Variables) sesion.getAttribute("var");
		v.setCont(1);
    	
    	try {
    	
	    	if(user.getRol() != null)
	    	{
		    	if(user.getRol().equals("ROLE_ADMIN"))
		    	{
		    		ModelAndView model = new ModelAndView();
		    		Publicacion p = publicacionDAO.find(request.getParameter("id"));
		    		model.addObject("publicacion", p);
		            model.setViewName("admin/editarPublicacion");
		            return model;
		    	}
	    	}
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/admin/publicaciones");
    	}
    	
    	return new ModelAndView("redirect:/admin/publicaciones");

    }
	
	@RequestMapping(value = "/admin/updatePublicacion**", method = RequestMethod.POST)
    public ModelAndView updatePublicacion(HttpSession session, HttpServletRequest request) {
    	Publicacion p = publicacionDAO.find(request.getParameter("id"));
    	p.setTexto(request.getParameter("texto"));
    	
    	publicacionDAO.update(p);
    	
    	Variables v = (Variables) session.getAttribute("var");
		v.setCont(0);
		v.setMensaje("Pubicación actualizada correctamente");
        
		return new ModelAndView("redirect:/admin/publicaciones");
    }

}
