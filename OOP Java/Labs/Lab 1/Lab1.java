/*
 * Shahir Chowdhury
 * 2017-05-08
 * Lab1.java
 *
 * This program displays the next largest prime number after a user inputted number
*/

import java.util.*;

public class Lab1{
	public static void main(String[] args){
		int in;					//inputted variable
		Boolean isPrime = true;	//flag for if the current 
		Scanner kb = new Scanner (System.in);

		System.out.println("Hello Shahir! I hope you are doing well today! Please input a number:");
		in = kb.nextInt();

		while (true){	//test each number after the inputted number for being a prime number
			in++;
			isPrime = true;
			for (int i = 2;i <= (Math.sqrt(in));i++){	//only have to check up to the square root of the current number being tested
				if (in % i == 0){
					isPrime = false;
					break;
				}
			}
			if (isPrime == true){
				break;
			}
		}

		if (in < 2){	//case for if number is less than the first prime number (2)
			System.out.println("The next prime is: 2");
		}
		else{
			System.out.printf("The next prime is: %d\n", in);
		}

		kb.close();

		return;
	}
}
