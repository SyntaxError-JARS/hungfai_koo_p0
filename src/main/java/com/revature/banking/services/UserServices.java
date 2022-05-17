package com.revature.banking.services;

import com.revature.banking.daos.UserDao;
import com.revature.banking.exceptions.AuthenticationException;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistanceException;
import com.revature.banking.models.User;
import com.revature.banking.util.logging.Logger;

import java.io.IOException;

public class UserServices implements Serviceable<User>{

    private UserDao userDao;
    private Logger logger = Logger.getLogger();

    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User[] readAll(){
        logger.info("Begin reading Users in our file database.");


        try {
            // TODO: What trainerDao intellisense telling me?
            User[] users = userDao.findAll();
            logger.info("All users have been found here are the results: \n");
//
            return users;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User readById(String id){
        try {
            User user = userDao.findById(id);
            return user;
        } catch (ResourcePersistanceException e){
            logger.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public User update(User updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public boolean validateEmailNotUsed(String email){
        return userDao.checkEmail(email);
    }

    @Override
    public User create(User newUser) {
        logger.info("User trying to be registered: " + newUser);
        if(!validateInput(newUser)){
            throw new InvalidRequestException("User was not validated");
        }
        if(validateEmailNotUsed(newUser.getEmail())){
            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
        }

        User persistedUser = userDao.create(newUser);

        if (persistedUser == null){
            throw new ResourcePersistanceException("User was not persisted to the database upon registration");
        }
        logger.info("User has been persisted: " + newUser);
        return persistedUser;
    }

    @Override
    public boolean validateInput(User newUser) {
        logger.debug("Validating Trainer: " + newUser);
        if(newUser == null) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        if(newUser.getPassword() == null || newUser.getPassword().trim().equals("")) return false;
        if(newUser.getFirst_name() == null || newUser.getFirst_name().trim().equals("")) return false;
        if(newUser.getLast_name() == null || newUser.getLast_name().trim().equals("")) return false;
        return newUser.getAge() != null || !newUser.getAge().trim().equals("");
    }

    public User authenticateUser(String email, String password){

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        User authenticatedUser = userDao.authenticateUser(email, password);

        if (authenticatedUser == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedUser;

    }
}