import java.util.Scanner;
public class BubbleSort {
	// A simple Bubble Sort algorithm. Run-time O(n^2)
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
		bubbleSort2(a, n);
		printArray(a, n);
		sc.close();
	}
	public static void bubbleSort1(int[]a , int n) {
		for (int j = 0; j < n-1; j++) {
			for (int i = 0; i < n-1-j; i++) {
				if (a[i] > a[i+1]) {
					int tmp = a[i];
					a[i] = a[i+1];
					a[i+1] = tmp;
				}
			}
		}
	}
	public static void bubbleSort2(int[] a, int n) {
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < n-1; i++) {
				if (a[i] > a[i+1]) {
					sorted = false;
					int tmp = a[i];
					a[i] = a[i+1];
					a[i+1] = tmp;
				}
			}
		}
	}
	public static void printArray(int[] a, int n) {
		System.out.print("Sorted array: ");
		for (int i = 0; i < n-1; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[n-1]);
	}

}
