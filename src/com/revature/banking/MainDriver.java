package com.revature.banking;

// importing the class from other packages to be leveraged
import com.revature.banking.models.users;
import com.revature.banking.models.newUser;
import com.revature.banking.util.AppState;

// importing everything from java.io.
// this is a library that's provided by....JRE
import java.io.*;

// What's this?
// Creating a class called MainDriver
public class MainDriver {

    public static void main(String[] args){

        System.out.println("1. AppState instantiated");
        AppState appState = new AppState();

        System.out.println("Banking Application starting up....");
        appState.startup();

    }
}
