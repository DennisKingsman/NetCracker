package com.example.auth.controller.afterAuth;


import com.example.auth.entity.crunch.BorderTemperature;
import com.example.auth.entity.plants.MyPlants;
import com.example.auth.entity.plants.Plant;
import com.example.auth.entity.sensor.Sensor;
import com.example.auth.entity.sensor.SensorNames;
import com.example.auth.entity.user.User;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.services.boxService.GrowBoxService;
import com.example.auth.services.dataBasePlantsService.MyPlantService;
import com.example.auth.services.sensorService.SensorService;
import com.example.auth.services.userPlantService.PlantService;
import com.example.auth.services.userService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SensorController {

    @Autowired
    private UserService userService;

    @Autowired
    private GrowBoxService growBoxService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private MyPlantService myPlantService;

    @Autowired
    public JavaMailSender emailSender;

    private static final Logger logger = LoggerFactory.getLogger(SensorController.class);

    @RequestMapping(value = "/showSensors/{boxId}", method = RequestMethod.GET)
    public String showPlants(Model model, Principal principal,
                             @PathVariable(name = "boxId") Long id) throws ResourceNotFoundException {
        List<Plant> plants = plantService.findByBoxId(id);
        List<Sensor> sensors = sensorService.findByBoxId(id);
        if(sensors != null) {
            sensors = refreshValues(sensors);

            if(plants != null) {
                BorderTemperature borderTemperature = setBorderTemperature(plants);
                String email = userService.findUser(principal.getName()).getEmail();
                logger.info("EMAIL : " + email);
                tryToSendEmail(sensors, borderTemperature, email, id);
                model.addAttribute("plantData", borderTemperature);
            }
            model.addAttribute("sensors", sensors);
        }

        model.addAttribute("boxId", id);

        return "showSensors";
    }

    @RequestMapping(value = "/addSensor/{boxId}", method = RequestMethod.GET)
    public String addSensor(Model model,
                            @PathVariable(name = "boxId") Long id){
        String[] values = new String[SensorNames.values().length];
        int i = 0;
        for (SensorNames s: SensorNames.values()) {
            logger.info("TO STRING : "+ s.toString());
            values[i] = s.toString();
        }

        model.addAttribute("names", values);
        model.addAttribute("boxId", id);
        return "addSensorAttribute";
    }

    @RequestMapping(value = "/addSensorAttribute/{boxId}", method = RequestMethod.POST)
    public String addSensor(Model model,
                            @RequestParam("sensorName") String sensorName,
                            @PathVariable(name = "boxId") Long id) throws ResourceNotFoundException {

        Sensor sensor = new Sensor();
        logger.info("ATTENTION sensor: " + sensorName);
        sensor.setSensorName(sensorName);
        sensor.setActiveGrowBox(growBoxService.findById(id));
        sensor.setValue(getRandomValue());
        sensorService.saveSansor(sensor);



//        model.addAttribute("sensors", sensorService.findByBoxId(id));
//        model.addAttribute("boxId", id);
        return "redirect:/showSensors/" + id;
    }

    @RequestMapping(value = "/deleteSensor/{boxId}/{id}", method = RequestMethod.GET)
    public String plantDelete(Model model,
                              @PathVariable(name = "boxId") Long boxId,
                              @PathVariable(name = "id") Long id) throws ResourceNotFoundException {

        sensorService.deleteSensor(id);

//        model.addAttribute("sensors", sensorService.findByBoxId(boxId));
//        model.addAttribute("boxId", boxId);
        return "redirect:/showSensors/" + id;
    }

    private int getRandomValue(){
        int min = 5;
        int max = 40;
        return min + (int) (Math.random() * max);
    }

    private BorderTemperature setBorderTemperature(List<Plant> plants) throws ResourceNotFoundException {

        BorderTemperature borderTemperature = new BorderTemperature();
        MyPlants defaultPlant = myPlantService.findById(plants.get(0).getMyPlantId());
        borderTemperature.setMinTemperature(defaultPlant.getCriticalLowerTemperature());
        borderTemperature.setMaxTemperature(defaultPlant.getCriticalHigherTemperature());

        for (Plant p: plants) {
            MyPlants myPlant = myPlantService.findById(p.getMyPlantId());
            if(borderTemperature.getMaxTemperature() > myPlant.getCriticalHigherTemperature()){
                borderTemperature.setMaxTemperature(myPlant.getCriticalHigherTemperature());
            }

            if(borderTemperature.getMinTemperature() < myPlant.getCriticalLowerTemperature()){
                borderTemperature.setMinTemperature(myPlant.getCriticalLowerTemperature());
            }
        }

        logger.info("AFTER SETTING BORDERS : " +
                borderTemperature.getMinTemperature() + " , " +
                borderTemperature.getMaxTemperature());
        return borderTemperature;
    }

    private List<Sensor> refreshValues(List<Sensor> sensors){

        for (Sensor s: sensors) {
            s.setValue(getRandomValue());
        }
        return sensors;
    }

    private void tryToSendEmail(List<Sensor> sensors,
                                   BorderTemperature borderTemperature,
                                   String email,
                                   Long boxId){


        for (Sensor s: sensors) {
            logger.info("VALUES : " + borderTemperature.getMinTemperature() +
                    " : " + s.getValue() +
                    " : " + borderTemperature.getMaxTemperature());
            boolean temp = s.getValue() < borderTemperature.getMinTemperature();
            logger.info("IF < min : " +  temp );
            temp = s.getValue() > borderTemperature.getMaxTemperature();
            logger.info("IF > max : " + temp);
            if (s.getValue() < borderTemperature.getMinTemperature() ||
                    s.getValue() > borderTemperature.getMaxTemperature()){
                logger.info("WE ARE IN");
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(email);
                message.setSubject("Attention");
                message.setText("In your box № " + boxId + " is incorrect temperature.");
                emailSender.send(message);
            }
        }
    }
}
