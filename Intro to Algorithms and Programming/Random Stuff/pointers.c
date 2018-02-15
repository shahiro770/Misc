/* Final Contents:
ch2 FUNDAMENTALS: "#"s, main(), statements, comments, layout
ch3 SCANF()/PRINTF(): "%-m.pX , %d, %f, %s," &a, "\t\n" 
ch4 EXPRESSIONS: + - * / % = -= *= /= %= ++ -- ()
ch5 SELECTIONS: < > <= >= == != ! && || if/else switch break
ch6 LOOPS: while do for break continue i++ --i null
ch7 TYPES: int float double char base(10,8,16) conversion sizeof (max values do not need to be memorized)
ch8 ARRAYS: elements of 1d/2d intitialization index sizeof
ch9 FUNCTIONS: declare/define, arg-parm(value/ref types) return/exit
ch10 ORGANIZATION: scopes, external/local, main() + functions */

/*Many number of small questions
	-Prof doesn't like too many small questions
		-Make sure you understand
	-Three groups
		1) find the error (1-3 errors) (simpler than midterm)
		2) given code, find out output
		3) given a prototype, know what it will do and finish the code
  RAPTOR HellChart
  	-Given a flowchart implement it into code
  	-Simple problem Solving
  		-Solver a problem using one specific feature
  Difficult Problem Solving
  	-Use functions to solve problem
  2.5 Hours 
  No Calculators, ID, no phones */
//Final Practice 1

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define R_NUM rand() % (w-2) + 2 //random number range : [2,w-1]
	//Changes value everytime it is called, but w must be declared first

int main(void){
	int h = -1;
	int w = -1;
	srand (time(NULL)); //Seed is amount of time taken to generate a random number; more time to generate gives higher chance of a different random number
						//Random algorithm isn't really random, so increase the chances


	while (h <= 0){ //get h
		printf ("Enter you h value (h > 0) ");
		scanf("%d", &h);
		if (h <= 0){
			printf ("Invalid h\n");
		}
	} 
	while (w <= 7){ //get w
		printf("Enter your w value (w > 7) ");
		scanf("%d", &w);
		if (w <= 7){
			printf ("Invalid w\n");
		}
	}

	for (int i=0; i < w; i++){ //print the first w row
		printf("*");
	}
	printf("\n");

	for (int i=0; i < h; i++){ //print the h rows
		int r = (rand() % (w-2)) + 2;
		if (r < w/2){
			for (int j=0; j < r-1; j++){
				printf("*");
			}
			printf("\n");
		} 
		else if (r >= w/2) {
			for (int j=0; j < r; j++){
				printf(" ");
			}
			for (int j=0; j < w-r; j++){
				printf("*");
			}
			printf("\n");
		}
	}
	for (int i=0; i < w; i++){ //print the last w row
		printf("*");
	}
}

#include <stdio.h>
#include <math.h>

int main (void){
	printf ("%d", 0 || 30 || 1);
}

/*double trianglearea (double base, double height)
{
	double product;
	product = base * height;
	return product / 2;
}

int f(int a, int b){
	printf ("%d %d\n", a,b);
	return a+b;
}

int main(void){
	int i;
	double x;

	i = f(83, 12);
	x = f(83, 12);
	i = f(310293840129385039248509243850923845092834509345.15, 9.28);
	x = f(3.15, 9.28);
	f(83, 12);



	return 0;
}*/






