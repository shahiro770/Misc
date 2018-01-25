/*
 * Shahir Chowdhury
 * 2017-06-29
 * Household.java
 *
 * This program creates a Household object. The Household stores the information of up to a user defined number of Person type objects.
*/

public class Household{
	private Person[] householdMembers;
	private int totalMembers = 0;
	private int maxMembers;			//array size of houseHoldMembers

	//sets maxMembers and householdMembers
	public Household(int maxMembers){
		this.maxMembers = maxMembers;
		householdMembers = new Person[this.maxMembers];
	}

	//default constructor that sets sets maxMembers to 10 and householdMembers to an array of size 10
	public Household(){
		maxMembers = 10;
		householdMembers = new Person[maxMembers];
	}

	//adds a Person to the householdMembers array at the lowest available indice
	public boolean insertNewHouseholdMember(Person newMem){
		if (totalMembers == maxMembers){
			return false;
		}
		for (int i = 0;i < maxMembers;i++){
			if (householdMembers[i] == null){
				householdMembers[i] = newMem;
				totalMembers++;
				break;
			}
		}

		return true;
	}

	//sorts the people in the household  by their names from least to greatest in a lexographical ordering
	public String sortHouseholdMembers(){
		Sort houseSorter = new Sort();
		houseSorter.sortAnything(householdMembers, totalMembers);

		return this.toString();	//returns a string containing the household members in their new ordering
	}

	//determines the number of people in the household who make over $3000
	public int findNumberOfFatCats(){
		int cats = 0;

		for (int i = 0;i < totalMembers; i++){
			if (householdMembers[i].fatCat() == true){
				cats++;
			}
		}

		return cats;
	}

	//displays the information of every Person contained inside the householdMembers array
	@Override
	public String toString(){
		String result = "";

		for (int i = 0;i < maxMembers;i++){
			if (householdMembers[i] != null){
				result += householdMembers[i].toString() + "\n";
			}
		}

		return result;
	}
}

