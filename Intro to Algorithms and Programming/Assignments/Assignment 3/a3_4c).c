/*
*Assignment 3
*Shahir Chowdhury
*2016-10-24
*
*This program prints every number between 0 and 11 (exclusive)*/

#include <stdio.h>

int main(void){
	int k = 1;
	do{
		printf("%d ", k);
		k += 1;
	}while(k <= 10);

	return 0;
}