/*The printf Function
-Ordinary characters in a format string are printed as they appear in the string; conversion specifications are replaced
-E.g. 
	int i,j;
	float x,y;
	
	i = 10;
	j = 20;
	x = 43.2892f;
	y = 5527.0f;
	printf("i = %d, j = %d, x = %f, y = %f\n", i, j, x, y);
	
	Output:
	i = 10, j = 20, x = 43.2892000, y = 5527.000000
	
-Compilers aren't required to check that the number of conversion specifications
in a format string matches the number of output items
-Too many conversion specifications:
	printf("%d %d\n", i); *WRONG*
-Too few conversion specifications:
	printf("%d \n", i, j); *WRONG*
-C requires you to declare a variable before assigning it a value because there must be a cell in RAM 
with the variable to assign the value to

scanf Function:
-scanf reads input according to a particular format
-A scanf format string may contain both ordinary characters and conversion specifications
-The conversions allowed with scanf are essentially the same as those in printf

-In many cases, a scanf format string will contain only conversion specifications:
	int i,j;
	float x,y;

	scanf("%d%d%f%f", &i, &j &x, &y); //& sign says to find the location of the cell with that variable (find the address) 
-Using the and sign(&), we can tell the program where to save the input value 
-Sample input:
	1 -20 .3 -4.0e3
	scanf will assign 1, -20, 0.3, and -4000.0 to i, j,x, and y respectively 
-Floats take up 2 cells in RAM (first cell for the integer, second cell for the values after the decimal point)
-When inputs are taken in, a buffer holds the input values (an i/o buffer) 
	-All values are stored as ASCII codes in the cell 
	-E.g. values 1 and -20 are stored as
		[1]
		[space]
		[-]
		[2]
		[0]
-When using scanf, the programmer must check that the number of conversion specifications matches the number of input variables
-scanf ignores white-space characters (space, horizontal and vertical tab, form-feed, and new-line) when searching for a number
-A call of scanf that reads four numbers scanf("%d%d%f%f", &i, &j &x, &y);
-The numbers can be on one line or spread over several lines:
	1
	-20	.3
		-4.0e3
-scanf sees a stream of characters (••1¤-20•••.3¤•••-4.0e3¤ ssrsrrrsssrrssssrrrrrr (s= skipped; r= read)
*/