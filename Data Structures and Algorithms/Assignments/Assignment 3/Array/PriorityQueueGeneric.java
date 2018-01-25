/*
 *QueueGeneric.java
 *Last Updated: 2017-11-05
 *Shahir Chowdhury
 *
 *This program constructs an abstract priority queue data structure with the array implementation
 *5 is assumed to be the lowest priority and 1 is assumed to be the highest priority
*/

public class PriorityQueueGeneric<T> implements MyPriorityQueue<T>{
	private final int MAXSIZE = 100;
	private class QueueNode<T>{
		private T element;
		private int priority;

		//sets element and priority
		public QueueNode(T element, int priority){
			this.element = element;
			this.priority = priority;
		}

		//sets element, with priority defaulting to 5
		public QueueNode(T element){
			this.element = element;
			this.priority = 5;	//lowest priority
		}

		//empty constructor
		public QueueNode(){
			this.element = null;
			this.priority = 5;
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

	/* RS This still gives a raw data type warning
	StackOverFlow has a smart solution, but I don't think you know enough Java to understand it */
	@SuppressWarnings("unchecked")
	private QueueNode<T> nodes[] = (QueueNode<T>[]) new QueueNode[MAXSIZE]; 
	private int front;
	private int size;

	//initializer
	public PriorityQueueGeneric(){
		front = 0;
		size = 0;
	}

	//RS Nice circular array Shahir! Tryhard.
	//adds an element to the priority queue, inserting it at a position depending on its priority
	public void enqueue(T element, int priority, PriorityQueueGeneric<T> queue){
		if (isFull(this) != true){
			QueueNode<T> newNode = new QueueNode<T>(element, priority);	
			if (isEmpty(this)){												//case where queue is empty
				nodes[front] = newNode;
			}
			else if (newNode.getPriority() < nodes[front].getPriority()){	//case where newNode is higher priority than head
				int moveNode = front;
				for (int i = 0; i < size;i++){								//traverse to the first empty after the first element in the queue
					moveNode++;
					if (moveNode == MAXSIZE){
						moveNode = 0;
					}
				}
				for (int i = size;i > 0;i--){								//shift all nodes back one position
					if (moveNode == 0){
						nodes[MAXSIZE - 1] = nodes[0];
						moveNode = MAXSIZE - 1;
					}
					else{
						nodes[moveNode] = nodes[moveNode - 1];
					}
					moveNode--;					
				}
				nodes[front] = newNode;
			}
			else{
				int curr = front + 1;
				if (curr == MAXSIZE){	//if head is the last node in the array, loop to the start
					curr = 0;
				}
				/* Traverse to the position where the new node will be inserted
				The node will always be inserted as the last node of its priority */
				while(nodes[curr] != null && nodes[curr].getPriority() <= newNode.getPriority()){	
					if (curr == MAXSIZE){
						curr = 0;
					}
					else{
						curr++;
					}
				}
				if (nodes[curr] == null){						//case that the new node is being inserted at the end of the queue
					nodes[curr] = newNode;
				}
				else{											//new node is being inserted in the middle
					int moveNode = curr;
					int numMoves = 0;							//number of times moveNode has changed
					while (nodes[moveNode] != null){			//traverse to the first empty spot after the first element in the queue
						moveNode++;
						numMoves++;
						if (moveNode == MAXSIZE){
							moveNode = 0;
						}
					}
					/* RS Your logic here doesn't work; shifting everything back by one Size times is incorrect
					You're gonna need a way to figure out how many times you want to shift when inserting in the middle */
					for (int i = numMoves;i > 0;i--){	//shift all nodes back one position
						if (moveNode == 0){
							nodes[MAXSIZE - 1] = nodes[0];
							moveNode = MAXSIZE - 1;
						}
						else{
							nodes[moveNode] = nodes[moveNode - 1];
						}
						moveNode--;					
					}
					nodes[curr] = newNode;
				}
			}
			size++;
		}
	}
		
	//adds an element to the priority queue, giving the new node a default priority of 5
	public void enqueue(T element, PriorityQueueGeneric<T> queue){
		QueueNode<T> newNode = new QueueNode<T>(element);	
		if (isEmpty(this)){								//case where queue is empty
			nodes[front] = newNode;
		}
		else{
			int curr = front + 1;
			if (curr == MAXSIZE){	//if head is the last node in the array, loop to the start
				curr = 0;
			}
			/* Traverse to the position where the new node will be inserted
			The node will always be inserted as the last node of its priority */
			while(nodes[curr] != null && nodes[curr].getPriority() <= newNode.getPriority()){	
				if (curr == MAXSIZE){
					curr = 0;
				}
				else{
					curr++;
				}
			}
			nodes[curr] = newNode;
		}
		size++;
	}

	//removes the first highest priority node in the priority queue
	public void dequeue(PriorityQueueGeneric<T> queue){
		if (isEmpty(this) == false){
			nodes[front] = null;
			front++;
			if (front == MAXSIZE){
				front = 0;
			}
		}
		size--;
	}

	//returns the first element in the queue
	public T peek(PriorityQueueGeneric<T> queue){
		if (isEmpty(this) == false){
			return nodes[front].getElement();
		}
		return null;
	}

	//RS Your forgot isRegular...again
	//returns true if each element in the queue has the same priority
	public boolean isRegular(PriorityQueueGeneric<T> queue){
		if (isEmpty(this) == false){
			int curr = front;
			for (int i = 0;i < size;i++){
				if (curr + 1 == MAXSIZE){
					if (nodes[curr].getPriority() != nodes[0].getPriority()){
						return false;
					}
				}
				else{
					if (nodes[curr].getPriority() != nodes[curr + 1].getPriority()){
						return false;
					}
				}
				curr++;
				if (curr == MAXSIZE){
					curr = 0;
				}
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
		return size == 0;
	}

	//returns if the queue is full, which will always be false due to the nature of the linked list implementation
	public boolean isFull(PriorityQueueGeneric<T> queue){
		return size == MAXSIZE;
	}

	/* RS For cleaner design and maintainability make a toString method and just print that in display.
	   It's probably okay for this assignment though */
	//displays all elements in the queue
	public void display(PriorityQueueGeneric<T> queue){
		String result = "";
		int num = 0;

		if (this.isEmpty(this)){
			result += "The Queue is empty!";
		}
		while (nodes[num] != null){
			result += "Item #" + num + ": " + nodes[num].getElement() + "\n";
			num++;
		}
		 System.out.println(result);
	}

	public static void main(String[] args){
		PriorityQueueGeneric<Integer> myQueue = new PriorityQueueGeneric<Integer>();
		System.out.println(myQueue.isEmpty(myQueue));
		System.out.println(myQueue.isFull(myQueue));
		System.out.println(myQueue.size(myQueue));
		System.out.println(myQueue.peek(myQueue));
		myQueue.display(myQueue);
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
	}

	/*for (int i = front ; i < front+5 ; i++) {
		int index = i % size;
		array[index]
	}*/

}