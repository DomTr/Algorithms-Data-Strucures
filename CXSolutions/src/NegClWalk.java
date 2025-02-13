import java.util.ArrayList;

public class NegClWalk {
	public static int minCost(int n, int m, ArrayList<ArrayList<Integer>> E, ArrayList<ArrayList<Integer>> W) {
// IDEA: Floyd-Warshall on the graph to compute dist[i][j] = min cost from i to j.
// Then the result is min_{i != j} dist[i][j] + dist[j][i] + 1.

		int[][] dist = new int[n][n];
		boolean[][] computed = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			computed[i][i] = true;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < E.get(i).size(); j++) {
				dist[i][E.get(i).get(j)] = W.get(i).get(j);
				computed[i][E.get(i).get(j)] = true;
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i != k && j != k && i != j && computed[i][k] && computed[k][j]) {
						if (!computed[i][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
							computed[i][j] = true;
						} else {
							dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
						}
					}
				}
			}
		}

		int result = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j && computed[i][j] && computed[j][i]) {
					result = Math.min(result, dist[i][j] + dist[j][i]);
				}
			}
		}

		return Math.max(0, result + 1);
	}
}
