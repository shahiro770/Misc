/*
 * Shahir Chowdhury
 * 2016-06-11
 * Person.java
 *
 * This program creates a Person object. The person stores a name, possibly a spouse and allows for interaction with
 * those two variables.
*/

public class Person{
	private Name personName;
	private Person spouse;

	//sets personName
	//nameString is assumed to be in the format of "firstName middleName lastName" with middleName being optional
	public Person(String nameString){
		this.personName = new Name(nameString);
	}

	//sets spouse
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

	//calculates the total salary between the person and their spouse should either be of the "NewWorker" object type
	public double getFamilyIncome(){
		double tot = 0;					//total income

		if (this instanceof NewWorker){
			tot += ((NewWorker)this).getSalary();
		}
		if (spouse instanceof NewWorker){
			tot += ((NewWorker)spouse).getSalary();
		}

		return tot;
	}

	//displays the name of the person as well as their spouse if they are lucky enough to have one
	public String toString(){
		String result = "";

		result += "Name is, " + this.getPersonName();
		if (spouse != null){
			result += "\nMarried to " + spouse.getPersonName();
		}

		return result;
	}
}