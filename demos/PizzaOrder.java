import edu.jsu.mcis.*;

public class PizzaOrder{		
	public static void main(String[] args){
		
		ArgumentParser p = new ArgumentParser();
		
		p.setProgramDescription("Pizza Creator");
		
		p.addArgument("Size", "The size of the pizza (Small, Medium, or Large)", ArgumentParser.Types.STRING);
		p.addArgument("Crust", "The style of pizza crust", ArgumentParser.Types.STRING);
		p.addArgument("Toppings", "The number of toppings for the pizza", ArgumentParser.Types.INTEGER);
		p.addOptionalArgument("Quantity", "Allows you to order multiple of the same pizza", ArgumentParser.Types.INTEGER, 1);
		p.addOptionalArgument("Drink", "The drink you will have with your pizza", ArgumentParser.Types.STRING, "No Drink");
        p.addOptionalFlag("Togo", "Is the order to go?");
		
		p.parse(args);
        
        boolean toGo = p.getOptionalArgumentValueOf("Togo");
        
		System.out.println("\n\nYou have ordered "  + p.getOptionalArgumentValueOf("Quantity")
                            + " " + p.getValueOf("Size") + " pizza(s) with " + p.getValueOf("Crust")
                            + " crust and " + p.getValueOf("Toppings") + " topping(s)");
		System.out.println("Drink: " + p.getOptionalArgumentValueOf("Drink"));
        if(toGo){
            System.out.println("This order is to-go");
        }else{
            System.out.println("This order is for here");
        }
	}
}