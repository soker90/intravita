package com.mensubiqua.intravita.controller;

import java.util.Date;

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
import com.mensubiqua.intravita.model.User;

@Controller
public class UserController {

	@Autowired
	UserDAOImpl userDAO;

	@Autowired
	Variables var;

	@Autowired
	PublicacionDAOImpl publicacionDAO;

	@RequestMapping(value = "/user**")
	public ModelAndView homePage(HttpSession sesion) {
		User user = (User) sesion.getAttribute("user");

		try {

			if (user.getRol() != null) {
				if (user.getRol().equals("ROLE_USER") | user.getRol().equals("ROLE_ADMIN")) {
					ModelAndView model = new ModelAndView();
					model.addObject("title", "Web para usuarios");
					model.addObject("message", "Pagina para usuarios corrientes");
					model.setViewName("user/index");
					return model;
				}
			}
		} catch (Exception e) {
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

		Publicacion p = new Publicacion(user.getNickname(), request.getParameter("texto"),
				request.getParameter("privacidad"), new Date());

		publicacionDAO.insert(p);

		return new ModelAndView("redirect:/user");
	}

}
