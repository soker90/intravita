package com.mensubiqua.intravita.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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


}
