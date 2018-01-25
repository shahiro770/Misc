/*
 * Shahir Chowdhury
 * FaceList.java
 * 60-254 Assignment 1
 * 2017-09-19
 *
 * This program stores the information of Facebook posts inside a linked list. This information includes the number of likes, comments,
 * size of the post (in characters), date the post was made, and the name of the post's author. The list can be manipulated in multiple
 * ways, providing the user with the ability to add, remove, and sort the posts to their liking.
*/

import java.util.*;
import java.util.InputMismatchException;

	public class FaceList{
		private class Node{		//Node to hold the information of each post
		private int likes;		//number of likes
		private int comments;	//number of comments
		private int size;		//size of the post
		private int	date;		//date of post
		private String name;	//name of the post owner
		private Node next;		//address of the next node

		//sets likes, comments, size, date, name, and the next node
		public Node(int likes, int comments, int size, int date, String name, Node next){
			this.likes = likes;
			this.comments = comments;
			this.size = size;
			this.date = date;
			this.name = name;
			this.next = next;
		}	

		//empty constructor in case of emergencies
		public Node(){
			likes = 0;
			comments = 0;
			size = 0;
			date = 00000000;
			name = null;
			next = null;
		}

		public int getLikes(){
			return likes;
		}
		public int getComments(){
			return comments;
		}
		public int getSize(){
			return size;
		}
		public int getDate(){
			return date;
		}
		public String getName(){
			return name;
		}
		public Node getNext(){
			return next;
		}
		public void setNext(Node next){
			this.next = next;
		}

		/* Compares this node with another node, returning true if the value compared is greater than the other node's
		   Type represents the information to be compared, and is assumed to be an integer between 1 and 4 */
		public boolean compare(Node other, int type){	
			if (type == 1){								//likes
				if (other.likes < this.likes){
					return true;
				}
			}
			if (type == 2){								//comments
				if (other.comments < this.comments){
					return true;
				}
			}
			if (type == 3){								//date of creation
				if (other.date > this.date){			//greater for years implies the date that is older
					return true;
				}
			}
			if (type == 4){								//size of post (in characters)
				if (other.size < this.size){
					return true;
				}
			}
			return false;
		}

		//displays the information of the post in a nice format
		@Override
		public String toString(){
			String result = "";
			result += "Likes: " + likes + " Comments: " + comments + " Size: " + size + " Date: " + date + " Author: " + name;
			return result;
		}
	}

	private Node head;	//pointer to the first node in the list

	public FaceList(){	//sets head
		head = null;
	}

	//adds a new node to the end of the list, establishing the appropriate linkages
	public void add(int likes, int comments, int size, int date, String name){
		if (head == null){														//make new node head if the list is empty
			head = new Node(likes, comments, size, date, name, null);
		}
		else{																	//loop through list until the end of the list is reached
			Node current = head;
			while (current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(new Node(likes, comments, size, date, name, null));
		}
	}

	/* Removes the first instance of a node containing a specified date
	   Assumes the case that head is not null */
	public void delete(int date){	
		Node current = head;		//current node being examined
		boolean deleted = false;	//flag for deletion of a node has occured

		if (current.getDate() == date){	//delete the head if the target node is the head, establishing the next node as the head
			head = head.getNext();
			deleted = true;
		}
		else{							//loop through the list until the target node is found or the end of the list is reached
			while (current.getNext() != null){
				if (current.getNext().getDate() == date){
					current.setNext((current.getNext().getNext()));
					deleted = true;
					break;
				}
				current = current.getNext();
			}
		}
		
		if (deleted == false){			//inform the user if target node is not found
			System.out.println("A post with that date does not exist");
		}
	}

	/* Overloaded delte method that removes the first instance of a node containing a specified author name
	   Assumes the case that head is not null */
	public void delete(String name){
		Node current = head;		//current node being examined
		boolean deleted = false;	//flag for deletion of a node has occured

		if (current.getName().equals(name)){	//delete the head if the target node is the head, establishing the next node as the head
			head = head.getNext();
			deleted = true;
		}
		else{									//loop through the list until the target node is found or the end of the list is reached
			while (current.getNext() != null){
				if (current.getNext().getName().equals(name)){
					current.setNext((current.getNext().getNext()));
					deleted = true;
					break;
				}
				current = current.getNext();
			}
		}
			
		if (deleted == false){					//inform the user if target node is not found
			System.out.println("A post with that author does not exist");
		}
	}

	//returns the number of posts contained in the list as an integer
	public int length(){
		int count = 0;

		Node current = head;
		while (current != null){
			current = current.getNext();
			count++;
		}

		return count;
	}

	//bubblesorts the list based on a given key in ascending order
	public void sort(int type){
		int length = length();	//length of list
		boolean swapped;		//flag for if there was a rearrangement of posts after a run-through of the entire list
		Node previous;			//node linked before the current node
		Node current;			//current node being examined
		Node next;				//node linked after the current node

		do{							//bubblesort until a run-through of the entire list can be done without any changes being made
			swapped = false;	
			previous = null;		//previous begins as null when starting at the head
			current = head;
			next = head.getNext();

			while (next != null){	//stop once the end of the list is reached
				if (current.compare(next, type)){
					swapped = true;
					if (previous != null){	//treat the swap as if there was a node before the current node if currently not at the head
						previous.setNext(next);
						current.setNext(next.getNext());
						next.setNext(current);
					}
					else{					//otherwise, assume the swap was made on the head of the list
						head = next;
						current.setNext(next.getNext());
						next.setNext(current);
					}

					previous = next;		//move on to the next nodes after swapping
					next = current.getNext();
				}
				else{						//move on to the next nodes if there was no swapping
					previous = current;
					current = next;
					next = next.getNext();
				}
			}
		}while (swapped);
	}

	//displays the information of each post in the linked list in a nice format
	@Override
	public String toString(){
		String result = "";
		Node current = head;
		while (current != null){
			result += current + "\n";
			current = current.getNext();
		}
		return result;
	}

	//displays a menu of the options the user has to manipulate the linked list with
	public static void displayMenu(){
		System.out.println("\n------------SELECT AN OPTION------------");
		System.out.println("0) Display stored posts");
		System.out.println("1) Display information about the first post");
		System.out.println("2) Total number of posts");
		System.out.println("3) Add new post");
		System.out.println("4) Delete a post");
		System.out.println("5) Delete all posts");
		System.out.println("6) Sort posts");
		System.out.println("7) Exit");
		System.out.println("------------SELECT AN OPTION------------\n");
	}

	/* Handles all user inputs and displays outputs of the actions the user makes with repsect to the above menu
	   Assumes the user might do something stupid, baring all the necessary error handling capabilities */
	public int getOption(){
		Scanner kb = new Scanner (System.in);
		int option = -1;	//-1 is a placeholder to represent that an option has not been picked yet

		try{ 
			option = kb.nextInt();
			if ((option < 0) || (option > 7)){								//reject inputs that are irrelevant
				System.out.println("That option does not exist!");
			}
			else{															//print the contents of the linked list
				if (option == 0){
					if (head != null){
						System.out.print(this);
					}
					else{
						System.out.println("The list is empty!");
					}
				}
				if (option == 1){											//ask the user for what information they would like to know about the first post
					if (head != null){
						String request;

						System.out.print("What would you like to know? ");
						kb.nextLine();										//remove newline character from past nextInt() call
						request = kb.nextLine().toLowerCase();
						System.out.println(request);

						if (request.equals("likes")){
							System.out.println("Number of likes: " + head.getLikes());
						}
						else if (request.equals("comments")){
							System.out.println("Number of comments: " + head.getComments());
						}
						else if (request.equals("size") || request.equals("number of characters") || request.equals("characters")){
							System.out.println("Size (number of characters): " + head.getSize());
						}
						else if (request.equals("date")){
							System.out.println("Date of post: " + head.getDate());
						}
						else if (request.equals("author") || request.equals("post author") || request.equals("post owner") || request.equals("name")){
							System.out.println("Post author: " + head.getName());
						}
						else{
							System.out.println("Invalid request");
						}
					}
					else{
						System.out.println("The list is empty!");
					}
				}
				if (option == 2){											//tell the user the number of posts in the list
					int length = length();
					System.out.println("Total number of posts: " + length);
				}
				if (option == 3){											//store a new post in the list
					int likes;
					int comments;
					int size;
					String date;
					String name;
					String datePattern;
					Node newNode;
					//I might have tried too hard
					datePattern =  "([0-9][0-9][0-9][0-9][0][1-9][0][1-9]|[0-9][0-9][0-9][0-9][0][1-9][1-2][0-9]|[0-9][0-9][0-9][0-9][0][13-9][3][0]|[0-9][0-9][0-9][0-9][0][13578][3][1]"
					             + "|[0-9][0-9][0-9][0-9][1][0-2][0][1-9]|[0-9][0-9][0-9][0-9][1][0-2][1-2][0-9]|[0-9][0-9][0-9][0-9][1][0-2][3][0]|[0-9][0-9][0-9][0-9][1][02][3][1])";	

					System.out.println("Please enter the values of the new post");
					try{
						System.out.print("Likes: ");
						likes = kb.nextInt();
						System.out.print("Comments: ");
						comments = kb.nextInt();
						System.out.print("Size (number of characters): ");
						size = kb.nextInt();
						while(true){
							System.out.print("Date (YYYYMMDD): ");
							date = kb.next();
							if (date.matches(datePattern)){					//reject invalid dates 
								break;
							}
							System.out.println("Invalid date");
						}
						System.out.print("Post Author: ");
						name = kb.next();

						add(likes, comments, size, Integer.parseInt(date), name);
					}catch(InputMismatchException e){
						System.out.println("Invalid information");
						kb.nextLine();
					}
				}
				if (option == 4){											//delete a post based on a key of the user's choice
					int choice;

					if (head == null){
						System.out.println("The list is empty! There is nothing to delete!");
					}
					else{
						System.out.print("Do you want to delete by (1)Post Date or (2)Post Author? ");
						choice = kb.nextInt();

						if (choice == 1){
							System.out.print("What is the date of the post? ");
							delete(kb.nextInt());
						}
						else if (choice == 2){
							System.out.print("Who is the author of the post? ");
							delete(kb.next());
						}
						else{
							System.out.println("That option does not exist!");
						}
					}
				}
				if (option == 5){											//delete all posts in the list
					if (length() == 0){
						System.out.println("There's nothing to delete, but lets delete everything anyways!");
					}
					head = null;
				}
				if (option == 6){											//sorts the list based on a key of the user's choice
					int choice;

					if (head == null || length() == 1){
						System.out.println("There aren't enough posts to require sorting");
					}
					else{
						System.out.print("Do you want to sort by (1)Likes (2)Comments (3)Date or (4)Size?");
						choice = kb.nextInt();

						if (choice > 0  && choice < 5){
							sort(choice);
						}
						else{
							System.out.println("That option does not exist!");
						}
					}
				}
			}
		}catch(InputMismatchException e){
			System.out.println("That is not a number. Try again.");
			kb.nextLine();	// Gets rid of the input
		}

		return option;
	}

	public static void main (String[] args){
		FaceList theList = new FaceList();
		do{
			displayMenu();
		}while (theList.getOption() != 7);
	}
}

