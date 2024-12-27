import java.util.Arrays;

public class PerfectSquares {
	
	public int numSquares(int n) {
		int[] dp = new int[n+1];
		Arrays.fill(dp, 1000);
		dp[0] = 0;
		int[] squares = getKSquares(101);
		for (int i = 1; i <= n; i++) {
			for (int sq : squares) {
				if (sq > i) break;
				dp[i] = Math.min(dp[i], dp[i-sq] + 1);
			}
		}
		return dp[n];
	}
	public int[] getKSquares(int k) {
		int[] squares = new int[k];
		for (int i = 0; i < k; i++) {
			int x = (i+1)*(i+1);
			squares[i] = x; 
		}
		return squares;
	}
}
