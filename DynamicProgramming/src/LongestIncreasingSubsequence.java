import java.util.Arrays;
import java.util.Scanner;
public class LongestIncreasingSubsequence {
	/*
	 * Runtime: O(n log n)
	 * Space complexity: O(n)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Length of the array: ");
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		int ans = LIS(a);
		System.out.println("The length of longest increasing subsequence is " + ans);
	}
	public static int LIS(int[] a) {
		int n = a.length, index = 0;
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = a[0];
		for (int i = 1; i < n; i++) {
			if (a[i] > a[i-1]) {
				dp[index+1] = a[i];
				index++;
			}
			else {
				int id = lowerBound(dp, a[i]);
				dp[id] = a[i];
			}
		}
		return index+1;
	}
	public static int lowerBound(int[] arr, int x) { // smallest element that is greater than or equal to x
		int l = -1, r = arr.length-1;
		while (r - l > 1) {
			int m = l + (r-l)/2;
			if (arr[m] >= x) {
				r = m;
			}
			else {
				l = m;
			}
		}
		return r;
	}

}
