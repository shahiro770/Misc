/*
Lab 3 Ex 1
Shahir Chowdhury
2016-10-12

This program displays the missing check digit in an ISBN number */

#include <stdio.h>
#include <math.h>

int main (void){

	int isbn; 
	int posVal; //value of the digit at the current position of the ISBN
	int prodSum = 0; //sum of all the digits multiplied by their position in the ISBN
	int checkDigit;

	printf ("Enter the first nine digits of the ISBN: ");
	scanf ("%d", &isbn);
	for (int i=0;i<9;i++){
		posVal = (int)(floor(isbn/(100000000/pow(10,i)))) % 10; //obtain the value of the digit at the current position
																//by dividing the ISBN number sequence so that the desired
		prodSum += posVal * (10-i);								//digit is the result of modding the quotient by 10
	}
	checkDigit = 11 - (prodSum % 11);

	printf ("The check digit is: %d", checkDigit);
	return 0;
}