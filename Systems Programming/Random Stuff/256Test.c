/* #include <stdio.h>
#include <unistd.h>
#include  <stdlib.h>

void child(int fd[]);
void parent(int fd[]);

int main(int argc, char *argv[]){
	int fd[2];
	int pid;

	if	(pipe(fd)	==	-1)
		exit(0);
	if	((pid = fork())	==	0)
		child(fd);
	else
		parent(fd);
	exit(0);
}

void child(int fd[])	{
	char	message[255]	=	"Hello	from	child	process\n";
	close(fd[0]);
	sleep(5);
	write(fd[1],	message,	26);
}
void parent(int *fd){
	char ch;
	close(fd[1]);
	printf("Reading	data	from	child…\n");
	for	(int i =	0;	i <	28;	i++)	{
		read(fd[0],	&ch,	1);
		printf("%c",	ch);
	if	(ch ==	'\n')
		break;
	}
} */


#include	<stdio.h>
#include	<unistd.h>
#include	<stdlib.h>
void parent(int*,	char*[]);
void child(int*,	char*[]);
int main(int argc,	char	*argv[])	{
	int fd[2];
	if	(pipe(fd)	==	-1)
		exit(1);
	if	(fork()	>	0)
		parent(fd,	argv);
	else
		child(fd,	argv);
	}
void parent(int *fd, char *argv[])	{
	close(fd[0]);
	dup2(fd[1],	STDOUT_FILENO);
	close(fd[1]);
	execlp(argv[1],	argv[1], NULL);
}
void child(int *fd,	char *argv[])	{
	close(fd[1]);
	dup2(fd[0],	STDIN_FILENO);
	close(fd[0]);
	execlp(argv[2],	argv[2], NULL);
}
