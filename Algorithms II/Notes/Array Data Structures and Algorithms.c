/* Array Data Structures and Algorithms

if ((A = scanf("%d%d", &data1, &data2) == EOF){ //checks if there is data in the input file  (EOF = end of file)
	return 0;
}
//if data exists, input data until EOF is encountered
for (int s = 0; s< numberofdata;s++){
	if ((A= scanf("%d", &dataplace)) == EOF ){
		do something
	}
}

- nd arrays as function arguments
	- Unlike 1d arrays, all array maximum dimensions must be delcared explicitly (except for the leftmost dimension, or subscript)

- E.g. InputMarks (NS, NA, Mark)
	- Prototype 
		void InputMarks(int, int, float[][NUM_ASSIGNS]); //you leave first parameter free because row size may vary

- Memory allocation of 2D arrays is done (typically) using Row Major Ordering
	- The compiler must know, in advance, what size of allocation to provide for each row
	- The actual number of rows is not important
	- e.g. 4 row, 3 column array
		Col 1 	2	3
			4	5	6
			7	8	9
			10	11	12
		-Data is Contiguously located (side by side and touching)  

2D Arrays and Graphics
----------------------
-Pixel = Picture Element
	-Building up groups of three dots (red green and blue) in squares on computer science, we can create an optical illusion, this is graphic design

-Jaggies; a jagged or staircase, line (also called 'aliasing')

-Notable Algorithms
	-DDA - digital differntial analyzer
	-More optmized versions of DDAs



