/*
 * Shahir Chowdhury
 * 2017-06-15
 * Carbohydrate.java
 *
 * This class creates a Carbohydrate object, a child class of Food. It contains all information relevant to Food as well as details
 * on the composition of its calories
*/

public class Carbohydrate extends Food{
	//sets name, unit, price, stock, and caloriesPerUnit
	public Carbohydrate(String name, String unit, double price, double stock, int caloriesPerUnit){
		super(name, unit, price, stock, caloriesPerUnit);
	}

	//returns the percentage of the food that is fat
	public double getFat(){
		return 0.2;
	}

	//returns the percentage of the food that is sugar
	public double getSugar(){
		return 0.6;
	}

	//returns the percentage of the food that is protein
	public double getProtein(){
		return 0.2;
	}
	
	//displays the relevant information of the Food object in a nice format
	@Override
	public String toString(){
		String result;
		
		result = "Carbohydrate: " + super.toString();

		return result;
	}
}