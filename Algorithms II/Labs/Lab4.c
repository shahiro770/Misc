/* 	
	Lab4.c
	Shahir Chowdhury
	2017-07-02
	This program contains some tracing alongside a bunch of random functions
*/

/*QUESTION 1/////////////////////////////////////////////////////////////////

 #include <stdio.h> int main(){
	int ids[3] = {100,200,300};
	int *salary, salary1, salary2, *salary3;
	salary1 = ids[0] * ids[1];
	salary = &ids[1] ;
	salary2 = *(ids+1)* *(ids+2);
	salary3 = ids+2;
	printf("*salary = %d\nsalary1 = %d\n", *salary, salary1); 
	printf("salary2 = %d\nsalary3 = %p\n", salary2, salary3);
}

TRACING////////////

salary1 = ids[0] * ids[1] = 100 * 200 = 20000
salary = &ids[1]
salary2 =  200 * 300 = 60000
salary3 = &ids[2]

OUTPUT/////////////

*salary = 200
salary1 = 20000
salary2 = 60000
salary3 = 0x7D8


//////////////////////////////////////////////////////////////////////////*/

//INCLUDES
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

//C- PREPROCESSOR DIRECTIVES
#define arraySize 10	
#define max 10			//maximum value array can have
#define min 1			//minimum value array can have

//PROTOTYPES
int *Largest(int *array, int size);			//returns the largest value in a given 1d array
void Swap(int *x, int *y);					//swaps the values of two integer variables
void populate1D(int *array, int size);		//fills a 1d array with random integers
void printArray1D(int *array, int size);	//prints the contents of an array

//MAIN
int main (void){
	int num1, num2;
	int *largo;
	int nums[arraySize];
	populate1D(nums,arraySize);
	printArray1D(nums,arraySize);

	num1 = 2;
	num2 = 4;

	printf("num1 = %d num2 = %d\n", num1, num2);
	Swap(&num1,&num2);
	printf("num1 = %d num2 = %d\n", num1, num2);	

	largo = Largest(nums,arraySize);
	printf("Address  = %p largest = %d\n",largo, *largo);


	return 0;
}

//FUNCTION DEFINITIONS
/* 
	Objective: Finds the address of the largest element in an array
	Input: array is assumed to be a 1d array
	Output: Returns the address of the largest element
*/
int *Largest(int *array, int size){
	int *largest;
	largest = &array[0];

	for (int i=1;i<size;i++){
		if (*largest < *(array+i)){
			largest = &array[i];
		}
	}

	return largest;
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

/* 
	Objective: Fills a given 1D array with random values
	Input: array is assumed to be a 1D array
	Output: Fills the array with values 
*/
void populate1D(int *array, int size){				
	srand(time(NULL));							
	for (int i=0;i<size;i++){			
		int r = (rand() % (max*min)); 
		*(array + i) = r; 
	}
	return;
}

/* 
	Objective: Prints the contents of a 1D array
	Input: array is assumed to be 1D
	Output: Prints all the values in the array in a nice format
*/
void printArray1D(int *array, int size){
	for (int i=0;i<size;i++){
		printf("%d ", *(array+i));
	}
	printf("\n");	

	return;
}




















