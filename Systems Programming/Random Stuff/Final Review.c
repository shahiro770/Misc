/* Final Review (2017-12-04)
Sike, we're doing standard input and output hahahahahahahheheuhghuatearstearstearshelpIhatethiscoursesomeonePreneypleaseKsIaLvLeme

I/O System Calls
	Unbuffered I/O
	Not part of ISO C
Standard I/O Library
	Buffered
	Specified in C standard
	We use buffered because of performance for optimization
		Reading and writing to the disc (physical write) is more expensive
	For all the following code segments, assume #include <stdio.h> is given in the includes

Streams and FILE Object
	Comparing I/O system call and standard I/O
						Buffering			File Operations are Associated With
	I/O System Call 	No Buffer			File Descriptor
	Standard I/O 		Buffer 				Stream

	Stream
		With standard I/O, open or create a file implies to associate a stream with the file
	Characters in Stream
		Single byte character set
		Multi-byte (wide) character set

	Calling fopen gets you a stream
		FILE object
			A structure that contains all needed info to manage a stream
				File descriptor (the same thing you get using the system call open)
				Size of buffer 
				A pointer to a buffer for the stream
				A count of the characters currently in buffer (e.g. 512 size buffer, 10 characters inside)
				Error flag
				End-of-file flag (EOF)
		fopen fp provides a pointer to a file 
*/
	typedef struct{
			int level;
			unsigned flags;
			char fd;
			unsigned char hold;
			int bsize;
			unsigned char *buffer;
			unsigned char *curp;
			unsigned istemp;
			short token;	
	} FILE //this is everything you get by calling fopen
/*
		
					File Descriptors	File Pointers
					<unistd.h> 			<stdio.h>
	input			STDIN_FILENO 		stdin
	output 			STDOUT_FILENO      	stdout
	errorOutput 	STDERR_FILENO 		stderr
	
	Why Buffer?
		Minimize number of I/O system calls
		Try to do buffering automatically for each I/O stream
	Types
		 Fully buffered
		 Line buffered
		 Unbuffered
	
Buffering
	When does it physically write?
	Fully Buffered I/O
		When to do actual I/O
			You call read and write, when do you actually read and write into the disc or network?
			When I/O buffer is filled
		Example
			Disk files are fully buffered by standard I/O library
		When is the buffer created?
			The first time I/O is performed on a stream
			Created using malloc
		
		*/ int fflush(FILE *fp); /*
			Forces the associated buffer to be written even when it is partially filled
			E.g. the buffer isn't full but you want to send the message, so call this
			If fp = NULL, fflush will flush ALL open output streams 
	Line Buffered I/O
		When will the actual I/O take place?
			A new line character is encountered on input or output
			The buffer is full 
				Size of buffer used by standard I/O library is fixed at BUFSIZ, so actual I/O may take place before we enter newline!
		Typically used for standard input and standard output stream
			Keyboard (waiting for that \n)
			Monitor
	Unbuffered I/O
		When will the actual I/O take place?
			Each time we print or read a single character
			E.g. Standard error stream is normally unbuffered
	Default Buffering
		Standard I/O
			Fully buffered if and only if they do not refer to an interactive device (required by ANSI C)
			Terminal devices are usually line buffered (actual implementation)
		Standard Error
			Never fully buffered (required by ANSI C)
			Unbuffered (actual implementation)
*/
	int main (void) {
		int i = 0;
		char line[100] = "No discrimination!\n";
		while (line[i] != '\0'){
			putchar(line[i++]);
			//fflush (stdout);
			sleep(1);
		}
	}
	//outputs nothing for 20 seconds, the physical write only triggers once it hits the newline character
	//The buffer is line buffered
/*

*/
	int main (void) {
		int i = 0;
		char line[100] = "No discrimination!\n";
		while (line[i] != '\0'){
			putchar(line[i++]);
			fflush (stdout);
			sleep(1);
		}
	}
	//outputs one character at a time, printing the entire phrase after 20 seconds
/*

*/
	int main (int argc, char *argv[]) {
		while(1){
			printf("No discrimination!");
			//printf("No discrimination!\n");
			sleep(1);
		}
	}
	//prints nothing, because after every loop we're just writing into the buffer, it will eventually write once the buffer fills up
	//How long that will take, no idea cause buffer size varies from program to program
	//In class it took like 3 minutes, but it will print a billion "No discrimination!", before repeating the process
