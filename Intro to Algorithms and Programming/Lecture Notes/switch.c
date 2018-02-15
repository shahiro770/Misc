/*
*grade.c
*Shahir Chowdhury
*2016-10-19 */

#include <stdio.h>

int main(void){
	int grade;
	printf("Enter your grade: ");
	scanf("%d", &grade);

	switch (grade) {
	case 4:
		printf("Excellent");
		break; //not having the break will result in the next case being read 
	case 3:
		printf("Good");
		break;
	case 2:
		printf("Average");
		break;
	case 1: case 0:
		printf("Failing");
		break;
	default:
		printf("Illegal grade");
		break;
	}
}
