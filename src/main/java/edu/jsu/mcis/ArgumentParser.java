package edu.jsu.mcis;

public class ArgumentParser
{
	private String mainInput;
	
	public ArgumentParser()
	{
    
    }
	
	public String getArguments()
	{
		return mainInput;
	}
	
	public void sendArguments(String arguments)
	{
		String mainInput = arguments;
	}
    
    public static void main(String[] args)
	{
	
    }
    
}