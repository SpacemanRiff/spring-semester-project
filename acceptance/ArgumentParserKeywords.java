import edu.jsu.mcis.*;

public class ArgumentParserKeywords{
    ArgumentParser a;
    
    public void startProgramWithArguments(String[] args){
        a = new ArgumentParser();
        
        a.addPositionalArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        a.addPositionalArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        a.addPositionalArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        a.parse(args);
    }
    
    public void startProgramWithFlaggedArguments(String[] args){
        a = new ArgumentParser();
        
        a.addPositionalArgument("Temperature", "How warm it is going to be.", ArgumentParser.Types.INTEGER);
        a.addNamedArgument("Raining", "It is raining", ArgumentParser.Types.BOOLEAN, false);
        a.addNamedArgument("Cloudy", "It is cloudy", ArgumentParser.Types.BOOLEAN, false);
        
        a.parse(args);
    }
    
    public void startProgramWithOptionalArguments(String[] args){
        a = new ArgumentParser();
        
        a.addPositionalArgument("Length", "Length of the shape", ArgumentParser.Types.INTEGER);
        a.addPositionalArgument("Width", "Width of the shape", ArgumentParser.Types.INTEGER);
        a.addPositionalArgument("Height", "Height of the shape", ArgumentParser.Types.INTEGER);
        a.addNamedArgument("Color", "C", "Color of the shape", ArgumentParser.Types.STRING, "Black");
        
        a.parse(args);
        
    }
    
    public int getLengthOf(){
        int length = a.getValueOf("Length");
        
        return length;
    }
    
    public int getWidth(){
        int width = a.getValueOf("Width");
        
        return width;
    }
    
    public int getHeight(){        
        int height = a.getValueOf("Height");
        
        return height;
    }
    
    public int getTemperature(){
        int temperature = a.getValueOf("Temperature");
        
        return temperature;
    }
    
    public String getColor(){
        String color = a.getValueOf("Color");
        
        return color;
    }
    
    public boolean getFlagValueOfRaining(){
        boolean flagValue = a.getValueOf("Raining");
        
        return flagValue;
    }
    
    public boolean getFlagValueOfCloudy(){
        boolean flagValue = a.getValueOf("Cloudy");
        
        return flagValue;
    }
    
    public int getVolume(){        
        int volume = (int)a.getValueOf("Length") * (int)a.getValueOf("Width") * (int)a.getValueOf("Height");
        
        return volume;
    }
}