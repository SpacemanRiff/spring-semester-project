package edu.jsu.mcis;

import edu.jsu.mcis.ArgumentParser.Types;

public class OptionalArgumentInformation extends ArgumentInformation{    
    public OptionalArgumentInformation(String description, Types type, Object defaultValue){
        super(description, type);
        setValue(defaultValue + "");
    }
}