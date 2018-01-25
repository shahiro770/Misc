/*
 * Shahir Chowdhury
 * 2017-06-11
 * NewWorker.java
 *
 * This program creates a NewWorker object, a child class of Person. In addition to Person's contents, the NewWorker keeps track
 * of their salary, supervisor, when they joined the company, and even what number worker they are with respect to other workers.
*/

public class NewWorker extends Person{
	private static int howManyWorkers = 1;	//total number of workers
	private int workerNumber;
	private double salary;
	private NewWorker supervisor;
	private MyDate dateJoiningCompany;		

	//sets personName, dateJoiningCompany, and salary  
	public NewWorker(String nameString, String dateString, double startSalary){
		super(nameString);
		this.dateJoiningCompany = new MyDate(dateString);
		this.salary = startSalary;
		this.workerNumber = howManyWorkers;
		howManyWorkers++;
	}

	//sets personName and dateJoiningCompany, defaulting salary to 0
	public NewWorker(String nameString, String dateString){
		super(nameString);
		this.dateJoiningCompany = new MyDate(dateString);
		this.salary = 0;
		this.workerNumber = howManyWorkers;
		howManyWorkers++;
	}

	//mutator for salary
	public void setSalary(double newSalary){
		salary = newSalary;
	}

	//accessor for salary
	@Override
	public double getSalary(){
		return salary;
	}

	//mutator for supervisor
	public void setSupervisor(NewWorker newSuper){
		supervisor = newSuper;
	}

	//accessor for supervisor
	public Name getSupervisor(){
		if (supervisor != null){
			return new Name(supervisor.getPersonName());
		}
		else{
			return null;
		}
	}

	//displays all of the NewWorker's information in a clean format
	@Override
	public String toString(){
		String result = "";

		result += "Worker Number " + workerNumber + "\nWorker Name " + this.getPersonName() + "\n";
		result += "Date Joined: " + dateJoiningCompany + "\n";
		if (supervisor != null){
			result += "Supervisor: " + supervisor.getPersonName() + "\n";
		}
		else{
			result += "No supervisor\n";
		}
		result += "Salary: $" + salary + "\n";

		return result;
	}

	//accessor for the total number of workers
	public static int getHowManyWorkers(){
		return howManyWorkers;
	}
}