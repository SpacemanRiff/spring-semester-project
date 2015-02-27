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
	
    public void addOptionalFlag(String argName, String argDescription){
        optionalArgumentNames.add(argName);
        optionalArgumentMap.put(argName, new OptionalArgumentInformation(argDescription, Types.BOOLEAN, false));        
        optionalArgumentMap.get(argName).setFlagStatus(true);
    }
    
    public void addOptionalArgument(String argName, String argDescription, Types type, Object defaultValue){
        optionalArgumentMap.put(argName, new OptionalArgumentInformation(argDescription, type, defaultValue));
        optionalArgumentNames.add(argName);        
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
	
	public void parse(String[] args) throws TooManyArgumentsException, NotEnoughArgumentsException, InvalidArgumentException{           
		getHelp(args);     
        
        List<String> argumentValuesList = new ArrayList<String>();
        
        for(int i = 0; i < args.length; i++){
            argumentValuesList.add(args[i]);
        }
        
        pullOptionalArguments(argumentValuesList);
        
		if(argumentValuesList.size() < getNumberOfArguments()){
			throw new NotEnoughArgumentsException(argumentValuesList.toString());
		}else if(argumentValuesList.size() > getNumberOfArguments()){
			throw new TooManyArgumentsException(argumentValuesList.toString());
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
                        lookUpString = lookUpString;
                    }else if(optionalArgumentMap.get(lookUpString.substring(0,1).toUpperCase() + lookUpString.substring(1)) != null){
                        lookUpString = lookUpString.substring(0,1).toUpperCase() + lookUpString.substring(1);
                    }else{
                        throw new UnknownArgumentException("\n\nCould not find optional argument \"" + args.get(i) + "\"\n");                    
                    }
                    
                    if(!optionalArgumentMap.get(lookUpString).getFlagStatus()){
                        try{
                            optionalArgumentMap.get(lookUpString).setValue(args.get(i+1));
                            args.remove(i);
                            args.remove(i);
                            i--;
                        }catch(IndexOutOfBoundsException ex){
                            throw new InvalidArgumentException("\n\n" + "\nExpected a value following \"" + args.get(i) + "\"");
                        }
                    }else{
                        optionalArgumentMap.get(lookUpString).setValue("true");
                        args.remove(i);
                        i--;                            
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
            printProgramInformation();
            System.exit(0);
        }
    }
    
    public void printProgramInformation(){
        System.out.println("\n" + programDescription + "\n");     
        int numberOfFlags = 0;
        
        for(int i = 0; i < argumentNames.size(); i++){
            System.out.print(argumentNames.get(i).toLowerCase() + "  ");
        }            
        for(int i = 0; i < optionalArgumentNames.size(); i++){
            if(!optionalArgumentMap.get(optionalArgumentNames.get(i)).getFlagStatus()){
                System.out.print("[--" + optionalArgumentNames.get(i).toLowerCase() + " " + optionalArgumentMap.get(optionalArgumentNames.get(i)).getTypeAsString() + "] ");
            }else{
                System.out.print("[--" + optionalArgumentNames.get(i).toLowerCase() + "] ");
                numberOfFlags++;
            }
        }
        
        System.out.println("\n\n***Required Arguments***");
        System.out.printf("%-15s %-10s %-30s \n", "Name", "Data Type", "Description");   
        System.out.printf("%-15s %-10s %-30s \n", "----", "---- ----", "-----------");           
        for(int i = 0; i < argumentNames.size(); i++){
            System.out.printf("%-15s %-10s %-30s \n", argumentNames.get(i), argumentMap.get(argumentNames.get(i)).getTypeAsString(), argumentMap.get(argumentNames.get(i)).getDescription());
        }
        System.out.println("\n***Optional Arguments***");
        System.out.printf("%-15s %-10s %-30s \n", "Name", "Data Type", "Description"); 
        System.out.printf("%-15s %-10s %-30s \n", "----", "---- ----", "-----------"); 
        for(int i = 0; i < optionalArgumentNames.size(); i++){
            if(!optionalArgumentMap.get(optionalArgumentNames.get(i)).getFlagStatus()){
                System.out.printf("%-15s %-10s %-30s \n", optionalArgumentNames.get(i), 
                optionalArgumentMap.get(optionalArgumentNames.get(i)).getTypeAsString(), 
                optionalArgumentMap.get(optionalArgumentNames.get(i)).getDescription());
            }
        }
        
        if(numberOfFlags > 0){            
            System.out.println("\n***Optional Flags***");
            System.out.printf("%-15s %-30s \n", "Name", "Description"); 
            System.out.printf("%-15s %-30s \n", "----", "-----------"); 
            for(int i = 0; i < optionalArgumentNames.size(); i++){
                if(optionalArgumentMap.get(optionalArgumentNames.get(i)).getFlagStatus()){
                    System.out.printf("%-15s %-30s \n", optionalArgumentNames.get(i), 
                    optionalArgumentMap.get(optionalArgumentNames.get(i)).getDescription());
                }
            }
        }
    }	
}