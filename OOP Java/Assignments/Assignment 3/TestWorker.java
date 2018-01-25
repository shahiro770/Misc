/*
 * Shahir Chowdhury
 * 2016-06-01
 * Name.java
 *
 * This program creates various workers and displays their information.
*/

import java.util.*;

public class TestWorker{
	public static void main (String[] args){
		Worker w1, w2, w3;

		w1 = new Worker("Robert William Hunter", "23/10/2005", 35000);
		w2 = new Worker("John Smith", "15/11/2005", 25000);
		w3 = new Worker("Mary Jane Hull", "06/09/2007", 20000);

		w2.setSalary(20000);
		w2.setSupervisor(w1);
		w3.setSupervisor(w1);

		System.out.printf("Number of workers = %d\n", w1.getHowManyWorkers()); //call from the class 
		System.out.printf("Supervisor of John is %s\n", w2.getSupervisorName());
		System.out.printf("%s%s%s", w1.toString(), w2.toString(), w3.toString());

		return;
	}
}