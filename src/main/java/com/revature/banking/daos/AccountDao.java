package com.revature.banking.daos;

import com.revature.banking.models.Account;
import com.revature.banking.util.logging.Logger;

import java.io.IOException;

public class AccountDao implements Crudable<Account> {

    private Logger logger = Logger.getLogger();

    @Override
    public Account create(Account newObject) {
        return null;
    }

    @Override
    public Account[] findAll() throws IOException {
        return new Account[0];
    }

    @Override
    public Account findById(String id) {
        return null;
    }

    @Override
    public boolean update(Account updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}