//package edu.jsu.mcis;


public class ArgumentParser
{
	public String[] labels;
	public String[] values;
	public int numberOfArgs;
	
	
	public String getLabel(int p)
	{
		return labels[p];
	}
	
	public ArgumentParser()
	{
		numberOfArgs = 3;
		
		labels = new String[numberOfArgs];
		values = new String[numberOfArgs];
		
		for(int i = 0; i < numberOfArgs; i++)
		{
			labels[i] = "";
		}
    }
	
	
	public void parse(String[] args)
	{
		for(int i = 0; i < numberOfArgs; i++)
		{
			values[i] = args[i];
		}
	}
	
	public void addArg(String newArgLabel)
	{
		for(int i = 0; i < numberOfArgs; i++)
		{
			if(labels[i] == "")
			{
				labels[i] = newArgLabel;
				break;
			}	
		}
	}
	
	public String get(String argLabel)
	{
		for(int i = 0; i < numberOfArgs; i++)
		{
			if(labels[i] == argLabel)
			{
				return values[i];
			}
		}
		
		return "Unknown Label";
	}
    
    public static void main(String[] args)
	{
		ArgumentParser p = new ArgumentParser();
		
		p.addArg("Length");
		p.addArg("Width");
		p.addArg("Height");
		
		p.parse(args);
		
		String l = p.get("Length");
		String w = p.get("Width");
		String h = p.get("Height");

        System.out.println(p.getLabel(0) + " is " + l);
		System.out.println(p.getLabel(1) + " is " + w);
		System.out.println(p.getLabel(2) + " is " + h);
		
    }
}