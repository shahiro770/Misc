public class Assignment6Tester {
	public static void main(String a[]){
		University ourUniversity;
		Student aStudent;
		MyDate dateOfEntryToCanada;
		ourUniversity = new University(10);
		aStudent = new CanadianStudentUnder65("John");
		ourUniversity.insertStudent(aStudent);
		aStudent = new CanadianStudentUnder65("Mary", 3);
		ourUniversity.insertStudent(aStudent);
		aStudent = new SeniorStudent("Tom", 3, 1500.0);
		ourUniversity.insertStudent(aStudent);
		dateOfEntryToCanada = new MyDate("25/05/2009");
		aStudent = new ForeignStudent("Xiao", 3, "China", dateOfEntryToCanada);
		ourUniversity.insertStudent(aStudent);
		dateOfEntryToCanada = new MyDate("12/08/2013");
		aStudent = new ForeignStudent("Jagjit", 5, "India", dateOfEntryToCanada);
		ourUniversity.insertStudent(aStudent);
		dateOfEntryToCanada = new MyDate("05/04/2008");
		aStudent = new ForeignStudent("Liz", 3, "Ireland", dateOfEntryToCanada);
		ourUniversity.insertStudent(aStudent);
		dateOfEntryToCanada = new MyDate("13/11/2011");
		aStudent = new ForeignStudent("Hong", 4, "China", dateOfEntryToCanada);
		ourUniversity.insertStudent(aStudent);
		aStudent = new SeniorStudent("Pat", 2, 2000.00);
		ourUniversity.insertStudent(aStudent);
		dateOfEntryToCanada = new MyDate("21/09/2009");
		aStudent = new ForeignStudent("Ting", 5, "China", dateOfEntryToCanada);
		ourUniversity.insertStudent(aStudent);
		
		System.out.println(ourUniversity);
		System.out.println("Number of students from Canada is " + 
				ourUniversity. numberOfStudents("Canada"));
		System.out.println("Number of students from China is " + 
				ourUniversity. numberOfStudents("China"));
		System.out.println("\n************* Now we will sort the students********************\n");
		/* 
		 * Note that the sorting will depend on the lessThan method you have defined for the 
		 * different classes. Verify for yourself that, in the output, we expect all the Canadian Seniors first,
		 * followed by the foreign students, followed by the Canadian students under 65.
		 * In each group the students are sorted according to the lexicographic order.
		 */
		ourUniversity.sortStudents();
		System.out.println(ourUniversity);
	}
}