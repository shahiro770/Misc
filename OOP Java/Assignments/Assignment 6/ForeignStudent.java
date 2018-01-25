/*
 * Shahir Chowdhury
 * 2017-06-31
 * ForeignStudent.java
 *
 * This program creates a ForeignStudent object. It possess all the functionality of a Student with the additional
 * ability to compute its university fees and compare itself to other subclasses of Student.
*/

public class ForeignStudent extends Student{
	private String country;	//country of origin
	private MyDate entry;	//date of entry

	//sets studentName, numberOfCoursesTaken, country, and entry 
	public ForeignStudent(String name, int courseNum, String country, MyDate entry){
		super(name, courseNum);
		this.country = country;
		this.entry = entry;
	}

	//returns the cost for the courses the ForeignStudent is taking
	@Override
	public double computeFees(){
		return 1000 * numberOfCoursesTaken;
	}

	//returns the country of origin for the ForeignStudent
	@Override
	public String findCountry(){		
		return country;
	}

	/*compares this Student with another Student type object, determining which is greater through a preset key 
	being CanadianStudentUnder65 > ForeignStudent > SeniorStudent*/
	//assumes that anObject will be of type SeniorStudent, ForeignStudent, or CanadianStudentUnder65
	@Override
	public boolean lessThan (Sortable anObject){
		if (anObject instanceof SeniorStudent){	//ForeignStudent is higher than SeniorStudent
			return false;
		}
		else if (anObject instanceof CanadianStudentUnder65){ //ForeignStudent is lower than CanadianStudentUnder65
			return true;
		}
		//compare the names of each SeniorStudent lexographically if the other Student is of type SeniorStudent
		//return true if this Student has a name lexographically less than the other Student
		else if (anObject instanceof ForeignStudent){
			ForeignStudent otherStudent = (ForeignStudent)anObject;
			if (this.studentName.compareTo(otherStudent.studentName) < 0){
				return true;
			}
		}

		return false;	//this Student is a higher value than whatever it is being compared with
	}
}	

