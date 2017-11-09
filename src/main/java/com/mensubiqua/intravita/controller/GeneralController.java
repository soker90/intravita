package com.mensubiqua.intravita.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import com.mensubiqua.intravita.auxiliar.MailSender;
import com.mensubiqua.intravita.auxiliar.Variables;
import com.mensubiqua.intravita.dao.PublicacionDAOImpl;
import com.mensubiqua.intravita.dao.UserCodeDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;
import com.mensubiqua.intravita.model.UserCode;
import com.mongodb.diagnostics.logging.Logger;

@Controller
public class GeneralController {

	@Autowired
    UserDAOImpl userDAO;
	
	@Autowired
	PublicacionDAOImpl publicacionDAO;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	UserCodeDAOImpl userCodeDAO;
	
	private String url_heroku = "https://intravita.herokuapp.com";

	
    @RequestMapping("/default**")
    public ModelAndView defaultAfterLogin(HttpSession sesion, HttpServletRequest request) {
    	User user = (User) sesion.getAttribute("user");
    	
    	boolean local = request.getRequestURL().toString().contains("localhost");
    	Variables var = new Variables();
        var.setUrl(local);
        request.getSession().setAttribute("var", var);
        request.getSession().setAttribute("url", var.getUrl());
        
    	try {
    		
	    	if(user.getRol() == null)
	    		return new ModelAndView("redirect:/login");
	    	
	        if (user.getRol().equalsIgnoreCase("ROLE_ADMIN")) {
	        	return new ModelAndView("redirect:/admin");
	        }
	
	        if (user.getRol().equalsIgnoreCase("ROLE_USER")) {
	        	return new ModelAndView("redirect:/user");
	        }
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login");
    	}
    	
    	return new ModelAndView("redirect:/login");
    }
    
    @RequestMapping(value = "/login**", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpSession sesion) {  
    	String mensaje = (String) sesion.getAttribute("mensaje");
    	String mensaje2 = (String) sesion.getAttribute("mensaje2");
    	request.getSession().setAttribute("mensaje2", "");
        request.getSession().setAttribute("mensaje", "");
        
        String nombre = (String) sesion.getAttribute("rnombre");
    	String apellido = (String) sesion.getAttribute("rapellido");
    	String email = (String) sesion.getAttribute("remail");
    	String nick = (String) sesion.getAttribute("rnick");
    	
    	request.getSession().setAttribute("mensaje2", "");
        request.getSession().setAttribute("mensaje", "");
        
        request.getSession().setAttribute("rnombre", "");
        request.getSession().setAttribute("rapellido", "");
        request.getSession().setAttribute("remail", "");
        request.getSession().setAttribute("rnick", "");
        
        ModelAndView model = new ModelAndView();
        
        model.addObject("mensaje", mensaje);
        model.addObject("mensaje2", mensaje2);
        model.addObject("rnombre", nombre);
        model.addObject("rapellido", apellido);
        model.addObject("remail", email);
        model.addObject("rnick", nick);
        
        Variables url = new Variables();
    	boolean local = request.getRequestURL().toString().contains("localhost");
        url.setUrl(local);
        model.addObject("url", url.getUrl());
        
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "registro**", method = RequestMethod.POST)
    public ModelAndView registrar(HttpServletRequest request)  {
    	User user = null;
    	UserCode uc = null;
    	
    	String sNombre = request.getParameter("nombre");
    	String sApellido = request.getParameter("apellido");
    	String sEmail = request.getParameter("email");
    	String sNick = request.getParameter("nickname");
    	
    	request.getSession().setAttribute("rnombre", sNombre);
    	request.getSession().setAttribute("rapellido", sApellido);
    	request.getSession().setAttribute("remail", sEmail);
    	request.getSession().setAttribute("rnick", sNick);
    	
    	if(Funciones.validarEmail(request.getParameter("email")))
    	{
	        if (!request.getParameter("password").equals(request.getParameter("password2"))) 
	        	request.getSession().setAttribute("mensaje", "<strong style=\"color: red !important;\">Las contrasenas no coinciden</strong>");
	
	        else if (userDAO.find(Funciones.encrypt(request.getParameter("nickname"))) != null)
	        	request.getSession().setAttribute("mensaje", "<strong style=\"color: red !important;\">Este usuario ya existe</strong>");
	
	        else {
	        	
	        	String nombre = Funciones.encrypt(sNombre);
	        	String apellido = Funciones.encrypt(sApellido);
	        	String email = Funciones.encrypt(sEmail);
	        	String password = Funciones.encrypt_md5(request.getParameter("password"));
	        	String nick = Funciones.encrypt((sNick.toLowerCase()));
	        	
	            user = new User(nombre, apellido, email, password,
	            		"ROLE_USER", nick,false);
	            userDAO.insert(user);
	            uc = new UserCode(Funciones.decrypt(user.getNickname()),Funciones.generarStringAleatorio());
	            userCodeDAO.insert(uc);
	            
	            String url = "https://intravita.herokuapp.com";
	            if(request.getRequestURL().toString().contains("localhost"))
	            	url = "https://localhost:8443/intravita";
	            
	            MailSender EnviadorMail = new MailSender(request.getParameter("email"),
	                    "Validar cuenta Intravita", "Hola: "+uc.getNickname()+". Este es su codigo de validacion: "+uc.getCode()+". Para validar su usuario introduzca el codigo en el siguiente enlace: " + url + "/validacion");
	            request.getSession().setAttribute("mensaje", "<strong style=\"color: blue !important;\">Usuario creado con exito, consulte su correo para validar su cuenta</strong>");
	        }
    	} else {
    		request.getSession().setAttribute("mensaje", "<strong style=\"color: red !important;\">El correo electr&oacute;nico no es v&aacute;lido</strong>");
    	}

    	return new ModelAndView("redirect:/login");
    }




