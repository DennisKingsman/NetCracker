package com.example.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller    // This means that this class is a Controller
@RequestMapping
public class MainController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "home";
    }

    @RequestMapping(value = "/personalAccount", method = RequestMethod.GET)
    public String personalAccount(Model model, Principal principal){

        String userInfo = principal.getName();
        model.addAttribute("userInfo", userInfo);

        return "personalAccount";
    }
}
