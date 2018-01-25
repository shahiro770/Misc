/*
 *MazeNav.java
 *Last Updated: 2017-11-17
 *Shahir Chowdhury
 *
 *This program navigates a maze and prints the shortest path. The maze is assumed to be inputted as an adjacency matrix.
 *Input is assumed to be in the form of numberOfRooms startRoom endRoom adjacencyMatrix
*/

import java.util.*;

public class MazeNav{
	public static void main (String[] args){
		int roomNum;
		int startRoom;
		int endRoom;
		int currentRoom;
		Scanner kb;
		boolean found = false;
		List<LinkedList<Integer>> maze = new LinkedList<LinkedList<Integer>>();
		QueueGeneric<Integer> visiting = new QueueGeneric<Integer>();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();

		kb = new Scanner(System.in);
		kb.useDelimiter("[ ,\r\n]");

		//scan in maze dimensions and starting and ending rooms
		roomNum = kb.nextInt();
		startRoom = kb.nextInt();
		endRoom = kb.nextInt();

		//scan in the adjacency matric and convert it to an adjacency list
		for (int i = 0;i < roomNum;i++){
			LinkedList<Integer> room = new LinkedList<Integer>();
			for (int j = 0;j < roomNum;j++){
				room.add(kb.nextInt());
			}
			maze.add(room);
		}

		//set up list containing the latest and greatest paths
		ArrayList<Integer> newPath = new ArrayList<Integer>();
		newPath.add(startRoom);
		paths.add(newPath);

		visiting.enqueue(startRoom);	//starting room's connections
		visited.add(startRoom);

		//breadth first search for the fastest path
		while (visiting.isEmpty() == false){
			LinkedList<Integer> cons = maze.get(visiting.peek());
			currentRoom = visiting.peek();
			visiting.dequeue();
			for (int i = 0;i < cons.size();i++){
				//if the room has not yet been visited and if a connection exists, add the room to the visiting queue
				if (visited.contains(i) == false && cons.get(i) != 0) {	
					visiting.enqueue(i);
					visited.add(i);
					buildOldPaths(paths, currentRoom, i);
					if (i == endRoom){		//end the search if the room has been found
						found = true;
						visiting.clear();	//ends the search
					}
				}
			}
		}

		if (found == true){
			ArrayList<Integer> fastest = paths.get(paths.size() - 1);
			for (int i = 0;i < fastest.size();i++){
				if (i != fastest.size() - 1){
					System.out.print(fastest.get(i) + ",");
				}
				else{
					System.out.print(fastest.get(i));
				}
			}
			System.out.print("\n");
		}
		else{
			System.out.println("There is no solution to this maze");
		}
	}

	//builds up new paths using old paths that lead to the latest room
	private static void buildOldPaths(ArrayList<ArrayList<Integer>> paths, int oldRoom, int newRoom){
		for (int i = 0;i < paths.size();i++){
			if (paths.get(i).lastIndexOf(oldRoom) == paths.get(i).size() - 1){
				ArrayList<Integer> newPath = new ArrayList<Integer>();
				for (int j = 0;j < paths.get(i).size();j++){
					newPath.add(paths.get(i).get(j));
				}
				newPath.add(newRoom);
				paths.add(newPath);
			}
		}
	}
}
