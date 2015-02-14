package edu.jsu.mcis;

import java.util.ArrayList;

public class ArgumentParser
{
    private ArrayList<ArgumentInformation> argumentNames;
    private ArrayList<Object> argumentValues;
    private String programDescription;
    public enum Types {INTEGER, STRING, FLOAT, BOOLEAN};
	
	public ArgumentParser()
	{
		argumentNames = new ArrayList<ArgumentInformation>();
        argumentValues = new ArrayList<Object>();
        programDescription = "";
    }
	
	public void addArgument(String argName, String argDescription, Types type)
	{
		argumentNames.add(new ArgumentInformation(argName, argDescription, type));
	}
    
    public int getNumberOfArguments()
    {
        return argumentNames.size();
    }
	
	public String getArgumentName(int p)
	{
		return argumentNames.get(p).getName();
	}
    
    public String getArgumentDescription(int p)
    {
        return argumentNames.get(p).getDescription();
    }
    
    public Types getArgumentType(int p)
    {
        return argumentNames.get(p).getType();
    }
	
	public void parse(String[] args) throws IncorrectNumberOfArgumentsException
	{
		getHelp(args);
		
		if(args.length < getNumberOfArguments())
		{
			throw new IncorrectNumberOfArgumentsException("\n\nToo few arguments.\n\n");
		}

		else if(args.length > getNumberOfArguments())
		{
			throw new IncorrectNumberOfArgumentsException("\n\nToo many arguments.\n\n");
		}        
		
		for(int i = 0; i < args.length; i++)
		{
            switch(getArgumentType(i))
            {
                case INTEGER:
                    argumentValues.add(Integer.parseInt(args[i]));
                    break;
                case FLOAT:
                    argumentValues.add(Float.parseFloat(args[i]));
                    break;
                case BOOLEAN:
                    argumentValues.add(Boolean.parseBoolean(args[i]));
                    break;
                default:
                    argumentValues.add(args[i]);
                    break;
            }
		}
	}
    
    public void getHelp(String[] args)
    {
        boolean isHelpNeeded = false;
        
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-h")){
                isHelpNeeded = true;
            }
        }
        if(isHelpNeeded){
            System.out.println(programDescription);
            for(int i = 0; i < argumentNames.size(); i++){
                System.out.println(argumentNames.get(i).getName() + "\t" + argumentNames.get(i).getDescription());
            }
            System.exit(0);
        }
    }
	
    @SuppressWarnings("unchecked") //talk to Dr. Garrett about this
	public <T> T getValueOf(String argName)
	{
		for(int i = 0; i < argumentNames.size(); i++)
		{
			if(argumentNames.get(i).getName() == argName)
			{
				return (T)argumentValues.get(i);
			}
		}
        return (T)"Unknown Label";
	}

    public void setProgramDescription(String programDescription)
    {
        this.programDescription = programDescription;
    }
    
    public String getProgramDescription()
    {
        return programDescription;
    }
    
    public static void main(String[] args)
	{
		ArgumentParser p = new ArgumentParser();
        
        p.addArgument("Length", "The length of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Width", "The width of the box", ArgumentParser.Types.INTEGER);
        p.addArgument("Height", "The height of the box", ArgumentParser.Types.INTEGER);
		
		p.parse(args);
		
		String l = p.getValueOf("Length");
		String w = p.getValueOf("Width");
		String h = p.getValueOf("Height");

        System.out.println(p.getArgumentName(0) + " is " + l);
		System.out.println(p.getArgumentName(1) + " is " + w);
		System.out.println(p.getArgumentName(2) + " is " + h);		
    }
    
    private class ArgumentInformation
    {
        String name, description;
        Types type;
        
        private ArgumentInformation(String name, String description, Types type)
        {
            this.name = name;
            this.description = description;
            this.type = type;
        }
        
        private String getName()
        {
            return name;
        }
        
        private String getDescription()
        {
            return description;
        }
        
        private Types getType()
        {
            return type;
        }
    }
}