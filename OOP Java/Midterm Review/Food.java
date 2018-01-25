import java.util.*;

public class Food{
	private int calories;
	private int taste;
	private static int yummy;

	public Food(int taste, int yummy, int calories){
		this.calories = calories;
		this.taste = taste;
		this.yummy = yummy;
	}

	public int getCalories(){
		return calories;
	}
	public static void setYummy(int amount){
		yummy += amount;
	}
	public int getYummy(){
		return yummy;
	}
}