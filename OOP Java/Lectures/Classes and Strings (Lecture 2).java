/* Classes and Strings (Lecture 2) 2017-05-10

Characteristics of Java
--------
	Simple
		Partially modelled on c++ but greatly simplified, and in many ways improved (c++-- in some words)
		More functionality and fewer negatives
		NO POINTERS!!!
	Object-Orietented
		Inherently OO
		Many OOP languages began as procedural languages and thne modified into OOP, but Java was designed to be OO from the state
		Popular approach because we live in a world where we describe life with objects 
		OOP maximizes code reuse, flexibility, modularity, and clarity
	Distributed
		Distributed computing involves multiple computers working together on a network (Interprocess communication (ITC))
		Java is designed in a way that makes distrbuted computing easy (Has a lot of classes that are utilized for networking)
		Networking is relatively easy in Java (marshalling/unmarshalling APIs)
			Lets you package stuff into objects and send them over networks
			Lots of functionality for sending data across networks
		Any Java program can work on any computer (with an OS/processor architecture), provided it supports some version of the JRE
	Interpreted
		Java programs are compiled into bytecode, which is run by the Java Virtual Machine (JVM)
		The JRE contains the JVM, among all kinds of other stuff, and the JVM contains a Java interpreter (interprets Java byte code)
		There are different JRE packages for different OS/processor architectures, and so code that is written anywhere...
			... can be put on any computer 
				Not everything is supported by some version of the JRE
		Java code is both compiled and interpreted
			Compilation can occur at compile-time and at runtime
			
			Inside Compile-time Environment
			Java Source (.java) -> Java Compiler -> Java bytecode (.class) -> bytecode moves locally or through network...
				...as long as it is supported by a JRE
			Inside Run-time Environment
			->Class loader (bytecode verifier) -> a just in time compiler alongside a Java interpreter (Java Virtual Machine) 
			->Runtime System -> Operating System -> Hardware

	Robust
		Java Compilers can detect errors that typically show up in runtime in other languages
		Many Java programming environments detect errors as code is being written
		Java has rich runtime exception handling (failure handling), very useful for debugging (stack traces can be printed, etc.)
		Java as a language has eliminated several programming constructs that make users prone to errors in other languages 
			E.g. pointers
	Secure
		Java has all kinds of security mechanisms that protect your system against harm caused by stray programs
			Applications can be subject to security controls
			Encryption packages are available for writing data to files or over networks
			Authentication and verification tools for databases, files, and installers
	Architecture-Neutral
		"Write once, run anywhere"
	Portable
		Because architecture-neutrality, compiled Java code can be run on any machine with a JRE 
	Easy to write, inefficient to run (a trade off)
		Java is typically the last choice for high-performance software, one of the best choices for rapid development or portability
		Why?
			interpretation is computationally costly (its expensive)
			You have a lot of built-in tools at your disposal, but they are not always efficient
			Other languages give you more control (like pointers)
			JAVA DOES HAVE POINTERS but under the hood (you can't see it or utilize them beyond an extent) 
		Just-in-time Compilation
			Interpreter chooses to interpret on the fly or compiles beforehand, it identifies what needs to compiled and when
			Identifies parts of interpreted code that would benefit from real time compilation (into machine code), during runtime
			Actually compiles the code to make it more efficient
		Dynamic Linking
			Pulling packages into memory at runtime, as needed
			Saves RAM
		For High performance computing (HPC) chooses C++
	Multithreaded (synchronous programming) (Spawning subprocesses to do other things so that it doesn't lock up other threads)
		Smoothly integrated in Java
		Can easily spawn new threads of various types
		Lots of tools that allow synchonization of processes and their threads
		Other languages often have particular packages for multithreading for any system you would want to perform multithreading
			e.g. Multithreading in C on Windows or Unix requires different packages and thus special statements... 
				...to determine the OS and which packaages to load
	Dynamic
		Designed to adapt to an evolving world
		Java bytecode can be loaded without recompilation for different environments
		New features to programs can be integrated with ease and without worry
			No need to install different versions of supporting software for different machines

Strings
--------
	Strings are not primitive types in Java 
	Java primitives: 

	*/
		Boolean
		Byte
		Char
		Short
		Int
		Long
		Float
		Double
	/*

	The class String is a pre-defined class in Java, used to store and process strings
	Objects of type String are comprised of strings of characters written within double quotes
		Any string, in quotes is a constant (aka a literal) of type String 
			Ex: "Ryan is awesome";
	A variable of type String can be given the value of a String object, whether it is a literal or object
		String statement = "Ryan is awesome";
		We will not talk too much more about literals because it only makes a difference deep under the hood
			On the surface, Strings and string literals behave very similarly
			Literals are a performance optimization (less overhead to declare a literal than to create an actual object)
		Both statements point to the same memory address (Woah)

	String Concatentation
		Connecting two strings together to form a longer string is done using the + operator on two strings in Java

		*/
			String greeting 1 = "Hello";
			String greeting 2 = "everyone";
			String classGreeting = greeting1 + " " + greeting2; 
		/*

	String Methods
		The String class contains many useful methods for string processing applications
		A string method is called by creating a String object, followed by a dot, followed by method name, followed by (args needed)
		Like C, count from 0

		*/
			String greeting = "Hello";
			int count = greeting.length()
			System.out.println("Length is " + count); 
		/*

	*/

	int length() 
		//Returns length of the string on which the method is called
	boolean equals(String otherString)
		/*Returns a boolean value of true if the value of the called string and value of otherString are exactly the same
			False otherwise
		Case sensitive
		We'll talk more about '==' vs. '.equals' late(hint, == compares memory address, .equals compares value)
		Generally, ".equals" for objects (including string literals), and "==" for primitive data types (ints, shorts, chars, etc.)*/
	char charAt(int p)
		//Returns the character in the calling object string at the position p, zero-indexed
		//Negative values or values larger than length-1 will return an error
	String substring(int startIndex)
		//Returns the substring of the calling object string, starting at startIndex and ending at the end of the calling string
		//The character at position startIndex is included 
	String substring(int startIndex, int endIndex)
		//Returns the substring of the calling object string, starting at the startIndex and ending at the endIndex
		//The character at the position startIndex is included, but endIndex is not 
	int indexOf(String sample)
		//Returns the index of the first occurrence of the string sample in the calling object String
			//If not found, it returns -1
	int lastIndexOf (String sample)
		//returns the index of the last occurence of the string sample in the calling object String
	int compareTo (String sample)
		/*Compares the calling object string and the string sample to determine which comes first in lexicographical order
			Uppercase > lowercase, otherwise alphabetical order is used
		Returns a positive value if the calling string is first, 0 if the strings are equal, and negative if the argument is first*/

	/*

	String Indexing
		Zero-indexed
		Blanks and punctuation count as characters 
	String Processing
		A String object is considered immutable in Java (can't manipulate characters inside a String)
		There is another class in Java called StringBuffer which contains the means of editing String objects 
		However, String objects can be reassigned by using the assignment operator

	*/
		String name = "Ryan ";
		name = name + "Scott ";		//you can reassign strings like this
		name += "is okay I guess"; 	//or like this
	/*

	Constants
		If a value (e.g. an int) needs to never change throughout the run of a program, declare it as a named constant
			Refer to the name instead of the number itself
				Use the keyword final
				Prevents the value from being changed
		Note the naming convention - use all uppercase letters and underscores for spaces */
		public static final int INCHES_PER_FOOT = 12; /*
	Comments
		A line comment begins with // 
		A block comment /* and */ /*
	Importing Packages and Classes
		Java libraries are called packages
		A package is a collection of classes stored in an easily-accessible manner, usable in any program
		In order to use a class belonging to a package, the class must be brought into the program using an import statement
		Classes found in java.lang are imported automatically into every Java program
	
	*/
		import java.text.NumberFormat; //specific
		import java.text.*; //lets you bring in everything inside the .text package and wastes RAM
	/*

Scanner
--------
	Since Java 5.0,  Java includes a class for simply taking keyboard input
		It is called Scanner
		In order to use Scanner you must import the Scanner class

		 */ import java.util.Scanner; /*

		This statement tells Java to:
			Make the Scanner class available to the program
			Find the Scanner class in a java package (library of classes) named java.util
		The following line creates an object of the class Scanner, and names the object keyboard 

		*/ Scanner keyboard = new Scanner (System.in); /*

		The name "keyboard" is common for a scanner, but you can name it whatever you like (aside from Java keywords)
		Once a Scanner object has been created, its methods can be used to take keyboard input
		The nextInt method reads an int value typed in, and assigns it to a variable
			
			*/ int numberOfWords = keyboard.nextInt(); /*

		The nextDouble method reads on double value typed in, and assigns it to a variable
			
			*/ double dub = kb.nextDouble(); /*

		Multiple inputs must be seperated by white space
		The method next() reads one string of non-whitespace characters delimited by the whitespace characters

		*/
			//Input is "Yay Java"
			String word1 = keyboard.next();
			String word2 = keyboard.next(); //would read "Yay Java" as "Yay" for word1 and "Java" for word2
		/*

		The method nextLine reads an entire line of keyboard input
		The following code takes an entire line of keyboard input and places it into the variable called "line"
			
		*/ String line = keyboard.nextLine() /*

		The end of an input line is indicated by '\n' (automatically read by console)
			This is known as the newline character
			\n is read by nextLine() but not included in the string value returned
	Pitfalls
		"10\n" with nextInt() and nextLine() would read "10" and then "" because nextLine stops on a newline character
			nextInt() gives no shits once it can't find anything numerical

	*/ useDelimiters(String delimiter) /*

	The above method changes the delimiter used by the calling scanner
		After this statement is executed, delimiter is the only delimiter that separates words or numbers for the calling scanner
		Generally speaking, a delimiter is a value that is used to seperate input into tokens (tokenization)
	Prompting for Input
		A program should always prompt the user when the user needs to input data
		Echo all input that a program recieves, that way the user can check whats being taken in
	The Empty String
		"" is the empty String
		When a program executes nextLine() on a scanner, if the user does nothing but presses the enter key, an empty string is taken
	


	



















*/