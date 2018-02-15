/*
*Assignment 4 Question 2
*Shahir Chowdhury
*2016-11-17
*
*This program computes the result of two fractions with a given operation*/

#include <stdio.h>

int main(void){
	char op;
	int num1, denom1, num2, denom2, result_num, result_denom;
	
	printf ("Enter two fractions seperated by an operation sign: ");
	scanf ("%d/%d%c%d/%d", &num1, &denom1, &op, &num2, &denom2);
	if ((denom1 == 0) || (denom2 == 0)){
		printf("You can't divide by zero!");
	}
	else{
		switch (op){ //perform the calculations depending on the symbol given 
		case ('+'):
			result_num = num1 * denom2 + num2 * denom1;
			result_denom = denom1 * denom2;
			printf("The sum is %d/%d", result_num, result_denom);
			break;	
		case ('-'):
			result_num = num1 * denom2 - num2 * denom1;
			result_denom = denom1 * denom2;
			printf("The difference is %d/%d", result_num, result_denom);
			break;	
		case ('*'):
			result_num = num1 * num2;
			result_denom = denom1 * denom2;
			printf("The product is %d/%d", result_num, result_denom);
			break;		
		case ('/'):	
			result_num = num1 * denom2;
			result_denom = denom1 * num2;
			printf("The quotient is %d/%d", result_num, result_denom);
			break;
		}
	}
	return 0;
}

