/* Files as Containers and File Processing
	
	 Notes about files
---------------------

	-A long piece of magnetic tape with bits

	Sequential Access File:
	
											|	Filesname 	|
	Begining								|101010101010101|		
	----------------------------------------------------------------------------------------------
															|Filepointer|			|File Lengths|
															|	VARIABLE/FIXED LENGTH RECORDS	 |

	//Offset is positive going from beginning of file
		//Offset is negative going from end of file

	-All of these records are stored in a FILE (a struct) that stores all this information in a structure known as a FAT (File Allocation Table)
	-Two techniques we'll learn: 1) Sequential and 2) Direct Access 
		1) Data is offset from the beginning, meaning you have to search through a sequence of data (one after another) until you find your data 
		2) Fixed length allows you to compute the offset of where the record is located (read from where you need to be),
	
	Storage Devices
	-----------------
	- John Von Neumann (Noy Man) first expressed the architecture of the STORED PROGRAM DIGITAL COMPUTER

	- Most of our previous discussions have been centred on how the C language supports dealing with data in memory (RAM)
		-How to declare the reference variables in a program (and the actual data at run time)
		-Expression of data in character string format (human centred)
			-Data Types
			-Variables
			-Aggregate data structures (e.g. arrays, structs, unions, bit strings)
		-Conc


	
	Concept of File
	---------------
	-The concept of a file derives from its use in business, government and other areas
		-A folder containing multiple pieces of paper (or tape, film etc), called records, containing information presented in differing ways
	- A digital file retains real life conceptual characteristics
		-Aggregates of data of differeing data types and representations
		-Requires standardized structures for packaging and communicating data
	-Files devices are any suitable hardware that supports file processing techniques
		-stdin and stdout utilize default devices as does stderr
		-Each of the stdin/stdout/stderr is actaully a pointer to a struct
		-File processing is implemented through the operating system (O/S) as an intermediator
		-Processing functions include opening, closing, seeking, reading, writing...
	-Access techniques to files fall into two general categories
		-Sequential access - usually variable length records
		-Direct access - must be fixed length records	** DO LAB 8 TONIGHT **

	File Streams and Buffers (briefly)
	----------------------------------
	|	Q/S
	|	
	|---------
	|	API 	//Application Program Interface => e.g. printer instructions on a cd
	|---------
	|	I/O 	
	|	Buffers
	|-----------
	|	User Program
	|	Executable logic
	|	Variables, Structures
	|-------------------
	|
	
	-How it works (e.g. how scanf works):
		1)Program - send YourFile data transaction message to O/S
		2)O/S - point to device API, allocate I/O buffer
		3)O/S send protocol wrapped message to device
		4)Device - respond with message directed to proper I/O buffer
		5)O/S - move message to program buffer(s)

	-The cost of I/O:
		-Typical input or output operations on most devices require 1/1000's of seconds to complete.
		-This is thousands, to millions of times slower than memory or cpu based operations.
		-Complicated file access scehemes (organizations and algorithms) are always being developed to speed up programs and reduce their access times

	Making and Breaking File Connections
	------------------------------------

	-When a program is loaded into RAM, the O/S is provided with information about the default file system (stdin and stdout) to be
	used and also whether additional files on storage devices will be needed
		-Note that stdin normally points at the keyboard, while stdout points at the monitor
		-These can be modified to refer to specific files, using file redirection */
				cmdline% a.out < infile.dat > Outfile.dat
	/* 	In order to communicate with a file it is necessary, first, to open a channel to the device where the file
		is located (or will be located, once created). When the program is finished with the file, it is necessary to close
		the channel. All required functions are defined in <stdio.h>
			-All required information concerning the file attributes (characteristics) is contained in a C-defined data 
			structure called FILE */
				FILE *filePtr; //pointer to struct that will hold file attributes
	/*		-There can be many files opened at the same time, each using its own file structure and file pointer
		
		File Control Block (FCB)
		File Name String
		File Offset (Bytes)
		Access Mode (R, W, B, +)

	-In order to communicate with a file it is necessary, first, to open a channel to the device where the file is located
	(or will be located once created). When the program is finished with the file, it is necessary to close the channel.
		-Channels may be re-opened and closed, multiple times
		-A FILE pointer may be re-assigned to different files
	-Assuming the declaration: */
			FILE *cfPtr1, *cfPtr2; 	//declare two C file pointers
		//To open a file channel
			cfPtr1 = fopen("MyNewFileName.dat", "w"); 	//open for writing
			cfPtr2 = fopen("MyOldFileName.dat", "r");	//open for reading

		//To close a file channel
			fclose (cfPtr1);
			fclose (cfPtr2);
	//Every file contains an end-of-file indicator that the O/S can detect and report. This is shown with an example
			while (!feof(cfPtr1)) printf("More data to deal with \n");
		//feof = file end of file function
		//Ctrl d in unix/linux will exit a file
		//Crtl z in windows machines will exit a file (slightly more flexible)

	//In the previous slide we saw the statements 
	cfPtr1 = fopen("MyNewFileName.dat", "w"); 	//open for writing
	cfPtr2 = fopen("MyOldFileName.dat", "r");	//open for reading

	//E.g. Writing a sequential file
		fprintf (cfPtr, FormatString[, Parameter list]);

		fprintf (cfPtr, "%d%1f\n", int Sum, float Ave);
		fprintf (cfPtr, "This is a message string, no values\n");

	//E.g. Reading from a sequential file 
		fscanf (cfPtr, "%d%1f", &intSum, &floatAve);
		fscanf (cfPtr, "%s", stringVar);

	//Interpreting return values
		fopen 	//NULL means "no file exists"
		fprintf //returns number of parameters outputted, or failure of operation, use it to check what crashes your program
		fscanf //returns the number of parameters inputted, or failure of operation
		feof //returns 0 if EOF is not found, otherwise non-zero, do not test for 1, only zero or greater than 0

