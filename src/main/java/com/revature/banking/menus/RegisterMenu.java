package com.revature.banking.menus;

import com.revature.banking.models.Account;
import com.revature.banking.services.AccountServices;

import java.io.BufferedReader;

// Inheritance from menu abstract class :D another pillar of OOP
public class RegisterMenu extends Menu{

    private AccountServices accountServices = new AccountServices();

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
        int age = terminalReader.readLine();



        // What's happening here??
        //
        if (!password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new User object in memory
        Account newAccount = new Account(id, account, account_type, balance, email);
        System.out.println("Here is the account that was provided by the user: " + newAccount);
        accountServices.registerAccount(newAccount);
    }
}