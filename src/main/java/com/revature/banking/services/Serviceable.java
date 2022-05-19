package com.revature.banking.services;

import com.revature.banking.models.User;

public interface Serviceable<T> {

    // Create
    T create(T newObject);

    // Read
    T[] readAll();
    T readByEmail(String email);

    // Update
    T update(T updatedObject);
    T deposit(T updatedObject);
    T withdraw(T updatedObject);
    // Delete
    boolean delete(String id);

    boolean validateInput(T object);

}
