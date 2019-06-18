package com.example.auth.controller.afterAuth;

import com.example.auth.dao.MyPlantNameExpDAO;
import com.example.auth.entity.*;
import com.example.auth.entity.crunch.DropDownForm;
import com.example.auth.entity.crunch.MyPlantNamesExp;
import com.example.auth.entity.plants.MyPlants;
import com.example.auth.entity.plants.Plant;
import com.example.auth.entity.plants.PlantForm;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.services.boxService.GrowBoxService;
import com.example.auth.services.dataBasePlantsService.MyPlantService;
import com.example.auth.services.userPlantService.PlantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PlantController {

    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);

    private final int DEFAULT_NUM = 1;

    @Autowired
    private PlantService plantService;

    @Autowired
    private GrowBoxService growBoxService;

    @Autowired
    private MyPlantService myPlantService;

    @Autowired
    private MyPlantNameExpDAO myPlantNameExpDAO;

    @RequestMapping(value = "/showPlants/{boxId}", method = RequestMethod.GET)
    public String showPlants(Model model,
                             @PathVariable(name = "boxId") Long id) throws ResourceNotFoundException {

        List<Plant> plants = plantService.findByBoxId(id);

        logger.info("Step One : " + plants.toString());

        List<PlantForm> plantForms = warpInForm(plants);


        model.addAttribute("plants",  plantForms);
        model.addAttribute("boxId", id);

        return "showPlants";
    }

    @RequestMapping(value = "/plantEdit/{boxId}/{id}", method = RequestMethod.GET)
    public String plantEditForm(Model model,
                                    @PathVariable(name = "boxId") Long boxId,
                                    @PathVariable(name = "id") Long id) throws ResourceNotFoundException {

        logger.info("now we observe plant with id : " + id);
        Plant plant = plantService.findById(id);

        Long myPlantId = plant.getMyPlantId();
        logger.info("MyPlatId is : " + myPlantId);

        model.addAttribute("plant", plant);
        model.addAttribute("boxId", boxId);
        model.addAttribute("myPlantId", myPlantId);

        return "plantEdit";
    }

    @RequestMapping(value = "/plantEdit/{boxId}/{myPlantId}", method = RequestMethod.POST)
    public String plantEditForm(Model model, Plant plant,
                                  @PathVariable(name = "boxId") Long boxId,
                                    @PathVariable(name = "myPlantId") Long myPlantId) throws ResourceNotFoundException {

        GrowBox box = growBoxService.findById(boxId);
        plant.setResponsibleGrowBox(box);
        logger.info("MyPlantId still : " + myPlantId);
        plant.setMyPlantId(myPlantId);

        plantService.savePlant(plant);

        List<Plant> plants = plantService.findByBoxId(boxId);

        List<PlantForm> plantForms = warpInForm(plants);

        model.addAttribute("plants", plantForms);
        model.addAttribute("boxId", boxId);
        return "showPlants";
    }

    @RequestMapping(value = "/plantAdd/{boxId}", method = RequestMethod.GET)
    public String plantAdd(Model model,
                           @PathVariable(name = "boxId") Long boxId){

        List<MyPlantNamesExp> listPlantNames = myPlantNameExpDAO.getNames();

        MyPlantNamesExp test = listPlantNames.get(0);
        logger.info("Add method : " + test.toString());

        DropDownForm form = new DropDownForm();

        model.addAttribute("form", form);

        model.addAttribute("plants", listPlantNames);
        model.addAttribute("boxId", boxId);

        return "addPlant";
    }

    @RequestMapping(value = "/plantAdd/{boxId}", method = RequestMethod.POST)
    public String plantAdd(Model model, @Valid @ModelAttribute("form")DropDownForm form,
                           @PathVariable(name = "boxId") Long boxId) throws ResourceNotFoundException {
        logger.info("from user action : " + Long.parseLong(form.getPlantName(), 10));
        Plant plant = new Plant();
        Long id = Long.parseLong(form.getPlantName(), 10);

        MyPlants myPlants = myPlantService.findById(id);
        logger.info(myPlants.getPlantName());

        plant.setResponsibleGrowBox(growBoxService.findById(boxId));
        plant.setMyPlantId(myPlantService.findById(id).getId());
        plant.setNum(DEFAULT_NUM);
        plantService.savePlant(plant);

        model.addAttribute("plants", warpInForm(plantService.findByBoxId(boxId)));
        model.addAttribute("boxId", boxId);

        return "showPlants";
    }

    @RequestMapping(value = "/plantDelete/{boxId}/{id}", method = RequestMethod.GET)
    public String plantDelete(Model model,
                                @PathVariable(name = "boxId") Long boxId,
                                @PathVariable(name = "id") Long id) throws ResourceNotFoundException {

        plantService.deletePlantById(id);

        List<Plant> plants = plantService.findByBoxId(boxId);

        List<PlantForm> plantForms = warpInForm(plants);

        model.addAttribute("plants", plantForms);
        model.addAttribute("boxId", boxId);
        return "showPlants";
    }

    public List<PlantForm> warpInForm(List<Plant> plants) throws ResourceNotFoundException {

        logger.info("Step two (inside warp method) : ");

        logger.info("Get plants : " + plants.toString());
        List<PlantForm> plantForms = new ArrayList<>();

        logger.info("Start warping : ");
        for(Plant p : plants){
            logger.info("plant : " + p.toString());
            MyPlants myPlant = myPlantService.findById(p.getMyPlantId());

            PlantForm plantForm = new PlantForm();
            plantForm.setNum(p.getNum());
            plantForm.setPlantName(myPlant.getPlantName());
            plantForm.setCriticalHigherTemperature(myPlant.getCriticalHigherTemperature());
            plantForm.setCriticalLowerTemperature(myPlant.getCriticalLowerTemperature());
            plantForm.setRecommendedTemperature(myPlant.getRecommendedTemperature());
            plantForm.setId(p.getId());

            plantForms.add(plantForm);
        }

        return plantForms;
    }
}
