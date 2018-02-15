/*
*Lab 4
*Shahir Chowdhury
*2016-10-24
*
*This program evaluates a simple expression using two numbers and an operator*/

#include <stdio.h>

int main(void){
	float num1;
	float num2;
	char op;

	printf("Enter a simple expression: ");
	scanf(" %f %c %f", &num1, &op, &num2); 

	if (op == '+'){
		printf("Output: %.2f + %.2f = %.2f", num1, num2, num1+num2);
	}
	else if(op == '-'){
		printf("Output: %.2f - %.2f = %.2f", num1, num2, num1-num2);
	}
	else if(op == '*'){
		printf("Output: %.2f * %.2f = %.2f", num1, num2, num1*num2);
	}
	else if(op == '/'){
		if(num2 == 0){ //check to see if division by zero
			printf("Output: Division by zero!");
		}
		else{
			printf("Output: %.2f / %.2f = %.2f", num1, num2, num1/num2);
		}
	}
	else{
		printf("Output: Unkown operator!");
	}
	return 0;
}
