/*
Lab 2 Ex 2
Shahir Chowdhury
2016-10-04 

This program formats product information into an aesthetically pleasing display */

#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main (void){
	int itemNum;
	int year,month,day;
	int unitPriceLen;
	double unitPrice;
	char str[10];

	//inputs
	printf("Enter the item number:\n");
	scanf("%d", &itemNum);
	while (true){ 	//only take in unit prices that are $9999.99 or less 
		printf("Enter the unit price:\n");
		scanf("%lf", &unitPrice);
		if (unitPrice < 10000 ){
			break;
		}
		else{
			printf("No one can afford that! Lower the cost!\n");
		}
	}
	printf("Enter the purchase date (mm/dd/yyyy):\n");
	scanf("%d/%d/%d", &month, &day, &year);

	//display 
	printf("Item\t\tUnit\t\tPurchase\n"); 
	printf("\t\tPrice\t\tDate\n");
	printf("----\t\t------\t\t--------------\n");
	printf("%-8d\t$%-7.2f\t%d/%d/%d",itemNum,unitPrice,month,day,year);
	
	return 0;
}