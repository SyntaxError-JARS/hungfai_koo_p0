package com.revature.banking.util;

import com.revature.banking.daos.UserDao;
import com.revature.banking.menus.RegisterMenu;
import com.revature.banking.menus.WelcomeMenu;
import com.revature.banking.services.UserServices;
import com.revature.banking.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    private WelcomeMenu welcomeMenu;
    private RegisterMenu registerMenu;
    private final Logger logger;
    // once we add register we will need private final MenuRouter router;

    public AppState() {

        logger = Logger.getLogger(true);

        logger.log("2. Generating instance of AppState.");
        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        UserServices userServices = new UserServices(new UserDao());

        // TODO: Why are we doing all of this!?
        this.welcomeMenu = new WelcomeMenu(terminalReader, userServices, logger);
        this.registerMenu = new RegisterMenu(terminalReader);
    }

    public void startup(){
        try {
            while(isRunning) {
                logger.info("Application successfully started");
                // registerMenu.render();
                welcomeMenu.render(); // comment in and out based on what you want to use
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown(){
        isRunning = false;
    }

}