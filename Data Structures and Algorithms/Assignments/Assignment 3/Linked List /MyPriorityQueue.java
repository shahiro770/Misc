/*
 *MyPriorityQueue.java
 *Last Updated: 2017-11-05
 *Shahir Chowdhury
 *
 *This program constructs an interface that identifies that a program possesses the functionality of a stack data structure
*/
public interface MyPriorityQueue<T>{
	public void enqueue(T element, int priority, PriorityQueueGeneric<T> queue);
	public void dequeue(PriorityQueueGeneric<T> queue);
	public T peek(PriorityQueueGeneric<T> queue);
	public boolean isRegular(PriorityQueueGeneric<T> queue);
	public int size(PriorityQueueGeneric<T> queue);
	public boolean isEmpty(PriorityQueueGeneric<T> queue);
	public boolean isFull(PriorityQueueGeneric<T> queue);
	public void display(PriorityQueueGeneric<T> queue);
}