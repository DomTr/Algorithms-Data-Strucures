import java.util.Arrays;
import java.util.Scanner;
public class MinimalSubarrayWithBiggerSum {
	/*
	 * Complexity: O(n log n) because of sort. Algorithm uses greedy approach.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		long ans = minimalSubsetWithBiggerSum(a, n);
		System.out.println(ans);

	}
	public static long minimalSubsetWithBiggerSum(int[] a, int n) {
		int[] cpy = Arrays.copyOf(a, n);
		Arrays.sort(cpy);
		long total = 0;
		for (int i = 0; i < n; i++) {
			total += cpy[i];
		}
		long ans = 0;
		long left = total;
		for (int i = n-1; i >= 0; i--) {
			if (ans >= left) { // bigger or equal sum. Decided on equal because it could be that there is only one element in the array. Then the maximal sum can be the element itself
				break;
			} else {
				ans += cpy[i];
				left -= cpy[i];
			}
		}
		return ans;
	}

}
