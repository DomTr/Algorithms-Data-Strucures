import java.util.Scanner;
public class ColourfulArray {
	/*
	 * Problem:
	 * A Colourful array Z[1...k][1...k] is a square array with entries {0,1} with a quality that no adjacent entries of Z are equal.
	 * Given a two dimensional A[1...n][1...m] array with entries {0,1} output the largest colourful array contained in A[1...n][1...m]
	 * That is the largest k, such that for some 1 <= i <= n - k + 1, 1 <= j <= m - k + 1, the array A[i...i+k-1][j...j+k-1] is a colourful array
	 * 
	 * Solution:
	 * DP: runtime: O(n m)
	 * dp[i][j] = size of the largest colourful array in A whose bottom-right entry is A[i][j]
	 * Answer is the maximum of dp[i][j] over 1<=i<=n, 1<=j<=m
	 * 
	 * Explanation of the recursive steps:
	 * dp[i][j] = 1 if A[i][j] == A[i-1][j] || A[i][j] == A[i][j-1] || A[i][j] != A[i-1][j-1] then dp[i][j] = 1. The largest colourful array can only consist of 1 entry
	 * 	if A[i][j] == A[i-1][j] || A[i][j] == A[i][j-1], then neighboring cells are the same => no colourful array
	 * 	if A[i][j] != A[i-1][j] && A[i][j] != A[i][j-1], but A[i][j] != A[i-1][j-1], then A[i][j] is surrounded by 3 cells of the same colour => no colourful array
	 * 
	 * Otherwise:
	 * dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
	 * 	This is because A[i][j] can only be a part of another colourful array which has the lowest length.
	 * 	If it extends a square A[i-1][j-1] which has a length bigger length than any other square say from A[i-1][j].
	 * 	k1 = dp[i-1][j-1] > dp[i][j-1] = k2, then from i-1-k1 > i-1-k2 there will not be able to be a square because otherwise dp[i][j-1] would be bigger. 
	 * 	A picture here would be worth 1000 words... Best thing would be to draw an entry (i,j) and one square from A[i-1][j], other from A[i-1][j-1] and the third one from A[i][j-1]
	 * 	If not the minimal length is chosen, there will be a contradiction.
	 * Test cases to add:
	  5 5
	  1 0 0 1 1
	  1 0 1 0 1
	  1 1 0 1 0
	  1 0 1 0 1
	  0 1 0 1 0
	 * Answer: 5 (Array from A[n][m])
	 * 
	  4 4
	  0 1 0 0
	  1 0 1 0
	  0 1 0 1
	  1 0 1 0
	 * Answer: 3 (Array from A[n][m])
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] A = new int[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				A[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		int k = solve(n, m, A);
		System.out.println("The largest possible length of a colourful array inside A is " + k);
	}
	static int solve(int n, int m, int[][] A) {
		int[][] dp = new int[n+1][m+1];
		// Base cases:
		for (int i = 1; i <= n; i++) {
			dp[i][1] = 1;
		}
		for (int i = 1; i <= m; i++) {
			dp[1][i] = 1;
 		}
		
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= m; j++) {
				if (A[i][j] == A[i-1][j] || A[i][j] == A[i][j-1] || A[i][j] != A[i-1][j-1]) {
					dp[i][j] = 1; // entry must consist of only 1 element.
				}
				else {
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
				}
			}
		}
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans;
	}


}
