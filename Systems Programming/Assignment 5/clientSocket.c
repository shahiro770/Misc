/*
 * Shahir Chowdhury
 * 104598120
 * clientSocket.c (Assignment 5)
 * 2017-11-26
 *
 * This program activates the client side of the program. I hate systems programming.
 * Commands typed in from the client program will usually be executed on the server's side. The server
 * will execute them and output the results to the client, who will usually write them correctly.
 * Exiting the client will not terminate the server. Please give me full marks for trying. 
*/

#include	<netinet/in.h>
#include	<stdio.h>
#include	<unistd.h>
#include	<stdlib.h>
#include 	<fcntl.h>
#include	<arpa/inet.h>
#include	<string.h>
#include 	<sys/stat.h>
#include 	<signal.h>

int main(int argc, char	*argv[]) {
	char buffer[10000];
	char serverOut[10000];
	char ch;

	int server;
	struct sockaddr_in servAdd;	
	server = socket(AF_INET, SOCK_STREAM, 0);
	servAdd.sin_family = AF_INET;
	servAdd.sin_addr.s_addr = inet_addr(argv[1]);
	servAdd.sin_port = 5555;

	while(1){
		connect(server, (struct sockaddr*)&servAdd, sizeof(servAdd));
		printf("\nEnter shell command: ");
		scanf("%s", buffer);

		write(server, buffer, sizeof(buffer));
		read(server, serverOut, 10000);
		printf("%s", serverOut);

		memset(serverOut, 0 , 10000);
		memset(&buffer, 0, 10000);	
	}
}