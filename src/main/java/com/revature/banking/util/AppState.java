package com.revature.banking.util;

import com.revature.banking.menus.RegisterMenu;
import com.revature.banking.menus.WelcomeMenu;
import com.revature.banking.services.NewUserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    private WelcomeMenu welcomeMenu;
    private RegisterMenu registerMenu;
    // once we add register we will need private final MenuRouter router;

    public AppState() {
        System.out.println("2. Generating instance of AppState.");
        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        NewUserService = new NewUserService();

        // TODO: Why are we doing all of this!?
        this.welcomeMenu = new WelcomeMenu(terminalReader, trainerServices);
        this.registerMenu = new RegisterMenu(terminalReader);
    }

    public void startup(){
        try {
            while(isRunning) {
                System.out.println("Application successfully started");
                // registerMenu.render();
                welcomeMenu.render(); // comment in and out based on what you want to use
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown(){
        isRunning = false;
        System.out.println("Application shutting down...");
    }

}