public class ConvertToNondecreasing {
	// given n integers between [0, C-1] output the minimal number of operations to make the array non-decreasing
	// allowed operations: increasing or decreasing by 1
	public static int minCost(int n, int C, int[] A) {
	    // TODO: implement.
	    // dp[i][j] - minimal number of changes required to make A[0...i] non-decreasing and end with number j.
	    // base: dp[0][j] = Math.abs(A[0] - j);
	    // computation: dp[i][j] = min(dp[i-1][x] + Math.max(0, x - j)) for 0 <= x < C
	    // extraction: min dp[n-1][j] over 0 <= j < C
	    int[][] dp = new int[n][C];
	    for (int j = 0; j < C; j++) {
	      dp[0][j] = Math.abs(A[0] - j);
	    }
	    for (int i = 1; i < n; i++) {
	      for (int j = 0; j < C; j++) {
	        dp[i][j] = dp[i-1][j] + Math.abs(A[i] - j);
	        if (j > 0) {
	          dp[i][j] = Math.min(dp[i][j], dp[i][j-1] - Math.abs(A[i] - (j-1)) + Math.abs(A[i]-j)); // smart! use dp[i][j-1] instead of dp[i-1][x].
	        }
	        // for (int x = 0; x < j; x++) {
	        //   dp[i][j] = Math.min(dp[i][j], dp[i-1][x] + Math.max(0, x - j) + Math.abs(A[i] - j));
	        // }
	      }
	    }
	    int minimum = n*C;
	    for (int j = 0; j < C; j++) {
	      minimum = Math.min(minimum, dp[n-1][j]);
	    }
	    //printTable(n, C, dp);
	    return minimum;
	  }
}
