package edu.jsu.mcis;

public class ArgumentAlreadyInGroupException extends RuntimeException{    
    public ArgumentAlreadyInGroupException(String error){
        super(error);
    }
}