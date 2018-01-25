/*Introduction (Lecture 1) 2017-05-08

About Me
--------
	Blackboard
	Office Hours: MW 2:30pm - 4:00pm

Course Info
--------
	Labs 10%		(every week)
	Assignments 10% (every week)
	Midterm 1 June 7th in class
	Midterm 2 July 19th in class
	Final August 15th, 3:30pm
	No Lates

	Timestamped labs, each lab is due a week after 
	2 submissions per lab/assignment 
	They will help prepare for the next week, but no debugging on the due date
	Open book midterms/finals
	Use stack overflow
	Go during office hours

Introduction to Java
--------
	Java can be used for a lot of stuff (Embedded software, web, mobile applications, standalone, cross-platform applications)
	OOP(Object-Oriented-Programming) language (objects that interact with each other, with functions (called METHODS))


Objects, Classes, and Methods
--------
	Objects are entities that store data and take/perform actions
		E.g., objects of the String class store data consisting sequences of characters
			We call the objects strings when we work with them in Java
	Calling or INVOKING a method
		Object.method(arguments); 
		Ryan.setIsAwesome(true);

	Two types of Java programs: APPLICATIONS and APPLETS
		A Java application is the "regular" JAVA program, consisting of at least one class, with a single method named "main"
		A Java applet is the web application (we aren't studying this)

Compiling and Running Java Programs
--------
	System.out.println
		Java works by having objects perform actions
		System.out is a system object used for sending output to the console

	Variable Declaration
		Declaraion is the same as C
	Assignment Operator
		= is the assignment operator
		Requires a variable on the left and a value (or a value held in a variable) on the right side of the operator
	Compilation
		Each class definition must be in a file whose name is the same as the class name, followed by .java
			E.g., the class Intro1 must be a file named Intro1.java
		Each class is compiled with the command javac followed by the name of the file in which the class resides
			For example, javac Intro1.java results in a Intro1.class file (the .java file is the source file)
			Run command is java Intro1
		The main method looks like this: public static void main (string[] args)
	Running Java in Eclipse
		Go over in the lab
	JRE = Java Runtime Environment (Needed to run Java Applications)
	JDK = Java Development Kit 
		Contains the JRE and command-line tools such as compilers and debuggers necessary/useful for developing java apps and applets
	Dangling = running and not closed (e.g. Scanners need to be closed)
		Things that can dangle: files,pointers, references, processessors
		Zombie Processes: Multithreading a process with no playback reference (process won't close until computer is shut off)
*/
