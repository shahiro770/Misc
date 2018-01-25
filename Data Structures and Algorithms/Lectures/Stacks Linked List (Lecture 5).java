/* Stacks Linked List Implementation (Lecture 5) 2017-09-21

Stack Linked Lists based implementation
	The array based implementation of a stack has the folllowing limitations:
		1) Preaallocation of memory
		2) Fixed capacity during runtime
	The array based impplementation has the following advantages:
		1) All access mechanism (operations) have O(1) except traverse
		2) Implementation is straightforward

	Using a linked list structure to implement a stack will avoid the need to preallocate memory in advance
		Also provides a dynamic
		... 
	The stack in this case is defined by a pointer top that points to the top (head) node in the linked list
		Top -> null
		Add 3
		top -> 3 -> null
		Add 4
		top -> 4 -> 3 -> null

Abstract Data Types
	Bob provided Alice with his Stack ADT specifications that inform Alice about the avialable interfaces and how she can use them
		Bob will change his stack ADT from array-based implementation to linked list based implementation
			Alice does not need to change her code at all
			Bob will maintain his Stack ADT interfaces(specifications)

Stack ADT Functions
	Create Stack
	*/
		int main() {
			Stack mystack;
			InitiatteStack(myStack);
			return 0;
		}

		void INitiateStack(Stack *ptrStack){
			ptrStack -> top = nullptr;
		}
	/*
	StackNode or StackElement?
		StackNode Alice does not need to change her code (its correct)
		StackElement does not contain a pointer to the top
			Alice needs to work with StackElement, so any changes will give Alice trouble
	Push element
	*/
		void Push(StackElement e, Stack *ptrStack){
			StackNode * node = (StackNode*)malloc(sizeof(StackNode));
			node -> element = e;
			node -> next = ptrStack -> top;
			ptrStack -> top = node;
		}
	/* 
	*/
		int IsFullStack (Stack *ptrStack){
			return 0;
		}
	/* 
		But we don't need IsFull anymore because Alice is probably not using all the memory in her program, righT?
			We don't want to break her code, so keep it implemented
	*/
		void Pop (StackElement *e, Stack *ptrStack){
			*e = ptrStack -> top -> element;
			StackNode * temp = ptrStack -> top;
			ptrStack -> top = ptrStack -> top -> next;
			free(temp)
		}
	/*
	*/
		void DeleteStack (Stack *ptrStack){
			StackNode *crr = ptrStack -> top;
			while (crr! = nullptr){
				crr = crr -> next;
				free(ptrStack -> top);
				ptrStack -> top = crr;
			}
		}
	/*
	*/
		int StackSize (Stack *ptrStack){
			StackNode *crr = ptrStack ->top;
		}

		void StackTop(StackElement *e, Stack *ptrStack){
			*e = ptrStack -> top -> element;
		}
	/* */

Time Complexity:
	Push = O(1)
	Pop = O (1)
	Stack Size = O(1) //O(n) without a counter in each node
	Is Empty = O(1)
	Is Full = O(1)
	Delete/Empty Stack  = O(n)

Stacks Application: Parsing Arithmetic Expressions
	Infix Notation: Arithmetic Expressions are written with an operator (+,-,*,/,^) placed between two operands (A+B)
	Prefix Notation: The operator written before the two operands (+AB)
	Postfix Notation: the operator written after the two operands (AB+)

This will be the second assignment:
	You have a list L and a Stack
	L:
	Add an A
	L: A
	Add *
	L: A
	Stack: *
	Add B
	L: AB
	Add -
	Stack: Pop *, push - //Before pushing, check the stack top, if the priority is higher on the lower operand, pop it into the list first
	L: AB*
	Add C
	L: AB*C
//Write pseudocode on midterms

