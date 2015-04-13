package edu.jsu.mcis;

import edu.jsu.mcis.ArgumentParser.Types;
import java.util.ArrayList;

/** Stores information about a Named Argument */
public class NamedArgument extends Argument{   
    private Object defaultValue;
    private boolean isRequired;
    private boolean isShorthand;
    private boolean isInGroup;
    private String groupName;
    
    /**
     *  Creates a new NamedArgument object that can store a description, a type, and a default value, as well parse 
     *  a value to the specified types, and restrict the value that can be saved to a few specified values.
     *
     *  @param description a string containing a description to describe what this argument will store
     *  @param type an ArgumentParser.Types enum value to specify what data type will be stored by this argument
     *  @param defaultValue the desired default value, stored as an Object
     */
    public NamedArgument(String description, Types type, Object defaultValue){
        super(description, type);
        setDefaultValue(defaultValue + "");
        setValue(defaultValue + "");
        isRequired = false;
        isInGroup = false;
        isShorthand = false;
        allowableNumberOfValues = 1;
    }
    
    private void setDefaultValue(String defaultValue){
        try{
            switch(getType()){
                case INTEGER:
                    this.defaultValue = Integer.parseInt(defaultValue);
                    break;
                case FLOAT:
                    this.defaultValue = Float.parseFloat(defaultValue);
                    break;
                case BOOLEAN:
                    this.defaultValue = Boolean.parseBoolean(defaultValue);
                    break;
                default:
                    this.defaultValue = defaultValue;
                    break;
            }
        }catch(IllegalArgumentException ex){
            throw new InvalidArgumentException("\n\nInvalid argument \"" + defaultValue + "\"\n");
        }   
    }
    
    /**
     *  Returns the default value that has been stored in this Argument.
     *
     *  @return the default value, as an object, that has been stored
     */
    public Object getDefaultValue(){
        return defaultValue;
    }
    
    /**
     *  Sets this argument to be required, meaning it has to be used whenever the argument parser is called.
     */
    public void setRequired(){
        isRequired = true;
    }
    
    /**
     *  Tells whether this argument is a required argument or not.
     *
     *  @return true if the argument is required, or false if the argument is not
     */
    public boolean isThisRequired(){
        return isRequired;
    }    
    
    /**
     *  Tells this argument that it has a shorthand value.
     */
    public void setShorthand(){
        isShorthand = true;
    }
    
    /**
     *  Tells whether this argument has a shorthand value or not.
     *
     *  @return true if the argument has a shorthand value, or false if the argument does not have a shorthand value
     */
    public boolean isArgumentShorthand(){
        return isShorthand;
    }
    
    /**
     *  Sets a group name for this group, and makes isInAGroup() return true
     *
     *  @param groupName the desired group name for this argument
     */
    public void setGroupName(String groupName){
        this.groupName = groupName;
        isInGroup = true;
    }
    
    /**
     *  Tells whether this argument is in a mutually exclusive group or not
     *
     *  @return true if the argument is in a group, or false if the argument is not in a group
     */
    public boolean isInAGroup(){
        return isInGroup;
    }
}