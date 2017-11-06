package com.mensubiqua.intravita.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.auxiliar.Variables;
import com.mensubiqua.intravita.dao.PublicacionDAOImpl;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.Publicacion;
import com.mensubiqua.intravita.model.PublicacionVista;
import com.mensubiqua.intravita.model.User;

@Controller
public class UserController {

	@Autowired
	UserDAOImpl userDAO;

	@Autowired
	PublicacionDAOImpl publicacionDAO;
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/user**")
	public ModelAndView homePage(HttpSession sesion) {
		User user = (User) sesion.getAttribute("user");

		try {

			if (user.getRol() != null) {
				if (user.getRol().equals("ROLE_USER") | user.getRol().equals("ROLE_ADMIN")) {
					ModelAndView model = new ModelAndView();
					ArrayList<PublicacionVista> publicaciones = new ArrayList<PublicacionVista>(); 
					for (Publicacion p : publicacionDAO.selectAll()) {
						if(!p.getNickname().equals(user.getNickname()) && p.getPrivacidad().equals("privada"))
							continue;
						User u = userDAO.find(Funciones.encrypt(p.getNickname()));
						
						File f = new File(servletContext.getRealPath("/resources/img/"+u.getNickname()+".jpg"));
			            if(f.exists() && !f.isDirectory()) { 
			                u.setFoto(u.getNickname());
			            } else {
			            	u.setFoto("user");
			            }
			            
						publicaciones.add(new PublicacionVista(p, u));
					}
					String vacio = "";
					if(publicaciones.size() == 0)
						vacio = "vacio";
					
					model.addObject("vacio", vacio);
					model.addObject("publicaciones", publicaciones);
					
					Variables v = (Variables) sesion.getAttribute("var");
					v.setCont(1);
					
					model.setViewName("user/index");
					return model;
				}
			}
		} catch (Exception e) {
			System.out.println("Error al cargar la vista de usuario");
			return new ModelAndView("redirect:/default");
		}

		return new ModelAndView("redirect:/default");

	}

	@RequestMapping(value = "/user/configuracion**")
	public ModelAndView perfil(HttpSession sesion) {
		User user = (User) sesion.getAttribute("user");

		try {

			if (user.getRol() != null) {
				if (user.getRol().equals("ROLE_USER") | user.getRol().equals("ROLE_ADMIN")) {
					ModelAndView model = new ModelAndView();
					model.setViewName("user/perfil");
					Variables v = (Variables) sesion.getAttribute("var");
					v.setCont(1);
					return model;
				}
			}
		} catch (Exception e) {
			return new ModelAndView("redirect:/user");
		}

		return new ModelAndView("redirect:/user");

	}
	
