package be.thomasmore.repairaza.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping({"/","home","/index"})
    public String home(Model model, Principal principal){
        final String loginName = (principal != null)? principal.getName():"Nobody";
        logger.info("home - logged in: "+loginName);
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
