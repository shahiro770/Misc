/* 	
	assign5.c
	Shahir Chowdhury
	Last Modified: 2017-03-29
	This program retrieves information of students from a file and stores them within a linked list sorted by student ID values from least to greatest.
	The information can then be modified by the user and stored inside a file.
*/

//INCLUDES
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

//STRUCTS
struct CourseInfo{ 					//contains information pertaining to a specific course
	int courseID;
	char courseName[30]; 
};
typedef struct CourseInfo CourseInfo;

struct studentInfo{					//contains information regarding the student and their courseload
	char studentID[11];
	char firstName[21];
	char lastName[26];
	int courseNum;				//number of courses student is attending
	CourseInfo courses[10];
	struct studentInfo *next;
};
typedef struct studentInfo StudentInfo;

//PROTOTYPES
int getChoice();													//gets the choice of the user
StudentInfo *addStudent (StudentInfo *List);						//adds a student to the linked list
StudentInfo *deleteStudent(StudentInfo *List);						//deletes a student from the linked list
StudentInfo *searchStudentID(StudentInfo *List, char targetID[10]);	//returns a student in the linked list based on ID
void searchStudentlName(StudentInfo *List);							//searches for a student in the linked list based on name
void displayStudentInfo(StudentInfo *List);							//displays all student information stored in the linked list
void saveStudentInfo(StudentInfo *List, char *fileName);			//saves all student information to a file
StudentInfo *loadStudentinfo(char *fileName);						//loads student information from a file
void capitalizeFirst(char *str);									//capitalizes the first letter of a word
void myExit(StudentInfo *List, char *fileName);						//exits the program

//MAIN
int main(){
    int choice = 0;
    char *fileName = "studentList.txt";
    StudentInfo *studList = loadStudentinfo(fileName);

    while (choice >= 0){
    	choice = getChoice();

    	switch (choice){
    		case 1:
    			studList = addStudent(studList);
    			break;
    		case 2:
    			studList = deleteStudent(studList);
    			break;
    		case 3:
    			searchStudentlName(studList);
    			break;
    		case 4:
    			displayStudentInfo(studList);
    			break;
    		case 5:
    			saveStudentInfo(studList, fileName);
    			break;
    		case 6:
    			myExit(studList, fileName);
    			break;
    		default: 
    			printf("That is not a valid choice\n");
    	}
    }
    if (studList){
    	free(studList);
    } 

    return 0;
}

//FUNCTION DEFINITIONS
/* 
	Objective: Display all options the user has to manipulate the course information
	Input: User is assumed to input an integer that corresponds to an option on the menu
	Output: Returns the integer the user picked
*/
int getChoice() {
	int choice;

	printf("\n------------SELECT AN OPTION------------\n\n");
	printf("1) Add new student\n");
	printf("2) Delete a student\n");
	printf("3) Search for a student\n");
	printf("4) Display current students\n");
	printf("5) Save student information to file\n");
	printf("6) Exit\n\n");
	printf("------------SELECT AN OPTION------------\n");

	scanf("%d", &choice);
    return choice; 
}

