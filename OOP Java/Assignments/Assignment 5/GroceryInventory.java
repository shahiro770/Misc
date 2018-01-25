import java.util.Scanner;
import java.util.StringTokenizer;

public class GroceryInventory {

	public static final int maxFoodItems = 10;
	
	private Food[] foodItems = new Food[maxFoodItems]; // Keep track of the store's inventory
	private int totalFoodItems = 0;
	
	public static void main(String[] args) {
		
		// For input
		Scanner keyboard = new Scanner(System.in);
		String input;
		StringTokenizer inputTokenizer;
		
		// Stock up on items
		GroceryInventory inventory = new GroceryInventory();
		
		/************************************* Name       unit 		price 	stock 	caloriesPerUnit**/ 
		inventory.addFoodItem(new Fruit(       "Apple",   "kg",		1.89, 	200.35,	200));
		inventory.addFoodItem(new Fruit(       "Orange",  "dozen",	2.36,  	38,		300));
		inventory.addFoodItem(new Carbohydrate("Rice",    "kg", 	2.99,  	20,		450));
		inventory.addFoodItem(new MeatFish(    "Chicken", "kg",		5.49, 	538.32,	800));
		inventory.addFoodItem(new MeatFish(    "Lamb",    "kg",		4.32, 	134.62,	1200));
		inventory.addFoodItem(new MeatFish(    "Shrimp",  "kg",		9.65,  	74.52,	900));
		
		System.out.println("Inventory:");
		System.out.println("");
		inventory.printInventory();
		System.out.println("");		
		
		System.out.println("Enter <item> <amount> to buy");
		System.out.println("Type STOP to finish");
		System.out.println("");
		
		double totalPrice = 0;
		double totalFat   = 0;
		double totalSugar = 0;
		double totalProtein = 0;
		
		// Keep parsing through input
		while( !(input = keyboard.nextLine()).equals("STOP") ) {
			
			inputTokenizer = new StringTokenizer(input);
			
			String name = inputTokenizer.nextToken();
			double amount = Double.parseDouble(inputTokenizer.nextToken());
			
			// Check if it matches any of the items in the inventory
			for(int i = 0; i < inventory.totalFoodItems; i++) {
				if(inventory.foodItems[i].getName().equals(name)) {
					
					inventory.foodItems[i].decreaseStock(amount);
					
					totalPrice += amount * inventory.foodItems[i].getPrice();
					totalFat   += amount * inventory.foodItems[i].getFat() * inventory.foodItems[i].getCaloriesPerUnit();
					totalSugar += amount * inventory.foodItems[i].getSugar() * inventory.foodItems[i].getCaloriesPerUnit();
					totalProtein += amount * inventory.foodItems[i].getProtein() * inventory.foodItems[i].getCaloriesPerUnit();
					
					break;
				}				
			}
		}
		
		keyboard.close();
		
		// Print results
		System.out.println("Total price: $" + totalPrice);
		System.out.println("Total calories from fat:   " + totalFat + " calories");
		System.out.println("Total calories from sugar: " + totalSugar + " calories");
		System.out.println("Total calories from protein: " + totalProtein + " calories");
		
		System.out.println("");
		System.out.println("Inventory:");
		System.out.println("");
		inventory.printInventory();
	}
	
	
	
	// Add a food item to the inventory
	public boolean addFoodItem(Food f) {		
		// List is full
		if(totalFoodItems >= maxFoodItems) {
			return false;
		}
		
		foodItems[totalFoodItems] = f;
		totalFoodItems++;
		
		return true;		
	}
	
	public void printInventory() {
		for(int i = 0; i < totalFoodItems; i++) {
			System.out.println(foodItems[i].toString());
		}
	}
	
}