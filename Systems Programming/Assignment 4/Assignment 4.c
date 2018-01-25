/*
	Assignment 4.c
	Shahir Chowdhury 104598120
	2017-11-14
	This program creates a binary ppm files using a varied number of child processes. The children all calculate their inputs beforehand,
	with the parent using signals to tell each child when to write.
	This input for the image constructed is of the form: imageName, centerColour, topLeftColour, topRightColour, bottomLeftColour,
	bottomRightColour, numberOfRows, numberOfColumns, and numberOfChildren.
	The colours assumed are red(255,0,0), green(0,255,0), blue(0,0,255), yellow(255,255,0), orange(255,165,0), cyan(0,255,255), 
	magenta(255,0,255), ocean(127,205,255), and violet (238,130,238).
*/

//INCLUDES
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>

//PROTOTYPES
void myAlarmHandler(int dummy){}; 
int getCurrCol(int maxRow, int maxCol, int row, int col);
unsigned char *getColComp(char* colour);

//MAIN
int main (int argc, char *argv[]){
	int fd;
	int numRows, numCols, numChil;
	int headerSize = 3;					//size of header starts at 3
	int currCol;						//current colour being drawn
	int maxRows;						//maximum number of rows writable by each child
	int dRight, dLeft;					//right and left positions of the diamond

	char inputCountErr[] = "You inputted the wrong number of inputs!\n";
	char forkErr[] = "Fork error!\n";
	char openErr[] = "Open error!\n";
	char *name, *colC, *colTL, *colTR, *colBL, *colBR, *numRowsStr, *numColsStr;

	signal(SIGALRM, myAlarmHandler);	//register SIGALRM as the signal to resume paused processes with myAlarmHandler

	if (argc != 10){
		write(STDOUT_FILENO, inputCountErr, sizeof(inputCountErr));	
	}
	
	else{
		name = argv[1];
		colC = argv[2];
		colTL = argv[3];
		colTR = argv[4];
		colBL = argv[5];
		colBR = argv[6];
		numRowsStr = argv[7];
		numColsStr = argv[8];
		numRows = atoi(argv[7]);
		numCols = atoi(argv[8]);
		numChil = atoi(argv[9]);
		dLeft = numCols / 2;
		dRight = numCols / 2;

		if ((fd = open(name, O_CREAT | O_WRONLY | O_TRUNC, 0733)) != -1){
			headerSize += strlen(numRowsStr) + 1 + strlen(numColsStr) + 1 + 5;
			char header[headerSize];
			memset(header, 0, sizeof(header));
			strcat(header, "P6\n");
			strcat(header, numRowsStr);
			strcat(header, " ");
			strcat(header, numColsStr);
			strcat(header, "\n255\n");
			write(fd, header, sizeof(header) - 1);

			int pids[numChil];				//pids of all children
			memset(pids, 0, sizeof(pids));

			maxRows = numRows / numChil;
			unsigned char childContents[maxRows * numCols * 3];	//array containing contents of what each child will print
			memset(childContents, 0, sizeof(childContents));
			int childContentsPos = 0;

			for (int i = 0;i < numChil;i++){
				if ((pids[i] = fork()) < 0){
					write(STDOUT_FILENO, forkErr, sizeof(forkErr));
				}
				else{
					if (pids[i] == 0){
						memset(childContents, 0, sizeof(childContents));
						if (i <= numChil / 2){					//calculation of what each child will print
							dLeft -= (i * maxRows) - (numRows / 4);
							dRight += (i * maxRows) - (numRows / 4);
						}
						else{
							dLeft += ((i - numChil / 2) * maxRows) - (numRows / 4);
							dRight -= ((i - numChil / 2) * maxRows) - (numRows / 4);
						}
						for (int j = 0; j < maxRows ; j++){
							if (j + (i * maxRows) <= numRows / 4){
								dLeft = numCols / 2;
								dRight = numCols / 2;
							}
							/*if (j + (i * maxRows) == numRows / 2){
								
							}*/
							for (int k = 0; k < numCols;k++){
								currCol = getCurrCol (numRows, numCols, i * maxRows + j, k);
								unsigned char *colour;
								switch (currCol){
									case 0:
										colour = getColComp(colTL);
										break;
									case 1:
										colour = getColComp(colTR);
										break;
									case 2:
										colour = getColComp(colBL);
										break;
									case 3:
										colour = getColComp(colBR);
										break;
								}
								if (((j + maxRows * i) >= (numRows / 4)) && ((j + maxRows * i) <= (numRows / 2))){
									if (k == dLeft){
										for (int d = 0;d <= (dRight - dLeft);d++){
											colour = getColComp(colC);
											for (int l = 0;l < 3;l++){
												childContents[childContentsPos] = colour[l];
												childContentsPos++;
											}
											k++;
										}
										dLeft--;
										dRight++;
									}
								}
								else if (((j + maxRows * i) >= (numRows / 2)) && ((j + maxRows * i) <= (numRows * 3 / 4))){
									if (k == dLeft){
										for (int d = 0;d <= (dRight - dLeft);d++){
											colour = getColComp(colC);
											for (int l = 0;l < 3;l++){
												childContents[childContentsPos] = colour[l];
												childContentsPos++;
											}
											k++;
										}
										dLeft++;
										dRight--;
									}
								}
								for (int l = 0;l<3;l++){
									childContents[childContentsPos] = colour[l];
									childContentsPos++;
								}
							}
						}
						pause();
						for (int i = 0;i < sizeof(childContents) / sizeof(unsigned char);i++){
							write(fd, &childContents[i], 1);
						}
						close(fd);

						exit(0);	//sends a signal to the parent to tell the next child to write
					}
				}
			}
			//parent tells each child to write
			sleep(5);
			for (int i = 0;i < numChil;i++){
				kill(pids[i], SIGALRM);
				pause();
			}
			
		}
		else{
			write(STDOUT_FILENO, openErr, sizeof(openErr));
		}
	}
	return 0; 
}

