#include <unistd.h>
#include<fcntl.h>
//Can't open a word document with a text based editor
int main(){
	int fd;
	char s[50] = "Hello World";
	fd = open ("text", O_WRONLY | , 0755);
	write(fd, s, sizeof(s));
	close(fd);
}