/* 	
	assign3.c
	Shahir Chowdhury
	Last Modified: 2017-03-12
	This program analyzes several lines of text and displays the frequency of letters, words, and word lengths in the given text.
*/

//INCLUDES
#include <stdio.h>
#include <ctype.h>
#include <string.h>

//C- PREPROCESSOR DIRECTIVES
#define MAXLINELENGTH 80		//maximum length of an inputtable line
#define MAXWORDLENGTH 20		//maximum length of any word in a line
#define MAXLINENUM 11			//maximum lines of text, including the number of lines specifier

//PROTOTYPES
void letterAnalysis(char text[][MAXLINELENGTH], int lineNum);					//displays the frequency of each letter in the text
int wordLengthAnalysis(char text[][MAXLINELENGTH], int lineNum, int length);	//finds the frequency of an inputted word length in the text
void wordAnalysis(char text[][MAXLINELENGTH], int lineNum);						//displays the frequency of each word in the text
void fancyspacer(char word[], int len);											//spaces out output in wordAnalysis

//MAIN
int main(void){
	int lineNum; 			//number of lines in text
	scanf("%d", &lineNum);
	char text[MAXLINENUM][MAXLINELENGTH] = {{0}};

	for (int i=0;i<=lineNum;i++){
		fgets(text[i],80,stdin);
	}

	letterAnalysis(text, lineNum);

	printf("Total Word Lengths:\n");
	for (int i=1;i<=20;i++){						//minimum word length is 1, maximum is assumed to be 20
		int occ = wordLengthAnalysis(text, lineNum, i);	
		if (occ == 1){
			printf("%d\tword of length %d\n", occ, i);
		}
		else if (occ > 1){
			printf("%d\twords of length %d\n", occ, i);
		}
	}

	wordAnalysis(text, lineNum);
	
	return 0;
}

//FUNCTION DEFINITIONS
/* 
	Objective: Finds the number of times each letter of the alphabet occured in the text
	Input: text is assumed to be a 2d char array with maximum proportions MAXLINENUM x MAXLINELENGTH
		   lineNum is the number of lines containing strings in the input
	Output: Displays the frequency of each letter in a nice tabular format
*/
void letterAnalysis(char text[][MAXLINELENGTH], int lineNum){
	int letterNum[26] = {0};						//frequency array of each letter in the alphabet
	char currlet;									//current letter being observed

	for (int i=1;i<=lineNum;i++){					//i = 1 to skip first line containing the line number specifier
		for (int j=0;j<MAXLINELENGTH;j++){
			if (isalpha(text[i][j])){
				currlet = tolower(text[i][j]);
				letterNum[currlet - 'a']++;			//each letter in ascii has a value of +97 from its  position in the frequency array
			}
		}
	}

	printf("Total letter counts:\n");
	for (int i=0; i<26;i++){
		printf("%c: %2d\n", i + 'a', letterNum[i]);
	}

	return;
}

/* 
	Objective: Finds the frequency of words with an inputted length in the text
	Input: text is assumed to be a 2d char array with maximum proportions MAXLINENUM x MAXLINELENGTH
		   lineNum is the number of lines containing strings in the input
		   length is the current word length being searched for
	Output: Returns the frequency of words of that length in the text
*/
int wordLengthAnalysis(char text[][MAXLINELENGTH], int lineNum, int length){
	int total = 0;									//counter for number of words with the given length
	int currwordlength;								//length of current word being observed

	for (int i=1;i<=lineNum;i++){					//i = 1 to skip first line 
		currwordlength = 0;
		for (int j=0;j<MAXLINELENGTH;j++){
			if ((!isblank(text[i][j])) && (text[i][j] != 0) && (!iscntrl(text[i][j]))){
				currwordlength++;
			}
			else if(text[i][j] == 0 || isblank(text[i][j])) {
				if (currwordlength == length){
					total++;
				}
				currwordlength = 0;
			}
		}
	}

	return total;
}

/* 
	Objective: Finds the number of times each word appeared in the text
	Input: text is assumed to be a 2d char array with maximum proportions MAXLINENUM x MAXLINELENGTH
		   lineNum is the number of lines containing strings in the input
	Output: Displays the frequency of each word in a nice tabular format
*/
void wordAnalysis(char text[][MAXLINELENGTH], int lineNum){
	char text2[MAXLINENUM][MAXLINELENGTH];			//duplicate of text array for a second string tokenization
	char *tokenPtr;									//points to each token in a string
	int wordcount = 0;								//counter for the number of words in the text
	int largest = 0; 								//size of the largest string in the text

	for (int i=0;i<MAXLINENUM;i++){					//store a duplicate of text
		strcpy(text2[i], text[i]);		
	}

	for (int i=1;i<=lineNum;i++){					//counter the number words in text
		tokenPtr = strtok(text[i], " \n\r\b\t");

		while (tokenPtr != NULL){
			wordcount++;
			tokenPtr = strtok(NULL, " \n\r\b\t");

		}
	}

	char words[wordcount][MAXWORDLENGTH];			//list of all the words in the inputted text
	int wordocc[wordcount];							//frequency of each word in words array
	int wordpos = 0; 								//position in words array;		

	memset(words, 0, sizeof(words[0][0]) * wordcount * MAXWORDLENGTH);
	memset(wordocc, 0, sizeof(wordocc[0]) * wordcount);
	
	for (int i=1;i<=lineNum;i++){					//store each word in the text inside the words array 
		tokenPtr = strtok(text2[i], " \n\r\b\t"); 

		while (tokenPtr != NULL){
			strcpy(words[wordpos], tokenPtr); 

			wordpos++;
			tokenPtr = strtok(NULL, " \n\r\b\t");
		}
	}

	for (int i=0;i<wordcount;i++){					//count the number of times each word appears in the words array
		if (strcmp(words[i]," ") != 0){
			for (int j=i;j<wordcount;j++){
				if (strcmp(words[i], words[j]) == 0){
					wordocc[i]++;
					if (i != j){					//ignore first instance of word when storing data in array
						strcpy(words[j]," ");		//ignore future analysis of a word if a duplicate appears 
					}	
				}
			}
		}
	}

	for (int i=0;i<wordcount;i++){					//find the largest word in words 
		int currlen = 0;						
		for (int j=0;j<MAXWORDLENGTH;j++){
			if (words[i][j] != 0){
				currlen++;
			}
		}
		if (currlen > largest){
			largest = currlen;
		}
	}

	printf("Total Word Occurences:\n");
	for (int i=0;i<wordcount;i++){
		if (wordocc[i] >= 1){
			printf("\"%s\"", words[i]);
			fancyspacer(words[i], largest);
			if (wordocc[i] == 1){
				printf("appeared 1 time\n");
			}
			else if (wordocc[i] > 1){
				printf("appeared %d times\n", wordocc[i]);
			}
		}	
	} 

	return;
}

/* 
	Objective: Formats output to allign text in a nice tabular format
	Input: word is assumed to be a 1d char array, len is the largest word length that occured in the text
	Output: Prints a given number of spaces equal to the difference between length and the word's length
*/
void fancyspacer(char word[], int len){
	int wordlen = strlen(word);

	for (int i=0;i<len - wordlen + 1;i++){
		printf(" ");
	}
	
	return;
}





