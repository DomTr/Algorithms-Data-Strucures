
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
		return dist;
	}
}
