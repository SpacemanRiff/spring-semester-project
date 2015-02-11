package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest
{
    //@Test
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
		
		assertEquals("Length", p.getName(0));
		assertEquals("Width", p.getName(1));
		assertEquals("Height", p.getName(2)); 
    }
    
    @Test
    public void testAddArgIndividually(){
		ArgumentParser p = new ArgumentParser();
        
        p.addArg("Length");
        p.addArg("Width");
        p.addArg("Height");
		
		assertEquals("Length", p.getName(0));
		assertEquals("Width", p.getName(1));
		assertEquals("Height", p.getName(2));         
    }
	
	@Test
    public void testGetValueReturnsCorrectValue()
	{
		ArgumentParser p = new ArgumentParser();
		
        String[] argumentNames = {"Length", "Width", "Height"};        
		p.addArgumentNames(argumentNames);
				
		String[] args = {"1", "2", "3"};
		p.parse(args);
		
		assertEquals("1", p.getValue("Length"));
		assertEquals("2", p.getValue("Width"));
		assertEquals("3", p.getValue("Height"));
	}
	
	//@Test(expected=UnknownArgumentException.class)
	public void testGetValueOfUnknownArgumentThrowsException()
	{
		ArgumentParser p = new ArgumentParser();
		
        String[] argumentNames = {"Argument1"};        
		p.addArgumentNames(argumentNames);
        
		String[] args = {"7"};
		p.parse(args);
		p.getValue("8");
	}

}