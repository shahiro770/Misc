/*
 * Shahir Chowdhury
 * 2016-05-25
 * Lab2b.java
 *
 * This program checks if a user inputted name is a valid name (given the format of "Salutation FirstName MiddleInitial LastName" where Salutation and 
 * MiddleInitial are optional) and then prints the first and last name if the name is valid. Otherwise, it kindly informs the user that the name given
 * was invalid.
*/

import java.util.*;

public class Lab2b{
	public static void main(String[] args){
		String curr;	//current token being analyzed
		String name;	//name inputted by user
		String regexPattern;
		StringTokenizer st;

		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter a name:");
		name = kb.nextLine();

		regexPattern = "^((Ms )|(Mr ))?[A-Z][a-z]* ([A-Z]\\.? )?[A-Z][a-z]*$";	//I knew someone whose last name was just a single letter 

		if (name.matches(regexPattern)){
			st = new StringTokenizer(name);
			curr = st.nextToken();
			if (curr.equals("Ms") || curr.equals("Mr")){	//ignore the salutation if given
				curr = st.nextToken();						//print the first name
			}
			System.out.printf("%s ", curr);
			while (st.countTokens() > 1){					//arrive at the last token to print the last name
				curr = st.nextToken();
			}
			curr = st.nextToken();
			System.out.printf("%s\n", curr);				
		}
		else{
			System.out.println("Invalid name.");
		}

		kb.close();

		return;
	}
}
