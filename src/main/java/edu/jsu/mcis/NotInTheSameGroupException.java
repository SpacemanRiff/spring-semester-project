package edu.jsu.mcis;

/**An exception thrown when two arguments are trying to be used, but they are not in the same groups*/
public class NotInTheSameGroupException extends RuntimeException{    
    /**
     *  Creates a new exception for when two arguments are trying to be used, but they are not in the same groups.
     *
     *  @param message the message to accompany this exception
     */
    public NotInTheSameGroupException(String message){
        super(message);
    }
}