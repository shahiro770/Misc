#include <unistd.h>
#include<fcntl.h>

int main(){
	int fd;
	char s[50] = "Hello World";
	fd = open ("text", O_WRONLY, 0755);
	write(fd, s, sizeof(s));
	close(fd);
}