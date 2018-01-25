/*
 * Shahir Chowdhury
 * 2016-05-25
 * Assign2a.java
 *
 * This program takes in a set of grades (in either number or fraction form) and outputs the average of the marks assuming all inputs are valid
*/
import java.util.*;

public class Assign2a{
	public static void main(String[] args){
		String grades;	
		String curr;		//currently analyzed token
		StringTokenizer st;
		double tot = 0;		//average mark
		double num, den; 	//numerator and denominator for marks in fraction form
		int markNum = 0;	//number of marks
		Boolean fracFlag = false;	//flag for if a mark in fraction form is given

		Scanner kb = new Scanner(System.in);
		System.out.println("Enter a set of grades:");
		grades = kb.nextLine();

		st = new StringTokenizer(grades, " ;()");
		while (st.hasMoreTokens()){
			curr = st.nextToken();
			fracFlag = false;
			markNum += 1;

			for (int i=0;i<curr.length();i++){
				if (curr.substring(i,i+1).equals("/")){	//divide fraction if a fraction is given
					fracFlag = true;
					num = Double.parseDouble(curr.substring(0,i));
					den = Double.parseDouble(curr.substring(i+1, curr.length()));
					tot += ((num / den) * 100); 
					break;
				}	
			}
			if (fracFlag == false){
				tot += Double.parseDouble(curr);
			}
		}

		System.out.printf("The average is %.1f\n", tot / markNum);
		kb.close();

		return;
	}
}