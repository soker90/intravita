package com.mensubiqua.intravita.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
						if(!p.getNickname().equals(user.getNickname()) && p.getPrivacidad().equals("privado"))
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

	@RequestMapping(value = "/user/perfil**")
	public ModelAndView perfil(HttpSession sesion) {
		User user = (User) sesion.getAttribute("user");

		try {

			if (user.getRol() != null) {
				if (user.getRol().equals("ROLE_USER") | user.getRol().equals("ROLE_ADMIN")) {
					ModelAndView model = new ModelAndView();
					model.setViewName("user/perfil");
					return model;
				}
			}
		} catch (Exception e) {
			return new ModelAndView("redirect:/default");
		}

		return new ModelAndView("redirect:/default");

	}

	@RequestMapping(value = "/user/editarCuenta**", method = RequestMethod.POST)
	public ModelAndView updateAccount(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		user.setNickname(request.getParameter("nick"));
		user.setNombre(request.getParameter("nombre"));
		user.setApellido(request.getParameter("apellidos"));
		// user.setFoto(//request.getParameter("foto"));
		user.setEmail(request.getParameter("email"));

		userDAO.update(user);

		return new ModelAndView("redirect:/user/perfil");
	}

	@RequestMapping(value = "/user/cambiarPassword**", method = RequestMethod.POST)
	public ModelAndView updatePassword(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		if (Funciones.encrypt_md5(request.getParameter("password_old")).equals(user.getPassword())) {
			if (request.getParameter("password").equals(request.getParameter("password2"))) {
				user.setPassword(Funciones.encrypt_md5(request.getParameter("password")));
				userDAO.updatePassword(user);
				request.getSession().setAttribute("user", user);
			} else {
				// TODO enviar un mensaje a una variable de sesion
			}
		} else {
			// TODO enviar un mensaje a una variable de sesion
		}
		

		return new ModelAndView("redirect:/default");
	}

	@RequestMapping(value = "/user/borrarCuenta**", method = RequestMethod.GET)
	public String deleteAccount(HttpSession session) {
		User user = (User) session.getAttribute("user");
		userDAO.delete(Funciones.encrypt(user.getNickname()));
		session.invalidate();

		return "redirect:/default";
	}

	@RequestMapping(value = "/user/publicar", method = RequestMethod.POST)
	public ModelAndView publicar(HttpSession session, HttpServletRequest request) {

		User user = (User) session.getAttribute("user");
		Date fecha = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM HH:mm"); 
		String sFecha = dt.format(fecha);
		Publicacion p = new Publicacion(user.getNickname(), request.getParameter("texto"),
				request.getParameter("privacidad"), sFecha);

		publicacionDAO.insert(p);

		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value = "/user/removePublicacion**", method = RequestMethod.POST)
	public String removePublicacion(HttpSession session, HttpServletRequest request) {
		System.out.println(request.getParameter("id"));
		Publicacion p = publicacionDAO.find(request.getParameter("id"));
		User user = (User) session.getAttribute("user");
		
		if(p.getNickname().equals(user.getNickname()))
		{
			publicacionDAO.delete(p.getId());
		}

		return "redirect:/user";
	}

}
