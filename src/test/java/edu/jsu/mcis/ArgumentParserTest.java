package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest
{
    @Test
    public void TestInitialLabelArrayIsEmpty()
	{
		ArgumentParser p = new ArgumentParser();
		
		
		assertEquals("", p.labels[0]);
		assertEquals("", p.labels[1]);
		assertEquals("", p.labels[2]);
 
    }
	
	@Test
    public void TestAddArgFillsLabelArray()
	{
		ArgumentParser p = new ArgumentParser();
		
		p.addArg("Length");
		p.addArg("Width");
		p.addArg("Height");
		
		assertEquals("Length", p.labels[0]);
		assertEquals("Width", p.labels[1]);
		assertEquals("Height", p.labels[2]);
 
    }
	
	@Test
    public void testGetValueReturnsCorrectValue()
	{
		ArgumentParser p = new ArgumentParser();
		
		p.addArg("Length");
		p.addArg("Width");
		p.addArg("Height");
				
		String[] args = {"1", "2", "3"};
		p.parse(args);
		
		assertEquals("1", p.getValue("Length"));
		assertEquals("2", p.getValue("Width"));
		assertEquals("3", p.getValue("Height"));
	}
	
	@Test(expected=SomeException.class)
	public void testGetValueOfUnknownArgumentThrowsException()
	{
		ArgumentParser p = new ArgumentParser();
		p.addArg("something");
		String[] args = {"7"};
		p.parse(args);
		p.getValue("other");
	}

}