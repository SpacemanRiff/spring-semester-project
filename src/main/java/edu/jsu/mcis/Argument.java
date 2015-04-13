package edu.jsu.mcis;

import edu.jsu.mcis.ArgumentParser.Types;
import java.lang.*;
import java.util.List;
import java.util.ArrayList;

/** Stores information about a positional argument. */
public class Argument{
    private String description;
    private Types type;
    private Object value;
    private boolean hasRestrictedValues;
    private Object[] restrictedValues;
    protected int allowableNumberOfValues;
    private int numOfValues;
    
    /**
     *  Creates a new Argument object that can store a description and a type, as well parse a value to the specified types,
     *  and restrict the value that can be saved to a few specified values.
     *
     *  @param description a string containing a description to describe what this argument will store
     *  @param type an ArgumentParser.Types enum value to specify what data type will be stored by this argument
     */
    public Argument(String description, Types type){
        this.description = description;
        restrictedValues = new Object[0];
        hasRestrictedValues = false;
        this.type = type;
        allowableNumberOfValues = 1;
    }
    
    /**
     *  Parses an individual value from a string to the type assigned to this Argument object.
     *
     *  @param value the value to be parsed, as a string
     *  @throws InvalidArgumentException if the value sent in does not match the type specified, or it does not match one of the restricted values
     */
    public void setValue(String value){
        Object holdValue;        
        try{
            switch(type){
                case INTEGER:
                    holdValue = Integer.parseInt(value);
                    break;
                case FLOAT:
                    holdValue = Float.parseFloat(value);
                    break;
                case BOOLEAN:
                    holdValue = Boolean.parseBoolean(value);
                    break;
                default:
                    holdValue = value;
                    break;
            }
        }catch(IllegalArgumentException ex){
            throw new InvalidArgumentException("\n\nInvalid argument \"" + value + "\"\n\"" + value + "\" does not match the type " + getTypeAsString() + "\n");
        }   
        
        if(hasRestrictedValues){
            boolean foundValue = false;
            int i = 0;
            while(i < restrictedValues.length && !foundValue){
                if(holdValue == restrictedValues[i]){
                    foundValue = true;
                }else if(type == ArgumentParser.Types.FLOAT){
                    if(Float.compare((float)holdValue, (float)restrictedValues[i]) == 0){
                        foundValue = true;
                    }
                }else if(type == ArgumentParser.Types.STRING){
                    if(holdValue.equals(restrictedValues[i])){
                        foundValue = true;                        
                    }
                }
                i++;
            }
            if(!foundValue){
                throw new InvalidArgumentException("\n\n\"" + value + "\" is not using one of the restricted value\nPlease use one of the following values: " + restrictedValues.toString() + "\n");
            }
        }
        this.value = holdValue;
    }
    
    /**
     *  Returns the value that has been stored in this Argument.
     *
     *  @return the value, as an object, that has been stored
     */
    public Object getValue(){
        return value;
    }
    
    /**
     *  Returns the description of this Argument.
     *
     *  @return the description of this Argument as a string
     */
    public String getDescription(){
        return description;
    }
    
    /**
     *  Returns the data type of this argument.
     *
     *  @return the data type of this argument stored as an ArgumentParser.Types enum value
     */
    public Types getType(){
        return type;
    }
    
    /**
     *  Returns the data type of this argument as a string.
     *
     *  @return the data type of this argument stored as a string
     */
    public String getTypeAsString(){            
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
    
    /**
     *  Sets the restricted of this Argument to the specified array of values, and parses them to the necessary data type.
     *
     *  @param values an array of the values to be set as the restricted values
     *  @throws InvalidArgumentException if an object in the array cannot be used as this arguments data type
     */
    public void setRestrictedValues(Object[] values){
        restrictedValues = new Object[values.length];
        numOfValues = 0;
        for(int i = 0; i < values.length; i++){
            try{
                switch(type){
                    case INTEGER:
                        restrictedValues[i] = Integer.parseInt(values[i] + "");
                        break;
                    case FLOAT:
                        restrictedValues[i] = Float.parseFloat(values[i] + "");
                        break;
                    default:
                        restrictedValues[i] = values[i];
                        break;
                }
                numOfValues++;
            }catch(IllegalArgumentException ex){
                throw new InvalidArgumentException("\n\nInvalid argument \"" + value + "\"\n");
            }   
        }
        hasRestrictedValues = true;
    }
    
    /**
     *  Tells whether this argument has restricted values or not.
     *
     *  @return true if there are restricted values, and false if there are not any restricted values
     */
    public boolean containsRestrictedValues(){
        return hasRestrictedValues;
    }
    
    /**
     *  Returns all of the restricted values.
     *
     *  @return all restricted values as an array of objects
     */
    public Object[] getRestrictedValues(){
        return restrictedValues;
    }
    
    /**
     *  Returns a specific restricted value.
     *
     *  @param index the index of the desired value
     *  @return a restricted value as an object
     */
    public Object getRestrictedObject(int index){
        return restrictedValues[index];
    }
    
    /**
     *  Returns the number of restricted values.
     *
     *  @return an integer representing the number of restricted values in this argument.
     */
    public int numOfRestrictedValues(){
        return numOfValues;
    }
    
    public void setAllowableNumberOfValues(int allowableNumberOfValues){
        this.allowableNumberOfValues = allowableNumberOfValues;
    }
    
    public int getAllowableNumberOfValues(){
        return allowableNumberOfValues;
    }
}