/* 
	Objective: Adds a student to the linked list of students while maintaining the list's sorted order
	Input: List is a pointer to the first node in the linked list (the head)
		   The user is assumed to know the specifications regarding the size of the information (student ID is expected to be 9 characters long,
		   first name is no longer than 20 letters, last name is no longer than 25 letters, and a student has no more than 10 courses)
	Output: Returns a pointer to the head of the linked list
*/
StudentInfo *addStudent (StudentInfo *List){
    StudentInfo *temp;			//pointer to position where new node will be added to the linked list
    StudentInfo *head = List;	//pointer to first node in linked list
    StudentInfo *prev = List;	//pointer to StudentInfo node preceding the position where the new node will be added

    char tempID[10];			//ID of the new student
	char tempFirst[21];			//first name of the new student
	char tempLast[26];			//last name of the new student
	int tempNum;				//number of courses the new student is taking
	CourseInfo tempCourses[10];	//array containining the details of each 

	int tempCourseID;			//course ID
	char tempCourseName[30];	//course name

    printf("Adding new student:\n");
    printf("Student ID: ");
    scanf("%s", tempID);
    printf("First name: ");
    scanf("%s", tempFirst);
    printf("Last name: ");
    scanf("%s", tempLast);
    printf("Number of courses: ");
    scanf("%d", &tempNum);

    for (int i=0;i<tempNum;i++){
    	printf("Course ID: ");
    	scanf("%d", &tempCourseID);
    	printf("Course Name: ");
    	scanf("%s", tempCourseName);

    	(tempCourses + i) -> courseID = tempCourseID;
    	strcpy((tempCourses + i) -> courseName, tempCourseName); 
    }

    temp = malloc(sizeof(StudentInfo));
   	strcpy(temp -> studentID, tempID);
    strcpy(temp -> firstName, tempFirst);
    strcpy(temsdp -> lastName, tempLast);
    temp -> courseNum = tempNum;
    for (int i = 0;i < tempNum;i++){
    	(((temp -> courses) + i) -> courseID) = ((tempCourses + i) -> courseID);
		strcpy((((temp -> courses) + i) -> courseName), ((tempCourses + i) -> courseName));
    }

  	capitalizeFirst(temp -> firstName);
  	capitalizeFirst(temp -> lastName);

    if (List == NULL){											//add new node to the start of the list if the list is empty
        temp -> next = NULL;
        head = temp;
    }

	else if ((strcmp(tempID, (List -> studentID))) < 0){		//add new node to the start of the list, having it precede the first element
       															//if it has a student ID value that is less than the first element's ID value

        if (strcmp(temp -> studentID, List -> studentID) == 0){	//ignore duplicates
			printf("Duplicate student ID ignored");
			return head;
		}

    	temp -> next = List;
    	head = temp;
    }

    else{												
        head = List;

        while ((List -> next) != NULL){
        	if (strcmp(tempID, (List -> next) -> studentID) < 0){
	        	prev = List;
	        	break;
        	} 
        	else{
        		prev = List;
	        	List = List -> next;
        	} 
        }

        if (strcmp(temp -> studentID, List -> studentID) == 0){	//ignore duplicates
			printf("Duplicate student ID ignored");
			return head;
		}

        if ((List -> next) != NULL){							//add new node in the middle of the list
        	(temp -> next) = (prev -> next);
        	(prev -> next) = temp;
        }   

        else if ((List -> next) == NULL){						//add new node at the end of the list
        	(temp -> next) = NULL;
        	(List -> next) = temp;
        } 
    }
    return head;
} 

/* 
	Objective: Delete a student from the linked list while maintaining the list's sorted order
	Input: List is a pointer to the head of the linked list
	Output: Returns a pointer to the head of the linked list
*/
StudentInfo *deleteStudent(StudentInfo *List){
    StudentInfo *head = List;	//pointer to first node in linked list
    StudentInfo *prev = List;	//pointer to StudentInfo node preceding the position where the new node will be added
	char targetID[10];			//ID to be deleted

	printf("Student ID: ");
	scanf("%s", targetID);

	if (List == NULL){	
		printf("The list is empty!\n");
	}
	else if (strcmp(targetID, (List -> studentID)) == 0){		//deleting the first node in the linked list
		head = List -> next;
		free (List);
		printf("Student information deleted\n");
	}
	else{														//deleting any node after the first node
		 while (List != NULL){
        	if (strcmp(targetID, (List) -> studentID) == 0){	   
	        	break;
        	}
        	else{
        		prev = List;
	        	List = List -> next;
        	} 
        }
        if (List == NULL){										//node with target ID was not found
        	printf("That student ID could not be found\n");
        	return head;
        }
        else {													//node was found
        	(prev -> next) = (List -> next);
        	printf("Student information deleted\n");
        	free (List);    	
        }
	}

	return head;
}

/* 
	Objective: Searches for a student with a given student ID in the linked list
	Input: List is a pointer to the head of the linked list
	Output: Returns a pointer to the node with the desired student ID, NULL if not found
*/
StudentInfo *searchStudentID(StudentInfo *List, char targetID[10]){
	StudentInfo *head = List;

	while ((List != NULL) && (strcmp(List -> studentID, targetID) >= 0)){	//stop early if it is impossible to find the ID
		if (strcmp(targetID, (List) -> studentID) == 0){
			return List;
		}
		else{
			List = List -> next;
		}
	}

	List = head;
	return NULL;
}

/* 
	Objective: Search for a student in the linked list based on last name
	Input: List is a pointer to the head of the linked list
		   Inputted last name is expected to be no more than 25 characters long
		   Assumes only one person with the given last name is stored in the linked list (otherwise, will only take the first occurence)
	Output: Prints all personal and course information pertaining to that student
*/
void searchStudentlName(StudentInfo *List){
	char targetName[26];
	StudentInfo *head = List;

	printf("What is the name of the last student? ");
	scanf("%s", targetName);

	capitalizeFirst(targetName);

	while (List != NULL){
		if (strcmp(targetName, (List) -> lastName) == 0){
			break;
		}
		else{
			List = List -> next;
		}
	}
	if (List != NULL){
        printf("%s\n%s\n%s\n%d\n", List -> studentID, List -> firstName, List -> lastName, List -> courseNum);
        for (int i = 0;i < List -> courseNum;i++){
        	printf("%s %d\n", ((List -> courses) + i) -> courseName, ((List -> courses) + i) -> courseID);
        }
	}
	else{
		printf("A student by that name could not be found\n");
	}

	List = head;
	return;
}

