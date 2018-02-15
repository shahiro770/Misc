/*
*Assignment 3 Question 5
*Shahir Chowdhury
*2016-10-24
*
*This program approximates the value of epsilon based on an integer the user inputs */

#include <stdio.h>
#include <math.h>
unsigned long factorial(int);

int main(void){
	int n;
	float e = 1;

	printf("Enter a positive integer between 0 and 33: ");
	scanf("%d", &n);

	for (int i = 1; i <= n; i++){
		e += 1.00/factorial(i);
	}
	printf("The approximate value of epsilon is %f", e);
	return 0;
}

unsigned long factorial(int n){ //method for calculating n factorial
	int prod = 1;
	for (int i = 1; i <= n; i++){  
		prod *= i;
	}

	return prod;
}