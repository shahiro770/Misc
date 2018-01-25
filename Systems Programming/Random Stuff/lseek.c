#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

int main(){
	char buf1[] = "abcdefghij";
	char buf2[] = "ABCDEFGHIJ";
	int fd;

	if ((fd = creat("newfile", 0533)) < 0){
		printf("creat error\n");
		exit(1);
	}
	if ((write(fd, buf1, 10)) != 10){
		printf("write error\n");
		exit(1);
	}
	if ((lseek(fd, 15, SEEK_SET)) == -1) {
		printf("write error\n");
		exit(1);
	}
	if ((write(fd, buf2, 10)) != 10){
		printf("write error\n");
		exit(1);
	}

	close(fd);
}

/* command line arguments
	>>> merge firework.ppm 150.ppm firework.5appm

	main (int argc, char * argv[]){
		...
	}
	
	argc is the argument count, including the program name
	argv is an array containing the program slots
		i.e. argv[1] is "merge"
			 argv[2] is "firework.ppm"
			 argv[3] is the file name that needs to be made
	

*/