Advanced Programming in the UNIX Environment Notes
HOW TO READ THESE
	Indentation represents bullet points
	Something encased in "" is a keyword
	Code blocks are enclosed in //
	Sub-headings are enclosed in /* */
	...means the sentence was cut in half
	The goal of this note format is to minimize punctuation while keeping everything readable and indepth

Chapter 1: UNIX System Overview
-------------------------------

1.1 Intro
---------
All "operating systems" (OS for short) provide services for programs they run (executing, opening, reading, allocating memory, etc.)
	This text describes what services the UNIX operating system provides

1.2 UNIX Architecture
---------------------
"Operating system": The software that controls the hardware resources of a computer and...
	...creates an environment in which all programs can run
	We call this software a "kernel" because its small and resides at the core of the environment

A layer of software known as "system calls" is used to interact with the kernel
"Libraries" containing common functions are built on top of system calls, but "applications" are free to use both
The "shell" is a special application that provides an interface for running other applications
	-------------------------
	|applications			|	Applications can interact with shell,libraries, and system calls
	---------------------	|	
	|shell and libraries|	|	Shell and libraries can interact with system calls
	--------------		|	|
	|system calls|		|	|	System calls can interact with kernel
	|  --------  |-------	|
	|  |kernel|  |			|	Kernel has friends
	|  --------  |			|
	-------------------------
Linux is the kernel used by the GNU operating system
	This combo is often shortened to GNU/Linux operating systems, and further shortened to Linux

In summary, the OS consists of a kernel and other software that interacts with it through some means 
	Other software includes system utilities, applications, shells, libraries, and etc.

1.3 Logging In
--------------
/*Login Name*/
When we log in to a UNIX system, we enter our login name and password
	The system looks for our login name in its password file usually in /etc/password
		The information located in /etc/password is composed of 7 colon seperated fields
			login name:encypted password:numeric user ID: numeric group ID:comments:home directory:shell program
				sar:x:205:105:Stephen Rago:/home/sar:/bin/ksh
/*Shells*/
Once we log in, system info messages or a window management programs are shown, and then we can type our commands into the shell program
	A "shell" is a "command-line" interpreter that reads user input and executes commands
		A "terminal" is an interactive shell (i.e. the user can type into it directly)

Default available shells:
	Name 				Path 		FreeBSD 8.0 	Linux 3.2.0 	MAC OS X 10.6.8 	Solaris 10
	Bourne shell 		/bin/sh 	Yes 		 	Yes				Copy of bash		Yes
	Bourne-Again shell 	/bin/bash   Optional		Yes				Yes 				Yes
	C shell 			/bin/csh 	Link to tcsh	Optional		Link to tcsh 		Yes
	TENEX C shell 		/bin/tcsh 	Optional		Optional		Yes  				Yes
	Korn shell 			/bin/ksh 	Yes				Optional		Yes 				Yes

The OS executes which shell depending on the final field of each entry in the password file
Bourne Shell
	Made by Steve Bourne at Bell Labs
	Used in almost every UNIX system ever
C Shell
	Made by Bill Joy at Berkely
	"BSD": Berkely Software Distribution (Berkely research update to UNIX; BSD now refers to anything that descended from it)
	Used  in every BSD release 
	Built on the 6th Edition shell, not the Bourne shell
	Looks more like C, and does more than the Bourne shell (job control, command-line editing)
Korn Shell
	Made by David Korn at Bell Labs
	"SVR4": System 5 Release 4, the first commerical release of UNIX and the most successful
	Before SVR4, korn shell was was an add-on, afterwards it came with every UNIX
	Successor to Bourne shell, and thus compatible
	Includes features that made the C shell popular (job control, command-line editing)
Bourne-Again Shell
	Provided with all Linux OS
	Designed to be "POSIX" conformant
		"POSIX": The Portable Operating System Interface (standards set by IEEE to make sure all OS are compatible with each other)
	Supports Bourne, C, and Korn shell compatibility
