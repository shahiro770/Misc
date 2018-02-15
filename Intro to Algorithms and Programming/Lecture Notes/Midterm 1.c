/*
*Midterm Practice 1
*Shahir Chowdhury
*2016-10-26
*
*This program tells the user how they are doing in terms of their grades*/

/*Closed book
First question is find the errors 
Second question is solve a problem in c
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void){
	int grade;
	srand(time(NULL));

	do {
		grade = (rand() % 7)-1;
		if (grade < 0 || grade > 4){
			printf("%d: Invalid\n", grade);
		}
		else{
			if (grade<2){
				printf("%d: Fail\n", grade);
			}
			else{
				printf("%d: Pass\n", grade);
			}
		}
	} while(grade != 0);
	printf("Get help");

	return 0;
}
