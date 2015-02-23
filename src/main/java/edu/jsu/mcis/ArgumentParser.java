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
            throw new UnknownArgumentException("\n\nCould not find optional argument \"" + argName + "\"\n");
        }
    }    
    public Types getOptionalArgumentType(String argName) throws UnknownArgumentException{
		if(optionalArgumentMap.get(argName) != null){
            return optionalArgumentMap.get(argName).getType();
        }else{
            throw new UnknownArgumentException("\n\nCould not find optional argument \"" + argName + "\"\n");
        }
    }    
    public String getOptionalArgumentTypeAsString(String argName) throws UnknownArgumentException{
		if(optionalArgumentMap.get(argName) != null){
            return optionalArgumentMap.get(argName).getTypeAsString();
        }else{
            throw new UnknownArgumentException("\n\nCould not find optional argument \"" + argName + "\"\n");
        }
    }	
    @SuppressWarnings("unchecked")
	public <T> T getOptionalArgumentValueOf(String argName) throws UnknownArgumentException{
		if(optionalArgumentMap.get(argName) != null){
            return (T)optionalArgumentMap.get(argName).getValue();
        }else{
            throw new UnknownArgumentException("\n\nCould not find optional argument \"" + argName + "\"\n");
        }
    }
	
	public void parse(String[] args) throws IncorrectNumberOfArgumentsException, InvalidArgumentException{           
		getHelp(args);     
        
        List<String> argumentValuesList = new ArrayList<String>();
        
        for(int i = 0; i < args.length; i++){
            argumentValuesList.add(args[i]);
        }
        
        pullOptionalArguments(argumentValuesList);
		
		if(argumentValuesList.size() < getNumberOfArguments()){
			//throw new IncorrectNumberOfArgumentsException("\n\nToo few arguments.\n");
			throw new IncorrectNumberOfArgumentsException(argumentValuesList.toString());
		}else if(argumentValuesList.size() > getNumberOfArguments()){
			//throw new IncorrectNumberOfArgumentsException("\n\nToo many arguments.\n");
			throw new IncorrectNumberOfArgumentsException(argumentValuesList.toString());
		}        

		for(int i = 0; i < argumentValuesList.size(); i++){
            argumentMap.get(argumentNames.get(i)).setValue(argumentValuesList.get(i));          
		}
	}
    
    public void pullOptionalArguments(List<String> args){
        for(int i = 0; i < args.size(); i++){
            if(args.get(i).length() > 1){
                if(args.get(i).substring(0,2).equals("--")){
                    String lookUpString = args.get(i).substring(2);
                    if(optionalArgumentMap.get(lookUpString) != null){
                        try{
                            optionalArgumentMap.get(lookUpString).setValue(args.get(i+1));
                            args.remove(i);
                            args.remove(i);
                            i--;
                        }catch(IndexOutOfBoundsException ex){
                            throw new InvalidArgumentException("\n\n" + "\nExpected a value following \"" + args.get(i) + "\"");
                        }
                    }else if(optionalArgumentMap.get(lookUpString.substring(0,1).toUpperCase() + lookUpString.substring(1)) != null){
                        try{
                            optionalArgumentMap.get(lookUpString.substring(0,1).toUpperCase() + lookUpString.substring(1)).setValue(args.get(i+1));
                            args.remove(i);
                            args.remove(i);
                            i--;
                        }catch(IndexOutOfBoundsException ex){
                            throw new InvalidArgumentException("\n\n" + "\nExpected a value following \"" + args.get(i) + "\"");
                        }
                    }else{
                        throw new UnknownArgumentException("\n\nCould not find optional argument \"" + args.get(i) + "\"\n");                    
                    }
                }
            }
        }
    }
 
    private void getHelp(String[] args){
        boolean isHelpNeeded = false;
        
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-h") || args[i].equals("--help")){
                isHelpNeeded = true;
            }
        }
        
        if(isHelpNeeded){
            System.out.println("\n" + programDescription);
            for(int i = 0; i < argumentNames.size(); i++){
                System.out.println(argumentNames.get(i)
                    + "\t" + argumentMap.get(argumentNames.get(i)).getTypeAsString() 
                    + "\t" + argumentMap.get(argumentNames.get(i)).getDescription());
            }
            for(int i = 0; i < optionalArgumentNames.size(); i++){
                System.out.println("--" + optionalArgumentNames.get(i)
                    + "\t" + optionalArgumentMap.get(optionalArgumentNames.get(i)).getTypeAsString() 
                    + "\t" + optionalArgumentMap.get(optionalArgumentNames.get(i)).getDescription());
            }
            System.exit(0);
        }
    }
}