/*
 * Shahir Chowdhury
 * 2017-06-15
 * Fruit.java
 *
 * This class creates a Fruit object, a child class of Food. It contains all information relevant to Food as well as details
 * on the composition of its calories
*/

public class Fruit extends Food{
	//sets name, unit, price, stock, and caloriesPerUnit
	public Fruit(String name, String unit, double price, double stock, int caloriesPerUnit){
		super(name, unit, price, stock, caloriesPerUnit);
	}

	//returns the percentage of the food that is fat
	public double getFat(){
		return 0;
	}

	//returns the percentage of the food that is sugar
	public double getSugar(){
		return 1.0;
	}	

	//returns the percentage of the food that is protein
	public double getProtein(){
		return 0;
	}

	//displays the relevant information of the Food object in a nice format
	@Override
	public String toString(){
		String result;
		
		result = "Fruit: " + super.toString();

		return result;
	}
}