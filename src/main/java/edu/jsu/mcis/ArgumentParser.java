package edu.jsu.mcis;

public class ArgumentParser
{
	private String mainInput;
	
	public ArgumentParser(){
        /*thinking we should have the constructor take in an array of strings
        * that are the names of the arguments he wants to take in, which
        * in turn will also tell us the number of integer/float/double valued
        * arguments to take in (this is not including flags like --type sphere or whatever)
        *
        * for instance, {"length","width","height"}
        */
    }
	
	public String getArguments(){
		return mainInput;
	}
	
	public void sendArguments(String arguments){
		String mainInput = arguments;
	}
    
    public static void main(String[] args){
	
    }
    
}