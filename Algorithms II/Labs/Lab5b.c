/* 	
	Lab5b.c
	Shahir Chowdhury
	2017-02-14
	This program bubblesorts a 1d array and prints the sorted form
*/

//INCLUDES
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//C- PREPROCESSOR DIRECTIVES
#define SIZE 20
#define true 1
#define false 0

//PROTOTYPES
void FillArray ( int *array, int size ); 	//fills an array with pseudo random value
void PrintArray ( int *array, int size );	//prints the contents in an array
void BubbleSort ( int *array, int size );	//bubble sorts an array
void Swap ( int *x, int *y );				//swaps the addresses of two values in an array

//MAIN
int main(void){
	int NumList[20];

	FillArray(NumList, SIZE);
	PrintArray(NumList, SIZE);
	BubbleSort(NumList, SIZE);
	PrintArray(NumList, SIZE);

	return 0;
}

//FUNCTION DEFINITIONS
/* 
	Objective: Fills a given 1D array with pseudo-random values from 0 to 100
	Input: array is assumed to be a 1D array, size is the size of the array
	Output: Fills the array with values 
*/
void FillArray ( int *array, int size ){
	srand(time(NULL));		
	
	for (int i=0;i<size;i++){	
		int r = (rand() % 101); 
		*(array + i) = r;					// OR DO array = r
											// array++
	}
	
	return;
}

/* 
	Objective: Prints the contents of a 1D array
	Input: array is assumed to be 1D, size is the size of the array
	Output: Prints all the values in the array in a nice format
*/
void PrintArray ( int *array, int size ){
	for (int i=0;i<size;i++){
		printf("%d ", *(array + i));
	}
	printf("\n");
	return;
}

/* 
	Objective: Bubble sorts a 1d array in descending order (greatest to smallest)
	Input: array is assumed to be 1D, size is the size of the array
	Output: Sorts the array in descending order 
*/
void BubbleSort ( int *array, int size ){
	_Bool bsFlag = true;
	for (int i=0;i<size;i++){
		for (int j=0;j<size-1;j++){
			if (*(array + j) < *(array + j + 1)){
				Swap((array + j), (array + j + 1));
				bsFlag = false;
			}
		}
		if (bsFlag == true){ //stop sorting prematurely if array no longer needs sorting
			break;
		}
	}
	return;
}

/* 
	Objective: Swaps the values of two integer variables
	Input: x and y are assumed to be two integers
	Output: Swaps the values that they point to 
*/
void Swap(int *x, int *y){
	int temp = *x;
	*x = *y;
	*y = temp;

	return;
}