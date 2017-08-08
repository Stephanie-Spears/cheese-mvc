package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("cheese")
public class CheeseController {

// Request path: /cheese--because of 'cheese' above
    @RequestMapping(value ="")
    public String index(Model model){

        model.addAttribute("title", "My Cheese");
        return "cheese/index"; //path to templates directory that is specifically for the cheese controller
    }

}
