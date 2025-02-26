import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class DFS {
	/*
	 * Testcase:
	 5 4 0
	 0 1
	 0 2
	 1 3
	 2 4
	 dfsIterative1 will return [0, 2, 4, 1, 3]
	 dfsIterative2 will return [0, 1, 3, 2, 4]
	 This is because dfsIterative2 from vertex u prioritizes neighboring vertices v which were added the first.
	 dfs2 also has this property, but it is recursive implementation. 
	 */
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int n;
	static int m;
	static int T = 0;
	static int[] dfsNumbers;
	static ArrayList<Integer> order;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		int start = sc.nextInt();
		initialisation();
		for (int i = 0; i < m; i++) {
			int x, y; // zero indexed nodes
			x = sc.nextInt(); // directed graph
			y = sc.nextInt();
			graph.get(x).add(y);
		}	
		sc.close();
		dfsIterative2(start);
		System.out.println(order.toString());
		//printArr(dfsNumbers);
	}
	static void dfs() {
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				search(i);
			}
		}
	}
	static void search(int u) {
		visited[u] = true;
		for (int v : graph.get(u)) {
			if (!visited[v]) {
				search(v);
			}
		}
	}
	static void initialisation() {
		visited = new boolean[n];
		dfsNumbers = new int[n];
		Arrays.fill(dfsNumbers, -1);
		order = new ArrayList<>();
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0;i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}
	}
	// iterative dfs implementation:
	static void dfsIterative1(int start) {
		Stack<Integer> st = new Stack<>();
		st.add(start);
		while (!st.isEmpty()) {
			int u = st.pop();
			if (visited[u]) continue;
			//System.out.println("ok");
			//dfsNumbers[u] = T;
			//T++;
			visited[u] = true;
			order.add(u);
			for (int v : graph.get(u)) {
				if(!visited[v]) {
					st.push(v);
				}
			}
		}
	}
	static void dfsIterative2(int start) {
		Stack<Integer> st = new Stack<>();
		st.push(start);
		while(!st.empty()){
			int v = st.pop();
			if (!visited[v]) {
				visited[v] = true;
				order.add(v);
			}
			for (int u : graph.get(v)) { // Potential improvement: remember the index where it was stopped
				if (!visited[u]) {
					st.push(v);
					st.push(u);
					break;
				}
			}
		}
	}
	static void dfs2(int v) {
		visited[v] = true;
		order.add(v);
		for (int u : graph.get(v)) {
			if (!visited[u]) {
				dfs2(u);
			}
		}
		return;
	}
	
	static void printArr(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int x : a) {
			sb.append(x + " ");
		}
		System.out.println(sb.toString());
	}

}
