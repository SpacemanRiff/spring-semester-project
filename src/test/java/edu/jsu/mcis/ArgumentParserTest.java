package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest{       
    ArgumentParser p;
    
    @Before
    public void setUp(){        
        p = new ArgumentParser(); 
    }
    
    @Test
    public void testInitialArgumentMapIsEmpty(){        
        assertEquals(0, p.getNumberOfArguments());
    }   
    
    @Test
    public void testAddArgAndTestDescription(){    
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        assertEquals("The length of the box", p.getArgumentDescription("Length"));
        assertEquals("The width of the box", p.getArgumentDescription("Width"));
        assertEquals("The height of the box", p.getArgumentDescription("Height")); 
    }   
    
    @Test
    public void testAddArgAndTestType(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Length"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Width"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Height"));
    }    
    
    @Test
    public void testAddArgAndTestTypeUsingValueOf(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.valueOf("INTEGER"));
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.valueOf("INTEGER"));
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.valueOf("INTEGER"));
        
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Length"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Width"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Height"));
    }    
    
    @Test
    public void testAddArgAndTestTypeAsString(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        assertEquals("INTEGER", p.getArgumentTypeAsString("Length"));
        assertEquals("INTEGER", p.getArgumentTypeAsString("Width"));
        assertEquals("INTEGER", p.getArgumentTypeAsString("Height"));
    }

    @Test
    public void testInitialOptionalArgumentMapIsEmpty(){
        assertEquals(0, p.getNumberOfOptionalArguments());
    }     
    
    @Test
    public void testAddOptionalArgAndTestDescription(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.INTEGER, 10);   
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.STRING, "value"); 
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.BOOLEAN, true); 
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.FLOAT, 2.0f); 
        assertEquals("Optional Argument Description 1", p.getArgumentDescription("Optional Argument Name 1"));        
        assertEquals("Optional Argument Description 2", p.getArgumentDescription("Optional Argument Name 2"));      
        assertEquals("Optional Argument Description 3", p.getArgumentDescription("Optional Argument Name 3"));  
        assertEquals("Optional Argument Description 4", p.getArgumentDescription("Optional Argument Name 4"));
    }    
    
    @Test
    public void testAddOptionalIntegerAndTestType(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.INTEGER, 10); 
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Optional Argument Name 1"));        
    }     
    
    @Test
    public void testAddOptionalStringAndTestType(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.STRING, "value");     
        assertEquals(ArgumentParser.Types.STRING, p.getArgumentType("Optional Argument Name 2"));    
    }  
    
    @Test
    public void testAddOptionalBooleanAndTestType(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.BOOLEAN, true);     
        assertEquals(ArgumentParser.Types.BOOLEAN, p.getArgumentType("Optional Argument Name 3"));    
    }  
    
    @Test
    public void testAddOptionalFloatAndTestType(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.FLOAT, 2.0f);   
        assertEquals(ArgumentParser.Types.FLOAT, p.getArgumentType("Optional Argument Name 4"));
    } 
    
    @Test
    public void testAddOptionalIntegerAndTestTypeUsingValueOf(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.valueOf("INTEGER"), 10);  
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Optional Argument Name 1"));        
    }     
    
    @Test
    public void testAddOptionalStringAndTestTypeUsingValueOf(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.valueOf("STRING"), "value"); 
        assertEquals(ArgumentParser.Types.STRING, p.getArgumentType("Optional Argument Name 2"));      
    }  
    
    @Test
    public void testAddOptionalBooleanAndTestTypeUsingValueOf(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.valueOf("BOOLEAN"), true); 
        assertEquals(ArgumentParser.Types.BOOLEAN, p.getArgumentType("Optional Argument Name 3"));    
    }  
    
    @Test
    public void testAddOptionalFloatAndTestTypeUsingValueOf(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.valueOf("FLOAT"), 2.0f); 
        assertEquals(ArgumentParser.Types.FLOAT, p.getArgumentType("Optional Argument Name 4"));   
    } 
    
    @Test
    public void testAddOptionalIntegerAndTestTypeAsString(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);        
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.INTEGER, 10); 
        assertEquals("INTEGER", p.getArgumentTypeAsString("Optional Argument Name 1"));      
    }  
    
    @Test
    public void testAddOptionalStringAndTestTypeAsString(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.STRING, "value"); 
        assertEquals("STRING", p.getArgumentTypeAsString("Optional Argument Name 2"));      
    }  
    
    @Test
    public void testAddOptionalBooleanAndTestTypeAsString(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.BOOLEAN, true); 
        assertEquals("BOOLEAN", p.getArgumentTypeAsString("Optional Argument Name 3"));    
    }  
    
    @Test
    public void testAddOptionalFloatAndTestTypeAsString(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.FLOAT, 2.0f); 
        assertEquals("FLOAT", p.getArgumentTypeAsString("Optional Argument Name 4"));    
    }
    
    @Test
    public void testAddOptionalIntAndTestDefaultValue(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 1", "Optional Argument Description 1", ArgumentParser.Types.INTEGER, 10);  
        int intValue = p.getDefaultValueOf("Optional Argument Name 1");
        assertEquals(10, intValue);           
    }
    
    @Test
    public void testAddOptionalStringAndTestDefaultValue(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 2", "Optional Argument Description 2", ArgumentParser.Types.STRING, "value"); 
        String stringValue = p.getDefaultValueOf("Optional Argument Name 2");
        assertEquals("value", stringValue);                
    }
    
    @Test
    public void testAddOptionalBooleanAndTestDefaultValue(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 3", "Optional Argument Description 3", ArgumentParser.Types.BOOLEAN, false); 
        boolean boolValue = p.getDefaultValueOf("Optional Argument Name 3");
        assertEquals(false, boolValue);               
    }
    
    @Test
    public void testAddOptionalFloatAndTestDefaultValue(){
        p.addArgument("Argument Name", "Argument Description", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument Name 4", "Optional Argument Description 4", ArgumentParser.Types.FLOAT, 2.0f); 
        float floatValue = p.getDefaultValueOf("Optional Argument Name 4");
        assertEquals(2.0f, floatValue, 0.1f);           
    }
    
    @Test
    public void testAddProgramDescription(){        
        p.setProgramDescription("This is simply a test.");
        assertEquals("This is simply a test.", p.getProgramDescription()); 
    }
    
    @Test
    public void testGetValueReturnsCorrectValue(){
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
        String color = p.getValueOf("Color");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals("red", color);
    }   
    
    @Test
    public void testGetValueOfOptionalArgumentInTheMiddleOfTheList(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
                
        String[] args = {"1", "--Color", "red", "2", "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        String color = p.getValueOf("Color");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals("red", color);
    }       
    
    @Test
    public void testGetValueOfOptionalArgumentAtBeginningOfTheList(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
                
        String[] args = {"--Color", "red", "1", "2", "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        String color = p.getValueOf("Color");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals("red", color);
    }
    
    @Test
    public void testGetValueOfMultipleOptionalArguments(){
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
        String color = p.getValueOf("Color");
        int weight = p.getValueOf("Weight");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals("red", color);
        assertEquals(5, weight);
    }
    
    @Test(expected=InvalidArgumentException.class)
    public void testOptionalArgumentAtEndOfTheListWithNoValueThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
                
        String[] args = {"1", "2", "3", "--Color"};
        p.parse(args);
    }   
    
    @Test(expected=UnknownArgumentException.class)
    public void testUnknownOptionalArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.addOptionalArgument("Color", "The color of the box", ArgumentParser.Types.STRING, "white");
                
        String[] args = {"1", "2", "3", "--Nice", "Nice"};
        p.parse(args);
    }
    
    @Test
    public void testGetValueOfIntegerReturnsInteger(){
        p.addArgument("Length", "The length", ArgumentParser.Types.INTEGER);
                
        String[] args = {"2"};
        p.parse(args);
        
        assertEquals("INTEGER", p.getArgumentTypeAsString("Length"));
        
        int length = p.getValueOf("Length");
        
        assertEquals(2, length);
    }
    
    @Test
    public void testGetValueOfStringReturnsString(){
        p.addArgument("Current Month", "The current month", ArgumentParser.Types.STRING);
                
        String[] args = {"January"};
        p.parse(args);
        
        assertEquals("STRING", p.getArgumentTypeAsString("Current Month"));
        
        String month = p.getValueOf("Current Month");
        
        assertEquals("January", month);
    }
    
    @Test
    public void testGetValueOfBooleanReturnsBoolean(){
        p.addArgument("Raining", "Whether or not it is raining", ArgumentParser.Types.BOOLEAN);
                
        String[] args = {"false"};
        p.parse(args);
        
        assertEquals("BOOLEAN", p.getArgumentTypeAsString("Raining"));
        
        boolean isRaining = p.getValueOf("Raining");
        
        assertEquals(false, isRaining);
    }
    
    @Test
    public void testGetValueOfFloatReturnsFloat(){
        p.addArgument("Precipitation", "The amount of precipitation in the last week", ArgumentParser.Types.FLOAT);
        String[] args = {"2.0f"};
        p.parse(args);
        assertEquals("FLOAT", p.getArgumentTypeAsString("Precipitation"));
        float precipiation = p.getValueOf("Precipitation");
        assertEquals(2.0f, precipiation, 0.1f);
    }
    
    @Test
    public void testSetOptionalFlagAtBeginning(){
        p.addOptionalArgument("Raining", "Is it snowing?", ArgumentParser.Types.BOOLEAN, false);
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        p.printProgramInformation();
        
        String[] args = {"--Raining", "1", "2" , "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        boolean value = p.getValueOf("Raining");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals(true, value);
    }
    
    @Test
    public void testSetOptionalFlagAtEnd(){
        p.addOptionalArgument("Raining", "Is it snowing?", ArgumentParser.Types.BOOLEAN, false);
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"1", "2" , "3", "--Raining"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        boolean value = p.getValueOf("Raining");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals(true, value);
    }
    
    @Test
    public void testSetOptionalFlagInMiddle(){
        p.addOptionalArgument("Raining", "Is it snowing?", ArgumentParser.Types.BOOLEAN, false);
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"1", "--Raining", "2" , "3"};
        p.parse(args);
        
        int length = p.getValueOf("Length");
        int width = p.getValueOf("Width");
        int height = p.getValueOf("Height");
        boolean value = p.getValueOf("Raining");
        
        assertEquals(1, length);
        assertEquals(2, width);
        assertEquals(3, height);
        assertEquals(true, value);
    }
    
    @Test
    public void testShorthandArgument(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Color", "The color of the shape", ArgumentParser.Types.STRING, "None");
        
        String[] args = {"2", "3", "4", "-C", "red"};
        p.parse(args);
        
        assertEquals("red", p.getValueOf("Color"));
    }
    
    @Test
    public void testGetArgumentsFromFile(){
        XMLManager.loadArguments("testXML/testRead.xml", p);        
        
        p.printProgramInformation();
        
        assertEquals("Argument 1 description", p.getArgumentDescription("Argument1"));
        assertEquals(ArgumentParser.Types.INTEGER, p.getArgumentType("Argument1"));
        
        assertEquals("Optional Argument 1 description", p.getArgumentDescription("OptionalArgument1"));
        assertEquals(ArgumentParser.Types.STRING, p.getArgumentType("OptionalArgument1"));
        assertEquals("Value1", p.getDefaultValueOf("OptionalArgument1"));
        
        assertEquals("Optional Flag 1 description", p.getArgumentDescription("OptionalFlag1"));
        assertEquals(ArgumentParser.Types.BOOLEAN, p.getArgumentType("OptionalFlag1"));
        assertEquals(false, p.getDefaultValueOf("OptionalFlag1"));
        
    }
    
    @Test
    public void testWriteArgumentsToFile(){
        p.addArgument("Length", "The length of the shape", ArgumentParser.Types.STRING);
        p.addOptionalArgument("Color", "The color of the shape", ArgumentParser.Types.STRING, "red");
        p.addOptionalArgument("Flag", "Is there a flag", ArgumentParser.Types.BOOLEAN, false);
        
        String[] args = {"3", "-C", "red"};
        p.parse(args);
        p.printProgramInformation();
        
        XMLManager.writeArguments("testXML/testWrite.xml", p);
        p = new ArgumentParser();
        XMLManager.loadArguments("testXML/testWrite.xml", p);
        
        assertEquals("The length of the shape", p.getArgumentDescription("Length"));
        assertEquals(ArgumentParser.Types.STRING, p.getArgumentType("Length"));
        
        assertEquals("The color of the shape", p.getArgumentDescription("Color"));
        assertEquals(ArgumentParser.Types.STRING, p.getArgumentType("Color"));
        assertEquals("red", p.getDefaultValueOf("Color"));
        
        assertEquals("Is there a flag", p.getArgumentDescription("Flag"));
        assertEquals(ArgumentParser.Types.BOOLEAN, p.getArgumentType("Flag"));
        assertEquals(false, p.getDefaultValueOf("Flag"));        
    }
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetDescriptionOfUnknownArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        p.getArgumentDescription("Not length");
    }    
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetTypeOfUnknownArgumentThrowsException(){     
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        p.getArgumentType("Not length");
    }    
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetTypeAsStringOfUnknownArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        p.getArgumentTypeAsString("Not length");
    }   
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetValueOfUnknownArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"7"};

        p.parse(args);
        p.getValueOf("8");      
    }
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetDescriptionOfUnknownOptionalArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, 20);
        
        p.getArgumentDescription("Not optional argument");
    }    
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetTypeOfUnknownOptionalArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, 20);
        
        p.getArgumentType("Not optional argument");
    }    
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetTypeAsStringOfUnknownOptionalArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, 20);
        
        p.getArgumentTypeAsString("Not optional argument");
    }   
    
    @Test(expected=UnknownArgumentException.class)
    public void testGetValueOfUnknownOptionalArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, 20);

        p.getValueOf("Not optional argument");
    }   
    
    @Test(expected=InvalidArgumentException.class)
    public void testSendInvalidOptionalArgumentDefaultValueThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addOptionalArgument("Optional Argument", "Optional Argument Description", ArgumentParser.Types.INTEGER, "bad");        
    }
    
    @Test(expected=InvalidArgumentException.class)
    public void testSendInvalidArgumentThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"nice"};

        p.parse(args);  
    }
    
    @Test(expected = NotEnoughArgumentsException.class)
    public void testNotEnoughArgumentsThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"7"};
        p.parse(args);
    }
    
    @Test(expected = TooManyArgumentsException.class)
    public void testTooManyArgumentsThrowsException(){
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        
        String[] args = {"7", "4", "8"};
        p.parse(args);
    }
}