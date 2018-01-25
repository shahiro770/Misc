//InterpolationSearch.java
/*
	Best Case O(1)
	Worst Case O(n)
	Average Case O(log(logn))
	Notes:  Only works for sorted lists (e.g. ascending)
			List is assumed to be uniform to get maximum performance (i.e. not skewed)
*/

import java.util.*;

public class InterpolationSearch{
	public static void main (String[] args){
		int size = 10;
		int high = size - 1;
		int low = 0;
		int mid = -1;
		int array[] = new int[size];
		//int array[] = {3, 7, 9, 13 ,17, 21, 33 , 41, 47, 51};
		boolean found = false;

		for (int i = 0;i < size;i++){
			array[i] = i;
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
		int target = (int)(Math.random() * (size + 3));
		//int target = 7;
		System.out.println("Looking for " + target);
		if ((array[low] <= target) && (array[high] >= target)){
			while (low <= high){
				mid = low + (int)(((float)(high - low) / (float)(array[high] - array[low])) * (target - array[low]));
				System.out.println(mid);
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
		}

		if (found == true){
			System.out.printf("Found it at %d\n", mid);
		}
		else{
			System.out.printf("Couldn't find %d\n", target);
		}
	}
}