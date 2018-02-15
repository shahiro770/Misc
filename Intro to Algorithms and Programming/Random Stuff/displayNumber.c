/*
*Chapter 10 Programming Project 7
*Shahir Chowdhury
*2016-12-15
*
*This program takes in a number and draws it in ASCII art */

#include <stdio.h>

#define MAX_DIGITS 10

//void clear_digits(int digits[][MAX_DIGITS*4]);
void process_digit(int digit, int position, int segments[][7], int digits[][MAX_DIGITS*4]);
void print_digits_array(int digits[][MAX_DIGITS*4]);

int main (void){
	int num[MAX_DIGITS] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	char temp;

	int pos = 0; //position in num array
	int segments[10][7] = {{1,1,0,1,1,1,1},{0,0,0,1,0,0,1},{1,0,1,1,1,1,0},{1,0,1,1,0,1,1},{0,1,1,1,0,0,1},{1,1,1,0,0,1,1},
	{1,1,1,0,1,1,1},{1,0,0,1,0,0,1},{1,1,1,1,1,1,1},{1,1,1,1,0,0,1}};
	int digits[3][MAX_DIGITS * 4] = {0};

	printf("Enter a number: "); 
	do{ //ignore non-numeric values in input
		if (pos < MAX_DIGITS){
			scanf("%c", &temp);
			switch (temp){
				case '0':
					num[pos] = 0;
					pos += 1;
					break;
				case '1':
					num[pos] = 1;
					pos += 1;
					break;
				case '2':
					num[pos] = 2;
					pos += 1;
					break;
				case '3':
					num[pos] = 3;
					pos += 1;
					break;
				case '4':
					num[pos] = 4;
					pos += 1;
					break;
				case '5':
					num[pos] = 5;
					pos += 1;
					break;
				case '6':
					num[pos] = 6;
					pos += 1;
					break;
				case '7':
					num[pos] = 7;
					pos += 1;
					break;
				case '8':
					num[pos] = 8;
					pos += 1;
					break;
				case '9':
					num[pos] = 9;
					pos += 1;
					break;
				default: 
					break; 
			}
		}
		else{
			break;
		}
	}while(temp != '\n');

	//clear_digits(digits);

	for (int i = 0; i < MAX_DIGITS; i++){
		if (num[i] != -1){
			process_digit(num[i], i, segments, digits);
		}
	}
	print_digits_array(digits);

	return 0;
}

/*This function prepares a clean display for the numbers to be drawn on to */
/*void clear_digits(int digits[][MAX_DIGITS*4]){
	for (int i = 0; i < 3; i++){ //number of rows
		for (int j = 0; j < MAX_DIGITS*4; j++){ //number of columns
			digits[i][j] = 0;
		}
	}
} */

void process_digit(int digit, int position, int segments[][7], int digits[][MAX_DIGITS*4]){
	for (int i = 0; i < 7; i++){
		if (segments[digit][i] == 1){
			if (i == 0){
				digits[0][1 +(position*4)] = 1;
			}
			if ((i > 0) && (i < 4)){
				digits[1][((i+2)%3) + position*4 ] = 1;
			}
			if ((i > 3) && (i < 7)){
				digits[2][((i+2)%3) + position*4 ] = 1;
			}
		}
	}
}

void print_digits_array(int digits[][MAX_DIGITS*4]){
	for (int i=0 ;i <3; i++){
		for (int j=0; j< MAX_DIGITS*4; j++){
			if (digits[i][j] == 1){
				if (i==0){
					printf("_");
				}
				else if (i==1){
					if (j % 2 == 0){
						printf("|");
					}
					else{
						printf("_");
					}
				}
				else if (i==2){
					if (j % 2 == 0){
						printf("|");
					}
					else{
						printf("_");
					}
				}
			}
			else{
				printf(" ");
			}
		}
		printf("\n");
	}
}