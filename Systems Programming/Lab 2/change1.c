#include <unistd.h>
#include <fcntl.h>

int main(){
	int buf1 = 101;
	int fd;

	fd = open("list1.txt", O_RDWR , 0733);

	write(fd, &buf1, 4);
	close(fd);

	return 0;
}