package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Cheese {
/*a 'bean' is essentially a class for encapsulation of many objects into a single object(the bean), so they can be lended as a single bean object instead of several individual objects. Conventions dictate a JavaBean is a Java Object that is -serializable (conversion of object to bytes, so the object can be easily saved to persistent storage or streamed across a communication link--which can then be reversed to replicate the original object), -has a null(no args) constructor, -allow access to properties via getter and setter methods. Serialization takes the "value" of the object, not the class definition, so things like methods aren't passed.*/

/*field-level constraint--used on a field of a class. Validation engine directly accesses the instance variable and does not invoke the accessor method(getters).
* property-level constraint -- same as field-level, but works on the class' getter properties. To validate it is recommended to choose only one annotation type per class, as calling both the field-level and property-level (on the getter method), whould result in validating the same field twice.
* class-level constraint -- on the class itself, entire object is evaluated (against custom validator?) -- does not support Bean Validation API constraints, such as
* @AssertFalse ->checks if boolean/Boolean annotated element is false,
* @Valid -> data type: any non-primitive type -> works recursively on any non-primitive type,
* @Pattern(regex=,flag=) -> data type: CharSequence, Collection, Map, arrays -> checks if annotated string matches the regex considering the given flag 'match'
* @Future or @Past -> data type: java.util.Date, java.util.Calendar -> checks if annotated date is in the future/past
* */
    @NotNull
    @Size(min=3, max=25)
    @Pattern(regexp = "[a-zA-Z]*", message="Alphabet Only")
    private String name;


    @NotNull
    @Size(min=1, message = "Description must not be empty")
    @Pattern(regexp = "[a-zA-Z0-9_.,!? ]*", message="Alphanumeric only")
    private String description;


    private int cheeseId;
    private static int nextId = 1;


    public Cheese(String name, String description) {
//        this(); //this calls the no argument constructor for the class, given below ...allows for empty input, aside from cheeseId? ...wha
        this();
        this.name = name;
        this.description = description;
    }


    /*Default Class constructor--gets called by controller method displayAddCheeseForm*/
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
///*need to implement in class validation*/
//    public boolean isAlphaNumeric(String s){
//        String pattern= "^[a-zA-Z0-9]*$";
//        return s.matches(pattern);
//    }

}