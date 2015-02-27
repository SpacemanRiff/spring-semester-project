package edu.jsu.mcis;

public class NotEnoughArgumentsException extends RuntimeException{
    public NotEnoughArgumentsException(String message){
        super(message);
    }
}