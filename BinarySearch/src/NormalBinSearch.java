
public class NormalBinSearch {
	public static int binSearch(int x, int n, int[] a) {
		int l = 0;
		int r = n-1;
		boolean found = false;
		while (r-l >= 0) {
			int m = l + (r-l)/2;
			if (a[m] == x) {
				found = true;
				break;
			}
			else if (a[m] < x) {
				l = m + 1;
			}
			else {
				r = m - 1;
			}
		}
		if (found) {
			return l+(r-l)/2;
		}
		else return -1;
	}
}