TENEX C Shell
	Better C shell
	Borrows features from the TENEX OS(Made by Bolt Bernanek and Newman in 1972), such as command completion
The idea of a shell was standardized in POSIX 1003.2 standard (at minimum requiring ksh and sh features)

1.4 Files and Directories
-------------------------
/*File System*/
"root" directory is the starting directory, whose name is just /
"Directory": A file that contains "directory entries"
	"Directory Entries": A filename with details about the file, such as:
		Type of the file (regular or directory)
		Size
		The owner
		Permissions (can other users access the file?)
		Last time modified
	Use fstat() or stat() functions to return structure information of a file
/*Filename*/ 
The names in a directory are called "filenames"
	The only two characters that cannot be in a filename are / and the null character (probably NULL)
		We use shell special characters in a filename, but then shit gets complicated 
	Everything else is legal
		a-z, A-Z, 0-9, . , -, and _
Two filenames are automatically made whenever a new directory is created; . and ..
	. ("dot") refers to the current directory 
	..("dot-dot") refers to the parent directory (they are kind of like pointers)
	In the root directory, .. is the same as . (cause root is batman)

/*Pathname*/
A sequence of one or more filenames separated by slashes and optionally starting with a slash forms a "pathname"	
	A pathname that begins with a slash is called an "absolute pathname"
		Root is a special absolute pathname with no filename component

/*Example*/
//This program lists the names of all files in a directory (bare-bones of ls(1))
#include "apue.h"
//#include <dirent.h>
//#include <stdlib.h>
//#include <stdin.h>

int main (int argc, char *argv[]){
	DIR *dp;
	struct dirent *dirp;

	if (argc != 2){
		err_quit("usage: ls directory_name");
	}
	if ((dp = opendir(argv[1])) == NULL) {
		err_sys("can't open %s", argv[1]);
	}
	while ((dirp = readdir(dp)) != NULL){
		printf("%s\n", dirp -> d_name);
	}

	closedir(dp);
	exit(0);
}
//

The notation ls(1), is the normal way to refer to a particular entry in UNIX system manuals
	Refers to the entry for ls() section 1
		Sections are normally numbered 1-8, and sometimes letters for subsections
	To access your manual for ls(1), use "man 1 ls" or "man -s1 ls"
To compile your code, use "cc" 
	"cc" is the standard c compiler
	"gcc" if your machine has the GNU C compilation system (cc will probably link to gcc anyway)
$ is the prompt printed by the shell

Some notes about the code above
	Do not use apue.h in your code (it just contains a bunch of textbook related constants and prototypes, irrelevant)
		err_sys and err_quit are both error functions in apue.h (basically useless for us)
	dirent.h holds function prototypes for opendir and readdir
	Format of directory entries vary between UNIX systems, thats why we have opendir, readdir, and closedir to manipulate the directory
	opendir
		Returns a pointer to a DIR structure
	readdir
		Reads the next directory entry
		Returns a pointer to a DIR structure when unfinished, and a NULL pointer if finished
	exit(0)
		Terminates the program
		0 means program exited ok, 1-255 means an error

/*Working and Home Directories*/
"Working Directory" is the directory we are currently in (i.e. how all relative pathnames are interpreted)
	If we are in potatoes, all directories we try to "cd" into will lowkey be attached to potatoes
		cd frenchfries is actually us moving to potatoes/frenchfries
	Can change directory with the chdir() function
"Home Directory" is the directory our shell always starts in upon logging in

1.5 Input and Output
--------------------
/*File Descriptors*/
"File Descriptors" are normally small non-negative integers that the kernal uses to indentify the files accessed by a process
	The kernel returns a file descriptor whenever a process opens or creates a file, which we use to read or write with

/*File I/O Descriptors*/
By convention all shells open three descriptors whenever a new program is run:
	Standard input
	Standart output
	Standard error
When nothing fancy is done, all three "FD"s are connected to the terminal
	ls
However, it is possible to redirect 1 or more FD
	ls > file.list 
		standard output for the ls command is redirect to the file named file.list

