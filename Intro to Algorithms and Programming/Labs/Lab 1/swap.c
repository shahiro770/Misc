/*
Lab 1 Ex 4
Shahir Chowdhury
2016-10-12

This program swaps the values of two integers */

#include <stdio.h>
int main (void){
	int i,j,k;
	printf("Enter the first number:\n");
	scanf("%d", &i);
	printf("Enter the second number:\n");
	scanf("%d", &j);
	printf("Before swapping, the first number was %d and the second number was %d.\n", i,j);
	k=i;
	i=j;
	j=k;
	printf("After swapping, the first number was %d and the second number was %d.\n", i,j);
	return 0;
}