package com.mensubiqua.intravita.controller;


import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

@Controller
public class LoginController {

    @Autowired
    UserDAOImpl userDAO;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {    	
        return "login";
    }

    @RequestMapping(value = "registro", method = RequestMethod.POST)
    public ModelAndView registrar(HttpServletRequest request)  {

    	User user = null;
    	ModelAndView model = new ModelAndView();
        model.setViewName("login");
    	
        if (!request.getParameter("password").equals(request.getParameter("password2"))) 
        	model.addObject("mensaje", "Las contraseñas no coinciden");

        else if (userDAO.find(Funciones.encrypt(request.getParameter("nombre") + "." + request.getParameter("apellido"))) != null) 
        	model.addObject("mensaje", "Este usuario ya existe");

        else {
            user = new User(Funciones.encrypt(request.getParameter("nombre")), Funciones.encrypt(request.getParameter("apellido")),
            		Funciones.encrypt(request.getParameter("email")), Funciones.encrypt_md5(request.getParameter("password")),
            		"ROLE_USER", Funciones.encrypt(request.getParameter("nombre") + "." + request.getParameter("apellido")));
            userDAO.insert(user);
            model.addObject("mensaje", "Usuario creado con exito");
        }

        return model;
    }


    @RequestMapping(value = "borrarCuenta", method = RequestMethod.GET)
    public String deleteAccount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        userDAO.delete(Funciones.encrypt(user.getNickname()));
        session.invalidate();

        return "redirect:/default";
    }

    @RequestMapping(value = "logear", method = RequestMethod.POST)
    public ModelAndView logear(HttpServletRequest request)  {
        ModelAndView model = new ModelAndView();
        User user = userDAO.find(Funciones.encrypt(request.getParameter("username")));
        
        model.setViewName("redirect:/default");
    	
        if (user == null) model.addObject("mensaje2", "Este usuario no existe");

        else if (!Funciones.encrypt_md5(request.getParameter("password")).equals(user.getPassword())) 
        	model.addObject("mensaje2", "Las contraseñas no coinciden");

        else {
            request.getSession().setAttribute("user", user);
        }

        return model;
        
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession sesion) {
    	sesion.invalidate();
        return "redirect:/default";
    }

}