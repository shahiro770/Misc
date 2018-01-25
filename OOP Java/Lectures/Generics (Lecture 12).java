/* Generics (Lecture 12) 2017-07-10 *NOT ON MIDTERM*

Generics
--------
	With Java 5.0, Java allows class and method definitions that include parameters for types
	Such a definition is called GENERICS
		Generic programming with a type parameter enables code to be written that applies to any class
			...with potential restrictions on the class
	Classes and methods can have a type parameter
		A type parameter can have any class type plugged in, no primitives though
		When a specific type is plugged in, this produces a specific class type or method
		Traditionally, a single uppercase letter is used to represent a type parameter, but any non-keyword identifier may be used
	ArrayList is a class in the standard Java libraries
		Unlike arrays, which have fixed length once they are made, ArrayList is an object that can grow or shrink during runtime
	In general, an ArrayList serves the same purpose as an array, except it is more flexible
		We are discussing ArrayList because it is a predefined class in Java that is defined as generic
	The class ArrayList is implemented using an array as a private instance variable
		When this hidden array is full, a new and larger hidden array is created and the data is transferred to this new array
	So why not always use ArrayList?
		Arrays are more efficient than ArrayLists (lots of overhead cause doubling of sizes)
		No convenient [] notation
		The base type of an ArrayList must be a class type, not a primitive like you do with arrays
			Not that big of a deal anymore with automatic boxing/unboxing of primitives
	In order to make use of the ArrayList class, it must first be imported from the package java.util
	An ArrayList is created and named in the same way as an object of any other class, except that you specify the base type as follows:

	*/ ArrayList<Type> aList = new ArrayList<Type>(); /*

	An initial capacity can also be specified when creating an ArrayList
		The following code creates an ArrayList that stores objects of the base type String with an initial capacity of 20 items

	*/ ArrayList<String> list = new ArrayList<String>(20); /*

		Specifying an initial capacity does not limit the size to which an ArrayList can eventually grow
	Note that the base type of an ArrayList is specified as a type parameter
	The add method is used to put an element into the ArrayList 

	*/ list.add("something"); /*

		The method named add is overloaded
		The one-argument version adds the item to the first unused position of the ArrayList
		There is also a two-argument version that allows you to specify the position at which to add the element
	The size method is used to find out how many indices already have elements in the ArrayList
	
	*/ int howMany = list.size(); /*

	The set method is used to replace any existing element, and the get method is used to access the value of any existing element

	*/
		list.set(index, "something else");
		String thing = list.get(index);
	/*

	add() vs set()
		Add only adds to either the end of the list (1-argument version) or at the specified index (2-argument version)
			Bumping all elements after it back in the list by one position
		Set will replace an element in a position where there currently exists another element
			For set, you cannot use an index where no element currently exists
			Use the size method to determine the number of elements currently stored in the ArrayList
	The tools for manipulating arrays are quite limited
		Just the square brackets notation and the length instance variable
	ArrayLists have a selection of powerful methods that can do all kinds of cool things
		It would take a lot of code to implement these tools with an array
	ArrayList Methods 

	*/	
		public ArrayList<Base_Type>(int initialCapacity)	//create an empty arraylist with initial capacity as its size
		public ArrayList<Base_Type>()						//empty constructor with initial capacity as 10
		public Base_Type set(int index, Base_Type newElement)	//set the element at the specified index
			//Returns the element as well, however method is usually used as if it were void, index must be >= 0 and < size()
		public Base_Type get(int index)						//return the element at the specified index
		public boolean add(Base_Type newElement)			//add the specified element to the end of the arraylist
			//increases size by 1, if capacity must be increased if required, returns true if add was successful
		public void add(int index, Base_Type newElement)	//add the specified element at the specified index
			//each element with an index >= the specified index is shifted up by 1
			//note that the index can < OR = to the size of the array (cause then you just adding to the end)
		public Base_Type remove(int index)					//Deletes and returns the element at the specified index
			//each element with an index > the specified index is shifted down by 1
			//often used as a void method
		protected void removeRange(int fromIndex, int toIndex)	//Deletes all the elements with indices i such that
			//fromIndex <= i < toIndex , elements with indice > toIndex are deleted appropriately
		public boolean remove(Object theElement)	//removes one occurence of the element from the calling Arraylist
			//if the element is found, each element with an index greater than theElement is shifted down by 1
			//returns true if the element is found, returns false otherwise
		public void clear()
			//removes all elements from the calling Arraylist and sets the arraylist size to 0
		public boolean contains(Object target)
			//return true if the calling arraylist contains target, otherwise returns false. Uses the method
			//equals of the object target to test for equality with any element in the calling Arraylist
		public int indexOf(Object target)
			//returns the index of the first element that is equal to target. Uses the method equals of the object
			//target to test for equality. Returns -1 if target is not found
		public int lastIndexOf(Object target)
			//returns the index of the last element that is equal to target. Uses the method equals of the object
			//target to test for equality. Returns -1 if is not found
		public boolean isEmpty()
			//returns true if the calling arraylist is size 0 (i.e. empty), false otherwise
		public boolean equals(Object other)
			//if other is an arraylist of any base type, OR ANY KIND OF LIST, then if other has the same size and contains 
			//the same elements in the same order as the calling arraylist, return true, false otherwise
	/*
		
	You will notice that some parameters are of Base_Type and others are of type Object. Why?
		The ArrayList class implements a number of interfaces and also inherits some methods from its various ancestor classes 
			ArrayList guarantees whatever it does to its objects function with its ancestor classes
			e.g. equals()
	The For Each Loop
		The ArrayList class is an example of a collection class
		Since Java 5.0, Java has added a new kind of for loop called a for-each loop (AKA enhanced for loop)
			It is a for loop that is designed to automatically cycle through all the elements in a collection (such as an ArrayList)

	*/
		for (String x: a) { //Object type followed by a variable name, followed by the array being searched (for each x in a)
			anArrayList.add(x);
		}
	/*

	Stacks
		A stack is a data structure akin to a stack of plates
			You add any new element to the top of the stack, and you also remove elements from the top
			You don’t take a plate from the bottom of a stack of plates (you can try, but you’re probably going to break some plates)
		What’s a basic stack need?
			Some push method to add to the top of the stack
			A pop method to remove from the top of the stack
			A method to check if the stack is empty
			A toString
	Sample Stack (Some Notes)
		The Stack class is made generic (it has a type parameter, and the type is T which means it can be anything)
		It is implemented using an ArrayList, which is of the same type as the stack (T)
		Note the use of ArrayList methods such as size, add, remove
		The for-each loop in the toString
		The use of the StackGeneric class in main
			Wrapper class Integer, not int
		T is a type, thats how you instantiate a generic
			T must be provided an argument

	*/
		import java.util.*;
		public class StackGeneric<T>{ // The type parameter is usually specified
			private ArrayList<T> myStack;// using 1 upper case char.
			
			public StackGeneric(){
				myStack = new ArrayList<T>();
			}
			
			public boolean isEmpty(){// Check if size is 0
				return (myStack.size() == 0);
			}
			
			public void push(T x){
				myStack.add(0, x);
			}
			
			public T pop(){
				T valueOnTopOfStack;
				if (myStack.size() == 0){
					return null;
				}
				else {
					valueOnTopOfStack = myStack.get(0);
					myStack.remove(0);
					return valueOnTopOfStack;	//returns the element removed
				}
			}
			
			public String toString(){
				String s = "The stack has " + myStack. size() + " elements:\n";
				for(T t: myStack){	//note the use of T in this for-each loop
					s += "" + t + ", ";
				}
				return s;
			}
		}
	/*

User-Defined Generics
--------
	A class definition with a type parameter (AKA a generic class definition) is stored in a file and compiled just like any other class
	Once A parameterized class is compiled, it can be used just like any other class
		However, it requires that the class type for the type parameter is specified during use
		You can instantiate the generic class as follows

	*/ Sample<String> object  = new Sample<String>(); /*

	*/
		public class Sample<T>{
			private T data;

			public void setData(T newData){
				data = newData;
			}

			public T getData(){
				return data;
			}
		}
	/*

	The type parameter is given in angular brackets after the class name in the class definition
		< > specify that a type must be provided (a type requirement)
	Any non-keyword identifier can be used for the type parameter
		However, by convention the parameter starts with an uppercase letter (T is classically used)
	The type parameter can be used like any other class type in the definition of a class
		You can make variables with it or even instantiate other generics with it (as you saw in the StackGeneric example)

Single-Typed Generics
--------
	Example below: a generic ordered pair of the same type
	
	*/
		public class Pair<T>{
			private T first;
			private T second;

			public Pair(){			//constructor heading does not include "<T>"
				first = null;
				second = null;
			}

			public Pair(T firstItem, T secondItem){	//constructor heading does not include "<T>"
				first = firstItem;
				second = secondItem;
			}
		}
	/*

	Pitfall: The constructor and the calling of the constructor appear rather different
		Although the class name in a generic class definition has a type parameter, the constructor definition uses different syntax

	*/ public Pair<T>() /* THIS IS WRONG

		A constructor uses the type parameter as parameters in the constructor, but angular brackets are not used (such as below)

	*/ public Pair(T first, T second) /*

		However, when you instantiate a generic, the angular brackets are used
	
	*/ Pair<String> pair = new Pair<String>("Happy", "Day"); /*

Multiple-Typed Generics
--------
	We have only showed single-typed generics, so far (to aid in your understanding)
		You can, however, take as many types as you like as type parameters
			Simply list multiple type parameters in a list of angular brackets, separated by commas
			double and a string
			int and a float
			float and a double
	*/		
		public class TwoTypePair<T1, T2>
		public class TwoTypePair<T, U>
	/*

	*/
		public class TwoTypePair<T1, T2>{
			private T1 first;
			private T2 second;
		}

		public TwoTypePair(){
			first = null;
			second = null;
		}

		public TwoTypePair(T1 first, T2 second){
			this.first = first;
			this.second = second;
		}

		public void setFirst(T1 first){
			this.first = first;
		}

		public void setFirst(T2 second){
			this.second = second;
		}

		public T1 getFirst(){
			return first;
		}

		public T2 getSecond(){
			return second;
		}

		public String toString(){
			return ("First: " + first.toString() + "\nSecond: " + second.toString());
		}

		public boolean equals(Object otherObject){
			if (otherObject == null){
				return false;
			}
			else if (getClass() != otherObject.getClass()){
				return false;
			}
			else{
				TwoTypePair<T1, T2> otherPair = (TwoTypePair<T1, T2>)otherObject;
				return(first.equals(otherPair.first) && second.equals(otherPair.second));	//compare both T1 and T2 of both pairs
			}
		}
	/*

Bounding Generics
--------
	In many cases it makes sense to bound (restrict) the possible types that can be plugged in for a type parameter (let’s say, T)
		For instance, maybe I only want T to be of type Comparable for a particular generic class
	
	*/ public class OurClass<T extends Comparable> {}/*

		“extends Comparable” restricts the type parameter T to a class that implements Comparable
		Attempting to plug in a type for T which does not implement Comparable will result in a compile-time error
	The restriction can be either a class name or an interface name
		If it is a class name, only that class or its subclasses can be used for the type parameter
		If it is an interface, only classes that implement the interface can be used

	*/ public class Example<T extends Class1> {}/*
	
	A bounding expression may contain multiple interfaces and up to one class
	If there is more than one type parameter, the syntax is as follows:
	
	*/ public class Two<T1 extends Class1, T2 extends Class2 & Comparable> {}/*


	