/*

*/
	int main (int argc, char *argv[]) {
		while(1){
			//printf("No discrimination!");
			printf("No discrimination!\n");
			sleep(1);
		}
	}
	//Will print a "No Discrimination" every second
/*

*/
	int main (void) {
		int i = 0;
		char line[100] = "No discrimination!\n";
		while (line[i] != '\0'){
			write(1, line + i, 1);
			i++;
			sleep(1);
		}
	}
	//Will write 1 character per second, because write is a system call and is unbuffered
/*

*/
	int main (void) {
		int i = 0;
		char line[100] = "No discrimination!\n";
		while (line[i] != '\0'){
			putchar(line[i++]);
			//putchar(line[i++], stderr);
			sleep(1);
		}
	}
	//outputs the entire line in about 20 seconds, all at once
/*

*/
	int main (void) {
		int i = 0;
		char line[100] = "No discrimination!\n";
		while (line[i] != '\0'){
			//putchar(line[i++], stdout);
			putchar(line[i++], stderr);
			sleep(1);
		}
	}
	//error stream is not buffered (just like system calls), hence this will print one character every second
	//terminal device is line buffered (stdout and stdin)
	//disc is fully buffered
/*

*/
	int main(void){
		int pid, var = 88;
		if (write(STDOUT_FILENO, buf, sizeof(buf) - 1) != sizeof(buf) - 1){
			perror("write error");
		}
		printf("before fork\n");
		pid = fork();
		if (pid < 0){
			perror ("cannot fork");
			exit(1);
		}
		if (pid == 0){
			glob++;
			var++;
		}
		else{
			sleep(2);
		}
		printf("pid = %d , glob = %d, var = %d\n", getpid(), glob, var);
		exit(0);
	}
/*
	output
	write to stdout
	before fork
	pid = 1974, glob = 101, var = 89
	pid = 1973, glob = 100, var = 88
	
	However if we run this again, the contents might be different
	
	write to stdout
	before fork
	pid = 1901, glob = 101, var = 89
	before fork
	pid = 1980, glob = 100, var = 88
	
	The first write is written immediately to the file (the terminal) due to unbuffered
	printf belongs to library io, and hence has a buffer, which is line buffered, so its written to the buffer but not to the disc
		This can lead to some inconsistency
	When you call fork, the child inherits everything from the parent, resulting in both containing a buffer containing "before fork"
	This results in before fork being written twice, if the buffer isn't flushed in time
*/

	int main(){
		int status;

		printf("starting %d...\n", getpid());
		fork();
		printf("working %d...\n", getpid());
		fork();
		printf("ending %d...\n", getpid());

		wait(&status);
	}

	//this wil print starting once, working twice, and ending 4 times, all on seperate lines
/*

*/
	int main(){
		int status;

		printf("starting %d...", getpid());
		fork();
		printf("working %d...", getpid());
		fork();
		printf("ending %d...\n", getpid());

		wait(&status);
	}

	/* 	This wil print starting 4 times, working 4 times, and ending 4 times
			Each starting working ending will be on the same line
		The starting and working buffers are not flushed between forks, as terminal device is line buffered
		End result is that each process carries over starting and working 
			She drew a big diagram for something this easy, bless everyone who's not in the lecture right now
		Output:
		starting 2050...working 2050...ending 2050...
		starting 2050...working 2050...ending 2052...
		starting 2050...working 2051...ending 2051...
		starting 2050...working 2051...ending 2053...
	*/
