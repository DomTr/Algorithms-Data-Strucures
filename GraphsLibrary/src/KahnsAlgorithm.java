import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class KahnsAlgorithm {
	// Kahn's algorithm to find a topological sorting of a graph
	static int n;
	static int m;
	static ArrayList<ArrayList<Integer>> g;
	static int[] indegrees;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		indegrees = new int[n];
		g = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			// if nodes are not zero-indexed, no need to substract 1
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			g.get(x).add(y);
			indegrees[y]++;
		}
		sc.close();
		ArrayList<Integer> ans = topoSort(g, indegrees, n);
		if (ans == null) {
			System.out.println("No topological sorting, there is a cycle in the graph.");
		} else {
			StringBuilder sb = new StringBuilder();
			for (int u : ans) {
				sb.append((u+1) + " ");
			}
			System.out.println(sb.toString());
		}
	}
	static ArrayList<Integer> topoSort(ArrayList<ArrayList<Integer>> g, int[] indegrees, int n) {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if(indegrees[i] == 0) {
				q.add(i);
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		int index = 0;
		while (q.size() > 0) {
			int curr = q.poll();
			for (int u : g.get(curr)) {
				indegrees[u]--;
				if (indegrees[u] == 0) {
					q.add(u);
				}
			}
			ans.add(curr);
			index++;
		}
		if (index < n) {
			return null;
		}
		return ans;
	}
	int[] shortestPaths(ArrayList<ArrayList<Integer>> g, int[] indegrees, int[][] costs, int n, int start) {
		int[] d = new int[n];
		ArrayList<Integer> sortedNodes = topoSort(g, indegrees, n);
		for (int i = 0; i < n; i++) {
			int v = sortedNodes.get(i);
			if (v == start) {
				d[v] = 0;
			} 
			else if (indegrees[v] == 0) {
				d[v] = Integer.MAX_VALUE;// unreachable 
			} 
			else {
				d[v] = Integer.MAX_VALUE;
				for (int j = 0; j < i; j++) {
					int w = sortedNodes.get(j);
					if (w == v) break;
					d[v] = Math.min(d[v], d[w] + costs[w][v]);
				}
			}
		}
		return d;
	}
}
