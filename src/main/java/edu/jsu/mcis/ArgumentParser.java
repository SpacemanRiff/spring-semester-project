package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser{
    private Map<String, ArgumentInformation> argumentMap;
    private Map<String, OptionalArgumentInformation> optionalArgumentMap;
    private List<String> argumentNames;
    private List<String> optionalArgumentNames;
    private String programDescription;
    public enum Types {INTEGER, STRING, FLOAT, BOOLEAN};
	
	public ArgumentParser(){
        argumentMap = new HashMap<String, ArgumentInformation>();
        optionalArgumentMap = new HashMap<String, OptionalArgumentInformation>();
        argumentNames = new ArrayList<String>();
        optionalArgumentNames = new ArrayList<String>();
        programDescription = "";
    }

    public void setProgramDescription(String programDescription){
        this.programDescription = programDescription;
    }
    
    public String getProgramDescription(){
        return programDescription;
    }
	
	public void addArgument(String argName, String argDescription, Types type){
        argumentMap.put(argName, new ArgumentInformation(argDescription, type));
        argumentNames.add(argName);
	}    
    public int getNumberOfArguments(){
        return argumentMap.size();
    }    
    public String getArgumentDescription(String argName) throws UnknownArgumentException{
		if(argumentMap.get(argName) != null){
            return argumentMap.get(argName).getDescription();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }    
    public Types getArgumentType(String argName) throws UnknownArgumentException{
		if(argumentMap.get(argName) != null){
            return argumentMap.get(argName).getType();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }    
    public String getArgumentTypeAsString(String argName) throws UnknownArgumentException{
		if(argumentMap.get(argName) != null){
            return argumentMap.get(argName).getTypeAsString();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }	
    @SuppressWarnings("unchecked")
	public <T> T getValueOf(String argName) throws UnknownArgumentException{
		if(argumentMap.get(argName) != null){
            return (T)argumentMap.get(argName).getValue();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    public void addOptionalArgument(String argName, String argDescription, Types type, Object defaultValue){
        optionalArgumentMap.put(argName, new OptionalArgumentInformation(argDescription, type, defaultValue));
        optionalArgumentNames.add(argName);        
    }    
    public int getNumberOfOptionalArguments(){
        return optionalArgumentMap.size();
    }    
    public String getOptionalArgumentDescription(String argName) throws UnknownArgumentException{
		if(optionalArgumentMap.get(argName) != null){
            return optionalArgumentMap.get(argName).getDescription();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }    
    public Types getOptionalArgumentType(String argName) throws UnknownArgumentException{
		if(optionalArgumentMap.get(argName) != null){
            return optionalArgumentMap.get(argName).getType();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }    
    public String getOptionalArgumentTypeAsString(String argName) throws UnknownArgumentException{
		if(optionalArgumentMap.get(argName) != null){
            return optionalArgumentMap.get(argName).getTypeAsString();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }	
    @SuppressWarnings("unchecked")
	public <T> T getOptionalArgumentValueOf(String argName) throws UnknownArgumentException{
		if(optionalArgumentMap.get(argName) != null){
            return (T)optionalArgumentMap.get(argName).getValue();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
	
	public void parse(String[] args) throws IncorrectNumberOfArgumentsException, InvalidArgumentException{
		getHelp(args);
		
		if(args.length < getNumberOfArguments()){
			throw new IncorrectNumberOfArgumentsException("\n\nToo few arguments.\n");
		}else if(args.length > getNumberOfArguments()){
			throw new IncorrectNumberOfArgumentsException("\n\nToo many arguments.\n");
		}        

		for(int i = 0; i < args.length; i++){
            try{
                argumentMap.get(argumentNames.get(i)).setValue(args[i]); 
            }catch(IllegalArgumentException ex){
                throw new InvalidArgumentException("\n\nInvalid argument \"" + args[i] + "\"\n");
            }            
		}
	}
 
    private void getHelp(String[] args){
        boolean isHelpNeeded = false;
        
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-h")){
                isHelpNeeded = true;
            }
        }
        
        if(isHelpNeeded){
            System.out.println("\n" + programDescription);
            for(int i = 0; i < args.length; i++){
                System.out.println(argumentNames.get(i)
                    + "\t" + argumentMap.get(argumentNames.get(i)).getTypeAsString() 
                    + "\t" + argumentMap.get(argumentNames.get(i)).getDescription());
            }
            System.exit(0);
        }
    }
}