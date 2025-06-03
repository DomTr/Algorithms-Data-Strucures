
public class TernarySearch {
	public static void main(String[] args) {
		int[] arr = new int[] {1, 4, 5, 19, 22};
		int x = -29;
		System.out.println(ternarySearch(arr, x));
	}
	public static int ternarySearch(int[] array, int x) {
		return ternarySearch(array, x, 0, array.length - 1);
	}
	
	private static int ternarySearch(int[] array, int x, int l, int r) {
		if (l > r)
			return -1;
		int partitionSize = (r - l) / 3;
		int pivotLeft = l + partitionSize;
		int pivotRight = r - partitionSize;

		if (array[pivotLeft] == x)
			return pivotLeft;
		if (array[pivotRight] == x)
			return pivotRight;
		if (x < array[pivotLeft]) {
			return ternarySearch(array, x, l, pivotLeft - 1);
		}
		if (x > array[pivotRight]) {
			return ternarySearch(array, x, pivotRight + 1, r);
		}
		return ternarySearch(array, x, pivotLeft + 1, pivotRight - 1);
	}
	/*
	 * One of the two must apply
	 * 1. f strictly increases, reaches its maximum and starts decreasing
	 * 2. f strictly decreases, reaches its minimum and starts increasing
	 * Then ternary search is applicable for searching for the golden section
	 */
	static double f(double x) {
		return 0;
	}
	public static double ternarySearch(double l, double r) {
		for (int it = 1; it <= 100; it++) { // could be replaced with while(r - l > eps), where eps = 1e-6
			double m1 = l + (r-l)/3;
			double m2 = r - (r-l)/3;
			double f1 = f(m1);
			double f2 = f(m2);
			if (f1 < f2) {
				l = m1;
			} else {
				r = m2;
			}
		}
		return f(l);
	}
}
