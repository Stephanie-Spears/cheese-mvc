package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {

    static ArrayList<User> users = new ArrayList<>();

    // getAll

    public static ArrayList<User> getAll() {
        return users;
    }

    // add

    public static void add(User newUser) {
        users.add(newUser);
    }

    // getByID

    public static User getByID(int id) {

        User theUser = null;

        for(User candidateUser : users) {
            if(candidateUser.getUserID() == id) {
                theUser = candidateUser;
            }
        }

        return theUser;
    }
}