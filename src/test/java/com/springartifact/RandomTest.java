package com.springartifact;

import com.springartifact.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class RandomTest {
    @Test
    void testIsOnePlusOneIsTwo(){
        int i = 1+1;    //Arrange + Act

        //Assert -> Check against the expected value
//        if(i ==2){          // we donot have to do this if we are using Junit
//            //Success
//        }else{
//            //Fail
//        }
        assert i==2;
//        assert 2==3; In one test case we can have multiple assert statements, and the TC will pass iff all the assert statements will pass, even if one assert statement fails, asserts fails, TC fails
        assertEquals(2, i);//expected vs actual value
        assertEquals(7, i, "1 + 1 should be equal to 2");

        assertTrue();
        assertThrows(ProductNotFoundException.class, fun(10)); //check whether a particular function fun() with parameter fun(10) is throwing an exception or not
        assertNull(); // whether particular object in the output is coming null or not
        assertTimeout(); //whether a function is getting completed in 2 sec or not. syntax: Duration: 2 secs, Executable: lambda expressions
    }

}


/*
Test case is nothing but a method that tests a particular functionality across the expected value
3A ->

Arrange
Act
Assert

In some of the TC, we might need to check if the function is throwing exception or not
Scenario: If you want to test the time taken  by a particular function to get executed.
For this we have a library assertJ library
 */