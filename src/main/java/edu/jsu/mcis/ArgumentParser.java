package edu.jsu.mcis;

import java.util.ArrayList;

public class ArgumentParser
{
    private ArrayList<ArgumentInformation> argumentNames;
    private ArrayList<String> argumentValues;
    private String programDescription;
	
	public ArgumentParser()
	{
		argumentNames = new ArrayList<ArgumentInformation>();
        argumentValues = new ArrayList<String>();
        programDescription = "";
    }
	
	public void addArgument(String argName, String argDescription)
	{
		argumentNames.add(new ArgumentInformation(argName,argDescription));
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
	
	public void parse(String[] args)
	{
        lookForHelp(args);
		for(int i = 0; i < args.length; i++)
		{
			argumentValues.add(args[i]);
		}
	}
    
    private void lookForHelp(String[] args)
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
	
	public String getValueOf(String argName)
	{
		for(int i = 0; i < argumentNames.size(); i++)
		{
			if(argumentNames.get(i).getName() == argName)
			{
				return argumentValues.get(i);
			}
		}
		return "Unknown Label";
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
        
        p.addArgument("Length", "The length of the box");
        p.addArgument("Width", "The width of the box");
        p.addArgument("Height", "The height of the box");
		
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
        private ArgumentInformation(String name, String description)
        {
            this.name = name;
            this.description = description;
        }
        
        private String getName()
        {
            return name;
        }
        
        private String getDescription()
        {
            return description;
        }
    }
}