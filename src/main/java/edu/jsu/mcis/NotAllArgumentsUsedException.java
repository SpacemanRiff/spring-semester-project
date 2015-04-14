package edu.jsu.mcis;

/**An exception that is thrown when not all required arguments are being used. */
public class NotAllArgumentsUsedException extends RuntimeException {    
    /**
     *  Creates a new exception for when not all required arguments are being used.
     *
     *  @param message the message to accompany this exception
     */
    public NotAllArgumentsUsedException(String message){
        super(message);
    }
}