import java.util.*;

public class Practical1a{
	public static void main(String[] args){
		MyDynamicArray<CoursePair> coursepairs = new MyDynamicArray<CoursePair>(CoursePair.class);
		MyDynamicArray<Integer> prereqs = new MyDynamicArray<Integer>(Integer.class);
		MyDynamicArray<Integer> achieveable = new MyDynamicArray<Integer>(Integer.class);

		boolean possible = true;
		int taken = 0;

		Scanner kb = new Scanner(System.in);
		//kb.useDelimiter("[ ,\r\n]");

		int numReq = kb.nextInt();
		int courses = 0;
		int takenCourses = 0;

		for (int j = 0;j<2;j++){
			int i = kb.nextInt();
			int b = kb.nextInt();
			CoursePair next = new CoursePair(i, b);
			coursepairs.add(next);
			courses++;
		}

		for (int i = 0;i < coursepairs.size();i++){
			for (int j = 0;j < coursepairs.size();j++){
				if (coursepairs.get(i).compareOp(coursepairs.get(j))){
					System.out.println("False");
					return;
				}
			}
		}

		for (int i = 0;i < coursepairs.size();i++){
			prereqs.add(coursepairs.get(i).getc2());
			achieveable.add(coursepairs.get(i).getc1());
		}

		for (int i = 0;i < prereqs.size();i++){
			for (int j = 0;j < achieveable.size();j++){
				if (prereqs.get(i) == achieveable.get(j)){
					takenCourses++;
				}
			}
		}
		/*if (courses == takenCourses){
			System.out.println("False");
			return;
		}*/


		QueueGeneric<Integer> coursesRefined = new QueueGeneric<Integer> ();
		for (int i = 0;i < prereqs.size();i++){
			coursesRefined.enqueue(prereqs.get(i));
		}
		//int curr = coursesRefined.get(0);
		while (prereqs.size() > 0){
			int fulfilled = 0;
			int curr = prereqs.get(0);
			prereqs.remove(0);
			while (true){
				for (int i = 0;i < coursepairs.size();i++){
					if (coursepairs.get(i).getc2() == curr){
						curr = coursepairs.get(i).getc1();
						fulfilled++;
					}
				}
				if (fulfilled == numReq){
					System.out.println("True");
					return;
				}
				if (!prereqs.contains(curr)){
					break;
				}
				else{
					System.out.println("False");
					return;
				}
			}
		}
		System.out.println("False");
	}
}

class CoursePair{
		int c1;
		int c2;
		public CoursePair(int c1, int c2){
			this.c1 = c1;
			this.c2 = c2;
		}

		public int getc1(){
			return c1;
		}

		public int getc2(){
			return c2;
		}

		public boolean compareOp(CoursePair other){
			if (other.c1 == c2 && other.c2 == c1) {
				return true;
			}
			return false;
		}
	}