package com.revature.banking.services;

import com.revature.banking.daos.AccountDao;
import com.revature.banking.models.Account;

import java.io.IOException;

public class AccountServices {

    private AccountDao accountDao = new AccountDao();

    public void readAccounts(){
        System.out.println("Begin reading Account in our file database.");
        Account[] accounts;

        try {
            // TODO: What trainerDao intellisense telling me?
            accounts = AccountDao.findAll();
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
             Account account = new Account();

            // TODO: Why is this declared as an Object and not a Trainer??
            Object account1 = new Account("Charles", "Jester", "cj@mail.com", "p", 1111);

            Account iCanNameThisWhatEverTheHeckoIWant = new Account();
            System.out.println(iCanNameThisWhatEverTheHeckoIWant.getEmail());

            System.out.println(" ----------THIS THINGGGGGGGG--------------- ");
            System.out.println(account1.toString());
            System.out.println("-------------------------");
            // the (User) is casting the Object trainer1 in java's Heap Memory to view as a Trainer object instead
            System.out.println(((Account) account1).getEmail());

            // forEach
            for(Object a:accounts ){
                if(a != null) {
                    System.out.println((Account) a); // trainer indicates a single element in the trainers array
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
        accountDao.checkEmail(email);
        return false;
    }

    public boolean registerAccount(Account newAccount){
        System.out.println("Account trying to be registered: " + newAccount);
        if(!validateAccountInput(newAccount)){ // checking if false
            System.out.println("User was not validated");
            // TODO: throw - what's this keyword?
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newAccount.getEmail());

        Account persistedAccount = AccountDao.create(newAccount);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        System.out.println("User has been persisted: " + newAccount);
        return true;
    }

    private boolean validateAccountInput(Account newAccount) {
        System.out.println("Validating Account: " + newAccount);
        if(newAccount == null) return false;
        if(newAccount.getId() == null || newAccount.getId().trim().equals("")) return false;
        if(newAccount.getAccount() == null || newAccount.getAccount().trim().equals("")) return false;
        if(newAccount.getAccount_type() == null || newAccount.getAccount_type().trim().equals("")) return false;
        if(newAccount.getBalance() == null || newAccount.getBalance().trim().equals("")) return false;
        return newAccount.getEmail() != null || !newAccount.getEmail().trim().equals("");
    }
}
