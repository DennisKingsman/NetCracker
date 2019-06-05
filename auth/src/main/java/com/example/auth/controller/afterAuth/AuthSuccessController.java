package com.example.auth.controller.afterAuth;

import com.example.auth.entity.GrowBox;
import com.example.auth.entity.User;
import com.example.auth.entity.UserForm;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.repo.GrowBoxRepo;
import com.example.auth.services.GrowBoxService;
import com.example.auth.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller    // This means that this class is a Controller
public class AuthSuccessController {

    private static final Logger logger = LoggerFactory.getLogger(AuthSuccessController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private GrowBoxService growBoxService;

    @RequestMapping(value = "/personalAccount", method = RequestMethod.GET)
    public String personalAccount(Model model, Principal principal){

        User user = userService.findUser(principal.getName());
        model.addAttribute("userInfo", user.getUsername());
        model.addAttribute("userId", user.getId());

        List<GrowBox> growBoxes = growBoxService.findByUser(user.getId());
        model.addAttribute("boxes", growBoxes);

        return "personalAccount";
    }

    @RequestMapping(value = {"/boxEdit/{userId}", "/boxEdit/{userId}/{id}"}, method = RequestMethod.GET)
    public String growBoxesEditForm(Model model,
                                    @PathVariable(name = "userId") Long userId,
                                    @PathVariable(required = false, name = "id") Long id) throws ResourceNotFoundException {

        logger.info("now we observe box with id : " + id);

        if(id != null){
            model.addAttribute("box", growBoxService.findById(id));
        }else {
            GrowBox box = new GrowBox();
            box.setResponsibleUser(userService.findById(userId));
            model.addAttribute("box", new GrowBox());
        }

        model.addAttribute("userId", userId);

        return "growBoxEdit";
    }

    @RequestMapping(value = "/boxEdit/{userId}", method = RequestMethod.POST)
    public String growBoxEditForm(Model model, GrowBox growBox,
                                  @PathVariable(name = "userId") Long userId) throws ResourceNotFoundException {

        User user = userService.findById(userId);
        growBox.setResponsibleUser(user);
        growBoxService.saveBox(growBox);

        model.addAttribute("boxes", growBoxService.findByUser(userId));
        model.addAttribute("userInfo", user.getUsername());

        return "personalAccount";
    }

    @RequestMapping(value = "/delete/{userId}/{id}", method = RequestMethod.GET)
    public String growBoxDelete(Model model, Principal principal,
                                @PathVariable(name = "userId") Long userId,
                                @PathVariable(name = "id") Long id){

        growBoxService.deleteBox(id);
        model.addAttribute("boxes", growBoxService.findByUser(userId));
        return "personalAccount";
    }
}
