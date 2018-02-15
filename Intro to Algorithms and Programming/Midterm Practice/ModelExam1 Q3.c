/*
*Mdterm 1 Question 3
*Shahir Chowdhury
*2016-10-27
*
*This program makes change using a greedy solution to find the minimum number of coins necessary*/

#include <stdio.h>

int main(void){
	int change;
	int quarters, dimes, nickels, pennies;
	int currCoin;

	printf("Please enter the amount for change in the range from 0 to 99 cents: ");
	scanf("%d", &change);


	do{
		if (change >= 25){
			currCoin = 25;
		}
		else if (change >= 10){
			currCoin = 10;
		}
		else if (change >= 5){
			currCoin = 5;
		}
		else if (change >= 1){
			currCoin = 1;
		
}		switch(currCoin){
			case 25:
				change -= 25;
				quarters += 1;
				break;
			
			case 10:
				change -= 10;
				dimes += 1;
				break;
			
			case 5:
				change -= 5;
				nickels += 1;
				break;
			
			case 1:
				change -= 1;
				pennies += 1;
				break;
		}
	} while(change > 0);

	printf("The change is ");

	for (int i = 0; i < 4; i++){
		if ((i == 0) && (quarters > 0)){
			printf("%d quarter(s), ", quarters);
		}
		if ((i == 1)  && (dimes > 0)){
			printf("%d dime(s), ", dimes);
		}
		if ((i == 2)  && (nickels > 0)){
			printf("%d nickel(s), ", nickels);
		}
		if ((i == 3) && (pennies > 0)){
			printf("%d penny/pennies.", pennies);
		}
	}
	return 0;
}