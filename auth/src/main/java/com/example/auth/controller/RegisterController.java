package com.example.auth.controller;

import com.example.auth.entity.user.Role;
import com.example.auth.entity.user.User;
import com.example.auth.entity.user.UserForm;
import com.example.auth.services.userService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        UserForm personForm = new UserForm();
        model.addAttribute("personForm", personForm);

        return "register";
    }

    @RequestMapping(value = {"/register"} , method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("personForm") UserForm personForm, Model model) {

        if(!personForm.getPassword().equals(personForm.getDoublePassword())){
            logger.info("first pass :" + personForm.getPassword());
            logger.info("second pass :" + personForm.getDoublePassword());
            model.addAttribute("passwordIncorrect", "Ur password is incorrect");
            return "register";
        }

        logger.info(personForm.getName());

        User userFromDb = userService
                .findSameUser(personForm.getName(), personForm.getEmail());

        if(userFromDb != null){
            model.addAttribute("message", "User exists");
            return "register";
        }

        User user = new User();

        user.setUsername(personForm.getName());
        user.setPassword(personForm.getPassword());
        user.setEmail(personForm.getEmail());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.saveUser(user);

        return "redirect:/login";
    }

}
