package edu.jsu.mcis;

public class ArgumentParser{
	private String mainInput;
    private String[] argumentNames;
    private int[] parsedValues;
	
	public ArgumentParser(String[] argumentNames){
        this.argumentNames = argumentNames;
        parsedValues = new int[argumentNames.length];
    }
	
	public int getArgumentAt(String requestedValue){
		return 1;
	}
	
	public int getArgumentAt(int requestedPosition){
		return 1;
	}
	
	public void sendArguments(String arguments){
		mainInput = arguments;
	}
    
    public void splitArguments(){
        //needs implementation
    }
}