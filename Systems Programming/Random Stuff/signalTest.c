#include <fcntl.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>


void alarm_handler(int dummy){
		printf("I got an alarm. I take care of signal %d\n",dummy);
		signal(SIGALRM, alarm_handler);		//set up the alarm again? What happens?
		alarm(3);
		sleep(4);							
	}

	int main (int argc, char * argv[]){
		signal(SIGALRM, alarm_handler);
		alarm(3);	//alarm triggers after 3 seconds, BUT ONLY ONCE
		while(1) {
			printf("I am working\n");	//after the event is handled, you get back to the previous statement
			sleep(1);
		}
	}