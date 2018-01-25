public class Worker {
	
	private static int howManyWorkers = 0;
	
	private int workerNumber;
	private Name workerName;
	private MyDate dateJoiningCompany;
	private double salary;
	private Worker supervisor;
	
	// Sets name, date joined company, and salary
	public Worker(String workerName, String dateJoiningCompany, double salary) {
		this.workerName = new Name(workerName);
		this.dateJoiningCompany = new MyDate(dateJoiningCompany);
		this.salary = salary;
		
		// Keep track of how many workers there have been
		// and give the worker a number
		howManyWorkers++;
		workerNumber = howManyWorkers;
	}
	
	// Sets name, date joined company
	// salary defaults to 0.0
	public Worker(String workerName, String dateJoiningCompany) {
		this(workerName, dateJoiningCompany, 0.0);
	}
	
	// Copy constructor
	public Worker(Worker worker) {
		workerNumber = worker.workerNumber;
		workerName = new Name(worker.workerName);
		dateJoiningCompany = new MyDate(worker.dateJoiningCompany);
		salary = worker.salary;
		
		// No deep copy for supervisor
		// This is useful, for example, if supervisor changes
		// their name, salary, or worker number
		supervisor = worker.supervisor;
	}
	
	// Set the salary of the worker
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	// Set the supervisor of the worker
	public void setSupervisor(Worker supervisor) {
		this.supervisor = supervisor;
	}
	
	// Get how many workers have been created
	public static int getHowManyWorkers() {
		return howManyWorkers;
	}
	
	// Return a new copy of the supervisor's name, if supervisor exists
	public Name getSupervisorName() {
		if(supervisor == null) {
			return null;
		} else {
			return new Name(supervisor.workerName);	
		}		
	}
	
	// Return a description containing:
	// worker number, worker name (toString()),
	// date joined (toString()), supervisor name (toString()),
	// salary
	public String toString() {
		String desc = "";
		
		desc += "Number:     "     + workerNumber                  + "\n";
		desc += "Name:       "     + workerName.toString()         + "\n";
		desc += "Joined:     "     + dateJoiningCompany.toString() + "\n";
		desc += "Supervisor: " + ((supervisor == null) ?
				"<NO SUPERVISOR>" : getSupervisorName())           + "\n";
		desc += "Salary:     "     + salary                        + "\n";
		
		return desc;
	}
	
}