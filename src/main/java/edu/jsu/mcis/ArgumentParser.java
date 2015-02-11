package edu.jsu.mcis;


public class ArgumentParser
{
	private String[] name;
	private String[] values;
	public int numberOfArgs;	
	
	public String getName(int p)
	{
		return name[p];
	}
	
	public ArgumentParser()
	{
		numberOfArgs = 3;
		
		name = new String[numberOfArgs];
		values = new String[numberOfArgs];
		
		for(int i = 0; i < numberOfArgs; i++)
		{
			name[i] = "";
		}
    }
	
	
	public void parse(String[] args)
	{
		for(int i = 0; i < numberOfArgs; i++)
		{
			values[i] = args[i];
		}
	}
	
	public void addArg(String newArgName)
	{
		for(int i = 0; i < numberOfArgs; i++)
		{
			if(name[i] == "")
			{
				name[i] = newArgName;
				break;
			}	
		}
	}
	
	public String getValue(String argName)
	{
		for(int i = 0; i < numberOfArgs; i++)
		{
			if(name[i] == argName)
			{
				return values[i];
			}
		}		
		return "Unknown Label";
	}
    
    public void addArgumentNames(String [] names){
        name = names;
    }
    
    public int getNumberOfArguments(){
        return numberOfArgs;
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