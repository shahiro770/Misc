/*Object Oriented Concepts (Lecture 3) 2017-05-15

Classes
--------
	Classes are the most important language concept in OOP languages
	Programming in Java consists of defining any number of classes
		Every program has at least one class
		Any supporting package consists of classes
		Any programmer-defined type is a class
	They big in java
	Today we will learn how to define your own classes and to make instances (objects) of any class
	DEFINITION: A class is a special programmer-defined type, and variables can be declared of any class type (some restrictions apply)
		If A is a class, the following phrases are the same:
			b is of type A
			b is an object of the class A
			b is an instance of the class A

	Classes vs. Primitives
	--------
	DEFINITION: A primitive is a single piece of data
		Only has a value, no other methods or properties within it
	A class object can have many pieces of data (its properties) and actions (its methods)
		All objects of a given class have the same methods
		All objects of a class have the same set of data pieces, but their values can differ
		The properties describe the object
		For instance, consider a class called Rectangle with properties length, width, and colour
		*/ r1 = new Rectangle (10,10,blue); /*

	Contents of a Class
	--------
	A class definition specifies the properties and methods that all objects of the class will have
	The properties and methods are also known as the members of the object
	Properties in Java (unlike languages such as C#), are also known as fields or instance variables
	Declarations of properties and definitions of methods can occur in any order within a class (usually properties at top though)

	The “new” Keyword
	--------
	We need a way to instantiate an object of a given class type
		Instantiate = create an instance of
	As classes are just types, we declare a variable of a given class the same way we would with primitives (like ints or booleans)
	The new operator must then be used to create the object and we assign it to the variable of the same type 

	*/
		Classname class
		class = new Classname (arguments)
		//or
		Classname class = new Classname (arguments) 
	/*

	new does not define scope, where the object is created is what defines its scope
	
	Instance Variables and Methods
	--------
	Instance variables can be defined as below, and they are defined within the scope of the class but outside of any methods
		Ignore the words "public" or "private" for now, we will get to those later 

		*/
			public String instanceVariable1;
			private int instanceVariable2;
		/*

	Instance method definitions are divided into two parts: the heading and the body
	Methods are invoked using the name of the calling object and then the method name
		Invoking a method is equivalent to executing the method body (just like C functions)
		The below method would be invoked using "classVar.myMethod(...)" 

		*/
			public void myMethod(…){
				//do stuff here
				//compute stuff there
			}
		/*

	Methods can simply perform an action or they can compute and return a single value
		Methods that only perform an action are methods that return nothing (type void)
		Methods that compute and return a value can return objects or primitives
			Need to return multiple values in a method? 
				Create a class that has the relevant properties and return an object of that type
	Methods should be responsible for single objectives, maximizes code reuse
	Methods can differ in their definition and how they are invoked
	Methods that return values must specify the return type in the heading
		A void method must use the keyword void in its heading
	If a method heading states that a method should return a value, ALL code paths must return a value
		There must be no possible way for the code in the method to execute without returning a value
		This is enforced at development time (compile time)
	The main method is a void method (Java is hardcoded so it can't be anything else)
		A basic application in Java is just a class that has a main method
	The runtime system finds and invokes the main method in a program
	The main method has the following heading:  

		*/ public static void main(String[] args) /*

	The body of either type of method contains a list of declarations and statements enclosed in a pair of braces

	*/

		public returnType swag(){
			//declarations and statements
			return returnTypeValue
		}

	/*

	The body of a method that returns a value must contain one or more return statements
		A return statement specifies the value returned, and terminates the method invocation
	A void method does not need to have a return statement, but do it if Robert Kent's words echo through your soul every night
	An invocation of a method that returns a value can be used as an expression anywhere a value of the return type can be used

	*/ 
		someVariable = object.yolo() //someVariable must have the same type as the return type of .yolo() 
	/*

	Any method can be used as a void method
		Methods with return values may perform some actions and the return value does not need to be kept
			Though usually you'd want a return value kept
		Invoke the method as if it returned void 

		*/ object.yolo() /*

Basic Java Programming Constructs
--------
	A variable declared within a method definition or in a flow control block
	All variables declared in main are local to main
		In other words, their scope is the main function, and they only are accessible within the main function
	Blocks
		A block is another name for a compound statement, which is a set of Java statements enclosed in braces {}
		A variable declared inside of a block is local to that block, and cannot be accessed outside of a block
		Only one variable with a given name can be declared in a given block
			It will give you a development time error if you try to make two or more variables with the same name in the same scope
	Global Variables
		Java does not have global variables (variables accessible by any class in a program)
			The highest scope for which a variable can be accessed is within a class
			Any method within a class can access any instance variable within the class
			Objects of different classes need a reference in order to access variables within an object
		If a method has a local variable and the class has a variable of the same name...
			...then any reference using that variable name will refer to the local 
	For Loops
		You can declare a variable within the initialization section of a for loop
			Some versions of C allow it too (e.g. C99)
			Variables declared within the initialization of a for loop are local to that for loop (cannot be accessed outside of it)

Parameters and Arguments
--------
	If a method has no parameters, the method heading must contain an empty set of parentheses
	Methods can recieve additional data via a list of parameters in order to perform their actions
		We call these parameters formal parameters
	A parameter list provides a description of the data required by a method
		The number and types of data needed
		The order in which they must be given
		The local name for them within the method
	When a method is invoked, the appropriate values must be passed to the method
		We call these the arguments
			We also call these actual parameters (Arguments are actual)
		They are not interchangable (don't be dumb even if the convention is dumb)
	If the types of a parameter and argument do not match perfectly, Java will try to convert the type of the argument, if possible
		Implicit conversion of primitives can happen as follows (a given type can be converted automatically to any type right of it)
			byte -> short -> int -> long -> float -> double
			OR
			char -> int -> long -> float -> double 
				Cannot go backwards
		Any other conversion requires an explicit cast

		*/
			long j = 123L;
			int k = (int)j; //watch out for overflow though  (huge long will blow up your int)
		/*	

	The value of each argument (always and strictly, the value) is plugged into the corresponding parameter
		Not the address, not the reference, ALWAYS VALUE (so you pass in x, if x = 5, then 5 is passed in)
		"call-by-value"
		Sometimes called pass-by-value in C
	How does pass-by-value happen in C?
		You pass in argument, a shallow copy of the value is sent in (cannot change the argument's value)
			Locally you can modify the value
			Key here is COPY
				So you can modify the local copy, but cannot modify original
	Java's call-by-value is illustrated by this basic example (which actually does nothing at all) 

	*/
		public void badSwap(int v1, int v2){
			int temp = v1;
			v1 = v2;
			v2 = temp;	//can't swap this way in Java with primitives
		}
	/*

	That example was with primitives, but what about when we use objects?
		You can swap the properties of objects (its members)
		Java is always passed by value, but you can modify the members inside any method (you have access to their references)
		Pass by reference does not occur in Java, however when you pass in an object, you pass in the address of the object
			Addresses are copied, so they can be referred to, and as such, the properties of the objects can be modified 
			Can't change the objects themselves (Change the copy address, lose access to the actual objects)
	Even references are call-by-value in Java
	Parameters act as placeholders that are filled in by the values of the corresponding arguments

The “this” Keyword
--------
	All instance variables are understood to have "the calling object" in front of them, when referred to within the class itself
	If an explicit reference to the calling object is needed you can use the "this" keyword
	In a  method containing a local variable with the same name as an instance variable, all references refer to the local one
		Use "this" to not call the local, but the instance

	*/
		public class SomeClass{
			private int theVar = 14; //instance variable

			public void someMethod(){
				int theVar = 7; //local variable
				this.theVar += 2; 	//increments the instance variable
			}
		} 
	/*

The Boolean Value
--------
	We have a boolean type (woahhh its a primitive)
	No more C #define bs
	Value is true or false

“==“ and equals
--------
	All objects in Java (under the hood) are of type Object (more on this later)
		The Object class has a set of methods that are useful in everyday computing, for any object
			"equal" is one such method
			Thus every single object in Java has an equals method (because all are of type Object)
		Using "==" is not a sufficient test for equality of two objects because it is sufficient for primitives or constants 
			You'll just compare the addresses
			This goes back to object creation (you're making a reference)

The toString Method
--------
	Similar to equals, it is available to all objects because they are of type Object
		For basic objects or primitives, the description might just be a single value
		For more complex objects, a thorough description is useful for testing a formal output
		By default prints the address, but you can @override them to make your own
	Defining a toString method 

	*/
		public String toString(){
			return ("Student Name: " + name);
		}
	/*

Testing Methods
--------
	When developing, each method should be tested in a program in which it is the only untested code element
		This way, if there is an issue you know exactly why
	One method often invokes (depend on) other methods, so a good way to do this is to first test lower-level methods
		Test higher-level methods later
			Called bottom-up testing
		Sometimes you might need to test a method before another required method is completed or well-tested
			In this case, you can simplify or simulate return values from the lower-level method 
				or use a simplified version of the lower-level method
					This is called a stub (make a simplified version of the method)

Information Hiding and Encapsulation
--------
	DEFINITION: Information hiding is the practice of separating how to use a class from the details of its implementation
		Abstraction is a related term, used to express the concept of discarding details in order to avoid information overload 
			Black-box methodology (you put something in, get something out, don't care how it gets done)
	DEFINITION: Data and methods of a class are combined into a single unit (an object of a given class)...
		...hiding implementation details  yet providing all relevant functionality is known as encapsulation
		Knowing the details is unnecessary becauase interaction should occur through a well-defined and simple interface between classes
			An interface is how things interact
		You can hide details of a class by marking properties or methods within a class as private
			Keep them under the hood, keep things clean

Public and Private Modifiers
--------
	The modifier public means there is no restriction on where an instance variable or method can be accessed or used
	The modifier private means that the instance variable or method cannot be accessed by name outside of the class
		It is generally a good idea to make all instance variables private
	Most methods are public, providing controlled access to the object
		Usually methods are only private if they are used for helping other methods in the class
		For instance, a geometric or physics computation that is relevant within the class but not outside of it
	Within the definition of a class, private members of any object of the class can be accessed
		Not just private members of the calling object

Important Acronyms
--------
	API
		Application Programming Interface
		The API for a given class is the description of how to use that class
		A programmer needs to only read the API of a class in order to know how to use it, assuming the class is well-designed
	ADT
		Abstract Data Type
			More on this later, but think "Shape" vs "Circle"
				Shape is abstract wheras Circle is concrete
				We'll leave this abstract, for now (ba dum tsss)

Accessors and Mutators
--------
	Accessor methods allow the program to obtain the value of an object's instance variables
		Data can be accessed but not changed
		"Getters"
		Formatted as get____
	Mutator methods allow the programmers to change the value of an object's instance variables in a controlled manner
		Incoming data can be tested or filtered as needed
		"Setters"
		Formatted as set____
	Mutator methods could issue an error message and terminate the program if they are given erroneous or nonsensical values
	Alternatively, mutator methods can return a boolean value
		This is useful for notifying a caller whether the mutator method was successful or not
			The caller can then handle the situation based on the boolean value returned to it

Overloading
--------
	Overloading is when two or more methods within a given class have the same method name
		Only works if the two definitions of the method have different method signatures
			The signature of a method consists of the name of the method and its parameter list
				Note that return type is not listed here
			Different signatures means different name or different number and types of parameters
				You cannot have two methods with the same signature differ only by return type
					Machine would not know which method is being called
	You cannot overload operators in Java
		Some languages like C++ let you do this, Java does not

Constructors
--------
	A constructor is a special kind of method that is designed to initialize an instance of an object
	A constructor and its class have the same name
	A construct has no return type (no, not even void)
		Constructors can be overloaded, in fact they are overloaded more often than not
	Constructor is called when creating an object of that class type, using the keyword "new"

	*/className object = new className (args); /*

	The name of the constructor and its list of arguments follow the keyword
	This is the only way you can invoke a constructor
		You cannot invoke a construct in the same way that you invoke an ordinary method
	If a constructor is invoked again (using new) on a previously-defined object, the original object is discarded and a new one is made
		Therefore to change the values of an object, use a mutator method
	You can invoke methods inside of a constructor
		The first action taken by a constructor is to create an object with instance variables
		Therefore, it is legal to invoke methods of the created object inside of the definition of a constructor 
		For instance, you could use mutator methods to set values of the instance variables for the object
			You can even invoke constructors of other objects inside of a constructor
	The constructor already has a reference to the created object using "this"
		You can use this.instanceVariable to set or get the values of instance variables for the object you are creating
	Java automatically includes a default, no-argument constructor that takes no args and performs no initialization... 
		...but does create an instance of the object
	Always define your own no-arg constructor that performs an initialization, to avoid potential issues with unintialized variables

		*/ this("Name Unkown"); /*

	Empty constructor would forward a preset value to another constructor, passing in "Name Unknown"
	If you include any constructor in your class, Java will not provide the default no-arg constructor (error will show up)
	
	*/ 	
		public class ClassName{
			//static variables
			//instance variables
			//static methods
			//one or more Constructors
			//methods
		} 
	/*

*/
