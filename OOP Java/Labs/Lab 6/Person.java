/*
 * Shahir Chowdhury
 * 2017-06-29
 * Person.java
 *
 * This program creates the abstract Person object. The person stores a name, possibly a spouse and allows for interaction with
 * those two variables.
*/

public abstract class Person implements Sortable, HighIncome{
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

	//compares the name of this person object with another, returning true if this person's name is lower lexographically 
	//than the compared name
	public boolean lessThan(Object other){
		if (other instanceof Person){
			Person otherPerson = (Person)other;
			if (this.personName.lessThan(otherPerson.personName)){
				return true;
			}
		}

		return false;
	}

	//checks if the Person is making equal to or more than $3000
	public boolean fatCat(){
		if (this.getSalary() >= 3000){
			return true;
		}

		return false;
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