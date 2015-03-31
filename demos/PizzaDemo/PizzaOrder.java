import edu.jsu.mcis.*;

public class PizzaOrder{		
	public static void main(String[] args){
		
		ArgumentParser p = new ArgumentParser();
		
		p.setProgramDescription("Pizza Creator");
		
		p.addPositionalArgument("Size", "The size of the pizza (Small, Medium, or Large)", ArgumentParser.Types.STRING);
		p.addPositionalArgument("Crust", "The style of pizza crust", ArgumentParser.Types.STRING);
		p.addPositionalArgument("Toppings", "The number of toppings for the pizza", ArgumentParser.Types.INTEGER);
		p.addNamedArgument("quantity", "Allows you to order multiple of the same pizza", ArgumentParser.Types.INTEGER, 1);
		p.addNamedArgument("drink", "The drink you will have with your pizza", ArgumentParser.Types.STRING, "No Drink");
        p.addNamedArgument("togo", "Is the order to go?", ArgumentParser.Types.BOOLEAN, true);
		
		p.parse(args);
        
        boolean toGo = p.getValueOf("togo");
        
		System.out.println("\n\nYou have ordered "  + p.getValueOf("quantity")
                            + " " + p.getValueOf("Size") + " pizza(s) with " + p.getValueOf("Crust")
                            + " crust and " + p.getValueOf("Toppings") + " topping(s)");
		System.out.println("Drink: " + p.getValueOf("drink"));
        if(toGo){
            System.out.println("This order is to-go");
        }else{
            System.out.println("This order is for here");
        }
	}
}