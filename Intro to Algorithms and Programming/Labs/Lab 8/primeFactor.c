/*
*Lab 8
*Shahir Chowdhury
*2016-11-22
*
*This program tells the user all the prime factors of a given number*/

#include <stdio.h>
#include <stdbool.h>
int readNum(void);
bool isPrime(int num);
int findPrimeCount (int num, int prime);

int main(void){
	int currPrime = 2;
	
	int num;

	num = readNum();
	printf("%d = 1", num);
	while (num != 1){ 
		if (num % currPrime == 0){ //onyl get prime frequency if num is divisible by the current prime number
			num = findPrimeCount(num, currPrime);
		}
		else{ //find the next prime number 
			currPrime += 1; 
			while (isPrime(currPrime) == false){
				currPrime += 1;
			}
		}
	}
}

/* This function reads inputted numbers from the user until it recieves a valid number*/
int readNum(void){
	int validPrime;

	while (true){
		printf("Enter a valid number (>1): ");
		scanf("%d", &validPrime);

		if (validPrime > 1){
			break;
		}
		else{
			printf("Invalid number.\n");
		}
	}
	return validPrime;
}

/*This functions tests if a number is a prime number */
bool isPrime(int num){
	 bool p_flag = true;

	 for (int i = 2; i <= num; i++){
	 		if (num % i == 0 && i != num){ //prime numbers are only divisibly 1 and themselves
	 			p_flag = false;
	 		}
	 }

	 return p_flag;
}

/*This function finds the number of times a given prime number occurs within a number, factoring
out the given prime number after each occurence */
int findPrimeCount(int num, int prime){
	int primeFreq = 0;

	while (num % prime == 0){
			primeFreq += 1;
			num = num / prime;
	}

	if (primeFreq > 0){
		printf(" x (%d^%d)", prime, primeFreq);
	}

	return num;
}