/*	There are two ways of re-reading a sequential file
		1) close the file and then re-open it
			-considered quite inefficient because computers are busy and will queue up your close and re-open
		2) rewind the file to the beginning (reset the file offset value in the FCB) while leaving it open */
				rewind (cfPtr);

	/* Before moving on it should be noted that most files that contain character based data alone have variable record length

/*	Direct Access Techniques are also called Random Access techniques
		Two kinds of movement for the Read/Write Head:
			1) Linear, Across the disk along a radius
			2) Rotary, Around the disk, within a circular track
		Each kind requires different timing and control, but both contribute to access time. */

	cfPtr1 = fopen ("MyNewFileName.dat", "wb"); //open for writing
	cfPtr2 = fopen ("MyOldFileName.dat", "rb"); //open for reading

	/* C supports three types of fixed length file transactions, called binary modes 
		1) read binary 		rb+
		2) write binary	 	wb+
		3) append binary	ab+ 

		The term binary refers to a bit-level machine representation of data (ie. not characters, necessarily - recall signed binary
		Ex. unsigned and signed binary, IEEE float and double, etc.
			-We use binary because it is compact and versatile (in machine representations every float, double, and int is 4 bytes)*/

	fwrite (&DataStruct, sizeof (DS_t), NumRecs, cfPtr);
	fread (&DataStruct, sizeof (DS_t), NumRecs, cfPtr);

	//Seeking a record in a direct access file
	(int) fseek(FILE *cfPtr, long int Offset, int Whence);	//just to screw around with you
			//Offset just refers to sizeof (DS_t)
			//Whence is one of the three standard values (Defined in <stdio.h>)
				//SEEK_SET - seek based on offset from beginning of file
				//SEEK_CUR - seek based on relative offset from current file position
				//SEEK_END - seek based on offset from end of file

	/* Checking for errors
			fwrite
				Returns the number of items outputted. If this number is less than the 3d argument, then an error has occured*/
				fwrite (&DataStruct, sizeof (DS_t), NumRecs, cfPtr);
	/*		fread
				Returns th enumber of data items successfully inputted, or EOF

			fseek
				Returns a non-zero value if the seek cannot be performed correctly

	Some additional problems to consider:
		- sort a file by a special value (called a key)
		- Merge two files into a single file, maintaing sorted order?
		- Store blcoks of memory (RAM) to a file, then recover it later into memory (concept of virtual memory management)
		- Develop a hierarchial technique for accessing files based on organization patterns
			-Ex. Indexed sequential Access Techniques