/*Unbuffered I/O*/
Unbuffered I/O is provided by the functions open, read, write, lseek and close
	These functions all need file descriptors

//This program takes in user input through standard input and copies it to standard output
#include <unistd.h>
#include <stdlib.h>
#define BUFFSIZE 4096
int main (void){
	int n;
	char buf[BUFFSIZE];

	while ((n = read(STDIN_FILENO, buf, BUFFSIZE)) > 0){
		if (write(STDOUT_FILENO, buf, n) != n){
			err_sys("write error");
		}
		if (n < 0){
			err_sys("read error");
		}
	}
	exit(0);
}
//

unistd.h header contains the constants "STDIN_FILENO" and "STDOUT_FILENO"
	It also contains a ton of prototypes for UNIX system services, such as read and write
		STDIN_FILENO is the fd for standard input (equal to 0)
		STDOUT_FILENO is the fd for standard output (equal to 1)
BUFFSIZE is a constant size for the array, which should allow the copying of any filename
read() returns the number of bytes read
	For this program, this value is used as the number of bytes to write
	If read returns -1, an error has occured (most system functions do this)
If we compile the program with the standard name (a.out), we can execute, in many ways such as
	./a.out > data
		Redirect output to data
	./a.out < data > datacopy
		Redirect input to data and output to datacopy (essentially copies data to datacopy)

/*Standard I/O*/
Standard I/O functions provide a "buffered" interface to the "unbuffered" I/O functions
	"unbuffered" means there is no buffer for reading or writing (the system reads and writes char by char)
		Generally huge performance loss
	"buffered" means it uses some kind of storage, so instead of dealing with one char, we deal with blocks of char values
Standard IO relieves the need to choose optimal buffer sizes such as BUFFSIZE in the previous example
They also simplify getting lines of input
	fgets() reads an entire line, whereas read() reads a specified number of bytes
For standard IO, you must include stdio.h
	This contains printf and such

//This program copies standard input to standard output
#include <stdio.h>
#include <stdlib.h>

int main(void){
	int c;
	while ((c = getc(stdin)) != EOF){
		if (putc(c, stdout) == EOF){
			err_sys("output error");
		}
		if (ferror(stdin)){
			err_sys("input error");
		}
	}
	exit(0);
}
//

The function getc reads one character a time, and this character is written by putc
	Once the last byte has been read, getc returns the constant EOF (defined in stdio.h)
stdin and stdout are IO constants defined in stdio.h that refer to standard input and standard output respectively

1.6 Programs and Processes
--------------------------
/*Program*/
A "program" is an executable file residing on disk in a directory
	A program is read into memory and executed by the kernel as a result of one of the seven exec functions (7 dragon balls)
		We will cover the 7 deadly sins in chapter 8

/*Processes and Process ID*/
An executing instance of a program is called a "process" or "task"
	Every process has a unique numeric identifier called a "process ID" and is always non-negative

//Prints the process ID
#include <unistd.h>
#include <stdlib.h>

int main(void){
	printf("hello world from process ID %ld\n", (long)getpid());
	exit(0);
}
//

Some sample output if we compiled the program above
	./a.out
		hello world from process ID 679
	./a.out
		hello world from process ID 682
When this program runs, it calls the function getpid() to obtain its process ID
	getpid() returns a pid_t data type (more on this later)
	We do not know the size of the process, but UNIX standards guarantee it will fit in a long int
		Have to cast it to long for printf to prevent errors in the event the pid is a long (although it is usually an int)

/*Process Control*/
There are three primary functions for process control
	fork
	exec (7 variants, but collectively, they will be known as exec)
	waitpid

