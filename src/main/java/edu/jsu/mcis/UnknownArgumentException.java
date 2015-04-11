package edu.jsu.mcis;

public class UnknownArgumentException extends RuntimeException{  
    private String argumentName;
    public UnknownArgumentException(String argumentName){
        super("\n\nCould not find argument \"" + argumentName + "\"\n");
        this.argumentName = argumentName;
    }
    public String getArgumentName() { return argumentName; }
}