import java.util.Scanner;
public class Trip {
	/*
	 * Problem: given cities c0, c1, ..., c_n, their distances from each other 0 = k0 < k1 < ... < kn
	 * It must be that for any i >= 1: k_i - k_(i-1) <= d (otherwise, k_i is unreachable, so k_n will be unreachable)
	 * Then dp[n] = 0;
	 * 
	 * Find number of ways to visit city c_n 
	 * 
	 */
	static final int MOD = 1000000007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		int[] c = new int[n+1];
		int[] k = new int[n+1];
		for (int i = 1; i <= n; i++) {
			c[i] = sc.nextInt();
		}
		for (int i = 1; i <= n; i++) {
			k[i] = sc.nextInt();
		}
		sc.close();
		long ans = solveDP1(n, d, c, k);
		System.out.println(ans);
	}
	// O(n^2) solution
	static long solveDP1(int n, int d, int[] c, int[] k) {
		/*
		 * dp[i] - number of ways to visit city i if we stop at it. 0 <= i <= n
		 * 
		 * Base:
		 * dp[0] = 1
		 * Recursion:
		 * dp[i] = sum(j = 1, i-1) dp[j] s.t. k[i] - k[j] <= d
		 * 
		 * Runtime: O(n^2)
		 */
		long[] dp = new long[n+1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i-1; j++) {
				if (k[i] - k[j] <= d) {
					dp[i] = (dp[i] + dp[j]) % MOD;
				}
			}
		}
		return dp[n];
	}
	// O(n) solution
	static long solveDP2(int n, int d, int[] c, int[] k) {
		/*
		 * dp[i] - number of ways to visit city i if we stop at it. 0 <= i <= n
		 * pref[i] - prefix sum of dp[0]...dp[i]
		 * lastReachable - index of the last reachable element
		 * 
		 * Base:
		 * dp[0] = 1
		 * pref[0] = 1
		 * lastReachable = 0
		 * 
		 * Recursion:
		 * while (lastReachable <= n && k[i] - k[lastReachable] > d) lastReachable++;
		 * 
		 * dp[i] = prefix[i-1] - prefix[lastReachable-1];
		 * prefix[i] = prefix[i-1] + dp[i];
		 * 
		 * Runtime: O(n)
		 */
		long[] dp = new long[n+1];
		long[] prefix = new long[n+1];
		
		dp[0] = 1;
		prefix[0] = 1;
		int lastReachable = 0;
		
		for (int i = 1; i <= n; i++) {
			while (lastReachable <= n && k[i] - k[lastReachable] > d) {
				lastReachable++; // lastReachable will traverse the whole array once in worst case
			}
			dp[i] = (prefix[i-1] - prefix[lastReachable-1] + MOD) % MOD;
			prefix[i] = (prefix[i-1] + dp[i]) % MOD; 
		}
		return dp[n];
	}
	static long solveDP3(int n, int d, int[] c, int[] k) {
		/*
		 * dp[i] - number of ways to visit city i if we stop at it. 0 <= i <= n
		 * pref[i] - prefix sum of dp[0]...dp[i]
		 * lastReachable - index of the last reachable element
		 * 
		 * Base:
		 * dp[0] = 1
		 * prefix[0] = 1
		 * lastReachable = lastReachable from i entry inside 1...i. Since 0=k0<k1<...<kn is sorted, we can use binary search. log n run time.
		 * 
		 * Recursion:
		 * dp[i] = prefix[i-1] - prefix[lastReachable-1]
		 * prefix[i] = prefix[i-1] + dp[i];
		 * 
		 * Runtime: O(n log n)
		 */
		long[] dp = new long[n+1];
		long[] prefix = new long[n+1];
		int lastReachable;
		dp[0] = 1;
		prefix[0] = 1;
		for (int i = 1; i <= n; i++) {
			lastReachable = binSearch(d, i, k);
			dp[i] = (prefix[i-1] - prefix[lastReachable-1] + MOD) % MOD;
			prefix[i] = (prefix[i-1] + dp[i]) % MOD; 
		}
		return dp[n];
	}
	static boolean check(int d, int i, int j, int[] k) {
		return k[i] - k[j] <= d;
	}
	static int binSearch(int d, int i, int[] k) {
		int l = -1, r = i-1;
		while (r - l > 1) {
			int m = l + (r-l)/2;
			if (check(d, i, m, k)) {
				r = m;
			} else {
				l = m;
			}
		}
		return r;
	}
}
