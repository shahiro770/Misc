/* Java Review
	4 Questions on Midterm

Question 1: Recurssion 
	Review Recurssion in Java
	Always comment to get part marks (explain your steps)

Question 2: Correct the Errors
	Small program with a main method, two smaller methods
	6 Errors (he might scale it down if everyone bombs)
	Lose 1 mark on each incorrect error assumed (you can't guess)
	Correct them in the notebook

Question 3: Write a class
	Make the class
	Make the tester (look at TestWorker)

Question 4: 3 Short Answer Questions
	Somewhere from Midterm Review 

A class contains:
	static variables
	instance variables
	static methods
	instance methods
	A constructor

A method has:
	privacy modifiers
	return type
	parameters

What is OOP?
	The programming methodology that views the program as if it is consisting of objects that interact with each other.
	Defining things as objects
	Simplifies code
	Prevents code rewriting (inheritance)
	
Java is an OOP language. Is it interpreted or compiled?
	Both (java is compiled into byte code which is interpreted in Java Runtime Environment through the Java Virtual Machine)
		JVM can also compile using its Just-In-Time compiler
	Yes, you can run a program in Java on any computer 
	"write once, run anywhere"

Declare a variable
	type variable name; or type variable name = suitable value;

"Ryan" == "Ryan" is true cause string literal
"Ryan" == new String("Ryan") false cause reference
“Ryan”.equals(new String(“Ryan”)) is true


Strings are immutable
	Don't have to create a deepcopy, it can't be modified between objects

Class "Shape"
	Invoked with constructor */

public class Shape{
	private int x;
	private int y;
	static int z =0;

	public Shape(int instanceVar1, int instanceVar2){
		this.x = instanceVar1;
		this.y = instanceVar2;
	}
}

/*
Anonymous objects are not named

Invariant is true in all objects of the class

Cannot modify original in deep copy multidimensional






