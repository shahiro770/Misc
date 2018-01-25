/* 	
	Lab2.c
	Shahir Chowdhury
	2017-01-24

	This program provides a set of recursive functions for the user to play around with
*/

// INCLUDES
#include <stdio.h>

//C-PREPROCESSOR DIRECTIVES
int summation (int n); 		//computes the sum of numbers from 1 to n
int factorial (int n); 		//computes n factorial
int fib (int n); 			//finds the nth fib 
int gcd (int x, int y); 	//finds the greatest common divisor of x and y
int power (int a, int b); 	//computes a raised to the power b
void printmenu(); 			//prints the input menu

//MAIN
int main (void){
	int num = 1;
	while (num != 0){
		printmenu();
		printf("Please select a function by typing the number written beside it (0 to exit): ");
		scanf("%d", &num);
		if (num == 0){
			break;
		}
		else{
			int n,x,y,a,b; //inputted values
			int result; //the result of the desired functions
			switch(num){
				case 1:
					printf("Input your n value: ");
					scanf("%d",&n);
					result = summation(n);
					if (result != -1){
						printf("The sum of numbers from 1 to n is %d\n", result);
					}
					break;
				case 2:
					printf("Input your n value: ");
					scanf("%d",&n);
					result = factorial(n);
					if (result != -1){
						printf("%d factorial is equal to %d\n",n,result);
					}
					break;
				case 3:
					printf("Input your n value: ");
					scanf("%d",&n);
					result = fib(n);
					if (result != -1){
						printf("The fibonacci term value is %d\n",result);
					}
					break;
				case 4:	
					printf("Input your x and y values(input form x y): ");
					scanf("%d %d",&x, &y);
					result = gcd(x,y);
					if (result != -1){
						printf("The greatest common divisor is %d\n",result);
					}
					break;
				case 5:
					printf("Input your a and b values(input form a b): ");
					scanf("%d %d",&a, &b);
					result = power(a,b);
					if (result != -1){
						printf("The resulting number is %d\n",result);
					}
					break;
			}
		}

	}
	return 0;
}
//FUNCTIONS
/* 
	Objective: computes the sum of numbers from 1 to an inputted value n 
	Input: n must be greater than or equal to 1
	Output: returns the sum from 1 to n
*/
int summation (int n){
	if (n < 1){
		printf("Invalid input: Input for this function must be >= 1\n");
		return -1;
	}
	else{
		if (n == 1){ //base case 
			return 1;
		}
		else{
			return summation(n-1) + n;
		}
	}
}

/* 
	Objective: computes the factorial for a given input n
	Input: n must be greater than or equal to 0
	Output: returns the product of numbers from 1 to n
*/
int factorial (int n){
	if (n < 0){
		printf("Invalid input: Input for this function must be greater than 0\n");
		return -1;
	}
	else{
		if (n == 0){ //base case
			return 1;
		}
		else{
			return factorial(n-1) * n;
		}
	}
}

/* 
	Objective: computes the nth fibonacci term
	Input: n must be greater than or equal to 0
	Output: returns the nth fibonacci term
*/
int fib (int n){
	if (n < 0){
		printf("Invalid input: Input for this function must be greater than 0\n");
		return -1;
	}
	else{
		if (n == 0){ //base case - 0th fibonacci term 
			return 0;
		}
		else if (n == 1){ //base case - first fibonacci term
			return 1;
		}
		else{
			return fib(n-1) + fib(n-2);
		}
	}
}

/* 
	Objective: computes the greatest common divisor between two inputted numbers
	Input: x and y must both be greater than or equal to 0
	Output: returns the greatest common divisor between two numbers
*/
int gcd (int x, int y){
	if (y > x){ //reorder the input to avoid dividing the smaller number by the larger
		int temp = x; 
		x = y;
		y = temp;
	}
	if ((x < 0) || (y < 0)){
		printf("Invalid input: Inputted values for this function must be greater than 0\n");
		return -1;
	}
	else{
		if (x % y == 0){ //base case
			return y;
		}
		else{
			if (x > y){
				return gcd(y,x % y);
			}
			else{
				return gcd(x,y % x);
			}
		}
	}
}

/* 
	Objective: computes the value of 'a' raised to the power 'b'
	Input: a must be greater than 0, b must be greater than or equal to 0
	Output: returns the value of a^b
*/
int power (int a, int b){
	if ((a <= 0) && (b < 0)){
		printf("Invalid inputs:  a must be greater than 0 and b must be greater than or equal to 0\n");
		return -1;
	}
	else if (a <= 0){
		printf("Invalid input: a must be greater than 0\n");
		return -1;
	}
	else if (b < 0){
		printf("Invalid input: b must be greater than or equal to 0\n");
		return -1;
	}
	else{
		if (b == 1){ //base case
			return a; 
		}
		else if (b == 0){
			return 1;
		}
		else{
			return a * power(a,b-1);
		}		
	}
	return 0;
}

/* 
	Objective: Displays a nice looking menu for the user to understand what the program can do
	Input: None
	Output: Prints the functions that may be called
*/
void printmenu(){
	printf("\n");
	printf("---------SELECT A FUNCTION---------\n");
	printf("\n");
	printf("(1) Summation(n)\tn >= 1\n");
	printf("(2) Factorial(n)\tn >= 0\n");
	printf("(3) Fibonacci(n)\tn >= 0\n");
	printf("(4) gcd(x,y)\t\tx,y >= 0 \n");
	printf("(5) Power(a,b)\t\ta > 0, b >= 0\n");
	printf("\n");
	printf("---------SELECT A FUNCTION---------\n");
	printf("\n");

	return;
}