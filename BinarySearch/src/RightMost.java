
public class RightMost {
	/*
	 * finds closest integer from the right in a sorted array b.
	 * Invariant: b[right] >= x always. Therefore the last value of right is returned.
	 */
	public static int nearestToTheRight(int pos, int m, int[] b) {
	    int left = -1; 
	    int right = m-1;
	    while (right-left > 1) {
	      int mid = left + (right-left)/2;
	      if (b[mid] >= pos) {
	        right = mid;
	      }
	      else {
	        left = mid;
	      }
	    }
	    return right;
	  }
}
