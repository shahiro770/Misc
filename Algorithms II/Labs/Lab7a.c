/* 	
	Lab7a.c
	Shahir Chowdhury
	2017-03-07
	This program recreates some well known C library functions
*/

//INCLUDES
#include <stdio.h>

//C- PREPROCESSOR DIRECTIVES
#define true 1

//PROTOTYPES
int myAtoI ( const char * str );								//converts an string of integers to an integer
int myStrCmp ( const char * str1, const char * str2 );			//compares the ascii values of two strings
char * myStrCpy ( char * destination, const char * source ); 	//copies one string onto another
char * myStrCat ( char * destination, const char * source ); 	//concatenates two strings
char * myStrChr ( char * str, int character );					//searches a string for the first occurence of a character

//MAIN
int main (void){
	printf("%d\n", myAtoI("1234"));

	printf("%d\n", myStrCmp("Pota", "Potat"));

	char s[] = "Potat";
	char b[] = "Row";
	printf("%s\n", myStrCpy(s,b));

	char src[100];
	char dest[100]; 
	myStrCpy(src, " source");
	myStrCpy(dest, "dest");
	printf("%s\n", myStrCat(dest, src));

	printf("%s\n", myStrChr("Potato", 'P'));


	return 0;
}

//FUNCTION DEFINITIONS
/*
	Objective: Converts a string of integers to an integer
	Input: str is assumed to be a valid string of characters ending with the delimiter character  '\0' 
	Output: Returns the initial segment containing an integer value within the string
*/
int myAtoI ( const char * str ){
	int val = 0;
	int negflag = 0; //flag for if number is negative

	if (*str == '-'){
		negflag = 1;
		str++;
	}
	while (*str != '\0'){
		if ((*str >= '0') && (*str <= '9')){
			val = val*10 + (*str - '0');
			str++;
		}
		else{
			if (negflag){
				val *= -1;
			}
			return val;
		}
	}
	if (negflag){
		val *= -1;
	}

	return val;
}

/*
	Objective: Compares two strings and determines if they are equal
	Input: str1 and str2 are assumed to be valid strings of characters both ending with the delimiter character  '\0' 
	Output: Returns 1 if str1 is of greater value than str2, -1 if str1 is of lesser value than str2, and 0 if they are equal
*/
int myStrCmp ( const char * str1, const char * str2 ){	
	while ((*str1 != '\0') && (*str2 != '\0')){
		if (*str1 > *str2){
			return 1;
		}
		if (*str2 > *str1){
			return -1;
		}
		str1++;
		str2++;
	}
	if (*str1 == '\0' && *str2 == '\0'){	//strings are equivalent case
		return 0;
	}
	if (*str2 == '\0'){						//str2 is shorter than str1 and preceding characters were lower in value
		return 1;
	}
	else{									//str2 is longer than str1 and preceding characters were lower in value
		return -1;
	}

	return 0;
}

//Assume destination is large enough to contain source
/*
	Objective: Copies one string's contents onto another string
	Input: 	destination and source are assumed to be valid strings of characters both ending with the delimiter character  '\0' 
			destination is assumed to be large enough to contain the characters held in source
	Output: Returns a pointer to the destination string (the string who's contents were overwritten in the copying process)
*/
char *myStrCpy ( char * destination, const char * source ){
	char *start = destination;

	while (true){
		*destination = *source;
		if (*destination == '\0'){	//check after each copy to see if the delimiter character was reached
			break;
		}
		destination++;
		source++;
	}

	return start;
} 

//Assume destination is large enough to contain source
/*
	Objective: Adds an inputted string on to the end of another string
	Input: 	destination and source are assumed to be valid strings of characters both ending with the delimiter character  '\0' 
			destination is assumed to be large enough to contain the characters held in source
	Output: Returns a pointer to the destination string (the string who had another string joined at the end of it)
*/
char *myStrCat ( char * destination, const char * source ){
	char *start = destination;

	while (*destination != '\0'){
		destination++;
	}

	*destination = *source;
	destination++;
	source++;

	while (true){
		*destination = *source;
		if (*destination == '\0'){ //check after each addition to see if the delimiter character was reached
			break;
		}
		destination++;
		source++;
	}

	return start;
}
/*
	Objective: Scans a string to see if an inputted character apears within its contents
	Input: 	str is assumed to be a valid string of characters ending with the delimiter character  '\0'
			character is assumed to be an integer value corresponding to a valid ASCII value
	Output: Returns a pointer to the location of the character within the string, NULL otherwise
*/
char *myStrChr ( char * str, int character){
	while ( *str != '\0'){
		if (*str == character){
			return str;
		}
		str++;
	}

	return NULL;
}