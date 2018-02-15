/*
Assignment 2 Question 1
Shahir Chowdhury
2016-10-13

This programs displays the largest and smallest numbers in a set of four numbers */

#include <stdio.h>

int main(void){
	int int1, int2, int3, int4;
	int large1, small1, large2, small2;
	printf ("Enter four integers: ");
	scanf ("%d %d %d %d", &int1, &int2, &int3, &int4);
	if (int1 > int2){
		large1 = int1;
		small1 = int2;
	}
	else{
		large1 = int2;
		small1 = int1;
	}
	if (int3 > int4){
		large2 = int3;
		small2 = int4;
	}
	else{
		large2 = int4;
		small2 = int3;
	}
	if (large1 > large2){ //get the largest of the two determined larger integers
		printf("Largest: %d\n", large1);
	}
	else{
		printf("Largest: %d\n", large2);
	}
	if (small1 > small2){ //get the smallest of the two determined smaller integers
		printf("Smallest: %d", small2);
	}
	else{
		printf("Smallest: %d", small1);
	}
	return 0;
}