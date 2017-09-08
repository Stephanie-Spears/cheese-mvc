package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import javax.validation.*;

import java.util.*;


/* -Access Levels (abstraction->hiding all but the relevant object data)-
* public : global
* protected : subclass
* no modifier : package
* private : class
* */


/*Typically Spring MVC apps use @Controller classes which prepare a model map with data and selects a view to be rendered. The model map allows for the complete abstraction (showing only the relevant object data) of the view technology--in Thymeleaf it is transformed into a Thymeleaf context variable, which makes all the defined variables available to expressions executed in templates
Spring MVC calls the pieces of data that can be accessed during the execution of views "model attributes". Thymeleaf calls them "context variables". */

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @RequestMapping(value = "") // Request path: /cheese--because of 'cheese' above
    public String index(Model model) {
        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", CheeseData.getAll()); // pass data object (ArrayList of Cheese class) to view, via CheeseData class method getAll()
        return "cheese/index"; //path to templates directory that is specifically for the cheese controller
    }


    /*controller method calls default Cheese constructor, which gets initialized with an id. Model class method addAttribute is called with the newly instantiated Cheese object argument--which is now accessible as a context variable in Thymeleaf templates */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
//        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    /*
    * @Valid annotation on an object tells the validation framework to process (check) the annotated object
    * @ModelAttribute is an annotation that binds form data with a bean
    * When used as a method argument, it indicates the argument should be retrieved from the model. When not present, it should be first instantiated and then added to the model and once present in the model, the arguments fields should be populated from all request parameters that have matching names.
    * It accepts an optional "value", which indicates the name of the attribute. If no value is supplied to the annotation, then the value would be defaulted to the return type name in case of methods and parameter type name in case of method-parameters.(@ModelAttribute("cheese"))
    *
    * -So here it checks if Cheese class is already in the form, and tries to set it locally into newCheese. If not in the form, it creates a new instance of Cheese class and sets all of the class' matching property names (from class setters and from the form context variables' names).
    * -Since @Valid is used, the validator must ensure that any specifications (@NotNull/@Size) in the Cheese class are met. If the data currently bound in the instance doesn't pass, it will store the errors in the error property, which is then checked in the processAddCheeseForm handler. If it contains errors, they will be accessed by the View and displayed in the add.html, it will return the string 'cheese/add' which essentially just reloads the page(?).
    * -If there are no errors, it calls CheeseData which adds the instance of Cheese to the CheeseData's arrayList, and returns a "redirect:" string, which is handled by the index method, rerouting to root
    * */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }
        CheeseData.add(newCheese);
        return "redirect:";
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
            model.addAttribute("errors", "You can't delete NOTHIN, dummy!");
            return "cheese/remove";
        }

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        return "redirect:";
    }


    @RequestMapping(value = "edit/{cheeseId}", method=RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {

        Cheese cheeseToEdit = CheeseData.getById(cheeseId);

        model.addAttribute("cheese", cheeseToEdit);

        return "cheese/edit";

    }

    @RequestMapping(value = "edit/{cheeseId}", method=RequestMethod.POST)
    public String processEditForm(@RequestParam int cheeseId, @RequestParam String name, @RequestParam String description) {

        Cheese cheeseToEdit = CheeseData.getById(cheeseId);
        cheeseToEdit.setName(name);
        cheeseToEdit.setDescription(description);

        return "redirect:";

    }

}



