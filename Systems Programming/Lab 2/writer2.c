#include <unistd.h>
#include <fcntl.h>

int main(){
	char buf1[40] = "GM\t101\tBuick\t2010\nFord\t102\tLincoln\t2005";
	int fd;

	fd = open("list2.txt", O_WRONLY | O_CREAT | O_TRUNC, 0733);

	write(fd, &buf1, 40);
	close(fd);

	return 0;
}