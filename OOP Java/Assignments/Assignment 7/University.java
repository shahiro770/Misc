/*
 * Shahir Chowdhury
 * 2017-06-30
 * University.java
 *
 * This program creates a University object. The University holds the information for all of its attending students, allowing for new
 * students to be added and for their information to be viewed.
*/

public class University{
	private int maxNumOfStuds;		//maximum number of students allowed at the university
	private int totNumOfStuds = 0;	//current number of students attending the university
	private Student[] students;		//array holding all of the students at the university

	//sets the maximum number of students and an array of the corresponding size to hold them
	public University(int maxNumOfStuds){
		this.maxNumOfStuds = maxNumOfStuds;
		students = new Student[maxNumOfStuds];
	}

	//adds a new student to the university
	public boolean insertStudent(Student newGuy){
		for (int i = 0;i < maxNumOfStuds;i++){
			if (students[i] == null){
				students[i] = newGuy;
				totNumOfStuds++;
				return true;	//return true if the student was successfully added
			}
		}

		return false;	//student could not be added, array was full
	}

	//gets the number of students attending the university coming from a specific country
	public int numberOfStudents(String country){
		int reps = 0;	//number of students from the specified country contained in the students array

		for (int i = 0;i < maxNumOfStuds;i++){
			if (students[i] != null){
				if (students[i].findCountry().equals(country)){
					reps++;
				}
			}
		}

		return reps;
	}

	//sorts the students in the University by their type (Sorting key is CanadianStudentUnder65 > ForeignStudent > SeniorStudent)
	//students with the same class are ordered by their names from least to greatest in a lexographical ordering
	public void sortStudents(boolean isDescending){
		Sort studentSorter = new Sort();
		studentSorter.sortAnything(students, totNumOfStuds, isDescending);
	}

	//displays the information of every student contained inside the students array
	@Override
	public String toString(){
		String result = "";

		result += "Number of students in university = " + totNumOfStuds + "\n";
		for (int i = 0;i < maxNumOfStuds;i++){
			if (students[i] != null){
				result += students[i].toString() + "\n";
			}
		}

		return result;
	}


}