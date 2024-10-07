
public class LeftMost {
	/*
	 *  finds closest integer from the left in a sorted array b
	 *  Invariant: b[left] <= x always. Therefore the last value of left is returned
	 */
	public static int nearestToTheLeft(int x, int m, int[] b) {
	    int left = 0; 
	    int right = m;
	    while (right-left > 1) {
	      int mid = left + (right-left)/2;
	      if (b[mid] <= x) {
	        left = mid;
	      }
	      else {
	        right = mid;
	      }
	    }
	    return left;
	  }
}
