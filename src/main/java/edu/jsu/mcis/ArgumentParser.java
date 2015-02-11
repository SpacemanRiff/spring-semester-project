package edu.jsu.mcis;

import java.util.ArrayList;

public class ArgumentParser
{
    private ArrayList<String> argumentNames;
    private ArrayList<String> argumentValues;
	
	
	public ArgumentParser()
	{
		argumentNames = new ArrayList<String>();
        argumentValues = new ArrayList<String>();
    }
	
	public String getName(int p)
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
	
	public void addArg(String newArgName)
	{
		argumentNames.add(newArgName);
	}
	
	public String getValue(String argName)
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
    
    public static void main(String[] args)
	{
		ArgumentParser p = new ArgumentParser();
		
		p.addArg("Length");
		p.addArg("Width");
		p.addArg("Height");
		
		p.parse(args);
		
		String l = p.getValue("Length");
		String w = p.getValue("Width");
		String h = p.getValue("Height");

        System.out.println(p.getName(0) + " is " + l);
		System.out.println(p.getName(1) + " is " + w);
		System.out.println(p.getName(2) + " is " + h);
		
    }
}