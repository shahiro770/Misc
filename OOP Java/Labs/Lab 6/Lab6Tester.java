public class Lab6Tester {
	public static void main(String[] args){
		NewWorker w1, w2, w3;
		Student s1, s2, s3;
		Household myFamily;
		s1 = new Student("Edward Teller", "Physics");
		s2 = new Student("Liz Powell", "Computer Science");
		s3 = new Student("Tom Porter", "Computer Science");

		w1 = new NewWorker("Robert William Hunter", "23/10/2009", 5000.00);
		w2 = new NewWorker("Mary Jane Hull", "06/09/2012");
		w3 = new NewWorker("Liz Mary Hull", "09/12/2014", 2000.00); 

		w2.setSalary(6000.00);

		myFamily = new Household();

		myFamily. insertNewHouseholdMember(s1);
		myFamily. insertNewHouseholdMember(s2);
		myFamily. insertNewHouseholdMember(s3);
		myFamily. insertNewHouseholdMember(w1);
		myFamily. insertNewHouseholdMember(w2);
		myFamily. insertNewHouseholdMember(w3);
				
		w1.setSpouse(w2);
		w2.setSpouse(w1);
		System.out.println("Number of fat cats in family is "+ myFamily.findNumberOfFatCats());
		System.out.println("\nDescription of the family before sorting is:\n");
		System.out.println(myFamily);
		System.out.println("*********************************************************");
		System.out.println("\nDescription of the family after sorting is:\n");
		System.out.println(myFamily.sortHouseholdMembers());
		
		
		
	}

}