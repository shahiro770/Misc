/*
 *StackGeneric.java
 *Last Updated: 2017-10-15
 *Shahir Chowdhury
 *
 *This program constructs a generic stack data structure with the linked list implementation.
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
		T popped = top();
		head = head.getNext();
		size--;
		return popped;
	}

	public T top(){
		return head.data;
	}

	public void setTop(T data){
		head.setData(data);
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

		while (curr != null){
			result += curr.data + " ";
			curr = curr.next;
		}

		return result;
	}
}

