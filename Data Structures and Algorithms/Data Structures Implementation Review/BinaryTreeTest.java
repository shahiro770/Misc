//BinaryTreeTest.java

public class BinaryTreeTest{
	public static void main(String[] args){
		BinaryTree<Integer> potato = new BinaryTree<Integer>();
		potato.insert(4);
		potato.insert(6);
		potato.insert(5);
		
		
		System.out.println(potato);
	}
}