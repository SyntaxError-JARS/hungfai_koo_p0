package com.revature.banking.services;

import com.revature.banking.daos.UserDao;
import com.revature.banking.models.User;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UserServicesTestSuite {

    UserServices sut;
    UserDao mockUserDao;

    @BeforeEach
    public void testPrep(){
        // in order to specify and mock a dao
        mockUserDao = mock(UserDao.class);
        sut = new UserServices(mockUserDao);
    }

    @Test
    public void test_validateInput_givenValidTrainer_returnTrue(){

        // Arrange
        User user = new User("valid", "valid", "valid","valid","valid");

        // Act
        boolean actualResult = sut.validateInput(user);

        // Assert
        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsTrainer(){
        // Arrange
        User user = new User("pie", "pie", "pie","pie","pie");
        // THe below code ensures that the services can continue execution and get expected results from the dao without any issues
        when(mockUserDao.create(user)).thenReturn(user);

        // Act
        User actualUser = sut.create(user);

        // Assert
        Assertions.assertEquals("pie", actualUser.getEmail());
        Assertions.assertEquals("pie", actualUser.getPassword());
        Assertions.assertEquals("pie", actualUser.getFirst_name());
        Assertions.assertEquals("pie", actualUser.getLast_name());
        Assertions.assertEquals("pie", actualUser.getAge());
        // Mockito is verifying that the creation method was execute only once!
        verify(mockUserDao, times(1)).create(user);
    }

    @Test
    @Disabled
    public void test3(){

    }

}
