/* 	
	Lab5a.c
	Shahir Chowdhury
	2017-02-14
	This program examines some pre- given print statements and explains what they do
*/

//INCLUDES
#include <stdio.h>

//MAIN
int main(void)
{
	 int a = 7 ;				//initializes the integer a and declares its values as 7
     int *aPtr ;				//initializes the integer pointer aPtr
     aPtr = &a ;				//pointer aPtr points to the address of a
     printf( "%p", &a );		//prints the address of a
     printf( "%p", aPtr );		//prints the address of the value contained in aPtr
     printf( "%p", &aPtr );		//prints the address of aPtr
     printf( "%d", a );			//prints the value of a
     printf( "%d", *aPtr );		//dereferences aPtr and prints the value contained inside
     printf( "%p", *&aPtr );	//prints the address of the value contained in aPtr
     printf( "%p", &*aPtr );	//prints the address of the value contained in aPtr
     printf( "%d", *&a );		//prints the value contained at the address of a
     //printf( "%d", &*a );		//error is produced; program is requesting to print the address of a value of a value
     return 0;
}
