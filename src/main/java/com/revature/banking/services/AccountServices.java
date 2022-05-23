package com.revature.banking.services;

import com.revature.banking.daos.AccountDao;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.Account;
import com.revature.banking.models.User;
import com.revature.banking.util.logging.Logger;

public class AccountServices implements Serviceable<Account> {
    private final AccountDao accountDao;

    private Logger logger = Logger.getLogger();

    public AccountServices(AccountDao accountDao){
        this.accountDao = accountDao;

    }


    @Override
    public Account create(Account newAccount) {
        logger.info("User trying to be registered: " + newAccount);
        if(!validateInput(newAccount)){
            throw new InvalidRequestException("Account was not validated");
        }
        if(validateEmailNotUsed(newAccount.getEmail())){
            throw new InvalidRequestException("User id is already in use. Please try again with another email or login into previous made account.");
        }

        Account persistedAccount = accountDao.create(newAccount);

        logger.info("Account has been persisted: " + newAccount);
        return persistedAccount;
    }

    private boolean validateEmailNotUsed(String email) {
        return false;
    }

    @Override
    public Account[] readAll() {
        return new Account[0];
    }
    public Account readByEmail(String email) {
        return null;
    }

    public Account readById(String id) {
        return null;
    }

    @Override
    public Account update(Account updatedAccount) {
        logger.info("User trying to update: " + updatedAccount);
        if(!validateInput(updatedAccount)){
            throw new InvalidRequestException("Account was not validated");
        }
        if(validateEmailNotUsed(updatedAccount.getEmail())){
            throw new InvalidRequestException("User id is already in use. Please try again with another email or login into previous made account.");
        }

        Account persistedAccount = accountDao.create(updatedAccount);

        logger.info("Account has been persisted: " + updatedAccount);
        return updatedAccount;
    }

    public Account withdraw(Account createUpdate) {


        return accountDao.withdraw(createUpdate);
    }

    @Override
    public Account deposit(Account newUpdate) {
        logger.info("User trying to update: " + newUpdate);
        return null;
    }


    public boolean delete(String id) {
        return false;
    }


    public boolean validateInput(Account object) {
        return true;
    }
}