/* 	
	Title: Lab5.c
	Author(s): Shahir Chowdhury
	Date last modified: 2017-10-23
	Objective: This program creates 7 processes that form a very awkward family tree
	
	Lectures Notes Monday October 23:
		You can also call wait(), takes takes the address of an integer and returns a pid
			You get blocked (your program can get stuck)
			You can wait for a specific child, by calling waitpid(pid, , blockFlag)
				0 means blocking, WNOHANG for something else????
		Zombie process: child has been terminated but the parent is trying to call it
			Block Input: parent checks if the child has terminated, child is still alive, so block until it happens
		Orphan: parent terminates before child does

	Something I overheard while tutoring
	Tsin asks questions about definitions about sets and etc.
*/

//INCLUDES
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

//MAIN
int main(int argc, char *argv[]){
	int pid;
	int processNum = 1;

	for (int i = 1;i < 3;i++){
		if (processNum == 1){
			if ((pid = fork()) < 0){
				exit(1);
			}
		}
		if (pid == 0){
			if (processNum == 1){
				processNum = i + 1;
			}
			if (processNum == 2 || processNum == 3){
				for (int j = 1;j < 3;j++){
					if (processNum == 2 || processNum == 3){
						if ((pid = fork()) < 0){
							exit(1);
						}
					}
					if (pid == 0){
						if (processNum == 2 || processNum == 3){
							if (j == 1){
								processNum = i + j + 2;
							}
							else if (j == 2){
								processNum = i + j + 3;
							}
						}
					}
				}
			}
		}
	}
	sleep(atoi(argv[processNum]));
	printf("process %d terminated pid=%d\n", processNum,getpid());
}
