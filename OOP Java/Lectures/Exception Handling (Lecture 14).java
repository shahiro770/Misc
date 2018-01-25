/*Exception Handlng (Lecture 14) 2017-07-30 

What is Exception Handling
--------
	An exception is an indication that something went wrong during program execution
	Exception handling means that the program has the ability to detect errors when they occur and possibly recover from them
		Or at the very least, tell the use whats going on
	Without exception handling, the program could abort abruptly or produce incorrect results
	
	Everyday life example:
		How a day in the life of Ryan normally goes:
			Go for a run
			Have some food and maybe a coffee
			Brush my teeth
			Make course content and do research
		Some problems that could arise:
			It’s storming
			Car battery is dead, no Timmies  
			Plumbing fails
			Laptop died (please no) – buy a new one and restore using my most recent backup or get it repaired
	One way to handle these errors is by inserting instructions throughout our code that deal with our errors
		If it’s storming, run on the treadmill
		Else run outside
		If car battery is dead, walk to Timmies for coffee
		Else drive and get coffee
		If plumbing fails call plumber
		Else brush teeth as per usual
		If laptop fails get it repaired ASAP
		Else work on lectures and research
	However, the truth of the matter, in life and in code, is that there could be all kinds of potential ways for a plan to fail 
		Especially when other users are working with your program and doing their own strange things
			What if it’s not storming but it’s snowing?
				What if it’s not storming or snowing, but it’s 120F outside?
					Etc.
	You generally don’t plan every single thing you do in your life, thinking of every contingency and planning all of your outs
		You proceed as though the day were normal, and handle issues when they arise
	In Java (and several other OOP languages), we do the same – handle issues when they arise
	If we wrote out every contingency, code would become way too difficult to read, understand, or maintain
	Almost any line of code contains something that could cause an issue

Java’s Approach to Exception Handling
--------
	DEFINITION: An exception (issue, error) in any step of program execution causes the program to “throw” an object of a...
		...built in class called Exception
	This object has to be “caught” by an exception handler, which is simply a block of code that deals with the exception
	Why generally?
		Because Java has checked and unchecked exceptions
			Checked exceptions are noticed by the compiler during compile time, unchecked are not
			Unchecked are also known as runtime errors (well it runs, it'l l just terminate once sometihng goes wrong)
	
	An Example
	--------
		Normal Code:	Code with Exception Handling:
						 try:
		Step 1				Step 1  Can throw error
		Step 2				Step 2  Can throw error
		Step 3				Step 3  Can throw error
		Step 4				Step 4  Can throw error
					 	catch:
							resolve any issue
					 	finally (optional):
							do other stuff
		The programmer puts all the normal code inside a try block
			Try to execute this code and hopefully there are no issues with it
		The try block is followed by zero or more catch blocks
			Each catch block indicates what kind of error to look for and how to deal with it
		The finally block executes regardless of whether or not there was an error, and is optional
		
		When an error occurs, normal processing of the next instruction in the try block is skipped
		The catch blocks are searched to find an exception which matches the exception that has occurred
			When the matching catch block is found, the code inside of it is executed
		
	Another Example
	--------
		Example: Computing the Factorial
			Step 1: Read an integer from the user
			Step 2: Calculate the factorial of it
			Step 3: Display the computed value
		What can go wrong here?
			Integer is not given
			Negative number is given
			Non-numeric value can be given, probably the biggest thing to look out for
				This throws a NumberFormatException
		An example finally block:
			In this example, we’ll keep asking for a number from the user until the user gives us a valid integer
			We will calculate the total number of strings the user entered before giving an int
			The idea:
				We will increment a count irrespective of whether the inputted string is a valid int or not
	*/
		public class TooBigANumberException extends ArithmeticException{ //the exception handling class
		    public TooBigANumberException(){
		        super("The number typed in is too big to handle");
		    }
		}

		import java.util.InputMismatchException;
		import java.util.Scanner;

		public class CalculateFactorial {
			public static void main(String args[]){
				boolean flag = true;
				Scanner keyboard = new Scanner(System.in);
				int number, result, numAttempts = 1;
				while (flag){
					try{
						System.out.println("Enter number");
						number = keyboard.nextInt();
						result = calculateFactorial(number);
						System.out.println(number + "!= " + result);
						flag = false;
					}
					catch (InputMismatchException e){
						System.out.println("Input invalid. Try again. Attempts = " + numAttempts);
						keyboard.nextLine(); // Needed to get rid of the input
					}
					catch (TooBigANumberException e){
						System.out.println("Input too big. Try again. Attempts = " + numAttempts);
						keyboard.nextLine(); // Needed to get rid of the input
					}
					finally{
						numAttempts++;
					}
				}
			}
			
			private static int calculateFactorial(int number) throws TooBigANumberException{
				int value, factorial = 1;
				try{
					value = number;
					if ((number == 0) || (number == 1)) factorial = 1;
					if (number > 10){// We cannot handle number > 10
						throw new TooBigANumberException();	//will procede to the following catch block
					else while (number > 1){
						factorial = factorial * number;
						number --;
					}
				}
				catch(Exception e){
					System.out.println("There is a problem");
					System.out.println(e.getMessage());	//gives the super constructor's string
					throw (TooBigANumberException) e;	//main will try to deal with this
				}
					return factorial;	//not called if there's an exception thrown
			}
		}
	/*
	
	Basic structure of a program with a try-catch structure for exception handling:
		try{
			statements which can throw exceptions
		} catch(Exception1 e1){ //Exception1 is a condition
			code that handles e1
		} catch (Exception2 e2{ //Exception2 is another
			code that handles e2
		}…catch(Exceptionm em){
			code that handles em
		} finally{ optional block that happens regardless }

Defining Your Own Exceptions
--------
	Note that you can have try catches inside try catches (maybe to catch things further along )
	Java already has an extensive list of exceptions
	You may define your own exception classes
		You can use these to handle conditions that you perceive as illegal
	All exception classes must derive from the built-in class called Exception
	A method can throw an exception, to be caught by a catch block if thrown
	Define your own exception by:
		Extending some existing Exception
		Including your own constructor
	The constructor of each exception takes a string as an argument
		This should be a description of the error
	
	*/
		public class TooBigANumberException extends ArithmeticException{
		    public TooBigANumberException(){
		        super("The number typed in is too big to handle");
		    }
		}
	/*

	In a method header, you can indicate which exceptions can be thrown by the method
		Unchecked methods need not be listed
	All exceptions that are thrown by a method must be caught or thrown by its caller
		Otherwise the program will not gracefully terminate (which means the error is not actually handled at all)
	A try or catch block may also throw an exception
		This allows nesting try-catch constructs and forwarding an error to other handlers until it is gracefully resolved
	A constructor may also throw an exception if necessary
		In this case the object is not constructed properly and the object is marked as garbage
	An example:
		A Person class, constructor throws an exception
		We define an exception called BadAgeException
		BadAgeException (and all exceptions) are just objects, and so you can use them like any other object, define members and whatnot
		MESS AROUND WITH THIS EXAMPLE, ITS ON THE FINAL
	*/
		public class BadAgeException extends Exception{
			String ageString;
			
			public BadAgeException(String message, String ageString){
				super(message + "; supplied age is " + ageString);
				this.ageString = ageString;
			}
			
			public String getAgeString(){
				return ageString;
			}
		}

		public class Person {
			private String name;
			private int age;
			
			public Person(String name, String ageString) throws BadAgeException{
				try{
					this.name = name;
					this.age = Integer.parseInt(ageString);
				}catch(NumberFormatException e){
					throw new BadAgeException("invalid age string ", ageString);
				}
			}
			
			public String toString(){
				return ("name = " + name + "; age = " + age);
			}
			
			public static void main(String args[]){
				Person p = null;
				try{
					p = new Person("John", "xyz");
					System.out.println(p);
				}catch(Exception e){
					System.out.println(e.getMessage());
					System.out.println("method getBadAge returns " + ((BadAgeException)e).getAgeString());
				}
				System.out.println(p);
			}
		}
	/*
*/