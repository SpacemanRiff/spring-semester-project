package edu.jsu.mcis;

/**An exception thrown when the user tries to get the name of a group an argument is in, but that argument is not in a group. */
public class NotInGroupException extends RuntimeException{
    /**
     *  Creates a new exception for when the user tries to get the name of a group an argument is in, but that argument is not in a group.
     *
     *  @param message the message to accompany this exception
     */
    public NotInGroupException(String message){
        super(message);
    }
}