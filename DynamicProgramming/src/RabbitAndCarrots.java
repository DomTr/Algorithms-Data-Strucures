import java.util.Scanner;
public class RabbitAndCarrots {
	/*
	 * Problem: Rabbit start on cite (1,1) of an n × n grid. It can only moves right or down and on each path-way there is a number of carrots. How many carrots does the rabbit collect maximally?
	 * Too many possible path to calculate all (Ω(2n)).
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] grid = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		sc.close();
		long ans = solve(n, grid);
		System.out.println(ans);
	}
	static long solve(int n, int[][] grid) {
		long[][]dp = new long[n+1][n+1];
		long max = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
				max = Math.max(max, dp[i][j]);
			}
		}
		return max;
	}

}
