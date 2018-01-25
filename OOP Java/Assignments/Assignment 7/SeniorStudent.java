/*
 * Shahir Chowdhury
 * 2017-06-31
 * SeniorStudent.java
 *
 * This program creates a SeniorStudent object. It possess all the functionality of a CanadianStudent with the additional
 * ability to compute its university fees and compare itself to other subclasses of Student.
*/

public class SeniorStudent extends CanadianStudent{
	private double pension;

	//sets studentName, numberOfCoursesTaken, and pension
	public SeniorStudent(String name, int courseNum, double pension){
		super(name, courseNum);
		this.pension = pension;
	}

	//returns the fee that SeniorStudent pays for courses, which is locked at 50
	@Override
	public double computeFees(){
		return 50;
	}

	/*compares this Student with another Student type object, determining which is greater through a preset key 
	being CanadianStudentUnder65 > ForeignStudent > SeniorStudent*/
	//assumes that anObject will be of type SeniorStudent, ForeignStudent, or CanadianStudentUnder65
	@Override
	public boolean lessThan(Sortable anObject){
		if ((anObject instanceof CanadianStudentUnder65) || (anObject instanceof ForeignStudent)){	//SeniorStudent is the least valued
			return true;
		}
		//compare the names of each SeniorStudent lexographically if the other Student is of type SeniorStudent
		//return true if this Student has a name lexographically less than the other Student
		else if (anObject instanceof SeniorStudent){
			SeniorStudent otherStudent = (SeniorStudent)anObject;
			if (this.studentName.compareTo(otherStudent.studentName) < 0){
				return true;
			}
		}

		return false;	//this Student is a higher value than whatever it is being compared with
	}

	//overrides Student's toString to include details regarding the SeniorStudent's pension
	@Override 
	public String toString(){
		return super.toString() + ", senior citizen who gets pension $" + pension; 
	}
}	

