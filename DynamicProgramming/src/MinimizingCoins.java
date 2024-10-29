import java.util.Arrays;
import java.util.Scanner;
public class MinimizingCoins {
	/*
	 * Problem: given n coins in array c, print the minimal amount of coins required to make sum x
	 * If it is not possible, print -1.
	 * Complexity: O(x*n)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = sc.nextInt();
		}
		sc.close();
		int ans = solve(n, x, c);
		System.out.println(ans);
	}
	static int solve(int n, int x, int[] c) {
		// dp[i] - minimal number of coins required to get x
		int[] dp = new int[x+1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		for (int i = 1; i <= x; i++) {
			for (int j = 0; j < n; j++) {
				if (i - c[j] < 0) continue;
				if (dp[i-c[j]] >= 0 && (dp[i] == -1 || dp[i] > dp[i-c[j]] + 1)) {
					dp[i] = dp[i-c[j]] + 1;
				}
			}
		}
		return dp[x];
	}

}
