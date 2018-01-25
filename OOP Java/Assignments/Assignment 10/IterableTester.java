import java.util.Iterator;

public class IterableTester {
	public static void main(String args[]){
		AddAndIterate aList; // NOTE CHANGE!! aList is of the interface type
		Iterator<String> anIterator;
		aList = new IterableList<String>(); // This is the only line that we need to change to have a different data structure
		aList.add("John"); // rest of code is the same as before
		aList.add("Mary");
		aList.add("Tom");
		aList.add("Paul");
		System.out.println("Check contents of list");
		System.out.println(aList);
		anIterator = aList.iterator();
		System.out.println("What the iterator returns");
		while(anIterator.hasNext()){
			System.out.println(anIterator.next());
		}
	}
}