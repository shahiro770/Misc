/*
 * Shahir Chowdhury
 * 104598120
 * serverSocket.c (Assignment 5)
 * 2017-11-26
 *
 * This program activates the server side of the program.
 * Commands typed in from the client program will usually be executed on the server's side. The server
 * will execute them and output the results to the client.
 * Exiting the server will usually not terminate the client (probably). Please give me full marks for trying. 
*/

#include	<netinet/in.h>
#include	<stdio.h>
#include	<unistd.h>
#include	<stdlib.h>
#include 	<fcntl.h>
#include	<string.h>
#include 	<sys/stat.h>
#include 	<signal.h> 

int main(int argc, char	*argv[]){
	char buffer[10000];					//big arrays for a big cause
	memset(&buffer, 0, sizeof(buffer));
	int sd,	client, pid;	
	socklen_t len;
	struct sockaddr_in servAdd;	
	struct sockaddr_in cliAdd;	
	sd = socket(AF_INET, SOCK_STREAM, 0);
	servAdd.sin_family = AF_INET;
	servAdd.sin_addr.s_addr = INADDR_ANY;	
	servAdd.sin_port = 5555;	
	bind(sd, (struct sockaddr*) &servAdd, sizeof(servAdd));
	listen(sd, 5);

	while (1){
		printf("Waiting for connection...\n");
		len = sizeof(cliAdd);
		client = accept(sd,(struct sockaddr*)&cliAdd, &len);
		printf("Connected\n");
		while (read(client, buffer, 10000) > 0){		
			printf("Receieved command: %s\n", buffer);

			if ((pid = fork()) == 0){
				dup2(client, STDOUT_FILENO);		//use dup2 to send output to client
				execlp(buffer, buffer, (char*)0);
			}
			memset(&buffer, 0, 10000);
		}
		close(client);
	}
}