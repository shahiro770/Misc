/* 	
	Assignment 1.c
	Shahir Chowdhury 104598120
	2017-10-08
	This program merges two images together, putting the smaller image on the top right of the first image. 
	The program assumes that only two images will be given as arguments, and that they will be in P6 ppm files.
	The second image must always be smaller than the first It tells the user there is an error if inputted otherwise.
*/

//INCLUDES
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

//MAIN
int main (int argc, char *argv[]){
	int fd1, fd2, fd3;			//file pointers to the first image, second image, and merged image respectively
	const char error[] = "Error!\n";	//Error message if images are inputted in the wrong order
	char t1[4], t2[4];			//type of ppm file for both images
	char w1[5], h1[5];			//width and height of the first image, maximum size being 9999 x 9999
	char w2[5], h2[5]; 			//width and height of the second image, maxixum size being 9999 x 9999
	char r1[4];					//range of colour values for the first image
	char newline[2] = "\n";		//contains the newline character for formatting the header
	char space[2] = " ";		//contains the space character for formatting the header;
	int w1i, h1i;				//integer forms of w1 and h1
	int w2i, h2i;				//integer forms of w2 and h2
	int headsize1 = 7;			//counter for the size (in bytes) for the first header, starts at 7 counting formating, range and type
	int headsize2 = 7;			//counter for the size (in bytes) for the second header, starts at 7 counting formating, range and type

	fd1 = open(argv[1], O_RDONLY, 0733);
	fd2 = open(argv[2], O_RDONLY, 0733);
	fd3 = open("merged.ppm", O_CREAT | O_WRONLY | O_TRUNC, 0733);	//new file

	//only run if the specified number of inputs is given
	if (argc != 3){
		write(STDOUT_FILENO, error, sizeof(error)/sizeof(error[0]) - 1);
		return 0;
	}

	read(fd1, t1, 3);	//read the type of the first image

	//obtain the dimensions from the header of the first image
	for (int i = 0;i < 5; i++){
		headsize1++;
		read(fd1, w1 + i, 1);
		if (*(w1 + i) == ' ' || *(w1 + i) == '\n'){
			*(w1 + i) = '\0';
			break;
		}
	}

	for (int i = 0;i < 5; i++){
		headsize1++;
		read(fd1, h1 + i, 1);
		if (*(h1 + i) == ' ' || *(h1 + i) == '\n'){
			*(h1 + i) = '\0';
			break;
		}
	}

	read(fd1, r1, 4);	//get the maximum value of the colour components of the first image

	read(fd2, t2, 3);	//read the type of the second image

	//obtain the dimensions from the header of the first image
	for (int i = 0;i < 5; i++){
		headsize2++;
		read(fd2, w2 + i, 1);
		if (*(w2 + i) == ' ' || *(w2 + i) == '\n'){
			*(w2 + i) = '\0';
			break;
		}
	}

	for (int i = 0;i < 5; i++){
		headsize2++;
		read(fd2, h2 + i, 1);
		if (*(h2 + i) == ' ' || *(h2 + i) == '\n'){
			*(h2 + i) = '\0';
			break;
		}
	}

	//conversions
	w1i = atoi(w1);
	h1i = atoi(h1);
	w2i = atoi(w2);
	h2i = atoi(h2);

	//throw an error if the size of the second image is larger than the first
	if (w1i < w2i || h1i < h2i){
		write(STDOUT_FILENO, error, sizeof(error)/sizeof(error[0]) - 1);

		close (fd1);
		close (fd2);
		close (fd3);
		return 0;
	}

	//write the header of the merged image to be the same as the first's
	write(fd3, t1, 3);
	if (strlen(w1) == 4){
		write(fd3, w1, 4);
	}
	else if (strlen(w1) == 3){
		write(fd3, w1, 3);
	}
	else if (strlen(w1) == 2){
		write (fd3, w1, 2);
	}
	else if (strlen(w1) == 1){
		write (fd3, w1, 1);
	}
		
	write(fd3, space, 1);

	if (strlen(h1) == 4){
		write(fd3, h1, 4);
	}
	else if (strlen(h1) == 3){
		write(fd3, h1, 3);
	}
	else if (strlen(h1) == 2){
		write (fd3, h1, 2);
	}
	else if (strlen(h1) == 1){
		write (fd3, h1, 1);
	}	
	write(fd3, newline, 1);
	write(fd3, r1, 4);

	char rwbuff[3];	//buffer containing image pixel information to be read and written

	//write the larger image to the output file
	for (int i = 0;i < h1i;i++){
		for (int j = 0;j < w1i * 3;j++){
			read(fd1, rwbuff, 3);
			write(fd3, rwbuff, 3);
		}
	}

	//write the smaller image to the corner of the output file
	lseek(fd2, headsize2, SEEK_SET);
	lseek(fd3, headsize1, SEEK_SET);

	for (int i = 0;i < h2i;i++){
		lseek(fd3, (w1i * 3) - (w2i * 3), SEEK_CUR);
		for (int j = 0;j < w2i;j++){
			read(fd2, rwbuff, 3);
			write(fd3, rwbuff, 3);
		}
	}

	close (fd1);
	close (fd2);
	close (fd3);
}