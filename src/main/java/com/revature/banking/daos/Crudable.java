package com.revature.banking.daos;

import java.io.FileNotFoundException;
import java.io.IOException;

// This is another form of abstraction
public interface Crudable<T> {


    // Create
    T create(T newObject);

    // Read
    T[] findAll() throws IOException;
    T findByEmail(String email);

    // Update
    //Update
    T deposit(T updatedObj);
    T withdraw(T updatedObj);

    //Delete
    boolean delete(String id);

}