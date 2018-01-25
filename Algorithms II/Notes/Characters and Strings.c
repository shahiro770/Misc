/* Characters and Strings
	IBM = International Business Machines

	-A string refers to a sequence of items
		-0 or more elements
		-an abstract data structure that can interpret data
	-An end of string control character; a DELIMITER
	-A string of characters in cs terms, usually refers to a vector, or list, of char values

	-ASCII is commonly used
	-UniCode is another

	-In the C language, the special delimiter character '\0' (called character null) is recognized by the compiler and the 
	assigned 

Character Handling Libraries
---------------------------
	Chracter Type Library <ctype.h>
	--------------------------------
		-in WWII, 8 wires sent 8 bits, thats why we started with an 8 bit codes
			-Three levels of voltage were sent
				-High level = 1
				-Low level = 0
				-Very Low level = did signal send?
			-7 bits represent -127 to 128 characters (Alphabet, punctuation, successful transmisions/receivals bits (acknowledgements), 
			negative acknowledgements (errors), sounds, etc.)
			-8th bit was parity bit (DIGITAL DESIGN OVERLAPS WOAHHHHHH)
			-carriage return was checking print from left to right
			-line feed meant rotating 
			-0x30 = numbers
			-0x41 = capital letters
			-0x61 = lowercase letters
		-Now they do it with radio signals

		-Begin with character query functions
							 -------
			-General prototype form:
				int isValueRange (int c); //returns 1 if a match, or 0
				-ValueRange refers to a single value or a range of values
		-Note the input argument c has the data type int
			-input argument c has the data type int
				-intuition would suggest c should be char type */

		int isblank (int c); 	// Returns a positive value if c is ' '; otherwise 0
		int isdigit (int c); 	// Returns a positive value if c is a base 10 digit in the range '0' to '9'; otherwise 0
		int isalpha (int c);	// Returns positive value if c is in the alphabet (lower or uppercase); otherwise 0
		int isalnum (int c);	// Returns a postive value if c is an alphabetic character, or a base 10 digit; otherwise 0
		int isxdigit (int c);	// Returns a positive value if c is a base 16 (hexadecimal) digit in the range '0' to '9' or
								// or 'a' to 'f' or 'A' to 'F'; otherwise 0

		int isblank (int c){
			if (c != ' '){
				return 0;
			}
			return 1;
		}

		int isdigit (int c){				
			if (c >= '0' && c <= '9'){		//A lexicon is a dictionary, lexical ordering is apple > banana, or alpha > apple
				return 1;
			}
			return 0;
		}

		/* 	-Additional query fucntions provide infor about the nature of the character data

			-Transformative functions modify the character data 	*/

		int tolower (int c ){
			if (c >= 'A' && c <= 'Z'){			//only works for meaningful input (uppercase * vs lowercase *)
				return c + 'a' - 'A'; 			// c + 32 for ASCII
			}
			return c;
		}

		int toupper (int c){
			if (c >= 'a' && c <= 'z'){
				return c + 'A' - 'a';			// c - 32 for ASCII
			}
		}

		/* -query functions for non-alphanumeric character data (e.g. graphical, control signals, punctuation) */

		int isspace (int c); 	//checks if valid white space character (blank,newline,tab, etc.)
		int iscntrl (int c); 	//checks if control character such as \n and \b
		int ispunct (int c);	//checks if valid printable punctuation character
		int isprint (int c);	//returns valid printable character data
		int isgraph (int c;)	//check if graphical symbol ('%', '$', etc.)

