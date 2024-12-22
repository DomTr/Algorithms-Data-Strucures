import java.util.Arrays;
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
				} else {
					//return quickselect(n, k, 0, n-1, a);
					return quickselectLinear(n, k, a);
				}
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
			return p; // returning an index isn't that easy because the array get's changed in the process.
		} else if (k < i) {
			return quickselect(n, k, l, i-1, a);
		} else { // k > i
			return quickselect(n, k, i+1, r, a); // no need to write k-i because you search interval [l,r]
		}
	}

	static int quickselectLinear(int n, int k, int[] a) {
		// 1. Divide into 5-er Gruppen
		// 2. Find median of each group A'
		// 3. p = Median of A' (with quickselectLinear)
		// 4. i = partition(A[1...n],p)
		// 5. if k = i: return a[k]
		// 6. else if k < i: return quickselectLinear(i, a[0...i-1], k)
		// 7. else return quickselectLinear(n-i, a[i+1...length], k-i)
		
		// Steps 1 and 2:
		if (n == 1) {
			return a[0];
		}
		int[] A = mediansOfSubgroups(n, a);
		int p = quickselectLinear(A.length, A.length/2, A); // find median
		int i = partitionHoare(a, 0, n-1, p);
		if (k == i) return a[k];
		else if (k < i) {
			int[] newArray = Arrays.copyOfRange(a, 0, i);
			return quickselectLinear(newArray.length, k, newArray);
		}
		else {
			int[] newArray = Arrays.copyOfRange(a, i, n);
			return quickselectLinear(newArray.length, k-i, newArray);
		}
	}
	
	public static int[] mediansOfSubgroups(int n, int[] a) {
		int[] A = new int[(n+4)/5];
		int k = 0;
		for (int i = 0; i < n; i+=5) {
			int left = i, right = Math.min(left+4, n);
			sortGroup(a, left, right);
			int medianIndex = (right-left)/2 + left;
			A[k] = a[medianIndex];
			k++;
		}
		return A;
	}
	public static void sortGroup(int[] a, int left, int right) {
		boolean ok = false;
		while (!ok) {
			ok = true;
			for (int i = left; i < right-1; i++) {
				if (a[i] > a[i+1]) {
					swap(a, i, i+1);
					ok = false;
				}
				
			}
		}
	}
	public static int partitionHoare(int[] a, int l, int r, int p) {
		// Find pivot and swap it to the right place
		// Have left and right pointer. Find a suitable pair (i, j) with a[i] > p && a[j] <= p to swap with
		int i = l;
		int j = r-1;
		/* Nice trick: choosing a pivot, swapping it with the right-most element and then performing swaps
		 * Code only for the case, when pivot is the right-most element is relevant. One can choose actually any pivot, but then do not forget it to swap with the right-most
		 * Same trick applied in Lomuto's partition
		 */
		int pivotIndex = findIndex(a.length, a, p);
		swap(a, pivotIndex, r);
		
		do {
			while (i < r && a[i] <= p) {
				i++;
			}
			while (j > l && a[j] > p) {
				j--;
			}
			if (i < j) {
				swap(a, i, j);
			}
		} while (i < j);
		swap(a, i, r); //always swap with the right, it is the index of the pivot because we swapped at the beginning.
		return i;
	}
	public static int findIndex(int n, int[] a, int p) {
		for (int i = 0; i < n; i++) {
			if (a[i] == p) {
				return i;
			}
		}
		return -1;
	}
	public static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
