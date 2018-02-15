/*
Assignment 2 Question 1
Shahir Chowdhury
2016-10-13

This programs adds two fractions and prints the sum 
*/

#include <stdio.h>

char *lcd(int num, int denom);

int main(void){
	int num1, denom1, num2, denom2, result_num, result_denom;
	
	printf ("Enter two fractions seperated by a plus sign: ");
	scanf ("%d/%d+%d/%d", &num1, &denom1, &num2, &denom2);
	
	result_num = num1 * denom2 + num2 * denom1;
	result_denom = denom1 * denom2;

	printf("%s\n", lcd(result_num, result_denom));
	//printf("The sum is %d/%d", result_num, result_denom);
}

char *lcd(int num, int denom){
	static char result [120]= "";
	int divisor = denom;

	while (divisor != 1){
		if ((num % divisor == 0) && (denom % divisor == 0)){
			num /= divisor;
			denom /= divisor;
		}
		divisor -= 1;
	}
	sprintf(result, "The sum is %d/%d", num, denom); 

	return result;
}