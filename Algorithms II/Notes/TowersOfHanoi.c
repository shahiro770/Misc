/* 	
	Title:
	Author(s):
	Date last modified:
	Objective:
*/

//INCLUDES
#include <stdio.h>

//C- PREPROCESSOR DIRECTIVES


//PROTOTYPES
			

//MAIN
int main (void){
	int plates[] = {}


	return 0;
}

void Hanoi(int a[], int b[], int c[], int n){  //a b and c are same size
	if(n == 1){		//Case 1: Move 1 plate from A to B
		Hanoi(a,b,c,1); 
	}
	else if(n == 2){	//Case 2: Move 2 plates from A to B aka (a,b,c,2)
		Hanoi(a,c,b,1);
		Hanoi(a,b,c,1);
		Hanoi(c,b,a,1); 
	}
	else if(n == 3){	//Case 3: Move 2 plates from A to B aka (a,b,c,2)
		Hanoi(a,c,b,2);
		Hanoi(&a[2], &b[2],c,1);
		Hanoi(c,b,a,2);

	}
	

}




//FUNCTION DEFINITIONS