package com.revature.banking.services;

import com.revature.banking.daos.NewUserDao;
import com.revature.banking.models.Account;

import java.io.IOException;

public class AccountServices {

    private NewUserDao newUserDao = new NewUserDao();

    public void readTrainers(){
        System.out.println("Begin reading Trainers in our file database.");
        user[] newUsers;

        try {
            // TODO: What trainerDao intellisense telling me?
            Account = NewUserDao.findAll();
            System.out.println("All newUsers have been found here are the results: \n");
//            for (int i = 0; i < trainers.length; i++) {
//                Trainer trainer = trainers[i];
//                if(trainer != null) {
//                    System.out.println(trainer);
//                }
//            }

            // first time declaring variable you must defined data type (primitive or non-primitive)
            // trainer is now declared as a reference variables for an instance of a Trainer class
            // new keyword allows for the construction (or more technically the instantiation of a Trainer class with a No-Arg Construtor)
            // new Trainer() is instantiating a new trainer object via the No-Args Constructor
             Account = new NewUser();

            // TODO: Why is this declared as an Object and not a Trainer??
            Object user1 = new User("Charles", "Jester", "cj@mail.com", "p", "1111");

            NewUser iCanNameThisWhatEverTheHeckoIWant = new Account();
            System.out.println(iCanNameThisWhatEverTheHeckoIWant.getEmail());

            System.out.println(" ----------THIS THINGGGGGGGG--------------- ");
            System.out.println(user1.toString());
            System.out.println("-------------------------");
            // the (User) is casting the Object trainer1 in java's Heap Memory to view as a Trainer object instead
            System.out.println(((User) User1).getEmail());

            // forEach
            for(Object t:Users ){
                if(t != null) {
                    System.out.println((Account) t); // trainer indicates a single element in the trainers array
                }
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }



    // TODO: Implement me to check that the email is not already in our database.
    // public this allows the use of this method anywhere there is a TrainerServices object or within the class itself
    // boolean - it's a true or false value in java and it's specifying the return type
    // validateEmailNotUse() this is a method what we want to call to the DAO to check if the email is already in use
    // String email is the defiend parameters for arguments required when invoking this method
    public boolean validateEmailNotUsed(String email){
        newUserDao.checkEmail(email);
        return false;
    }

    public boolean registerUser(User newUser){
        System.out.println("Trainer trying to be registered: " + newUser);
        if(!validateUserInput(newUser)){ // checking if false
            System.out.println("User was not validated");
            // TODO: throw - what's this keyword?
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newUser.getEmail());

        User persistedUser = NewUserDao.create(newUser);

        if(persistedUser == null){
            throw new RuntimeException();
        }
        System.out.println("User has been persisted: " + newUser);
        return true;
    }

    private boolean validateUserInput(User newUser) {
        System.out.println("Validating User: " + newUser);
        if(newUser == null) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        if(newUser.getPassword() == null || newUser.getPassword().trim().equals("")) return false;
        if(newUser.getFirst_Name() == null || newUser.getFirst_Name().trim().equals("")) return false;
        if(newUser.getLast_Name() == null || newUser.getLast_Name().trim().equals("")) return false;
        return newUser.getAge() != null || !newUser.getAge().trim().equals("");
    }
}
