/*
 *StackGeneric.java
 *Last Updated: 2017-10-05
 *Shahir Chowdhury
 *
 *This program constructs an abstract stack data structure with the linked list implementation.
*/

public class StackGeneric<T> implements MyStack<T>{
	private class StackNode<T>{
		private T data;
		private StackNode<T> next;

		public StackNode(T data, StackNode<T> next){
			this.data = data;
			this.next = next;
		}

		public StackNode(){
			this.data = null;
			this.next = null;
		}

		public void setNext(StackNode<T> next){
			this.next = next;
		}

		public StackNode<T> getNext(){
			return next;
		}

		public void setData(T data){
			this.data = data;
		}

		public T getData(){
			return data;
		}
	}

	private StackNode<T> head;
	private int size = 0;

	public StackGeneric(){
		head = null;
	}

	public void push(T data){
		StackNode<T> newNode = new StackNode<T>(data, head);
		head = newNode; 
		size++;
	}

	public T pop(){
		StackNode<T> temp = head;
		head = head.getNext();
		size--;
		return head.getData();
	}

	public T top(){
		return head.data;
	}

	public int getSize(){
		return size;
	}

	public boolean isEmpty(){
		return head == null;
	}

	public void empty(){
		head = null;
	}

	private StackNode<T> find(T data){
		StackNode<T> curr = head;
		while (curr != null){
			if (curr.data == data){
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}

	public T findData(T data){
		return find(data).data;
	}

	public boolean contains(T data){
		return (find(data) != null);
	}

	@Override
	public String toString(){
		StackNode<T> curr = head;
		String result = "";
		int num = 0;

		if (isEmpty()){
			result += "The stack is empty!";
		}
		while (curr != null){
			result += "Item #" + num + ": " + curr.data + "\n";
			curr = curr.next;
		}

		return result;
	}

	/*public boolean myEquals(Object otherObject){
		if (otherObject == null){
			return false;
		}
		else if (getClass() != otherObject.getClass()){
			return false;
		}
		else{
			StackGeneric<T> otherStack = (StackGeneric<T>) otherObject;
			if (size != otherStack.size){
				return false;
			}
			StackNode<T> curr = head;
			StackNode<T> otherCurr = otherStack.head;
			while (curr != null){
				if (!(curr.data.equals(otherCurr.data))){
					return false;
				}
				curr = curr.next;
				otherCurr = otherCurr.next;
			}

			return true;
		}
	}*/
}

