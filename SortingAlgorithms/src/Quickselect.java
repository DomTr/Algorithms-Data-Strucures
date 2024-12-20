import java.util.Scanner;
public class Quickselect {
	/*
	 *  finds k-th (0-indexed) smallest element, elements don't have to be distinct.
	 *  partitions the array around k-th element.
	 *  First smallest element would be returned by kthSmallest(n, 0, a), where n - length of the array a.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		System.out.println(kthSmallest(n, k, a));
	}
	/**
	 * Finds k-th smallest element in an unsorted array using QuickSelect algorithm.
	 * 
	 * @param n - size of the array
	 * @param k - 0-based indexed of the element to find
	 * @param a - the input array of integers
	 * @return k-th smallest element of the array
	 */
	static int kthSmallest(int n, int k, int[] a) {
				if (k >= a.length || k < 0) {
					throw new IllegalArgumentException("Index k is out of bounds: " + k);
				}
				return quickselect(n, k, 0, n-1, a);
	}
	/**
	 *  QuickSelect algorithm implementation. Uses partition strategy from QuickSort class. Also uses two parameters l and r. Because of that partitions are taken in place and
	 *  there is no need in the case of k > i to write that the k-i-th smallest element is being searched for.
	 *  @param n - size of the array
	 * 	@param k - 0-based indexed of the element to find
	 * 	@param l - left bound of the array  (inclusive)
	 *  @param r - right bound of the array (inclusive)
	 * 	@param a - the input array of integers
	 * 	@return k-th smallest element of the array
	 * 	Worst-case runtime: O(n^2) - when pivots are chosen unluckily. 
	 *  T(n) <= T(n-1) + cn, then T(n) <= O(n^2)
	 */
	static int quickselect(int n, int k, int l, int r, int[] a) {
		// 1. select pivot
		// 2. i = partition(a, p)
		// 3. if k = i: return p, if k < i return QuickSelect(n, k, l, i-1, a) else return QuickSelect(n, k, k+1, r, a)
		int i = Quicksort.partitionStrategy(a, l, r);
		int p = a[i];
		if (i == k) {
			return p;
		} else if (k < i) {
			return quickselect(n, k, l, i-1, a);
		} else { // k > i
			return quickselect(n, k, i+1, r, a); // no need to write k-i because you search interval [l,r]
		}
	}
}
