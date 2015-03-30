package edu.jsu.mcis;

import edu.jsu.mcis.ArgumentParser.Types;

public class NamedArgument extends Argument{   
    private Object defaultValue;
    private String shorthand;
    
    public NamedArgument(String description, Types type, Object defaultValue){
        super(description, type);
        setDefaultValue(defaultValue + "");
        setValue(defaultValue + "");
    }
    
    public NamedArgument(String description, String shorthand, Types type, Object defaultValue){
        super(description, type);
        setDefaultValue(defaultValue + "");
        setValue(defaultValue + "");
        shorthand = this.shorthand;
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
    
    public String getShorthandName(){
        return shorthand;
    }
}