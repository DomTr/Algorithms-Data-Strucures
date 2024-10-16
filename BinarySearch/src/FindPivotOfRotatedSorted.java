import java.util.Scanner;
public class FindPivotOfRotatedSorted {
	/*
	 * Input: n >= 2
	 * Array A[1...n] containing n unique integers that satisfy the following property:
	 * There is an integer 1 <= k <= n-1 such that the sub arrays A[1...k] and A[k+1...n] are sorted (in ascending order)
	 * And A[n] < A[1].
	 * We call k the pivot.
	 * 
	 * Output: pivot k
	 * Runtime: O(log n)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		int pivot = find(a);
		System.out.println(pivot);
	}
	public static int find(int[] a) {
		int n = a.length;
		int l = 0; //check(l) = true
		int r = n; //check(r) = false
		if (a[0] > a[1]) {
			return 0;
		}
		while(r-l > 1) {
			int m = l + (r-l)/2;
			if (m == n - 1) {
				return -1; //there is no pivot. Array is sorted in ascending order.
			}
			if (a[m] > a[m-1] && a[m] > a[m+1]) {
				return m;
			}
			if (a[m] >= a[l]) {
				l = m;
			}
			else {
				r = m - 1;
			}
		}
		
		return l;
	}

}
