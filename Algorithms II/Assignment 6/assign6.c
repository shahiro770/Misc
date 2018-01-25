/* 	
	assign6.c
	Shahir Chowdhury
	Date last modified: 2017-04-05
	This program reads the grade information of a set of students and creates a file with each student's GPA whilst placing
	the student information in ascending order in accordance with their student ID.  
*/

//INCLUDES
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//STRUCTS
struct StudentGrades{ 					//contains information pertaining to a student's grades and GPA
	int studentID;						//assumed to be 6 digits
	float courseMarks[10];				//values in all 10 positions are assumed to be between 0.0 and 100.0
	float GPA; 							//GPA is calculated as the average of all marks scaled to be between 0.0 and 4.0
};
typedef struct StudentGrades StudGrades;

//PROTOTYPES
int getEntryNum(char *filename);		//gets the number of records in the input file
float getGPA(StudGrades stud);			//gets the GPA of a given student

//MAIN
int main (void){
	FILE *fptrIn, *fptrOut, *fptrResults;

	int recNum;			//number of records in input file
	int bytesIn = 0;	//size in bytes of the input file
	int bytesOut = 0;	//size in bytes of the output file

	char *inputName = "assign6.dat";			//input file is assumed to exist 
	char *outputName = "assign6out.dat";		//output file
	char *resultsName = "assign6results.dat";	//results file
	char *tokenPtr;

	StudGrades temp;	//StudGrades structure to hold information during analysis
	StudGrades curr;	//StudGrades structure to hold information during sorting
	StudGrades comp;	//StudGrades structure to hold information during sorting

	recNum = getEntryNum(inputName);

	fptrIn = fopen(inputName, "r");
	fptrOut = fopen(outputName , "w+");
	fptrResults = fopen (resultsName , "w");

	fseek(fptrIn, 0, SEEK_END);
	bytesIn = ftell(fptrIn);	
	fseek(fptrIn, 0, SEEK_SET);
	
	char currstr[1000];						//arbitrarily large string, assumes any line in the input won't exceed 1000 characters
	memset(currstr, 0, sizeof(currstr));

	for (int i=0;i<recNum;i++){				//extract the contents of the input file and store them in an output file
		fgets(currstr,sizeof(currstr), fptrIn);

		tokenPtr = strtok(currstr, "\n");
		while (tokenPtr != NULL){
			sscanf(currstr, "%d %f %f %f %f %f %f %f %f %f %f", &(temp.studentID), 
			&(temp.courseMarks[0]), &(temp.courseMarks[1]), &(temp.courseMarks[2]),
			&(temp.courseMarks[3]), &(temp.courseMarks[4]), &(temp.courseMarks[5]),
			&(temp.courseMarks[6]), &(temp.courseMarks[7]), &(temp.courseMarks[8]),
			&(temp.courseMarks[9]));
			tokenPtr = strtok(NULL, "\n");
		}
		temp.GPA = getGPA(temp);
		
		fwrite(&temp, sizeof(temp), 1, fptrOut);
		bytesOut += sizeof(temp);
	}

	fseek(fptrOut, 0, SEEK_SET);

	for (int i=0;i<recNum;i++) {									//perform an in place bubble sort of the contents of the output file
     	for (int j=0;j<(recNum - 1);j++){
     		fseek(fptrOut, j * sizeof(StudGrades), SEEK_SET);
     		fread(&curr, sizeof(StudGrades), 1 ,fptrOut);
     		fread(&comp, sizeof(StudGrades), 1 ,fptrOut);
     		if (curr.studentID > comp.studentID){
     			fseek(fptrOut, j * sizeof(StudGrades), SEEK_SET);
     			fwrite(&comp, sizeof(StudGrades), 1, fptrOut);
     			fwrite(&curr, sizeof(StudGrades), 1, fptrOut);
     		}
     	}
    }

    fseek(fptrOut, 0, SEEK_SET);									//write the sorted student grade information to the results file,
    for (int i=0; i< recNum;i++){									//including the size of the input and output file
	    fread(&temp, sizeof(temp), 1, fptrOut);
	    fprintf(fptrResults, "%06d %.1f %.1f %.1f %.1f %.1f %.1f %.1f %.1f %.1f %.1f %.1f\n", temp.studentID, 
			temp.courseMarks[0], temp.courseMarks[1], temp.courseMarks[2],
			temp.courseMarks[3], temp.courseMarks[4], temp.courseMarks[5],
			temp.courseMarks[6], temp.courseMarks[7], temp.courseMarks[8],
			temp.courseMarks[9], temp.GPA);
    }
    fprintf(fptrResults, "%d\n%d", bytesIn, bytesOut);

    fclose(fptrIn);
    fclose(fptrOut);
    fclose(fptrResults);

}

//FUNCTION DEFINITIONS
/* 
	Objective: Finds the number of entries within the file containing student information
	Input: filename is assumed to be a valid file containing the required information
		   A newline character '\n' is assumed to be at the end of every line except for the final line
	Output: Gets the number of student grade information entries in the file
*/
int getEntryNum(char *filename){
	int entryNum = 0;
	int ch = 0;
	FILE *fptr;

	fptr = fopen(filename, "r");
	if (fptr == NULL){	//if file is empty
		return entryNum;
	}

	while (!feof(fptr)){
		ch = fgetc(fptr);
		if (ch == '\n'){
			entryNum++;
		}
	}

	/*Last line is ignored due to not ending with a newline character.
	Assuming newline characters are the signal for a succeeding record, 
	check if the file was empty at the start. If not empty, assume there is at least one more line of information.*/
	fseek(fptr, 0, SEEK_SET);
	if ((fptr != NULL) && (entryNum > 0)){		
		entryNum++;
	}
	else if ((fptr != NULL) && (entryNum == 0)){
		entryNum++;
	}	
	fclose(fptr);

	return entryNum;
}

/* 
	Objective: Get the GPA of the student 
	Input: stud is assumed to have float values between 0.0 and 100.0 in all 10 of their courses
	Output: Returns the GPA of the student
*/
float getGPA(StudGrades stud){
	int tot = 0;
	float GPA; 
	for (int i=0;i<10;i++){
		tot += stud.courseMarks[i];
	}

	GPA = (tot/10) * 0.04;	//cool math

	return GPA;
}