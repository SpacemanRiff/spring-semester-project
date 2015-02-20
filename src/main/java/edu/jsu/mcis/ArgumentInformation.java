package edu.jsu.mcis;

import edu.jsu.mcis.ArgumentParser.Types;

public class ArgumentInformation{
    private String description;
    private Types type;
    private Object value;
    
    public ArgumentInformation(String description, Types type){
        this.description = description;
        this.type = type;
    }
    
    public void setValue(String value){
        switch(type){
            case INTEGER:
                this.value = Integer.parseInt(value);
                break;
            case FLOAT:
                this.value = Float.parseFloat(value);
                break;
            case BOOLEAN:
                this.value = Boolean.parseBoolean(value);
                break;
            default:
                this.value = value;
                break;
        }
    }
    
    public Object getValue(){
        return value;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Types getType(){
        return type;
    }
    
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
}