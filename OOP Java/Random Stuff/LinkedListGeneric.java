
public class LinkedListGeneric<T> {

	private class Node<T> {
		private T item;
		private Node<T> link;
		
		public Node(){
			link = null;
			item = null;
		}
		
		public Node(T newItem, Node<T> linkValue){
			item = newItem;
			link = linkValue;
		}

		public T getValue(){
			return item;
		}

		public Node<T> getNext(){
			return link;
		}

		public void setValue(T item){
			this.item = item;
		}

		public void setNext(Node<T> next){
			this.link = link;
		}
	}
	
	private Node<T> head;
	private Node<T> current;
	
	public LinkedListGeneric(){
		head = null;
		current = head;
	}
	
	public void addToStart(T itemName){
		head = new Node<T>(itemName, head);
		restart();
	}

	public void addAfterCurrent(T x){
		if (head == null){
			addToStart(x);
			System.out.println(head.getValue());
			System.out.println(current.getValue());
			current = head;
		}
		else{

			if (current.getNext() == null){
				Node<T> newNode = new Node<T>(x, null);
				current.link = newNode;
				current = newNode;
			}
			else{
				Node<T> newNode = new Node<T>(x, current.getNext());
				current.link = newNode;
				current = newNode;
			}
		}
	}

	public void restart(){
		current = head;

	}
	
	public boolean deleteHeadNode(){
		if (head != null){
			head = head.link;
			return true;
		}
		return false;
	}

	public Node<T> getCurrent(){
		return current;
	}

	public T getCurrentValue(){
		return current.getValue();
	}

	public boolean getNextNode(){
		if (head != null){
			current = current.getNext();
			return true;
		}
		return false;
	}

	public boolean endOfList(){
		if (current == null){
			return true;
		}
		return false;
	}

	public int size(){
		int count = 0;
		Node<T> position = head;
		while(position != null){
			count++;
			position = position.link;
		}
		return count;
	}
	
	public boolean contains(T item){
		return (find(item) != null);
	}
	
	private Node<T> find(T target){
		Node<T> position = head;
		T itemAtPosition;
		while (position != null){
			itemAtPosition = position.item;
			if (itemAtPosition.equals(target)) return position;	//T requires a well-defined equals() for this to work
			position = position.link;
		}
		return null;
	}
	
	public T findData(T item){
		return find(item).item;
	}
	
	public void outputList(){
		Node<T> position = head;
		while (position != null){
			System.out.println(position.item);	//T requires a well-defined toString()
			position = position.link;
		}
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public void clear(){
		head = null;
	}
	
	public boolean equals(Object otherObject){	//For two linked lists to be equal, all of their elements must be
		if (otherObject == null){				//the same and in the same order
			return false;
		}else if(getClass() != otherObject.getClass()){
			return false;
		}else{
			LinkedListGeneric<T> otherList = (LinkedListGeneric<T>)otherObject;
			if (size() != otherList.size()){
				return false;
			}
			Node<T> position = head;
			Node<T> otherPosition = otherList.head;
			while(position != null){
				if (!(position.item.equals(otherPosition.item))){
					return false;
				}
				position = position.link;
				otherPosition = otherPosition.link;
			}
			return true;
		}
	}
}