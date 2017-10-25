package com.mensubiqua.intravita.controller;


import com.mensubiqua.intravita.auxiliar.Funciones;
import com.mensubiqua.intravita.dao.UserDAOImpl;
import com.mensubiqua.intravita.model.Session;
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
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

@Controller
public class LoginController {

    @Autowired
    UserDAOImpl userDAO;
    
    @Autowired
    private Session sesion;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {    	
        return "login";
    }

    @RequestMapping(value = "registro", method = RequestMethod.POST)
    public ModelAndView registrar(@RequestParam(value = "nombre", required = true) String nombre,
                                  @RequestParam(value = "apellido", required = true) String apellido,
                                  @RequestParam(value = "email", required = true) String email,
                                  @RequestParam(value = "password", required = true) String password,
                                  @RequestParam(value = "password2", required = true) String password2)  {
    	
    	ModelAndView model = new ModelAndView();
        model.setViewName("login");
        String pass_md5 = null;
    	
    	byte[] thedigest = null;
    		try {
    			byte[] bytesOfMessage = password.getBytes("UTF-8");

    			MessageDigest md = MessageDigest.getInstance("MD5");
    			thedigest = md.digest(bytesOfMessage);
    			pass_md5 = DatatypeConverter.printHexBinary(thedigest).toLowerCase();
    		}catch(Exception e)
    		{
    			model.addObject("mensaje", "Error desconocido");
    			return model;
    		}
    	
        User user = new User(Funciones.encrypt(nombre), Funciones.encrypt(apellido), Funciones.encrypt(email), pass_md5, "ROLE_USER", Funciones.encrypt(nombre+"."+apellido));
        if (!password.equalsIgnoreCase(password2) & pass_md5 != null) model.addObject("mensaje", "Las contraseñas no coinciden");

        else if (userDAO.find(user.getNickname()) != null) model.addObject("mensaje", "Este usuario ya existe");

        else {
            userDAO.insert(user);
            model.addObject("mensaje", "Usuario creado con exito");
        }

        return model;
    }


    @RequestMapping(value = "/borrarCuenta")
    public ModelAndView deleteAccount() {
    	ModelAndView model = new ModelAndView();
        if(sesion.getNickname() != null) {
        	System.out.println(sesion.getNickname());
        	userDAO.delete(sesion.getNickname());
        	model.addObject("mensaje2", "Usuario borrado correctamente");
        	model.setViewName("login");
        }else {
        	model.addObject("mensaje", "Error al borrar cuenta");	
        }
        model.addObject("user",sesion.getNickname());
        sesion.setRol(null);
        return model;
    }

    @RequestMapping(value = "logear", method = RequestMethod.POST)
    public ModelAndView logear(@RequestParam String username, @RequestParam String password)  {
        ModelAndView model = new ModelAndView();
        User user = userDAO.find(Funciones.encrypt(username));
        model.setViewName("login");
    	
    	byte[] thedigest = null;
    		try {
    			byte[] bytesOfMessage = password.getBytes("UTF-8");

    			MessageDigest md = MessageDigest.getInstance("MD5");
    			thedigest = md.digest(bytesOfMessage);
    			password = DatatypeConverter.printHexBinary(thedigest).toLowerCase();
    		}catch(Exception e)
    		{
    			model.addObject("mensaje", "Error desconocido");
    			return model;
    		}
    	
    		

        if (user == null) model.addObject("mensaje2", "Este usuario no existe");

        else if (!password.equalsIgnoreCase(user.getPassword())) model.addObject("mensaje2", "Las contraseña es incorrecta");

        else {
        	sesion.setName(user.getNombre());
            sesion.setRol(user.getRol());
            sesion.setApellido(user.getApellido());
            sesion.setEmail(user.getEmail());
            sesion.setFoto(user.getFoto());
            sesion.setNickname(user.getNickname());
            model.addObject("user", user);
            return new ModelAndView("redirect:/default");
        }
        
        return model;
        
    }

}