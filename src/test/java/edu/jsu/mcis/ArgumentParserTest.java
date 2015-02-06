package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest{
    @Test
    public void testAddArguments(){
        ArgumentParser a = new ArgumentParser();
        String[] arguments = new String[3];
        
        arguments[0] = "1";
        arguments[1] = "4";
        arguments[2] = "7";
        a.addArguments(arguments);
        
        for(int i = 0; i < arguments.size(), i++){
            assertEquals(arguments[i], a.getArguments(i));
        }
    }
    
    @Test
    public void testCalculateVolume(){
        ArgumentParser a = new ArgumentParser();
        String[] arguments = new String[3];
        
        arguments[0] = "1";
        arguments[1] = "4";
        arguments[2] = "7";
        a.addArguments(arguments);

        assertEquals(arguments[0]*arguments[1]*arguments[2], a.calcVolume);
    }
}