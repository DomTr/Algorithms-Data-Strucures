
public class SubsetSumWithDuplicates {
	/*
	 * Subset sum problem, but an element can be taken zero, one or two times. Could be extended into a problem where an additional array K is given which tells, how many times you can take i'`th element
	 * Runtime: O(n b)
	 * Memory: O(n b) - could be done in O(b) because only the last two lines are needed.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	static boolean subsetSumWithDuplicates(int n, int b, int[] a) {
		boolean[][] dp = new boolean[n+1][b+1];
		// Base cases:
		for (int i = 0; i <= n; i++) {
			dp[i][b] = true;
		}
		for (int i = 1; i <= b; i++) {
			dp[0][i] = false;
		}
		
		// Recursion
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= b; j++) {
				dp[i][j] = dp[i-1][j] || 
						( j-a[i] >= 0 && dp[i-1][j-a[i]] )|| 
						( j-2*a[i] >= 0 && dp[i-1][j-2*a[i]] );
			}
		}
		return dp[n][b];
	}
	

}
