package com.example.myapplication;

import java.lang.Exception;

/**
 * The <code>IsPlantException</code> represents an exception indicating
 * that the plant cannot eat
 *
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954
 *    Recitation: 02
 **/
public class IsPlantException extends Exception{
    /**
     * Creates a IsPlantException with an error message
     */
    public IsPlantException(){
        super("ERROR: The cursor is at a plant node. Plants cannot be predators");
    }
}
