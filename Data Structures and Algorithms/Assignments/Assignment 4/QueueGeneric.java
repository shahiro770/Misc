/*
 *QueueGeneric.java
 *Last Updated: 2017-11-17
 *Shahir Chowdhury
 *
 *This program constructs an abstract queue data structure with the linked list implementation
*/

public class QueueGeneric<T> implements MyQueue<T>{	
	private class QueueNode<T>{
		private T element;
		private QueueNode<T> next;

		//sets element, nextNode, and priority
		public QueueNode(T element, QueueNode<T> next){
			this.element = element;
			this.next = next;
		}

		//empty constructor
		public QueueNode(){			
			this.element = null;
			this.next = null;
		}

		public void setNext(QueueNode<T> next){
			this.next = next;
		}

		public QueueNode<T> getNext(){
			return next;
		}

		public void setElement(T element){
			this.element = element;
		}

		public T getElement(){
			return element;
		}
	}

	private QueueNode<T> head, tail;
	private int size;

	//initializer
	public QueueGeneric(){
		tail = null;
		head = null;
		size = 0;
	}

	//adds an element to the queue
	public void enqueue(T element){
		QueueNode<T> newNode = new QueueNode<T>(element, null);	
		if (head == null){										//case where queue is empty
			head = newNode;
			tail = newNode;
		}
		else{
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
	}

	//removes the node from the start of the queue
	public void dequeue(){
		if (isEmpty() == false){
			if (head == tail){
				tail = null;
			}
			head = head.getNext();
			size--;
		}
	}

	//returns the first element in the queue
	public T peek(){
		if (isEmpty() == false){
			return head.getElement();
		}
		return null;
	}

	//returns the size of the queue
	public int size(){
		return size;
	}

	//returns if the queue is empty
	public boolean isEmpty(){
		return head == null;
	}

	//returns if the queue is full, which will always be false due to the nature of the linked list implementation
	public boolean isFull(){
		return false;
	}

	public void clear(){
		head = null;
		tail = null;
	}

	//converts the contents of the queue to a string
	@Override
	public String toString(){
		QueueNode<T> curr = head;
		String result = "";
		int num = 0;

		if (isEmpty()){
			result += "The Queue is empty!";
		}
		while (curr != null){
			result += "Item #" + num + ": " + curr.element + "\n";
			curr = curr.next;
			num++;
		}
		
		return result;
	}

	//display the contents of the queue
	public void display(){
		System.out.println(this.toString());
	}
}