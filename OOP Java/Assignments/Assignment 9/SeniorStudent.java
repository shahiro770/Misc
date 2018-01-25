public class SeniorStudent extends CanadianStudent {

	private double pension;
	
	public SeniorStudent(String studentName, int numberOfCoursesTaken, double pension) {
		super(studentName, numberOfCoursesTaken);
		this.pension = pension;
	}

	@Override
	public double computeFees() {
		return 50.0;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", senior citizen who gets pension $" + pension;
	}
	
	
	
}