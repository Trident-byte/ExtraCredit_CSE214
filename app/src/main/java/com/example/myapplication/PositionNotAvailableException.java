package com.example.myapplication;

import java.lang.Exception;

/**
 * The <code>PositionNotAvailableException</code> represents an exception indicating
 * that an animal cannot have more predators
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/
public class PositionNotAvailableException extends Exception{
    /**
     * Creates a PositionNotAvailableException with an error message
     */
    public PositionNotAvailableException(){
        super("ERROR: There is no more room for more prey for this predator");
    }
}
