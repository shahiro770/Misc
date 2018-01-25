/* Interfaces and Inner Classes (Lecture 9) 2017-06-26

Quick Note on Override
--------
If a method is annotated with the @Override annotation type compilers are required to generate an error message 
	Unless at least one of the following conditions hold:
		The method does override or implement a method declared in a supertype.
		The method has a signature that is override-equivalent to that of any public method declared in Object.
			Signature is the method name and parameters (the heading excluding the return type)
			This means for interfaces, you can do the former but not the latter
	*/
		interface Bar { @Override int hashCode(); }
		interface Quux { @Override Object clone(); }	//not publicly declared in Object, cannot do
	/*		

Interfaces
--------
	Main problem:
		How can I ever know if an object has the capabilities (methods) that meet my requirements?
			Of course, if I know all of the details of all of the methods in the class, I know the answer
			But if not (or if I just started the class), it is nice to be able to enforce it to have a certain set of methods
				Or if you have a bunch of completely disjoint classes (shapes, people, etc.) and want them all function the same
			Interfaces are used to describe a set of methods that an object must provide
	Analogy:
		Can I view my laptop as a CD player?
		Can I view my Xbox One as a CD player?
		Can I view my car as a CD player?
		Can I view my dog as a CD player? (woah)
		What capabilities do  I need in an object such that I can view it as a CD player?
			Able to insert a CD if the tray is empty
			Play a CD if the tray has one
			Take a CD out of the tray
			Pause a CD if it is being played
		What capabilites do you need if we wish to use an object as a vehicle?
			Needs an ignition (on/off)
			Seats
			Wheels
			Steering
			Propulsion

	DEFINITION: An interface is a definition of the capabilities that a class that implements the interface must have
	Interfaces are a special sort-of-class which only includes a set of abstract methods and possibly some public final static data
	Any class that implements the interface must provide definitions of the methods specified in the interface
		In order for a car to be considered a CD player, we must be able to perform all the CD player capabilities with a car
	You can visualize an interface as a contract or a guarantee 
		The class implementing the interface will include all of the methods the interface requires
	Abstract classes and interfaces are two techniques used to ensure integrity of extended classes
		They force you to define methods to meet the requirements of the interface
	An interface is like an extreme case of an abstract class
		However, interfaces are not considered classes
			"Sort-of-class"
			It is a type that can be satisifed by any class that implmements the interface
		The syntax for defining an interface is similar to that of defining a class
			You only need to use the word "interface" where you would normally use the word "class"

	*/	public interface Sortable{...} /*

	An interface specifies a set of methods that any class that implements the interface must have
		It only contains method headings and constants
		No instance variables, no method definitions
	Serves a function similar to a base class (though again, it's not a class!!)
		You can implement an interface, which is very similar to extending a base class
		Some languages (C++) allow a class to be derived from two or more different classes (multiple inheritance)
			This is not allowed in Java, so instead we can approximate multiple inheritance through interfaces
	An interface and all of its heading must be declared public
		They cannot be given private, protected, or package access
	When a class implements an interface, it must make all the interface methods public as well
	Because an interface is a type, a method may be written with a parameter of an interface type
		That parameter will accept as an argument an object of any class that implements the interface
			i.e. just like plugging in a child class to a parameter of a parent class
	An interface already defined in Java:

	*/ 
		public interface ActionListener{
			public void actionPerformed(ActionEvent e);
		}
	/*

	Any concrete class C implementing ActionListener must include the definition of the actionPerformed method
		With exactly the same signature and return type
		When the action listener uses an object of class C, the object C must be able to handle the event
		This guarantee is provided because, since C implements ActionListener, it must have a definition for actionPerformed
		Abstract classes can implement interfaces, and they need not specify the definition of interface methods 
			Only concrete classes must do this

	
	*/
		public interface Ordered{
			public boolean precedes(Object other);
			public boolean follows(Object other); 

			/* for a pair of objects o1 and o2, ensure that o1.follows(o2) == o2.precedes (o1) */ 
		}
	/*
	
	To implement an interface, a concrete class must do two things
		It must included the phrase "implements InterfaceName" at the start of its class definition
			If more than one interface is implmemented, each is listed and separated by commas
		The class must implmement (read as "define") all the methods listed in the definitions of the interfaces(s)
			"Student implements Ordered" must define all of Ordered if it is a concrete class
	Note the use of Object as the parameter type in the following examples 

	Make use of instanceOf()

	*/
		else if (!(other instanceof hourlyEmployee)){	//could use getClass() for equality, but instanceof() is good for interfaces
			//do stuff
		}
	/*			

	As mentioned earlier, abstract classes may implement any number of interfaces
		You may give definitions of the interfaces' methods inside the abstract class, or you may leave them as abstract
			Define them in concrete classes later
		A concrete class must give the definitions for all the method headings given in the abstract class and the interface
	Subclasses of classes that implement and define methods specified in an interface need not define and implement the interface again
	An interface is very useful to define:
		Sortable (as in any object that implements this interface is able to be sorted given some implementations of sorting algorithms)
	
	*/	
		public interface Sortable {
			public boolean lessThan(Sortable anObject);
			/* You can add as many abstract methods as you want
			You may also add some public final static data as well but nothing else is allowed. 
			Store, as usual, in file Sortable.java*/ 
		}
	/*

	Sortable:
		The idea is that we want to be able to subject objects of any class implementing Sortable to sorting algorithms
		Two issues:
			How do we compare two things?
			If we know how to compare two things, how do we sort an array of these things?
		Both of these issues depend on the thing in question
		For instance, if we have {12, 3, 8, 2, 17} and we want it sorted ascending, we'll get {2, 3, 8, 12 ,17}
		Can we use the same comparisons for these ints on strings on last names? 
			i.e. a less than method on a class by class basis	
	Whenever we need to sort a collection of things, we need to make sure that it is always possible to compare the things
		Interfaces ensure that we can sort any object that implements the Sortable interface
			Requires little work, just define the lessThan method for any class
				Create a class that calls these lessThan methods to sort arrays of classes that implement Sortable
				No matter what the Sortable collection is, we can then sort it without checking the type or anything like that
					The code does not change and is very flexible
		Whats the alternative?
			5 classes, each with 20 ways to sort them
			If each individual class were to hold their sort methods, we'd have 100 sort methods total
			However, only 20 sort methods total if we have an interface
	
	*/
		public class Name implements Sortable{
			private String myName;
			public Name(String input){
				myName = new String(input);
			}
			public boolean lessThan(Sortable anotherName){ 			
				Name temp; 
				temp = (Name) anotherName;	//Name implements Sortable, you can typecast assuming it has the necessary methods
				if (myName.compareTo(temp.myName) < 0)
					return true;
				else return false;
			}
			public String toString(){
				return myName;
			}
		}

		public class Sort {	//assumes it will be sorting something of type Sortable
			public static void sortAnything(Sortable listObjects[], int numObjects){
				Sortable temp;
				int indexSmallest;
				int index1;
				int index2;
				for (index1 = 0; index1 < numObjects - 1; index1++){
					indexSmallest = index1;
					for (index2 = index1 + 1; index2 < numObjects; index2++){
						if (listObjects[index2].lessThan(listObjects[indexSmallest])){
							indexSmallest = index2;
						}
						temp = listObjects[index1];
						listObjects[index1] = listObjects[indexSmallest];
						listObjects[indexSmallest] = temp;
					}
				}
			}
		}

		public class TestGeneralSort{
			public static void main(String a[]){
				Name nameArray[] = new Name[a.length];
				for (int i = 0; i < a.length; i++)
					nameArray[i] = new Name(a[i]);
				Sort.sortAnything(nameArray, nameArray.length);
				for (int i = 0; i < nameArray.length; i++)
					System.out.println(nameArray[i]);
			}
		}	

	/*

	A problem with a simple solution (now thanks to interfaces):
		Given what we just discussed, how might we use the SortAnything method to sort a list of Student class?
			Use their major as the sorting key
			Implement Sortable

Inner Class	 Anonymous Class
--------
	Until now, we have only defined classes that were defined at the scope of a file which can be used in any valid context
	Inner classes including anonymous classes, involve defining a class inside of another class
		A class including an inner class is an outer class
		There is no specific location where the definition of the inner class must be within its outer class
		Conventionally they appear at the beginning or end of the outer class so that they are easy to find
	An inner class definition is a member of the outer class (same way instance variables and methods of the outer class are members)
		An inner class is local to the outer class definition
		The name of an inner class may be reused for something else outside of the outer class
		If the inner class is private, then the inner class cannot be accessed by name outside the definition of the outer class
	Two main reasons to use inner classes:
		They can make the outer class more self-contained, since they are defined in it
		Both classes (the inner class and outer class) have access to each other's private members
	Typically inner classes are used as "helper" classes
		If an inner class is used as a helper class (as in they are only there for support) then it should be marked as private
	Within the definition of a method of an inner class:
		It is legal to refer to private instance variables of the outer class
		It is legal to invoke a private method of the outer class
	Within the definition of the outer class:
		It is legal to refer to a private instance variable of the inner class, on an object of the inner class
		It is legal to invoke nonstatic methods of the inner class as long as you have an object to invoke the method on
		
Anonymous Class
--------
	If an object is to be created, but there is no need to name the object's class, then an anonymous class definition can be used
		The class definition is embedded inside the expression with the new operator
	Anonymous classes are sometimes used when they are to be assigned to a variable of another type
		The other type must be such that an object of the anonymous class is also an object of the other type
		The other type is usually an interface (not a class)
*/