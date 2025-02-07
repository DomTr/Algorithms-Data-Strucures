
public class ShortestUncommonSubsequence {
	/*
	 * My solution to the problem Shortest Uncommon Subsequence
	 */
	public static int ShortestUncommonSubsequenceSol(int n, int m, char[] A, char B[]) {
		int[][] dp = new int[n + 1][m + 1];
		// TODO: implement
		A = normalizeCharArray(n, A);
		B = normalizeCharArray(m, B);
		int INF = 100005;
		// dp[i][j] - shortest uncommon subsequence of A[1..i] in B[1..j].
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
		}
		for (int j = 0; j <= m; j++) {
			dp[0][j] = INF;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int k = 0; // largest position in B that matches the last character of A
				for (int l = 1; l <= j; l++) {
					if (B[l] == A[i])
						k = l;
				}
				if (k == 0) {
					dp[i][j] = 1; // we take the last character as the shortest subsequence. It is nuot present in
									// B[1..j]
				} else {
					dp[i][j] = Math.min(1 + dp[i][k - 1], dp[i - 1][j]);
				}
			}
		}
		// printArr(dp);
		return dp[n][m];
	}

	public static char[] normalizeCharArray(int n, char[] C) {
		char[] newArray = new char[n + 1];
		for (int i = 0; i < n; i++) {
			newArray[i + 1] = C[i];
		}
		return newArray;
	}

	public static void printArr(int[][] a) {
		int n = a.length;
		int m = a[0].length;
		for (int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < m; j++) {
				sb.append(a[i][j] + " ");
			}
			System.out.println(sb.toString());
		}
	}
}
