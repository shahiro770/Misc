/*Referencing Addresses, Pointers

Concept of Pointer as Memory Address
-------------------------------------

1) All programs exist in RAM when executing 
	- Data and Code
2) Variables and data structures (e.g. arrays) are allocated memory locations (addresses)
	- In static blocks, in stack frames, on the heap
3) It is often useful to exploit knowledge of data structures along with location, or address, data
	- It is the ability to define a variable that stores 

- RAM is partitioned (subdivided) int blocks 

| Code Address Block
|
| Data Block Address
|	int intVarName
|	float fltVarName
|
| Heap Block Address
|
| Stack Block Address
|
| Buffer Block Address
|

-For programmer defined variables, names are used 
	int intVarname = 64;
	float fltVarName = 3.14

Pointer Definitions
-------------------

- C defines two pointer related operators
	- * dereferencing (asterisk)
	- & address of (ampersand)
	- ALL POINTER TYPES ARE UNIQUE (FLOAT POINTER CAN'T POINT TO INT POINTER)

- E.g. variable name for an integer storage with value 5
	int varname = 5;
- E.g. Variable name for a pointer-to-integer storage
	with value set to the RAM address of varname
	int* ptrVarname = &varname
- Declarations
	int N, *ptrN; //N is an int variable, ptrN is a pointer, can be on the same line
	double *X,*Y //both are pointers to doubles, but * for each pointer
- Assigning Values
	ptrN = &N; //use the address of operator to get the RAM address
	X = Y; //can assign type compatible pointers
	*X = 24.53; //use derefencing to assign compatible values that will be stored at the RAm location X is pointing to
	*Y = *X; //Copy the value stored where X is pointing to where y is pointing

Note about NULL
----------------
	- Special constant NULL
	- If a pointer variable is not assigned a value then it cannot be referenced. It is useful to have a value be referenced with
	 NULL (pointing to nothing)

Pointer Practice
-----------------
	int aN [100];
	int *pN;
	pN = aN 			//points to first value in aN
	pN = &aN[0] 		//same thing

	pN = pN + 1;		//points to aN[1] (pN is not equal to an integer value, adding 1 updates pN)
	pN++; or ++pN 		//now points to aN[2] and then aN[3]
	pN -= 3;			//back to aN[0]

	-NOTE: All of these operations are additive or subtractive (no multiplication)

	float A[1000], *ptrA = &A[0] , *ptrB = &A[49];

	A[5] = 6.73;		//
	ptrA[5] = 6.73;		//ALL OF THESE ARE THE SAME
	*(ptrA+5) = 6.73;	//

	Now consider a problem. Using pointer arithmetic only, determine the array subscript corresponding to the position
	of the pointer relative to the beginning of the array:
		- Assume: int Subscript;
		- Answer: Subscript = (ptrB - ptrA) / sizeof(float);
								//value is 49
								//pointers can be subtracted from each other, but not added (gives integer from subtraction)


	-if A,B,C and D are compatible pointers, then consider the following expressions:
		A-B 		OK			A+B 	ERROR	
		A-B+C 		OK			A+B-C 	ERROR
		A-B+C-D		OK			A+(B-C) OK
		(A-B)+(C-D) OK			A-B-D+C ERROR
		
		-Pointer + Pointer = invalid
		-Int - Pointer = invalid 
		-Pointers cannot be added to eachother directly (because it is handled differently from computer to computer due to byte)
		-Pointer difference is dependent on memory allocation 


C Philosophy: -All C functions use pass-by-value (call by value) exclusively and explicitly in all cases of argument passing
-------------

	-If no data type is defined, data types default to int 
	-4 Pointer cases
		1) non-const pointer to non-const data
			-Straight forward,  the pointer changes, data chanegs 
		2) non-const pointer to const data
			-"const char *s" is read in order of "2 1 3" (aka a pointer to constant characters)
		3) const pointer to non const data
		4) const pointer to const data
			-can't change the constant pointer, can't change the constant value held in constant pointer
				-constant pointer to 

Bubble Sort with pointers
-------------------------


void Bubble (int A[], const int N, int (*compare)(int x, int y));
											^
										USER DEFINED
Bubble (Arr1, N1, ascending);
Bubble (Arr2, N2, descending);

void Bubble (int A[], const int N, int (*compare)(int x, int y)){
	int p,q;
	for (p = 1;; p<N; p++){
		for (q = 0; q<N-1; q++){
			if ((*compare)(A[q],A[q+1])){
				swag(&A[q],&A[q+1]);
			}
		}
	}
	return;
}

int ascending (int a, int b){return a>b;}
int descending (int a, int b){return a<b;}




































