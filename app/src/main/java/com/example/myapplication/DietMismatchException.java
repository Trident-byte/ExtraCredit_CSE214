package com.example.myapplication;

import java.lang.Exception;

/**
 * The <code>DietMismatchException</code> represents an exception indicating
 * that the animal cannot eat the prey given
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/
public class DietMismatchException extends Exception{
    /**
     * Creates a DietMismatchException with an error message
     */
    public DietMismatchException(){
        super("ERROR: This prey cannot be added as it does not match the diet of the predator");
    }
}
