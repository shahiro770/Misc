/*
*Assignment 4 Question 6
*Shahir Chowdhury
*2016-11-17
*
*This program checks user numbers inputted by the user for repeated digits 
*Inputting a number less than or equal to 0 ends the program*/

#include <stdio.h>
#include <stdbool.h>

int main (void){
	bool digit_seen[10] = {false};
	int digit;
	long n;

	while(true){
		printf("Enter a number: ");
		scanf("%ld", &n);
		if (n <= 0){ //program ends if user inputs a number less than or equal to 0
			break;
		}

		while (n > 0){ //check each digit for repeats
			digit = n % 10;
			if (digit_seen[digit]){
				break;
			}
			digit_seen[digit] = true;
			n /= 10;
		}

		if (n > 0){ 
			printf("Repeated digit\n");
		}
		else{
			printf("No repeated digit\n");
		}

		n = 1;	//reset variables in preparation for next possible number
		for (int i=0; i<10; i++){
			digit_seen[i] = false;
		}
	}
	return 0;
}