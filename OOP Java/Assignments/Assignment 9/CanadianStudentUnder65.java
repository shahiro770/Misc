public class CanadianStudentUnder65 extends CanadianStudent {

	public CanadianStudentUnder65(String studentName, int numberOfCoursesTaken) {
		super(studentName, numberOfCoursesTaken);
	}
	
	public CanadianStudentUnder65(String studentName) {
		this(studentName, 5);
	}

	@Override
	public double computeFees() {
		if(numberOfCoursesTaken >= 4) {
			return 800.0;
		} else {
			return numberOfCoursesTaken * 200.0;
		}
	}
}