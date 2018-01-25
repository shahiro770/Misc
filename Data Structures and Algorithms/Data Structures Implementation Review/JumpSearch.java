//JumpSearch.java
/*
	Best Case O(1)
	Worst Case O(square root n)
	Average Case O(square root n)
	Notes: Only works for sorted lists (e.g. ascending)
		   For max efficiency, do jump search recursively
*/

import java.util.*;

public class JumpSearch{
	public static void main (String[] args){
		int size = 9;
		int array[] = new int[size];
		int jump = (int)Math.sqrt(size);
		int index = -1;
		int left = 0;
		int right = 0;
		boolean found = false;

		for (int i = 0;i < size;i++){
			array[i] = i;
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
		int target = (int)(Math.random() * (size + 3));
		System.out.println("Looking for " + target);

		right = Math.min(array.length - 1, left + jump);	//the upper bound of the jump region we might inspect

		while (left < array.length && array[left] < target){
			if (array[left] <= target && array[right] >= target){
				break;
			}
			left += jump;
			right = Math.min(array.length - 1, left + jump);//move the upper bound up with the lower bound
		}

		if (left >= array.length || array[left] > target){
			found = false;
		}

		else{
			for (int i = left;i <= right;i++){
				if (array[i] == target){
					index = i;
					found = true;
					break;
				}
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