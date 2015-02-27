package edu.jsu.mcis;

public class TooManyArgumentsException extends RuntimeException{
    public TooManyArgumentsException(String message){
        super(message);
    }
}