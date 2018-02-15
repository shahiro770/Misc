/*
*Midterm 1 Question 2
*Shahir Chowdhury
*2016-10-27
*
*This program draws very pretty ascii art*/

#include <stdio.h>

int main(void){
	int h,w;

	printf("Please enter the width and height: ");
	scanf("%d %d", &w, &h);

	for (int i=h; i>=0; i--){
		for (int j = 0;j < i; j++){
			printf(" ");
		}
		for (int j = 0;j < w;j++){
			printf("*");
		}
		printf("\n");
	}
	return 0;
}