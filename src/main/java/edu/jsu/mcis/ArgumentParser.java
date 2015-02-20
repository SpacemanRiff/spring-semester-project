package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser{
    private Map<String, ArgumentInformation> argumentMap;
    private List<String> argumentNames;
    private String programDescription;
    public enum Types {INTEGER, STRING, FLOAT, BOOLEAN};
	
	public ArgumentParser(){
        argumentMap = new HashMap<String, ArgumentInformation>();
        argumentNames = new ArrayList<String>();
        programDescription = "";
    }
	
	public void addArgument(String argName, String argDescription, Types type){
        int position = argumentMap.size();
        argumentMap.put(argName, new ArgumentInformation(argDescription, type));
        argumentNames.add(argName);
        argumentMap.get(argName).setPosition(position);
	}
    
    public int getNumberOfArguments(){
        return argumentMap.size();
    }
    
    public String getArgumentDescription(String p){
        return argumentMap.get(p).getDescription();
    }
    
    public Types getArgumentType(String p){
        return argumentMap.get(p).getType();
    }
    
    public String getArgumentTypeAsString(String p){
        return argumentMap.get(p).getTypeAsString();
    }
	
	public void parse(String[] args) throws IncorrectNumberOfArgumentsException, InvalidArgumentException{
		getHelp(args);
		
		if(args.length < getNumberOfArguments()){
			throw new IncorrectNumberOfArgumentsException("\n\nToo few arguments.\n");
		}

		else if(args.length > getNumberOfArguments()){
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
            for(Map.Entry<String, ArgumentInformation> entry : argumentMap.entrySet()){
                System.out.println(entry.getKey() + "\t" + entry.getValue().getTypeAsString() 
                        + "\t" + entry.getValue().getDescription());
            }
            System.exit(0);
        }
    }
	
    @SuppressWarnings("unchecked") //we should talk to Dr. Garrett about this
	public <T> T getValueOf(String argName) throws UnknownArgumentException{
		if(argumentMap.get(argName) != null){
            return (T)argumentMap.get(argName).getValue();
        }
        else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }

    public void setProgramDescription(String programDescription){
        this.programDescription = programDescription;
    }
    
    public String getProgramDescription(){
        return programDescription;
    }
    
    private class ArgumentInformation{
        String description;
        Types type;
        Object value;
        int position;
        
        private ArgumentInformation(String description, Types type){
            this.description = description;
            this.type = type;
        }
        
        private void setPosition(int i){
            position = i;
        }
        
        private void setValue(String value){
            switch(type){
                case INTEGER:
                    this.value = Integer.parseInt(value);
                    break;
                case FLOAT:
                    this.value = Float.parseFloat(value);
                    break;
                case BOOLEAN:
                    this.value = Boolean.parseBoolean(value);
                    break;
                default:
                    this.value = value;
                    break;
            }
        }
        
        private Object getValue(){
            return value;
        }
        
        private int getPosition(){
            return position;
        }
        
        private String getDescription(){
            return description;
        }
        
        private Types getType(){
            return type;
        }
        
        private String getTypeAsString(){            
            switch(type){
                case INTEGER:
                    return "INTEGER";
                case FLOAT:
                    return "FLOAT";
                case BOOLEAN:
                    return "BOOLEAN";
                default:
                    return "STRING";
            }
        }
    }
}