import java.util.Arrays;
import java.util.Scanner;
public class JumpGame {
	/*
	 * Problem description: 
	 * Input: Array A of positive integers
	 * From i you can jump to i+1, i+2, ..., i + A[i]
	 * Find the minimal number of jumps required to reach A[n]
	 * The answer always exists in this variant because the array only contains positive numbers, i.e. no zeros.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Length of the array: ");
		int n = sc.nextInt();
		int[] a = new int[n];
		System.out.println("Array: ");
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		
		int ans = secondSol(a);
		System.out.println(ans);
	}
	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	public static int firstSol(int[]a) {
		int n = a.length;
		int[] dp = new int[n];
		Arrays.fill(dp, n);
		dp[0] = 0;
		for (int i = 0; i < n-1; i++) {
			for (int j = 1; j + i < n && j <= a[i]; j++) {
				dp[i+j] = Math.min(dp[i+j], dp[i] + 1);
			}
		}
		return dp[n-1];
	}
	public static int secondSol(int[] a) { // very similar to the firstSol
		int n = a.length;
		int[] dp = new int[n];
		Arrays.fill(dp, n);
		dp[0] = 0;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (j + a[j] >= i) {
					dp[i] = Math.min(dp[i], dp[j]+1);
				}
			}
		}
		return dp[n-1];
	}
	public static int thirdSol(int[] a) {
		int n = a.length;
		int[] M = new int[n]; // M[k] - Maximal index that can be reached with k jumps.
		int k = 0;
		M[k] = 0;
		while (M[k] < n) {
			k++;
			int i;
			if (k == 1) {
				i = 0;
			}
			else i = M[k-2];
			for (; i <= M[k-1]; i++) {
				M[k] = Math.max(M[k], i+a[i]);
			}
		}
		return M[k];
	}
}
