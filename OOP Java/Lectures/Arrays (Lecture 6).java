/*Arrays (Lecture 6) 2017-05-29

Note About .close()
--------
	Scanner opens up channels under the hood (e.g. files)
	Imagine scanning 4gb files while running a 6gb simulation (total 10gb)
		Instead, open the file, close it, and then run the simulation (cut down 4gb)

Intro to Arrays
--------
	An array is a data structure used to process a collection of data that is all of the same type
		Arrays in Java are similar to arrays in C
	Arrays are best visualized as tables
	All of the cells of an array contain the same type of data (either primitives like chars or ints, or objects of the same class type)
	Arrays are zero-indexed (just like in C)
		Access by specifying the indices at which you would like retrieve or change data
	The important operations on arrays in Java are:
		Defining an array variable
		Creating an array
		Accessing an array

Defining an Array
--------
	The number of indexed variables in an array is called the length or size of the array
	The indexed variables are then numbered starting with zero (“zero-indexed”)
	 	Results in ending with the integer that is one less than the length of the array
	
	*/ 	
		double[] score; 
		//if score had values [10,11,12,13];
	 	score[0], score[1], score[2], score[3] //would give 10, 11, 12, and 13;
	/*

Creating an Array
--------
	To create an array, use the new keyword
	The value passed inside [] is the length of the array
	The value can be an integer (>= 0) or it can be a variable evaluation to >= 0

	*/
		int arraySize = 4;
		score = new Double [arraySize];
			//OR
		double[] score = new double[4];
	/*

	Why have an array of size 0? 
		Check if an array is empty
	Length can be determined at runtime (i.e. from file)
	Arrays can have indexed variables of any type
	All indexed variables are of the same type in an array, called the base type
	
	*/
		BaseType[] ArrayName = new BaseType[size];
		Person[] personArray = new Person[size];
	/*

Accessing an Array
--------
	Each array element can be used just like any other single variable by referring to it using an indexed expression

	*/ double firstScore = score[0]; /*

	The array itself (the entire collection of indexed variables) can be referred to using the array name (without any square brackets)
	The name of the array is a reference to the array
		Thus, arrays behave like objects and are technically objects
			Therefore arrays have instance variables
				One such variable is length
				When an array is created, the instance variable length is automatically set its size
				The value of length cannot be changed, other than by creating an entirely new array

	Pitfall: Array Index Out of Bounds
		You will see this at least a few times in your life (and you will be sad)
		Array indices always start with zero and end with length-1
		The most common programming error with arrays is using a nonexistent array index
			If you use a nonexistent array index, the index is said to be out of bounds
		Trying to reach an out of bounds index will cause the program to terminate with a runtime error
			Commonly occurs on the first or last iteration when looping through an array

Initializing Arrays
--------
	An array can be initalized when it is declared
		Values for the indexed variables are enclosed in braces ("{" and "}") and comma-separated
		The array size is automatically set to the number of values in the braces
		
	*/ int[] age = {2, 12, 1}; /*
		//Given the above array age, age.length has a value of 3

	Another way to initialize an array is by using a for loop to iterate through all of the uninitialized elements
	If the elements of an array are not initialized explicitly, they will automatically be initialized to default values
		i.e. the default values of their base type (null for objects, 0 for ints, e.t.c)
	
	*/ 
		double[] reading = new double[100];
		for (int index = 0; index < reading.length; index++){
			reading[index] = 42.0;
		}
	/*

	Arrays of characters are not strings!
	Even though an array of characters is conceptually a string {a list of characters}, an array of characters is not a String object
	However an array of characters can be converted to an object of type String
	The class String has a constructor that has a single parameter of type char[]
		Using this constructor creates an entirely independent copy of the array, but as a String

	*/
		char[] a = {‘A’, ‘B’, ‘C’};
		String s = a; //FAIL
		String s = new String(a); //SUCCESS
	/*

Arrays are Objects
--------
	Like class type objects, a variable of an array type holds a reference
		Therefore arrays are objects
		A variable of an array type holds the address of where the array object is stored in memory
		An array can be viewed as a collection of index variables
			Also as a single item whose value is a collection of values of a base type
				An array names the collection as a single item
				A new expression creates the array object and allocates the object in memory
				An assignment statement places a reference to the memory address of the array in an array variable

	Note that a single assignment operation occurs when creating an array, and thus an array variable contains a single value
		The memory address (reference) to the array
	An array is a reference type
		The behaviour of arrays with respect to assignment, equality testing, and parameter passing is the same for class objects
	
Nonprimitive Arrays
--------
	The base type of an array can be a class type

	*/ Date[] holidayList = new Date[20]; /*
	
	The above example creates 20 indexed variables of type dates 
		It does not create 20 objects of the class Date, it allocates memory 
		Default initialization for each of the indexed variables is null, as they are class type objects
		Any attempt to refer to any indexed variable with only the default initialization would result in a null pointer exception
	Like any other object, each of the indexed variables requires separate invocation of a constructor to create an object to refer to
		Use new, one-by-one or done with a for loop
		Can reference each of the indexed variables once that is done since they each have a memory address of a date object

	*/
		holidayList[0] = new Date();
		//…
		holidayList[19] = new Date();
				//or
		for (int i = 0; i < holidayList.length; i++){
			holidayList[i] = new Date();
		}
	/*

Arrays as Parameters
--------
	Both array indexed variables and entire arrays can be used as arguments to any method
		An indexed variable can be an argument to a method the same way any other variable of the array base type can be an argument
	
	*/ 
		double [] a = new double[10]; //all elements = 0 
	/*

	Given myMethod, which takes an argument of type double, all of the following are legal
	
	*/
		i = 4;
		myMethod(a[3]);
		myMethod(a[i]);
	/*
	An argument to a method may be an entire array
	Array arguments behave like objects of any class
		A method can change the values stored in the indexed variables of an array argument
		However, you cannot replace the entire original array with a new 
			Does not work, only affects the variable local to the function
		A method with an array parameter must specify the base type of the array only
			It does not need to specify the length of the array
	
	*/ public int myOtherMethod (BaseType[] btArray) /*
	
	For instance, the following method specifies an array of double to use as its single argument

	*/
		public class SampleClass {
			public static void doubleElements(double[] a){
				int i;
				for (i = 0; i < a.length; i++){
					a[i] = a[i] * 2;
				}
			}
		}
	/*
	
	Look at these two arrays

	*/
		double[] a = new double[10];
		double[] b = new double[30];
	/*

	No square brackets are used when an entire array is passed as an argument to a method
	Note that any method that specifies an array for a parameter can take an array of any length
		E.g. doubleElements from the class SampleClass can be invoked as follows:
	
	*/
		SampleClass.doubleElements(a);
		SampleClass.doubleElements(b);
	/*

Use of = and == With Arrays
--------
	Because an array variable contains the memory address of the array it names, the assignment operator only copies the memory address
		It does not copy the values of the indexed variables
		Using the assignment operator with two arrays will make two array variables be different names for the same array
			In  the following example, the memory address of a is references in b as well, they refer to the same array
				Just like with other object, a == b will be true

	*/
		int a[] = {0, 5, 7};
		int b[] = {5, 2, 4, 8};
		b = a;
	/*

	You can use a for loop to make two different arrays have the same values in each indexed variable
	
	*/
		for (int i = 0; (i < a.length) && (i < b.length); i++){
			b[i] = a[i];
		}
	/*

	The above code will not make b an exact copy of a, unless a and b have the exact same length
	Even if the lengths are the same, a == b will evaluate to false because == compares memory addresses of nonprimitives (like array)
		Assuming a and b are separate array references
	In the same way that an equals method can be defined for any class, an equalsArray method can be defined for a type of array
	This is how two arrays must be tested to see if they contain the same elements
	The following method tests two integer arrays to see if they contain the same integer values

	*/
		public static boolean equalsArray(int[] a, int[] b){
			if (a.length != b.length) return false;
				else{
					int i = 0;
					while (i < a.length){
						if (a[i] != b[i]) return false;
						i++;
				}
			}
		}
	/*
	
Arguments to the Main Methods
--------
	The heading for the main method of a program has a parameter for an array of Strings
		By convention it is typically called args
	If a Java program is run without giving an argument to main, then a default empty array of strings is automatically provided

	*/	public static void main(String[] args) /*

	You can actually use the array of strings in your program
		Below is a program that expects three string arguments
		The arguments are taken initially as strings, but you can use the parse methods to get numbers if needed

	*/
		public class SomeProgram{
			public static void main (String[] args){
				System.out.println(args[0] + “ “ + args[1] +
					“ “ + args[2]);
			}
		}
	/*
	
	If a program requires that the main method be provided an array of string arguments, each element must be provided 
		Must be provided from the command line when the program is run

	*/	java SomeProgram Hi 1 ! /*

		This will set args[0] to “Hi”, args[1] to “1”, and args[2] to “!”, and args.length is 3
	You can also set command line arguments in Eclipse (Run  Run Configurations  Arguments)
	In Java, methods can return arrays in the same way that they can return any other kind of object

Returning Arrays
--------
	In Java, methods can return arrays in the same way that they can return any other kind of object

	*/
		public static int[] incrementArray(int[] a, int inc){
			int[] temp = new int[a.length];
			for (int i = 0; i < a.length; i++){
				temp[i] = a[i] + inc;
			}
			return temp;
		}
	/*

The For-Each Loop
--------
	The For-Each loop can make code cleaner and less prone to errors
	If the checked variable in a for loop is used only to cycle through an array’s elements, then a for-each loop is preferable
		The for-each syntax is simpler and clean
		For example:
	
	*/
		for (int i = 0; i < a.length; i++)
			a[i] = 0.0;
		//Can be changed to
		for (double element: a)	//double is the type, element is the local name, a is the array to iterate on
			element = 0.0;
	/*

Privacy Leaks With Array Instance Variables
--------
	If an accessor method does return the contents of an array, special care must be taken
		Same situation as when an accessor returns a reference to any private object
		The below example results in a privacy leak
	
	*/
		public double[] getArray(){
			return anArray; //FAIL
		}
	/*

	anArray being declared private means you only want members of the class to be able to modify the array (enforcing encapsulation)
		When the array is returned from the getter, the caller can do something like the following, which is bad

	*/
		double[] newArr = obj.getArray();
		newArr[4] = 3.1;	//you have a copy of the address so do whatever evil things you want
	/*
	
	How to solve this problem?
		We modified the accessor to a private instance variable such that it returns a deep copy of the object, not the original
		Similarly, an accessor to an array instance variable must return a deep copy of the private array object
	
	*/
		public double[] getArray(){
			double[] temp = new double[count];
			for (int i = 0; i < count; i++){
				temp[i] = anArray[i];
			}
			return temp;
		}
	/*

		Similarly, if the array holds nonprimitive objects, the copies must also be deep copies so as to never modify the originals
	
	*/
		public ClassType[] getArray(){
			ClassType[] temp = new ClassType[count];
			for (int i = 0; i < count; i++){
				temp[i] = new ClassType(anArray[i]);
			}
			return temp;
		}
	/*

Multidimensional Arrays
--------
	It is sometimes useful to have an array with more than one index
	Multidimensional arrays are declared and created in essentially the same way as one-dimensional arrays
		You simply use as many square bracket pairs as there are dimensions
		Each index must be enclosed in its own brackets

	*/
		double[][] table = new double[100][10];
		int[][][] figure = new int[10][20][30];
		Person[][] = new Person[10][100];
	/*

	Methods may specify how many dimensions are needed in an array argument
		Same way as when we have a one-dimensional array
		They use the same number of sets of square brackets as they have dimensions
		In the below example, a is a 2D array
	
	*/
		public void myMethod(int[][] a){
			…
		}
	/*

	Methods may also specify the number of dimensions in a returned array (similarly as above)

	*/
		public double[][] aMethod(){
			…
		}
	/*

Using the Length Instance Variable
--------
	Just like with one-dimensional arrays, any element of the array is still just a variable of the base type
	In Java, a two-dimensional array is actually an array of arrays
		A three-dimensional array is actually an array of array of arrays, and so forth for higher dimensions
	In a multidimensional array, the length variable does not give the total number of indexes
		It gives the number of indexes along a dimension
	
	*/	char[][] page = new char [30][100]; /*
	
		So, in the 2D array called page (above), page.length is equal to the number of first indices (“rows”), which is 30
		Similarly, page[0].length is equal to 100
	You can use nested for loops to process multidimensional arrays, for instance length is used below to iterate across a 2D array
	
	*/
		int r, c;	//row and column
		for (r = 0; r < page.length; r++)
			for (c = 0; c < page[row].length; c++)
				page[r][c] = ‘Z’;
	/*

Ragged Arrays
--------
	Why use the length of each specific row in the previous example, and not just page[0] throughout the entire inner loop? 
		Each row of a two-dimensional (in fact, n-dimensional) array need not have the same number of elements
	An array that has a different number of elements per row is called a ragged array
		AKA a jagged array… same thing
	
	*/	double[][] a = new double[3][5]; /*

	The above line is equivalent to the following:

	*/
		double[][] a;
		a = new double[3][]; //Note below
		a[0] = new double[5];
		a[1] = new double[5];
		a[2] = new double[5];
	/*

	The second line makes a the name of an array with room for 3 entries, each which can be an array of doubles of any length
	The next 3 lines each create an array of size 5
	Raggedly, you could also do this
	*/
		a[0] = new double[5];
		a[1] = new double[3];
		a[2] = new double[4];
	/*
	
	Then each array is of different length: 5, 3, and 4


