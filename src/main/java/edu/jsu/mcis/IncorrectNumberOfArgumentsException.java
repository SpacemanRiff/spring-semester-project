package edu.jsu.mcis;

public class IncorrectNumberOfArgumentsException extends RuntimeException 
{
	public IncorrectNumberOfArgumentsException()
	{
		
	}
	
	public IncorrectNumberOfArgumentsException(String error)
	{
		super (error);
	}
}