/*
 * Shahir Chowdhury
 * 2017-06-11
 * Manager.java
 *
 * This program creates a Manager object, a child of the class NewWorker. In addition to all of NewWorker's capabilities,
 * Manager is able to store an array of NewWorkers, tracking details regarding the NewWorkers it supervises. It also recieves
 * different versions of NewWorker related methods to accompany the Manager's superiority, such as a bonus to its salary.
*/

public class Manager extends NewWorker{
	private int numWorkersSupervised = 0; 
	private NewWorker[] employeesSupervised = new NewWorker[10];	//list of all the NewWorkers being supervised by this Manager

	//sets personName, dateJoiningCompany, and salary  
	public Manager(String nameString, String dateString, double startSalary){
		super(nameString, dateString, startSalary);
	}

	//adds a newWorker to the employeesSupervised array
	public void addWorker(NewWorker newbie){
		for (int i = 0;i < employeesSupervised.length;i++){
			if (employeesSupervised[i] == null){
				employeesSupervised[i] = newbie;
				numWorkersSupervised += 1;		
				break;
			}
			if (numWorkersSupervised == 10){ //only increase number of supervised workers if the array can hold them 
				System.out.println("Too many workers");	//alert the user about the mistake
				break;
			}
		}
	}

	//deletes a NewWorker from the employeesSupervised array, only deleting the first in the array if there are duplicates
	public void deleteWorker(NewWorker failure){
		for (int i = 0;i < employeesSupervised.length; i++){	//find and remove the employee in the array
			if (employeesSupervised[i] == failure){
				employeesSupervised[i] = null;
				numWorkersSupervised -= 1;		  
				for (int j = 0;j < employeesSupervised.length;j++){ //shift all NewWorkers past the removed NewWorker to fill in the gap
					if (employeesSupervised[j] == null){
						if (j != 9 && employeesSupervised[j + 1] != null){
							employeesSupervised[j] = employeesSupervised[j + 1];
							employeesSupervised[j + 1] = null;
						} 
					}
				}
				break;
			}
		}
	}

	//accessor for salary
	@Override
	public double getSalary(){
		double money;

		money = numWorkersSupervised * 100 + super.getSalary();	//Managers get a bonus based on the number of NewWorkers they supervise

		return money;
	}

	//displays all of the Manager's information in a clean format
	public String toString(){
		String result = super.toString();	//include information provided about variables derived from NewWorker

		if (numWorkersSupervised != 0){
			result += "\n" + numWorkersSupervised + " Worker(s) supervised by this Manager:\n";
			for (int i = 0;i < numWorkersSupervised;i++){
				result += employeesSupervised[i].getPersonName() +"\n";
			}
		}
		else{
			result += "\nNot supervising any employees\n";
		}

		return result;
	}
}