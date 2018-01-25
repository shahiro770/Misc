/* signal means to register, not send a signal (e.g. SIGALRM, SIGUSR, SIGTERU)
pause means suspend yourself
kill means send a signal (depends on the signal sent)
So if we had kill(pid, signo)
	we are sending a signal to the process executing signal(Signo, tp)
		tp will be executed
No matter where you are, you immediately handle the signal (it has highest priority)
Note that signals can arrive before other signals, resulting in weird results (e.g. one signal terminates the next signal was meant for)
If both proccess send signals telling each other to wait, nothing will happen */

/* 	
	Lab7.c
	Shahir Chowdhury
	2017-11-06
	This program makes the children and parent work together to write some stuff
*/
//PROTOTYPES
void myAlarmHandler(int dummy){}; 	//dummy function to hold signals

//INCLUDES
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <signal.h>

//MAIN
int main (int argc, char *argv[]){
	int fd;
	pid_t pid1 = -1; 
	pid_t pid2 = -1;

	signal(SIGALRM, myAlarmHandler);	//register SIGALRM as the signal to resume paused processes with myAlarmHandler

	char buf0[] = "EXAM! EXAM! EXAM!\n";
	char buf1[] = "HELP! HELP! HELP!\n";
	char buf2[] = "STUDY! STUDY! STUDY!\n";
	fd = open(argv[1], O_WRONLY | O_CREAT | O_TRUNC, 0733);
	if ((pid1 = fork()) < 0){			//Give birth to the first child
		printf("Fork error!\n");   
	} 
	if (pid1 > 0){
		printf("parent created child process: %d\n", pid1);
		if ((pid2 = fork()) < 0){		//Give birth to the second child
			printf("Fork error!\n");	
		}
		if (pid2 > 0){
			printf("parent created child process: %d\n", pid2);
		}
		pause();			//Pause the parent and second child, sending the first child off to the west to make a name for himself
	}

	if (pid2 > 0){			//Parent will send the second child off to the west once it hears the first child lived the american dream
		kill(pid2, SIGALRM);
		pause();
	}

	if (pid1 == 0 || pid2 == 0){
		if (pid1 == 0){					//First child writes
			write(fd, &buf0, sizeof(buf0) - 1);
			sleep(5);
			printf("%d has written to the file: %s\n", getpid(), buf0);
			kill(getppid(), SIGALRM);	//send signal to the parent that the child hasn't been eaten by beavers
			exit(0);
		}
		else if (pid2 == 0){			//Second child writes
			//printf("%lu\n", sizeof(buf1) - 1);
			write(fd, &buf1, sizeof(buf1) - 1);
			sleep(5);
			printf("%d has written to the file: %s\n", getpid(), buf1);
			kill(getppid(), SIGALRM);	//Send signal to the parent that the child hasn't been eaten by beavers
			exit(0);
		}
	}
	write(fd, &buf2, sizeof(buf2) - 1);		//Parent finally comes to America
	sleep(5);
	printf("parent has written to the file: %s\n", buf2);
	close(fd);
	printf("parent has closed the file"); 
	return 0;
}