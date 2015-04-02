import edu.jsu.mcis.*;

public class WritePizzaArgs{		
	public static void main(String[] args){
		
		ArgumentParser p = new ArgumentParser();
		
		p.setProgramDescription("Pizza Creator");
		
		p.addPositionalArgument("size", "The size of the pizza (Small, Medium, or Large)", ArgumentParser.Types.STRING);
		p.addPositionalArgument("crust", "The style of pizza crust", ArgumentParser.Types.STRING);
		p.addPositionalArgument("toppings", "The number of toppings for the pizza", ArgumentParser.Types.INTEGER);
		p.addNamedArgument("quantity", "Allows you to order multiple of the same pizza", ArgumentParser.Types.INTEGER, 1);
		p.addNamedArgument("drink", "The drink you will have with your pizza", ArgumentParser.Types.STRING, "No Drink");
		
		p.parse(args);
        
        XMLManager.writeArguments("PizzaArguments.xml", p);
        
	}
}