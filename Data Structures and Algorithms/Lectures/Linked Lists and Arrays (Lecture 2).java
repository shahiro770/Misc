/* Linked Lists and Arrays(Lecture 2) 2017-09-14
	Think Carefully
	Consider the following problems:
		Write a computer program in C/C++ that reads GPA of 100 students and return the maxiumum, miniumum, and the average GPA for all the students
		Write a computer program in C/C++ that reads GPA of 100 students and sort them in descending order
		Difference is the latter needs storage in a data structure
	Arrays Again!
	Instead of declaring individual variables, such as GPA variables for each student, 
		Just declare one array variable GPAs of float type and use GPAs[1], GPas[2], ... GPAs[100] to represent individual students GPAs
	Array Disadvantages
		Preallocates memory up front
			This is bad, memory blocks can be allocated in an inefficient order
		Fixed Size (can't dynamically increase array size)
		Contiguous memory allocation (insertion and deletion are more expnesive, but access time is faster)
		Insertion and deletion are in general expensive
	Linked Lists
		Elements are located in a linear order
		A collection of nodes where each node has a value and points to the next node in the list
	Linked List Properties
		Pointers connect successive delements
		A linked list has a dynamic size, it can grow or shrink during execution
			It takes up more space, each node contains data and a pointer
		It can grow as much as we want or until the system run-out of memory
	Disadvantages
			Not contiguous memory allocation, O(1) access time is lost, you need a pointer that moves one item at a time 
	Advantages
		Memory allocation is dynamic the list could shrink or grow during execution time. In other words, preallocation is not required

	Motivation and Background

	Linked List Definition and Operations
	Types of Linked Lists
	Linked List ADT Operations (Abstract Data Types)
		Traverse: Visit each node (element) in the list ina s equential order
		Insert: Add new node to the list
		Delete: Delete an existing node from the list
		Search: Find the index of a node with given data value
		Sort: Sort the nodes in the list based on some order
		Merge: Combine two linked lists
	Singly Linked List:
		A linked list where each node contains a poinder (next) pointing to the next node in the list
	*/
	struct SinglyListNode{
		int data;
		struct SinglyListNode * next;
	}
	struct StudentNode {
		int SID;
		char * name;
		float GPA;
		struct StudentNode *next;
	}
	/*
	Doubly Linked List
		A linked list where each node contains a pointer (next) pointing to the next node in the list and a second pointer (prev) pointing to previous node
	*/
	struct DoublyListNode{
		int data
		struct StudentNode *next;
		struct StudentNode *prev;
	}
	/*
	Circular Linked List: A linked list where each node contains a pointer (next) pointing to the next node in the list. 
		The pointer of the last node in the list point to the head node (first node in the list)
		There is no going back however
	Unrolled Linked List: Store multiple elements in each node. Improve the elment access time.
		Each node or block has an ID and store a range of elements.
			Have them store an array of elements
			This efficiency bcomes O(b) + n where b is the block of memory and 1 is each step required to get the information
	Skip Linked List
		Is built in layers. The bottom layer is a regular linked list. Each higher layer provide the ability to scan thee list in bigger steps, so we can jump and skip elements.
	*/
		struct SkipListNode{
			int data;
			struct ListNode * next;
			???
		}
	/*
	Traversing a Linked List
		Start with head pointer (Have a current pointer point to the head, that way you can reuse the head)
		Follow the next pointers
		Processs every node required (print the node value, find x in the nodes, etc.)
		Stop when the pointer next points to null
		The time complexity is O(n)
		Traversing a circularly linked list is special
	Inserting
		When we are inserting a node into a linked list, we have three cases:
		1) Inserting a node at the beginning of the list
			Create a new node, temporarily store the head pointer content in a temporary pointer
			Set the next pointer of the new node to the temp node
			Update the head pointer to point to the new node
		2) Inserting a node at the end of the list
			Starting from the head pointer (first node) follow the pointers until you reach the last node (next = NULL)
			Set the next pointer of the new node to NULL
			Set the next pointer of the last node to point to the new node
		3) Inserting a node at the middle of the list
			Starting from the head pointer (first node) follow the pointers until you reach 
	Deleting
		When we are deleting a node from a linked list, we have three cases
			1) Deleting from the beginning
			2) Deleting from the end 
			3) Deleting from the middle
				Almost the same as deleting from the end
				Make sure to check if the position actually contains the node
				add an item? you're shifting n items in an array, not the same in a linked list

	Self Assessment
		When to use an array vs a linked list?
			Use a linked list for dynamic storage
		Arrays take up less space 
	
*/