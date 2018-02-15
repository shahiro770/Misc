/*Lab 7
*Shahir Chowdhury
*2016-11-14
*
*This programs simulates the GPAs of a given class size and finds the frequency of each GPA*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main (void){
	int studNum;
	int currGPA = -1;
	int GPAFreq[] = {0,0,0,0};
	srand( time(NULL) );

	printf("Enter the number of students: ");
	scanf("%d", &studNum);

	for (int i=1; i <= studNum; i++){
		currGPA = rand() % 6; //generate random GPAs to simulate input
		if ((currGPA > 0) && (currGPA < 5)){
			printf("GPA of student #%d is: \t%d\n", i, currGPA);
			GPAFreq[currGPA - 1] += 1;	
		}
		else{
			printf("Invalid number!\n");	
			i--;		
		}
	}
	printf("The total number of students is %d\n", studNum);
	for (int i=0; i<4; i++){
		printf("GPA %d --- %d student(s)\n", i+1, GPAFreq[i]);
	}
}