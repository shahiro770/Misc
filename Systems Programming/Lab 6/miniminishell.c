/* 	
	Lab6.c
	Shahir Chowdhury
	2017-10-30
	This program runs parallel UNIX commands, assuming the commands cannot communicate between each other (e.g. pipes)
*/

//INCLUDES
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>

//MAIN
int main (int argc, char *argv[]){
	int pid;
	int status;
	int pathNameSize;
	char forkErr[] = "Fork error!\n"; 

	for (int i = 0;i < (argc) / 2;i++){		//loop for every command and argument pair
		if ((pid = fork()) < 0){	
			write(1, forkErr, sizeof(forkErr));
		}
		else if (pid == 0){
			char *command = argv[i * 2 + 1];	
			if ((argc % 2 == 0) && (i == (argc + 1) / 2 - 1)){		//special case for the last command as it can consist of no arguments
				pathNameSize = 5 + strlen(command);					//5 accounts for /bin/
				char pathName[pathNameSize];
				memset(pathName, 0, sizeof(pathName));				//build 
				strcat(pathName, "/bin/");
				strcat(pathName, command);
				execl(pathName, command, (char *)0);
				printf("exec failed.\n");
			}
			else{
				char *arg = argv[i * 2 + 2];
				pathNameSize = 5 + strlen(command) + strlen(arg);	//5 accounts for /bin/
				char pathName[pathNameSize];
				memset(pathName, 0, sizeof(pathName));
				strcat(pathName, "/bin/");
				strcat(pathName, command);
				execl(pathName, command, arg, (char *)0);

				printf("exec failed.\n");
			}
		}

		wait(&status);
		printf("\nProcess #%d did it's job!\n\n", pid);
	}
	return 0;
}