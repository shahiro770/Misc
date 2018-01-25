public class TestManager{

	public static void main(String[] args) {
		
		NewWorker w1 = new NewWorker("Robert William Hunter", "23/10/2005", 35000.00);
		NewWorker w2 = new NewWorker("John Smith", "15/11/2005", 25000.00);
		NewWorker w3 = new NewWorker("Mary Jane Hull", "06/09/2007");
		
		Manager m1 = new Manager("Bob Roberts", "05/02/2004", 60000);
		Manager m2 = new Manager("John Doe", "01/02/2003", 65000);
		
		m1.addWorker(w1);
		m1.addWorker(w2);
		m1.addWorker(w3);
		
		m2.addWorker(w1);
		m2.addWorker(w2);
		m2.addWorker(w3);
		m2.addWorker(m1);
		m1.setSupervisor(m2);
		
		System.out.println(m1.toString());
		System.out.println(m2.toString());
		
		m2.deleteWorker(m1);
		m2.deleteWorker(w1);
		
		System.out.println(m2.toString());
		
	}
	
}