import java.util.Scanner;
public class HeapSort {

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
		heapSort(a, n);
		printArray(a, n);
		sc.close();
	}
	public static void heapSort(int[] a, int n) {
		int[] H = heapify(a, n);
		for (int i = n-1; i >= 0; i--) {
			a[i] = extractMax(H, i+1);
		}
	}	
	public static int[] heapify (int[] a, int n) {
		int[] H = new int[n];
		H[0] = a[0];
		for (int i = 1; i < n; i++) {
			H[i] = a[i];
			int currInd = i;
			while(currInd > 0) {
				int parentInd = (currInd-1)/2;
				if (H[parentInd] < H[currInd]) {
					swap(H, parentInd, currInd);
					currInd = parentInd;
				}
				else break;
			}
		}
		return H;
	}
	
	public static int extractMax(int[] H, int n) {
		int ans = H[0]; //Wurzel
		H[0] = H[n-1];
		n--;
		int currInd = 0;
		// sift-down
		while(2*currInd+1 < n) {
			int leftChild = 2*currInd + 1;
			int rightChild = 2 * currInd + 2;
			if (rightChild > n-1) {
				if (H[leftChild] > H[currInd]) {
					swap(H, leftChild, currInd);
				}
				else break; // Heap condition restored
			}
			else if (H[leftChild] >= H[rightChild] && H[currInd] < H[leftChild]) {
				swap(H, leftChild, currInd);
				currInd = leftChild;
			}
			else if (H[rightChild] > H[leftChild] && H[currInd] < H[rightChild]) {
				swap(H, rightChild, currInd);
				currInd = rightChild;
			}
			else break;
		}
		return ans;
	}
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	public static void printArray(int[] a, int n) {
		System.out.print("Sorted array: ");
		for (int i = 0; i < n-1; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[n-1]);
	}

}
