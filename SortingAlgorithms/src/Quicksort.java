import java.util.Scanner;

public class Quicksort {
	/*
	 *  QuickSort algorithm:
	 *  Runtime: O(n^2)
	 *  Takes last element as the pivot element.
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
	public static void quicksort(int[] a, int l, int r) {
		if (l < r) {
			int k = partitionInPlace(a, l, r);
			quicksort(a, l, k-1);
			quicksort(a, k+1, r);
		}
	}
	public static int partitionInPlace(int[] a, int l, int r) {
		// Find pivot and swap it to the right place
		// Have left and right pointer. Find a suitable pair (i, j) with a[i] > p && a[j] <= p to swap with
		int i = l;
		int j = r-1;
		int p = a[r];
		while (i <= j) {
			while (i < r && a[i] <= p) {
				i++;
			}
			while (j >= l && a[j] > p) {
				j--;
			}
			if (i < j) {
				swap(a, i, j);
			}
		}
		swap(a, i, r);
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
