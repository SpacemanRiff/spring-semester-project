package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser{
    private Map<String, Argument> positionalArgumentMap;
    private Map<String, NamedArgument> namedArgumentMap;
    private Map<String, String> argumentGroupValues;
    private List<String> positionalArgumentNames;
    private List<String> namedArgumentNames;
    private List<String> namedArgumentShorthand;
    private String programDescription;
    private int totalRequiredArguments;
    private int numberOfGroups;
    public enum Types {INTEGER, STRING, FLOAT, BOOLEAN};
	
	public ArgumentParser(){
        positionalArgumentMap = new HashMap<String, Argument>();
        namedArgumentMap = new HashMap<String, NamedArgument>();
        argumentGroupValues = new HashMap<String, String>();
        positionalArgumentNames = new ArrayList<String>();
        namedArgumentNames = new ArrayList<String>();
        namedArgumentShorthand = new ArrayList<String>();
        numberOfGroups = 0;
        
        programDescription = "";
    }

    public void setProgramDescription(String programDescription){
        this.programDescription = programDescription;
    }
    
    public String getProgramDescription(){
        return programDescription;
    }
	
    public List<String> getPositionalArgumentNames(){
        return positionalArgumentNames;
    }
    
    public List<String> getNamedArgumentNames(){
        return namedArgumentNames;
    }
    
    public List<String> getNamedArgumentShorthand(){
        return namedArgumentShorthand;
    }
    
    public Map<String, NamedArgument> getNamedArgumentMap(){
        return namedArgumentMap;
    }
    
	public void addPositionalArgument(String argName, String argDescription, Types type){
        positionalArgumentMap.put(argName, new Argument(argDescription, type));
        positionalArgumentNames.add(argName);
	}
    
    public void addNamedArgument(String argName, String argDescription, Types type, Object defaultValue){
        namedArgumentMap.put(argName, new NamedArgument(argDescription, type, defaultValue));
        namedArgumentNames.add(argName);        
    }
    
    public void addNamedArgument(String argName, String shorthand, String argDescription, Types type, Object defaultValue){
        namedArgumentMap.put(argName, new NamedArgument(argDescription, type, defaultValue));
        namedArgumentNames.add(argName);
        namedArgumentShorthand.add(shorthand);
        namedArgumentMap.get(argName).setShorthand();
    }

    public void addRequiredNamedArgument(String argName, String argDescription, Types type, Object defaultValue){
        namedArgumentMap.put(argName, new NamedArgument(argDescription, type, defaultValue));
        namedArgumentMap.get(argName).setRequired();
        namedArgumentNames.add(argName); 
        totalRequiredArguments++;
    }
    
    public void addArgumentToGroup(String argName, String groupName){
		if(namedArgumentMap.get(argName) != null){
            namedArgumentMap.get(argName).setGroupName(groupName);
            argumentGroupValues.put(argName, groupName);
        }else if(positionalArgumentMap.get(argName) != null){
            throw new InvalidArgumentException("\n\nCannot add positional argument to a group\n");      
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }        
    }
    
    public void setRestrictedValues(String argName, Object[] values){
		if(positionalArgumentMap.get(argName) != null){
            positionalArgumentMap.get(argName).setRestrictedValues(values);
        }else if(namedArgumentMap.get(argName) != null){
            namedArgumentMap.get(argName).setRestrictedValues(values);       
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }        
    }
    
    public int getNumberOfArguments(){
        return positionalArgumentMap.size();
    }
    
    public String getArgumentDescription(String argName){
		if(positionalArgumentMap.get(argName) != null){
            return positionalArgumentMap.get(argName).getDescription();
        }else if(namedArgumentMap.get(argName) != null){
            return namedArgumentMap.get(argName).getDescription();        
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    public Types getArgumentType(String argName){
		if(positionalArgumentMap.get(argName) != null){
            return positionalArgumentMap.get(argName).getType();
        }else if(namedArgumentMap.get(argName) != null){
            return namedArgumentMap.get(argName).getType();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    public String getArgumentTypeAsString(String argName){
		if(positionalArgumentMap.get(argName) != null){
            return positionalArgumentMap.get(argName).getTypeAsString();
        }else if(namedArgumentMap.get(argName) != null){
            return namedArgumentMap.get(argName).getTypeAsString();            
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    @SuppressWarnings("unchecked")
	public <T> T getValueOf(String argName){
		if(positionalArgumentMap.get(argName) != null){
            return (T)positionalArgumentMap.get(argName).getValue();
        }else if(namedArgumentMap.get(argName) != null){
            return (T)namedArgumentMap.get(argName).getValue();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getDefaultValueOf(String argName){
        if(namedArgumentMap.get(argName) != null){
            return (T)namedArgumentMap.get(argName).getDefaultValue();
		}else if(positionalArgumentMap.get(argName) != null){
            throw new InvalidArgumentException("\n\n\"" + argName + "\" is not a named argument\n");
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    public int getNumberOfOptionalArguments(){
        return namedArgumentMap.size();
    }	
    
    public String getArgumentGroup(String argName){
		if(namedArgumentMap.get(argName) != null){
            if(namedArgumentMap.get(argName).isInAGroup()){
                return argumentGroupValues.get(argName);
            }else{
                throw new NotInGroupException("\n\n\"" + argName + "\" is not in a group\n");
            }
        }else if(positionalArgumentMap.get(argName) != null){
            throw new InvalidArgumentException("\n\nPositional arguments cannot be in a group\n");      
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }        
    }
	
	public void parse(String[] args){           
		getHelp(args);
        
        List<String> argumentValuesList = new ArrayList<String>();
        
        for(int i = 0; i < args.length; i++){
            argumentValuesList.add(args[i]);
        }
        pullNamedArguments(argumentValuesList);
        
        int numArgs = getNumberOfArguments();
		if(argumentValuesList.size() < numArgs){
			throw new NotEnoughArgumentsException("\n\n\tNot enough arguments." + "\n\tGiven: " + argumentValuesList.size() + " Expected: "
                                                    + numArgs + "\n\tArguments given: " + argumentValuesList.toString() + "\n");
		}else if(argumentValuesList.size() > getNumberOfArguments()){
			throw new TooManyArgumentsException("\n\n\tToo many arguments." + "\n\tGiven: " + argumentValuesList.size() + " Expected: " 
                                                    + numArgs + "\n\tArguments given: " + argumentValuesList.toString() + "\n");
		}        

		for(int i = 0; i < argumentValuesList.size(); i++){
            positionalArgumentMap.get(positionalArgumentNames.get(i)).setValue(argumentValuesList.get(i));          
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
            String compareString = args.get(i);
            if(isNotCharacterLength(compareString)){
                if(isShortArgument(compareString)){
                    String replaceString = renameShortArgument(compareString.substring(1));
                    args.set(i, "--".concat(replaceString));
                }
            }
        }
    }
    
    private String renameShortArgument(String s){
        for(int i = 0; i < namedArgumentShorthand.size(); i++){
            if(s.equals(namedArgumentShorthand.get(i))){
                s = namedArgumentNames.get(i);
            }
        }
        return s;
    }
    
    private void pullNamedArguments(List<String> args){
        setShortArguments(args);
        List<String> usedRequiredArguments = new ArrayList<String>();
        boolean usingGroups = false;
        String usedGroup = "";
        for(int i = 0; i < args.size(); i++){
            if(isNotCharacterLength(args.get(i)) && isLongArgument(args.get(i))){
                String lookUpString = args.get(i).substring(2);
                if(namedArgumentMap.get(lookUpString) != null){
                    if(namedArgumentMap.get(lookUpString).isInAGroup()){
                        if(!usingGroups){
                            usedGroup = argumentGroupValues.get(lookUpString);
                            usingGroups = true;
                        }else{
                            if(!usedGroup.equals(argumentGroupValues.get(lookUpString))){
                                throw new NotInTheSameGroupException("\n\n\"" + lookUpString + "\" is not in the group \"" + usedGroup + "\"\n");
                            }
                        }
                    }
                    if(getArgumentType(lookUpString) != Types.BOOLEAN){
                        try{
                            namedArgumentMap.get(lookUpString).setValue(args.get(i+1));
                            args.remove(i);
                            args.remove(i);
                            i--;
                        }catch(IndexOutOfBoundsException ex){
                            throw new InvalidArgumentException("\n\n" + "\nExpected a value following \"" + args.get(i) + "\"");
                        }
                    }else{
                        namedArgumentMap.get(lookUpString).setValue("true");
                        args.remove(i);
                        i--;                            
                    }
                    if(namedArgumentMap.get(lookUpString).isThisRequired()){
                        usedRequiredArguments.add(lookUpString);
                    }
                }else{
                    throw new UnknownArgumentException("\n\nCould not find optional argument \"" + args.get(i) + "\"\n");                    
                }
            }
        }
        if(usedRequiredArguments.size() != totalRequiredArguments){
            throw new NotEnoughArgumentsException("\n\nDid not use all required arguments.\nExpected " + totalRequiredArguments + ", but only got " + usedRequiredArguments.size());
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
        
        for(int i = 0; i < positionalArgumentNames.size(); i++){
            System.out.print(positionalArgumentNames.get(i).toLowerCase() + "  ");
        }            
        for(int i = 0; i < namedArgumentNames.size(); i++){
            if(getArgumentType(namedArgumentNames.get(i)) != Types.BOOLEAN){
                System.out.print("[--" + namedArgumentNames.get(i).toLowerCase() + " " + namedArgumentMap.get(namedArgumentNames.get(i)).getTypeAsString() + "] ");
            }else{
                System.out.print("[--" + namedArgumentNames.get(i).toLowerCase() + "] ");
                numberOfFlags++;
            }
        }
        
        System.out.println("\n\n***Positional Arguments***");
        System.out.printf("%-15s %-10s %-30s \n", "Name", "Data Type", "Description");   
        System.out.printf("%-15s %-10s %-30s \n", "----", "---- ----", "-----------");           
        for(int i = 0; i < positionalArgumentNames.size(); i++){
            System.out.printf("%-15s %-10s %-30s \n", positionalArgumentNames.get(i), positionalArgumentMap.get(positionalArgumentNames.get(i)).getTypeAsString(), positionalArgumentMap.get(positionalArgumentNames.get(i)).getDescription());
        }
        System.out.println("\n***Named Arguments***");
        System.out.printf("%-15s %-10s %-30s \n", "Name", "Data Type", "Description"); 
        System.out.printf("%-15s %-10s %-30s \n", "----", "---- ----", "-----------"); 
        for(int i = 0; i < namedArgumentNames.size(); i++){
            if(getArgumentType(namedArgumentNames.get(i)) != Types.BOOLEAN){
                System.out.printf("%-15s %-10s %-30s \n", namedArgumentNames.get(i), 
                namedArgumentMap.get(namedArgumentNames.get(i)).getTypeAsString(), 
                namedArgumentMap.get(namedArgumentNames.get(i)).getDescription());
            }
        }
        
        if(numberOfFlags > 0){            
            System.out.println("\n***Named Flags***");
            System.out.printf("%-15s %-30s \n", "Name", "Description"); 
            System.out.printf("%-15s %-30s \n", "----", "-----------"); 
            for(int i = 0; i < namedArgumentNames.size(); i++){
                if(getArgumentType(namedArgumentNames.get(i)) == Types.BOOLEAN){
                    System.out.printf("%-15s %-30s \n", namedArgumentNames.get(i), 
                    namedArgumentMap.get(namedArgumentNames.get(i)).getDescription());
                }
            }
        }
    }	
}