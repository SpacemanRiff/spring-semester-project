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
	
    public List<String> getArgumentNames(){
        return argumentNames;
    }
    
    public List<String> getOptionalArgumentNames(){
        return optionalArgumentNames;
    }
    
	public void addArgument(String argName, String argDescription, Types type){
        argumentMap.put(argName, new ArgumentInformation(argDescription, type));
        argumentNames.add(argName);
	}
    
    public void addOptionalArgument(String argName, String argDescription, Types type, Object defaultValue){
        optionalArgumentMap.put(argName, new OptionalArgumentInformation(argDescription, type, defaultValue));
        optionalArgumentNames.add(argName);        
    }
    
    public int getNumberOfArguments(){
        return argumentMap.size();
    }
    
    public String getArgumentDescription(String argName){
		if(argumentMap.get(argName) != null){
            return argumentMap.get(argName).getDescription();
        }else if(optionalArgumentMap.get(argName) != null){
            return optionalArgumentMap.get(argName).getDescription();        
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    public Types getArgumentType(String argName){
		if(argumentMap.get(argName) != null){
            return argumentMap.get(argName).getType();
        }else if(optionalArgumentMap.get(argName) != null){
            return optionalArgumentMap.get(argName).getType();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    public String getArgumentTypeAsString(String argName){
		if(argumentMap.get(argName) != null){
            return argumentMap.get(argName).getTypeAsString();
        }else if(optionalArgumentMap.get(argName) != null){
            return optionalArgumentMap.get(argName).getTypeAsString();            
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    @SuppressWarnings("unchecked")
	public <T> T getValueOf(String argName){
		if(argumentMap.get(argName) != null){
            return (T)argumentMap.get(argName).getValue();
        }else if(optionalArgumentMap.get(argName) != null){
            return (T)optionalArgumentMap.get(argName).getValue();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getDefaultValueOf(String argName){
        if(optionalArgumentMap.get(argName) != null){
            return (T)optionalArgumentMap.get(argName).getDefaultValue();
		}else if(argumentMap.get(argName) != null){
            throw new InvalidArgumentException("\n\n\"" + argName + "\" is not a named argument\n");
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    public int getNumberOfOptionalArguments(){
        return optionalArgumentMap.size();
    }	
	
	public void parse(String[] args){           
		getHelp(args);
        
        List<String> argumentValuesList = new ArrayList<String>();
        
        for(int i = 0; i < args.length; i++){
            argumentValuesList.add(args[i]);
        }
        pullOptionalArguments(argumentValuesList);
        
        int numArgs = getNumberOfArguments();
		if(argumentValuesList.size() < numArgs){
			throw new NotEnoughArgumentsException("\n\n\tNot enough arguments." + "\n\tGiven: " + argumentValuesList.size() + " Expected: "
                                                    + numArgs + "\n\tArguments given: " + argumentValuesList.toString() + "\n");
		}else if(argumentValuesList.size() > getNumberOfArguments()){
			throw new TooManyArgumentsException("\n\n\tToo many arguments." + "\n\tGiven: " + argumentValuesList.size() + " Expected: " 
                                                    + numArgs + "\n\tArguments given: " + argumentValuesList.toString() + "\n");
		}        

		for(int i = 0; i < argumentValuesList.size(); i++){
            argumentMap.get(argumentNames.get(i)).setValue(argumentValuesList.get(i));          
		}
	}
    
    private boolean isNotCharacterLength(String s){
        if(s.length() > 1)
            return true;
        else
            return false;
    }
    
    private boolean isShortArgument(String s){
        if(s.substring(0,1).equals("-") && !s.substring(1,2).equals("-"))
            return true;
        else
            return false;
    }
    
    private boolean isLongArgument(String s){
        if(s.substring(0,2).equals("--"))
            return true;
        else
            return false;
    }
    
    private void setShortArguments(List<String> args){
        for(int i = 0; i < args.size(); i++){
            if(isNotCharacterLength(args.get(i))){
                if(isShortArgument(args.get(i))){
                    String replaceString = renameShortArgument(args.get(i).substring(1));
                    args.set(i, "--".concat(replaceString));
                }
            }
        }
    }
    
    private String renameShortArgument(String s){
        for(int i = 0; i < optionalArgumentNames.size(); i++){
            if(s.equals(optionalArgumentNames.get(i).substring(0,1))){
                s = optionalArgumentNames.get(i);
            }
            else
                s = s;
        }
        return s;
    }
    
    private void pullOptionalArguments(List<String> args){
        setShortArguments(args);
        for(int i = 0; i < args.size(); i++){
            if(isNotCharacterLength(args.get(i))){
                if(isLongArgument(args.get(i))){
                    String lookUpString = args.get(i).substring(2);
                    if(optionalArgumentMap.get(lookUpString) != null){                    
                        if(getArgumentType(lookUpString) != Types.BOOLEAN){
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
            if(getArgumentType(optionalArgumentNames.get(i)) != Types.BOOLEAN){
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
            if(getArgumentType(optionalArgumentNames.get(i)) != Types.BOOLEAN){
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
                if(getArgumentType(optionalArgumentNames.get(i)) == Types.BOOLEAN){
                    System.out.printf("%-15s %-30s \n", optionalArgumentNames.get(i), 
                    optionalArgumentMap.get(optionalArgumentNames.get(i)).getDescription());
                }
            }
        }
    }	
}