/* Linked Data Structures (Lecture 13) 2017-07-24

Linked Data Structures
--------
	A linked data structure consists of capsules of data known as nodes that are connected via links (edges)
		Links can be viewed as arrows and thought of as one-way passages feom one node to another
	In Java nodes are realized as objects of a node class (you can define this as you like)
		The data in a node is stored in instance variables
		The links are realized as object references
			Of course, a reference is a memory address, stored as the value in a variable of a class type
			Therefore, a link is an instance variable of the node class type itself

Linked Lists
--------
	The simplest kind of linked data structure is a linked list
	A linked list consists of a single chain of nodes, each connected to the next by a link
		The first node is called the head node
		The last node serves as an end marker (i.e. it equals null, we know its the end)
		So if we had a linked list of nodes that held the name of a food in a string and the quantity of the food on hand...

	head -(points to)> "rolls", 10, NextNode -> "jam", 5, NextNode -> "milk", 2, NextNode -> "tea", 1, nextNode = null (end marker) 

	A linked list must contain data and a link to another node
		Note that each node is typically illustrated as a box containing one or more pieces of data
	Each node contains data and a link to another node
		A piece of data is stored as an instance variable of the node
		Data is represented as information contained within the node
		Links are implemented as references to a node stored in an instance variable of the node type
		Links are typically illustrated as arrows that point to the node to which they link
		Bottom line - links are node references

	*/
		public class Node1{
			private String item;
			private int count;
			private Node1 link; //reference to another node (i.e. this is the link to the next node)
		}

	/*
	
	The first node in a linked list is called a head
		The entire linked list can be traversed by starting at the head node and visiting each node exactly once
	There is typically a variable of the node type that contains all the nodes in the linked list directly
		However, this is not the head node, or any node for that matter
			Its actually just a REFERENCE to the head node
	A linked list object contains the variable head as an instance variable of the class
		It uses the instance variable head to locate the head node of the list
	A linked list object does not contain all the nodes in the linked list directly
		Instead, it uses the instance variable head to locate the head node of the list
		The head node and every node of the list contain a link instance variable that provides a reference to the next node in the list
		Once the head node can be reached, every other node in the list can be reached by traversing some set of nodes in the list
	The head instance variable contains a reference to the first node in the linked list
		If the list is empty, this instance variable is set to null
		Note: this test is done using "==" not using.equals
	The linked list constructor sets the head instance variable to null
		This indicates that the newly created linked list is empty
	The last node in a linked list should have its link instance variable set to null
		That way, code can test whether or not a node is the last node of a list by checking if its link is null
			Again, null tests are done using "=="" not .equals
	Dangling pointer = a piece of data with no way to access it (garbage collector hunts these down)

	Traversing a Linked List
		If a linked list already contains nodes, it can be traversed as follows:
			Set a local variable equal to the value stored by the head node (its reference)
				This provides the location of the first node
		After accessing the first node, use the accessor method for the link instance variable to get the location of the next node
		Rinse and repeat until the location of the next node is equal to null

	"rolls", 10, NextNode -> "jam", 5, NextNode -(position.getLink())> "milk", 2, NextNode -> "tea", 1, nextNode = null (end marker)
	   ^					   ^
	 head					position			

	Deleting the Head Node from a Linked List
		The method deleteHeadNode removes the first node from the linked list
			It leaves the head variable pointing to (i.e., containing a reference to) the old second node in the linked list
		The deleted node will automatically be collected and its memory recycled
			Same result for any other nodes that are no longer accessible
				In Java, this process is called automatic garbage collection
	
	Suppose you define a Node class as follows:

	*/
		public class Node{
			private int value;
			private Node next;
			public Node(int value, Node next){
				this.value = value;
				this.next = next;
			}
			public int getValue(){
				return value;
			}
			public Node getNext(){
				return next;
			}
			public void setValue(int value){
				this.value = value;
			}
			public void setNext(Node next){
				this.next = next;
			}
		}
	/*

	Method to write: public Node delete(int valueToBeDeleted)
		What will delete(7) do?
			Return a pointer to the new list without 7

	5 -> 7 -> 12, delete(7), 5 -> 12

		What will delete(4) do?
			Return the same list because 4 doesn’t exist
		
	5 -> 7 -> 12, delete(4), 5 -> 7 -> 12
	
	Deletion x from a list:
		Considerations:
			What if start is pointing at an object containing an object with int value x?
			What if start is pointing at an object containing an object not containing x?
			What if start is null?
			Are you assuming the list is sorted?
			Are you assuming a given element can only be found once in the list? 
				If not, should the delete method find and delete all instances of the element?

	*/ 
		//my attempt
		public Node delete (int valueToBeDeleted){
			if (head == null){
				System.out.println("There's nothing here!");
			}
			else{
				if (head.getValue() == valueToBeDeleted){	//case where the target node is the head
					head = head.getNext();
				}

				Node position = head;	//traverse the list until the desired node is found
				do {	
					if (position.getNext().getValue() == valueToBeDeleted){
						Node removed = position.getNext()
						position.getNext() = position.getNext().getNext();
						break;
					}
					else{
						position = position.getNext();
					}
				} while (position != null);

				if (position == null){	//target node was not found
					System.out.println("The value you want to delete does not exist");
				}
			}

			return head;	
		}
	/*

Stacks
--------
	Imagine it like a stack of plates
	Stacks have the operations push and pop
		Push puts a plate on top of the stack
		Pop takes a plate off the top of the stack
		Stacks are known as FILO (first in last out)
	You can implement a stack using arrays but it will not be dynamic
	You can also implement a stack using ArrayList as we saw last time, but it still isn’t a great implementation
		ArrayLists are inefficient
	Instead, a stack is best implemented using a linked list as the base data structure
		Rather than having a variable for the head we would have a stackTop variable which points us to the top element
	A problem to consider:
		Write a class definition for StackInt, a stack of integers
		You must include the methods push, pop, toString, and isEmpty

	*/	
		//my attempt, using the previous Node structure
		public class StackInt{
			private Node stackTop;
			
			public StackInt(){
				stackTop = null;
			}

			public void push(Node item){
				Node oldTop = stackTop;
				stackTop = item;
				item.setNext(oldTop);
			}

			public Node pop(){
				Node oldTop = stackTop;
				stackTop = stackTop.getNext();
				return oldTop;
			}

			public String toString(){
				String result;
				Node position = stackTop;

				if(stackTop == null){
					result = "There's nothing here";
				}
				else{
					int count = 1;
					while (position != null){
						result += "Node #" + count + " holds an integer value of " + position.getValue();
					}
				}

				return result;
			}

			public boolean isEmpty(){
				return (stackTop == null);
			}
		}
	/*

	Note that the linked list class as we have been discussing is dependent on an external node class
	A linked list or similar data structure can be made self-contained by making the node class an inner class
	A node inner class should be made private, unless it is to be used elsewhere as well
		This can simplify the definition of the node class by eliminating the need for accessor and mutator methods
		Since the instance variables are private, they can be accessed directly from methods of the outer class
			Importantly, they can be accessed WITHOUT causing a privacy leak

	Spot the privacy leak!

	*/
		public class Node{
			private int value;
			private Node next;

			public Node(int value, Node next){
				this.value = value;
				this.next = next;
			}
			public int getValue(){
				return value;
			}
			public Node getNext(){	//woaho
				return next;
			}
			public void setValue(int value){
				this.value = value;
			}
			public void setNext(Node next){
				this.next = next;
			}
		}
	/*

	The original node and linked list classes we have so far discussed have a dangerous flaw
		The node class accessor method getNext returns a reference to a node
		Recall that if a method returns an instance variable of a mutable class type...
			...then the private restriction on the instance variable can easily be bypassed
		The easiest way to fix this problem would be to make the node class a private inner class in the linked list class
		Why can’t we use new Node() like we would have done in the past in this case?
			The new node will lack the appropriate links (it will be created without the previous node pointing to it)

	Some Useful Classes
		Node (obviously)
			In the next example, instead of a String value the Node will contain an Object
		ListGeneral
			Creates a linked list that can be of any object type
				How? Its node class has an Object variable for the value stored in the node
				Not the best way to do this… any idea what we should do instead? (cough, generics, cough)

	*/
		public class Node{
			private Object value; //gah
			private Node next;

			public Node(Object value, Node nextNode){ //woah
				this.value = value; 
				this.next = nextNode;
			}
			public Object getValue(){ //shit
				return value;
			}
			public Node getNext(){
				return next;
			}
			public void setValue(Object value){ // uh oh
				this.value = value;
			}
			public void setNext(Node next){
				this.next = next;
			}
		}
	/*
	
	Class ListGeneral
		Some Notes:
			It uses the Node class above, so the values stored in the list can be any object
			It has a method addBeforeCurrent and another called addAfterCurrent 
				This allows you to add elements before or after the current node
			It has currentNode and previousNode instance variables to aid in traversing and manipulation
			It has methods for traversing the list, restarting the list, and detecting the end of the list

Generic Linked Lists
--------
	As I mentioned previously, storing an Object instance variable in a node is not a good way to do things
		You typically want the entire collection to be of the same type
			Makes values comparable, sortable, handled in the same way, etc.
			Using Object is not what we call “type-safe”
			You can miss a cast or a class check and give yourself a run-time error that does not show up at compilation
	So, the solution? Use generics.
	A linked list can be created whose Node class has a type parameter T for the type of data stored in the node
		Therefore, it can hold objects of any class type, including types that contain multiple instance variables
	For the most part, this class can have the same methods, coded in basically the same way, as the previous linked list example
		The only difference is that a type parameter is used instead of an actual type for the data in the node
	You can add other useful methods too
	Let’s look at a code sample (same as the code in the next few slides)
	
	*/
		public class LinkedList<T>{
			private class Node<T>{
				private T data;			//this linked list holds objects of type T (T should have good equals() and toString() methods)
				private Node<T> link;

				public Node(){
					data = null;
					link = null;
				}

				public Node(T newData, Node<T> newLink){
					data = newData;
					link = newLink;
				}
			}	//end of inner class

			private Node<T> head;

			public LinkedList(){
				head = null;
			}

			public void addToStart(T itemData){
				head = new Node<T>(itemData, head);
			}

			public boolean deleteHeadNode(){
				if (head != null){
					head = head.link;
					return true;
				}
				return false;
			}

			public int size(){
				int count = 0;
				Node<T> position = head;
				while (position != null){
					count++;
					position = position.link;
				}
				return count;
			}

			public boolean contains(T item){
				return (find(item) != null);
			}

			private Node<T> find(T item){	//finds the first item in the list containing T
				Node<T> position = head;
				T itemAtPosition;
				while (position != null){
					itemAtPosition = position.data;
					if (itemAtPosition.equals(item)){	//e.g. why T needs a well defined equals method
						return position;
					}
				}
				return null;	//target not found
			}

			public T findData(T item){	//finds first node containing T, and returns a reference to that node's data
				return find(item).data
			}

			public void outputList(){
				Node<T> position = head;
				while (position != null){
					System.out.println(position.data);	//e.g. why T needs a wel defined toString method
					position = position.link;
				}
			}

			public boolean isEmpty(){
				return (head == null);
			}

			public void clear(){
				head = null; //garbage collector woah
			}

			public boolean equals (Object otherObject){	//test equality between two lists (i.e. must have same objects in same order)
				if (otherObject == null){
					return false;
				}
				else if (getClass() != otherObject.getClass()){
					return false;
				}
				else{
					LinkedList<T> otherList = (LinkedList<T>) otherObject;
					if (size() != otherList.size()){
						return false;
					}
					Node<T> position = head;
					Node<T> otherPosition = otherList.head;
					while (position != null){
						if (!(position.data.equals(otherPosition.data))){
							return false;
						}
						position = position.link;
						otherPosition = otherPosition.link;
					}
					return true;	//no mismatch found
				}
			}
		}

		//sample data class
		public class Entry{
			private String item;
			private int count;

			public Entry(String itemData, int countData){
				item = itemData;
				count = countData;
			}

			public String toString(){
				return (item + " " + count);
			}

			public boolean equals(Object otherObject){
				if (otherObject == null){
					return false;
				}
				else if (getClass() != otherObject.getClass()){
					return false;
				}
				else{
					Entry otherEntry = (Entry)otherObject;
					return ((item == otherObject.item) && (count == otherObject.count));
				}
			}

			//there should be other methods here like accessor and mutator methods, but they aren't important for this example
		}
	/*

Iterators (Can be used on non lists)
--------
	A collection of objects (i.e. nodes of a linked list) must often be traversed in order to perform some action object each object
		An iterator is any object that enables a list to be traversed in this way
	A linked list class may be created that has an iterator inner class
		If iterator variables are to be used outside the linked list class, then the iterator class would be made public
		The linked list class would have an iterator method that returns an iterator for its calling object
		Given a linked list named list, this can be done as follows:
	
	*/ LinkedList2.List2Iterator i = list.iterator(); /*

	The basic methods used by an iterator are:
		restart – Resets the iterator to the list’s beginning
		hasNext – Determines if there is another element
		next – Gets the next item in the list
	
	*/
		public class OurIterator{	//inner class of some LinkedList
			private Node position;
			private Node previous;	//previous value of position

			public OurIterator(){
				position = head;	//outer class's
				previous = null;
			}

			public void restart(){
				position = head;	//outer class's
				previous = null;
			}

			public String next(){
				if (!hasNext()){
					throw new NoSuchElementException();
				}

				String toReturn = position.item;
				previous = position;
				position = position.link;
				return toReturn;
			}

			public boolean hasNext(){	//if the current position is not null, assume there's a following node
				return ((position != null));
			}

			public String peek(){	//Returns the next value to be returned by next
				if (!hasNext()){
					throw new NoSuchElementException();
				}
				return position.item;
			}

			public void addHere(String newData){	//creates a new node before the current position
				Node newNode = new Node(newData, position);
				previous.link = newNode;
				previous = newNode;
			}

			public void changeHere(String newData){
				if (!hasNext()){
					throw new IllegalStateException();
				}
				position.item = newData;
			}

			public void delete(){
				if (!hasNext()){
					throw new IllegalStateException();
				}
				position = position.link;
				previous.link = position;	//thanks garbageCollector
			}

			//....some time further down where we find the linked list's contructor

			public SomeLinkedList(){
				public OurIterator iterator(){	//if "list" is an object of the class SomeLinkedList
					return new OurIterator();	//then list.iterator() returns an iterator for list
				}
				//whatever the other methods are
			}
		}
	/*
	
	The idea of iterators is to seperate functionality from structure
	Java has an interface called Iterator that specifies how Java would like an iterator to behave
		Although the iterators we’ve seen so far do not satisfy the needs of this interface, they can be easily modified to do so
			We’ll talk about this in a bit
	Iterators are not commonly used to add or delete a node in a linked list, but they can certainly be used to do so
	Given iterator variables position and previous, the following two lines of code will delete the node at location position
		Given that previous points to the node before position…
	
	*/
		previous.link = position.link;	//another way to delete, different from the above example
		position = position.link;
	/*
	
	So if we had a linked list with coat -> shoes -> socks -> pants
									 ^		 ^        ^
									head   previous  position
		Calling delete() at position, shoes will link to pants and pants will link to shoes
			What happens to socks?
		Since there is no way to actually access that variable anymore, Java’s garbage collector (yes that’s a real thing)
			...will determine that the memory should be freed for other variables
				Socks is removed automatically
	Okay, so deleting a node is straightforward enough it seems
		What about if we want to add a node?
	
	*/
		temp = new Node(newData, position)
		previous.link = temp;	//ez
	/*

	So with our current list coat -> shoes      -> pants
							  ^  	  ^			    ^
						 	 head    previous      position  
		We want to add socks back to where it once was
	
	*/ temp = new Node(newData, position); /*

	Let socks be the new addition to the list
	We initialize the new node using its data and link to next member (which our variable position points to)
		And we're almost back to coat -> shoes -> socks -> pants, except both shoes and socks link to pants

	*/ previous.link = temp; /*
	
		Now shoes points to socks, problem solved!
	…And that’s it
	Pretty easy to do either of these operations
	So let’s look at what Java gives us for an iterator interface
	But first: why do we need iterators?
		They allow the for-each notation
		They allow complete traversal of any kind of data structure that has the iterator
		They gracefully iterate even if elements are removed or added to the list (
			Just try iterating an ArrayList while removing an item
				Spoiler alert: ArrayList gets dunked on by iterator
	Java’s iterator interface is generic (honestly, almost anything that manages a collection is and should be written generically)
	Any object that satisfies the requirements of the Iterator<T> interface (and implements it) is an iterator
	An iterator does not stand on its own, it must be associate with some collection object using the iterator method
	If c is an instance of a collection class, the following obtains an iterator for c:
	
	*/ Iterator it = c.iterator(); /*

	Methods in Iterator<T> (found in util, NoSuchElementException is also in util)

	*/
		public T next()	//returns the next element of the collection that produced the iterator
						//throws a NoSuchElementException otherwise

		public boolean hasNext()//returns true if next() has not yet returned all elements in the collection, false otherwise

		public void remove() /*(OPTIONAL)Removes the last element returned by next(), can only be called once per next()
		If the collection is changed in any way other than remove(), the iterator will have unpredictable behaviour
		Throws IllegalStateException if the next() method has not been called, or already been called for the latest next() call
		Since the method is optional, throws UnsupportedOperationException if the remove option is not supported by the Iterator<T>*/
	/*

	Let’s look at some sample code!
	It makes an ArrayList of Strings
	Creates a variable for the iterator
	Creates the list
	Gets the iterator of the list
	Performs some operations on the list using the iterator
	
	*/
		import java.util.ArrayList;
		import java.util.Iterator;

		public class ArrayListWithIterator {// Iterator interface is in java.util
			public static void main(String args[]){
				ArrayList<String> myList = new ArrayList<String>();
				Iterator<String> anIterator;
				myList.add("cats");
				myList.add("dogs");
				myList.add("mice");
				myList.add("zebras");
				myList.add("donkeys");
				myList.add("lions");
				System.out.println("The list has " + myList);
				anIterator = myList.iterator();
				System.out.println("Traversing the list using an iterator ");
				
				while (anIterator.hasNext()){
					System.out.println(anIterator.next());
				}
				
				anIterator = myList.iterator(); // There is no reset operation in Iterator. We are now at the beginning
				anIterator.next();
				anIterator.next(); // Current element "dogs"
				anIterator.remove(); //"dogs" is gone!! Now next() will retrieve "mice"
				System.out.println("Traversing the list from the element containing mice");
				
				while (anIterator.hasNext()){
					System.out.println(anIterator.next());
				}
			}
		}
	/*

List Iterators (CAN ONLY BE USED ON LISTS)
--------
	This interface extends the Iterator<T> interface and is designed for collections that satisfy the List<T> interface
		A ListIterator<T> has all the methods that an Iterator<T> has, plus a few extras
		A ListIterator<T> can move bidirectionally, not just forward like we have seen so far
		A ListIterator<T> has methods such as set and add, used to modify the collection or its elements
	
	Methods in ListIterator<T> (found in util, NoSuchElementException is also in util, cursor is explained later)

	*/
		public T next()	/*returns the next element of the list that produced the iterator. More specifically, returns the element
						  immediately after the cursor*/
						//throws a NoSuchElementException if there is no next element

		public T previous() /*returns the previous element of the list that produced the iterator. More specifically, returns the 
		                      element immediately before the cursor*/
							//throws a NoSuchElementException if there is no previous element

		public boolean hasNext()//returns true if next() has not yet returned all elements in the collection, false otherwise

		public boolean hasPrevious()//returns true if previous() if there is a suitable element to return, false otherwise

		public int nextIndex()  //returns the index of the element that would be returned by a call to next()
								//i.e. returns the list size if at the end of the list
		public int previousIndex() //returns the index that would be returned by a call to previous()
								   //returns -1 if at the start of the list

		public void add(T newElement) /*(OPTIONAL)Inserts newElement at the location of the Iterator cursor before, if any, the 
		value of next(), and after, if any, the value of previous() (Can only be used once per next() or previous() call)
		Throws a shit ton of exceptions if you try anything stupid. This includes, but is not limited to:
			-calling more than once after a previous() or next() call
			-calling without a previous() or next() call
			-add(T newElement) not being supported by the iterator
			-newElement is not the right class
			-something about newElement prevents it from being in the list
		Bottom line is don't be stupid*/

		public void remove()/*(OPTIONAL) Removes the last element returned by next() or previous()
		Throws IllegalStateException if next()/previous() has not been called, or remove() has already been called per next()/previous()
		Since the method is optional, throws UnsupportedOperationException if the remove() option is not supported by this Iterator<T>*/

		public void set(T newElement) //(OPTIONAL)replaces the last element returned by next() or previous()
									  //see add(T newElement) for the things that can go wrong
	/*

	Every ListIterator<T> has a position marker known as the cursor
		If the list has n elements, they are numbered by indices 0 through n-1, but there are n+1 cursor positions
		When next() is invoked, the element immediately following the cursor position is returned...
			...and the cursor is moved forward one cursor position
		When previous() is invoked, the element immediately before the cursor position is returned...
			...and the cursor is moved back one cursor position
		So if we had a list   ele1 ele2 ele3 
							 ^    ^    ^    ^
							the cursor can be any of these (its always in-between)
	Let’s look at some sample code!
	Creates an ArrayList
	Creates a ListIterator
	Uses the ListIterator to pan back and forth through the list

	*/
		import java.util.ArrayList;
		import java.util.ListIterator;

		public class ArrayListWithListIterator{
			public static void main(String args[]){
				ArrayList<String> myList = new ArrayList<String>();
				ListIterator<String> anIterator ;
				myList.add("cats");
				myList.add("dogs");
				myList.add("mice");
				myList.add("zebras");
				myList.add("donkeys");
				myList.add("lions");
				System.out.println("The list has " + myList);
				anIterator = myList.listIterator(); // Everything we did in earlier example can be done here as well
				
				while (anIterator.hasNext()){
					anIterator.next();
				} // Now the cursor is beyond the last element.
				
				System.out.println("Traversing the list backwards using a list iterator");
				
				while (anIterator.hasPrevious()){
					System.out.println(anIterator.previous()); // each call gets the element before the
					// cursor position and moves the cursor one place left
				}
				
				anIterator.next();
				anIterator.next(); // This call to next() returns "dogs"
				anIterator.remove(); // "dogs" is gone!! The element returned by last call to next() or previous() will be gone
				anIterator.next(); // retrieves "mice"
				anIterator.add("raccoons"); // cannot be called right after another add or remove
				// adds "raccoons" after "mice" and before "zebras"
				System.out.println("Traversing the list from the element containing mice");
				
				while (anIterator.hasNext()){
					System.out.println(anIterator.next());
				}
				
				System.out.println("The new list has " + myList);
			}
		}
	/*

The Iterable<T> Interface (and ListIterable<T>)
--------
	This interface ensures that a class you are defining will give you the ability to use an iterator
		This must be used if you want to be able to use Java’s Iterator<T> interface
		This interface only contains one method, which returns an iterator over a set of elements of type T
	
	*/ Iterator<T> iterator() /*
	
	A class that implements this interface gives us access to an object that implements the Iterator interface
	You can then use the methods in that interface for traversal of the iterable collection
	
	Let’s look at a code sample!
	It just shows how Iterable<T> works, it is not really a good definition
	It does not allow multiple iterators to work on the list simultaneously

	*/
		import java.util.Iterator;

		public class IterableList implements Iterable<Object>{ //could also do IterableList<T>, so the iterator will be of type T, try!
		/* This is just to show how Iterable works. This is not a good definition.
		 * It does not allow multiple iterators to work on the list simultaneously.
		*/
			LinkedListGeneral aList;
			public class MyIterator implements Iterator<Object>{ // Inner iterator class
				public Object next(){
					Object value;
					value = aList.currentValue();
					aList.getNextNode();
					return value;
				}
				
				public boolean hasNext(){	//basic iterator needs to be able to check if there's a following value, and obtain it
					return !aList.endOfList();
				}
				
				public void remove() throws UnsupportedOperationException{
					//didn't fill this in
				}
			}
			
			public Iterator<Object> iterator(){
				MyIterator anIterator;
				anIterator = new MyIterator();
				aList.restart(); // pointing to the beginning of list
				return anIterator;
			}
			
			public IterableList(){
				aList = new LinkedListGeneral();
			}
			
			public void add(Object value){ // object is added to the end of list
				for(aList.restart(); !aList.endOfList(); aList.getNextNode());
					aList.addBeforeCurrent(value);
				}
			
			public String toString(){
				return aList.toString();
			}
		}
	/*

	Now we can use multiple things we have learned
	We can define a new interface that specifies what we want to do:
		We want to add a new object using add
		We want to be able to get an iterator object by calling the iterator method
		The idea:
			Program it using the interfaces we just discussed
				Basically a big generic iterable LinkedList, woohoo
	Fun fact: You can make multiple iterators for the same list

	Practice Assignemnt:
		Take the IterableTester that implements AddAndIterate and get it to work with LinkedListGeneric, rather than LinkedListGeneral
		That is, in the IterableList class:
			public class IterableList implements 

	6 Questions, should take 40 minutes
		Lots of short answer 
		Lots of conceptual stuff
		No recursion
		Shorter than what we've done on the midterms (25 to 30 marks)
		One optional question (out of 15 marks to write an easy class)
		One question have notes ready from a slide 
	Question Composition 
		One error correction
		One trace
		GUI (graphics and mouse events)
		Exception Handling
		Linked Lists (and iterators), practice converting generals to generics, look at his examples
		Generics
		2 Questions will be old material (polymorphism, abstracts, statics, etc.)	
	Possible Review Lecture in a small conference room 
*/