/*

Change Default Buffering
	Use setbuf() of setvbuf()
		Either must be called after the stream is opened but before any operations have been performed on the stream
		
	*/ void setbuf(FILE *restrict fp, char * restrict buf); /* //returns 0 if ok, nonzero on error 

		setbuf will infer how to buffer based on the input (fully buffered if to a file, line buffered if to terminal)
	How to turn on?
		buf must point to a buffer of length BUFSIZ
		BUFSIZ defined in <stdio.h>
	How to turn off?
		setbuf to NULL
	
	*/ int setvbuf(FILE *restrict fp, char *restrict buf, int mode, size_t size); /*

		_IOFBF	//io fully buffered
		_IOLBF	//io line buffered
		_IONBF	//io not/unbuffered
	
	If mode is _IONBF
		buf and size arguments are ignored
	If mode is _IOFBF or _IOLBF BUT buf is NULL
		Automatically allocate resources to a buffer of size BUFSIZ

Summary of changing default Buffer Size
function	mode		buf 		buffer 			length 								type of buffering
setbuf  	N/A			non-NULL	user buf  		BUFSIZ 								fully or line buffered
			N/A			NULL 		no buffer 		no buffer 							unbuffered
setvbuf		_IOFBF 		non-NULL 	user buf 		BUFSIZ								fully buffered
 						NULL 		system buf 		system picks something appropriate 	fully buffered
			_IOLBF		non-NULL 	user buf 		BUFSIZ 								line buffered
 						NULL 		system buf 		system picks something appropriate	line buffered
			_IONBF		(ignored) 	no buffer 		no buffer 							unbuffered

Opening a Stream
----------------
Three functions to open a standard I/O stream
	fopen
	freopen
	fdopen

fopen()
	Open or create a file
	Associate a stream with the file
	Return a pointer to a FILE object

*/	FILE *fopen(const char *restrict pathname, const char *restrict type); /* //Returns a file pointer if ok, NULL on error
	
	Open a specified file

	*/ fopen("./data.txt", "r") /*
	
*/ FILE *freopen(const char *restrict pathname, const char *restrict type, FILE *restrict fp); /* 
	//return: file pointer if ok, NULL on error
	
	Open a specified file on a specified stream
		Closes stream fp first if it is already open on another file
	Typically used to open a specified file as one of the predefined streams
		Standard input
		Standard output
		Standard error

*/
	int main(){
		FILE *fp;

		printf("You should see this sentence on your screen.\n");
		fp = freopen("mac", "w+", stdout);
		fprintf(fp, "fprintf: written to file.\n");
		printf("printf: redirect to file.\n");
		fclose(fp);
		return(0);
	}
	/* Output:
	You should see this sentence on your screen.
	Output in file:
		fprintf: written to file
		printf: redirect to file
	The two fobs behind me won't quiet down
/*

*/
	int main(){
		FILE *fp;
		fp = fopen("newfile", "w");
		//fp = fopen("newfile", "r+");
		fclose(fp);
	}

	//the newfile originally contained "Do not delete me!"
	//After w, the newfile gets emptied cause w truncates
/*

*/
	int main(){
		FILE *fp;
		//fp = fopen("newfile", "w");
		fp = fopen("newfile", "r+");
		fclose(fp);
	}

	//the newfile originally contained "Do not delete me!"
	//After r+, content is not truncated
/*

Modes for open
	O_RDONLY 
	O_WRONLY 
	O_RDWR
	O_CREAT
	O_TRUNC
	O_APPEND

Modes for fopen
	r 		corresponds to O_RDONLY
	w 		corresponds to O_WRONLY and O_CREAT and O_TRUNC
	r+ 		corresponds to O_RDWR
	w+ 		corresponds to O_RDWR and O_CREAT and O_TRUNC
	a 		corresponds to O_CREAT and O_APPEND
	a+ 		corresponds to O_RDWR and O_CREAT and O_APPEND

fopen("newfile", "r");
open("newfile", O_RDONLY, 0666);
Say we had a a umask value of 0022 (0 000 010 010)
	The original permissions for the newfile were  0666 ()
	End result is for the newly created file by open is 110 100 100 
		Makes sense, a 1 strips away the permission, 0 mantains the program's permission setting

THE ACTUAL REVIEW
-----------------

Signals
-------
	SIGALRM by default means quit

*/
	int main(){
		alarm(5);
		while (1){
			printf("going to sleep\n");
			sleep(1);
		}
	}
	//this would print going to sleep 5 times , printing going to sleep each time
/*

*/
	int main(){
	int status;

	alarm(5);
	int pid = fork();
	for (int i = 0;i < 0;i++){
		if (pid == 0){
			printf("I am the child\n!");
			else{

			}
		}
	}
	}
	//signals are not passed to the child
