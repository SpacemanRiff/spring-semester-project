package edu.jsu.mcis;

public class UnknownArgumentException extends RuntimeException 
{    
    public UnknownArgumentException(String error)
    {
        super(error);
    }
}