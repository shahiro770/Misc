/*
*pinCode.c
*Lab 6 
*Shahir Chowdhury
*2016-11-05
*
*This programs sees if a user inputted pincode is valid for opening a pin locked door.*/

#include <stdio.h>
#include <stdbool.h>
#include <math.h>

int main (void){
	bool valid = false;
	int pinCode; 
	int posVal;
	int attempts = 1;

	while (attempts < 7){ //max number of tries user gets is 6
		printf("Enter pin code (attempt %d): ", attempts);
		scanf ("%d", &pinCode);
		if ((pinCode / 10000 >= 1) && (pinCode / 10000  <= 9)){ //checks if pincode is 5 digits long
			for (int i = 0; i < 5; i++){ //checks if each digit in the pincode is divisible by 3
				posVal = (int)(floor(pinCode/(10000/ pow(10,i)))) % 10;
				if (posVal % 3 !=0){ 
					printf("Pincode %d was invalid!\n", pinCode);
					break;
				}
				if (i == 4){
					valid = true;
					attempts = 6;
					break;
				}
			}
		}
		else{
			printf("Pincode %d was invalid!\n", pinCode);
		}
		attempts += 1;
	}
	if (valid == true){
		printf("Pincode %d was a success!\n", pinCode);
	}
	else{
		printf("Intruder alert!\n");
	}
	return 0;
}