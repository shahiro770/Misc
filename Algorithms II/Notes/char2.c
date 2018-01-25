/* 	
	Title:
	Author(s):
	Date last modified:
	Objective:
*/

//INCLUDES
#include <stdio.h>
#include <stdlib.h>

//C- PREPROCESSOR DIRECTIVES


//PROTOTYPES
			;

//MAIN
int main (void){
	int C; //can also use char
	while ((C = getchar()) != EOF && C != '\n'){
		putchar (C);
	}

	int A; float X;
	char S[100], M[100];
	char FormatStr[7] = "%d%f%s";

	scanf(FormatStr, &A, &X, S); 	//from the KBD (stdin)
	printf(FormatStr, A, X, S);		//To the monitor (stdout)

	fgets(M, 100, stdin);
	sscanf(M, FormatStr, &A, &X, S); 	//From RAM (location M)
	sprintf(M, FormatStr, A, X, S);		//To RAM (location M)
	puts(M);

	return 0;
}	//Did i get mumps vaccination


//FUNCTION DEFINITIONS