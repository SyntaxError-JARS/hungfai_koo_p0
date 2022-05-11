package com.revature.banking.services;

import com.revature.banking.daos.AccountDao;
import com.revature.banking.models.Account;
import com.revature.banking.util.logging.Logger;

public class AccountServices implements Serviceable<Account> {
    private final AccountDao AccountDao;

    private Logger logger = Logger.getLogger();

    public AccountServices(AccountDao accountDao){
        this.accountDao = accountDao;


    }

    @Override
    public Account create(Account newObject) {
        return null;
    }

    @Override
    public Account[] readAll() {
        return new Account[0];
    }

    @Override
    public Account readById(String id) {
        return null;
    }

    @Override
    public Account update(Account updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Account object) {
        return false;
    }
}