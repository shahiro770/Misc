/*
Assignment 2 Question 3
Shahir Chowdhury
2016-10-13

This programs reads an integer and displays it in octal */

#include <stdio.h>

int main(void){
	int num;
	int pos=1;
	int octalNum;

	printf("%d",octalNum);

	printf ("Enter a number between 0 and 32767: ");
	scanf ("%d", &num);
	while (num % 8 != num){ //keep dividing until number cannot be reduced below 8
		octalNum += (num % 8) *pos;
		pos *= 10; //move position in octal to the next digit
		num = (int)(num/8);
	}
	octalNum += num * pos;

	printf ("In octal, your number is: %05d", octalNum);
}