    @RequestMapping(value = "logear**", method = RequestMethod.POST)
    public ModelAndView logear(HttpServletRequest request)  {
    	boolean local = request.getRequestURL().toString().contains("localhost");
        
        Variables var = new Variables();
        var.setUrl(local);
        request.getSession().setAttribute("var", var);
        
        User user = userDAO.find(Funciones.encrypt(request.getParameter("username").toLowerCase()));
        	
        if (user == null) request.getSession().setAttribute("mensaje2", "Este usuario no existe");

        else if(!user.isValidado())
        	request.getSession().setAttribute("mensaje2", "Consulte su correo y valide la cuenta");
        
        else if (!Funciones.encrypt_md5(request.getParameter("password")).equalsIgnoreCase(user.getPassword())) 
        	request.getSession().setAttribute("mensaje2", "Contraseña incorrecta");
        else {
            request.getSession().setAttribute("user", user);
            
            
            File f = new File(servletContext.getRealPath("/resources/img/"+user.getNickname()+".jpg"));
            if(f.exists() && !f.isDirectory()) { 
                user.setFoto(user.getNickname());
            } else {
            	user.setFoto("user");
            }
            
            request.getSession().setAttribute("mensaje2", "");
            request.getSession().setAttribute("mensaje", "");
            if(local)
            	return new ModelAndView("redirect:/user");
            
            return new ModelAndView("redirect:"+ var.getUrl() +"/user");
        }

        return new ModelAndView("redirect:/login");
        
    }
    
