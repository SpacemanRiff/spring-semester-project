package edu.jsu.mcis;

public class NotInTheSameGroupException extends RuntimeException{
    public NotInTheSameGroupException(String message){
        super(message);
    }
}