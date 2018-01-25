/* Abstract Data Structures.c

Self Referential Data Structures
-------------------------------

We are now going to consider dynamically allocated memory and techniques for exploiting it
	- This is done at execution time, hence we cannot create variable names to refer to locations, we must use pointers instead
	- We focus on malloc(), free(), and sizeof, all three are important!
	- Example: */
		NodePtr = malloc sizeof(Node_t);

		if (NodePtr != NULL)
			printf ("Memory allocation successful!\n");
		else
			printf("Memory not allocated!\n");
		free (NodePtr);							//CLEAN YOUR HEAP WHEN IT IS NOT BEING USED ANYMORE 	

		struct Nodetype {struct Nodetype *NextPr; ....};
		typedef struct Nodetype Node_t;
		Node_t *RootPtr;

		/*Memory block					|
		used for program variables		|
		with assigned names 			|




		We will be creating dynamic (e. runtime) data structures taht will contain pointers to other structures
			These are called linked structures

Linked Lists
--------------
*/	
	cfPtr = fopen ("DAFile.dat", "rb");
	RootPtr = malloc (NodeSize);			//
	fread(RootPtr, PayloadSize, 1, cfPtr);
	RootPtr -> NextPtr = NULL;

	Nptr = RootPtr;

	while (!feof(cfPtr)) {
		NodePtr = malloc (NodeSize);
		fread(NodePtr, PayloadSize, 1,cfPtr);

		Nptr -> NextPtr = NodePtr;
		Nptr = NodePtr;				//deallocate resources 
		Nptr -> NextPtr = NULL;
	}
	fclose (cfPtr);


/*	RootPtr
	Pointer ---> Data (Nxt)  --->	Data (Nxt) ---> Data (empty) 

	Another example: Traversal of a list to find an ID */
	Nptr = RootPtr; 	//Head of the list

	while (Nptr != NULL){	//list traversal loop
		if (Nptr -> Data.ID == IDval){
			break;			//search criterion
		}
		Nptr = NPtr -> NextPtr;	//point at successor (next) element
	}

	if (Nptr != NULL){
		printf ("ID = %d, Name = %s, Score = %lf\n", Nptr -> Data.ID, Nptr -> Data.Name, Nptr -> Data.Score);
	}

/*	Deletion of a list
   --------------------

	Also called de-allocation of memory
	This is an extremely important issue for programmers  */

	Nptr = RootPtr;

	while (Nptr != NULL) { //traverse the list
					//CAUTION: Order of statements is imporatnt
		NodePtr = Nptr;							//1. Save current node address
		Nptr = NodePtr -> NextPtr;				//2. Point at successor node
		free(NodePtr);							//3. Release memory for node*/		//segementation fault if you free before the changing pointers 

/* Important Point 

	Since memory blocks are allocated in unpredictable ways (effectively random), freeing those blocks may leave gaps or holes, in the heap
		Disk Fragmentation:
			Deleting a file frees a spot in memory that is hidden behind barriers that the OS can't manage
			You need to perform disk defragmentation in order to clear those holes by rewriting all of the files in the OS into a specific area
				-Very heavy procedure

	**FOR EVERY malloc(), THERE IS A free() CALL */

/* Input data into a linkde list so that it is sorted with each insertion of a node - Insertion Sort
	Special Cases:
		1) Insert at the Head of List, can only get at the head through the named root pointer
		2) Insert between two list nodes
		3) Insert at End of List; Null field will have to be contained by newly added record, update the current tail to contain data*/

//ASSUMPTION: File has been opened using cfPtr
		while (!feof(cfPtr)){
			NodePtr = malloc (NodeSize); 
			fread (NodePtr, PayloadSize, 1, cfPtr);

		//Logic to find where to insert this inputted data block
		// in the singly linked list

		}

		if (RootPtr == NULL){
			NodePtr -> NextPtr = NULL;
			RootPtr = NodePtr;
		}

		else if (NodePtr -> Payload.ID < RootPtr -> Payload.ID){
			NodePtr -> NextPtr = RootPtr;
			RootPtr = NodePtr;
		}
		else{
			//Locate insertion point in SL list
		}

		Node_t *PrevNodePtr = RootPtr;
		Nptr = RootPtr -> NextPtr;
		while (Nptr != NULL){
			if (NodePtr -> Payload.ID < NPtr -> Payload.ID){
				NodePtr -> NextPtr = NPtr;
				PrevNodePtr -> NextPtr = NodePtr;
				break;
			}
			else{
				PrevNodePtr = Nptr;
				Nptr = Nptr -> NextPtr;
			}
		}

		if (PrevNodePtr -> NextPtr == NULL){
			NodePtr -> NextPtr = NULL;
			PrevNodePtr -> NextPtr = NodePtr;
		}

/* This example illustrates the Top-Down approach where the original problem has been broken down into sub-problems
	These are organized methodically and the algorithm developed accordingly

	There are many details to consider and care must be taken when writing code with pointers, ensuring that the order of statements is correct

	Always test code!!

	-From the previous example it is seen that developing and using functions with well defined algorithms is worthwhile when working
	with dynamically allocated list data structures
		-This separates high-level intention from low-level details
		-Design focuses on Use-Cases, while details focus on how it works
	-Some other example functions to consider:
		Node_t* FindNode(Node_t *Head, int IDval);
		void *InsertNode (Node_t *Root, Node_t *InsertPtr, Node_t *InsertNodePtr);
		Node_t *FindLast (Node_t *Head);
		void *OutputList (Node_t *Head);
		void *DeleteList (Node_t *Head);
		int isEmpty 9Node_t *Head);
		long int ListLength (Node_t *Head); */

/* struct NodetypeDL{
	payload_t Data;
	struct NodetypeDL * PrevPtr;
	struct NodetypeDL * NextPtr;
};
typedef struct NodetypeDL Node_tDL;


	-Doubly linked lists feature two self-referential pointers, usually called Predecessor (previous) and Successor (Next) links
	-There are two named pointers, usually called Head and Tail pointers, the latter pointing to the last node in the list
		-Traversal can be performed in both directions
		-Limited traversals (to adjacent, or nearby, nodes) can be performed in both directions
	-Typical operations are similar to those for singly linked lists
		-InsertNode, DeleteNode, DeleteList, FindNode, and so on




























*/