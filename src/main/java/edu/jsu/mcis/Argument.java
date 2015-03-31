package edu.jsu.mcis;

import edu.jsu.mcis.ArgumentParser.Types;

public class Argument{
    private String description;
    private Types type;
    private Object value;
    private boolean hasRestrictedValues;
    private Object[] restrictedValues;
    
    public Argument(String description, Types type){
        this.description = description;
        hasRestrictedValues = false;
        this.type = type;
    }
    
    public void setValue(String value) throws InvalidArgumentException{
        try{
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
        }catch(IllegalArgumentException ex){
            throw new InvalidArgumentException("\n\nInvalid argument \"" + value + "\"\n");
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
    
    public void setRestrictedValues(Object[] values){
        restrictedValues = new Object[values.length];
        for(int i = 0; i < values.length; i++){
            try{
                switch(type){
                    case INTEGER:
                        restrictedValues[i] = Integer.parseInt(values[i] + "");
                        break;
                    case FLOAT:
                        restrictedValues[i] = Float.parseFloat(values[i] + "");
                        break;
                    case BOOLEAN:
                        restrictedValues[i] = Boolean.parseBoolean(values[i] + "");
                        break;
                    default:
                        restrictedValues[i] = values[i];
                        break;
                }
            }catch(IllegalArgumentException ex){
                throw new InvalidArgumentException("\n\nInvalid argument \"" + value + "\"\n");
            }   
        }
    }
}