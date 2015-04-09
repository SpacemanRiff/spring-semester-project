package edu.jsu.mcis;

import java.util.*;

/** Used to parse command line arguments into usable, storable values. */
public class ArgumentParser{
    private Map<String, Argument> positionalArgumentMap;
    private Map<String, NamedArgument> namedArgumentMap;
    private Map<String, String> argumentGroupValues;
    private List<String> positionalArgumentNames;
    private List<String> namedArgumentNames;
    private Map<String, String> namedArgumentShorthand;
    private String programDescription;
    private int totalRequiredArguments;
    private int numberOfGroups;
    /**
     *  The types that can be used by arguments.
     */
    public enum Types {
        /**
         *  Used to specify an integer value
         */
        INTEGER, 
        /**
         *  Used to specify a string value
         */
        STRING, 
        /**
         *  Used to specify a float value
         */
        FLOAT, 
        /**
         *  Used to specify a boolean value
         */
        BOOLEAN
    };
	
    /**
     *  Creates an Argument Parser object, used to parse command line arguments into usable values.
     */
	public ArgumentParser(){
        positionalArgumentMap = new HashMap<String, Argument>();
        namedArgumentMap = new HashMap<String, NamedArgument>();
        argumentGroupValues = new HashMap<String, String>();
        positionalArgumentNames = new ArrayList<String>();
        namedArgumentNames = new ArrayList<String>();
        namedArgumentShorthand = new HashMap<String, String>();
        numberOfGroups = 0;
        
        programDescription = "";
    }
    
    /**
     *  Sets the program description to a user specified value.
     *
     *  @param programDescription a string containing the program description
     */
    public void setProgramDescription(String programDescription){
        this.programDescription = programDescription;
    }
    
    /**
     *  Returns the program description specified by the user. 
     *  If The description has not been specified, this method returns an empty string.
     *
     *  @return the program description, or a blank string if the description has not be specified                
     */
    public String getProgramDescription(){
        return programDescription;
    }
	
    /**
     *  Returns a list of strings containing all of the positional argument names that have been specified.
     *
     *  @return a list of strings with all positional argument names
     */
    public List<String> getPositionalArgumentNames(){
        return positionalArgumentNames;
    }
	
    /**
     *  Returns a list of strings containing all of the named argument names that have been specified.
     *
     *  @return a list of strings with all named argument names
     */    
    public List<String> getNamedArgumentNames(){
        return namedArgumentNames;
    }
	
    /**
     *  Returns a map of strings to Argument objects mapping argument names to their respective Argument object.
     *
     *  @return a map that pairs argument names to argument objects
     */     
    public Map<String, Argument> getPositionalArgumentMap(){
        return positionalArgumentMap;
    }
	
    /**
     *  Returns a map of strings to NamedArgument objects mapping argument names to their respective NamedArgument object.
     *
     *  @return a map that pairs argument names to NamedArgument objects
     */ 
    public Map<String, NamedArgument> getNamedArgumentMap(){
        return namedArgumentMap;
    }
	
    /**
     *  Returns a map of strings to strings containing all of the shorthand 
     *  arguments paired with their full named counterparts that have been specified.
     *
     *  @return a map that pairs shorthand names to the respective full name
     */ 
    public Map<String, String> getNamedArgumentShorthand(){
        return namedArgumentShorthand;
    }
	
    /**
     *  Returns the number of restricted values assigned to a certain argument.
     *  
     *  @param argName a string representing the name of the argument to be looked up
     *  @return an integer representing the number of restricted values that the requested variable has
     *  @throws InvalidArgumentException if the argument requested doesn't have restricted arguments
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */     
    public int getNumberOfRestrictedArguments(String argName){
        if(namedArgumentMap.get(argName) != null){
            if(namedArgumentMap.get(argName).containsRestrictedValues()){
                return namedArgumentMap.get(argName).numOfRestrictedValues();
            }else{
                throw new InvalidArgumentException("\n\n" + argName + " does not have restricted values.\n");
            }
        }else if(positionalArgumentMap.get(argName) != null){
            if(positionalArgumentMap.get(argName).containsRestrictedValues()){
                return positionalArgumentMap.get(argName).numOfRestrictedValues();
            }else{
                throw new InvalidArgumentException("\n\n" + argName + " does not have restricted values.\n");
            }
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }       
    }
	
