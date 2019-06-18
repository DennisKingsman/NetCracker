package com.example.auth.controller;

import com.example.auth.entity.GrowBox;
import com.example.auth.entity.plants.MyPlants;
import com.example.auth.entity.plants.Plant;
import com.example.auth.entity.sensor.Sensor;
import com.example.auth.entity.user.User;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.services.boxService.GrowBoxService;
import com.example.auth.services.dataBasePlantsService.MyPlantService;
import com.example.auth.services.sensorService.SensorService;
import com.example.auth.services.userPlantService.PlantService;
import com.example.auth.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller    // This means that this class is a Controller
@RequestMapping
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    GrowBoxService growBoxService;

    @Autowired
    SensorService sensorService;

    @Autowired
    PlantService plantService;

    @Autowired
    MyPlantService myPlantService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcomePage(Model model) throws ResourceNotFoundException {

        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "home";
    }


}
