package edu.jsu.mcis;

public class NotAllArgumentsUsedException extends RuntimeException {    
    public NotAllArgumentsUsedException(String error){
        super(error);
    }
}