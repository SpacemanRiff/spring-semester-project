import edu.jsu.mcis.*;

public class ArgumentParserKeywords{
    ArgumentParser a;
    
    public void startProgramWithArguments(String[] args){
        a = new ArgumentParser();
        
        a.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        a.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        a.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        a.parse(args);
    }
    
    public void startProgramWithOptionalArguments(String[] args){
        a = new ArgumentParser();
        
        a.addArgument("Temperature", "How warm it is going to be.", ArgumentParser.Types.INTEGER);
        a.addOptionalFlag("Raining", "It is raining");
        a.addOptionalFlag("Cloudy", "It is cloudy");
        
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
    public boolean getFlagValueOfRaining(){
        boolean flagValue = a.getOptionalArgumentValueOf("Raining");
        
        return flagValue;
    }
    
    public boolean getFlagValueOfCloudy(){
        boolean flagValue = a.getOptionalArgumentValueOf("Cloudy");
        
        return flagValue;
    }
    
    public int getVolume(){        
        int volume = (int)a.getValueOf("Length") * (int)a.getValueOf("Width") * (int)a.getValueOf("Height");
        
        return volume;
    }
}