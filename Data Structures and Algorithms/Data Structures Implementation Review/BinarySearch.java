//BinarySearch.java
/*
	Best Case O(1)
	Worst Case O(log(base 2)n)
	Average Case O(log(base 2)n)
	Notes:  Only works for sorted lists (e.g. ascending)
			Fails if low + high exceeds the size of the data type (e.g. integer)
*/

import java.util.*;

public class BinarySearch{
	public static void main (String[] args){
		int size = 10;
		int high = size - 1;
		int low = 0;
		int mid = -1;
		int array[] = new int[size];
		boolean found = false;

		for (int i = 0;i < size;i++){
			array[i] = i;
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
		int target = (int)(Math.random() * (size + 3));
		System.out.println("Looking for " + target);

		while (low <= high){
			mid = (high + low) / 2;
			//System.out.println(mid);
			if (array[mid] == target){
				found = true;
				break;
			}
			else if (array[mid] < target){ //shift the lowpoint up if the value found is less than the target
				low = mid + 1;
			}
			else if (array[mid] > target){ //shift the highpoint down if the value found is greater than the target
				high = mid - 1;
			}
		}

		if (found == true){
			System.out.printf("Found it at %d\n", mid);
		}
		else{
			System.out.printf("Couldn't find %d\n", target);
		}
	}
}