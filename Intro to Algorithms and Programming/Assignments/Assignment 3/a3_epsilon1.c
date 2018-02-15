/*
*Assignment 3 Question 6
*Shahir Chowdhury
*2016-10-24
*
*This program approximates the value of epsilon based on a small float the user inputs*/

#include <stdio.h>
#include <math.h>

int main(void){
	float epsilon;
	float eps = 0.0f;
	float term = 1.0f;
	float i = 1.00;
	float fact = 1.00;

	printf("Enter a small float value for epsilon: ");
	scanf("%f", &epsilon); //smallest value 1/n! can be
	while(term > epsilon){ //loop until 1/n! equals epsilon
		eps += term; 
		i += 1;
		fact *= i;
		term = i/fact;
	}
	printf("Approximation of e: %f", eps);
	return 0;
}