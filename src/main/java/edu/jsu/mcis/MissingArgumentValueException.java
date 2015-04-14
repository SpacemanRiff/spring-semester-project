package edu.jsu.mcis;

/**An exception that is thrown when a named argument is called, but not given a value.*/
public class MissingArgumentValueException extends RuntimeException {  
    /**
     *  Creates a new exception for when when a named argument is called, but not given a value.
     *
     *  @param message the message to accompany this exception
     */  
    public MissingArgumentValueException(String message){
        super(message);
    }
}