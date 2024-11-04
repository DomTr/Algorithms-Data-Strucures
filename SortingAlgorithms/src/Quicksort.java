import java.util.Random;
import java.util.Scanner;

public class Quicksort {
	/*
	 *  QuickSort algorithm:
	 *  Runtime: O(n^2) Worst-case. On average: O(n log n)
	 *  Can take any element as the pivot element. How to choose it needs to be declared in choosePivotStrategy method
	 *  Can partition in 3 ways: Hoare's partition, Lomuto's and the one which is not in-place.
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
		quicksort(a, n);
		printArray(a, n);
		sc.close();
	}
	public static void quicksort(int[] a, int n) {
		quicksort(a, 0, n-1);
	}
	public static int partitionStrategy(int[] a, int l, int r) {
		return partitionLomuto(a, l, r);
	}
	static int choosePivotStrategy(int[] a, int l, int r) {
		return choosePivotMedianFrom3(a, l, r);
	}
	public static void quicksort(int[] a, int l, int r) {
		if (l < r) {
			int k = partitionStrategy(a, l, r);
			quicksort(a, l, k-1);
			quicksort(a, k+1, r);
		}
	}
	
	static int choosePivotRand(int l, int r) {
		// Doesn't need a[] as a parameter.
		int interval = r-l;
		Random rand = new Random();
		return l+rand.nextInt(interval);
	}
	// Strategy for the sake of completeness,
	static int choosePivotRight(int l, int r) {
		return r;
	}
	/*
	 *  Takes first {x} , middle {y} and the last {z} elements and returns the index of the one which is in between
	 *  If the length is less than 3, then the right-most element is returned
	 */ 
	static int choosePivotMedianFrom3(int[] a, int l, int r) {
		int interval = (r-l);
		if (interval+1<= 2) {
			return l+interval;
		}
		int x = a[l], y = a[l + interval/2], z = a[r];
		if ((x < y && x > z) || (x > y && x < z)) {
			return l;
		}
		if ((y < x && y > z) || (y > x && y < z)) {
			return l + interval/2;
		}
		return r;
	}
	// For test case 3 doesn't work.
	public static int partitionHoare(int[] a, int l, int r) {
		// Find pivot and swap it to the right place
		// Have left and right pointer. Find a suitable pair (i, j) with a[i] > p && a[j] <= p to swap with
		int i = l;
		int j = r-1;
		/* Nice trick: choosing a pivot, swapping it with the right-most element and then performing swaps
		 * Code only for the case, when pivot is the right-most element is relevant. One can choose actually any pivot, but then do not forget it to swap with the right-most
		 * Same trick applied in Lomuto's partition
		 */
		int pivotIndex = choosePivotStrategy(a, l, r);
		int p = a[pivotIndex];
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
	public static int partitionLomuto(int[] a, int l, int r) {
		int pivotIndex = choosePivotStrategy(a, l, r);
		int pivot = a[pivotIndex];
		swap(a, pivotIndex, r);
		int i = l;
		int j;
		for (j = l; j <= r-1; j++) {
			if (a[j] < pivot) {
				swap(a, i, j);
				i++;
			}
		}
		swap(a, i, j);
		return i;
	}
	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	public static int partition(int[] a, int l, int r) {
		int p = a[r]; // choosing the last element as pivot
		int k = numberOfLEQElements(a, p, l, r-1);
		int[] b = new int[r-l+1];
		b[k] = p;
		{
			int i = 0, j = k+1;
			for (int s = l; s <= r-1; s++) {
				if (a[s] <= p) {
					b[i] = a[s];
					i++;
				}
				else {
					b[j] = a[s];
					j++;
				}
			}
		}
		for (int i = 0; i < b.length; i++) {
			a[i+l] = b[i];
		}
		return l+k;
	}
	// number of less than or equal to elements
	public static int numberOfLEQElements(int[] a, int p, int l, int r) {
		int cnt = 0;
		for (int i = l; i <= r; i++) {
			if (a[i] <= p) {
				cnt++;
			}
		}
		return cnt;
	}
	public static void printArray(int[] a, int n) {
		System.out.print("Sorted array: ");
		for (int i = 0; i < n-1; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[n-1]);
	}
}
