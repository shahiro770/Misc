/*
 *QueueGeneric.java
 *Last Updated: 2017-11-05
 *Shahir Chowdhury
 *
 *This program constructs an abstract priority queue data structure with the linked list implementation
 *5 is assumed to be the lowest priority and 1 is assumed to be the highest priority
*/

public class PriorityQueueGeneric<T> implements MyPriorityQueue<T>{	//RS Make your interface <T> to silence warnings with raw data types
	private class QueueNode<T>{
		private T element;
		private QueueNode<T> next;
		private int priority;

		//sets element, nextNode, and priority
		public QueueNode(T element, QueueNode<T> next, int priority){
			this.element = element;
			this.next = next;
			this.priority = priority;
		}

		//sets element and nextNode, with priority defaulting to 5
		public QueueNode(T element, QueueNode<T> next){
			this.element = element;
			this.next = next;
			this.priority = 5;	//lowest priority
		}

		//empty constructor
		public QueueNode(){			//RS Empty constructor is kind of pointless but it doesn't hurt
			this.element = null;
			this.next = null;
			this.priority = 5;
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
		public void setPriority(int priority){
			this.priority = priority;
		}

		public int getPriority(){
			return priority;
		}
	}

	private QueueNode<T> head;
	private int size;

	//initializer
	public PriorityQueueGeneric(){
		head = null;
		size = 0;
	}

	//adds an element to the priority queue, inserting it at a position depending on its priority
	public void enqueue(T element, int priority, PriorityQueueGeneric<T> queue){
		QueueNode<T> newNode = new QueueNode<T>(element, null, priority);	
		if (head == null){										//case where queue is empty
			head = newNode;
		}
		else if (newNode.getPriority() < head.getPriority()){	//case where newNode is higher priority than head
			newNode.setNext(head);
			head = newNode;
		}
		else{
			QueueNode<T> curr = head;
			//traverse to the position where the new node will be inserted
			//the node will always be inserted as the last node of its priority
			while(curr.getNext() != null && curr.getNext().getPriority() <= newNode.getPriority()){	
				curr = curr.getNext();
			}
			//RS Your forgot the case where the new node is the last node being inserted
			if (curr.getNext() == null){						//case that the new node is being inserted at the end of the queue
				curr.setNext(newNode);					
			}
			else{												//new node is being inserted in the middle
				newNode.setNext(curr.getNext());
				curr.setNext(newNode);
			}
		}
		size++;
	}
		
	//adds an element to the priority queue, giving the new node a default priority of 5
	public void enqueue(T element, PriorityQueueGeneric<T> queue){
		QueueNode<T> newNode = new QueueNode<T>(element, null);	
		if (head == null){	//case where queue is empty
			head = newNode;
		}
		else{
			QueueNode<T> curr = head;
			//traverse to the position where the new node will be inserted (will always be the last element due to default priority)
			while(curr.getNext() != null){	
				curr = curr.getNext();
			}
			curr.setNext(newNode);
		}
		size++;
	}

	//removes the first highest priority node in the priority queue
	public void dequeue(PriorityQueueGeneric<T> queue){
		if (isEmpty(this) == false){
			head = head.getNext();
			size--;	//RS you forgot to decrease the size of the queue
		}
	}

	//returns the first element in the queue
	public T peek(PriorityQueueGeneric<T> queue){
		if (isEmpty(this) == false){
			return head.getElement();
		}
		return null;
	}

	//RS You forgot the isRegular method
	//returns true if each element in the queue has the same priority
	public boolean isRegular(PriorityQueueGeneric<T> queue){
		if (isEmpty(this) == false){
			QueueNode<T> curr = head;
			while(curr.getNext() != null){
				if (curr.getPriority() != curr.getNext().getPriority()){
					return false;
				}
				curr = curr.getNext();
			}
		}
		return true;
	}

	//returns the size of the queue
	public int size(PriorityQueueGeneric<T> queue){
		return size;
	}

	//returns if the queue is empty
	public boolean isEmpty(PriorityQueueGeneric<T> queue){
		return head == null;
	}

	//returns if the queue is full, which will always be false due to the nature of the linked list implementation
	public boolean isFull(PriorityQueueGeneric<T> queue){
		return false;
	}

	/* RS For cleaner design and maintainability, make a toString method and just print that in display.
	  It's probably okay for this assignment though */
	//displays all elements in the queue
	public void display(PriorityQueueGeneric<T> queue){
		QueueNode<T> curr = head;
		String result = "";
		int num = 0;

		if (this.isEmpty(this)){
			result += "The Queue is empty!";
		}
		while (curr != null){
			result += "Item #" + num + ": " + curr.element + "\n";
			curr = curr.next;
			num++;
		}
		 System.out.println(result);
	}
	/*public static void main(String[] args){
		PriorityQueueGeneric<Integer> myQueue = new PriorityQueueGeneric<Integer>();
		System.out.println(myQueue.isEmpty(myQueue));
		System.out.println(myQueue.isFull(myQueue));
		System.out.println(myQueue.size(myQueue));
		System.out.println(myQueue.peek(myQueue));
		myQueue.enqueue(1,5,myQueue);
		myQueue.enqueue(2,3,myQueue);
		myQueue.enqueue(3,2,myQueue);
		myQueue.enqueue(4,4,myQueue);
		myQueue.enqueue(5,myQueue);
		myQueue.enqueue(6,1,myQueue);
		myQueue.enqueue(7,2,myQueue);
		myQueue.enqueue(8,2,myQueue);
		System.out.println(myQueue.isEmpty(myQueue));
		System.out.println(myQueue.isFull(myQueue));
		System.out.println(myQueue.size(myQueue));
		System.out.println(myQueue.peek(myQueue));
		myQueue.display(myQueue);
	}*/
}