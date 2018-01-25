/* 	
	assign1.c
	Shahir Chowdhury
	Last Update: 2017-01-28
	
	This program writes out the factorial of a given number (assumed to be from 2 to 100) in terms of its prime factors
*/
 
//INCLUDES
#include <stdio.h>

//C-PREPROCESSOR DIRECTIVES
#define true 1
#define false 0
#define largestPrime 97 //Largest prime within the series of 1 to 100 is 97

//PROTOTYPES
_Bool is_prime(int num); 						//checks if a number is prime
int find_next_prime(int lastPrime); 			//finds the next prime number
int find_prime_count(int num, int prime); 		//counts the number of times a prime factor occurs in a number

//MAIN
int main(void){
	int currPrime = 2; 							//current prime number being looked at
	int primeCount[] = {[largestPrime] = 0}; 	//list of prime numbers
	int num; 									//inputted number
	int newlineCount = 0; 						//counter for the number of terms to be printed before moving to a new line 

	while (true){  
		scanf("%d", &num);
		if (num == 0){ 							//stop getting input if number is 0
			break;
		}
		else if ((num >= 2) && (num <= 100)){	
			for (int i=2;i<=num;i++){
				currPrime = 2; 					//reset currentPrime for the next number 
				while (currPrime <= i){
					primeCount[currPrime] += find_prime_count(i,currPrime);
					currPrime = find_next_prime(currPrime);
				}
			}

			printf("%3d! =  ",num); 			//print results
			for (int i=0;i<=largestPrime;i++){
				if (primeCount[i] != 0){

					if (newlineCount == 0){		
						printf("(%d^%d)",i,primeCount[i]);
					}
					else if (i != 0){
						printf("*(%d^%d)",i,primeCount[i]);
					}
					newlineCount++;
					if (newlineCount % 9 == 0){	//newline after the 9th term
						printf("\n       ");
					}

					primeCount[i] = 0; 			//reset array for next inputted factorial 
				}
			}
			printf("\n\n");
			newlineCount = 0;
		}
		else{ 									//invalid input
			printf("Invalid number.\n");
		}
	}
	return 0;
}


//FUNCTIONS
/* 
	Objective: determines if a number is prime
	Input: num can be any number
	Output: return true if the number is prime, false otherwise
*/
_Bool is_prime(int num){
	_Bool p_flag = true;

	 for (int i = 2; i <= num; i++){
	 		if (num % i == 0 && i != num){ //prime numbers are only divisibly 1 and themselves
	 			p_flag = false;
	 		}
	 }
	 return p_flag;
}

/* 
	Objective: finds the next prime number in increasing value
	Input: lastPrime is assumed to be a prime number
	Output: return the next prime number
*/
int find_next_prime(int lastPrime){
	int nextPrime = lastPrime;
	while (true){
		nextPrime++;
		if (is_prime(nextPrime)){
			return nextPrime;
		}
	}	
}

/* 
	Objective: finds how many times a prime number can be factored out of a number
	Input: num and prime are assumed to be from 2 to 100
	Output: return the number of times the prime can be factored out
*/
int find_prime_count(int num, int prime){
	int primeFreq = 0;

	while (num % prime == 0){
		primeFreq++;
		num = num / prime;
	}
	return primeFreq;
}




