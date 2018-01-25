/*
 *MyQueue.java
 *Last Updated: 2017-11-17
 *Shahir Chowdhury
 *
 *This program constructs an interface that identifies that a program possesses the functionality of a queue data structure
*/

public interface MyQueue<T>{
	public void enqueue(T element);
	public void dequeue();
	public T peek();
	public int size();
	public boolean isEmpty();
	public boolean isFull();
	public void display();
}