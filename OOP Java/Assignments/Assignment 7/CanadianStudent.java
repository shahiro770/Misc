/*
 * Shahir Chowdhury
 * 2017-06-31
 * CanadianStudent.java
 *
 * This program creates the abstract CanadianStudent object. It has all the functionality of a Student, with the addition of having
 * its country locked in as Canada.
*/

public abstract class CanadianStudent extends Student{

	//sets studentName and numberOfCoursesTaken
	public CanadianStudent(String name, int courseNum){
		super(name, courseNum);
	}

	//returns this Student's country of origin, which is set as "Canada"
	@Override
	public String findCountry(){
		return "Canada";
	}
}	