//FUNCTION DEFINITIONS
//gets the current colour to be drawn on the image
int getCurrCol(int maxRow, int maxCol, int row, int col){
	if (col < (maxCol / 2) && row < (maxRow / 2)){
		return 0;
	}
	else if (col >= (maxCol / 2) && row < (maxRow / 2)){
		return 1;
	}
	else if (col < (maxCol / 2) && row >= (maxRow / 2)){
		return 2;
	}
	else{
		return 3;
	}
}

//returns a string containing the colour components of the specified colour
unsigned char *getColComp(char* colour){
	unsigned char *chosen;	//colour value that will be retrieved

	unsigned char red[] =  {255, 0, 0};
	unsigned char green[] = {0, 255, 0};
	unsigned char blue[] = {0, 0, 255};
	unsigned char yellow[] = {255, 255, 0};
	unsigned char orange[] = {255, 165, 0};
	unsigned char cyan[] = {0, 255, 255};
	unsigned char magenta[] = {255, 0, 255};
	unsigned char ocean[] = {127, 205, 255};
	unsigned char violet[] = {238, 130, 238};
	unsigned char white[] = {255, 255, 255};

	if (strcmp("red", colour) == 0){
		chosen = red;
	}
	else if (strcmp("green", colour) == 0){
		chosen = green;
	}
	else if (strcmp("blue", colour) == 0){
		chosen = blue;
	}
	else if (strcmp("yellow", colour) == 0){
		chosen = yellow;
	}
	else if (strcmp("orange", colour) == 0){
		chosen = orange;
	}
	else if (strcmp("cyan", colour) == 0){
		chosen = cyan;
	}
	else if (strcmp("magenta", colour) == 0){
		chosen = magenta;
	}
	else if (strcmp("ocean", colour) == 0){
		chosen = ocean;
	}
	else if (strcmp("violet", colour) == 0){
		chosen = violet;
	}
	else{
		chosen = white;	//default to white to signal an error in creation
	}

	return chosen;
}