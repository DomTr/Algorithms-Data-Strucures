import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class BFS {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	int[] bfs(ArrayList<ArrayList<Integer>> g, int n, int start) {
		Queue<Integer> q = new LinkedList<>();
		int[] enter = new int[n];
		int[] exit = new int[n]; // is not needed, for instructional purposes to show intervals.
		int[] d = new int[n];
		for (int i = 0; i < n; i++) {
			d[i] = -1;
		}
		int[] parent = new int[n];
		int T = 0;
		q.add(start);
		enter[start] = T;
		T++;
		d[start] = 0;
		parent[start] = -1;
		while (!q.isEmpty()) {
			int curr = q.poll();
			exit[curr] = T;
			T++;
			for (int u : g.get(curr)) {
				if (d[u] == -1) {
					q.add(u);
					enter[u] = T;
					T++;
					d[u] = d[curr]+1;
				}
			}
		}
		return d;
	}
	// TODO: countMinimalPaths2 with enter and next_cut
	// TODO: countMinimalPaths3 with while until finish is discovered
	int countMinimalPaths1(int n, int start, int finish, ArrayList<ArrayList<Integer>> g) {
		Queue<Integer> q = new LinkedList<>();
		int[] distances = new int[n];
		for (int i = 0; i < n; i++) {
			distances[i] = -1;
		}
		int[] dp = new int[n]; //dp[i] - number of minimal paths from start to i
		q.add(start);
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int u : g.get(curr)) {
				if (distances[u] == -1) {
					q.add(u);
					distances[u] = distances[curr] + 1;
				}
				if (distances[u] == distances[curr] + 1) {
					dp[u] = dp[u] + dp[curr];
				}
				
			}
		}
		return dp[finish];
	}

}
