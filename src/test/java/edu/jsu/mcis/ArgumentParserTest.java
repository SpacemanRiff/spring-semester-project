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
    public void testAddArgIndividually()
    {
		ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
		
		assertEquals("Length", p.getArgumentName(0));
		assertEquals("Width", p.getArgumentName(1));
		assertEquals("Height", p.getArgumentName(2));       
		
		assertEquals("The length of the box", p.getArgumentDescription(0));
		assertEquals("The width of the box", p.getArgumentDescription(1));
		assertEquals("The height of the box", p.getArgumentDescription(2)); 
        
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType(0));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType(1));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType(2));
    }
    
    @Test
    public void testAddProgramDescription()
    {        
		ArgumentParser p = new ArgumentParser();
        
        p.setProgramDescription("This is simply a test.");
        
        assertEquals("This is simply a test.", p.getProgramDescription()); 
    }
	
	@Test
    public void testGetValueReturnsCorrectValue()
	{
		ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
				
		String[] args = {"1", "2", "3"};
		p.parse(args);
		
		assertEquals("1", p.getValueOf("Length"));
		assertEquals("2", p.getValueOf("Width"));
		assertEquals("3", p.getValueOf("Height"));
	}
    
    @Test
    public void testGetValueOfUnknownArgument()
    {        
		ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
		String[] args = {"7"};
		p.parse(args);
		assertEquals("Unknown Label", p.getValueOf("8"));
    }
	
	//@Test(expected=UnknownArgumentException.class)
	public void testGetValueOfUnknownArgumentThrowsException()
	{
		ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
		String[] args = {"7"};

		p.parse(args);
		p.getValueOf("8");
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testNotEnoughArgumentsThrowsException()
	{
		ArgumentParser p = new ArgumentParser();
		
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
		
		String[] args = {"7"};
		p.parse(args);
	}

}