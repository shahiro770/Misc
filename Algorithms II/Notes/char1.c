/* 	
	char1.c
	Shahir Chowdhury
	D2017-02-27
*/

//INCLUDES
#include <stdio.h>
#include <stdlib.h>

//C- PREPROCESSOR DIRECTIVES

//PROTOTYPES
			;

//MAIN
int main(void){

	double D;
	const char * S = " -123.895 $bC 7";
	char *EP;
	D = strtod(S, &EP);
	if (EP != S){
		printf("Value converted is %1f\n",D);
	}
	else{
		printf("No value could be converted\n");
	}
	S=EP;
	printf("%s", S);
	D = strtod(S, &EP);

	if (S != EP){
		printf("Value converted is %1f\n",D);
	}
	else{
		printf("No value could be converted\n");
	}

	return 0;
}

//FUNCTION DEFINITIONS