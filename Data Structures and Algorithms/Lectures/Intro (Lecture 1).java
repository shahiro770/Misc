/* Intro (Lecture 1) 2017-09-07
	1) Pointers (we know)
	2) Linked List (we know)
	3) Big - O Notation (we don't know)
	Imporatnt  Dates
		Midterm October 24th (Tuesday)
		Final Exam December 16th (Saturday)
	
	Agenda
		Learn what data structures and algorithms are
		Describe the properties of a good algorithm
		Explain the goals of algorithm analysis and the importance of algorithm analysis
		Linear vs nonlinear
	10 Point Bonus max for answering questions in class

What is an Algorithm
-------
	The word "Algorithm" is a combinatino of the latin word algorismus (named after Muhammad ibn Musa al-Khwarizmi)
		He was a 9th centure Persian mathematician and the Greek work arithmos, meaning "number"
	Al algorithm is a step by step (unambigious) instructions to solve a given problem
	An algorithm is a set of precise instructions that transfer the input into the desired output
	To exp

	Making a cup of tea in a natural language
		Boil water
		Pop in a tea bag into your mug and pour the hot water into the cup
		Before removing the tea bag, gently squeeze it against the side of the mug
		Finally customise your tea by adding milk, sugar, tears, or honey or nothing at all
	Sorting an array of N elements:
		Bubble Sort
		Selection Sort
	Any algorithm should have five properties:
		Precision: An algorithm should have clear and well-defined instructions
		Uniqueness: Each step taken in the algorithm should give a definite result	
			People should know why an algorithm is working and giving the results it is giving
		Feasibility: The algorithm should be practicable in real life
		Finiteness: The algorithm stops after a finite number of instructions are executed
		Generality: The algorithm applies to a set of inputs
	How do we judge an algorithm?
		In general, we use two criteria to evaluate an algorithm:
			Algorithm Correctness: Does the algorithm provide the desired solution to the problem in a finite number of steps
			Algorithm efficiency: How much resources (in term of time and memory) the algorithm consumes to produce the solution
		E.g. You have a web application that uses an algorithm to work on data sent to the server
			It can initially take in 15 - 25 requests per second
				A different data structure can improve the request handling to 700 - 1000 requests per second
	What is time complexity analysis?
		It is the process of determining how processing time increase as

	Rate of Growth and Big-O Notation
		The rate of growth shows how the running time of an algorithm increases as a function of the input
		The execution time is expressed using a function f(n) and the Big O notation gives the upper bound of f(n)
			We judge by the worst case scenario ("Whats the worst that can happen?")
				Average complexity tends to be what happens
		The Big O notation express how the function F(n) behaves as n moves towards infinity
		When we design an algorithm our objective is to minimize the rate of growth
		In general it is represented as f(n) = O(g(n)), when n is large, the upper bound of f(n) is g(n)

	Time complexity		Name 					Examples
	O(1)				Constant				Inserting an element at the beginning of a linked list
	O(logn)				Logarithmic				Finding an element in a sorted array
	O(n)				Linear 					Finding an element in an unsorted array
	O(nlogn)			Linear Logarithmic 		Sorting an array using merge sort or heap sort
	O(n^2)				Quadratic				Sorting an array using bubble sort or selection sort
	O(2^n)				Exponential				Calculation of Fibonacci numbers, Towers of Hanoi

Data Structures
	Data structures are a logical and mathematical model to store and organize data for computational operations so that it can be used efficiently
	Data structures are categorized into two types based on how we access the data
		Linear Data Structures: Elements in linear data structures are 

Data Structures Operations:
	1) Traversing - Access each data element exactly once so it can be processed
	2) Inserting  - To add a new element to the structure
	3) Deleting   - To remove an existing element from the structure
	4) Searching  - To find an element with a given key 
	5) Sorting
	6) Merging
	
Abstract Data Types (ADTs)
	What is abstract data types?
		ADT refers to a set of data values and associated well-defined operations that are independent of any particular implementation
		We know what a specific data types can do but we do not carevbvbbnnnnm/mmn/./.,;/.;;;;nnnbnbbnnb//≥≥≥//qwqwwq about the implementation details
		Examples
			short age;
			int Numbers[10];
		The implemntatino hide
	To go through spots in memory in an array in C:
		int myArray[10];
		A[q] base address + k * sizeof(int) 
			All the elements must be the same type (in order to make usage of k * sizeof(int))
	What is an array?
		A number

	How is the array stored in memory?
		Declare an array of 10 integers in C/C++
			myArray[10];
			myArray[3] = 7;
		Because the array is allocated in contiguous memory cells the compuder does not need to keep track of the address of every element
		It only needs to keep track of the address of the first element in myArray

Question 1:
	Consider the array AUTO which records the number of automobiles sold each year from 2010 through 2016.
	The base address of AUTO in memory is 400. What is the memory address of the automobiles sold in 2014
		Don't know the size of each record in the array, can't find it

Insertion Problems
	Dynamic allocation does not work in C with arrays alone
	Full array means we will get a segfault or runtime error
	Solutions? Check if we're about to go outside the size of the array and make a new array thats bigger

Searching:
	A: array, N: array size, ITEM: Search key
	1) Set j = 0 // j is a counter
	2) F = 0 // flag
	3) Repeat Steps 3 and 4 while J <= N
	4) 	If A[j] equals ITEM then F = 1, break
	5)	Set j = j + 1 
	6) Print F and j

Self Assesmnts:
	What is the run-time complexity of adding an item to an array? O(n)
	What is the run-time compleixty of deleting an item from an array? O(n)
	What is the worst case and best case complexity  for finding an item in an unsorted array? O(1) and O(n)
	What is the run-time compleixty for adding an item to end of an array? O(1)
bnmbb/.≥//]]]

*/