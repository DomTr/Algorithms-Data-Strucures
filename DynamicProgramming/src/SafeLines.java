import java.util.Scanner;
public class SafeLines {
	/*
	 * Problem: find number of ways to place m pawns on an n x m board such that their formation is a safe line
	 * A safe line is a line, when to every pawn (except for first and last columns) there is another pawn in column-1 diagonally to it and in column+1 diagonally to it.
	 * They make a safe chain together. The pawns in the edges need to only be 'watched' by their right (if pawn is in column 1) or left (if pawn is in column m) neighbours.
	 */
	static final int MOD = 1000000007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.close();
		long ans = safeLines(n, m);
		System.out.println(ans);
	}
	static long safeLines(int n, int m) {
		/*
		 * dp[i][j] - number of safe pawn lines that end with a pawn at (i, j) in an n x m board.
		 * dp[i][j] = dp[i-1][j-1] + dp[i+1][j-1] if 
		 */
		long[][] dp = new long[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			dp[i][1] = 1;
		}
		for (int j = 2; j <= m; j++) {
			for (int i = 1; i <= n; i++) {
				dp[i][j] = 0;
				if (i-1 >= 1) {
					dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % MOD;
				}
				if (i+1 <= n) {
					dp[i][j] = (dp[i][j] + dp[i+1][j-1]) % MOD;
				}
			}
		}
		long ans = 0;
		for (int i = 1; i <= n; i++) {
			ans = (ans + dp[i][m]) % MOD;
		}
		return ans;
	}
}
