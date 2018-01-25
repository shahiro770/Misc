/*
 * Shahir Chowdhury
 * 2016-05-25
 * Lab2a.java
 *
 * This program takes in a sentence from the user and prints each word on a separate line, finally printing the number of vowels in the longest word
*/

import java.util.*;

public class Lab2a{
	public static void main(String[] args){
		String curr;			//current token being analyzed
		String input;	
		String longest = "";	//longest word in sentence
		int vowels = 0;			//number of vowels in longest word
		StringTokenizer st;

		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter a sentence:");
		input = kb.nextLine();

		st = new StringTokenizer(input);
		while (st.hasMoreTokens()){
			curr = st.nextToken();
			System.out.println(curr);
			if (curr.length() > longest.length()){	//change longest recorded word if the current token is longer, ignoring ties with future tokens
				longest = curr;						
			}
		}
		System.out.println("");

		for (int i=0;i < longest.length(); i++){ 	//Count the number of vowels contained inside the longest word in the sentence
			String let = longest.substring(i,i + 1);
			if (let.equals("a") || let.equals("e") || let.equals("i") || let.equals("o") || let.equals("u")){ 
				vowels++;
			}
		}
		System.out.printf("%s has %d vowel(s)\n", longest, vowels); 

		kb.close();

		return;
	}
}