package edu.jsu.mcis;

/**An exception that is thrown when a value does not match the appropriate type defined by the argument, or if an argument is trying to be used for something that that specific type of argument can not have. */
public class InvalidArgumentException extends RuntimeException {    
    /**
     *  Creates a new exception for when a value does not match the appropriate type defined by the argument.
     *
     *  @param message the message to accompany this exception
     */
    public InvalidArgumentException(String message){
        super(message);
    }
}