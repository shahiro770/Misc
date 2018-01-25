/* Abstract Containers and Logic (Structures, Unions, Bit Manipulation and Enumerations)
	
	Previously we introduced the concept and techniques related to the array data structure
		-A collection of identical data types
			-Permits use ofoperations defined on the data types
		-Allocated (logically) contiguous locations in RAM
			-Permits use of relative offset to provide direct access to elements, based on the properties of the data types

	This raises the question: can one create other kinds of (abstract) data structures, and types, in the C language?
		-The answer is yes!
	

	struct
	------------
		-Contains different data types
			-permits use of operations approriate to each type
			-allocates memory in a way that supports direct access to elements
			-supports user defined abstract data types and containers

		-the C alnguage struct will provide what we need

		-Quick example
*/
		struct {
			int ID;				//ID for this circle
			float XC;			//Centre X coordinate
			float YC;			//Centre Y coordinate
			float Radius;		//Radius of circle
			char Colour;		//Colour of circl
		} aCircle; 				//name of this circle data structure

/*
		It is up to programmers to design and define the internal fields within a struct
			-Keep in mind that this is a logical framework that supports being able to refer to a field within a struct
			-Referencing struct fields is based on hierarchial access using the '.' operator as a binary operator connecting 
			 the name of the struct container and one of its subfields
			-This is demonstrated by the intialization assignments
				aCircle.ID = 1;
				aCircle.Radius = 1.0;
				aCircle.Colour = 'R';

				aCircle = Parent
				.______ = Child

		The actual organization and allocation of memory(RAM) to each field and to the struct as a whole, may not follow
		the conceptual definition

			aCircle =  	[0]
						[0]
						[1]
						[1]
						[5]
						[2]
						[3]
						[5]
						[4]
						[4]
			0 = ID
			1 = XC
			2 = YC
			3 = Radius
			4 = Colour
			5 = Sometimes memory leaks occur due to poorly design software/compiler (can be serious)
				-Hackers can insert malware into these locations

		There is a severe limitation with respect to the example

		*/struct {
			int ID;				//ID for this circle
			float XC;			//Centre X coordinate
			float YC;			//Centre Y coordinate
			float Radius;		//Radius of circle
			char Colour;		//Colour of circl
		} aCircle; 				//name of this circle data structure 

			/* -We can only define one Circle variable (or a list of variables)
			
		To overcome this, we need to define a structure type tag, hence: */
			struct CircleStruct { 	//Add the type tag CircleStruct (This declares a type, NOT a variable)
				int ID;				//ID for this circle
				float XC;			//Centre X coordinate
				float YC;			//Centre Y coordinate
				float Radius;		//Radius of circle
				char Colour;		//Colour of circle
			};
		/* Now the type of this structure is called struct CircleStruct */

		struct  CircleStruct aCircle1, aCircle2, AnotherCircle;

		/* Also very useful, create an alias name for this type (and avoid unecessary typing) using the typedef compiler directive */

		typedef struct CircleStruct circleTypeName; //typedef gives it a simpler alias

		//so we can now do this
		circleTypeName aCircle3, circleArray[100], *ptrCircle;

		//It is also possible to use struct's hierarchically, as shown in the example:

		struct objectType{
			circleTypeName aCircle;
			rectangleTypeName aRectangle;
			ellipseTypeName aEllipse;
			squareTypeName aSquare;
			struct objectType *ptrCircle; //POINTS TO ITSELF???
		} aObject;

		//Above we assume that typedef's have been defined for these structs

		/* One particular important use of structs arises when we include one or more pointer fields, including cases where the 
		pointer points to the same struct type
			//SELF REFERENTIAL DATA STRUCTURE
		*/

		struct CircleStruct aCircle1, aCircle2, arrCircle[20];

		/*It is vital to understand the rules and limitations concerning assignment operations and the size of the struct allocation
			-Recall that the manner of implementation in RAM may not follow the definition
				-As a consequence, holes may exist, by definition, a hole cannot be referenced hence cannot be initialized
				-A variable that has not been initialized cannot be assigned to another variable under majority of compilers
		*/

		aCircle2 = aCircle1;		//NOT ALLOWED!
		aCircle1.ID = aCircle2.ID 	//copy field by field! (assuming field has been initilized);

		/* general strategy is to make a copy function to copy all fields between objects 

		To emphasize the issue of RAM allocation implementation dependency, consider again*/

		struct CircleStruct { 	//Add the type tag CircleStruct (This declares a type, NOT a variable)
			int ID;				//ID for this circle
			float XC;			//Centre X coordinate
			float YC;			//Centre Y coordinate
			float Radius;		//Radius of circle
			char Colour;		//Colour of circl
		};

		//Note the relationship based on the example above

			sizeof(aCircle) >= sizeof(int) + 3*sizeof(float) + sizeof(char);

		/* Now we return to deal with pointers to structs */

		typedef struct Card Card_t;
		struct {
			int Value; 
			char Suit;
		} Card;
		Card_t Hand[52];
		card_t *ptrCard;

		ptrCard = &Hand[0];		//refer to the first card in the hand
		Hand[0].Value = 1;		//deal an Ace
		Hand[0].Suit = 'S';		//Make it a sapce ('S','H','D','C')
		Hand[1] = {3, 'D'};		//Deal next card as a 3 of Diamonds

		ptrCard -> Value = 10;	//change to a 10 instead
		ptrCard -> Suit = 'H';	//make it a heart

		//-> is the character known as the pointer-to-structure dereferencing operator (MUST be ->)

		//This operator avoids confusing using the * dereferencing operator, as shown below:

			ptrCard -> Value = 10;	//becomes (*ptrCard).Value = 10;	/
			ptrCard -> Suit  = 'H';	//becomes (*ptrCard).Suit = 'H';

		//NOTE

			*ptrCard.Value = 10;	//dereferencing an int IS DISALLOWED
			*(ptrCard).Value = 'H'; //dereferencing a struct to obtain a value IS ALLOWED
		
		/*The C language also provides for another abstract container called union
			-Contains different data types
				-Permitting use of operations appropriate to each type
				-Allocates memory in a way that still supports direct access to elements

		*/
			union TagName {	//Also has potential for holes
				int N;
				float F;
				double D;
			} aU;
			aU.N = f;

		/* The union container is used primarily as a way of saving storage since many
		variables with different data types can re-use the same (roughly) allocated RAM storage
			-This is less important today than years ago due to the dramatic increase in RAM size (4+GB) and lowering its cost ($GB)

		-Don't worry about unions much

	Logic and Sequences
	--------------------
		-As well as introducing abstract data types and containers we also consider two additional issues
			-Bit manipulation 
			-Enmuerated data types
		-Boolean logic
			-Created by George Boole (mid 1800s), further developed by others
				-Boole is considered one of the founders of CS
				-Boolean Logic is essential for designing and constructing digital computer s(von Neuman architectures)
				-Based on notions from Set Theory
			-Fundamental operations defined on bits, or sets of bits (concept as a 2 valued state)
				-AND(&), OR(|), COMPLEMENT (~), and other operators
				-These allow for manipulation of bits

		Logic
		--------
		0 is FALSE
		1 is TRUE

		& - AND (set intersection)
		| - inclusive OR (set union)
		^ - exclusive OR (set distinction)
		~ - Complement (set negation)

		-Performing the same operations on sets of bits are referred to as bitwise operations
		-Assume two ordered sets of 4 bits, A and B
			-Specifically:	A = 1100 and B = 1010

			A & B = 1000

			A | B = 1110

			A ^ B = 0110

			~ A   = 0011 DO NOT CONFUSE WITH THE RELATIONAL NOT '!' OPERATOR (~ CHANGES A, ! COMPARES VALUES)

		-Some interesting interpretive patterns arise for the & and | operators with certain argument values
		-E.g. A0 = 00, A1 = 11, B = 10
	
		- Using & to prove or RESET bit values
			A1 & B: A1 & B = 11 & 10 = 10 = B
			A0 & B: A0 & B = 00 & 10 = 00 	All bits reset to 0

		-Using | to prove or SET bit values
			A1 | B:	A1 
			A0 | B:
	
		-Using ~ as a bit TOGGLE

		-Using ^ to TOGGLE bit values

		-An interesting fact about XOR
			-Assume two ordered sets of 4 bits, A and B
				-Recall that A^B = 0110

			-Now consider the sequence of statements through an exhaustive proof
				A = A ^ B;	// A = 0110 B = 1010
				B = A ^ B;	// A = 0110 B = 1100
				A = A ^ B;	// A = 1010	B = 1100 //SWAPS THE ORIGINAL VALUES WITH NO TEMPS (called a "swap in place")

		Advanced Logic
		----------------

		-Boolean Logic also admits several additional operators
			NAND (Not AND)
				- A nand B == Not (A and B) ~(A & B)
			XOR (Not OR)
				- A nor B == not (A or B)	~(A | B)
			XNOR (selective OR)
				- A xnor B == (A and B) or ((not A) and (not B))	~(A ^ B)

		-These are used in hardware circuit design
		-De Morgan's theorems hold between both bit operations

		Quick Aside
		------------

		-Before proceeding, a quick aside on represtnations of integer data, using bits
			-The unsigned int data type has a numeric represntation, starting at 0 and increasing in steps of 1.
			- In base-2 using an 8 bit container size
				0 	00000000
				10	00001010
				255 11111111
			-The signed binary type int provides for both positive and negative integer numbers.
				-Assume an arbitrarty integer, N, and we'll call the machine representation X
					-X is formally defined (using logic) by:
						-X = |N| if N >= 0 , x = ~|N| + 1 if N < 0
						- For N = 1, x = {00000....0001} while for N = -1, X = {11111.... 1111}  

		-Another type of bit manipulation involves shifting of bit opsitions within a container
			-We will focus on shifting with integer types, both unsigned (logical shift) and signed binary (arithmetic shift)
			representations
				-Shifting is done differently on each representation

		-BASICS:
			-Shift operators have two forms:
				- Left Shift: <<		
				- Right shift: >>
				Examples: (Assume A = 00011010 in each case)
					A = A << 3;		A =	11010000	//in to the toilet once you shift out of the bits
					A = A >> 3;		A =	00000011	
					A = A << 8;		A = 00000000

				-bit shifting on an unsigned integer can not result in bits jumping 

			-Unsigned Integers are easy to deal with
				-When left shifting, high order bits are shifted out of the container and henceforth ignored -- they become lost bits!
				 zero bits are inserted onto the

				-Note that shifting to the left by 1 is equivalent to multiplying by 2. Thus shifting by K bits is equivalent to multiplying by pow (2,K)
					-Roughly multiplication 100 times slower than bit shifts

			-We Must be a bit more careful when dealing with signed binary (arithmetic shift) representations (specifically, int)
				-The semantic context of negative numbers must be preserved
				-Must be careful to interpret some results

				-Examples: (independent statements) Assume A = 00011010 (decimal 28)
					A = A << 3;		A =	11010000	negative ? //does not make sense
					A = A >> 3;		A =	00000011	3	//equivalent to integer divide by 8
					A = A << 8;		A = 00000000	0	//does not make sense

		Enumerated Data Type
		----------------------
			Dancer :: {First, Second, Third, Fourth, Fifth}
			Number :: {ONE, TWO, THREE}

			Think in numbers?
			
			An enumerated constant is a user-defined specification of a range of values, represented symbolically, and adapted to specific
			programming problems

			-Defined by the C language statement */
				enum eName {idlist};

			//Ex
				enum Workday {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};

				enum Colour {Red, Green, Blue, Violet};

			/* Each of the symbols above are assigned default values by the compiler, beginning with the first symbol (usually 0) and then
			incrementing by 1 the value of each successive symbol in the list
				-This can be modified by programming */

				enum Colour {Red = 1, Green, Blue, Violet}; //start at 1
				enum Colour {Red = 1 , Green = 3, Blue = 4, Violet = 2}; //Redfine the ordering

			//Enumerated lists provide a self documenting approach to useful symbols

				enum Colour {Red = 1, Green, Blue, Violet}; //Start at 1
				char TextColour[5][10] = {"Error", "Red", "Green", "Blue", "Violet"};

				char *ptrColour
				ptrColour = &TextColour[Green]		//Using enumerated constant
				printf ("The colour is %s\n", ptrColour); //outputs the string "Green"

			//It is the ability to write code using common, intuitive terminology that makes the job easier for programmers
					//Working with numbers and pointers can be confusing in simple applications

			//Can get up to 2^24 possible colour intensities on your screen thanks to hardware

			



