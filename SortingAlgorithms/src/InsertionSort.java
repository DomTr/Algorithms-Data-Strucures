import java.util.Scanner;

public class InsertionSort {
	// run-time: O(n^2)
	// memory: O(1)
	/* finding the index can be done in linear time via findMinimalIndexIterative
	 * or in logarithmic time using binary search via method findMinimalIndexBinSearch.
	 * In the end this does not make much difference in terms of run-time because push() method is O(n).
	 * Therefore the run-time in both cases will be O(n^2)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Length of the array: ");
		int n = sc.nextInt();
		if (n <= 0) {
			System.out.println("Array length must be at least 1");
			sc.close();
			return;
		}
		System.out.println("Array: ");
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		insertionSort(a, n);
		printArray(a, n);
		sc.close();
	}
	public static void insertionSort(int[] a, int n) {
		for (int j = 1; j < n; j++) {
			int k = findMinimalIndexBinSearch(a, n, j);
			int x = a[j];
			if (k != -1) {
				push(a, k, j);
				a[k] = x;
			}
		}
	}
	public static void push(int[] a, int k, int j) {
		for (int i = j; i >= k+1; i--) {
			a[i] = a[i-1];
		}
	}
	public static int findMinimalIndexIterative(int[] a, int n, int j) {
		for (int i = 0; i < j; i++) {
			if (a[j] <= a[i]) {
				return i;
			}
		}
		return -1;
	}
	public static int findMinimalIndexBinSearch(int[] a, int n, int j) {
		int l = -1;  // a[l] false
		int r = j;   // a[r] true
		while(r-l > 1) {
			int m = l + (r-l)/2;
			if (a[m] >= a[j]) {
				r = m;
			}
			else { //a[m] < a[j]
				l = m;
			}
		}
		if (r == j) r = -1;//not found. This was done in order to be compliant with findMinimalIndexIterative implementation
		return r;
	}
	public static void printArray(int[] a, int n) {
		System.out.print("Sorted array: ");
		for (int i = 0; i < n-1; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[n-1]);
	}
}
