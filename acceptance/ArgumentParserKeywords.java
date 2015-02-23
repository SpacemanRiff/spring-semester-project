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
    
    public int getVolume(){        
        int volume = (int)a.getValueOf("Length") * (int)a.getValueOf("Width") * (int)a.getValueOf("Height");
        
        return volume;
    }
}