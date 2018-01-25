#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/wait.h>

static void sig_int(int);	//signal catching function

int main(void){
	char buf[10000]; //arbitrary
	pid_t pid;
	int status;

	if (signal(SIGINT, sig_int) == SIG_ERR){
		printf("signal error!");
	}

	printf("%%"); //print prompt (%% is the escape sequence for %)
	while (fgets(buf, 10000, stdin) != NULL){
		if (buf[strlen(buf) - 1] == '\n'){
			buf[strlen(buf) - 1] = 0; //replace newline with null
		}
		if ((pid = fork()) < 0){
			printf("your fork was a spoon!");
		}
		else if (pid == 0){ //if there was a successful child, the child can only perform the following
			execlp(buf, buf, (char *) 0);
			exit(127);
		}

		if ((pid = waitpid(pid, &status, 0)) < 0){
			printf("waitpid error");
		}
		printf("%%");
	}
	exit(0);
}

void sig_int(int signo){
	printf("Interrrupt\n%% ");
}