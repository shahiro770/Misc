import java.util.*;

public class Potato extends Food implements CanSpice{
	public Potato(int taste, int yummy, int calories){
		super (taste, yummy, calories);
	}
	public Potato(){
		this(10,10,10);
	}
	public void spiceIt(int howHot){
		setYummy(howHot);
	}
	@Override
	public String toString(){
		return "Yummy: " + getYummy() + " Calories: " + getCalories();
	}


	public static void main(String[] args){
		Potato pot = new Potato(10,10,10);
		Food food; //= new Food(20,20,20);
		food = pot;

		System.out.println(food);

		if (food instanceof Potato){
			((Potato)food).spiceIt(10);
		}

		System.out.println(food);
		setYummy(10);
	}
}