package com.example.auth.controller.afterAuth;

import com.example.auth.entity.GrowBox;
import com.example.auth.entity.Plant;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.services.GrowBoxService;
import com.example.auth.services.PlantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PlantController {

    @Autowired
    PlantService plantService;

    @Autowired
    GrowBoxService growBoxService;

    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);

    @RequestMapping(value = "/showPlants/{boxId}", method = RequestMethod.GET)
    public String showPlants(Model model,
                             @PathVariable(name = "boxId") Long id){
        model.addAttribute("plants",  plantService.findByBoxId(id));
        model.addAttribute("boxId", id);

        return "showPlants";
    }

    @RequestMapping(value = {"/plantEdit/{boxId}", "/plantEdit/{boxId}/{id}"}, method = RequestMethod.GET)
    public String growBoxesEditForm(Model model,
                                    @PathVariable(name = "boxId") Long boxId,
                                    @PathVariable(required = false, name = "id") Long id) throws ResourceNotFoundException {

        logger.info("now we observe plant with id : " + id);

        if(id != null){
            model.addAttribute("plant", plantService.findById(id));
        }else {
            model.addAttribute("plant", new Plant());
        }

        model.addAttribute("boxId", boxId);

        return "plantEdit";
    }

    @RequestMapping(value = "/plantEdit/{boxId}", method = RequestMethod.POST)
    public String growBoxEditForm(Model model, Plant plant,
                                  @PathVariable(name = "boxId") Long boxId) throws ResourceNotFoundException {

        GrowBox box = growBoxService.findById(boxId);
        plant.setResponsibleGrowBox(box);

        plantService.savePlant(plant);

        model.addAttribute("plants", plantService.findByBoxId(boxId));
        return "showPlants";
    }

    @RequestMapping(value = "/plantDelete/{boxId}/{id}", method = RequestMethod.GET)
    public String growBoxDelete(Model model,
                                @PathVariable(name = "boxId") Long boxId,
                                @PathVariable(name = "id") Long id){

        plantService.deletePlantById(id);
        model.addAttribute("plants", plantService.findByBoxId(boxId));
        return "showPlants";
    }
}
