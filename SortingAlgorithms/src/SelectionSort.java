import java.util.Scanner;

public class SelectionSort {
	// Complexity: O(n^2)
	// In-place
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
		selectionSort(a, n);
		printArray(a, n);
		sc.close();
	}
	public static void printArray(int[] a, int n) {
		System.out.print("Sorted array: ");
		for (int i = 0; i < n-1; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[n-1]);
	}
	// Returns index of the maximal element from sub-array a. Interval [l;r) 
	public static int findMax(int[] a, int l, int r) {
		int ind = l;
		for (int i = l; i < r; i++) {
			if (a[ind] < a[i]) {
				ind = i;
			}
		}
		return ind;
	}
	public static void selectionSort(int[] a, int n) {
		for (int j = 0; j < n; j++) {
			int k = findMax(a, 0, n-j);
			int tmp = a[k];
			a[k] = a[n-1-j];
			a[n-1-j] = tmp;
		}
	}
}
