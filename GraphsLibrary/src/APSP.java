import java.util.ArrayList;
import java.util.PriorityQueue;

public class APSP {
	/*
	 * Assumption: no negative cycles (negative edges allowed)
	 * Runtime: O(n^3)
	 * Space:   O(n^2)
	 */
	public int[][] floydWarshall(int n, int[][] w) {
		int[][] dist = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (w[i][j] != Integer.MAX_VALUE) {
					dist[i][j] = w[i][j];
				} else {
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		/*
		 * dp connector [i][j] - length of the shortest path from i to j that has only nodes from 1...connector
		 * dp connector [i][j] = min (dp connector-1 [i][j], dp connector-1 [i][connector] + dp connector-1 [connector][j
		 */
		for (int connector = 0; connector < n; connector++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][connector] != Integer.MAX_VALUE 
							&& dist[connector][j] != Integer.MAX_VALUE) {
						if (dist[i][j] > dist[i][connector] + dist[connector][j]) {
							dist[i][j] = dist[i][connector] + dist[connector][j];
						}
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (dist[i][i] < 0) {
				return null; // negative cycle
			}
		}
		return dist;
	}
	/* Johnson's algorithm:
	 * @param n - number of nodes
	 * @param g - graph connections, adjacency list
	 * @param w - weights of edges
	 * @return - 2d dist array, which contains the lengths of the shortest paths from u to v to all u != v
	 * Dijkstra's algorithm from all n nodes. Runtime: O(n(n+m)log n)
	 * This implementation will not work for graphs with negative edges.
	 * It is not enough to add the negative of the most negative edge in graph. The shortest path length could become different, example:
	 * (1, 2, 1), (2, 3, 1), (3, 2, -5), (2, 1, -1)
	 * d11_3 = min(0, -1) = -1
	 * d11_3 = min(
	 */
	public int[][] johnsohnsAlgorithm (int n, ArrayList<ArrayList<Integer>> g, int[][]w) {
		int[][]dist = new int[n][];
		for (int i = 0; i < n; i++) {
			dist[i] = dijkstra(i, n, g, w);
		}
		return dist;
	}
	public int[] dijkstra(int start, int n, ArrayList<ArrayList<Integer>> g, int[][]w) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int[] distances = new int[n];
		boolean[] isSettled = new boolean[n];
		int settledCount = 0;
		for (int i = 0; i < n; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		pq.add(new Pair(start, 0));
		while (settledCount < n) {
			if (pq.isEmpty()) break;
			int u = pq.poll().a;
			if (isSettled[u]) continue;
			
			isSettled[u] = true;
			for (int v : g.get(u)) {
				if (!isSettled[v]) {
					int edgeCost = w[u][v];
					int newDist = edgeCost + distances[u];
					if (newDist >= 0 && newDist < distances[v]) {
						distances[v] = newDist;
						pq.add(new Pair(v, distances[v]));
					}
				}
			}
		}
		return distances;
	}
	static class Pair implements Comparable<Pair> {
		int a;
		int b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(APSP.Pair o) {
			// TODO Auto-generated method stub
			return b - o.b;
		}
		
	}
}
