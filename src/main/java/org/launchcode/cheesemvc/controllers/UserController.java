package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("users", UserData.getAll());
        return "/user/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @Valid @ModelAttribute User user, String verify) {

        if (user.getPassword().equals(verify)) {
            UserData.add(user);
            model.addAttribute("users", UserData.getAll());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("userID", user.getUserID());
            return "/user/index";
        } else {
            String error = "Your passwords don't match.";
            model.addAttribute("error", error);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            return "/user/add";
        }
    }


}
