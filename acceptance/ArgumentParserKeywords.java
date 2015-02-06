import edu.jsu.mcis.*;

public class ArgumentParserKeywords{
    ArgumentParser a;
    String arguments;
    
    public void startProgramWithArguments(String args){
        a = new ArgumentParser();
        arguments = args;
        a.sendArguments(arguments);
    }
    public float getLength(){
        a.getArgumentAt(0);
    }
    public float getWidth(){
        a.getArgumentAt(1);
    }
    public float getHeight(){
        a.getArgumentAt(2);
    }
}