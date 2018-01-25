/* 	
	Lab6.c
	Shahir Chowdhury
	2017-02-27
	This program manipulates different string arrays.
*/

//INCLUDES
#include <stdio.h>
#include <string.h>

//PROTOTYPES
void Reverse(char *string);				//reverses the memory positions of characters in a string
void ParseSentence(char *string);		//prints out each individual word in a string, ignoring a set of punctuation
int GetLength(char *string);			//gets the length of a character array

//MAIN
int main(void){
	char buffer1[] = {'t','h','i','s',' ','i','s',' ','t','h','e',' ' ,'f','i','r','s','t',' ','b','u','f','f','e','r','.','\0'};
	char buffer2[] = "this is the second buffer";
	char buffer3[80]; 
	char *pBuffer = &buffer3[0];

	//PART A

	scanf("%[^\n]s", &buffer3[0]);		//inverted scanset so scanning terminates on '\n'

	printf("%s\n%s\n%s\n", buffer1,buffer2,buffer3);

	while (*pBuffer != '\0'){
		printf ("%c", *pBuffer);
		pBuffer++;
	}
	printf("\n");

	pBuffer = &buffer3[0];				//reset the position of pBuffer to the start of the string
	pBuffer += 3;
	while (*pBuffer != '\0'){
		printf ("%c", *pBuffer);
		pBuffer++;
	}
	printf("\n"); 

	//PART B

	Reverse(buffer3);
	printf("%s\n", buffer3);
	Reverse(buffer3);

	//PART C

	ParseSentence(buffer3);
}

//FUNCTION DEFINITIONS
/* 
	Objective: Reverses the memory positions of each character in a character array
	Input: string is assumed to be a valid string of characters, ending with the delimiter '\0'	
	Output: The memory locations of each character in the string are reversed
*/
void Reverse(char *string){
	char *start = &string[0];
	char *end = &string[0];					
	char temp;

	while((*(end+1)) != '\0'){
		end++;
	}
	
	//Stop swapping character values once the swap positions coincide if the string length is odd
	//or if the swap positions pass each other if the string length is even
	while ((start < end) && (start != end)){
		temp = *start;
		*start = *end;
		*end = temp;

		start++;
		end--;
	}
	
	return;
}

/* 
	Objective: Extracts the words from a string and displays them without any punctuation
	Input: string is assumed to be a 1d array of characters
	Output: Prints the words contained in a string 
*/
void ParseSentence(char *string){
	int length = GetLength(string);
	int lettercount = 0;
	char currword[length];			//each word cannot be larger than the length of the entire string
	char *curr = &string[0];

	for (int i=0;i<length;i++){
		*(currword + i) = 0;
	}
	
	while (*curr != '\0'){
		if ((*curr == ' ') || (*curr == ',') || (*curr == ';') || (*curr == '.')){
			if (*currword != 0){						//if there is a word to be printed
				for (int i=0;i<lettercount;i++){
					printf("%c", *(currword + i));
					*(currword + i) = 0;
				}

				printf("\n");
				lettercount = 0;
			}
		}
		else{
			*(currword + lettercount) = *curr;
			lettercount++;
		}
		curr++;	
	}

	if (*currword != 0){								//print the final word in the string if a delimiter character is hit before printing
		for (int i=0;i<lettercount;i++){
			printf("%c", *(currword + i));
		}
		printf("\n");
	}

	return;
}

/* 
	Objective: Get the length of a string
	Input: string is assumed to be a valid 1d array of characters, ending with the delimiter character '\0'
	Output: Returns the length of the string as an integer
*/
int GetLength(char *string){
	int count = 0;
	char *curr = &string[0];

	while (*curr != '\0'){
		count++;
		curr++;
	}

	return count;
}







