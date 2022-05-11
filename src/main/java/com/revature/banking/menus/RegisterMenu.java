package com.revature.banking.menus;

import com.revature.banking.daos.UserDao;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistanceException;
import com.revature.banking.models.User;
import com.revature.banking.util.logging.Logger;

import java.io.BufferedReader;

// Inheritance from menu abstract class :D another pillar of OOP
public class RegisterMenu extends Menu{

    private UserServices userServices = new UserServices(new UserDao());
    private final Logger logger = Logger.getLogger(false);

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



        // What's happening here??
        //
        if (!password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        // Trainer trainer = new Trainer(); // why is this red?? there isn't a No-Arg constructor
        // What's happening here? Intialization a new User object in memory
        User newUser = new User(email, password, first_name, last_name, age);
        System.out.println("Here is the user that was provided by the user: " + newUser);

        // How to fix this emptry
        try{
            userServices.create(newUser); // this is the risky code
        } catch(InvalidRequestException | ResourcePersistanceException e){
            logger.warn(e.getMessage());
        }
    }
}