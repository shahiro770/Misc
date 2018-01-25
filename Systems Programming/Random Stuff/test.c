#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main (void) {
	int fd1;

	unlink("./fifo1");
	fd1 = mkfifo("./fifo1", 0777);
		int i = 0;
		char line[100] = "No discrimination!\n";
		while (line[i] != '\0'){
			putchar(line[i++]);
			//putchar(line[i++], stderr);
			sleep(1);
		}
	}
	//outputs the entire line in about 20 seconds, all at once
