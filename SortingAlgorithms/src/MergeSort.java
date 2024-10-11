import java.util.Scanner;

public class MergeSort {
	// Run time: O(nlogn)
	// Memory: O(nlogn)
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
		mergeSort(a, n);
		printArray(a, n);
		sc.close();
	}
	public static void mergeSort(int[] a, int n) {
		mergeSort(a, 0, n-1);
	}
	public static void mergeSort(int[] a, int l, int r) {
		if (l < r) {
			int m = l + (r-l)/2;
			mergeSort(a, l, m);
			mergeSort(a, m+1, r);
			int[]b = merge(a, l, m, r);
			for (int i = l, k = 0; i <= r; i++, k++) {
				a[i] = b[k];
			}
		}
	}
	public static int[] merge(int[] a, int l, int m, int r) {
		int[] b = new int[a.length];
		int i = l, j = m+1, k = 0;
		while(i <= m && j <= r) {
			if (a[i] < a[j]) {
				b[k] = a[i];
				i++;
			} else {
				b[k] = a[j];
				j++;
			}
			k++;
		}
		while (i <= m) {
			b[k] = a[i];
			i++;
			k++;
		}
		while (j <= r) {
			b[k] = a[j];
			j++;
			k++;
		}
		return b;
	}
	public static void printArray(int[] a, int n) {
		System.out.print("Sorted array: ");
		for (int i = 0; i < n-1; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[n-1]);
	}

}
