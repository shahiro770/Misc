/*
 * Shahir Chowdhury
 * 2017-07-23
 * MyInformationSystem.java 
 *
 * This program creates a MyInformationSystem object. It creates generic Pair objects that store information and allows for
 * the user to search through a list of these Pairs based on a key each Pair is created with.
*/
import java.util.*;

public class MyInformationSystem<T>{
	private ArrayList<Pair<T>> myList;	//Pair<T> so ArrayList does not contain a raw type

	public MyInformationSystem(){
		myList = new ArrayList<Pair<T>>();
	}

	// Provided main method
	public static void main(String a[]){
		String result;
		String keyList[] = {"126", "536", "428", "245"};
		MyInformationSystem<String> nameStudentNumberList;
		nameStudentNumberList = new MyInformationSystem<String>();
		nameStudentNumberList.insert("John", "245");// John has student number 245
		nameStudentNumberList.insert("Tom", "126");
		nameStudentNumberList.insert("Mary", "536");
		nameStudentNumberList.insert("Mark", "821");
		for (int i = 0; i < keyList.length; i++){
			try{
				result = nameStudentNumberList.retrieve(keyList[i]);
				System.out.println("For key " + keyList[i] +
						" the matching value is " + result);
			}
			catch(Exception e){
				System.out.println("No Match with " + keyList[i]);
			}
		}
	}

	//store a new Pair of information in MyList
	public void insert(T item, String key){
		Pair<T> itemPair = new Pair<T>(item, key);
		myList.add(itemPair);
	}	

	//returns the value of the Pair in myList with the specified key, null if it cannot be found
	public T retrieve(String key){
		for (Pair<T> thePair: myList){
			if (thePair.keyMatches(key)){
				return thePair.value;
			}
		}
		return null;
	}

	//object to store the desired type of information with an accompanying key
	private class Pair<U>{	//different generic name to not conflict with MyInformationSystem
		private U value;
		private String key;

		public Pair(){	//empty constructor for emergencies
			value = null;
			key = null;
		}

		public Pair(U value, String key){	//sets value and key
			this.value = value;
			this.key = key;
		}

		public boolean keyMatches(String otherKey){	//check if two keys are the same
			if (key.equals(otherKey)){
				return true;
			}
			return false;
		}
	}
}
