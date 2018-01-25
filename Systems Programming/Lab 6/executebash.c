/* 	
	executebash.c
	Shahir Chowdhury
	2017-10-30
	This program prints "EXAM! EXAM! EXAM!" to the screen and forks a child to execute a bash script named mybash
	that prints "STUDY! STUDY! STUDY!" to the screen.
*/

//INCLUDES
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <stdio.h>

//MAIN
int main (void){
	char warning[] = "EXAM! EXAM! EXAM!";
	int pid;
	int status;

	write(1, warning, sizeof(warning));
	if ((pid = fork()) < 0){
		exit(0);
	}
	else if (pid == 0){
		//when you call exec you start running the current program
		//execl("/bin/ls","ls", "-l", (char *)0);	//jumps from a c program to a shell command
													//you jump to execute another c prorgam 
		execl("./mybash.sh", "mybash.sh", (char *)0);	//jump from c to a shell script (we don't have the shell thats running this)
														//we need to tell it the shell to run in 
		printf("exec failed.\n");
	}
	wait(&status);

	return 0;
}

//FUNCTION DEFINITIONS