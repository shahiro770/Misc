/* Midterm Review
----------
	Focused on everything from lectures 7 to 10
		Inheritance
		Polymorphism and Abstract Classes
		Interfaces and Inner Classes
		Frames, GUIs, and Events
		Surprise! I’m not putting Graphics and Mouse Events on it! (Together – “Thanks Ryan!”)
		However, due to the nature of Java programming, it’s cumulative 
			Some things from before inheritance will definitely be on here – strings, static members, classes, etc.

	The things that the class generally struggled with from midterm 1?
		Statics?
		String manipulation?
		Method overloading?
		How to define a class?
		Arrays?
		.equals vs. ==?

Topics in Inheritance
----------
	What is inheritance? 
		When creating a new class, we indicate that the class (the subclass) inherits from some other class (the superclass)
		The instance variables and methods are all taken from the superclass, instead of starting from scratch
		Why is it useful?
			Duplication of effort, among other things instead of creating all from scratch 
				Makes life easier 
				Without inheritance, you would have to remake many of the methods and variables in Person
	Subclasses and Superclasses
		What is a subclass?
			The class that inherits everything
		What does the subclass inherit from the superclass (base class)?
			All instance variables and methods (both public and private, static and non-static, although can't reference private stuff)
	The Object Class
		What is the class that all other classes inherit from? Name some methods that all classes inherit from it.
			Object
				Everything inherits the toString() equals(), getClass()
	Overriding vs. Overloading
		Overload is an alternate definition
		Overriding is a completely new definition
	Can we access methods or variables from a superclass? If so, how?
		super.methodName(whatever it needs)
	Can we access private instance variables belonging to a superclass? If so, how?
		Not by name
		Must use a public accessor and mutator method
	The final Keyword
		C
	Creating a Subclass
		*/ public class HourlyEmployee extends Employee /*
	Casting
		Important concept: an object of a subclass has more than one type
			Therefore you can plug in any sublcass as an argument where a superclass is a parameter (BUT NOT THE REVERSE)
				its unidirectional
			Also can explicitly cast if you know something carries both types
		*/
			Food someFood;
			Fish myFish;	//Fish inherits from Food
			myFish = new Fish(...); 	//this works cause we know Fish is of type Food
			someFood = myFish;

			someFood = new Food(...); 	//this doesn't cause we don't know if Food is of type Fish
			myFish = someFood;	
		/*
		Assume we have a class called A, and another called B. 
			Outside of A and B (let’s say, in a third class called C), we have a variable called b of type B.
			B extends A, and B has overridden a method called m, which is defined in A. 
			Can we call A’s version of m in our code in class C? If so, how?
				super.m() (because B is of type B which inherits from A)?
					NOPE, can't use super in an object of the derived class, must be inside the derived class
					i.e. not inside C, but inside B
		Assume we have classes A, B, and  C, where B extends A and C extends B. 
		A has a method m which is overridden in B and C. In C, we want to call A’s m. Can we use super somehow to access A’s m?
			Nope, super can only call the immediate parent
			You can't chain super calls 
		How do we find out if a variable, c, is of type B?
	*/		
		if (c instanceof B){
	
		}
	/*
		How do instanceof and getClass differ?
			instanceof checks if a class is of a specified type
			getClass returns only the type of the subclass (i.e. not any of the parents)
				getClass is final, cannot be overriden
	Privacy Modifiers
	*/
		public int v1		//can be accessed by any class
		int v3				//can be accessed by anything in the package, but not children
		protected int v2	//can be accessed by anything that derives from the class, or package
		private int v4		//can only be accessed within the class
	/*


Topics in Polhymorphism and Abstracts
----------
	Downcasting
	*/
		Potato p;
		Food f;
		f = p;

		if (f instanceof potato){
			((Potato)f).fry();
		}
		//compiler will throw error if you do not check to downcast
	/*
	Late and Early Binding
		early/static binding (they synonomous)
		late/dynamic binding (they synonomous)
	*/
		simple.toString();
		discount.toString();

		sale n = discount;
		n.toString();	//what happens? Lowest level class's toString is called, but in static, whatever the object is at compile time
	/*
		Polymorphism is enabled by late binding (allows you to call methods of descendents at the time needed)
			The ability to associate many meanings to one method name
				Allows changes to be made to method definitions in the derived classes
			Call is always forwarded to lowest level
			One reference can take on many forms (latin)
	Lecture 8 code samples
		Usage of this to call another constructor within the class
	Abstract classes
		has a mix of abstract and non abstract methods in import junit.framework.TestCase;
		Cannot be instantiated
	Inner classes
		A class within a class
		if public, they can be accessed outside of the class containing it
		can access each other's private instance variables
		We use them because everything is contained
	Anonymous inner class
		You use new
		No name is the key thing
		Less writing overhead, code flexibility and maintainability (no huge if and else or separate class for every button handler)
	Interfaces
		No instance variables but yes constants (public final static stuff)
		An extreme version of abstract class, guarantee uniformity
		Can implement any number of interfaces (multiple inheritance almost)
		You better know how to write One
	Swing, Structure of Frame-based Applications, GUIs, Events and Event Handling
		Just know all of these, just know them and make them
		Make a JFrame 
3 or 4 Questions
	No recursion (bless)
	Error correction (anything, casting)
	Tracing (Polymorphism)
	Write your own class or interface, or subclass, or anything
	4th question easy trivia
	static
	No regex

	Tracing Question
	Fix Errors
	Write class
	Short Answer (optional that can be swapped in to one of the 1st three questions, only answer with conviction)
		Worth less than the first three questions, so guaranteed loss, but at least something
*/