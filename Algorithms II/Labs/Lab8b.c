/* 	
	Lab8b.c
	Shahir Chowdhury
	2017-03-21
	This program modifies the top secret information of employees by capitalizing the first letter of their first and last names 
*/

//INCLUDES
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

//PROTOTYPES
struct employee {
	char firstname[40];
	char lastname[40];
	int id;
	int GPA; 
};
typedef struct employee Employee;

void WordCap(char *word); 	//capitalizes the first letter of the inputted word

//MAIN
int main(void) {

	Employee EmployeeList[3];

	FILE *in, *out;

	in = fopen("employee.dat", "r");
	for (int i=0;i<3;i++){
		fscanf(in, "%d %s %s %d\n", &EmployeeList[i].id, EmployeeList[i].firstname, EmployeeList[i].lastname, &EmployeeList[i].GPA);
	}
	fclose(in);

	out = fopen("employee.dat", "w+");
	for (int i=0;i<3;i++){
		WordCap(EmployeeList[i].firstname);
		WordCap(EmployeeList[i].lastname);
		fprintf(in, "%d %s %s %d\n", EmployeeList[i].id, EmployeeList[i].firstname, EmployeeList[i].lastname, EmployeeList[i].GPA);
	}
	fclose(out);

	return 0;
}

//FUNCTION
/* 
	Objective: Capitalizes the first letter of a word
	Input: word is assumed to be a valid string
	Output: Capitalizes the first letter of a word
*/
void WordCap(char *word){
	*word = toupper(*word);

	return;
}

