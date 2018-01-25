/*
 * Shahir Chowdhury
 * 2017-06-01
 * Name.java
 *
 * This program creates a Name object that stores a first, middle, and last name.
*/

import java.util.*;

public class Name{
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