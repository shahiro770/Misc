/* 	
	assign2.c
	Shahir Chowdhury
	2017-02-12
	This program traverses a given maze and determines if there is a pathway from the entrance to an exit.
*/

//INCLUDES
#include <stdio.h>

//C- PREPROCESSOR DIRECTIVES
#define mazerows 12	//maze dimensions are defined as being 12x12, 
#define mazecols 12
#define UP 0		//macros for directions 
#define RIGHT 1
#define DOWN 2
#define LEFT 3

//PROTOTYPES
void findStart(int *startrow, char (*maze)[mazecols]); 			//finds the entrance of the maze
void mazeTraversal();											//traverses the maze
void printMaze(char maze[][mazecols]);							//prints the current state of the maze
int	validMove(int row, int col, char maze[][mazecols]);			//checks if a move is possible
int coordsAreEdge(int row, int col);							//checks if coordinates are on the edge of the maze
int traversedCheck(int row, int col, char maze[][mazecols]);	//checks if nearby positions have been traversed

//MAIN
int main(void){
	char maze[mazerows][mazecols] = {{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                     				 {'1', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1'},
				                     {'0', '0', '1', '0', '1', '0', '1', '1', '1', '1', '0', '1'},
				                     {'1', '1', '1', '0', '1', '0', '0', '0', '0', '1', '0', '1'},
				                     {'1', '0', '0', '0', '0', '1', '1', '1', '0', '1', '0', '0'},
				                     {'1', '1', '1', '1', '0', '1', '0', '1', '0', '1', '0', '1'},
				                     {'1', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1'},
				                     {'1', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1'},
				                     {'1', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1'},
				                     {'1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1'},
				                     {'1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1'},
				                     {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}};			
	int startrow = 0;				//y coordinate of entrance (x coordinate is by definition 0)
	int *startrowp = &startrow;	
	findStart(startrowp, maze);

	mazeTraversal(*startrowp, 0, RIGHT, maze);	//entrance to maze is always from leftmost column; direction will always be right

	return 0;
}

//FUNCTION DEFINITIONS
/* 
	Objective: Finds the start coordinate
	Input: *startrow  is pointing to an integer that is assumed to be initialized, maze is assumed to be a 2d array
	Output: Finds the address of the starting y value 
*/
void findStart(int *startrow, char maze[][mazecols]){
	for (int i=0;i<mazerows;i++){
		if (maze[i][0] == '0'){ 	//entrance will always be in the first column
			*startrow = i;
			maze[i][0] = 'X';
		}
	}
	return;
}

/* 
	Objective: Traverses the maze until an exit is found using the "right hand" method (as stated in assignment)
	Input: row and col are the current coordinates, dir is the direction the user is facing, maze is assumed to be a 2d array
		   the user's right is prioritized as the direction to move, with priority descending counterclockwise
	Output: Displays the state of the maze everytime the user presses enter. Displays if the maze is solvable
			or unsolvable 
*/
void mazeTraversal(int row, int col, int dir, char maze[][mazecols]){
	char c = ' ';  //uncomment to see maze move step by step

	printMaze(maze);
	printf("Hit return to see next move\n");

	while(c != '\n')			
    {
        c = getchar();
    }
	
	if (coordsAreEdge(row,col) && traversedCheck(row, col, maze)){		//basecase 
		if (col != 0){ 													//if edge is reached and it is not the entrance, exit has been found
			printf("\nmaze solved\n");
			printMaze(maze);
			return;
		}
		else if (col == 0) { 											//maze is unsolvable if entrance is reached again
			printf("failure to solve maze\n");
			return;
		}
	}
	else{
		switch(dir){		//direction the user is facing with respect to a bird's eye view of the maze
			case RIGHT:
				if (validMove(row+1, col, maze)){						//right of user
					maze[row+1][col] = ('X');
					return mazeTraversal(row+1, col, DOWN, maze);
				}
				else if (validMove(row, col+1, maze)){					//front of user
					maze[row][col+1] = ('X');
					return mazeTraversal(row, col+1, RIGHT, maze);
				}
				else if (validMove(row-1, col, maze)){					//left of user
					maze[row-1][col] = ('X');
					return mazeTraversal(row-1, col, UP, maze);
				}
				else if (validMove(row, col-1, maze)){					//behind user
					maze[row][col-1] = ('X');
					return mazeTraversal(row, col-1, LEFT, maze);
				}
				break;
			case DOWN:
				if (validMove(row, col-1, maze)){						//right of user 
					maze[row][col-1] = ('X');
					return mazeTraversal(row, col-1, LEFT, maze);
				}
				else if (validMove(row+1, col, maze)){					//front of user
					maze[row+1][col] = ('X');
					return mazeTraversal(row+1, col, DOWN, maze);
				}
				else if (validMove(row, col+1, maze)){					//left of user
					maze[row][col+1] = ('X');
					return mazeTraversal(row, col+1, RIGHT, maze);
				}
				else if (validMove(row-1, col, maze)){					//behind user
					maze[row-1][col] = ('X');
					return mazeTraversal(row-1, col, UP, maze);
				}
				break;
			case LEFT:
				if (validMove(row-1, col, maze)){						//right of user 
					maze[row-1][col] = ('X');
					return mazeTraversal(row-1, col, UP, maze);
				}
				else if (validMove(row, col-1, maze)){					//front of user
					maze[row][col-1] = ('X');
					return mazeTraversal(row, col-1, LEFT, maze);
				}
				else if (validMove(row+1, col, maze)){					//left of user
					maze[row+1][col] = ('X');
					return mazeTraversal(row+1, col, DOWN, maze);
				}
				else if (validMove(row, col+1, maze)){					//behind of user
					maze[row][col+1] = ('X');
					return mazeTraversal(row, col+1, RIGHT, maze);
				}
			case UP:
				if (validMove(row, col+1, maze)){						//right of user
					maze[row][col+1] = ('X');
					return mazeTraversal(row, col+1, RIGHT, maze);
				}
				else if (validMove(row-1, col, maze)){					//front user
					maze[row-1][col] = ('X');
					return mazeTraversal(row-1, col, UP, maze);
				}
				else if (validMove(row, col-1, maze)){					//left of user 
					maze[row][col-1] = ('X');
					return mazeTraversal(row, col-1, LEFT, maze);
				}
				else if (validMove(row+1, col, maze)){					//behind of user
					maze[row+1][col] = ('X');
					return mazeTraversal(row+1, col, DOWN, maze);
				}
		}
	}
}

/* 
	Objective: Prints the current state of the maze in terms of where it has been traversed
	Input: maze is assumed to be a 2d array
	Output: Prints the maze, with the characters '0' representing an untraversed location and 'X'
			representing a traversed location
*/
void printMaze(char maze[][mazecols]){
	for (int i=0;i<12; i++){
		for (int j=0;j<12; j++){
			printf("%2c", maze[i][j]);
		}
		printf("\n");
	}
	printf("\n");
	return;
}

/* 
	Objective: Checks if a position in the maze is not a wall and therefore a valid position to move to 
	Input: row and col are existing coordinates in the maze, maze is assumed to be a 2d array
	Output: returns true if a position in  maze is traversable, false otherwise
*/
int	validMove(int row, int col, char maze[][mazecols]){
	if(maze[row][col] != '1'){
		return 1;
	}
	return 0;
}

/* 
	Objective: Checks if a position in the maze is on the outside edges of the maze
	Input: row and col are existing coordinates in the maze, assumes maze has dimension that are larger than 1x1
	Output: returns true if position is on the outside edges of the maze, false otherwise
*/
int coordsAreEdge(int row, int col){
	if ((row == mazerows-1) || (row == 0) || (col == 0) || (col == mazecols-1)){
		return 1;
	}
	return 0;
}

/* 
	Objective: Check if the nearby positions have been traversed (if possible)
	Input: row and col are existing coordinates in the maze, maze is assumed to be a 2d array
	Output: Return true if nearby traversable positions have been traversed, false otherwise
*/
int traversedCheck(int row, int col, char maze[][mazecols]){
	if (row + 1 < mazerows){			//don't go outside the size of the maze
		if (maze[row+1][col] == '0'){	
			return 0;
		}
	}
	if (row - 1 > 0){
		if (maze[row-1][col] == '0'){
			return 0;
		}
	}
	if (col + 1 < mazecols){
		if (maze[row][col+1] == '0'){
			return 0;
		}
	}
	if (col - 1 > 0){
		if (maze[row][col-1] == '0'){
			return 0;
		}
	}
	return 1;
}
	
