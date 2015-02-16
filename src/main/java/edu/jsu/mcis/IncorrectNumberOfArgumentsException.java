package edu.jsu.mcis;

public class IncorrectNumberOfArgumentsException extends RuntimeException{	
	public IncorrectNumberOfArgumentsException(String error){
		super(error);
	}
}