import java.util.ArrayList;
import java.util.Scanner;

public class SSSP {
	static KahnsAlgorithm k;
	static int n;
	static int m;
	static ArrayList<ArrayList<Integer>> g;
	static int[] indegrees;
	static int[][] costs;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		k = new KahnsAlgorithm();
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		indegrees = new int[n];
		costs = new int[n][n];
		g = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			// if nodes are not zero-indexed, no need to substract 1
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			costs[x][y] = sc.nextInt();
			g.get(x).add(y);
			indegrees[y]++;
		}
		sc.close();
		int[] shortestPath = shortestPathsNegativeEdges(g, indegrees, costs, n, 0);
		StringBuilder sb = new StringBuilder();
		for (int u : shortestPath) {
			sb.append(u + " ");
		}
		System.out.println(sb.toString());
	}
	/*
	 * Assuming there are no negative cycles, finds shortest path even with negative edges.
	 * Run-time: O(n+m) somehow
	 */
	static int[] shortestPathsNegativeEdges(ArrayList<ArrayList<Integer>> g, int[] indegrees, int[][] costs, int n, int start) {
		int[] d = new int[n];
		ArrayList<Integer> sortedNodes = KahnsAlgorithm.topoSort(g, indegrees, n);
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
	/*
	 * Dijkstra's algorithm idea: sort nodes according to distance from start
	 */
	static int[] dijkstra (ArrayList<ArrayList<Integer>> g, int[][] costs, int n, int start) {
		return null;
	}
	
}
