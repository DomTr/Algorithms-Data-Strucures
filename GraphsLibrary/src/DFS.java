import java.util.ArrayList;
import java.util.Scanner;

public class DFS {
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int n;
	static int m;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		initialisation();
		for (int i = 0; i < m; i++) {
			int x, y; // zero indexed nodes
			x = sc.nextInt();
			y = sc.nextInt();
			graph.get(x).add(y);
		}	
		sc.close();
		dfs();
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
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0;i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}
	}

}
