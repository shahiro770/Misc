/* Intro (Lecture 1) 2017-09-07

Most of what we do in this course will be based on C or C++
You can use whatever language you want in the labs

N-Queen Problem
-------
	Given an N x N chessboard, arrange N queens so that none is attacking another
		3x3 has no solutions, 4x4 has one valid solution
	With 8 queens, there are 96 possible positions
	So if we have a 4x4 board, you could store it in a 2x2 array, but a 1 dimensional array works as well
		[[4],[4],[4],[4]] = rows and then columns
	How can we come up with an algorithm to create a solution?'
		A backtracking algorithm

Job Interview: Level 1
-------
	1) What are linear and nonlinear data structures? (Give examples)
		Linear = Array
		Nonlinear = Binary Tree
	2) What data structures are used for BFS and DFS algorithms?
		BFS = Breadth First Search
		DFS = Depth First Search
		Can be used in trees
	3) Which Data Structures should be used for implementing LRU cache?

	4) Describe binary tree and its property?

	5) Which data structure i used for a dictionary and spell checker?
		Hashtables
	6)
	7)

Job Interview: Level 2
-------
	0) Given the file fll of strings (Netflix):
			Vivek 1
			Vivek 2
			Ajit 3
			Krishna 4
			Keshav 6
			Keshav 7
		Print the following:


	1) What is a priority queue? How will you implement it? What is the complexity of each implementation? (Amazon)
	2) Given a dictionary of words. There can be duplicates. Given a bag of letters. There can be duplicates. Print the length of the longest valid dictionary word that can be formed from the bag (Amazon)
	3) Describe the data structure that is used to manage memory (Google)
	4) Given a binary tree, programmatically you need to prove it is a binary search tree (Google)

Job Interview: Level 3
-------
	How to implement an auto complete, like if I type bo, it would duggest me boy, bow, etc. (microsoft)
	How would you extend the algorithm to support word based auto suggestion i.e. if I search 'i am' it would duggest 'I am a boy', 'I am good' etc. (Microsoft)
	Write a function that takes a pointer to the head of a linked list and an index and dletes that node. Node consists of a void pointer data and a link to the next node. Write test cases for this function (Microsoft)

Course Info
	CS60 - 254 Data Structures and Algorithms
	Prereqs: 60-100 and 60 -141
	3 Hours lecture a week,
		1.5 Lab hours a week
		5-7 hours self study per week
	Course web page and email:
		Course email: cs254@wasplabs.ca
		Web Page: https://github.com/ebinsaad/CS60_254
	Last lab is a practical test (in lab exam, like an interview)

Topics Covered:
	Linear Data Structure
		Arrays and Linked Lists
		Stacks
		Queues
	Non-Linear Data Structure
		Trees
		Graph
	Applying Data Structure:

	Algorithm Design Paradigms
		Backtracking
		Divide and Conquer
		Dynamic Programming
		GReedy Algorithms
	Algorithm Analysis
		Types of Analysis
		Complexity Classes
Evaluation
	30% Lab Assignments (6% each)
	25% Midterm on October 19th, 2017
	10% Practical Lab Exam on November 27th, and 29th, 2017
	35% Final Exam on December 14, 2017

Labs
	
	
*/