/*
*Assignment 5
*Shahir Chowdhury
*2016-11-29
*
*This program computes the greatest common divisor of two random numbers */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int gcd (int m , int n);

int main(void){
	int m,n,cd;
	srand(time(NULL));

	m = rand() % 98 + 2; //random number between 100 and 2
	n = rand() % 98 + 2;

	if ( m > n){
		cd = gcd(m,n);
	}
	else{
		cd = gcd(n,m);
	}
	
	printf("The GCD of %d and %d is %d.", m, n, cd);
	return 0;
}

int gcd(int m , int n){ //function that continues to divide a number until the largest common divisor is reached
	if (m % n == 0){
		return n;
	}
	else{
		if (m > n){
			return gcd(n,m % n);
		}
		else{
			return gcd(m,n % m);
		}
	}
}