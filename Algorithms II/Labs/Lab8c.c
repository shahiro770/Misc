/* 	
	Lab8c.c
	Shahir Chowdhury
	2017-03-21
	This program modifies the top secret information of employees
*/

//INCLUDES
#include <stdio.h>
#include <string.h>

//PROTOTYPES
struct employee {
     char firstname[40];
     char lastname[40];
     int id;
     float GPA;
};
typedef struct employee Employee;


/* Input the employee data interactively from the keyboard */
void InputEmpRecord(Employee *EmpList);
/* Display the contents of Employee records from the list */
void PrintEmpList(const Employee *EmpList);
/* Save the employee records from the list to the newly created
binary file specified by FileName */
void SaveEmpList(const Employee *EmpList, const char *FileName);

//MAIN
int main() {
     Employee EmpList[3];
     InputEmpRecord(EmpList);
     PrintEmpList(EmpList);
     SaveEmpList(EmpList, "employeeDA.dat");

     return 0;
}

/* 
	Objective: Takes the inputted records of employees 
	Input: Emplist is assumed to contain the records of 3 employees
	Output: Stores the records inside a list
*/
void InputEmpRecord(Employee *EmpList){
	char first[40] = {"\0"};
	char last[40] = {"\0"};;
	int id = 0;
	float GPA = 0;

	for (int i=0;i<3;i++){
		printf("Enter the employee #%d's information (ID First_Name Last_Name GPA): ", i + 1);
		scanf("%d %s %s %f", &id, first, last, &GPA);

		EmpList[i].id = id;
		strcpy(EmpList[i].firstname, first);
		strcpy(EmpList[i].lastname, last);
		EmpList[i].GPA = GPA; 
	}

	return;
}

//FUNCTIONS
/* 
	Objective: Prints the contents of the employee list
	Input: Emplist is assumed to contain the records of 3 employees
	Output: Displays the information of each employee
*/
void PrintEmpList(const Employee *EmpList){
	for (int i=0;i<3;i++){
		printf("%d %s %s %f\n", EmpList[i].id, EmpList[i].firstname, EmpList[i].lastname, EmpList[i].GPA);
	}
}

/* 
	Objective: Saves the contents of the employee list to a direct access file
	Input: Emplist is assumed to contain the records of 3 employees
	Output: Outputs an "employeeDA.dat" file
*/
void SaveEmpList(const Employee *EmpList, const char *FileName){
	FILE *out;

	out = fopen(FileName, "w");
	fwrite(EmpList, sizeof(Employee), 3,  out);

	fclose(out);
}