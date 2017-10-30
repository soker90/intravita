package com.mensubiqua.intravita.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.auxiliar.Variables;
import com.mensubiqua.intravita.dao.PublicacionDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;

@Controller
public class GeneralController {

	@Autowired
    UserDAOImpl userDAO;
	
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	Variables var;
	
	
    @RequestMapping({"/default**", "/"})
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
    
    @RequestMapping(value = "/login**", method = RequestMethod.GET)
    public String login() {    	
        return "login";
    }

    @RequestMapping(value = "registro**", method = RequestMethod.POST)
    public ModelAndView registrar(HttpServletRequest request)  {

    	User user = null;
    	ModelAndView model = new ModelAndView();
        model.setViewName("login");
    	
        if (!request.getParameter("password").equals(request.getParameter("password2"))) 
        	model.addObject("mensaje", "Las contraseñas no coinciden");

        else if (userDAO.find(Funciones.encrypt(request.getParameter("nombre") + "." + request.getParameter("apellido"))) != null) 
        	model.addObject("mensaje", "Este usuario ya existe");

        else {
        	String nombre = Funciones.encrypt(request.getParameter("nombre"));
        	String apellido = Funciones.encrypt(request.getParameter("apellido"));
        	String email = Funciones.encrypt(request.getParameter("email"));
        	String password = Funciones.encrypt_md5(request.getParameter("password"));
        	String nick = Funciones.encrypt((request.getParameter("nombre") + 
        			"." + request.getParameter("apellido").toLowerCase()));
            user = new User(nombre, apellido, email, password,
            		"ROLE_USER", nick);
            userDAO.insert(user);
            model.addObject("mensaje", "Usuario creado con exito");
        }

        return model;
    }




    @RequestMapping(value = "logear**", method = RequestMethod.POST)
    public ModelAndView logear(HttpServletRequest request)  {
        ModelAndView model = new ModelAndView();
        User user = userDAO.find(Funciones.encrypt(request.getParameter("username").toLowerCase()));
        
        model.setViewName("redirect:/default");
    	
        if (user == null) model.addObject("mensaje2", "Este usuario no existe");

        else if (!Funciones.encrypt_md5(request.getParameter("password")).equals(user.getPassword())) 
        	model.addObject("mensaje2", "Las contraseñas no coinciden");

        else {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("var", new Variables());
            
            File f = new File(servletContext.getRealPath("/resources/img/"+user.getNickname()+".jpg"));
            if(f.exists() && !f.isDirectory()) { 
                user.setFoto(user.getNickname());
            } else {
            	user.setFoto("user");
            }
        }

        return model;
        
    }
    
    @RequestMapping(value = "logout**", method = RequestMethod.GET)
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
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("nick") String nick, HttpSession session) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// Crear el directorio para almacenar el archivo

				File dir = new File(servletContext.getRealPath("/resources/img/"));
				
				if (!dir.exists())
					dir.mkdirs();

				// Crear documento en el servidor
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nick + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				User user = (User) session.getAttribute("user");
				if(user.getNickname().equals(nick))
					user.setFoto(nick);

				System.out.println("Ubicación de documento = " + serverFile.getAbsolutePath());

				//return "Documento subido correctamente = " + file.getOriginalFilename() + " Ubicacion del Archivo = " + serverFile.getAbsolutePath();
			} catch (Exception e) {
				//return "Ocurrio un error al subir documento" + file.getOriginalFilename() + " => " + e.getMessage();
			}
		} else {
			//return "Ocurrio un error al subir " + file.getOriginalFilename() + " documento vacio.";
		}
		
		return "redirect:/user/perfil";
	}

}
