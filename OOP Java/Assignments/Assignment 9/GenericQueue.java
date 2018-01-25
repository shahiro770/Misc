/*
 * Shahir Chowdhury
 * 2017-07-23
 * GenericQueue.java 
 *
 * This program creates a GenericQueue object. It creates a queue data structure, storing information in a first in, first out manner
 * (FIFO).
*/
import java.util.*;

public class GenericQueue<T>{
	private ArrayList<T> myQueue;

	//initializes the queue
	public GenericQueue(){
		myQueue = new ArrayList<T>();
	}

	//checks if the queue is empty
	public boolean isEmpty(){
		return (myQueue.size() == 0);
	}

	//add an item to the end of the queue
	public void enqueue(T item){
		myQueue.add(item);
	}

	//remove an item from the front of the queue and return it
	public T dequeue(){
		if (isEmpty()){	//if queue is empty, return null
			return null;
		}
		T dequeued = myQueue.get(0);
		myQueue.remove(0);

		return dequeued;
	}

	//returns a string containing all the contents of the array in a nicely organized format
	public String toString(){
		String result = "***************************************************\n";
		result += "This queue has " + myQueue.size() + " elements:\n";
		for (int i = 0;i < myQueue.size();i++){
			result += myQueue.get(i).toString() + "\n";
		}
		result += "***************************************************";

		return result;
	}
}
