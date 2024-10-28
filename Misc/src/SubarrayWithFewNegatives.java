import java.util.Scanner;
public class SubarrayWithFewNegatives {
	/*
	 * TASK:
	 * Given an array of n elements, and k - maximum allowed negative elements in a sub array, find the longest sub array with at most k negative elements.
	 * Algorithm run-time: O(n)
	 * Run time of O(n^2) can be implemented quite easily.
	 * In general, this problem can be extended with any type of predicate that allows maximum k numbers, for example maximum k even/odd numbers, maximum numbers divisible by 10,11...
	 * 
	 * TODO: add another solution
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter n (array length) and k (maximum allowed number of negatives in a subarray):");
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		int ans = longestSubarray(n, k, a);
		System.out.printf("The answer is %d\n", ans);
	}
	/*
	 * Main idea: When iterating through array, we need to keep track of the start of
	 * the longest sub array that has at most k negative elements at the current index.
	 * We also need to remember how many negative numbers (countNeg0 are in this sub array.
	 * If countNeg > k, then we need to increase start until countNeg <= k, i.e. when countNeg = k.
	 * Complexity: O(n), even though we have a nested for loop. In the worst case, the array will be traversed twice.
	 */
	static int longestSubarray(int n, int k, int[] a) {
		int start = 0;
		int countNeg = 0;
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] < 0) {
				countNeg++;
			}
			while (countNeg > k) {
				if (a[start] < 0) {
					countNeg--;
				}
				start++;
			}
			result = Math.max(result, i - start + 1);
		}
		return result;
	}

}
