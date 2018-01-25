/*
 * Shahir Chowdhury
 * 2017-06-15
 * Student.java
 *
 * This program creates a Student object. Students retain all the functionality of a Person in addition to possessing a major.
*/

public class Student extends Person{
	private String major;

	//sets name and major
	//name is assumed to be in the format of "firstName middleName lastName" with middleName being optional
	public Student(String name, String major){
		super(name);		
		this.major = major;	
	}

	//Accessor for the student's assumed salary (students are broke)
	@Override	 
	public double getSalary(){
		return 0;
	}

	//Displays all information relevant to the student, including their details as a student
	@Override
	public String toString(){
		String result = "";

		result += super.toString();
		result += "\nMajor: " + major + "\n";

		return result;
	}
}