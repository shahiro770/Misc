import java.util.Iterator;

	public class IterableList<T> implements AddAndIterate<T>{
	/* This is just like the other IterableList. Only difference is that we are trying to generalize our code.*/
	LinkedListGeneric<T> aList;
	public class MyIterator implements Iterator<T>{
		public T next(){
			T value = aList.getCurrentValue();
			aList.getNextNode();
			return value;
		}
		
		public boolean hasNext(){
			return !aList.endOfList();
		}
		
		public void remove(){
			//not implemented
		}
	}
	
	public Iterator<T> iterator(){
		MyIterator anIterator;
		anIterator = new MyIterator();
		aList.restart(); // pointing to the beginning of list
		return anIterator;
	}
	
	public IterableList(){
		aList = new LinkedListGeneric<T>();
	}
	
	public void add(T value){ // object is added to the end of list
		//System.out.println(value);
		for(aList.restart(); !aList.endOfList(); aList.getNextNode());
			aList.addAfterCurrent(value);
	} 
	
	public String toString(){
		return aList.toString();
	} 
}