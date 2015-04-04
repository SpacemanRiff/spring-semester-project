import edu.jsu.mcis.*;

public class NumberCrunch{		
	public static void main(String[] args){
		
		ArgumentParser p = new ArgumentParser();
		
		p.setProgramDescription("Number Cruncher");
		
		p.addPositionalArgument("Number 1", "The first number you want to crunch", ArgumentParser.Types.INTEGER);
		p.addPositionalArgument("Number 2", "The price of the first item", ArgumentParser.Types.INTEGER);
		p.addNamedArgument("add", "a", "Adds the numbers", ArgumentParser.Types.BOOLEAN, false);
		p.addNamedArgument("sub", "s", "subtracts the numbers", ArgumentParser.Types.BOOLEAN, false);
        p.addNamedArgument("multiply", "m", "multiplies the numbers", ArgumentParser.Types.BOOLEAN, false);
		
        p.addArgumentToGroup("add", "Addition");
        p.addArgumentToGroup("sub", "Subtraction");
        p.addArgumentToGroup("multiply", "Multiplication");
        
		p.parse(args);
        
        boolean add = p.getValueOf("add");
        boolean sub = p.getValueOf("sub");
        boolean multiply = p.getValueOf("multiply");
        
        int num1 = p.getValueOf("Number 1");
        int num2 = p.getValueOf("Number 2");

		
		System.out.println("the answer is:\n");
		
        if(add){
            System.out.println(num1 + num2);
        }
        else if(sub){
            System.out.println(num1 - num2);
        }
        else if(multiply){
            System.out.println(num1 * num2);
        }
        else
        {
            System.out.println("no action selected. please consult --help");
        }
        
	}
}