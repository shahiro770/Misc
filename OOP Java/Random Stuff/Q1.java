/*
 * Shahir Chowdhury
 * 2017-06-17
 * Q1.java
 *
 * This program uses recursion to search for a string within two other strings, taking the bits of those two strings before the 
 * the occurence of the first string and adding them to a fourth string. This process repeats until the two strings that are searched
 * are empty, returning the fourth string to the user.
*/
import java.util.*;

public class Q1{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input the first string: ");
		String s1 = sc.nextLine();
		System.out.print("Please input the second string: ");
		String s2 = sc.nextLine();
		System.out.print("Please input the substring to find: ");
		String s3 = sc.nextLine();
		
		System.out.println(recursion1(s1, s2, s3));
		
		sc.close();
	}

	//searches s1 and s2 for s3, returns a string that is the amalgamation of all the string bits before s3 in both s1 and s2
	public static String recursion1(String s1, String s2, String s3){
		String result = "";
		return recursion1(s1, s2, s3, result);
	}

	//recursive logic for determining the amalgamation of all the string bits before s3 in both s1 and s2
	public static String recursion1(String s1, String s2, String s3, String result){
		int posIn1;	//position of s3 in s1
		int posIn2;	//position of s3 in s2

		if (s1.equals("") && s2.equals("")){	//base case to stop recurring if s1 and s2 are empty
			return result;
		}
		else{
			posIn1 = s1.indexOf(s3);	
			posIn2 = s2.indexOf(s3);

			if (posIn1 != -1){		//only add the string bit before s3 if it is found in s1
				result += s1.substring(0, posIn1);
				s1 = s1.substring(posIn1 + s3.length());
			}
			else if (posIn1 == -1){	//add the remaining string if it does not contain s3
				result += s1.substring(0);
				s1 = "";
				posIn1 = 0;
			}
			if (posIn2 != -1){		//only add the string bit before s3 if it is found in s2
				result += s2.substring(0, posIn2);	
				s2 = s2.substring(posIn2 + s3.length());
			}
			else if (posIn2 == -1){	//add the remaining string if it does not contain s3
				result += s2.substring(0);
				s2 = "";
				posIn2 = 0;
			}

			return recursion1(s1, s2, s3, result);
		}
	}
}