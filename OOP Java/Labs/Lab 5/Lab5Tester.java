public class Lab5Tester{
	public static void main(String[] args){
		NewWorker w1, w2;
		Student s1, s2, s3;
		Household myFamily;
		s1 = new Student("Edward Teller", "Physics");
		s2 = new Student("Liz Powell", "Computer Science");
		s3 = new Student("Tom Porter", "Computer Science");

		w1 = new NewWorker("Robert William Hunter", 
				"23/10/2005", 35000.00);
		w2 = new NewWorker("Mary Jane Hull", "06/09/2007");

		w2.setSalary(40000.00);

		myFamily = new Household(5);

		myFamily.insertNewHouseholdMember(s1);
		myFamily.insertNewHouseholdMember(s2);
		myFamily.insertNewHouseholdMember(s3);
		myFamily.insertNewHouseholdMember(w1);
		myFamily.insertNewHouseholdMember(w2);

		w1.setSpouse(w2);
		w2.setSpouse(w1);
		s1.setSpouse(s2);
		s2.setSpouse(s1);

		System.out.println("Total family income of Robert and Mary "
				+ w1.getFamilyIncome());
		System.out.println("Description of the family is as follows:\n"
				+ myFamily.toString());
	}
}