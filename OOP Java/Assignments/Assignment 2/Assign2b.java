/*
 * Shahir Chowdhury
 * 2016-05-26
 * Assign2b.java
 *
 * This program tells the user if a user inputted date in the form of DD/MM/YY or DD-MM-YY is a valid date
*/

import java.util.*;

public class Assign2b{
	public static void main(String[] args){	
		String date = "";		//date inputted by user
		String regexPattern;
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a date in DD/MM/YY or DD-MM-YY format:");		
		date = kb.nextLine();

		//regexPattern handles all special day and month combinations (e.g. Feburary has maximum 29 days) and ensures consistent - or / formatting
		regexPattern = "^([0][1-9]-[0][1-9]-[0-9][0-9]|[1-2][0-9]-[0][1-9]-[0-9][0-9]|[3][0]-[0][13-9]-[0-9][0-9]|[3][1]-[0][13578]-[0-9][0-9]"  
					  + "|[0][1-9]-[0][1-9]-[0-9][0-9]|[1-2][0-9]-[1][0-2]-[0-9][0-9]|[3][0]-[1][0-2]-[0-9][0-9]|[3][1]-[1][02]-[0-9][0-9]" 
					  + "|[0][1-9]/[0][1-9]/[0-9][0-9]|[1-2][0-9]/[0][1-9]/[0-9][0-9]|[3][0]/[0][13-9]/[0-9][0-9]|[3][1]/[0][13578]/[0-9][0-9]" 	
					  + "|[0][1-9]/[0][1-9]/[0-9][0-9]|[1-2][0-9]/[1][0-2]/[0-9][0-9]|[3][0]/[1][0-2]/[0-9][0-9]|[3][1]/[1][02]/[0-9][0-9])$";
						

		if (date.matches(regexPattern)){
			System.out.println("That is a valid date.");
		}
		else{
			System.out.println("That is an invalid date.");
		}

		return;
	}
}