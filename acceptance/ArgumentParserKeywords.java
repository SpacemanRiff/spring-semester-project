import edu.jsu.mcis.*;

public class ArgumentParserKeywords{
    ArgumentParser a;
    
    public void startProgram(String [] args)
    {
        a = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
        
        a.parse(args);
    }
    
    public int getLength()
    {
        int length = a.getValueOf("Length");
        
        return length;
    }
    
    public int getWidth()
    {
        int width = a.getValueOf("Width");
        
        return width;
    }
    
    public int getHeight()
    {        
        int height = a.getValueOf("Height");
        
        return height;
    }
}