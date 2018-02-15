/*
*Lab 9
*Shahir Chowdhury
*2016-11-29
*
*This program computes the triangular numbers of a given number */

#include <stdio.h>
#include <stdbool.h>
int readNum(void);
int iterativeTriNum(int number);
int recursiveTriNum(int number);

int main(void){
	int num, iTriNum, rTriNum;
	
	num = readNum();
	iTriNum = iterativeTriNum(num);
	rTriNum = recursiveTriNum(num);
	printf("Iterative: Triangular number of %d is %d\n", num, iTriNum);
	printf("Recursive: Triangular number of %d is %d", num, rTriNum);

	return 0;
}

int readNum(void){
	int validNum;
	while (true){
		printf("Enter a valid number (>0): ");
		scanf("%d", &validNum);

		if (validNum > 0){
			break;
		}
		else{
			printf("Invalid number.\n");
		}
	}
	return validNum;
}

int iterativeTriNum(int number){
	int triNum = 0;
	for (int i=1; i <= number; i++){
		triNum += i;
	}

	return triNum;
}

int recursiveTriNum(int number){
	if (number == 1){
		return 1;
	}
	else{
		return (recursiveTriNum(number-1) + number);
	}	
}