	@RequestMapping(value = "/user/ver**")
	public String ver_redirect() {
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/user/ver/{usuario:.+}")
	public ModelAndView ver(HttpSession sesion, @PathVariable(value="usuario") String nick) {
		User user = userDAO.find(Funciones.encrypt(nick));
		
		if(user == null)
			return new ModelAndView("redirect:/user");

		try {
				ModelAndView model = new ModelAndView();
				model.setViewName("user/ver");
				
				File f = new File(servletContext.getRealPath("/resources/img/"+user.getNickname()+".jpg"));
	            if(f.exists() && !f.isDirectory()) { 
	                user.setFoto(user.getNickname());
	            } else {
	            	user.setFoto("user");
	            }
				
				model.addObject("perfil", user);
				
				ArrayList<PublicacionVista> publicaciones = new ArrayList<PublicacionVista>(); 
				for (Publicacion p : publicacionDAO.findAll(user.getNickname())) {
					if(!p.getNickname().equals(user.getNickname()) && p.getPrivacidad().equals("privada"))
						continue;
					User u = userDAO.find(Funciones.encrypt(p.getNickname()));
					
					f = new File(servletContext.getRealPath("/resources/img/"+u.getNickname()+".jpg"));
		            if(f.exists() && !f.isDirectory()) { 
		                u.setFoto(u.getNickname());
		            } else {
		            	u.setFoto("user");
		            }
		            
					publicaciones.add(new PublicacionVista(p, u));
				}
				String vacio = "";
				if(publicaciones.size() == 0)
					vacio = "vacio";
				
				model.addObject("vacio", vacio);
				model.addObject("publicaciones", publicaciones);
				
				return model;
			
		} catch (Exception e) {
			
			return new ModelAndView("redirect:/user");
		}


	}

	@RequestMapping(value = "/user/editarCuenta**", method = RequestMethod.POST)
	public ModelAndView updateAccount(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		String oldNick = user.getNickname();
		user.setNickname(request.getParameter("nick").toLowerCase());
        user.setNombre(request.getParameter("nombre"));
        user.setApellido(request.getParameter("apellidos"));
        user.setFoto(request.getParameter("foto"));
        user.setEmail(request.getParameter("email"));
        
        String rutaFoto = servletContext.getRealPath("/resources/img/");
        userDAO.update(user, rutaFoto, oldNick);
		
		
		File f = new File(servletContext.getRealPath("/resources/img/"+user.getNickname().toLowerCase()+".jpg"));
		
        if(f.exists() && !f.isDirectory()) { 
            user.setFoto(user.getNickname().toLowerCase());
        } else {
        	user.setFoto("user");
        }
        
        
		Variables v = (Variables) session.getAttribute("var");
		v.setCont(0);
		v.setMensaje("Perfil actualizado correctamente");
		v.setTipo("correcto");

		return new ModelAndView("redirect:/user/configuracion");
	}

	@RequestMapping(value = "/user/cambiarPassword**", method = RequestMethod.POST)
	public ModelAndView updatePassword(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		Variables v = (Variables) session.getAttribute("var");
		v.setCont(0);
		
		if (Funciones.encrypt_md5(request.getParameter("password_old")).equals(user.getPassword())) {
			if (request.getParameter("password").equals(request.getParameter("password2"))) {
				user.setPassword(Funciones.encrypt_md5(request.getParameter("password")));
				userDAO.updatePassword(user);
				request.getSession().setAttribute("user", user);
				
				v.setMensaje("Contraseña actualizada correctamente.");
				v.setTipo("correcto");
			} else {
				v.setMensaje("ERROR: Las contraseñas no coinciden.");
				v.setTipo("error");
			}
		} else {
			v.setMensaje("ERROR: La contraseña antigua no coincide.");
			v.setTipo("error");
		}
		

		return new ModelAndView("redirect:/user/configuracion");
	}

	@RequestMapping(value = "/user/borrarCuenta**", method = RequestMethod.GET)
	public String deleteAccount(HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		File f = new File(servletContext.getRealPath("/resources/img/"+user.getNickname()+".jpg"));
		userDAO.delete(user.getNickname(), f);
		session.invalidate();

		return "redirect:/default";
	}

	@RequestMapping(value = "/user/publicar", method = RequestMethod.POST)
	public ModelAndView publicar(HttpSession session, HttpServletRequest request) {

		User user = (User) session.getAttribute("user");
		Date fecha = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String sFecha = dt.format(fecha);
		Publicacion p = new Publicacion(user.getNickname(), request.getParameter("texto"),
				request.getParameter("privacidad"), sFecha);

		publicacionDAO.insert(p);

		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value = "/user/removePublicacion**", method = RequestMethod.POST)
	public String removePublicacion(HttpSession session, HttpServletRequest request) {
		Publicacion p = publicacionDAO.find(request.getParameter("id"));
		User user = (User) session.getAttribute("user");
		
		if(p.getNickname().equals(user.getNickname()))
		{
			publicacionDAO.delete(p.getId());
		}
		
		Variables v = (Variables) session.getAttribute("var");
		v.setCont(0);
		v.setMensaje("Publicación borrada correctamente");
		v.setTipo("correcto");

		return "redirect:/user";
	}
	
	@RequestMapping(value = "/user/editarPublicacion**", method = RequestMethod.POST)
    public ModelAndView editarPublicacion(HttpSession sesion, HttpServletRequest request) {
    	System.out.println("llega");
    	Publicacion p = publicacionDAO.find(request.getParameter("id"));
    	
    	try {
    		
    		ModelAndView model = new ModelAndView();
    		model.addObject("publicacion", p);
    		Variables v = (Variables) sesion.getAttribute("var");
			v.setCont(1);
            model.setViewName("user/editarPublicacion");
            return model;
	    	
    	} catch (Exception e) {
            return new ModelAndView("redirect:/user");
    	}

    }
	
	@RequestMapping(value = "/user/updatePublicacion**", method = RequestMethod.POST)
    public ModelAndView updatePublicacion(HttpSession session, HttpServletRequest request) {
    	Publicacion p = publicacionDAO.find(request.getParameter("id"));
    	User user = (User) session.getAttribute("user");
    	p.setTexto(request.getParameter("texto"));
    	
    	if(user.getNickname().equals(p.getNickname()))
    	{
	    	
    		publicacionDAO.update(p);
    		Variables v = (Variables) session.getAttribute("var");
			v.setCont(0);
			v.setMensaje("Publicación actualizada correctamente");
			v.setTipo("correcto");
	    	
    	}

        return new ModelAndView("redirect:/user");
    }
	
	@RequestMapping(value = "/user/buscar**")
	public ModelAndView buscar(HttpSession sesion, HttpServletRequest request) {
		User user = (User) sesion.getAttribute("user");

		try {

			if (user.getRol() != null) {
				if (user.getRol().equals("ROLE_USER") | user.getRol().equals("ROLE_ADMIN")) {
					String cadena = request.getParameter("busqueda");
					ArrayList<User> usuarios = userDAO.search(cadena);
					
					String vacio = "";
					if(usuarios.size() == 0)
						vacio = "vacio";
					
					for (User user2 : usuarios) {
						setFoto(user2);
					}
					
					ModelAndView model = new ModelAndView();
					System.out.println(usuarios.size());
					model.addObject("usuarios",usuarios);
					model.setViewName("user/usuarios");
					
					Variables v = (Variables) sesion.getAttribute("var");
					v.setCont(1);
					return model;
				}
			}
		} catch (Exception e) {
			return new ModelAndView("redirect:/user");
		}

		return new ModelAndView("redirect:/user");

	}
	
	@RequestMapping(value = "/user/compartir**")
	public ModelAndView compartir(HttpSession sesion, HttpServletRequest request) {
		User user = (User) sesion.getAttribute("user");

		try {

			if (user.getRol() != null) {
				if (user.getRol().equals("ROLE_USER") | user.getRol().equals("ROLE_ADMIN")) {
					
					Date fecha = new Date();
					SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
					String sFecha = dt.format(fecha);
					Publicacion p = new Publicacion(user.getNickname(), "cp#" + request.getParameter("id"),
							"publico", sFecha); //TODO cambiar a amigos cuando esten las relaciones

					publicacionDAO.insert(p);
					
					Variables v = (Variables) sesion.getAttribute("var");
					v.setCont(1);
								
					return new ModelAndView("redirect:/user");
				}
			}
		} catch (Exception e) {
			Variables v = (Variables) sesion.getAttribute("var");
			v.setCont(0);
			v.setMensaje("No se ha podido guardar el 'Me gusta'");
			v.setTipo("error");
			return new ModelAndView("redirect:/user");
		}

		return new ModelAndView("redirect:/user");

	}
	
	
	private void setFoto(User usuario)
	{
		File f = new File(servletContext.getRealPath("/resources/img/"+usuario.getNickname()+".jpg"));
        if(f.exists() && !f.isDirectory()) { 
            usuario.setFoto(usuario.getNickname());
        } else {
        	usuario.setFoto("user");
        }
	}

}
