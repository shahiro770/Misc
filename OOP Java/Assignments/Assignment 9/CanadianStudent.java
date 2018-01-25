public abstract class CanadianStudent extends Student {

	public CanadianStudent(String studentName, int numberOfCoursesTaken) {
		super(studentName, numberOfCoursesTaken);
	}
	
	@Override
	public String findCountry() {
		return "Canada";
	}
	
}