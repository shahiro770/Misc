/*
Lab 2 Ex 1
Shahir Chowdhury
2016-10-04 

This program displays an integer and a float with specific formating specifications and takes in a given time value */

#include <stdio.h>

int main (void){
	int x = 40000;
	float y = 1.234;
	int hrs,min,sec;

	printf ("%-15.8d\n", x);
	printf ("%09.3f\n", y);
	scanf ("%d:%d:%d", &hrs, &min, &sec);
	
	return 0;
}
