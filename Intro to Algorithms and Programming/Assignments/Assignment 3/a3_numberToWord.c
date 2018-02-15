/*
*Assignment 3 Question 2
*Shahir Chowdhury
*2016-10-24
*
*This program writes out a two digit number in word form*/

#include <stdio.h>

int main(void){
	int num;

	printf("Enter a two-digit number: ");
	scanf("%d", &num);
	printf("You entered the number: ");

	if (num >= 11 && num <= 19){  //special cases where the number is between 11 and 19
		switch (num){
			case 11:
				printf("Eleven");
				break;
			case 12:
				printf("Twelve");
				break;
			case 13:
				printf("Thirteen");
				break;
			case 14:
				printf("Fourteen");
				break;
			case 15:
				printf("Fifteen");
				break;
			case 16:
				printf("Sixteen");
				break;
			case 17:
				printf("Seventeen");
				break;
			case 18:
				printf("Eighteen");
				break;
			case 19:
				printf("Nineteen");
				break;
		}
	}
	else{
		switch (num / 10){
			case 1:
				printf("Ten");
				break;
			case 2:
				printf("Twenty");
				break;
			case 3:
				printf("Thirty");
				break;
			case 4:
				printf("Fourty");
				break;
			case 5:
				printf("Fifty");
				break;
			case 6:
				printf("Sixty");
				break;
			case 7:
				printf("Seventy");
				break;
			case 8:
				printf("Eighty");
				break;
			case 9:
				printf("Ninety");
				break;
		}
		switch (num % 10){
			case 1:
				printf("-one");
				break;
			case 2:
				printf("-two");
				break;
			case 3:
				printf("-three");
				break;
			case 4:
				printf("-four");
				break;
			case 5:
				printf("-five");
				break;
			case 6:
				printf("-six");
				break;
			case 7:
				printf("-seven");
				break;
			case 8:
				printf("-eight");
				break;
			case 9:
				printf("-nine");
				break;
		}
	}
	return 0;	
}