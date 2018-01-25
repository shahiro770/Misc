/* 	
	test.c
	Author(s):
	Date last modified:
	Objective:
*/

//INCLUDES
#include <stdio.h>
#include <string.h>

//C- PREPROCESSOR DIRECTIVES
union TagName {
        int  N ;          // First declared field
        float  F ;        //  Second declared field
        double  D ;    //   Third declared field
    }aU;
   // aU.N = 5 ;    // Must initialize using its first declared field type


//PROTOTYPES
void swap (int *x, int *y);
void printlist(int *i, int len);
void binSearch(int *x, int len, int target);

//MAIN
int main (void){
	union TagName aU;    // Must initialize using its first declared field type

	int k = 0;
	const int j = 0;
	int * kptr = &k;
	const int * jptr = &j;
	int * const jptr2 = &k;
	(*jptr2)++;

	printf("%d %d %d %d %d\n", k, *kptr, j, *jptr, *jptr2);
	char *potato = "o";
	char *tomato = "looo";

	int x[8] = {10,9,8,6,3,2,11,-1};

	printlist(x,8);
	for (int i=0; i< 8 - 1;i++){
		int *largest = x + i;
		for (int j= i+1;j < 8;j++){
			if (*(x+j) > *largest){
				largest = x + j;
			}
		}
		swap(largest, x+i);
	}
	printlist(x,8);

	for (int i=0;i < 8-1;i++){
		for (int j=0;j < 8-i-1;j++){
			if (*(x+j) > *(x+j+1)){
				swap(x+j, x+j+1);
			}
		}
	}
	printlist(x, 8);
	binSearch(x, 8, 11);

	printf("%lu %lu\n", strspn(potato, tomato), strcspn(potato, tomato));


	return 0;
}

void swap(int *x, int *y){
	int temp = *y;
	*y = *x;
	*x = temp;

	return;
}

void printlist(int *x, int len){
	for (int i=0;i < len; i++){
		printf("%d ", *x);
		x++;
	}
	printf("\n");
	return;
}

void binSearch(int *x, int len, int target){
	int low = 0;
	int high = len-1;
	int mid = (low + high) / 2;

	while (low < high){
		int curr = x[mid];
		if (curr == target){
			printf("Found\n");
			return;
			
		}
		else{
			low = mid + 1;
			mid = (low + high) / 2;
			printf("%d\n", mid);
		}
	}

	printf("Not found\n");
	return;
}

//FUNCTION DEFINITIONS