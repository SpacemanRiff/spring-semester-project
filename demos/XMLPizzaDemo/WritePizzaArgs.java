import edu.jsu.mcis.*;

public class WritePizzaArgs{		
	public static void main(String[] args){
		
		ArgumentParser p = new ArgumentParser();
		
		p.setProgramDescription("Pizza Creator");
		
		p.addArgument("size", "The size of the pizza (Small, Medium, or Large)", ArgumentParser.Types.STRING);
		p.addArgument("crust", "The style of pizza crust", ArgumentParser.Types.STRING);
		p.addArgument("toppings", "The number of toppings for the pizza", ArgumentParser.Types.INTEGER);
		p.addOptionalArgument("quantity", "Allows you to order multiple of the same pizza", ArgumentParser.Types.INTEGER, 1);
		p.addOptionalArgument("drink", "The drink you will have with your pizza", ArgumentParser.Types.STRING, "No Drink");
		
		p.parse(args);
        
        XMLManager.writeArguments("PizzaArguments.xml", p);
        
	}
}