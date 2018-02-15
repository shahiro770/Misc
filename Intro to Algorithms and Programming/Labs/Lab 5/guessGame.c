/*
*Lab 5
*Shahir Chowdhury
*2016-10-31
*
*This program plays a number guessing game with the user*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void){
	int guess;
	srand( time(NULL) );
	int goal = rand() % 50 + 1;

	for (int i = 0;i < 10; i++){
		printf("Enter your guess (between 1 and 50): ");
		scanf("%d", &guess);

		if (guess == goal){
			break;
		}
		else{
			if (guess > goal){
				printf("Too high ...\n");
			}
			else if (guess < goal){
				printf("Too low ...\n");
			}
		}
		if (i == 9){
			printf("Sorry, the number was %d", goal);
		}
	}

	if (guess == goal){
		printf("Correct, the number was %d", goal);
	}



}