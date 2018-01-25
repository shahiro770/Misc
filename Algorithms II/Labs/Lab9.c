/* 	
	Lab9.c
	Shahir Chowdhury
	2017-03-27
	This program manipulates dynamically allocated data structures such as linked lists

    Final Exam Locations:   CE 1101
                            CE 1102
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct student {
   int ID;
   char name[40];
   struct student *next;
};
typedef struct student Student;

int getChoice();
Student *addToList(Student *List);
void printList(Student *List);
void printListRR(Student *List);
void searchList(Student *List);

int main() {
    int choice = 0;
    Student *SLIST = NULL;

    choice = getChoice();

    while(choice >= 0) {
        switch(choice) {
            case 0 : printf("Bye...\n"); exit(0);
            case 1 : SLIST = addToList(SLIST); break;
            case 2 : printList(SLIST); break;
            case 3 : printListRR(SLIST); break;
            case 4 : searchList(SLIST); break;
            default: printf("That is not a valid choice\n");
        }
        choice = getChoice();
    }

    if(SLIST) free(SLIST);
    return 0;
}

int getChoice() {
    int choice = 0;

    printf("\n****** MENU ******\n");
    printf("1. Add new student to list.\n");
    printf("2. Print the student list, beginning to end.\n");
    printf("3. Recursively print the student list from the end.\n");
    printf("4. Search the list for a student.\n");
    printf("0. Quit.\n");
    printf("\nEnter your choice: ");
    scanf("%d", &choice);

    return choice;
}

/* Define your functions below */
/* 
	Objective: Display all courses and the information that pertains to them in a tabular format
	Input: courselist is a list of courseInfo structs
		   len is the number of courses within the array
	Output: Prints each course's information
*/
Student *addToList(Student *List){
    Student *temp;
    Student *head = List;

    int tempID;
    char tempName[40];
    printf("What is the ID and name of the new student? (ID name)\n");
    scanf("%d %s", &tempID, tempName);

    if (List == NULL){
        List = malloc(sizeof(Student));

        List -> next = NULL;
        List -> ID = tempID;
        strcpy(List -> name, tempName);

        head = List;
    }

    else{
        head = List;

        temp = malloc(sizeof(Student));
        while ((List -> next) != NULL){
            List = List -> next; 
        }

        temp -> ID = tempID;
        strcpy((temp -> name), tempName);
        temp -> next = NULL;

        List -> next = temp;
    }

    return head;
}

/* 
	Objective: Display all courses and the information that pertains to them in a tabular format
	Input: courselist is a list of courseInfo structs
		   len is the number of courses within the array
	Output: Prints each course's information
*/
void printList(Student *List){
    if (List != NULL){
        while (List != NULL){
            printf("%d %s\n", List -> ID, List -> name);
            List = List -> next;
        }
    }
    else{
        printf("The list is empty!\n");
    }

    return;
}

/* 
	Objective: Display all courses and the information that pertains to them in a tabular format
	Input: courselist is a list of courseInfo structs
		   len is the number of courses within the array
	Output: Prints each course's information
*/
void printListRR(Student *List){
    if (List != NULL){
        if ((List -> next) != NULL){
            printListRR(List -> next);
        }
        printf("%d %s\n", List -> ID, List -> name);
    }
    else{
        printf("The list is empty!\n");
    }

    return;
}

/* 
	Objective: Display all courses and the information that pertains to them in a tabular format
	Input: courselist is a list of courseInfo structs
		   len is the number of courses within the array
	Output: Prints each course's information
*/
void searchList(Student *List){
    int searchID;

    printf("What is the student's ID? ");
    scanf("%d", &searchID);


    if (List == NULL){
        printf("The list is empty!\n");
    }
    else{
        while (List != NULL){
            if ((List -> ID) == searchID){
                printf("%d %s\n", List -> ID, List -> name);
                return;
            }    
            List = List -> next;
        }
        printf("ID not found\n");
        return;
    }

}
