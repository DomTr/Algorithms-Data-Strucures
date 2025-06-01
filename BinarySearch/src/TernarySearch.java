
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
}
