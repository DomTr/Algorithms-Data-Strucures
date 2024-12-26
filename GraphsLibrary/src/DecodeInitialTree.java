import java.util.Scanner;


public class DecodeInitialTree {
	/*
	 * POST_ODD: 9 1 7 5 21 22 27 25 20 10
	 * PRE: 10 10 7 4 3 5 9 11 15 12 16
	 */
	static final int[] order = new int[70000];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			order[i] = sc.nextInt();
		}
		sc.close();
		Tree root = constructTreePRE(N);
		if (root == null) return;
		String ans = root.traversePreOrder();
		System.out.println(ans);
	}

	static class Tree {
		int val;
		Tree left;
		Tree right;

		public Tree(int val, Tree left, Tree right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		public void insert(int newVal) {
			if (newVal <= val) { // unique positive integers, but <= for completeness
				if (left == null)
					left = new Tree(newVal, null, null);
				else
					left.insert(newVal);
			} else { // newVal > val
				if (right == null)
					right = new Tree(newVal, null, null);
				else
					right.insert(newVal);
			}
		}
		// odd: left-right
		public String traversePostOrderOdd() {
			StringBuilder sb = new StringBuilder();
			if (left != null) {
				sb.append(left.traversePostOrderOdd());
			}
			if (right != null) {
				sb.append(right.traversePostOrderOdd());
			}
			sb.append(val + " ");

			return sb.toString();
		}

		// even: right-left
		public String traversePostOrderEven() {
			StringBuilder sb = new StringBuilder();
			if (right != null) {
				sb.append(right.traversePostOrderEven());
			}
			if (left != null) {
				sb.append(left.traversePostOrderEven());
			}
			sb.append(val + "\n");

			return sb.toString();
		}
		public String traversePreOrder() {
			StringBuilder sb = new StringBuilder();
			sb.append(val + "\n");
			if (left != null) {
				sb.append(left.traversePreOrder());
			}
			if (right != null) {
				sb.append(right.traversePreOrder());
			}

			return sb.toString();
		}
	}

	public static Tree constructTreePOST(int size) {
		Index index = new Index(size-1);
		return reconstructTreeFromPOST(index, order[size-1], Integer.MIN_VALUE, Integer.MAX_VALUE, size);
	}
	public static Tree constructTreePRE(int size) {
		Index index = new Index(0);
		return reconstructTreeFromPRE(index, order[0], Integer.MIN_VALUE, Integer.MAX_VALUE, size);
	}
	static class Index 
	{
	    int orderindex = 0;
	    public Index(int orderindex) {
	    	this.orderindex = orderindex;
	    }
	}
	 
	public static Tree reconstructTreeFromPOST(Index orderIndex, int key, int min, int max, int size) {
		if (orderIndex.orderindex < 0) {
			return null;
		}
		Tree root = null;
		if (key > min && key < max) {
			root = new Tree(key, null, null);
			orderIndex.orderindex = orderIndex.orderindex - 1;
			if (orderIndex.orderindex >= 0) {
				root.right = reconstructTreeFromPOST(orderIndex, order[orderIndex.orderindex], key, max, size);
			}
			// don't forget to check that the index is not negative
			if (orderIndex.orderindex >= 0) {
				root.left = reconstructTreeFromPOST(orderIndex, order[orderIndex.orderindex], min, key, size);
			}
		}
		return root;
	}
	public static Tree reconstructTreeFromIN(Index orderIndex, int key, int min, int max, int size) {
		return null; // impossible. There is a counterexample. From PRE order traversal you will always get sorted sequence. The unique tree cannot be decoded solely from that sequence
	}
	public static Tree reconstructTreeFromPRE(Index orderIndex, int key, int min, int max, int size) {
		if (orderIndex.orderindex >= size) {
			return null;
		}
		Tree root = null;
		if (key > min && key < max) {
			root = new Tree(key, null, null);
			orderIndex.orderindex = orderIndex.orderindex+1;
			if (orderIndex.orderindex < size) {
				root.left = reconstructTreeFromPRE(orderIndex, order[orderIndex.orderindex], min, key, size);
			} //! don't forget to check that the index is not out of bounds
			if (orderIndex.orderindex < size) {
				root.right = reconstructTreeFromPRE(orderIndex, order[orderIndex.orderindex], key, max, size);
			}
		}
		return root;
	}
}
