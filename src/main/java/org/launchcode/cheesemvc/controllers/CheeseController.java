package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
@RequestMapping("cheese")
public class CheeseController
{
    //make arraylist available within the whole class (sort of like global)
    static ArrayList<String> cheeses = new ArrayList<>();

    // Request path: /cheese--because of 'cheese' above
    @RequestMapping(value ="")
    public String index(Model model){
        // pass data object, arraylist, to view
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheese");
        return "cheese/index"; //path to templates directory that is specifically for the cheese controller
    }

    // controller method to display a form
    @RequestMapping(value = "add", method= RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    //handler to process the form
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName){ //@requestparam tells spring to look for a requets parameter with the same name as the method parameter and insert it into this method call. Needs to match the html variable for spring to be able to find it
        cheeses.add(cheeseName);

        // redirect to /cheese --which was specified as root beforehand, so leaving this empty sends us to root
        return "redirect:";
    }

}
