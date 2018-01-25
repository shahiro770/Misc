//BinaryTree.java
/*
	When balanced:
		Insertion: O(logn)
		Deletion: O(logn)
		Searching: O(logn)
	Notes: This is a binary search tree, as will be all binary trees for my implementations
*/
import java.util.*;

public class BinaryTree<T>{
	private static final int PRE = 0;
	private static final int IN = 1;
	private static final int POST = 2;

	private class BNode<T> implements Comparable<T>{
		BNode<T> parent;
		BNode<T> left;
		BNode<T> right;
		T data;
		int height;

		public BNode(BNode<T> left, BNode<T> right, BNode<T> parent, T data, int height){
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.data = data;
			this.height = height;
		}

		public void setLeft(BNode<T> left){
			this.left = left;
		}

		public void setRight(BNode<T> right){
			this.right = right;
		}

		public void setParent(BNode<T> parent){
			this.parent = parent;	
		}

		public void setData(T data){
			this.data = data;
		}

		public void setHeight(int height){
			this.height = height;
		}

		public BNode<T> getLeft(){
			return left;
		}

		public BNode<T> getRight(){
			return right;
		}

		public BNode<T> getParent(){
			return parent;
		}

		public T getData(){
			return data;
		}

		public int getHeight(){
			return height;
		}

		@Override
		public int compareTo(T other){
			String T1 = data.toString();
			String T2 = other.toString();
			return T1.compareTo(T2);
		}

		public boolean lessThan(T other){
			if (this.compareTo(other) <= 0){
				return true;
			}
			return false;
		}

		public boolean lessThan(BNode<T> other){
			if (this.compareTo(other.data) <= 0){
				return true;
			}
			return false;
		}

		@Override
		public String toString(){
			return "<" + data + "> ";
		}
	}

	int size = 0;
	private BNode<T> root = null;
	private BNode<T> current = root;

	public boolean insert (BNode<T> B){
		boolean success = false;
		if (B.getData() != null){
			if (root == null){
				root = B;
				success = true;
			}
			else{
				success = insert(B, root, 0);			
			}
			if (success == true){
				size++;
			}
		}
		return success;
	}

	public boolean insert(BNode<T> curr, BNode<T> B, int height){
		if ((B.getLeft() == null && B.lessThan(curr)) || (B.getRight() == null && !B.lessThan(curr))){
			if (B.getLeft() == null	&& B.lessThan(curr) == true){
				B.setLeft(curr);
				curr.setParent(B);
			}
			else{
				B.setRight(curr);
				curr.setParent(B);
			}
			curr.setHeight(height + 1);
			return true;
		}
		else{
			if (B.lessThan(curr) && B.getLeft() != null){
				return insert(curr, B.getLeft(), height + 1);
			}
			else if (!B.lessThan(curr) && B.getRight() != null){
				return insert(curr, B.getRight(), height + 1);
			}
		}
		return false;	//will never end up here, but just in case
	}

	public boolean insert(T val){
		BNode<T> B = new BNode<T>(null, null, null, val, 0);
		return insert(B);
	}

	public BNode<T> find(T val){
		return find (val, root);
	}

	public BNode<T> find(T val, BNode<T> B){
		if (B.getData() == val){
			return B;
		}
		else if (B.getRight() == null && B.getLeft() == null){
			return null;
		}
		else{
			if (B.lessThan(val)){
				return find(val, B.getRight());
			}
			else{
				return find(val, B.getLeft());
			}
		}
	}

	//deletes the first corresponding value in the tree
	public boolean delete(T val){
		boolean success = false;
		if (find(val) == null){
			return success;
		}
		else{
			BNode<T> B = find(val);
			graft(B);
			success = true;
		}
		if (success == true){
			size--;
		}
		return success;
	}

	public boolean delete(BNode<T> B){
		boolean success = false;
		if (find(B.getData()) == null){
			return success;
		}
		else{
			graft(B);
			success = true;
		}
		if (success == true){
			size--;
		}
		return success;
	}


	public void graft(BNode<T> B){
		if (B == root){
			root = B.getRight();
			insert(B.getLeft());
		}
		else if (B.getLeft() == null && B.getRight() == null){
			if (B.getParent().getLeft() == B){
				B.getParent().setLeft(null);
			}
			else{
				B.getParent().setRight(null);
			}
		}
		else if (B.getLeft() == null){
			B.getRight().setParent(B.getParent());
			reheighten(B.getRight());
			if (B.getParent().getLeft() == B){
				B.getParent().setLeft(B.getRight());
			}
			else{
				B.getParent().setRight(B.getRight());
			}
		}
		else if (B.getRight() == null){
			B.getLeft().setParent(B.getParent());
			reheighten(B.getLeft());
			if (B.getParent().getLeft() == B){
				B.getParent().setLeft(B.getLeft());
			}
			else{
				B.getParent().setRight(B.getLeft());
			}
		}
		else{
			B.getLeft().setParent(B.getParent());
			reheighten(B.getLeft());
			if (B.getParent().getLeft() == B){
				B.getParent().setLeft(B.getLeft());
			}
			else{
				B.getParent().setRight(B.getLeft());
			}
			insert(B.getRight(), B.getParent(), B.getHeight());
		}
	}

	public int size(){
		return size;
	}

	@Override
	public String toString(){
		return toString(root, IN);
	}
	public String toString(int how){
		return toString(root, how);
	}

	public String toString(BNode<T> B, int how){
		String result = "";
		if (B == null){
			return result;
		}
		else{
			if (how == PRE){
				return result + B.toString() + toString(B.getLeft(), PRE) + toString(B.getRight(), PRE);
			}
			else if (how == IN){
				return result + toString(B.getLeft(), PRE)  + B.toString() + toString(B.getRight(), PRE);
			}
			else if (how == POST){
				return result + toString(B.getLeft(), PRE) + toString(B.getRight(), PRE) + B.toString();
			}
		}
		return result;
	}

	public int height(){
		int height = 0;
		if (root == null){
			return height;
		}
		else{
			return height(height, root);
		}
	}

	public int height(int height, BNode<T> B){
		if (B.getLeft() == null && B.getRight() == null){
			return height;
		}
		else{
			if (B.getLeft() == null){
				return height(height + 1, B.getRight());
			}
			else if (B.getRight() == null){
				return height(height + 1, B.getLeft());
			}
			else{
				return Math.max(height(height + 1, B.getRight()), height(height + 1, B.getLeft()));
			}
		}
	}

	public void reheighten(BNode<T> B){
		B.setHeight(B.getHeight() - 1);
		if (B.getRight() != null){
			reheighten(B.getRight());
		}
		if (B.getLeft() != null){
			reheighten(B.getLeft());
		}
	}

	/*	Note that if this was a binary tree (not a search tree):
		Store the path from the root to B1
		Store the path from the root to B2
		Traverse both paths until you find a matching key 
	*/

	public BNode<T> LCA(BNode<T> B, BNode<T> B1, BNode<T> B2){
		if (B == null){
			return null;
		}
		else{
			if (B1.lessThan(B) && B2.lessThan(B)){
				return LCA(B.getLeft(), B1, B2);
			}
			else if (!B1.lessThan(B) && !B2.lessThan(B)){
				return LCA(B.getRight(), B1, B2);
			}
			return B;
		}
	}
}