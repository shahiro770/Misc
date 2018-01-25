/*
 * Shahir Chowdhury
 * 2016-05-29
 * MyDate.java
 *
 * This program creates a MyDate object that stores a given date and various comparison methods with other MyDate objects.
 * It assumes the date given is always in the valid form of DD/MM/YYYY.
*/

import java.util.*;

public class MyDate{
	private int day;
	private int month;
	private int year;

	//no argument constructor defaults to 0 for all values
	public MyDate(){	
		this("00/00/0000");
	}

	//sets the day, month, and year
	public MyDate(String date){
 		StringTokenizer st;

 		st = new StringTokenizer(date,"/");	//tokenizer will always result in 3 tokens 

		this.day = Integer.parseInt(st.nextToken());
		this.month = Integer.parseInt(st.nextToken());
		this.year = Integer.parseInt(st.nextToken());	
	}

	//copy constructor
	public MyDate(MyDate other){	
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;
	}

	//returns a description of the date in the form of "Day Month, Year" with month being in word form instead of number
	public String toString(){
		String result = Integer.toString(day) + " ";
		switch (month){
			case 1:
				result += "January, ";
				break;
			case 2:
				result += "February, ";
				break;
			case 3:
				result += "March, ";
				break;
			case 4:
				result += "April, ";
				break;
			case 5:
				result += "May, ";
				break;
			case 6:
				result += "June, ";
				break;
			case 7:
				result += "July, ";
				break;
			case 8:
				result += "August, ";
				break;
			case 9:
				result += "September, ";
				break;
			case 10:
				result += "October, ";
				break;
			case 11:
				result += "November, ";
				break;
			case 12:
				result += "December, ";
				break;
		}
		result += Integer.toString((year / 10 % 10)) + Integer.toString((year % 10));

		return result;
	}

	//checks if two dates have the same day, month, and year values
	public boolean equals(MyDate other){
 		if (other.year == year){
 			if (other.month == month){
 				if (other.day == day){
 					return true;
 				}
 			}
 		}

		return false;
	}

	//checks if the current date is younger (less than) a given date
	public boolean lessThan(MyDate other){	
 		if (other.year > year){
 			return true;
 		}
 		else if (other.year == year){
 			if (other.month > month){
 				return true;
 			}
 			else if (other.month == month){
 				if (other.day > day){
 					return true;
 				}
 			}
 		}

 		return false;
	}
}