package com.example.auth.controller.afterAuth;

import com.example.auth.entity.GrowBox;
import com.example.auth.entity.User;
import com.example.auth.repo.GrowBoxRepo;
import com.example.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping
public class AuthSuccessController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    GrowBoxRepo growBoxRepo;

    @RequestMapping(value = "/personalAccount", method = RequestMethod.GET)
    public String personalAccount(Model model, Principal principal){

        User user = userRepo.findByUsername(principal.getName());

        model.addAttribute("userInfo", user.getUsername());

        List<GrowBox> growBoxes = growBoxRepo.findByResponsibleUserId(user.getId());

        model.addAttribute("growboxes", growBoxes);

        return "personalAccount";
    }

    @RequestMapping(value = "/showForm", method = RequestMethod.GET)
    public String showFormForAdd(Model theModel) {
        GrowBox growBox = new GrowBox();
        theModel.addAttribute("growBox", growBox);
        return "growBoxForm";
    }

    @RequestMapping(value = "/saveGrowBox", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("growBox") GrowBox growBox, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());

        growBox.setResponsibleUser(user);

        growBoxRepo.save(growBox);

        return "redirect:/personalAccount";
    }

    @RequestMapping(value = "/updateForm", method = RequestMethod.GET)
    public String showFormForUpdate(@RequestParam("growBoxId") Long theId,
                                    Model theModel) {
        GrowBox growBox = growBoxRepo.getOne(theId);
        theModel.addAttribute("growBox", growBox);
        return "growBoxForm";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam("growBoxId") Long theId) {
        growBoxRepo.deleteById(theId);
        return "redirect:/personalAccount";
    }
}
