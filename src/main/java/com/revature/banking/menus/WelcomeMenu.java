package com.revature.banking.menus;
import com.revature.banking.exceptions.AuthenticationException;
import com.revature.banking.models.User;
import com.revature.banking.services.UserServices;
import com.revature.banking.util.logging.Logger;

import java.io.BufferedReader;

import static com.revature.banking.util.AppState.shutdown;

public class WelcomeMenu extends Menu{

    private UserServices userServices;
    private final Logger logger;

    public WelcomeMenu(BufferedReader terminalReader, UserServices userServices, Logger logger) {
        super("Welcome", "/welcome", terminalReader);
        this.userServices = userServices;
        this.logger = logger;
    }

    @Override
    public void render() throws Exception {
        // String is the datatype we are declaring
        // welcome is the variable being declared as a STring
        // the value is being set to Welcome To the banking! in the String pool
        String welcome = "Welcome to the Banking!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        String option3 = "3) View/Create banking";
        String option4 = "4) View all accounts";
        String option5 = new String("5) Exit the banking"); // This is the same as ""

        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4, option5).println();

        System.out.print("\n Select number from above\n >");
        String userSelection = terminalReader.readLine();

        // TODO: What is a switch?
        switch (userSelection) {
            case "1":
                logger.info("User has selected login...");

                System.out.print("Please enter email address: \n>");
                String email = terminalReader.readLine();

                System.out.print("Please enter password: \n>");
                String password = terminalReader.readLine();

                try {
                    User user = userServices.authenticateUser(email, password);
                    System.out.println(user);
                } catch (AuthenticationException e){
                    e.printStackTrace();
                }
                break;
            case "2":
                System.out.println("User has selected register...");
                RegisterMenu registerMenu = new RegisterMenu(terminalReader);
                registerMenu.render();
                // register(); // ctrl + left-click
                break;
            case "3":
                System.out.println("User has selected to find user by ID...");
                System.out.println("Please enter the ID for specific user: ");
                String id = terminalReader.readLine();

                System.out.println(userServices.readById(id));
                break;
            case "4":
                System.out.println("User has selected view banking...");
                User[] users = userServices.readAll();

                for(int i = 0; i < users.length; i++){
                    System.out.println(users[i]);
                }

                break;
            case "5":
                System.out.println("User has selected exit...");
                // shutdown application here
                shutdown();
                logger.info("Application shutting down");
                break;
            default: // why have a default? catch all if input doesn't match any case above.
                System.out.println("No valid user input provide");
                break;
        }
    }
}
