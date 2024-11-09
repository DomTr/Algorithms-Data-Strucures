
public class IterativeBinSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	static boolean predicate(int x) { // replace this with custom predicate
		return false;
	}
	/*
	 * This approach is based on interval jumping. Essentially, we start from the beginning of the array, make jumps, and reduce the jump length as we get closer to the target element. 
	 */
	static int binSearch(int lo, int hi) {
		lo--;
		for (int diff = hi - lo; diff > 0; diff/=2) {
			while (lo + diff <= hi && predicate(lo+diff)) {
				lo += diff;
			}
		}
		return lo;
	}
}
