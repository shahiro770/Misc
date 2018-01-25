/*Inheritance (Lecture 7) 2017-05-31

Inheritance
--------

	Why is it important?
	--------
	Consider the following two classes
	
	*/
		public class Person{
			private String name;
			private int age, income;
			private String address;
			//constructors, etc…
			public double computeTax(){
				//definition of method
			}
			//other methods as needed
		}

		public class Student{
			private String name;
			private int age, income;
			private String address;
			private String major;
			String grades[];
			//constructors, etc…
			//other methods related to students
			//other methods as needed
		}
	/*

	Why is it useful?
		Duplication of effort, among other things instead of creating all from scratch 
			Makes life easier 
				Without inheritance, you would have to remake many of the methods and variables in Person
				With inheritance, all the of person related methods and variables are supplied, no duplication, just add student stuff

	What is it?
	--------
		When creating a new class, we indicate that the class (the subclass) inherits from some other class (the superclass)
			The instance variables and methods are all taken from the superclass, instead of starting from scratch
		Object is a class that is automatically and ultimately the superclass of all classes
		Examples 
			A subclass Student inherits from superclass Person
			A subclass CSStudent inherits from superclass Student
				CSStudent is called a direct subclass of Student, which means it is also an indirect subclass of Person
		Java only allows single inheritance
		Showing inheritance using class diagrams
			Example (imagine UML diagrams (Unified Modeling Language used to visualize object orient programming))
				A computer science student is a student
				Since all students are persons, a computer science student is, by transitivity, a person as well
				This is conveniently shown in a UML diagram (Unified Modeling Language)
			Inheritance allows construction of hierarchies
				things -> animal -> dog
					   -> food -> fish 
                               -> fruit
                All of these are things
        Inheritance is one of the main techniques of object oriented programming
        	Some languages allow for multiple inheritance
        Using this technique, a very general form of a class is first defined and compiled
        	More specialized versions of the class are defined by adding instance variables and methods
			The more specialized classes are said to inherit the methods and instance variables of the general more general class
		Inheritance is the process by which a new class is created from another class
			The new class is called a derived class or subclass
			The original class is the base class or superclass
		A subclass automatically has all the instance variables and methods that the superclass has
			It can have additional methods and/or instance variables as well
		Inheritance is advantageous as it allows code to be reused, without having to copy it into the definitions of the subclasses
		
	Subclasses
	--------
		When designing certain classes, there is often a natural hierarchy of grouping them
			Employees (hourly and salary employees)
		A subclass is defined by adding instance variables and methods to an existing class
		The phrase "extends BaseClass" must be added to the definition of the subclass

		*/ public class HourlyEmployee extends Employee /*
		
		When a subclass is defined it is said to inherit the instance variables and methods of the superclass that it extends
		A subclass is defined by taking another already defined class, and adding (and/or changing) methods and/or variables
			The derived class inherits all from the base class:
				 public methods
				 public and private instance variables
				 public and private static variables 
			The derived class can add more or change inherited instance variables, static variables, and/or methods
		A derived class automatically has all the instance variables, static variables, and public methods of its base class
			These members from the base class are inherited
		Definitions for the inherited variables and methods to not appear in the derived class
			The code is automatically reused without having to explicitly copy it
				Unless the derived class redefines one or more of the base class methods or variables (more on this later)
		
		Parent and Child Classes
			A superclass that is extended do define a subclass is also often called the parent class
			The subclass is then called a child class
			These relationships are often extended such that a class that is a parent of a parent is called an ancestor class
				If class A is an ancestor of class B then class B is a descendent of class A

The Object Class
--------
	In Java, every class is a descendent of the class Object (we discussed this to some extent before)
		Every object of every class is therefore of type Object and a subclass of Object
		Extend from Object to obtain toString() and equals()
		While these methods inherit from some ancestor (Object or some other child), these inherited methods can be overridden
			Give more appropriate definitions for certain objects

Overriding (different from overloading)
--------
	Although a cubclass inherits methods from the superclass, it can change or override an inherited method if necessary
		To override a method definition, a new definition of the method is placed in the subclass definition
			Just like any other method that is added to the subclass

	*/	
		public String toString(){	//method definition for Person
			//definition of method
			//includes name, age, income, etc
		}
		public String toString(){	//overriden definition for Student, a child of Person
			//describes the student, includes major and stuff
		}
	/*

	Do not confuse overriding a method with overloading a method (overload is an alternate definition, override redefines original)
	If the same method or variable is available in both the superclass and subclass , subclass reference overrides superclass
		E.g. B inherits from A, m1() and instanceVar1 in B override m1() and instanceVar1 in A
			The use of super allows us to access overridden methods or variables from the superclass
				super.instanceVar1 or super.m1() allows us to access the superclass version of each

	*/
		public class Human{
			String name;
			…
		}
		public class Student extends Human{	//subclass example
			String university;
			…
		}
	/*


	Final
	--------
		If the modifier final is placed before the definition of a method in a class, the method may not be redefined (overridden) 
			This is true for any subclass of that class
		If the modifier final is placed before the definition of a class, then no class may derive from it
		

Creating subclasses
--------
	Rules for creating subclass objects
		Superclass component of the object is always created before the subclass components under the hood
		Before creating an object of class Student, an object of class Human must be created 
			In the event human is a subclass of something else (Organism class), an object of the parent is made first and so on
	You can use super in a constructor of a subclass to call a constructor of its direct superclass
	Only the first statement in a constructor for a subclass can be a call to a constructor of its direct superclass
	If a constructor for a subclass does not contain an explicit call to a constructor of its direct superclass...
		Implicitly the constructor for the superclass having no argument will be called
		If the superclass does not have a no-argument constructor defined, but it does have constructors defined taking arguments...
			You must call super in the constructor with the arguments matching a constructor that has been defined

	*/
		public class Food{
			String name;
			int calories;
			Date expiryDate;
			public Food(String foodName, int numCalories, String expiryDate){
				name = foodName;
				calories = numCalories;
				expiryDate = new Date(theExpiryDate);
			}
		}

		public class Fish extends Food{
			String freshOrSaltWater;
			public Fish(String fishName, int numCalories, String expiryDate, String freshOrSalty){
				super(fishName, numCalories, expiryDate);	//Super to denote constructor of immediate superclass
															//the Fish doesn't exist until all components are done
				freshOrSaltWater = freshOrSalty;
			}
		}
	/*

	The super constructor
		A call to the base class constructor can never use the name of the base class, it must use super instead
		A call to super must be the first action taken in a constructor definition
		An instance variable cannot be used as an argument to super, it must be a variable local to the constructor
		An explicit call to super should almost always be used in a constructor within a subclass
			Usually have a defined constructor for a superclass, so you cannot leave it to implicitly call the no-argument constructor
	The this constructor
		The keyword this can be used as a name for invoking another constructor within the same class
			The same restrictions on how to use a call to super apply to the this constructor
		To call to both super and this, the call using this must be made first
			Then the constructor that is called must call super as its first action

	Use of private instance variables from the base class causes a pitfall
		An instance variable that is private in a class is not accessible, by name, in the definition of a method in any other class
			Not even in a derived class
		E.g. an object of Dog type cannot access the private instance variable hunger by name, even if it is inherited from Animal
		Must use public accessor and mutator methods from the base class to access private stuff
			Animal would need getHunger() and setHunger()
	If private instance variables of a class were accessible in method definitions of a subclass, it would be bad
		Any time someone wanted to access a private instance variable, they would just create a subclass and access it from that class
			This would allow private instance variables to potentially be changed by mistake or in inappropriate ways 
	Similarly, private methods of a base class are like private variables, they are not directly available
		They are only usable by a subclass if a public method in the super class invokes them

Casting
--------
	Important concept: an object of a subclass has more than one type
	An object of a subclass has the type of its class (obviously), but it also has the type of its superclass
	More generally, an object of any class has the type of every one of its ancestor classes
		An object of a subclass can be assigned to a variable of any ancestor type
			THIS IS HUGEEEEEEEE

	An object of a derived class can also be plugged in as an argument in place of any of its ancestor classes
		A derived class object can be used in any place that an object of any of its ancestor types can be used
	This relationship is unidirectional – an ancestor type can never be used in the place of one of its derived types
	
	*/
		Food someFood;
		Fish myFish;				//Fish inherits from Food
		myFish = new Fish(...); 	//this works cause we know Fish is of type Food
		someFood = myFish;

		someFood = new Food(...); 	//this doesn't cause we don't know if Food is of type Fish
		myFish = someFood;	
	/*

	What if we want to go from Food to Fish?
		Valid, but explicit casting is needed
	
	*/ 
		Food someFood;
		Fish myFish;
		myFish = new Fish(…); 
		someFood = myFish;
		myFish = (Fish) someFood;	//we know someFood is of type Fish, so we can explicitly cast it as type Fish
	/*

	Bottom line:
		Subclass to superclass casting is always possible and done implicitly
		Superclass to subclass or from one class to another where no superclass-subclass relationship holds is generally not possible
		The case of casting x = y, where x is in subclass X and y is in superclass Y...
			Is only possible if y is pointing to an object which may be cast as an object of class X
				This is the case where explicit casting is needed

Miscellaneous Topics
--------

	Protected and Package
	--------
	If a method or instance variable is modified by protected (not access modifiers public or private), it can be accessed by name:
		Inside its own class definition
		Inside any class derived from it
		In the definition of any class in the same package
	The protected modifier provides very weak protection, compared to private
		It allows direct access to any programmer who defines a suitable derived class
		Therefore, instance variables should normally not be marked as protected
	An instance variable or method definition that is not preceded with a modifier has package access
		Package access is also known as default or friendly
	Instance variables or methods having package access can be accessed by name inside the definition of any class in the same package
		Neither can be accessed outside of the package
	Note that package access is more restricted than protected
		Package access gives more control to the programmer defining the classes
		Whoever controls the package directory (or folder) controls the package access
	So in summary:
		If Class A, B, and C are part of package somePackage and class D and E are not

	*/
		public class A{
			public int v1
			protected int v2	
			int v3				//package access
			private int v4		//private things are private
		}
		public class B{
			//can access v1
			//can access v2
			//can access v3
			//cannot access v4
		}
		public class C extends A{
			//can access v1
			//can access v2
			//can access v3
			//cannot access v4
		}
		public class D extends A{
			//can access v1
			//can access v2		//can still access cause child
			//cannot access v3	//not a part of the package
			//cannot access v4
		}
		public class E{
			//can access v1
			//cannot access v2	//not related to A
			//cannot access v3	//not a part of the package
			//cannot access v4
		}
	/*

	Pitfall: forgetting about the Default package
	When considering package access, do not forget about the default package
		All classes in the current directory (not belonging to some other package) belongs to an unnamed package
			This is called the default package
		If a class in the current directory is not in any other package, then it is in the default package
			An instance var or method with package access can be accessed by name in the definition of any class in the default package
	
	TIP: "Is a" versus "Has a"
		A derived class demonstrates an “is a” relationship between it and its base class
			Forming an “is a” relationship is one way to make a more complex class out of a simpler one
			For example, an HourlyEmployee “is a(n)” Employee
			HourlyEmployee is a more complex class compared to the more general Employee class
		A “has a” relationship can also make a complex class out of a simpler one
			This type of relationship, called composition, occurs when a class contains an instance variable of a class type
				The Employee class contains an instance variable hireDate, of the class Date, so therefore Employee “has a” Date
		Both of these types of relationships are commonly used to create complex classes, often within the same class
			HourlyEmployee "is a(n)"" employee and "has a" Date
	
	TIP: Static Variables are Inherited
		Static variables in a base class are inherited by any of its derived classes, just like instance variables
			The modifiers public, private, protected, and package access have the same meaning for static variables 
	
	Within the definition of a method of a derived class, the base class version of the method can still be invoked if it was overriden
		Simply preface the method name with super
		However, when using an object of the derived class, you cannot do this outside of the derived class's definition 

	You cannot use multiple supers
		It is only valid to use super to invoke a method from a direct parent
		Repeating super will not invoke a method from some other ancestor class

	*/
		super.super.super.sayHello() //this doesn't work
	/*

	instanceof
	--------
		The instanceof operator checks if an object is of the type given as its second argument
		This will return true if the object is of type ClassName, and otherwise it returns false
		Note that this means it will return true if object is the type of any descendent class of className
	*/
		if (object instanceof ClassName){
			//do stuff
		}
	/*

	getClass
	--------
		Every object inherits the same getClass() method from the Object class
			Just like toString or equals
		However, this method is marked final in Object, so it cannot be overridden
	Any invocation of getClass() on an object returns a representation only of the class that was used with new to create the object
	The results of any two such invocations can be compared with == or != to determine if two objects are from the same class
	
	*/
		if (object1.getClass() == object2.getClass())
	/*

The Object Class
-------
	The class Object is in the package java.lang
		java.lang is automatically imported into your projects
	Having an Object class allows methods to be written with a parameter of type Object
		That means that you can make a method that accepts literally any object of any type
		Usually this is leveraged in general-purpose methods, such as those existing in official Java libraries

