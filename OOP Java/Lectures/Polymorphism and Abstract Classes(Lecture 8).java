/*Polymorphism and Abstract Classes (Lecture 8) 2017-06-12
	
Overloading Constructors
--------
Sending something over a network, store data in a byte array, would want to be able to accept something on one side and output on other
	Input might be of type food, output might be in a byte array
	Convert to byte and then convert back to food can be done using overloaded constructors
Can have constructors with the same variable parameters if you reorder the parameters 
	Can't do this if all parameters are of the same type
Note with method overloading:
	You can have duplicate method headers taking the same variable types if they are in a different order
		However in order for the compiler to recognize this, they can't all be the same variable
			E.g. String int string and int String String works
				String String String and String String String does not

Polymorphism
--------
	Don't forget about downcasting! It is an inheritance pitfall (calling lower level methods on a higher level object)
		Casting an object allocated to a superclass type to a subclass type
		It is the responsibility of the programmer to use downcasting only when it makes sense
			Use instanceof operator to make sure it makes sense
	
	*/ if (object instanceof ClassName){…do stuff} /*
		
			It will return true if object is an instance of any descendent of ClassName
		It is not a compile-time error to downcast improperly, it is a run-time error

	Problem:
		A bunch of animals that make different sounds
		Our Java application has an array of such objects
		We want to display the greetings of all of the animals in the array
			Not nice code when you’re using basic inheritance
	*/
		public class Dog{
			private String name;
			public Dog(String name){
				this.name = name;
			}

			public String greet(){
			    return “Dog “ + name + “says woof!”;
			}
		}

		public class Cat{
			private String name;
			public Cat(String name){
				this.name = name;
			}

			public String greet(){
			    return “Cat “ + name + “says meow!”;
			}
		}

		public class Snake{
			private String name;
			public Snake(String name){
				this.name = name;
			}

			public String greet(){
			    return “Snake “ + name + “doesn’t make 		any sound!”;
			}
		}

		public class GreetTester {
			public static void main(String args[]){
				Object animals[] = new Object[4]; // create an array to save 4 objects
				String greeting;
				animals[0] = new Cat("Mr. Lilly");
				animals[1] = new Dog("Lola");
				animals[2] = new Snake("Snakey the Snake");
				animals[3] = new Dog("Cooper");
				for (Object ani: animals){// iterate for every animal in array
					if (ani instanceof Cat){ // ugly code!!
						greeting = ((Cat)ani).greet();
						System.out.println(greeting);
					} else if (ani instanceof Dog){
						greeting = ((Dog)ani).greet();
						System.out.println(greeting);
					} else if (ani instanceof Snake){
						greeting = ((Snake)ani).greet();
						System.out.println(greeting);
					}
				}
			}
		}
	/*
	How can we clean this up? We do not want to have such an if/else construct, they are ugly and not maintainable
		Imagine 100 animals? 1000 animals?
			Its bad
	Intro to Polymorphism
		There are three main programming mechanisms that constitute OOP
			Encapsulation
				Data and methods of a class are combined into an object of a class, which hides implementation details 
					Yet provides all relevant functionality 
			Inheritance 
				It allows a base class to be defined, and then other classes derived from it
				Code in the base class can then be used for its own objects, as well as any derived objects
			Polymorphism
				The ability to associate many meanings to one method name
				Allows changes to be made to method definitions in the derived classes
					And have those changes apply to the software written for the base class
				It does this through a mechanism called late binding (aka dynamic binding)
	Late Binding
		The process of associating a method definition with a method invocation is called binding
		If the method definition is associated with its invocation when the code is compiled, that is called early binding
		If the method definition is associated with its invocation when the method is invoked (at runtime), its called late binding
	Java uses late binding for all methods, except for ones that are private, final, and static
		Because of late binding, a method can be written in a base class, even if portions of the task are not yet defined
	Remember how override works (the lowest level form of a method is always used)
		You can access the variables and methods accessible to the superclass
		Assume A and B both have the same method m() (and B has an override for it)
	*/
		A myA;
		myA = new B(…);
		myA.m();
	/*	
		Since myA is technically of type B we invoke B’s version of m(…)
	*/
		public class Poly2{
			private String name;
			public Poly2(String name){
			      this.name = name;
			}

			public String getName(){
			      return name;
			}

			public String toString(){
			      return "Name is " + name;
			}

			public static void main(String args[]){
				Poly2 p1;
				Object myObject;
				p1 = new Poly2("John");
				myObject = p1; // Is this valid? Why??	//valid cause Poly2 is a child of myObject
				System.out.println("In class Poly2 p1 is " + p1);	//valid, will print john
				System.out.println("In class Object myObject is " + myObject);	//valid, will print john
				//Can I say myObject.getName()??	//invalid, myObject has no getName() method
			}
		}
	/*
	Instead of checking instanceof to see if an object is of a particular class, just override the method in all of the subclasses 
		We can then implicitly cast objects of each subclass to the superclass type, and then call the shared method without worry
			It automatically calls the lowest-level version of it 

	An application with multiple types of Workers(Machinist, Carpenter, Builder) derived from the class Worker now looks like this:
	*/
		public class Application{
			public static void main(String args[]){
				Worker employeesInFactory[];
				// create the array employeesInFactory and populate it 
				// with all the workers in the factory
				for (Worker employee: employeesInFactory) {
					employee.work(); // This will give the work done by this 
					// particular employee – does not matter what kind
				}
			}
		}
	/*
	Same story with the animals from earlier
	*/
		public class Animal{
			private String name;
			public Animal(String name){
				this.name = name;
			}

			public String greet(){
			    return “Animal “ + name + “says something!”;
			}
		}

		public class GreetTester {
			public static void main(String args[]){
				Animal animals[] = new Animal[4]; 
				String greeting;
				animals[0] = new Cat(“Mr. Lilly");
				animals[1] = new Dog(“Lola");
				animals[2] = new Snake(“Snakey the Snake");
				animals[3] = new Dog(“Cooper");
				for (Animal ani: animals){// iterate for every animal in array
					ani.greet(); //that’s better
				}
			}
		}
	/*
	On a similar note, a decision must be made to determine which static method to call on the objects
		This decision is made at compile time, not run time
		Therefore with respect to static methods, there is no late binding and therefore no polymorphism
		This decision is made based on the type of variable the object is (rather than its lowest level subclass)
	Java uses static binding with respect to private, static, and final methods
		Static methods need to care about the object type, why would you late bind?
		Why would you override a private method anyways?
		Can't override a final method, its not possible
			Compiler uses early binding for them 
	Generally speaking, objects know the definition of their methods
	The type of a class variable determines which method names can be used with the variable
		This you can observe at development time
		However, the object named by the variable determines which definition with the same method name is actually used
			This is what we observe at run time through late binding
	A special case of this is as follows:
		The type of a class parameter determines which method names can be used with the parameter
		However the argument determines which definition of the method name is used
		Development time is the same thing as complie time
	
Abstract Classes
--------
	Java (and many other OOP languages) include two related concepts
		Abstract classes and abstract methods
		A class is called abstract if we are never allowed to directly create an instance of it
		Java allows abstract methods to be declared, and they must be declared in abstract classes
			An abstract method has a heading but no body
			The body of the method is only defined in derived classes
	An abstract class is like a placeholder class containing abstract (placeholder) methods 
		The abstract method will fully be defined in a derived class of the abstract class containing the abstract method
	It has a complete method heading, and the heading also has the modifier abstract
	It cannot be private – it is always meant to be overridden (as it doesn’t even have a body!) 
		In place of its method body, we put a semicolon
	*/
		public abstract double getPay();
		public abstract void doStuff(int count);	
	/*
	A class that has at least one abstract method must be defined as abstract
		An abstract class must have the modifier abstract in its class heading
		An example of an abstract class heading is below
	*/
		public abstract class SomeAbstractClass {
			//instance variables
			//any methods you want to define 
			public abstract double atLeastOneAbstract();
			//potentially other abstract methods and/or
			//constructors that cannot actually instantiate (a template constructor)
		}
	/*
	An abstract class can have any number of abstract and/or fully defined methods
		Have non abstracts for general stuff that will be used by all children
		Have abstract stuff for things that will be specified in children
	If a derived class of an abstract class adds to or does not define all of the abstract methods...
		Then it is also abstract and must say so in its class modifier
			Compiler will throw an error otherwise
	A class that has no abstract methods is called a concrete class
		We will instantiate concrete class objects, but never abstract class objects
	Thus an abstract class can only be used to derive more specialized classes
	An abstract class constructor cannot be used to create an object of the abstract class
		However, derived class constructors will include an invocation of the abstract class constructor using the keyword super
	An abstract class is a type
		Although an object of an abstract class cannot be created, it is fine to have a parameter of an abstract class type
			You can plug in an object of any of its descendent classes to fullfill the requirement of a method
		It is also fine to use a variable of an abstract class type, as long as it names objects of its concrete children only
	An example
		Consider a situation where we have four categories of people:
			Children
			Students
			Workers
			Senior Citizens
		They all share the same variables
			Name
			Age
			Sex
		Each one has things specific to them (seniors have pension, students have grades)
		We can make an abstract Human to be the superclass for all these classes to derive from
	Advantage of abstract classes with respect to polymorphism
		Code is cleaner
		We do not need huge switch or if/else constructs
		Adding new classes becomes very easy


	
		
	
*/ 