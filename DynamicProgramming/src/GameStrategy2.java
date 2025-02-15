/*
 * Problem description:
 * You are given an array A indexed from 0 to n-1. The game has following moves:
 * 1. If A contains at least two elements, you can remove leftmost and the rightmost element of A and add to your score the absolute value of their difference.
 * For example if leftmost value is x, rightmost y, then |x-y| is added
 * 2. Leftmost element of A can be removed with no change to the score
 * 3. Rightmost element of A can be removed with no change to your score
 * Find the maximum acquirable score.
 * Example:
 * 6
	5 7 1 2 4 1
	Answer: 10
 */

import java.util.Scanner;

public class GameStrategy2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = sc.nextInt();
		}
		sc.close();
		System.out.println(getMaximumScore(n, A));
	}

	public static int getMaximumScore(int n, int[] A) {
		// TODO: your code
		int[][] dp = new int[n][n]; // dp[i][j] - maximum score obtainable on A[i]...A[j].
		// base cases: dp[i][i] = 0, dp[j][j+1] = |A[j] - A[j+1]|. dp is a lower
		// triangular matrix.
		for (int i = 0; i < n; i++) {
			dp[i][i] = 0;
			if (i + 1 < n) {
				dp[i][i + 1] = Math.abs(A[i] - A[i + 1]);
			}
		}

		// recurrence:
		/*
		 * dp[i][j] = max(dp[i+1][j], dp[i][j-1], dp[i+1][j-1] + |x-y|)
		 */
		for (int d = 2; d <= n - 1; d++) {
			for (int i = 0; i < n - d; i++) {
				dp[i][i + d] = max(dp[i + 1][i + d], dp[i][i + d - 1],
						dp[i + 1][i + d - 1] + Math.abs(A[i] - A[i + d]));
			}
		}
		// printTable(n, dp);
		return dp[0][n - 1];
	}

	public static void printTable(int n, int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static int max(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}
}
