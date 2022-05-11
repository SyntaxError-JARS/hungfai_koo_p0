package com.revature.banking.services;

import com.revature.banking.models.User;

public interface Serviceable<T> {

    // Create
    T create(T newObject);

    // Read
    T[] readAll();
    T readById(String id);

    // Update
    T update(T updatedObject);

    // Delete
    boolean delete(String id);

    boolean validateInput(T object);

}
