package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @RequestMapping(value = "") // Request path: /cheese--because of 'cheese' above
    public String index(Model model) {
        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", CheeseData.getAll()); // pass data object (ArrayList of Cheese class) to view, via CheeseData class method getAll()
        return "cheese/index"; //path to templates directory that is specifically for the cheese controller
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
//        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }
        CheeseData.add(newCheese);
        return "redirect:";//leaving this empty redirects to root, which was specified as /cheese previously
    }

    // controller method to display form
    @RequestMapping(value = "remove", method= RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model){
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }


    //handler to process the form
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam(required=false) int[] cheeseIds, Model model) {
        if (cheeseIds == null){
            model.addAttribute("title", "Remove Cheese");
            model.addAttribute("cheeses", CheeseData.getAll());
            model.addAttribute("errors", "You can't delete nothin' FOO!");
            return "cheese/remove";
        }

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        return "redirect:";

    }

}



