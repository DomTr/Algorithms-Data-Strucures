import java.util.ArrayList;
import java.util.Scanner;

public class TopologicalSort {
	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<Integer> order;
	static boolean[] visited;
	static int n;
	static int m;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		visited = new boolean[n];
		graph = new ArrayList<ArrayList<Integer>>();
		initialisation();
		order = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			int x, y; // zero indexed nodes
			x = sc.nextInt();
			y = sc.nextInt();
			graph.get(x).add(y);
		}	
		sc.close();
		boolean thereIsACycle = dfs();
		if (thereIsACycle) {
			System.out.println("No topological sort is possible because there is a cycle");
		} else {
			System.out.println(order.toString());
		}
	}
	static boolean dfs() {
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				if (search(i)) {
					return true;
				}
			}
		}
		return false;
	}
	static boolean search(int u) {
		visited[u] = true;
		for (int v : graph.get(u)) {
			if (!visited[v]) {
				search(v);
			} else {
				return true;
			}
		}
		order.add(u);
		return false;
	}
	static void initialisation() {
		visited = new boolean[n];
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0;i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}
	}
}
