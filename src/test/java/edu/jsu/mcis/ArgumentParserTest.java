package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest
{
    @Test
    public void TestGetFirstInputUsingStringName(){
        String[] argumentNames = new String[] {"length","width","height"};
        
        ArgumentParser a = new ArgumentParser(argumentNames);
        String arguments = "1 2 3";
		
		int expectedFirst = 1;
		
		a.sendArguments(arguments);
        a.splitArguments();
		int first = a.getArgumentAt("length");
        
		assertEquals(expectedFirst, first);
    }
    
    @Test
    public void TestGetFirstInputUsingIndexValue(){
        String[] argumentNames = new String[] {"length","width","height"};
        
        ArgumentParser a = new ArgumentParser(argumentNames);
        String arguments = "1 2 3";
		
		int expectedFirst = 1;
		
		a.sendArguments(arguments);
		int first = a.getArgumentAt(0);
        
		assertEquals(expectedFirst, first);
    }
}