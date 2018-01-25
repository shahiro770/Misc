import java.util.*;

public class Course{
	private String courseString;
	private int courseNumber;
	private static int totalCourses = 1;
	private static int courseCSNumber;

	public Course(String inputCourse){
		this.courseString = inputCourse;
		this.courseNumber = totalCourses;
		totalCourses += 1;
		StringTokenizer st = new StringTokenizer(inputCourse, " -");

		st.nextToken();
		if (st.nextToken().equals("60")){
			courseCSNumber += 1;
		}
	}

	public static int getNumberOfComputerScienceCourses(){
		return courseCSNumber;
	}

	public String toString(){
		String result = "number ";
		result += courseNumber + ": ";
		result += courseString;

		return result;
	}

	public boolean isUndergraduateCourse(){
		StringTokenizer st = new StringTokenizer(courseString, " -");
		st.nextToken();
		st.nextToken();
		String year = st.nextToken().substring(0,1);
		if (year.equals("1") || year.equals("2") || year.equals("3") || year.equals("4")){
			return true;
		}

		return false;
	}

}