/*

*/
	int main(){
		int i;
		for (int i = 0;i < )
	}
/*

Be prepared for signal, signal + fork, and signal + exec

*/
	void alarm_handler(int dummy){
		printf("I got an alarm. I take care of signal %d\n",dummy);
		//signal(SIGALRM, alarm_handler);	//set up the alarm again? What happens?
		//alarm(3);
	}

	int main (int argc, char * argv[]){
		signal(SIGALRM, alarm_handler);
		alarm(3);	//alarm triggers after 3 seconds, BUT ONLY ONCE
		  (1) {
			printf("I am working\n");	//after the event is handled, you get back to the previous statement
			sleep(1);
		}
	}
	//output is I am working x3, followed by I got an alarm. I take care ofsignal, and then I am working forever
/*

*/
	void alarm_handler(int dummy){
		printf("I got an alarm. I take care of signal %d\n",dummy);
		//signal(SIGALRM, alarm_handler);	//set up the alarm again? What happens?
		alarm(3);							//the alarm will trigger again, meaning the signal will happen again
	}

	int main (int argc, char * argv[]){
		signal(SIGALRM, alarm_handler);
		alarm(3);	//alarm triggers after 3 seconds, BUT ONLY ONCE
		while(1) {
			printf("I am working\n");	//after the event is handled, you get back to the previous statement
			sleep(1);
		}
	}
	//output is I am working x3, followed by I got an alarm. This pattern repeats forever
/*

*/
	void alarm_handler(int dummy){
		printf("I got an alarm. I take care of signal %d\n",dummy);
		signal(SIGALRM, alarm_handler);		//set up the alarm again? What happens? No idea cause Chen forgot
		alarm(3);							
	}

	int main (int argc, char * argv[]){
		signal(SIGALRM, alarm_handler);
		alarm(3);	//alarm triggers after 3 seconds, BUT ONLY ONCE
		while(1) {
			printf("I am working\n");	//after the event is handled, you get back to the previous statement
			sleep(1);
		}
	}
	//output is the same as above, because the alarm)handler never gets the alarm signal while it is running
	//If there were a sleep(4) after the alarm(3) in the signal handler, we'd have a different store
/*

*/
	void alarmhandler() {
		static int n = 0;
		if (n < 3){
			printf("Escape!\n");
			alarm(3);	//set up the alarm again
		}
		else{
			exit(0)
		}
	}

	int main(int argc, char *argv[]){
		signal(14, alarmhandler);	//14 = SIGALRM
		alarm(3);
		while(1){
			pause();
		}
	}
	//Every 3 seconds, write escape. After three cycles, the program exits
/*

*/
	void handler(int signal) {	
		printf("Message from handler\n");
	}

	int main(int argc, char *argv[]){
		if (fork() == 0){
			sleep(1);
			exit(0);
		}
		signal(SIGCHILD, handler);	//SIGCHILD is usually ignored, but now its gonna have a handler to work with it
		printf("Parent printing...\n");
		sleep(2);
		printf("Parent printing...\n");
	}
	/*Outputs
		parent printing...
		message from handler //because the child dies and sends a SIGCHILD
		parent printing...
	*/
/*

//SIGPSTP corresponds go 24, and suspends the program

*/
	void action(int dummy){
		printf("Recieved cntrl - z, but not quitting\n");
		sleep(3);
	}
	//she's gonna press ctrl z twice
	int main{
		signal(SIGPSTP, action);
		while (1){
			sleep(5);
			printf("I am alive\n");
		}
	}
	/*The main program has a way to handle the signal
		But does the function linked to that signal have a way to handle another signal?
		If another signal arrives while you're handling the current signal, it depends on the implementation of linux
			Some linux will throw it away
			Some linux machines will store it in a queue and handle it right after
			The new signal entering does NOT have higher priority than the current signals
				Make the opposite of this statement on the final and chen will laugh at you
		On Chen's linux, pressing ctrl z twice, caused the signal to be stored, and executed right after
	*/
