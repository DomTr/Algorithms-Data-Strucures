
public class powerOfTwoSubsequence {
	/*
	 * Given a sequence of non-negative integers A, find the longest subsequence of A of which neighbouring elements' absolute difference is a power of two
	 * n <= 10^5
	 * 0 <= x <= 10^5
	 */
	// my solution
	// dp[i] - longest subsequence that ends with value i and all neighbouring elements' absolute difference is a power of 2.
	public int getLongestSub(int n, int[] A) {
		int h = 0;
		for (int i = 0; i < n; i++) {
			h = Math.max(h, A[i]);
		}
		int[] dp = new int[h+1];
		for (int i = 0; i < n; i++) {
			int k = 1;
			while (A[i] - k >= 0) {
				dp[A[i]] = Math.max(dp[A[i]], dp[A[i]-k] + 1);
				k = k * 2;
			}
			k = 1;
			while (A[i] + k <= h) {
				dp[A[i]] = Math.max(dp[A[i]], dp[A[i] + k]);
				k = k * 2;
			}
		}
		int answer = 0;
		for (int x : dp) {
			answer = Math.max(answer, x);
		}
		return answer;
	}
	// official solution - dp[i] - longest subsequence that ends with value A[i] and all neighbouring elements' absolute difference is a power of 2
	
	public int getLongestSub2(int n, int[] A) {
		int h = 0;
		for (int i = 0; i < n; i++) {
			h = Math.max(h, A[i]);
		}
		int[] lastSeen = new int[h+1];
		for (int i = 0; i <= h; i++) {
			lastSeen[i] = -1;
		}
		int[] dp = new int[n];
		int maxLength = 0;
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int k = 1; k < (1 << 20); k *= 2) {
				if (A[i] - k >= 0 && lastSeen[A[i] - k] != -1) {
					dp[i] = Math.max(dp[i], dp[A[i]-k]);
				}
				if (A[i] + k <= h && lastSeen[A[i] + k] != -1) {
					dp[i] = Math.max(dp[i], dp[A[i] + k]);
				}
			}
			maxLength = Math.max(maxLength, dp[i]);
			lastSeen[A[i]] = i;
		}
		return maxLength; 
	}
	
}