//This is a bare-bones structure of a shell-like program
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(void){
	char buf[10000]; //arbitrary
	pid_t pid;
	int status;

	printf("%%"); //print prompt (%% is the escape sequence for %)
	while (fgets(buf, 10000, stdin) != NULL){
		if (buf[strlen(buf) - 1] == '\n'){
			buf[strlen(buf) - 1] = 0; //replace newline with null
		}
		if ((pid = fork()) < 0){
			printf("your fork was a spoon!");
		}
		else if (pid == 0){ //if there was a successful child, the child can only perform the following
			execlp(buf, buf, (char *) 0);
			exit(127);
		}

		if ((pid = waitpid(pid, &status, 0)) < 0){
			printf("waitpid error");
		}
		printf("%%");
	}
	exit(0);
}
//

Some notes to consider about the above program
	fgets stops once it gets EOF, and it will return a null pointer	which ends the program
	We replace the newline character at the end of each line with a null character 
		execlp needs a null-terminated argument, not a newline-terminated argument
	Fork creates a new process that is a copy of the caller
		Caller is the parent, new process is the child
		Returns the non-negative process ID of the new child process to the parent
		Returns a 0 to the child
			Fork is called once but returned twice
	In the child, we use execlp to execute the command that was read from standard input
		This replaces the child process with the new program file
			The combination of fork followed by exec is called "spawning a new process" on some OS
		More on these guys in chapter 8

	Because the child calls execlp to execute the new program file, the parent wants to wait for the child to terminate (sad boys)
		This is done by calling waitpid, specifying which process to wait for, which takes in
			A pid argument (the process ID of the child)
			An integer address called status (holds the terminations status of the child)
				This allows us to examine how the child was terminated
			And more stuff (to be discussed later)
	This program does allow the passing in of any arguments to commands executed
		We would need to parse through the string to do this
		We can call ls, but not much more
Control characters such as (^D, or Control-D, which allows us to terminate a process) will be discucssed further in chapter 18

/*Threads and Thread IDs*/
Uusually a process has only one "thread of control"
	One set of machine instructions executing at a time
Some problems are easier to solve with more than one thread of control operating on a different part of the problem
	More threads means we can use the parallelism possible on multiprocessor systems
All threads within a process share the same address space, FDs, stacks, and process related attributes
	Each thread executes on its own stack , although any thread can access the stacks of other running threads
		This requires synchronization
Threads also have thread IDs (use these to call specific threads)
More on all of this in chapter 12

1.7 Error Handling
------------------
When an error occurs in one of the UNIX System functions, a negative value like -1 is returned
	"errno" is usually set to a value that tells why
		An error from open has about 15 possible errno values, such as ones for file does not exist or permission problems
errno.h defines the symbol errno and constants for each value that errno can assume
	Each of these constants begins with the character E
		man intro(2) has all these constants listed
POSIX and ISO C define errno as a symbol expanding into a modifiable lvalue of type integer
	This can be either an integer that contains the error number or a function that returns a pointer to the error number
		Historically this has been
			extern int errno;
But in environments that support threads, each thread needs its own copy of errno 
	Process address space is shared among multiple threads, so we need to prevent other threads from messing up each other 
Two rules to errno
	1. No function or constant ever sets errno to 0
	2. errno is never cleared if an error does not occur (it only changes value when an error happens)
Two C functions that help with printing error messages:

//
#include <string.h>

char * strerror(int errnum);
//

errnum is mapped into an error message string and returns a pointer to the string
	errnum is usually = errno

//
#include <stdio.h>

void perror(const char *msg);
//

Outputs the string pointed to by msg, followed by ; valueOfErrno\n

//Demonstrates strerror and perror
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>

int main (int argc, char *argv[]){
	fprintf(stderr, "EACCES: %s\n" , strerror(EACCES));
	errno = ENOENT;
	perror(argv[0]);
	exit(0);
}
//

If this were compiled, we get
	./a.out
	EACCES: Permission denied
	./a.out: No such file or directory
Some notes
	argv gives us access to the name of the program executed 
	The book uses all of its defined error functions because screw your education

/*Error Recover*/
Two types of errors in errno.h
	Fatal
		No recovery action
		The best we can do is print error messages or log the file and exit
	Nonfatal
		Temporary, such as resource shortage
		Best option is to delay and rety later

