package com.mensubiqua.intravita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        }

        if (request.isUserInRole("ROLE_USER")) {
            return "redirect:/user/index";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/user/index**", method = RequestMethod.GET)
    public ModelAndView homePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Web para usuarios");
        model.addObject("message", "Página para usuarios correintes");
        model.setViewName("user/index");
        return model;

    }

    @RequestMapping(value = "/admin/index**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Página para admins");
        model.addObject("message", "Página para administradores");
        model.setViewName("admin/index");

        return model;

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

}
