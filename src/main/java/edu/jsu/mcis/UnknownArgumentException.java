package edu.jsu.mcis;

/**An exception that is thrown when a requested argument can not be found. */
public class UnknownArgumentException extends RuntimeException{  
    private String argumentName;
    
    /**
     *  Creates an exception stating that an argument could not be found.
     *
     *  @param argumentName the argument that could not be found
     */
    public UnknownArgumentException(String argumentName){
        super("\n\nCould not find argument \"" + argumentName + "\"\n");
        this.argumentName = argumentName;
    }
    
    /**
     *  Used to get the name of the argument that could not be found.
     *
     *  @return a string representing the name of the argument that could not be found.
     */
    public String getArgumentName() { return argumentName; }
}