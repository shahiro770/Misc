/* 	
	Lab3.c
	Shahir Chowdhury
	2017-01-31
	This program constucts a 2D array and allows the user to play around with it using some preset functions
*/

//INCLUDES
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

//C- PREPROCESSOR DIRECTIVES
#define true 1
#define false 0
#define M 6
#define N 5

//PROTOTYPES
void ClearArray2D(int A[][N]);			//clears the 2D array
void PrintArray2D(int A[][N]);			//prints the contents of the 2D array
void PopulateRandom2D(int A[][N]);		//fills the array with pseudo-randomly generated numbers
_Bool LinearSearch2D(int A[][N],int n);	//tells the user if a given value is in the array
void LeftShift2D(int A[][N]);			//shifts all values in the array by one to the left
void printmenu();						//prints the input menu

//MAIN
int main(void){
	int A2D[M][N] = {{0}};
	int num = -1;
	while (num != 0){
		printmenu();
		printf("Please select a function by typing the number written beside it (0 to exit): ");
		scanf("%d", &num);
		if (num == 0){
			break;
		}
		else{
			int n; 						//inputted values
			switch(num){
				case 1:
					PrintArray2D(A2D);
					break;
				case 2:
					PopulateRandom2D(A2D);
					break;
				case 3:
					printf("Input the value to search the array for: ");
					scanf("%d",&n);
					if((n < 1) || (n > M*N)){
						printf("Invalid input: n must be greater than or equal to 1 and less than or equal to %d\n", M*N);
						break;
					}
					if (LinearSearch2D(A2D,n)){
						printf("%d is contained in the array\n", n);
					}
					else{
						printf("%d is not contained in the array\n", n);
					}
					break;
				case 4:	
					LeftShift2D(A2D);
					break;
			}
		}
	}
	return 0;
}

//FUNCTION DEFINITIONS
/* 
	Objective: Clears a given 2D array
	Input: A is assumed to be a 2D array
	Output: Clears the 2D array
*/
void ClearArray2D(int A[][N]){
	for (int i=0;i<M;i++){
		for(int j=0;j<N;j++){
			A[i][j] = 0;
		}
	}

	return;
}

/* 
	Objective: Displays the 2D array in a nice tabular format
	Input: A is assumed to be a 2D array
	Output: Prints the contents of the 2D array
*/
void PrintArray2D(int A[][N]){
	if (A[0][0] == 0) { 						//array is empty
		printf("The array is empty!\n");
		return;
	}

	int minwidth = (int)(floor(log10(M*N))) + 1; //formatting 

	for (int i=0;i<M;i++){
		for(int j=0;j<N;j++){
			printf("%*d ",minwidth,A[i][j]);
		}
		printf("\n");
	}

	return;
}

/* 
	Objective: Fills a given 2D array with random values ranging from 1 to M*N
	Input: A is assumed to be a 2D array
	Output: Fills the array with values from 1 to M*N, with no duplicates in random order
*/
void PopulateRandom2D(int A[][N]){
	int possiblenums[M*N] = {0}; 				//create an array of all possible numbers
	for (int i=0;i<(M*N);i++){
		possiblenums[i] = i+1;
	}

	srand(time(NULL));		
	ClearArray2D(A);							//clear the array in order to fill it 
	for (int i=0;i<M;i++){
		for(int j=0;j<N;j++){
			while (A[i][j] == 0){				
				int r = (rand() % (M*N)); 
				A[i][j] = possiblenums[r];
				possiblenums[r] = 0;			//do not allow the same number to be picked twice
			}
		}
	}
	return;

}

/* 
	Objective: Tells the user if a given value is contained within the array
	Input: n is assumed to be from 1 to M*N
	Output: Returns true if the value is contained, false otherwise
*/
_Bool LinearSearch2D(int A[][N],int n){
	if (A[0][0] == 0) { 						//array is empty
		printf("The array is empty!\n");
		return false;
	}

	for (int i=0;i<M;i++){
		for(int j=0;j<N;j++){
			if (A[i][j] == n){
				return true;
			}
		}
	}
	return false;
}

/* 
	Objective: Displays a nice looking menu for the user to understand what the program can do
	Input: None
	Output: Prints the functions that may be called
*/
void LeftShift2D(int A[][N]){
	int temp = A[0][0];							//store the first value temporarily 

	if (A[0][0] == 0) { 						//array is empty
		printf("The array is empty!\n");
		return;
	}
	
	for (int i=0;i<M;i++){
		for(int j=0;j<N;j++){
			if ((i == M-1) && (j == N-1)){		//case to set the value in the last column and row to the value in the first column and row
				A[i][j] = temp;
			}
			else if (j == N-1){					//case to move value from start of a row to the end of the preceding row
				A[i][j] = A[i+1][0];
			}
			else{								
				A[i][j] = A[i][j+1];
			}
		}
	}
	return;
}

/* 
	Objective: Displays a nice looking menu for the user to understand what the program can do
	Input: None
	Output: Prints the functions that may be called
*/
void printmenu(){
	printf("\n");
	printf("----------SELECT A FUNCTION----------\n");
	printf("\n");
	printf("(1) PrintArray2D()\n");
	printf("(2) PopulateRandom2D()\n");
	printf("(3) LinearSearch2D(n)\t(1 <= n <= %d)\n", M*N);
	printf("(4) LeftShift2D()\n");
	printf("\n");
	printf("----------SELECT A FUNCTION----------\n");
	printf("\n");

	return;
}