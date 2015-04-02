package edu.jsu.mcis;

import edu.jsu.mcis.ArgumentParser.Types;

public class NamedArgument extends Argument{   
    private Object defaultValue;
    private boolean isRequired;
    private boolean isShorthand;
    private boolean isInGroup;
    private String groupName;
    
    public NamedArgument(String description, Types type, Object defaultValue){
        super(description, type);
        setDefaultValue(defaultValue + "");
        setValue(defaultValue + "");
        isRequired = false;
        isInGroup = false;
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
    
    public Object getDefaultValue(){
        return defaultValue;
    }
    
    public void setRequired(){
        isRequired = true;
    }
    
    public boolean isThisRequired(){
        return isRequired;
    }
    
    public void setShorthand(){
        isShorthand = true;
    }
    
    public boolean isArgumentShorthand(){
        return isShorthand;
    }
    
    public void setGroupName(String groupName){
        this.groupName = groupName;
        isInGroup = true;
    }
    
    public boolean isInAGroup(){
        return isInGroup;
    }
}