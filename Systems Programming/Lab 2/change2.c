#include <unistd.h>
#include <fcntl.h>

int main(){
	int buf1 = 101;
	int fd;

	fd = open("list2.txt", O_RDWR , 0733);
	lseek(fd, 3, SEEK_SET);

	write(fd, &buf1, 4);
	close(fd);

	return 0;
}