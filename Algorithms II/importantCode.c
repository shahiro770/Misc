/* 	
	Important Code Compilation.c
	Shahir Chowdhury
	2017-03-12
	This is a compilation of important code bits i made
*/

//INCLUDES
#include <stdio.h>
#include <ctype.h>
#include <string.h>

//C- PREPROCESSOR DIRECTIVES
#define true 1
#define false 0
#define MAXLINELENGTH 80		//maximum length of an inputtable line
#define MAXWORDLENGTH 20		//maximum length of any word in a line
#define MAXLINENUM 11			//maximum lines lines of text, including the number of lines specifier

//PROTOTYPES
			;

//MAIN
int main (void){
	//does what strtok does
	int wordcount = 0;
	_Bool isword = false;

	for (int i=1;i<=lineNum;i++){			//find the maximum possible number of words in the text passage
		for (int j=0;j<MAXLINELENGTH;j++){
			if (textlines[i][j] != 0 && !isblank(textlines[i][j]) && !iscntrl(textlines[i][j])){
				isword = true;
			}
			else if((isword == true) && (textlines[i][j] == 0 || isblank(textlines[i][j]) || iscntrl(textlines[i][j]))){
				isword = false;
				wordcount++;
			}
		}
	}
	printf("words found %d\n", wordcount);

	return 0;
}

//FUNCTION DEFINITIONS