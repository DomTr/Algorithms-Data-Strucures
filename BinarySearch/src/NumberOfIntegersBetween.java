
public class NumberOfIntegersBetween {
	/*
	 * Given a sorted array A with n numbers, left-bound k_l, right- bound k_r output the number of elements x s.t. k_l <= x <= k_r.
	 * 
	 */
	public static int Range_Counting(int A[], int n, int k_l, int k_r) {
		int indexL = closestToTheRight(A, n, k_l);
		// System.out.println(indexL);
		int indexR = closestToTheLeft(A, n, k_r);
		// System.out.println(indexL + " " + indexR);
		return indexR - indexL + 1;
	}

	public static int rightExcluded(int[] a, int n, int x) {
		if (x > a[n - 1])
			return n;
		if (x < a[0])
			return 0;

		int l = 0, r = n - 1;
		while (r - l > 1) {
			int m = l + (r - l) / 2;
			// System.out.println("l: " + l + " r: " + r + " m: " + m);
			if (a[m] > x) {
				r = m;
			} else if (a[m] <= x) {
				l = m;
			}
		}
		return r;
	}

	public static int leftExcluded(int[] a, int n, int x) {
		if (x < a[0])
			return -1;
		if (x > a[n - 1])
			return n - 1;

		int l = 0, r = n - 1;
		while (r - l > 1) {
			int m = l + (r - l) / 2;
			if (a[m] >= x) {
				r = m;
			} else if (a[m] < x) {
				l = m;
			}
		}
		return l;
	}

	public static int closestToTheLeft(int[] a, int n, int x) {
		if (x < a[0])
			return -1;
		if (x > a[n - 1])
			return n - 1;
		int l = 0; // a[l] <= x
		int r = n; // a[r] > x
		while (r - l > 1) {
			int m = (l + (r - l) / 2);
			if (a[m] <= x) {
				l = m;
			} else {
				r = m;
			}
		}
		return l;
	}

	public static int closestToTheRight(int[] a, int n, int x) {
		if (x < a[0])
			return 0;
		if (x > a[n - 1])
			return n;
		int l = -1; // a[l] < x
		int r = n - 1; // a[r] >= x
		while (r - l > 1) { // searches the interval [l, r]
			int m = (l + (r - l) / 2);
			if (a[m] < x) {
				l = m;
			} else {
				r = m;
			}
		}
		return r;
	}
}
