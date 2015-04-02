package edu.jsu.mcis;

public class NotInGroupException extends RuntimeException{
    public NotInGroupException(String message){
        super(message);
    }
}