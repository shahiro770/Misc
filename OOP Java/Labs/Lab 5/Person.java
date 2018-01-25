/*
 * Shahir Chowdhury
 * 2017-06-15
 * Person.java
 *
 * This program creates the abstract Person object. The person stores a name, possibly a spouse and allows for interaction with
 * those two variables.
*/

public abstract class Person{
	private Name personName;
	private Person spouse;

	//sets personName
	//nameString is assumed to be in the format of "firstName middleName lastName" with middleName being optional
	public Person(String nameString){
		this.personName = new Name(nameString);
	}

	//mutator for spouse
	public void setSpouse(Person newSpouse){
		spouse = newSpouse;
	}

	//accessor for personName
	public Name getPersonName(){
		return new Name(personName);
	}

	//accessor the spouse's name
	public Name getSpouseName(){
		return new Name(spouse.getPersonName());
	}

	//abstract method for child classes to define their own salaries
	public abstract double getSalary();

	//calculates the total salary between the person and their spouse should either be of the "NewWorker" object type
	public double getFamilyIncome(){
		double tot = 0;					//total income

		tot += ((NewWorker)this).getSalary();
		if (spouse != null){
			tot += ((NewWorker)spouse).getSalary();
		}

		return tot;
	}

	//displays the name of the person as well as their spouse if they are lucky enough to have one
	@Override
	public String toString(){
		String result = "";

		result += "Name is " + this.getPersonName();
		if (spouse != null){
			result += "\nMarried to " + spouse.getPersonName();
		}

		return result;
	}
}