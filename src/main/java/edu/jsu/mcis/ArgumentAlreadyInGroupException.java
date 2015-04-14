package edu.jsu.mcis;

/**An exception that is thrown when the user tries to add an argument to a group, but that argument is already in a group. */
public class ArgumentAlreadyInGroupException extends RuntimeException{    
    /**
     *  Creates a new exception for when the user tries to add an argument to a group, but that argument is already in a group.
     *
     *  @param message the message to accompany this exception
     */
    public ArgumentAlreadyInGroupException(String message){
        super(message);
    }
}