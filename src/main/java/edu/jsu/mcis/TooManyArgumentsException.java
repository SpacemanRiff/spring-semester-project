package edu.jsu.mcis;

/**An exception that is thrown when too many arguments are being used. */
public class TooManyArgumentsException extends RuntimeException{
    /**
     *  Creates a new exception for when too many arguments are being used.
     *
     *  @param message the message to accompany this exception
     */
    public TooManyArgumentsException(String message){
        super(message);
    }
}