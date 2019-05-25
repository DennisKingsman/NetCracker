package com.example.auth.controller;

import com.example.auth.entity.Role;
import com.example.auth.entity.User;
import com.example.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

@Controller
public class RegisterController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        User personForm = new User();
        model.addAttribute("personForm", personForm);

        return "register";
    }

    @RequestMapping(value = {"/register"} , method = RequestMethod.POST)
    public String savePerson(User user, Model model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.addAttribute("message", "User exists");
            return "register";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

}
