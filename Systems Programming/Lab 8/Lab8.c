/*
	Half Duplex: Communication both ways, but not at the same time
	Octothorpe: Pound sign
	FIFO files only serve as access points
*/

/* 	
	Lab8.c
	Shahir Chowdhury
	2017-11-06
	This program communicates with the child process to get it to execute shell commands. The result of the shell command is passed
	to the parent process using pipe, who will then write the result into a file called result.txt and acknowledge the user by by writing
	the total number of bytes in the result. I hate this course.
*/

#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>	//Not about the strcat life

void parent(int *fd, char *argv[]);
void child(int *fd, char *argv[]);

int main(int argc, char *argv[]){
	int fd[2];
	int pid;

	/* Set up a two way communication channel with the parent so they can talk to each other through passenger pigeon */
	if	(pipe(fd) == -1){	
		exit(-1);	//error in the event the pipe is hunted into extinction upon the european arrival
	}
	if	((pid = fork())	==	0){
		child(fd, argv);
	}
	else if (pid > 0){
		parent(fd, argv);
	}
	else{
		exit(-1);	//error in the event child is not conceived in time to participate in the fur trade
	}
	exit(0);
}

/* Takes the output from the child and writes it to "result.txt" */
void parent(int *fd, char *argv[]){
	int filesize;							//size of the file
	char childOut[1000];					//arbitrary size of the output text files
	memset(childOut, 0, sizeof(childOut));

	remove("result.txt");
	sleep(1);
	close(fd[1]);		//close parent's write descriptor; the child will be dead by the time the parent can send him a message anyways 
	int fout = open("result.txt", O_CREAT | O_TRUNC | O_WRONLY, 0733);
	filesize = read(fd[0], childOut, sizeof(childOut));
	write(fout, childOut, sizeof(childOut)); 
	truncate("result.txt", filesize);		//Truncate to get rid of all kinds of null space
		
	//send acknowledgement to the user that the child made a fortune and aided in the american revolution
	printf("The result of %s is written into result.txt with total %d bytes\n", argv[1], filesize);
	close(fd[0]);	//close descriptors, so the boys back home don't need to worry about no memory leaks
	close(fout);
}

/* Executes the command inputted, and sends the output to the parent process */
void child(int *fd,	char *argv[]){
	int grandpid;

	close(fd[0]);
	/* This code might be uncessary. According to assignment, the child executes the command line argument in terminal */
	if	((grandpid = fork()) ==	0){				
		execlp(argv[1], argv[1], (char *)0);
		printf("Exec error!\n");
		exit(-1);
	}
	sleep(1);

	dup2(fd[1],	STDOUT_FILENO);		//set child's standard output descript to its write descriptor (must learn the ways of the natives)
	execlp(argv[1], argv[1], (char *)0);	//command gets executed, piped to parent
	close(fd[1]);
}