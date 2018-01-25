/*
 * Shahir Chowdhury
 * 2017-06-31
 * CanadianStudentUnder65.java
 *
 * This program creates a CanadianStudentUnder65 object. It possesses all the functionality of a CanadianStudent with the additional
 * ability to compute its university fees and compare itself to other subclasses of Student.
*/

public class CanadianStudentUnder65 extends CanadianStudent{

	//sets studentName and numberOfCoursesTaken
	public CanadianStudentUnder65(String name, int courseNum){
		super(name, courseNum);
	}

	//sets studentName and numberOfCoursesTaken, with numberOfCoursesTaken defaulting to 5 in the absence of an inputted value
	public CanadianStudentUnder65(String name){
		super(name, 5);
	} 

	//returns the total cost of courses the Student is taking
	@Override
	public double computeFees(){
		int fee = 0;

		if (numberOfCoursesTaken >= 4){
			fee += 800;
		}
		else{
			fee += 200 * numberOfCoursesTaken;
		}

		return fee;
	}

	/*compares this Student with another Student type object, determining which is greater through a preset key 
	being CanadianStudentUnder65 > ForeignStudent > SeniorStudent*/
	//assumes that anObject will be of type SeniorStudent, ForeignStudent, or CanadianStudentUnder65
	@Override
	public boolean lessThan(Sortable anObject){
		if ((anObject instanceof SeniorStudent) || (anObject instanceof ForeignStudent)){	//CanadianStudentUnder65 is the highest valued
			return false;
		}
		//compare the names of each CanadianStudentUnder65 lexographically if the other Student is of type CanadianStudentUnder65
		//return true if this Student has a name lexographically less than the other Student
		else if (anObject instanceof CanadianStudentUnder65){				
			CanadianStudentUnder65 otherStudent = (CanadianStudentUnder65)anObject;
			if (this.studentName.compareTo(otherStudent.studentName) < 0){	 
				return true;
			}
		}

		return false;	//this Student is a higher value than whatever it is being compared with
	}
}	

