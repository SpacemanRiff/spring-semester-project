import edu.jsu.mcis.*;

public class PizzaOrderFromXML{		
    public static void main(String[] args){
        ArgumentParser p = new ArgumentParser();
        XMLManager manager = new XMLManager();
		
        p.setProgramDescription("Pizza Creator");
        
        manager.loadArguments("PizzaArguments.xml", p); 
		
        p.parse(args);
        
		System.out.println("\nYou have ordered "  + p.getValueOf("quantity")
                            + " " + p.getValueOf("size") + " pizza(s) with " + p.getValueOf("crust")
                            + " crust and " + p.getValueOf("toppings") + " topping(s)");
		System.out.println("Drink: " + p.getValueOf("drink"));
	}
}

