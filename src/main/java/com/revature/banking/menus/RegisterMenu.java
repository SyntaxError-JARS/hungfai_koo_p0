package com.revature.banking.menus;

import com.revature.banking.models.Account;
import com.revature.banking.services.AccountServices;

import java.io.BufferedReader;

// Inheritance from menu abstract class :D another pillar of OOP
public class RegisterMenu extends Menu{

    private Account Account = new Account();

    public RegisterMenu(BufferedReader terminalReader) {
        super("Register", "/register", terminalReader);
    }

    // Polymorphism  Another pillar of OOP for the same thing doing different things
    // This is overriding a method
    @Override
    public void render() throws Exception {
        // TODO: Implement me!!!
        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        System.out.println("What is your password?");
        String password = terminalReader.readLine();

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("What is your first name?");
        String first_name = terminalReader.readLine();

        System.out.println("What is your last name?");
        String last_name = terminalReader.readLine();

        System.out.println("Age?");
        String age = terminalReader.readLine();

        // What's happening here???
        // Breaking or splitting the String fullName into an String array by " " spaces
        //String[] nameArray = fullName.split(" ");
        //String fname = nameArray[0];
        //String lname = nameArray[1];

        // What's happening here??
        //
        if (!password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new User object in memory
        Account Account = new Account(email, password, first_name, last_name, age);
        System.out.println("Here is the trainer that was provided by the user: " + Account);
        AccountServices.registerNewUser(Account);
    }
}