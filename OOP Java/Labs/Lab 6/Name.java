/*
 * Shahir Chowdhury
 * 2017-06-29
 * Name.java
 *
 * This program creates a Name object that stores a first, middle, and last name.
*/

import java.util.*;

public class Name implements Sortable{
	private String firstName;
	private String middleName;
	private String lastName;

	//sets firstName, and lastName, and if given, middleName
	//nameString is assumed to be in the format of "firstName middleName lastName" with middleName being optional
	public Name(String nameString){
		StringTokenizer st;

		st = new StringTokenizer(nameString);

		if (st.countTokens() == 3){
			firstName = st.nextToken();
			middleName = st.nextToken();
			lastName = st.nextToken();
		}
		else if (st.countTokens() == 2){
			firstName = st.nextToken();
			lastName = st.nextToken();
		}
	}

	//copy constructor
	public Name(Name otherName){
		if (otherName.firstName != null){
			this.firstName = otherName.firstName;
		}
		if (otherName.middleName != null){
			this.middleName = otherName.middleName;
		}
		if (otherName.lastName != null){
			this.lastName = otherName.lastName;
		}
	}

	//sets the firstName, middleName, and lastName 
	public void setName(String newFirst, String newMiddle, String newLast){
		firstName = newFirst;
		middleName = newMiddle;
		lastName = newLast;
	}

	//compares the values of the lastName of this Name object with another Name object, returning true if the
	//lastName value for the compared Name is lexographically greater than the lastName value for this Name
	public boolean lessThan(Object other){
		 if (other instanceof Name){
		 	Name otherName = (Name)other;
		 	if (this.lastName.compareTo(otherName.lastName) < 0){
		 		return true;
		 	}
		 	else if (this.lastName.equals(otherName.lastName)){	//use firstName as a tie breaker if both lastNames are equal
		 		if (this.firstName.compareTo(otherName.firstName) < 0){
		 			return true;
		 		}
		 	} 
		 }

		 return false;
	}

	//displays the full name in the format of "lastName, firstName initialOfMiddleName.", 
	@Override
	public String toString(){
		String result;

		result = lastName + ", ";
		result += firstName;
		if (middleName != null){ //only write the initial of the middle name if it was given
			result += " " + middleName.substring(0,1) + ".";
		}

		return result;
	}
}