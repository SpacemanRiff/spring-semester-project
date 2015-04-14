package edu.jsu.mcis;

/**An exception thrown when not enough arguments are being used. */
public class NotEnoughArgumentsException extends RuntimeException{
    /**
     *  Creates a new exception for when not enough arguments are being used.
     *
     *  @param message the message to accompany this exception
     */
    public NotEnoughArgumentsException(String message){
        super(message);
    }
}