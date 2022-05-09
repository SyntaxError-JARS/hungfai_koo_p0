package com.revature.banking.menus;
import com.revature.banking.services.AccountServices;

import java.io.BufferedReader;

import static com.revature.banking.util.AppState.shutdown;

public class WelcomeMenu extends Menu{

    private AccountServices accountServices;

    public WelcomeMenu(BufferedReader terminalReader, AccountServices accountServices) {
        super("Welcome", "/welcome", terminalReader);
        this.accountServices = accountServices;
    }

    @Override
    public void render() throws Exception {
        // String is the datatype we are declaring
        // welcome is the variable being declared as a STring
        // the value is being set to Welcome To the Pokedex! in the String pool
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
                System.out.println("User has selected login...");
                break;
            case "2":
                System.out.println("User has selected register...");
                RegisterMenu registerMenu = new RegisterMenu(terminalReader);
                registerMenu.render();
                // register(); // ctrl + left-click
                break;
            case "3":
                System.out.println("User has selected view/create banking...");
                // usersInput(); // ctrl + left-click
                break;
            case "4":
                System.out.println("User has selected view banking...");
                accountServices.readAccounts();
                break;
            case "5":
                System.out.println("User has selected exit...");
                // shutdown application here
                shutdown();
                break;
            default: // why have a default? catch all if input doesn't match any case above.
                System.out.println("No valid user input provide");
                break;
        }
    }
}