    @RequestMapping(value = "logout**", method = RequestMethod.GET)
    public String logout(HttpSession sesion, HttpServletRequest request) {
    	sesion.invalidate();
        return "redirect:/login";
    }



    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error403(Principal user, HttpServletRequest request){
    	ModelAndView model = new ModelAndView();
        model.addObject("head", "Error 403");
        model.addObject("title", "Error 403 - Acceso denegado");
        if (user != null){
            model.addObject("msg", "Hola "+user.getName()+", no tienes permiso para acceder a esta página");
        }else{
            model.addObject("msg", "No tienes permiso para acceder a esta página");
        }

        model.setViewName("error");
        
        Variables url = new Variables();
    	boolean local = request.getRequestURL().toString().contains("localhost");
        url.setUrl(local);
        model.addObject("url", url.getUrl());
        
        return model;

    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("nick") String nick, HttpSession session) {
    	Variables v = (Variables) session.getAttribute("var");
		v.setCont(0);

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

				v.setMensaje("Fotografía actualizada correctamente");
			} catch (Exception e) {
				v.setMensaje("ERROR: Ocurrió un error al subir la fotografía. Tamaño máximo 10Mb.");
			}
		} else {
			v.setMensaje("ERROR: Ocurrió un error al subir la fotografía. Tamaño máximo 10Mb.");
		}
		
		return "redirect:/user/perfil";
	}
    
    @RequestMapping(value = "/validacion", method = RequestMethod.GET)
    public String login(HttpServletRequest request) { 
    	return "validacion";
    }

    @RequestMapping(value = "validacion", method = RequestMethod.POST)
    public ModelAndView validar(HttpServletRequest request) {
    	ModelAndView model = new ModelAndView();
    	UserCode uc = userCodeDAO.find(request.getParameter("username").toLowerCase());
    	User u = null;
    	Variables var = null;
    	
    	model.setViewName("validacion");
    	
    	Variables url = new Variables();
    	boolean local = request.getRequestURL().toString().contains("localhost");
        url.setUrl(local);
        model.addObject("url", url.getUrl());
    	
    	if(uc == null)
    		model.addObject("mensaje2","Este usuario no existe");
    	else if(uc.getNickname().equals(request.getParameter("username").toLowerCase()) && uc.getCode().equals(request.getParameter("code"))) {
    		u = userDAO.find(Funciones.encrypt(uc.getNickname()));
    		if(u != null){
        			u.setValidado(true);
        			userDAO.updateValidacion(u);
        			userCodeDAO.delete(uc.getNickname());
        			
        			request.getSession().setAttribute("user", u);
                                        
                    var = new Variables();
                    var.setUrl(local);
                    var.setCont(0);
                    var.setMensaje("Su cuenta ha sido validada. ¡Bienvenido a intravita!.");
                    var.setTipo("info");
                    request.getSession().setAttribute("var", var);
                    
                    foto(u);
                    
                    model.setViewName("redirect:/user");
    		}else{
    			model.addObject("mensaje2","Error desconocido");
    		}
    	}else {
    		model.addObject("mensaje2","Usuario o codigo incorrectos");
    	}
 
    	return model;
    }
    
    @RequestMapping(value = "/recuperar", method = RequestMethod.GET)
    public ModelAndView recuperar(HttpServletRequest request, HttpSession sesion) {    	
    	String mensaje = (String) sesion.getAttribute("mensaje2");
		request.getSession().setAttribute("mensaje2", "");
		
		ModelAndView model = new ModelAndView();
        
        model.addObject("mensaje2", mensaje);
        
        model.setViewName("recuperar_pass");
        
        Variables url = new Variables();
    	boolean local = request.getRequestURL().toString().contains("localhost");
        url.setUrl(local);
        model.addObject("url", url.getUrl());
		
        return model;
    }

	@RequestMapping(value = "/recuperarpass", method = RequestMethod.POST)
	public ModelAndView recuperacion(HttpServletRequest request, HttpSession sesion)  {
	    
		User user = userDAO.find(Funciones.encrypt(request.getParameter("username").toLowerCase()));	   
		String password;
		
	    if (user == null) request.getSession().setAttribute("mensaje2", "Este usuario no existe");
	
	    else {
	    	request.getSession().setAttribute("mensaje2", "Se ha mandado un nueva contraseña a su correo");
	    	password = Funciones.generarStringAleatorio();
	    	MailSender EnviadorMail = new MailSender(user.getEmail(),
	                "Nueva contraseña", "Buenas " + user.getNombre() + " " + user.getApellido() + " su nueva contraseña es: " + password);
	    	password = Funciones.encrypt_md5(password);
	    	user.setPassword(password);
	        userDAO.updatePassword(user);
	        return new ModelAndView("redirect:/login");
	    }
	
	    return new ModelAndView("redirect:/recuperar");
	    
	}
    	
    public void foto(User u) {
    	File f = new File(servletContext.getRealPath("/resources/img/"+u.getNickname()+".jpg"));
        if(f.exists() && !f.isDirectory()) { 
            u.setFoto(u.getNickname());
        } else {
        	u.setFoto("user");
        }
    }
    

}
