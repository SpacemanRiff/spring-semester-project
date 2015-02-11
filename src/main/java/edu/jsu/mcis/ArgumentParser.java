package edu.jsu.mcis;

import java.util.ArrayList;

public class ArgumentParser
{
    private ArrayList<String> argumentNames;
    private ArrayList<String> argumentValues;
    private String description;
	
	public ArgumentParser()
	{
		argumentNames = new ArrayList<String>();
        argumentValues = new ArrayList<String>();
    }
	
	public String getArgumentName(int p)
	{
		return argumentNames.get(p);
	}
	
	public void parse(String[] args)
	{
		for(int i = 0; i < args.length; i++)
		{
			argumentValues.add(args[i]);
		}
	}
	
	public void addArgumentName(String newArgName)
	{
		argumentNames.add(newArgName);
	}
	
	public String getValueOf(String argName)
	{
		for(int i = 0; i < argumentNames.size(); i++)
		{
			if(argumentNames.get(i) == argName)
			{
				return argumentValues.get(i);
			}
		}
		return "Unknown Label";
	}
    
    public void addArgumentNames(String [] names)
    {
        for(int i = 0; i < names.length; i++)
        {
            argumentNames.add(names[i]);
        }
    }
    
    public int getNumberOfArguments()
    {
        return argumentNames.size();
    }
    
    public void lookForHelp(String[] args)
    {
        boolean isHelpNeeded = false;
        
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-h")){
                isHelpNeeded = true;
            }
        }
        if(isHelpNeeded){
            for(int i = 0; i < argumentNames.size(); i++){
                System.out.println(argumentNames.get(i));
            }
        }
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public static void main(String[] args)
	{
		ArgumentParser p = new ArgumentParser();
		
		p.addArgumentName("Length");
		p.addArgumentName("Width");
		p.addArgumentName("Height");
		
		p.parse(args);
		
		String l = p.getValueOf("Length");
		String w = p.getValueOf("Width");
		String h = p.getValueOf("Height");

        System.out.println(p.getArgumentName(0) + " is " + l);
		System.out.println(p.getArgumentName(1) + " is " + w);
		System.out.println(p.getArgumentName(2) + " is " + h);
		
    }
}