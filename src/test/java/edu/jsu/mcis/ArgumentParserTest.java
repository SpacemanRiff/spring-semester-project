package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest{
    @Test
    public void testInitialArgumentMapIsEmpty(){
        ArgumentParser p = new ArgumentParser();
        
        assertEquals(0, p.getNumberOfArguments());
    }   
    
    @Test
    public void testAddArgAndTestDescription(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        assertEquals("The length of the box", p.getArgumentDescription("Length"));
        assertEquals("The width of the box", p.getArgumentDescription("Width"));
        assertEquals("The height of the box", p.getArgumentDescription("Height")); 
    }   
    
    @Test
    public void testAddArgAndTestType(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Length"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Width"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Height"));
    }    
    
    @Test
    public void testAddArgAndTestTypeUsingValueOf(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.valueOf("INTEGER"));
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.valueOf("INTEGER"));
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.valueOf("INTEGER"));
        
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Length"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Width"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Height"));
    }    
    
    @Test
    public void testAddArgAndTestTypeAsString(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        assertEquals("INTEGER", p.getArgumentTypeAsString("Length"));
        assertEquals("INTEGER", p.getArgumentTypeAsString("Width"));
        assertEquals("INTEGER", p.getArgumentTypeAsString("Height"));
    }

    @Test
    public void testInitialOptionalArgumentMapIsEmpty(){
        ArgumentParser p = new ArgumentParser();
        
        assertEquals(0, p.getNumberOfOptionalArguments());
    }     
    
    @Test
    public void testAddOptionalArgAndTestDescription(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.INTEGER, 10);   
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.STRING, "value"); 
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.BOOLEAN, true); 
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.FLOAT, 2.0f); 
        
        assertEquals("Optional Argument Description 1", p.getOptionalArgumentDescription("Optional Argument Name 1"));        
        assertEquals("Optional Argument Description 2", p.getOptionalArgumentDescription("Optional Argument Name 2"));      
        assertEquals("Optional Argument Description 3", p.getOptionalArgumentDescription("Optional Argument Name 3"));  
        assertEquals("Optional Argument Description 4", p.getOptionalArgumentDescription("Optional Argument Name 4"));
    }    
    
    @Test
    public void testAddOptionalArgAndTestType(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.INTEGER, 10);   
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.STRING, "value"); 
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.BOOLEAN, true); 
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.FLOAT, 2.0f); 
        
        assertEquals(ArgumentParser.Types.INTEGER, p.getOptionalArgumentType("Optional Argument Name 1"));        
        assertEquals(ArgumentParser.Types.STRING, p.getOptionalArgumentType("Optional Argument Name 2"));      
        assertEquals(ArgumentParser.Types.BOOLEAN, p.getOptionalArgumentType("Optional Argument Name 3"));    
        assertEquals(ArgumentParser.Types.FLOAT, p.getOptionalArgumentType("Optional Argument Name 4"));
    }    
    
    @Test
    public void testAddOptionalArgAndTestTypeUsingValueOf(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.valueOf("INTEGER"), 10);   
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.valueOf("STRING"), "value"); 
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.valueOf("BOOLEAN"), true); 
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.valueOf("FLOAT"), 2.0f); 
        
        assertEquals(ArgumentParser.Types.INTEGER, p.getOptionalArgumentType("Optional Argument Name 1"));        
        assertEquals(ArgumentParser.Types.STRING, p.getOptionalArgumentType("Optional Argument Name 2"));      
        assertEquals(ArgumentParser.Types.BOOLEAN, p.getOptionalArgumentType("Optional Argument Name 3"));    
        assertEquals(ArgumentParser.Types.FLOAT, p.getOptionalArgumentType("Optional Argument Name 4"));   
    }    
    
    @Test
    public void testAddOptionalArgAndTestTypeAsString(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.INTEGER, 10);   
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.STRING, "value"); 
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.BOOLEAN, true); 
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.FLOAT, 2.0f); 
        
        assertEquals("INTEGER", p.getOptionalArgumentTypeAsString("Optional Argument Name 1"));        
        assertEquals("STRING", p.getOptionalArgumentTypeAsString("Optional Argument Name 2"));      
        assertEquals("BOOLEAN", p.getOptionalArgumentTypeAsString("Optional Argument Name 3"));    
        assertEquals("FLOAT", p.getOptionalArgumentTypeAsString("Optional Argument Name 4"));    
    }
    
    @Test
    public void testAddOptionalArgAndTestDefaultValue(){
        ArgumentParser p = new ArgumentParser();
        
        
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.INTEGER, 10);   
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.STRING, "value"); 
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.BOOLEAN, true); 
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.FLOAT, 2.0f); 
        
        int intValue = p.getOptionalArgumentValueOf("Optional Argument Name 1");
        String stringValue = p.getOptionalArgumentValueOf("Optional Argument Name 2");
        boolean boolValue = p.getOptionalArgumentValueOf("Optional Argument Name 3");
        float floatValue = p.getOptionalArgumentValueOf("Optional Argument Name 4");
        
        assertEquals(10, intValue);        
        assertEquals("value", stringValue);      
        assertEquals(true, boolValue);      
        assertEquals(2.0f, floatValue, 0.1f);           
    }
    
    @Test
    public void testAddProgramDescription(){        
        ArgumentParser p = new ArgumentParser();
        
        p.setProgramDescription("This is simply a test.");
        
        assertEquals("This is simply a test.", p.getProgramDescription()); 
    }
    
    @Test
    public void testGetValueReturnsCorrectValue(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.printProgramInformation();
                
        String[] args = {"1", "2", "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
    }
    
    @Test
    public void testGetValueOfOptionalArgumentAtEndOfTheList(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
        
        p.printProgramInformation();
                
        String[] args = {"1", "2", "3", "--Color", "red"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        String color = p.getOptionalArgumentValueOf("Color");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals("red", color);
    }   
    
    @Test
    public void testGetValueOfOptionalArgumentInTheMiddleOfTheList(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
                
        String[] args = {"1", "--Color", "red", "2", "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        String color = p.getOptionalArgumentValueOf("Color");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals("red", color);
    }       
    
    @Test
    public void testGetValueOfOptionalArgumentAtBeginningOfTheList(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
                
        String[] args = {"--Color", "red", "1", "2", "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        String color = p.getOptionalArgumentValueOf("Color");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals("red", color);
    }
    
    @Test
    public void testGetValueOfMultipleOptionalArguments(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
        p.addOptionalArgument("Weight", "The weight of the box", ArgumentParser.Types.INTEGER, 1);
                
        String[] args = {"--Color", "red", "1", "2", "3", "--Weight", "5"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        String color = p.getOptionalArgumentValueOf("Color");
        int weight = p.getOptionalArgumentValueOf("Weight");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals("red", color);
        assertEquals(5, weight);
    }
    
    @Test(expected=InvalidArgumentException.class)
    public void testOptionalArgumentAtEndOfTheListWithNoValueThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
                
        String[] args = {"1", "2", "3", "--Color"};
        p.parse(args);
    }   
    
    @Test(expected=UnknownArgumentException.class)
    public void testUnknownOptionalArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
                
        String[] args = {"1", "2", "3", "--Nice", "Nice"};
        p.parse(args);
    }
    
    @Test
    public void testGetValueReturnsCorrectValueType(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Temperature", "The current air temperature", ArgumentParser.Types.INTEGER);
        p.addArgument("Current Month", "The current month", ArgumentParser.Types.STRING);
        p.addArgument("Raining", "Whether or not it is raining", ArgumentParser.Types.BOOLEAN);
        p.addArgument("Precipitation", "The amount of precipitation in the last week", ArgumentParser.Types.FLOAT);
                
        String[] args = {"32", "January", "false", "2.0f"};
        p.parse(args);
        
        assertEquals("INTEGER", p.getArgumentTypeAsString("Temperature"));
        assertEquals("STRING", p.getArgumentTypeAsString("Current Month"));
        assertEquals("BOOLEAN", p.getArgumentTypeAsString("Raining"));
        assertEquals("FLOAT", p.getArgumentTypeAsString("Precipitation"));
        
        int temperature = p.getValueOf("Temperature");
        String month = p.getValueOf("Current Month");
        boolean isRaining = p.getValueOf("Raining");
        float precipiation = p.getValueOf("Precipitation");
        
        assertEquals(32, temperature);
        assertEquals("January", month);
        assertEquals(false, isRaining);
        assertEquals(2.0f, precipiation, 0.1f);
    }
    
    @Test
    public void testSetOptionalFlagAtBeginning(){
        ArgumentParser p = new ArgumentParser();
        p.addOptionalFlag("Raining", "Is it snowing?");
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.printProgramInformation();
        
        String[] args = {"--Raining", "1", "2" , "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        boolean value = p.getOptionalArgumentValueOf("Raining");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals(true, value);
    }
    
    @Test
    public void testSetOptionalFlagAtEnd(){
        ArgumentParser p = new ArgumentParser();
        p.addOptionalFlag("Raining", "Is it snowing?");
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"1", "2" , "3", "--Raining"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        boolean value = p.getOptionalArgumentValueOf("Raining");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals(true, value);
    }
    
    @Test
    public void testSetOptionalFlagInMiddle(){
        ArgumentParser p = new ArgumentParser();
        p.addOptionalFlag("Raining", "Is it snowing?");
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"1", "--Raining", "2" , "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        boolean value = p.getOptionalArgumentValueOf("Raining");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals(true, value);
    }
    
    @Test
    public void testShorthandArgument(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Color", "The color of the shape", ArgumentParser.Types.STRING, "None");
        
        String[] args = {"2", "3", "4", "-C", "red"};
        p.parse(args);
        
        assertEquals("red", p.getOptionalArgumentValueOf("Color"));
    }
    
    @Test
    public void testGetArgumentsFromFile(){
        ArgumentParser p = new ArgumentParser();
        
        XMLManager manager = new XMLManager();
        
        manager.loadArguments("testRead.xml", p);
        
        p.printProgramInformation();
        
        assertEquals("Argument 1 description", p.getArgumentDescription("Argument1"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Argument1"));
        
        assertEquals("Optional Argument 1 description", p.getOptionalArgumentDescription("OptionalArgument1"));
        assertEquals(ArgumentParser.Types.STRING, p.getOptionalArgumentType("OptionalArgument1"));
        
    }
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetDescriptionOfUnknownArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        p.getArgumentDescription("Not length");
    }    
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetTypeOfUnknownArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        p.getArgumentType("Not length");
    }    
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetTypeAsStringOfUnknownArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        p.getArgumentTypeAsString("Not length");
    }   
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetValueOfUnknownArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"7"};

        p.parse(args);
        p.getValueOf("8");      
    }
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetDescriptionOfUnknownOptionalArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, 20);
        
        p.getOptionalArgumentDescription("Not optional argument");
    }    
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetTypeOfUnknownOptionalArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, 20);
        
        p.getOptionalArgumentType("Not optional argument");
    }    
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetTypeAsStringOfUnknownOptionalArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, 20);
        
        p.getOptionalArgumentTypeAsString("Not optional argument");
    }   
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetValueOfUnknownOptionalArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, 20);

        p.getOptionalArgumentValueOf("Not optional argument");
    }   
    
    @Test(expected=InvalidArgumentException.class)
    public void testSendInvalidOptionalArgumentDefaultValueThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, "bad");        
    }
    
    @Test(expected=InvalidArgumentException.class)
    public void testSendInvalidArgumentThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"nice"};

        p.parse(args);  
    }
    
    @Test(expected = NotEnoughArgumentsException.class)
    public void testNotEnoughArgumentsThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"7"};
        p.parse(args);
    }
    
    @Test(expected = TooManyArgumentsException.class)
    public void testTooManyArgumentsThrowsException(){
        ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"7", "4", "8"};
        p.parse(args);
    }
}