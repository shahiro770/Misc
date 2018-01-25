/*
 * Shahir Chowdhury
 * 104598120
 * server.c (Lab 9)
 * 2017-11-20
 *
 * This program activates the server side of the program. I hate systems programming.
*/

#include <fcntl.h> 
#include <stdio.h>
#include <stdlib.h>
#include <signal.h> 
#include <unistd.h>
#include <sys/stat.h>

void handler(int sig); //lets server stop the client nanoseconds before the server process ends

int main(int argc, char *argv[]) {
	int fd1, fd2;
	pid_t pid;
	char ch, ch2;
	char blanks[55] = "                                                    | ";
	signal(SIGINT, handler);
	int writeblanks = 1;

	unlink("./fifo1");
	unlink("./fifo2");
	if ( mkfifo("./fifo1", 0777) || mkfifo("./fifo2", 0777)) {
		perror("fifo");
		exit(1); 
	}
	while (1) {
		printf("\nWaiting for connection...\n");
		fd1 = open("./fifo1", O_RDONLY);
		fd2 = open("./fifo2", O_WRONLY);

		printf("\nmy messages (server)                                received messages (client) \n");
		printf("-------------------------------------------------------------------------------\n");
		if ( (pid = fork()) == -1 ) {
			perror("fork");
			exit(1);
		}

		if ( pid == 0 ) {
			while (1) {
				while ( ( ch2 = getchar()) != -1){
					write(fd2, &ch2, 1); 
				}
				break;	
			}
		}

		while (read(fd1, &ch, 1) == 1 ) { 
			if ( writeblanks == 1 ){
				write(1, blanks, sizeof(blanks)); 
				writeblanks = 0;
			}
			write(1, &ch, 1);
			if (ch == '\n'){
				writeblanks = 1;
			}
		}

		close(fd1);
		close(fd2);
		printf("\nClient left.\n\n\n");
		kill(pid, SIGTERM);
	}
}

void handler(int sig) {
	int fd2 = open("./fifo2", O_WRONLY);
	write(fd2, "^", 1);	//^ is the safety word that will kill the cilent. This is a hacky solution but I got angry
	close(fd2);
	unlink("./fifo1");
	unlink("./fifo2");
	exit(0);
}