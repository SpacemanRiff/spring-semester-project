package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest
{
    //@Test
    public void TestGetFirstInput()
	{
        ArgumentParser a = new ArgumentParser();
        String arguments = "1 2 3";
		
		String expectedFirst = "1";
		
		a.sendArguments(arguments);
		String first = a.getArgumentAt(0);

        
		assertEquals(expectedFirst, first);
    }
	
	@Test
	public void TestGetFirstInput()
	{
        ArgumentParser a = new ArgumentParser();
        String arguments = "1 2 3";
		
		
		a.sendArguments(arguments);
		String returnedArgs = a.getArguments();
		

        
		assertEquals("1 2 3", returnedArgs);
    }
}