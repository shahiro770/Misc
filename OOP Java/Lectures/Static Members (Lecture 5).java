/*Static Members (Lecture 5) 2017-05-24

Static Methods
--------
	A static method is one that does not need an object in order to be called
	A static method belongs to a class, and its definition is given inside of a class definition
	When a static method is defined, the keyword static is used in the method header
		We've seen this in main (we didn't need an object to call main)

	*/	public static returnType myMethod(params){…} /*
		
	Static methods are invoked using the class name, in the same place you would normally put an object name
		
		*/ returnedValue = MyClass.myMethod(args); /*

	A static method cannot refer to instance variables inside of its body
		Cannot invoke instance (nonstatic) methods either
			Why?
				Static methods have no objects to instance or refer to
	Static methods can only invoke other static methods or use static variables
	However, nonstatic methods can call static methods and use static variables 

	You can put main in any class
		Again, only one copy per program
		But the class containing your main method need not be a basic class with just main in it
		It could be a class that you instantiate object from
		The main method is static, so it can only call other static methods within the class

Static Variables
--------
	A static variable is a variable that belongs to the class as a whole and not to any one object
		There is only one copy of a static variable across an entire class , unlike instance variables (each object has its own copy)
	All objects of the class can read and modify a static variable belonging to the class
	Static methods cannot access instance variables, but static methods can access static variables
	Static variables are declared like instance variables, except you add the modifier static
	
	*/ private static int myStaticVariable; /*

	Static variables can be declared and initialized at the same time, just like any other variable

	*/ private static int myStaticVariable = 0; /*

	If not explicitly initialized, a static variable will be automatically initialized to a default value where possible
		Static boolean variables are initialized to false
		Other primitives are initialized to their zero
		Object static variables are initialized to null
	It is always preferable to explicitly initialize static variables rather than leave them to defaults
	Static variables should almost always be defined as private, unless they are also a constant
		The value of a static defined constant cannot be altered (like any constant), therefore you can safely make it public 
		In addition to static, the declaration for a static defined constant must include the modifier final, 
			Indicates that the value cannot be changed (again, like any other constant)

	*/ public static final int BIRTH_YEAR = 1987; /*

		When referring to such a defined constant outside its class, use the name of its class in place of a calling object

	*/ int year = MyClass.BIRTH_YEAR; /*

Wrapper Classes
--------
	Wrapper classes provide a class type corresponding to each of the primitives
		Integer, Double, etc.
		This makes it possible to have class types that behave like their primitive counterparts
	The wrapper classes are Integer, Byte, Short, Long, Float, Double, and Character
	Wrapper classes also contain a number of useful predefined constants and static methods
		i.e. int is 4 bytes, Integer class is much larger because it has more overhead and etc.
	
	DEFINITION: Boxing is the process of going from a value of a primitive type to an object of its wrapper class
		You can convert a primitive value to an “equivalent” class type value
			Create an object of the corresponding wrapper class using the primitive value as an argument
			The new object will contain an instance variable that stores a copy of the primitive
		Unlike most other classes, wrapper classes do not have a no-argument constructor
	
	*/ Integer integerObject = new Integer(42); /*

	DEFINITION: Unboxing is the process of going from an object of a wrapper class to its corresponding primitive
		The method for converting an object from the wrapper class Integer to its primitive (int) is .intValue()

	*/ int i = integerObject.intValue(); /*

	Automatic boxing and unboxing:
		Starting with Java 5.0, its all automatic
		Instead of creating a wrapper class object using new (what we did before), it can be done as an implicit type cast

	*/ Integer integerObject = 46; /*
	
	Instead of having to invoke the appropriate method (like .intValue() for integers), we can do something else
		To convert from an object of a wrapper class type to its associated primitive, the value can just be converted automatically

	*/ int i = integerObject; /*

	Wrapper classes have static methods that convert a correctly formed string representation of a number to the given type
		We have seen this before when discussing tokenization and regex
		The method Integer.parseInt for the primitive int
			Takes a string and returns an integer equivalent, assuming that the string is a well-formed int
		As Integer is a class, parseInt is a static method and therefore does not need an object of class Integer to be called
			It simply needs a reference to the class

The Constant null
--------
	null is a special constant that may be assigned to a variable of any class type

	*/ YourClass yourObject = null; /*
	
	It is used to indicate that the variable has no real value (yet)
		It is often used in constructors to initialize class type instance variables when there is no object to use
	null is not an object, it is a placeholder for a reference that has not been allocated memory
		Because it is like a memory address, use == and not .equals to test a variable
		
	*/ if (yourObject == null) { /*Don't try to do stuff with yourObject"*/} /* 

The new Operator and Anonymous Objects
--------
	The new operator invokes a constructor that initializes an object, and returns a reference to its memory location 
		This reference can be assigned to a variable of the object’s class type
	Sometimes the object created is used as an argument to a method and never used again
		In this case, the object need not be assigned to a variable or given a name
	Any object whose reference is not assigned to a variable is called an ANONYMOUS object

Now we make our made up "Person" class
--------
	We will have a class called Person over the rest of this lecture
	Keep in mind what we have already seen about constructors, instance variables and methods, privacy, etc.
	Prepare for a few interesting considerations when designing a class, their implications, and potential solutions
	The person will have the following properties: name, a birth date, and a date of death

Class Invariants
--------
	A statement that is always true for any object of a given class is called a class invariant
		A class invariant can help to define a class in a consistent and organized way
	E.g. make sure the person class always has date of birth before date of death (make a method to check this consistency)
		Birth cannot be null, death can be

The .equals() Method
--------
	We saw this in our discussion of String class objects, as it is used to compare non-primitives
		For a string (or any object),the equals method performs a value-by-value comparison to see if objects are equal by value
			Rather than by reference (address), which is what == does with objects
	To compare Person objects, therefore we must define an equals method that compares Person objects, value-by-value
		Thus, compare all instance variables and if they are all the same, return true
		Need to use .equals() for nonprimitives, and == for primitives, always

The .toString() Method
--------
	Like the equals method, the Person class toString method invokes toString of some of its members
	The goal of toString is to print out a description of an object of a given class
		The information printed should be relevant
		The information printed may need to be specifically formatted 
			Required in the event that toString output will be going in a file or somewhere where it must be parsed

Copy Constructors
--------
	A copy constructor is a constructor with a single argument of the same type as its class
	Not all classes have copy constructors, but it can be a useful tool
	A copy constructor should create an object that is a separate, independent object
		i.e. the instance variables set such that it is an exact copy of the argument object
			That is, after using the copy constructor, .equals() on the new object and the argument must return true
	As person contains objects as instance variables (that is, not just primitives), it is dangerous to just copy the values as is
		In contrast, Date only has 3 primitive instance variables, nothing fancy needs to be done
	If the object type instance variables in Person were merely copied, they are still referring to instance variables of the original 
		It is exactly like how passing references by value works in Java (it just copies the address of the value)
			Which means we can modify them, but if we replace them altogether the reference is changed (this is bad)
		You must use the new keyword to create independent instances of those variables in Person

	*/ 
		born = original.born; //BAD!
		born = new Date(original.born); //GOOD! 
	/*

Mutable and Immutable Classes
--------
	DEFINITION: An immutable class contains no methods other than constructors that change any of the data in an object of the class 
		The objects of such a class are immutable objects
		It is perfectly safe to return a reference to an immutable object because the object itself cannot be modified in any way
	Good example: the String class and its objects
		As String objects are immutable, returning their reference is fine
		The only thing you can do with a string is replace its reference, which does not change the original reference
	DEFINITION: A mutable class contains public mutator methods or other public methods that can change data in its objects
		Its objects are called mutable objects
	Never write a method that returns a mutable object, it’s bad form
	Instead, use a copy constructor to return a reference to a completely independent copy of the mutable object
		This ensures that the original stays intact

	When writing a program, it is very important to ensure that private instance variables remain truly private
		Particularly if you never want a value to be modified outside of the class after initialization
			For a primitive type instance variable, just adding the private modifier should ensure that there will be no privacy leaks
		For a class type instance variable, however, adding private alone is insufficient
			When you use a getter to give it to a caller, you are giving the caller a reference to the actual object
				This means they can modify it, which means making it private it pointless

Stopping Privacy Leaks
--------
	We just observed how poorly defining a constructor can result in a privacy leak 
		Being able to modify Person A’s instance variables through a copy-constructed Person B
	A similar problem can occur with poorly defined accessor or mutator methods

	*/
		public Date getBirthDate(){
			return born;	//FAIL
		}

		public Date getBirthDate(){
			return new Date(born);	//SUCCESS
		}
	/*

Deep and Shallow Copying
--------
	A deep copy of an object is a copy that, with one exception, has no references in common with the original
		The only exception is that references to immutable objects are allowed to be shared
			Why? Because you can’t modify them, so no harm
	Any copy that is not a deep copy is called a shallow copy
		Shallow copying can create dangerous privacy leaks in a program
			Leads to properties of objects being modified when you may not want them to be (likely by accident)

MIDTERM
--------
	Definitely 70 Lines of code, find the x number of mistakes
	Definitely Write a basic class/method
	Simple one liner questions (T/F, tell the output)
	Definitle a trace Tracing
	Some regex



			
