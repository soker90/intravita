package com.mensubiqua.intravita.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {


    //TODO temporal, si se entra a user/ o admin/ tendra que redirigir al index
    @RequestMapping(value = {"/user**", "/user/index**" }, method = RequestMethod.GET)
    public ModelAndView homePage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Web para usuarios");
        model.addObject("message", "Página para usuarios correintes");
        model.setViewName("user/index");
        return model;

    }

    @RequestMapping(value = {"/admin**", "/admin/index**"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Página para admins");
        model.addObject("message", "Página para administradores");
        model.setViewName("admin/index");

        return model;

    }

}
