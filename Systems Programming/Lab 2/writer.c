#include <unistd.h>
#include <fcntl.h>

int main(){
	char buf1[44] = "101   GM\tBuick\t2010\n102   Ford\tLincoln\t2005";
	int fd;

	fd = open("list1.txt", O_WRONLY | O_CREAT | O_TRUNC, 0733);

	write(fd, &buf1, 44);
	close(fd);

	return 0;
}