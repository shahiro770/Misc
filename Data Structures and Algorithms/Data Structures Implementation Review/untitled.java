//JumpSearch.java
/*
	Best Case O(1)
	Worst Case O(n)
	Average Case O(n/2)
	Notes: 
*/

import java.util.*;

public class JumpSearch{
	public static void main (String[] args){
		int size = 10;
		int array[] = new int[size];
		int jump = Math.sqrt(size);
		int index = -1;
		boolean found = false;

		for (int i = 0;i < size;i++){
			array[i] = (int)(Math.random() * size);
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
		int target = (int)(Math.random() * (size + 3));
		System.out.println("Looking for " + target);

		for (int i = 0;i < size;i++){
			if (array[i] == target){
				found = true;
				index = i;
				break;
			}
		}

		if (found == true){
			System.out.printf("Found it at %d\n", index);
		}
		else{
			System.out.printf("Couldn't find %d\n", target);
		}
	}
}