    /**
     *  Returns the value of the requested restricted argument.
     *  
     *  @param argName a string representing the name of the argument to be looked up
     *  @param index an integer representing which specific restricted value that is wanted
     *  @return an object representing the value of the requested restricted value
     *  @throws InvalidArgumentException if the argument requested doesn't have restricted arguments
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */    
    public Object getRestrictedValue(String argName, int index){
        Object returnObj;
        if(namedArgumentMap.get(argName) != null){
            if(namedArgumentMap.get(argName).containsRestrictedValues()){
                returnObj = namedArgumentMap.get(argName).getRestrictedObject(index);
            }else{
                throw new InvalidArgumentException("\n\n" + argName + " does not have restricted values.\n");
            }
        }else if(positionalArgumentMap.get(argName) != null){
            if(positionalArgumentMap.get(argName).containsRestrictedValues()){
                returnObj = positionalArgumentMap.get(argName).getRestrictedObject(index);
            }else{
                throw new InvalidArgumentException("\n\n" + argName + " does not have restricted values.\n");
            }
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
        return returnObj;
    }
	
    /**
     *  Returns all of the restricted values for the specified argument.
     *  
     *  @param argName a string representing the name of the argument to be looked up
     *  @return an array containing all of the restricted value for the requested argument
     *  @throws InvalidArgumentException if the argument requested doesn't have restricted arguments
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */    
    public Object[] getAllRestrictedValues(String argName){
        Object[] objArr;
        if(namedArgumentMap.get(argName) != null){
            if(namedArgumentMap.get(argName).containsRestrictedValues()){
                objArr = new Object[namedArgumentMap.get(argName).numOfRestrictedValues()];
                objArr = namedArgumentMap.get(argName).getRestrictedValues();
            }else{
                throw new InvalidArgumentException("\n\n" + argName + " does not have restricted values.\n");
            }
        }else if(positionalArgumentMap.get(argName) != null){
            if(positionalArgumentMap.get(argName).containsRestrictedValues()){
                objArr = new Object[positionalArgumentMap.get(argName).numOfRestrictedValues()];
                objArr = positionalArgumentMap.get(argName).getRestrictedValues();
            }else{
                throw new InvalidArgumentException("\n\n" + argName + " does not have restricted values.\n");
            }
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
        return objArr;
    }
    
    /**
     *  Adds a positional argument with the specified description, and type to this ArgumentParser
     *
     *  @param argName a string representing the desired name for the argument
     *  @param argDescription a string representing the desired description for the argument
     *  @param type a value from the Types enum contained in ArgumentParser to define the object type for the argument
     */
	public void addPositionalArgument(String argName, String argDescription, Types type){
        positionalArgumentMap.put(argName, new Argument(argDescription, type));
        positionalArgumentNames.add(argName);
	}
    
    /**
     *  Adds a named argument with the specified description, type, and default value to this ArgumentParser
     *
     *  @param argName a string representing the desired name for the argument
     *  @param argDescription a string representing the desired description for the argument
     *  @param type a value from the Types enum contained in ArgumentParser to define the object type for the argument
     *  @param defaultValue a value to be set as the default value for the argument
     */
    public void addNamedArgument(String argName, String argDescription, Types type, Object defaultValue){
        namedArgumentMap.put(argName, new NamedArgument(argDescription, type, defaultValue));
        namedArgumentNames.add(argName);        
    }
    
    /**
     *  Adds a named argument with the specified description, type, and default value to this ArgumentParser that has a shorthand value.
     *
     *  @param argName a string representing the desired name for the argument
     *  @param shorthand a string representing the desired shorthand name for the argument
     *  @param argDescription a string representing the desired description for the argument
     *  @param type a value from the Types enum contained in ArgumentParser to define the object type for the argument
     *  @param defaultValue a value to be set as the default value for the argument
     */
    public void addNamedArgument(String argName, String shorthand, String argDescription, Types type, Object defaultValue){
        namedArgumentMap.put(argName, new NamedArgument(argDescription, type, defaultValue));
        namedArgumentNames.add(argName);
        namedArgumentShorthand.put(argName, shorthand);
        namedArgumentMap.get(argName).setShorthand();
    }
    
    /**
     *  Adds a named argument that is required to be used by the user with the specified description, type, and default value to this ArgumentParser
     *
     *  @param argName a string representing the desired name for the argument
     *  @param argDescription a string representing the desired description for the argument
     *  @param type a value from the Types enum contained in ArgumentParser to define the object type for the argument
     *  @param defaultValue a value to be set as the default value for the argument
     */
    public void addRequiredNamedArgument(String argName, String argDescription, Types type, Object defaultValue){
        namedArgumentMap.put(argName, new NamedArgument(argDescription, type, defaultValue));
        namedArgumentMap.get(argName).setRequired();
        namedArgumentNames.add(argName); 
        totalRequiredArguments++;
    }
    
    /**
     *  Adds a named argument that is required to be used by the user with the specified description, type, and default value to this ArgumentParser
     *
     *  @param argName a string representing the desired name for the argument
     *  @param shorthand a string representing the desired shorthand name for the argument
     *  @param argDescription a string representing the desired description for the argument
     *  @param type a value from the Types enum contained in ArgumentParser to define the object type for the argument
     *  @param defaultValue a value to be set as the default value for the argument
     */    
    public void addRequiredNamedArgument(String argName, String shorthand, String argDescription, Types type, Object defaultValue){
        namedArgumentMap.put(argName, new NamedArgument(argDescription, type, defaultValue));
        namedArgumentMap.get(argName).setRequired();
        namedArgumentNames.add(argName);
        namedArgumentShorthand.put(argName, shorthand);
        totalRequiredArguments++;
    }
    
    /**
     *  Adds the specified named argument to the specified group to allow for mutual exclusion
     *
     *  @param argName a string representing the name of the named argument to be added to the group
     *  @param groupName a string representing the name of the group that the argument will be added to
     *  @throws InvalidArgumentException if the specified argument is a positional argument, because positional arguments can't be in a group
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     *  @throws ArgumentAlreadyInGroupException if the specified argument is already in a group
     */
    public void addArgumentToGroup(String argName, String groupName){
        if(argumentGroupValues.get(argName) == null){
            if(namedArgumentMap.get(argName) != null){
                namedArgumentMap.get(argName).setGroupName(groupName);
                argumentGroupValues.put(argName, groupName);
            }else if(positionalArgumentMap.get(argName) != null){
                throw new InvalidArgumentException("\n\nCannot add positional argument to a group\n");      
            }else{
                throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
            }    
        }else{
            throw new ArgumentAlreadyInGroupException("\n\n\"" + argName + "\" is already in a group.\n");
        }            
    }
    
    /**
     *  Assigns an array of restricted values to the specified argument. 
     *  If the argument already has restricted values, they will be replaced with the new values.
     *
     *  @param argName a string representing the name of the named argument to be added to the group
     *  @param values an array of objects that should match the value type assigned to the argument to be assigned as the restricted values
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */
    public void setRestrictedValues(String argName, Object[] values){
		if(positionalArgumentMap.get(argName) != null){
            positionalArgumentMap.get(argName).setRestrictedValues(values);
        }else if(namedArgumentMap.get(argName) != null){
            namedArgumentMap.get(argName).setRestrictedValues(values);       
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }        
    }
    
    /**
     *  Returns the number of positional arguments.
     *
     *  @return an integer representing the number of positional arguments
     */
    public int getNumberOfArguments(){
        return positionalArgumentMap.size();
    }
    
    /**
     *  Returns the number of named arguments.
     *
     *  @return an integer representing the number of named arguments
     */
    public int getNumberOfOptionalArguments(){
        return namedArgumentMap.size();
    }
    
    /**
     *  Returns the description of the requested argument.
     *  
     *  @param argName a string representing the name of the desired argument
     *  @return a string containing the description of the specified argument
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */
    public String getArgumentDescription(String argName){
		if(positionalArgumentMap.get(argName) != null){
            return positionalArgumentMap.get(argName).getDescription();
        }else if(namedArgumentMap.get(argName) != null){
            return namedArgumentMap.get(argName).getDescription();        
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    /**
     *  Returns the type of the requested argument.
     *  
     *  @param argName a string representing the name of the desired argument
     *  @return the type of the specified argument as an ArgumentParser.Types enum object
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */    
    public Types getArgumentType(String argName){
		if(positionalArgumentMap.get(argName) != null){
            return positionalArgumentMap.get(argName).getType();
        }else if(namedArgumentMap.get(argName) != null){
            return namedArgumentMap.get(argName).getType();
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    /**
     *  Returns the type of the requested argument as a string.
     *  
     *  @param argName a string representing the name of the desired argument
     *  @return the type of the specified argument as a string
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */ 
    public String getArgumentTypeAsString(String argName){
		if(positionalArgumentMap.get(argName) != null){
            return positionalArgumentMap.get(argName).getTypeAsString();
        }else if(namedArgumentMap.get(argName) != null){
            return namedArgumentMap.get(argName).getTypeAsString();            
        }else{
            throw new UnknownArgumentException("\n\nCould not find argument \"" + argName + "\"\n");
        }
    }
    
    /**
     *  Returns the value of the requested argument.
     *  
     *  @param argName a string representing the name of the desired argument
     *  @param <T> the type that the returning value will be saved into
     *  @return an object that matches the type of the specified argument that represents the value
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */
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
    
    /**
     *  Returns the default value of the requested argument.
     *  
     *  @param argName a string representing the name of the desired argument
     *  @param <T> the type that the returning value will be saved into
     *  @return an object that matches the type of the specified argument that represents the default value
     *  @throws InvalidArgumentException if the argument requested isn't a named argument
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */
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
    
    /**
     *  Returns the name of the group the argument is in.
     *  
     *  @param argName a string representing the name of the desired argument
     *  @return a string representing the group the requested argument is in
     *  @throws NotInGroupException if the argument requested isn't in a group
     *  @throws InvalidArgumentException if the argument requested is a positional argument
     *  @throws UnknownArgumentException if the argument requested doesn't exist
     */
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
    
    /**
     *  Parses arguments and assigns values to their respected arguments.
     *  
     *  @param args the array of strings that is going to be parsed
     *  @throws NotEnoughArgumentsException if there are not enough positional values to fill the positional arguments
     *  @throws TooManyArgumentsException if there are too many positional values to fill the positional arguments
     *  @throws NotInTheSameGroupException if multiple named arguments were called, but they are not in the same group
     *  @throws MissingArgumentValueException if a named argument was called but not given a value
     *  @throws UnknownArgumentException if there is a named argument specified that does not exist
     *  @throws NotAllArgumentsUsedException if not all required named arguments were used
     */
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
        for(int i = 0; i < namedArgumentNames.size(); i++){
            if(s.equals(namedArgumentShorthand.get(namedArgumentNames.get(i)))){
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
                            throw new MissingArgumentValueException("\n\n" + "\nExpected a value following \"" + args.get(i) + "\"");
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
            throw new NotAllArgumentsUsedException("\n\nDid not use all required arguments.\nExpected " + totalRequiredArguments + ", but only got " + usedRequiredArguments.size());
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
    
    /**
     *  Prints a formatted and organized table containing all of the arguments and their respective values 
     *  like name, description, and type, as well as default value and group where applicable
     */
    public void printProgramInformation(){
        System.out.println("\n" + programDescription + "\n");     
        int numberOfFlags = 0;
        int numberOfRequiredArguments = 0;
        
        for(int i = 0; i < positionalArgumentNames.size(); i++){
            System.out.print(positionalArgumentNames.get(i)); 
            if(!positionalArgumentMap.get(positionalArgumentNames.get(i)).containsRestrictedValues()){
                System.out.print(" ");
            }else{
                System.out.print("(");
                Object[] objectArray = getAllRestrictedValues(positionalArgumentNames.get(i));
                for(int j = 0; j < objectArray.length; j++){
                    if(j != objectArray.length-1)
                        System.out.print(objectArray[j] + "/");
                    else
                        System.out.print(objectArray[j]);
                }
                System.out.print(") ");
            }           
        }            
        for(int i = 0; i < namedArgumentNames.size(); i++){
            if(getArgumentType(namedArgumentNames.get(i)) != Types.BOOLEAN){
                if(!namedArgumentMap.get(namedArgumentNames.get(i)).containsRestrictedValues()){
                    if(!namedArgumentMap.get(namedArgumentNames.get(i)).isThisRequired()){
                        System.out.print("[--" + namedArgumentNames.get(i) + " " + namedArgumentMap.get(namedArgumentNames.get(i)).getTypeAsString() + "] ");                 
                    }else{                        
                        System.out.print("(req)[--" + namedArgumentNames.get(i) + " " + namedArgumentMap.get(namedArgumentNames.get(i)).getTypeAsString() + "] ");
                        numberOfRequiredArguments++;
                    }
                }else{
                    if(!namedArgumentMap.get(namedArgumentNames.get(i)).isThisRequired()){
                        System.out.print("[--" + namedArgumentNames.get(i) + " (");                        
                    }else{                        
                        System.out.print("(req)[--" + namedArgumentNames.get(i) + " (");
                        numberOfRequiredArguments++;
                    }
                    Object[] objectArray = getAllRestrictedValues(namedArgumentNames.get(i));
                    for(int j = 0; j < objectArray.length; j++){
                        if(j != objectArray.length-1)
                            System.out.print(objectArray[j] + "/");
                        else
                            System.out.print(objectArray[j]);
                    }
                    System.out.print(")] ");
                }
            }else{
                System.out.print("[--" + namedArgumentNames.get(i) + "] ");
                numberOfFlags++;
            }
        }
        
        if(positionalArgumentNames.size() > 0){
            System.out.println("\n\n***Positional Arguments***");
            System.out.printf("%-18s %-10s %-32s \n", "Name", "Data Type", "Description");   
            System.out.printf("%-18s %-10s %-32s \n", "----", "---- ----", "-----------");  
        }        
        for(int i = 0; i < positionalArgumentNames.size(); i++){
            System.out.printf("%-18s %-10s %-32s \n", positionalArgumentNames.get(i), 
                positionalArgumentMap.get(positionalArgumentNames.get(i)).getTypeAsString(),
                positionalArgumentMap.get(positionalArgumentNames.get(i)).getDescription());
        }
        if(namedArgumentNames.size()-numberOfFlags-numberOfRequiredArguments > 0){
            System.out.println("\n***Named Arguments***");
            System.out.printf("%-18s %-10s %-10s %-32s \n", "Name", "Data Type", "Group", "Description"); 
            System.out.printf("%-18s %-10s %-10s %-32s \n", "----", "---- ----", "-----", "-----------"); 
        }
        for(int i = 0; i < namedArgumentNames.size(); i++){
            if(getArgumentType(namedArgumentNames.get(i)) != Types.BOOLEAN 
                    && !namedArgumentMap.get(namedArgumentNames.get(i)).isThisRequired()){
                String groupName = "[none]";
                if(argumentGroupValues.get(namedArgumentNames.get(i)) != null){
                    groupName = argumentGroupValues.get(namedArgumentNames.get(i));
                }
                System.out.printf("%-18s %-10s %-10s %-32s \n", namedArgumentNames.get(i), 
                    namedArgumentMap.get(namedArgumentNames.get(i)).getTypeAsString(),
                    groupName, namedArgumentMap.get(namedArgumentNames.get(i)).getDescription());
            }
        }
        
        if(numberOfRequiredArguments > 0){            
            System.out.println("\n***Required Named Arguments***");
            System.out.printf("%-18s %-10s %-32s \n", "Name", "Data Type", "Description"); 
            System.out.printf("%-18s %-10s %-32s \n", "----", "---- ----", "-----------"); 
            for(int i = 0; i < namedArgumentNames.size(); i++){
                if(namedArgumentMap.get(namedArgumentNames.get(i)).isThisRequired()){                  
                    System.out.printf("%-18s %-10s %-32s \n", namedArgumentNames.get(i), 
                        namedArgumentMap.get(namedArgumentNames.get(i)).getTypeAsString(), 
                        namedArgumentMap.get(namedArgumentNames.get(i)).getDescription());
                }
            }
        }
        
        if(numberOfFlags > 0){            
            System.out.println("\n***Named Flags***");
            System.out.printf("%-18s %-32s \n", "Name", "Description"); 
            System.out.printf("%-18s %-32s \n", "----", "-----------"); 
            for(int i = 0; i < namedArgumentNames.size(); i++){
                if(getArgumentType(namedArgumentNames.get(i)) == Types.BOOLEAN){
                    System.out.printf("%-18s %-32s \n", namedArgumentNames.get(i), 
                    namedArgumentMap.get(namedArgumentNames.get(i)).getDescription());
                }
            }
        }
    }	
}