/*
 *MyStack.java
 *Last Updated: 2017-10-16
 *Shahir Chowdhury
 *
 *This program constructs an interface that identifies that a program possesses the functionality of a stack data structure
*/
public interface MyStack<T>{
	public void push(T data);
	public T pop();
	public T top();
	public int getSize();
	public boolean isEmpty();
	public void empty();
	public String toString();
}