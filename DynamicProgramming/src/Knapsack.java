import java.util.Scanner;
public class Knapsack {
	/* Classical DP problem
	 * Given: P[1..n] - profits, W[1..n] - weights of items and maximal allowed total weight W find the biggest profit you can take.
	 * solveW solves efficiently the problem, when W <= 10^5
	 * solveP solves efficiently the problem, when totalP <= 10^5
	 * TODO: Polynomial knapsack that approximates the solution.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int w = sc.nextInt();
		int[] P = new int[n+1];
		int[] W = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			W[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		sc.close();
		long ans = solveP(n, w, P, W);
		System.out.println(ans);
	}
	static long solveW(int n, int w, int[] P, int[] W) {
		long[][] dp = new long[n+1][w+1];
		// dp[i][j] - maximal value that can be taken using 1..i elements and up until weight j
		// Base cases: 
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= w; j++) {
				if (j - W[i] >= 0) {
					dp[i][j] = Math.max(dp[i-1][j], P[i] + dp[i-1][j-W[i]]);
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[n][w];
	}
	// variant, when the total profit small is
	static long solveP(int n, int w, int[] P, int[] W) {
		int totalProfit = 0;
		for (int i = 1; i <= n; i++) {
			totalProfit += P[i];
		}
		long[][] dp = new long[n+1][totalProfit+1];//dp[i][j] - tells the minimum weight needed to get profit j from elements P[1..i] and W[1..i]
		
		// Base cases:
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 0;
		}
		for (int i = 0; i <= n; i++) {
			for (int j = 1; j <= totalProfit; j++) {
				dp[i][j] = w + 1;
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= totalProfit; j++) {
				if (j - P[i] >= 0) {
					dp[i][j] = Math.min(dp[i-1][j], W[i] + dp[i-1][j-P[i]]);
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		long mx = 0;
		for (int i = 1; i <= totalProfit; i++) {
			if (dp[n][i] != 0 && dp[n][i] <= w) {
				mx = Math.max(mx, i);
			}
		}
		return mx;
	}

}
