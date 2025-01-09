import java.util.Scanner;

public class LongestSnake {
/*
 5
 1 3 2 5 7
 4 4 1 4 3
 8 7 6 5 1
 9 8 5 4 3
 6 7 2 2 3
 => 8
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] grid = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		sc.close();
		int ans = solve2(n, grid);
		System.out.println(ans);
	}
	static int solve(int n, int[][] grid) {
		/*
		 * dp[i][j] - longest snake that starts in (i, j)
		 * BASE:
		 * dp[n-1][0] = 1;
		 * for (r = n-2...0) 
		 *   if |grid[r][0] - grid[r+1][0]| == 1: dp[r][0] = 1 + dp[r+1][0]
		 *   else dp[r][0] = 1;
		 *  
		 * for (c = 1...n-1)
		 *   if |grid[n-1][c] - grid[n-1][c-1]| == 1: dp[n-1][c] = dp[n-1][c-1] + 1
		 *   else dp[n-1][c-1] = 1
		 *  
		 * REGULAR CASE:
		 * for r = n-2...0
		 *   for c = 1...n-1
		 *     dp[r][c] = 1
		 *     if (|grid[r][c] - grid[r+1][c]| == 1: dp[r][c] = dp[r+1][c] + 1
		 *     if (|grid[r][c] - grid[r][c-1] == 1: dp[r][c] = max (dp[r][c], 1 + dp[r][c-1]);
		 * 
		 * EXTRACTING ANSWER:
		 * for i = 0...n-1
		 *   for j = 0...n-1
		 *   maximum = maximum(maximum, dp[i][j])
		 *   
		 * return maximum
		 */
		int[][] dp = new int[n][n];
		// BASE
		dp[n-1][0] = 1;
		for (int r = n-2; r >= 0; r--) {
			if (Math.abs(grid[r][0] - grid[r+1][0]) == 1) {
				dp[r][0] = 1 + dp[r+1][0];
			} else dp[r][0] = 1;
		}
		for (int c = 1; c <= n-1; c++) {
			if (Math.abs(grid[n-1][c] - grid[n-1][c-1]) == 1) {
				dp[n-1][c] = dp[n-1][c-1] + 1;
			} else dp[n-1][c] = 1;
		}
		// NORMAL COMPUTATION:
		for (int r = n-2; r >= 0; r--) {
			for (int c = 1; c <= n-1; c++) {
				dp[r][c] = 1;
				if (Math.abs(grid[r][c] - grid[r+1][c]) == 1) dp[r][c] = dp[r+1][c] + 1;
				if (Math.abs(grid[r][c] - grid[r][c-1]) == 1) dp[r][c] = Math.max(dp[r][c], dp[r][c-1]+1);
			}
		}
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max,  dp[i][j]);
			}
		}
		return max;
	}
	
	static int solve2(int n, int[][] grid) {
		/*
		 * dp[i][j] - longest snake that ends in (i, j)
		 * BASE:
		 * dp[0][0] = 1;
		 * for (c = 1...n-1) 
		 *   if |grid[0][c] - grid[0][c-1]| == 1: dp[0][c] = 1 + dp[0][c-1]
		 *   else dp[0][c] = 1;
		 *  
		 * for (r = 1...n-1)
		 *   if |grid[r][n-1] - grid[r-1][n-1]| == 1: dp[r][n-1] = dp[r-1][n-1] + 1
		 *   else dp[r][n-1] = 1
		 *  
		 * REGULAR CASE:
		 * for r = 1...n-1
		 *   for c = n-1...1
		 *     dp[r][c] = 1
		 *     if (|grid[r][c] - grid[r+1][c]| == 1: dp[r][c] = dp[r+1][c] + 1
		 *     if (|grid[r][c] - grid[r][c-1] == 1: dp[r][c] = max (dp[r][c], 1 + dp[r][c-1]);
		 * 
		 * EXTRACTING ANSWER:
		 * for i = 0...n-1
		 *   for j = 0...n-1
		 *   maximum = maximum(maximum, dp[i][j])
		 *   
		 * return maximum
		 */
		int[][] dp = new int[n][n];
		// BASE
		dp[0][0] = 1;
		for (int c = 1; c <= n-1; c++) {
			if (Math.abs(grid[0][c] - grid[0][c-1]) == 1) {
				dp[0][c] = 1 + dp[0][c-1];
			} else {
				dp[0][c] = 1;
			}
		}
		for (int r = 1; r <= n-1; r++) {
			if (Math.abs(grid[r][n-1] - grid[r-1][n-1]) == 1) {
				dp[r][n-1] = 1 + dp[r-1][n-1];
			} else {
				dp[r][n-1] = 1;
			}
		}
		// REGULAR CASE:
		for (int r = 1; r <= n-1; r++) {
			for (int c = n-2; c >= 0; c--) {
				dp[r][c] = 1;
				if (Math.abs(grid[r][c] - grid[r-1][c]) == 1) {
					dp[r][c] = dp[r-1][c] + 1;
				}
				if (Math.abs(grid[r][c] - grid[r][c+1]) == 1) {
					dp[r][c] = Math.max(dp[r][c], dp[r][c+1] + 1);
				}
			}
		}
		// EXTRACTING ANSWER:
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		return max;
	}
}
