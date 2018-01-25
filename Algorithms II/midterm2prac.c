/* 	
	midterm2prac.c
	Shahir Chowdhury
	2017-03-08
	Review for midterm 2
*/

//INCLUDES
#include <stdio.h>

//C- PREPROCESSOR DIRECTIVES
//#define

//PROTOTYPES
void fmerge(float *A, float *B, float *c);
	
//MAIN
int main (void){
	char potato[100]; 

	double a[] = {0,1,3,3};
	double b[4] = {0};
	float c[8];
	//char *words = "I love buffalos and wildwings";

	printf("%d\n", a-(a+2));
	scanf("%[\n]s", potato);

	printf("%s", potato);

	return 0;
}

//FUNCTION DEFINITIONS

/*void fmerge(float *A, float *B, float *c){
	//float *end = c;

	/*for (int i=0; *(A+i) >= 0; i++){
		*end = *(A+i);
		end++;
	}
	for (int i=0; *(B+i) >= 0; i++){
		*end = *(B+i);
		end++;
	}

	/*for ( float *pos = c; *(pos + 1) >= 0; pos++){
		for (float *j = c; j < (*float)(end-pos);j++){
			if (*(c+j) > *(c+j+1)){
				float temp = *(c+j);
				*(c+j+1) = *(c+j);
				*(c+j) = temp;
			}
		}
	}
	printf("%ld", end);
	*(end + 1) = -1;

	return;
} */