/*
*Assignment 3
*Shahir Chowdhury
*2016-10-24
*
*This program continuously square roots a number */

#include <stdio.h>
#include <math.h>

int main(void){
	float x = 10.0f;
	while(x > 1.001){	
		printf("%.4f ",x);
		x = sqrt(x);
	} 
	return 0;
}