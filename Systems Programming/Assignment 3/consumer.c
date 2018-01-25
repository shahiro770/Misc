/* 	
	consumer.c
	Shahir Chowdhury
	2017-11-01
	This program eats 3 units from the storage and then begs the producer for more, who will hopefully provide for it with the
	wealth it has accumulated by working hard under a capitalist system. The number in storage is assumed to be between 5 and 90.
*/

//INCLUDES
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

//C- PREPROCESSOR DIRECTIVES
#define NUMLENGTH 3

//MAIN
int main(void){
	char execErr[] = "Exec error!";
	char fromCon[] = "from consumer: current total is ";
	char temp[1];						//temporary storage for reading each character from storage
	char inNum[NUMLENGTH + 1];			//+1 for the delimiter character inserted by string concatentation
	unsigned char outNum[NUMLENGTH + 1];//outputted number
	memset(inNum, 0, sizeof(inNum));
	memset(outNum, 0 ,sizeof(outNum));
	int num;
	int fd;
	int fd2;

	//store the number in storage
	fd = open("storage", O_RDONLY);
	for (int i = 0;i < NUMLENGTH;i++){
		if (read(fd, temp, 1) > 0){
			if (strcmp(temp, "\n") == 0 || strcmp(temp, "\0") == 0){	//ignore newlines and delimiters
				break;
			}
			strcat(inNum, temp);
		}
	}

	//substract 3 from storage
	num = atoi(inNum);
	num -= 3;

	//extract every digit in the number and append it to a string (this will give us the number in reverse)
	for (int i = 0;i < NUMLENGTH;i++){	
		int digit = num % 10;
		switch (digit){
			case 0:
				outNum[i] = (unsigned char)48;
				break;
			case 1:
				outNum[i] = (unsigned char)49;
				break;
			case 2:
				outNum[i] = (unsigned char)50;
				break;
			case 3:
				outNum[i] = (unsigned char)51;
				break;
			case 4:
				outNum[i] = (unsigned char)52;
				break;
			case 5:
				outNum[i] = (unsigned char)53;
				break;
			case 6:
				outNum[i] = (unsigned char)54;
				break;
			case 7:
				outNum[i] = (unsigned char)55;
				break;
			case 8:
				outNum[i] = (unsigned char)56;
				break;
			case 9:
				outNum[i] = (unsigned char)57;
				break;
		}
		num /= 10;
		if (num <= 0){
			break;
		}
	}
	
	//write the new number to terminal
	write(STDOUT_FILENO, fromCon, sizeof(fromCon));
	for (int i = 0; i < NUMLENGTH;i++){
		write(STDOUT_FILENO, &outNum[NUMLENGTH - i - 1], 1);	//number is read in reverse
	}
	close(fd);

	//write the new number to storage
	fd2 = open("storage", O_CREAT | O_WRONLY | O_TRUNC, 0433);
	for (int i = 0; i < NUMLENGTH;i++){
		if (outNum[NUMLENGTH - i - 1] == 0){ //c is a weird language (empty positions in the array will result in the characters being written in binary)
			i+= 1;
		}
		if (outNum[NUMLENGTH - i - 1] == 0){ //second check in case number is less than 10 (still acts weird if number goes below 0)
			i+= 1;
		}
		write(fd2, &outNum[NUMLENGTH - i - 1], 1);	
	}
	close(fd2);
	execl("./producer", "producer", (char *)0);
	write(STDOUT_FILENO, execErr, sizeof(execErr));

	return 0;
}

