package edu.jsu.mcis;

public class MissingArgumentValueException extends RuntimeException {    
    public MissingArgumentValueException(String error){
        super(error);
    }
}