/*

*/
	int main(){
		signal(SIGUSR, handlerone);
		pause();
	}

	void handlerone(int dummy){
		signal(SIGUSR, handlertwo);
		printf("Recieved, SIGUSR1\n");
		sleep(10);
	}

	void handlertwo(int dummy){
		printf("Recieved SIGUSR1 again\n");
	}
	/*Output
		Recieved SIGUSR1
		Recieved SIGUSR1 again

		So yeah this is pretty sick, and Chen is acutally giving a decent lecture
		I hate her like 10% less now.
	/*
/*

Process Groups Review (This stuff is from the lecture on signals)
---------------------
UNIX organizes processes as follows
	Every process is a member of a process group
	A child inherits its process group from a parent
	When a process executes exec, its process group remains the same
	A process may change its process group using setgpid()

Every process may have an associated control
	Child inherits its control terminal from its parent
	When a process calls exec, its control terminal remains the same
Every terminal is associated with a control process
	A control process is a piece of software that manages the terminal
	E.g. when ctrl-c is typed, the terminal's control process sends a SIGINT to all processes in a process group

*/
	void CTRL_handler(int dummy){
		printf ("Process %d got a ctrl c, exit\n", getpid());
		exit(2);
	}

	int main(int argc, char *argv[]){
		int i;
		printf("First process, PID-%d, PPID = %d, PGID = %d\n", getpid(), getppid(), getgpid(0));
		signal(SIGINT, CTRL_handler);
		for (i = 1;i <= 3;i++){
			fork();
		}
		printf("PID = %d, PGID = %d\n", getpid(), getgpid(0));
		pause();
	}
	/*Output	
		First process, PID=3928, PPID=3885, PGID=3928
		PID=3928 PGID=3928
		PID=3931 PGID=3928
		PID=3929 PGID=3928
		PID=3930 PGID=3928
		PID=3932 PGID=3928
		PID=3933 PGID=3928
		PID=3934 PGID=3928
		PID=3935 PGID=3928
		Input a ^C
		Process	3932 got a CTRL-C, exit
		Process	3930 got a CTRL-C, exit
		Process	3929 got a CTRL-C, exit
		Process	3935 got a CTRL-C, exit
		Process	3931 got a CTRL-C, exit
		Process	3928 got a CTRL-C, exit
		Process	3934 got a CTRL-C, exit
		Process	3933 got a CTRL-C, exit
	All of the children will execute the signal, because they all receive the signal thanks to the control process
	*/
/*
	
*/
	void CTRL_handler(int dummy){
		printf ("Process %d got a ctrl c, exit\n", getpid());
		exit(2);
	}

	int main(int argc, char *argv[]){
		int i, pid;
		signal(2, CTRL_handler); //2 = SIGINT
		printf("First process, PID-%d, PPID = %d, PGID = %d\n", getpid(), getppid(), getgpid(0));
		if ((pid = fork()) == 0){
			setpgid(0, getpid()); //setgpid sets the gpid of the process specified by pid (first parameter) to gpid (second parameter)
								  //if the first param is 0, calling process pid is subbed in
								  //if the second param is 0, calling process pid is subbed in
								  //in this case, the calling process is putting itself in a group by itself
			printf("from child: PID=%d, PGID=%d\n",getpid(),getpgid(0));
		}
		else{
			printf("from parent: PID=%d,PGID=%d\n",getpid(),getpgid(0));
		}
		for (i=1; i<=10; i++) {
			printf("Process %d is alive\n", getpid());
			sleep(2);
		}
	}
	//Once a ctrl-c is fired off, the parent will die by the child will live on
/*

Random Diagram
--------------
REFER TO LECTURE ON SIGNALS SLIDE 77

Start a Program
	Command -> Run in foreground
	Command & -> Run in background

End a Program
	End a program in foreground with ctrl c
	End a program in background using kill -SIGINT

Suspend a Program
	Ctrl-z puts a foreground program into suspended
	kill -SIGTSTP puts a background program into suspended

Resume a Program
	To resume a foreground program, type fg
	To resume a background program, type bg

Random Shit
	To bring a background prorgam to foreground, type fg
	To end a suspended program, go to another terminal and kill-SIGINT pid (withwhatever its pid is)

80-100 minutes, very similar to the midterm
	Some of the content is related to knowledge some are related to skill


















