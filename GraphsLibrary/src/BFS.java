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
			for (int u : g.get(start)) {
				q.add(u);
				enter[u] = T;
				T++;
				d[u] = d[curr]+1;
			}
		}
		return d;
	}

}
