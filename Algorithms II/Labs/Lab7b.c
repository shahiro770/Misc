/* 	
	Lab7B.c
	Shahir Chowdhury
	2017-03-14
	This program plays around with strings and structs
*/

//INCLUDES
#include <stdio.h>
#include <string.h>

//PROTOTYPES
struct myWord{
	char Word[21];
	int Length;
};

//MAIN
int main(void){
	struct myWord WordList[20];
	char *tokPtr;
	int pos = 0;	//position in myWord

	char myString[] = "the cat in the hat jumped over the lazy fox";

	tokPtr = strtok(myString, " ");

	while (tokPtr != NULL){
		strcpy(WordList[pos].Word, tokPtr);
		WordList[pos].Length = strlen(WordList[pos].Word);
		pos++;

		tokPtr = strtok(NULL, " ");
	}

	for (int i=0; i<pos;i++){
		printf("Word: %s Length: %d\n",WordList[i].Word, WordList[i].Length);
	}

	for (int i=0;i<pos-1;i++){
		for (int j=0;j<pos-i-1;j++){
			if (strcmp(WordList[j].Word, WordList[j+1].Word) > 0){
				struct myWord temp = WordList[j];
				WordList[j] = WordList[j+1];
				WordList[j+1] = temp;
			}
		}
	}

	for (int i=0; i<pos;i++){
		printf("Word: %s Length: %d\n",WordList[i].Word, WordList[i].Length);
	}
}