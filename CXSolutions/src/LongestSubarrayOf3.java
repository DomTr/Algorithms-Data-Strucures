/*
 * Problem description:
 * A segment of non-empty subarray is 3-segmented if it consists of 3 consecutive segments.
 * I.e. it is of the form a,a,a,...,a.b,b...b,c,c,...,c
 * 
 * Given an array A of n integers, compute the length of its longest 3-segmented subarray.
 * For example, consider (5, 4, 4, 2, 2, 2, 3, 3). We have 4 segments: (5), (4, 4), (2, 2, 2), (3, 3). The longest 3-segmented subarray is (4, 4, 2, 2, 2, 3, 3).
 * (5, 2,2,2,3,3) is not a 3-segmented subarray because subarrays must be consecutive
 */

import java.util.Scanner;

public class LongestSubarrayOf3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = sc.nextInt();
		}
		sc.close();
		System.out.println(longestSubarray(n, A));
	}

	public static int longestSubarray(int n, int[] a) {
		return longestSubarray(n, a);
	}

	public static int longestSubarrayMySol(int n, int[] a) {
		int l = 0, r = 1, diff = 1, max = 0;
		for (; r < n; r++) {
			if (a[r] != a[r - 1]) {
				diff++;
			}
			if (diff == 3) {
				max = Math.max(max, r - l + 1);
			} else if (diff >= 4) {
				while (l < r) {
					if (a[l] != a[l + 1]) {
						l++;
						diff--;
						break;
					} else
						l++;
				}
			}
		}
		return max;
	}
	public static int longestSubarrayOff(int n, int[] a) {
	    int Seg1 = 1, Seg2 = 0, Seg3 = 0;
	    int max = 0;
	    for (int i = 1; i < n; i++) {
	      if (a[i] == a[i-1]) {
	        Seg1++;
	        if (Seg2 != 0) Seg2++;
	        if (Seg3 != 0) Seg3++;
	      } else {
	        Seg3 = Seg2 == 0 ? 0 : Seg2 + 1;
	        Seg2 = Seg1 + 1;
	        Seg1 = 1;
	      }
	      max = Math.max(max, Seg3);
	    }
	    return max;
	  }
}
