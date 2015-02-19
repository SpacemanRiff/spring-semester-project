package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser{
    private Map<String, ArgumentInformation> argumentMap;
    private ArrayList<ArgumentInformation> argumentNames;
    private ArrayList<Object> argumentValues;
    private String programDescription;
    public enum Types {INTEGER, STRING, FLOAT, BOOLEAN};
	
	public ArgumentParser(){
        argumentMap = new HashMap<String, ArgumentInformation>();
		argumentNames = new ArrayList<ArgumentInformation>();
        argumentValues = new ArrayList<Object>();
        programDescription = "";
    }
	
	public void addArgument(String argName, String argDescription, Types type){
        int position = argumentMap.size();
        argumentMap.put(argName, new ArgumentInformation(argDescription, type));
        argumentMap.get(argName).setPosition(position);
	}
    
    public int getNumberOfArguments(){
        return argumentMap.size();
    }
	
	/*public String getArgumentName(String p){
		return argumentMap.getKey(p);
	}*/
    
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
		int i = 0;
		for(Map.Entry<String, ArgumentInformation> entry : argumentMap.entrySet()){
            try{
                switch(entry.getValue().getType()){
                    case INTEGER:
                        entry.getValue().setValue(Integer.parseInt(args[entry.getValue().getPosition()]));
                        break;
                    case FLOAT:
                        entry.getValue().setValue(Float.parseFloat(args[entry.getValue().getPosition()]));
                        break;
                    case BOOLEAN:
                        entry.getValue().setValue(Boolean.parseBoolean(args[entry.getValue().getPosition()]));
                        break;
                    default:
                        entry.getValue().setValue(args[entry.getValue().getPosition()]);
                        break;
                }
                i++;
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
            for(int i = 0; i < argumentNames.size(); i++){
                System.out.println(argumentNames.get(i).getName() 
                        + "\t" + argumentNames.get(i).getTypeAsString() 
                        + "\t" + argumentNames.get(i).getDescription());
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
 /*   
    public static void main(String[] args){
		ArgumentParser p = new ArgumentParser();
        
        p.setProgramDescription("Calculates the volume of a box.");
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
		
		p.parse(args);
		
		int l = p.getValueOf("Length");
		int w = p.getValueOf("Width");
		int h = p.getValueOf("Height");

        System.out.println(p.getArgumentName(0) + " is " + l);
		System.out.println(p.getArgumentName(1) + " is " + w);
		System.out.println(p.getArgumentName(2) + " is " + h);	
		System.out.println("The volume of this shape is " + (l * w * h));		
    }
*/    
    private class ArgumentInformation{
        String name, description;
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
        
        private void setValue(Object value){
            this.value = value;
        }
        
        private Object getValue(){
            return value;
        }
        
        private int getPosition(){
            return position;
        }
        
        private String getName(){
            return name;
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