/* 
	Objective: Display all information pertaining to all students in the linked list
	Input: List is a pointer to the head of the linked list
	Output: Prints each student's information
*/
void displayStudentInfo(StudentInfo *List){
	int currStud = 1;	//current student in list 
	StudentInfo *head = List;

	if (List != NULL){
        while (List != NULL){
        	printf("\nStudent %d:\n", currStud);
            printf("%s\n%s\n%s\n%d\n", List -> studentID, List -> firstName, List -> lastName, List -> courseNum);
            for (int i = 0;i < List -> courseNum;i++){
            	printf("%s %d\n", ((List -> courses) + i) -> courseName, ((List -> courses) + i) -> courseID);
            }

            List = List -> next;
            currStud++;
        }
    }
    else{
        printf("The list is empty!\n");
    }

    List = head;
	return;
}

/* 
	Objective: Saves all information contained in the linked list to a text file
	Input: List is a pointer to the head of the linked list
		   filename is assumed to be a valid string 
	Output: Saves all information contained in the linked list while maintaining their sorted order
*/
void saveStudentInfo(StudentInfo *List, char *fileName){
	FILE *fPtr = fopen(fileName, "w");
	StudentInfo *head = List;

	while (List != NULL){
		fprintf(fPtr, "%s\n%s\n%s\n%d\n", List -> studentID, List -> firstName, List -> lastName, List -> courseNum);
		for(int i = 0;i < List -> courseNum;i++){
			fprintf(fPtr, "%s %d\n", ((List -> courses) + i) -> courseName, ((List -> courses) + i) -> courseID);
		}
		List = List -> next;
	}
	fprintf(fPtr, "***");
	fclose(fPtr);

	List = head;
	return;
}

/* 
	Objective: Loads student information from a text file and saves it to a linked list
	Input: fileName is assumed to be the name of a file contained in the same directory as this program
	Output: Returns a pointer to the head of the linked list where all the information is saved
*/
StudentInfo *loadStudentinfo(char *fileName){
	FILE *fPtr = fopen(fileName, "r");

	StudentInfo *head;
	StudentInfo *List = NULL;

	while(!feof(fPtr)){
		char tempID[10];
		char tempFirst[21];
		char tempLast[26];
		int tempNum;				
		CourseInfo tempCourses[10];

		int tempCourseID;
		char tempCourseName[30];

		StudentInfo *temp = malloc(sizeof(StudentInfo));
		fscanf(fPtr, "%s", tempID);

		if(strcmp(tempID, "***") == 0){			//*** is the signal to stop reading from the file
			break;
		} 
		fscanf(fPtr, "%s\n%s\n%d\n", tempFirst, tempLast, &tempNum);
		for(int i = 0;i < tempNum;i++){
			fscanf(fPtr, "%s %d\n",  tempCourseName, &tempCourseID);	

			strcpy(((tempCourses + i) -> courseName), tempCourseName);
			((tempCourses + i) -> courseID) = tempCourseID;
		}

		strcpy(temp -> studentID, tempID);
		strcpy(temp -> firstName, tempFirst);
		strcpy(temp -> lastName, tempLast);
		temp -> courseNum = tempNum;
		for (int i = 0;i < tempNum;i++){
			
	    	(((temp -> courses) + i) -> courseID) = ((tempCourses + i) -> courseID);
			strcpy((((temp -> courses) + i) -> courseName), ((tempCourses + i) -> courseName));
   		}

		capitalizeFirst(temp -> firstName);
  		capitalizeFirst(temp -> lastName);

		if(List == NULL){		
			head = temp;
			List = temp;
		}
		else{
			List -> next = temp;
			List = List -> next;
		}
	}

	fclose(fPtr);

	return head;
}

/* 
	Objective: Capitalizes the first letter of a word
	Input: str is assumed to be a valid string
	Output: Capitalizes the first letter of a word
*/
void capitalizeFirst(char *str){
	str[0] = toupper(str[0]);
}

/* 
	Objective: Exits the program, asking the user if they would like to save any changes they have made
	Input: List is a pointer to the head of the linked list
		   fileName is assumed to be a valid string
	Output: Exits the program and saves changes made to the linked list depending on user's input
*/
void myExit(StudentInfo *List, char *fileName){
	char answer[10];

	printf("Save student information to file before leaving?(y/n) ");
	scanf("%s", answer);
	if (strcmp(answer, "y") == 0){
		saveStudentInfo(List, fileName);
		printf("Student List saved successfully.\nBye!");
		exit(0);
	}
	else{
		printf("Bye!");
	}
	exit(0);

	return;
}



	


	


