package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest
{
    @Test
    public void testInitialLabelArrayIsEmpty()
	{
		ArgumentParser p = new ArgumentParser();
        
        assertEquals(0, p.getNumberOfArguments());
    }
	
	@Test
    public void testAddArgFillsLabelArray()
	{
		ArgumentParser p = new ArgumentParser();
		
        String[] argumentNames = {"Length", "Width", "Height"};        
		p.addArgumentNames(argumentNames);
		
		assertEquals("Length", p.getArgumentName(0));
		assertEquals("Width", p.getArgumentName(1));
		assertEquals("Height", p.getArgumentName(2)); 
    }
    
    @Test
    public void testAddArgIndividually()
    {
		ArgumentParser p = new ArgumentParser();
        
        p.addArgumentName("Length");
        p.addArgumentName("Width");
        p.addArgumentName("Height");
		
		assertEquals("Length", p.getArgumentName(0));
		assertEquals("Width", p.getArgumentName(1));
		assertEquals("Height", p.getArgumentName(2));         
    }
	
	@Test
    public void testGetValueReturnsCorrectValue()
	{
		ArgumentParser p = new ArgumentParser();
		
        String[] argumentNames = {"Length", "Width", "Height"};        
		p.addArgumentNames(argumentNames);
				
		String[] args = {"1", "2", "3"};
		p.parse(args);
		
		assertEquals("1", p.getValueOf("Length"));
		assertEquals("2", p.getValueOf("Width"));
		assertEquals("3", p.getValueOf("Height"));
	}
	
	//@Test(expected=UnknownArgumentException.class)
	public void testGetValueOfUnknownArgumentThrowsException()
	{
		ArgumentParser p = new ArgumentParser();
		
        String[] argumentNames = {"Argument1"};        
		p.addArgumentNames(argumentNames);
        
		String[] args = {"7"};
		p.parse(args);
		p.getValueOf("8");
	}

}