1.8 User Idenfitication
-----------------------
User ID
	Numeric value that identifies us to the system
	Assigned by the system administrator, and cannot be changed after logging in
	Normally assigned to be unique for every user
The user with the user ID of 0 is called the root or "superuser"
	The entry in the password file normally has a login name of root
		They get special priviledges, called superuser priviledges
			Most permission checks are bypassed and some special functions are restricted to the superuser
				Basically godmode
Group ID
	Numeric value that identifies us to the system
	Assigned by the system administrator, and cannot be changed logging in
	Normally used to collect users together for projects or deparments
		This can be used to share resources and stuff among specific users
/etc/group is the group file that maps group names into numeric group IDs
 However, user IDs work better with names than numbers, so numers are used to map IDs

//This program prints the user ID and group ID
 	#include <stdio.h>
 	#include <unistd.h>
 	#include <stdlib.h>

 	int main (void){
 		printf("uid = %d, gid = %d\n", getuid(), getgid());
 		exit(0);
 	}
//

 ./a.out
 uid = 711, gid = 911

/*Supplementary Group IDs*/
In addition to the group ID specified in the password file for a login name, users can belong to multiple groups
	This practice started in 4.2BSD (so not all UNIX systems)
These "supplementary group IDs" are obtained at login time by reading the file /etc/group
	They are the first 16 entries that list the user as a member
		(maybe more but at least 16)

1.9 Signals
-----------
"Signals" are a technique used to notify a process that some condition has occured
	E.g. dividing by 0 signals SIGFPE (floating point exception)
Three choices to deal with a signal
	Ignore it
	Have a default action occur (e.g. stop the process)
	Catch it (have specific functions to deal with it)
Many conditions generate signals
	The two terminal keys (interrupt(delete or ctrl-c) and quit(ctrl-\)) interrupt processes
	Kill() function sends a signal as well
You must be the owner of the other process (or superuser) to send it a signal

//Read commands from stdin and execute them
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/wait.h>

static void sig_int(int);	//signal catching function

int main(void){
	char buf[10000]; //arbitrary
	pid_t pid;
	int status;

	if (signal(SIGINT, sig_int) == SIG_ERR){
		printf("signal error!");
	}

	printf("%%"); //print prompt (%% is the escape sequence for %)
	while (fgets(buf, 10000, stdin) != NULL){
		if (buf[strlen(buf) - 1] == '\n'){
			buf[strlen(buf) - 1] = 0; //replace newline with null
		}
		if ((pid = fork()) < 0){
			printf("your fork was a spoon!");
		}
		else if (pid == 0){ //if there was a successful child, the child can only perform the following
			execlp(buf, buf, (char *) 0);
			exit(127);
		}

		if ((pid = waitpid(pid, &status, 0)) < 0){
			printf("waitpid error");
		}
		printf("%%");
	}
	exit(0);
}

void sig_int(int signo){
	printf("Interrrupt\n%% ");
}
//

1.10 Time Values
----------------
UNIX systems have two different time values:
	Calender Time 
		Number of seconds since Epoch
		Epoch is 00:00:00 January 1, 1970, Coordinated Universal Time (UTC)
		The primitive system data type time_t holds these time values
	Process Time (CPU time)
		Measures the central processor resources used by a process 
		This is in clock ticks, which are either 50,60 or 100 ticks per seconds
		The primitive system data type clock_t holds these time values
	The system maintains the following when a process is running:
		Clock time (how long it takes for a process to run)
			This number is given assuming no other process is running (would be lower otherwise)
		User CPU time: CPU time attributed to user instructions
		System CPU time: CPU time attributed to the kernel when it executes on behalf of the process
		The sum of user and system CPU time is known as "CPU Time"

1.11 System Calls and Library Functions
---------------------------------------
System Call: Entry points into the kernel
	For us, these are C functions (process calls a function that invokes a kernel service)
Library functions can be replaced, system calls cannot
If you do not like how memory allocation functions like malloc work, use the system call sbrk(2)
	








