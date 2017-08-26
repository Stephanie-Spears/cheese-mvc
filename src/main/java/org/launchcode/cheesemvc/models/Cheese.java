package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;


    private int cheeseId;
    private static int nextId = 1;


    public Cheese(String name, String description) {
//        this(); //this calls the no argument constructor for the class, given below ...allows for empty input, aside from cheeseId? ...wha
        this();
        this.name = name;
        this.description = description;
    }

    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
/*need to implement in class validation*/
    public boolean isAlphaNumeric(String s){
        String pattern= "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }

}