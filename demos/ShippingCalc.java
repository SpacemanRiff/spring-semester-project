import edu.jsu.mcis.*;

public class ShippingCalc{		
	public static void main(String[] args){
		
		ArgumentParser p = new ArgumentParser();
		
		p.setProgramDescription("Shipping Order");
		
		p.addArgument("Item 1", "The item you want to ship", ArgumentParser.Types.STRING);
		p.addArgument("Price 1", "The price of the first item", ArgumentParser.Types.FLOAT);
		p.addArgument("Shipping Cost", "The cost to ship this item", ArgumentParser.Types.FLOAT);
		p.addOptionalArgument("quantity", "Allows you to order multiple of the same item", ArgumentParser.Types.INTEGER, 1);
		p.addOptionalArgument("weight", "The weight of the item", ArgumentParser.Types.STRING, "n/a");
        p.addOptionalFlag("local", "This item will be shipped locally");
		
		p.parse(args);
        
        boolean local = p.getValueOf("local");
        
		System.out.println("\n\nYou have requested to ship "  + p.getValueOf("quantity")
                            + " " + p.getValueOf("Item 1") + "(s)\nEach " + p.getValueOf("Item 1") + " costs $"
                            + p.getValueOf("Price 1") + "\nShipping cost is $" + p.getValueOf("Shipping Cost") 
							+ "\nThe weight of this item is: " + p.getValueOf("weight") + "lbs");
		
		float shipping = p.getValueOf("Shipping Cost");
		float price = p.getValueOf("Price 1");
		float total = shipping + price;
		
		System.out.println("\n\nTotal cost: $" + total);
		
        if(local){
            System.out.println("This will be shipped locally");
        }else{
            System.out.println("This will be shipped internationally");
        }
	}
}