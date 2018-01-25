/*
 * Shahir Chowdhury
 * 2017-06-15
 * Food.java
 *
 * This programs holds the abstract class Food. It holds various information of the food object, such as its name, price, the unit
 * to describe it with (dozen, kg, etc.), the amount of it in stock and more. 
*/

public abstract class Food{
	private String name;
	private String unit;
	private double price;
	private double stock;
	private int caloriesPerUnit;

	//sets name, unit, price, stock, and caloriesPerUnit
	public Food(String name, String unit, double price, double stock, int caloriesPerUnit){
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.stock = stock;
		this.caloriesPerUnit = caloriesPerUnit;
	}

	//abstract methods for child classes that possess fat, sugar and/or protein
	public abstract double getFat();
	public abstract double getSugar();
	public abstract double getProtein();

	//accessor for name
	public String getName(){
		return name;
	}

	//accessor for price
	public double getPrice(){
		return price;
	}	

	//accessor for caloriesPerUnit
	public int getCaloriesPerUnit(){
		return caloriesPerUnit;
	}

	//decreases the stock if there is enough of the food in stock 
	public boolean decreaseStock(double amount){
		if (amount <= stock){
			stock -= amount;
			return true;
		}

		return false;	//return false if there isn't enough stock to meet the amount demanded
	}

	//displays the relevant information of the Food object in a nice format
	@Override
	public String toString(){
		String result;

		result = name + ", $" + price + "/" + unit + ", " + stock + " " + unit + " in stock";

		return result;  
	}
}