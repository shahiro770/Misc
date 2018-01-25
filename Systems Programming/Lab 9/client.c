/*
 * Shahir Chowdhury
 * 104598120
client.c (Lab 9)
 * 2017-11-20
 *
 * This program activates the client side of the program. I hate systems programming.
 * Assignment 5 should work like this: you type commands and the results should output the cs server's outputs
 * on the cs server, run the client program with the ip will use wifi to connect to the server 137.307.82.51
*/

#include <fcntl.h> 
#include <stdio.h>
#include <stdlib.h>
#include <signal.h> 
#include <unistd.h>
#include <sys/stat.h>

int main(int argc, char *argv[]) {
	int fd1, fd2;
	pid_t pid;
	char ch, ch2;
	char blanks[55]="                                                    | ";
	int writeblanks = 1;

	while (1) {
		fd1 = open("./fifo1", O_WRONLY);
		fd2 = open("./fifo2", O_RDONLY);
		printf("\nConnected.");
		printf("\nmy messages (client)                                received messages (server) \n");
		printf("-------------------------------------------------------------------------------\n");
		if ( (pid = fork()) == -1 ) {
			perror("fork");
			exit(1);
		}
		if ( pid == 0 ) {
			while (1) {
				while (read(fd2, &ch2, 1) == 1 ) { 
					if (ch2 == '^'){	//if the server says the safe word, client commits sudoku
						close(fd1);
						close(fd2);
						kill(getppid(), SIGTERM);
						break;
					}
					if ( writeblanks == 1 ){
						write(1, blanks, sizeof(blanks)); 
						writeblanks = 0;
					}
					write(1, &ch2, 1);
					if (ch2 == '\n'){
						writeblanks = 1;
					}

				}
				break;
			}
		}
		while ( ( ch = getchar()) != -1){
			write(fd1, &ch, 1); 
		}

		close(fd1);
		close(fd2);
		kill(pid, SIGTERM);
	}
}