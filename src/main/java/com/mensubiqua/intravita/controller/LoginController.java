package com.mensubiqua.intravita.controller;


import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.Session;
import com.mensubiqua.intravita.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserDAOImpl userDAO;
    
    @Autowired
    private Session sesion;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
    	System.out.println(sesion.getName());
        return "login";
    }

    @RequestMapping(value = "registro", method = RequestMethod.POST)
    public ModelAndView registrar(@RequestParam(value = "nombre", required = true) String nombre,
                                  @RequestParam(value = "apellido", required = true) String apellido,
                                  @RequestParam(value = "email", required = true) String email,
                                  @RequestParam(value = "password", required = true) String password,
                                  @RequestParam(value = "password2", required = true) String password2)  {
        User user = new User(nombre, apellido, email, password, "ROLE_USER");
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        if (!password.equalsIgnoreCase(password2)) model.addObject("mensaje", "Las contraseñas no coinciden");

        else if (userDAO.find(user.getNickname()) != null) model.addObject("mensaje", "Este usuario ya existe");

        else {
            userDAO.insert(user);
            model.addObject("mensaje", "Usuario creado con exito");
        }

        return model;
    }


    @RequestMapping(value = "borrarCuenta", method = RequestMethod.GET)
    public ModelAndView deleteAccount(HttpSession session) {
    	ModelAndView model = new ModelAndView();
        User user = (User) session.getAttribute("user");
        model.addObject("user", user);
        if(user != null) {
        	userDAO.delete(user.getNickname());
        	model.addObject("mensaje2", "Usuario borrado con �xito");
        	model.setViewName("login");
        }else {
        	model.addObject("mensaje", "Error al borrar cuenta");	
        }
        return model;
    }

    @RequestMapping(value = "logear", method = RequestMethod.POST)
    public ModelAndView logear(@RequestParam String username, @RequestParam String password)  {
        ModelAndView model = new ModelAndView();
        User user = userDAO.find(username);
        model.setViewName("login");

        if (user == null) model.addObject("mensaje2", "Este usuario no existe");

        else if (!password.equalsIgnoreCase(user.getPassword())) model.addObject("mensaje2", "Las contrase�a es incorrecta");

        else {
        	sesion.setName(user.getNombre());
            sesion.setRol(user.getRol());
            model.addObject("user", user);
            return new ModelAndView("redirect:/default");
        }
        
        return model;
        
    }

}