/*	String Conversion Functions <stdlib.h>
	--------------------------------
	
	-Purpose of these functions is to convert a string (or portion to (1) an integer or (2) a floating point type)
	-General prototype form :*/
		
		resultType strtoOutputType (const char * nPtr, char **endPtr[,int `1	base]);

/*		** points to a pointer
		//shahir is a big 'ol loser 

		nPtr points at the input string (protected as constant)
		resultType refers to one of double,	long int, or unsigned long int
		OutputType refres to one of d,		l,	or 		 ul 			(OutputType will convert character information to a numeric data type)
		base refers to the base of the input string (0, or 2.......36)
		endPtr points at the position within the input string 
		
*/
		double strtod(const char * nPtr, char **endPtr);	//converts char to double, or returns 0 if no part of the input string can be converted
															//will ignore spaces, terminates once a non-numeric character is read after a series of number characters
															//e.g. "  -123.895 $bc\0" will only read "-123.895" 

		long strol(const char * nPtr, char **endPtr, int base);				//converts char to long int, 0 if no part of the input string can be converted
		unsigned long stroul(const char * nPtr, char **endPtr, int base);	//performs analgously to strtol() for string to unsigned long in conversion
																			//base will default to 8, 10, or 16 if given as 0, base 10 is default
		/* Note about base argument value
		  ------------------------------
			-For base  = 0, the input string digits may be in base 8, 10 , or 16
			-The case base 1 is not used
			-For 2 <= base <=36 the characters that are interpretable as base digits lie in the range from 0 to (base-1) */

		int getchar (void);								//Fetches and returns a single character from the input stream (stdlin); if end of file
														//is signalled then the return value is EOF
		int putchar (int C);							//Outputs a single character to the output stream (stdout). Returns the same character if successful;
														//otherwise returns EOF on failure
		char *fgets (char * S, int N, FILE * stream);	//Fetches all characters up to either (a) a new line '\n', or b) EOF, or (c) N-1 characters have been inputted, and then appends a delimiter '\0'
														//to make a string.
		int puts (const char * S);						//Outputs the string of characters S, followed by a newline '\n'. Returns a non-zero integer result
														//(typically the number of characters outputted), or EOF on failure

		/* The functions sprintf() and sscanf() are used for processing of cahracter (string) data and machine represntations of data 
		(according to different data types)
			- All data processing is done in RAM - no I/O is involved */

		int sprintf (char *S, const char * format [, ...]); //used like printf, except the string of characters produced is directed to the string argument S,
															//according to the format string (and referenced parameters)
		int sscanf (char *S, const char *format[,...]);		//used in the same way as scanf(), except the string S contains the "input"
															//data to be processed according to the format string (and referenced parameters)


		/* Two functions ar eprovided to perform copying of one string into another string */

		char * strcpy (char * Dest, const char * Src);		//copies source string (src) to destination
															//perfect copy is source is smaller to equal to size of destination
															//if destination is smaller than source, will copy characters that 
															//fit, which may ignore delimiter '\0', leading to an invalid string

		char * strncpy (char * Dest, const char * Src, size_t N);	//copies the first N characters of the source string Src to the destination 
																	//(same problems as strcpy)

		/* Joining together of two strings is called string catenation (also called concatenation)
				- For instance, one might combine vairous words and phrases to form sentences and paragraphs */

		char * strcat (char * S1, const char * S2);			//Copies S2 to a position in S!, following the string already in S1
															//Note that the '\0' delimiter in S1 is overwritten by the first character in the S2 so only one delimiter
															//If the total number of characters is greater than the capcity of S1, then logic error

		char * strncat (char * S1, const char * S2, size_t N);	//copies the first N cahracters of S2 to S1
																//Behaves similarly to strcat

		/* Comparison of two strings is based on the notion of lexical ordering 
				- All characters are encoded (e.g. ASCII) and the numeric values of the characters defines the possible orderings */

		int strcmp (const char * S1, const char S2);	//Compares strings S1 and S2, returns 0 if S1 is equivalent to S2, 
														//positive if S1 > S2 and a negative if S1 < S2

		/* Searching for various characters and substrings within a string can be done with these functions*/

		char * strchr (const char * S , int C);	//locates the position in S of the FIRST occurnce of C, returns the pointer value to where 
												//C is first located, NULL otherwise

		size_t strspn (const char * S1, const char * S2); 	//S1 is searched, and returns th length of the initial substring segment 
															//in S1 that contains characters ONLY found in S2

		size_t strcspn (const char * S1, const char * S2);	//contraspan searches S1, and returns the length of characters NOT found in S2

		char * strpbrk (const char * S1, const char * S2);	//Locates the first occurence in S1 of any character found in S2 
															//returns a pointer to that position in S1, null otherwise

		char * strrchr (const char * S1, int C);			//Locates the last occurence in S1 of any character found in S2 
															//returns a pointer to that position in S1, null otherwise

		char * strstr (const char * S1, const char * S2);	//Locates the first occurence in S1 of the ENTIRE string S2
															//returns  that position in S1, null otherwise 
		char *strtok (char *S1, const char *S2);	//The first call to strtok states the argument S1 and provides the string of delimiters S2
													//Returns a pointer to the next token found in S1. Each subsequent call to strtok() uses NULL
													//as the first arg (instead of string S1), and the function REMEMBERS where it left off from the
													//last call. Each time strtok() is called, it points to the next token found and also replaces
													//the delimiter character by '\0'. Thus S1 is modified. Thus a sequence of calls to strtok() breaks
													//S1 into token substrings

		/* C also provides functions for dealing with blocks  of data in RAM
		   The blocks may be characters or other data types, hence the functions typically return a void * pointer value
		   (A void * pointer value can be assigned to any other pointer type, and vice versa) */

		void *memcpy (void *S1, const void *S2, size_t N);	//Copies N characters (bytes) from the object S2 into the object S1.
															//A pointer to the resulting object (S1) is returned, otherwise NULL is returned on failure
															//Note: the result of this function is UNDEFINED if S1 and S2 overlap 

		void *memmove (void *S1, const void *S2, size_t N);	//Copies N characters (bytes) from object S2 into the object S1. A pointer to the resulting
															//object (S1) is returned, otherwise NULL is returned on failure. Note: this function utilizes a temporary
															//memory space to perform the copying, hence the operation is always defined.

		int memcmp (const void *S1, const *S2, size_t N);	//Compares the first N characters (bytes) of S1 and S2. Returns 0 if S1 == S2,
															//>0 if S1 > S2, and <0 if S1 < S2

		void *memchr (const void *S1, int C, size_t N);		//Locates the first occurence of the character C in the first N characters (bytes)
															//of S1. If C is found, a pointer to C in S1 is returned. Otherwise, NULL is returned

		void *memset(void *S1, int C, size_t N);	//Copies the character (byte) C to the first N positions of S1. A pointer to S1 is returned,
													//or  NULL on failure. Note: The type of C is modified to unsigned char to enable copying to blocks
													//of arbitrary data type

		size_t strlen (const char *S);	//Determines and returns the number of characters in S, not including the '\0' delimiter

		/* 	-C11 standard with Annex K.
			-Addresses issues related to robustness of array based manipulation of character data (and other data containers)
				-Stack overflow detection
				-Array overflow detection */


































*/
