#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
	int num, fd1, fd2;
	int fact = 1;
	int id;

	num = atoi(argv[1]);
	id = fork();

	if (id > 0){
		if (num < 1 || num > 10){
			printf("Error!\n");
			return -1;
		}
		else{
			fd1 = open("data.dat", O_CREAT | O_WRONLY | O_TRUNC, 0733);
			write(fd1, argv[1], sizeof(argv[1])/sizeof(argv[1][0]));
		}
	}

	if (id == 0){
		sleep(1);
		fd2 = open("data.dat", O_RDONLY, 0733);
		for (int i = 1; i <= num; i++){
			fact *= i;
		}
		printf("%d\n", fact);
	}
}


		


