import java.util.Arrays;
import java.util.Scanner;
public class Quickselect {
	/*
	 *  finds k-th (0-indexed) smallest element, elements don't have to be distinct.
	 *  partitions the array around k-th element.
	 *  First smallest element would be returned by kthSmallest(n, 0, a), where n - length of the array a.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		System.out.println(kthSmallest(n, k, a));
	}
	/**
	 * Finds k-th smallest element in an unsorted array using QuickSelect algorithm.
	 * 
	 * @param n - size of the array
	 * @param k - 0-based indexed of the element to find
	 * @param a - the input array of integers
	 * @return k-th smallest element of the array
	 */
	static int kthSmallest(int n, int k, int[] a) {
				if (k >= a.length || k < 0) {
					throw new IllegalArgumentException("Index k is out of bounds: " + k);
				} else {
					//return quickselect(n, k, 0, n-1, a);
					return quickselectLinear(n, k, a);
				}
	}
	/**
	 *  QuickSelect algorithm implementation. Uses partition strategy from QuickSort class. Also uses two parameters l and r. Because of that partitions are taken in place and
	 *  there is no need in the case of k > i to write that the k-i-th smallest element is being searched for.
	 *  @param n - size of the array
	 * 	@param k - 0-based indexed of the element to find
	 * 	@param l - left bound of the array  (inclusive)
	 *  @param r - right bound of the array (inclusive)
	 * 	@param a - the input array of integers
	 * 	@return k-th smallest element of the array
	 * 	Worst-case runtime: O(n^2) - when pivots are chosen unluckily. 
	 *  T(n) <= T(n-1) + cn, then T(n) <= O(n^2)
	 */
	static int quickselect(int n, int k, int l, int r, int[] a) {
		// 1. select pivot
		// 2. i = partition(a, p)
		// 3. if k = i: return p, if k < i return QuickSelect(n, k, l, i-1, a) else return QuickSelect(n, k, k+1, r, a)
		int i = Quicksort.partitionStrategy(a, l, r);
		int p = a[i];
		if (i == k) {
			return p; // returning an index isn't that easy because the array get's changed in the process.
		} else if (k < i) {
			return quickselect(n, k, l, i-1, a);
		} else { // k > i
			return quickselect(n, k, i+1, r, a); // no need to write k-i because you search interval [l,r]
		}
	}

	static int quickselectLinear(int n, int k, int[] a) {
		// 1. Divide into 5-er Gruppen
		// 2. Find median of each group A'
		// 3. p = Median of A' (with quickselectLinear)
		// 4. i = partition(A[1...n],p)
		// 5. if k = i: return a[k]
		// 6. else if k < i: return quickselectLinear(i, a[0...i-1], k)
		// 7. else return quickselectLinear(n-i, a[i+1...length], k-i)
		// https://people.csail.mit.edu/rivest/pubs/BFPRT73.pdf
		
		// Steps 1 and 2: O(n). Median of <=5 elements could be found in constant time. In this implementation the array of segment of <= 5 elements is sorted and then the element in the middle is returned.
		// In the better implementations no sorting would be necessary, but since sorting is performed on the initial array, maybe next time it is easier to sort and to find the median of each sub-group.
		// + cn
		if (n == 1) {
			return a[0];
		}
		int[] A = mediansOfSubgroups(n, a);
		// Step 3:
		int p = quickselectLinear(A.length, A.length/2, A); // find median +T(n/5)
		
		// Step 4: 
		int i = partitionHoare(a, 0, n-1, p); // cn
		// Final steps 5, 6, 7
		if (k == i) return a[k];
		else if (k < i) {
			int[] newArray = Arrays.copyOfRange(a, 0, i);
			return quickselectLinear(newArray.length, k, newArray); // <= + T(7/10n)
		}
		else {
			int[] newArray = Arrays.copyOfRange(a, i, n);
			return quickselectLinear(newArray.length, k-i, newArray); // <= + T(7/10n)
		}
		
		// IN TOTAL: T(n) <= cn + T(n/5) + T(7/10n)
		/*
		 * Claim: T(n) <= 10cn
		 * I.B. T(1) <= c1n <= 10cn for our chosen c > 0
		 * I.H. for all k <= n-1 T(k) <= 10ck
		 * I.S. T(n) <= cn + T(n/5) + T(7/10n) <{I.H.}= cn + cn/5 * 10 + cn7/10 * 10 = cn + 2cn + 7cn = 10cn.
		 *  WHERE DOES 7/10 n come from? - It is the longest possible length of the array which is left after not considering medians and what is bigger or smaller than them.
		 *  If we take groups of 5 and have that k < i, we know that k-th element is less than every median of the first half of the median array, i.e. it is bigger than half of the medians. 
		 *  Since every median m has additional 2 elements which are bigger than m, we must have
		 *  that k-th smallest number is less than 1/2 * (1 + 2)/5n = 3/10 n elements of the whole array, i.e. we have 7/10n array left to explore.
		 *  Same reasoning applies in the case of k > i. Then we don't have to consider all medians of the first half of the 5-groups and 2 elements that come before that median and for each median in the second half we don't have to consider next 2 elements.
		 *  
		 * If we divide in subgroups of length 3 or 4, this will not work.
		 * Then we would have to prove that T(n) <= kcn for some k. For the case of 3:
		 * we know that half of the medians don't work and for each median there is an element in the 3-group which we can throw away, i.e. we don't have to consider 1/2 * (1+1)/3 = 1/3 elements, then we have to consider the rest: 1 - 1/3 = 2/3 elements.
		 * However, we have that T(n) <= cn + T(n/3) + T(2/3n). And in this case we won't be able to find any k:
		 * T(n) <= cn + T(n/3) + T(2/3n) <{I.H.}= cn + kcn/3 + 2kcn/3 = cn + kcn
		 * 
		 * What happens if we divide in groups of 10 or more? - We will be able to find such k, however, the finding median of each subgroup logic will be much more difficult to implement for arrays with bigger length.
		 * If we take division in groups of 10, we have that we can cancel 1/2*1/10*5n = 5/20n elements, then we have 15/20n elements left to check.
		 * T(n) <= cn + T(n/10) + T(15/20n) <{I.H.}= cn + ckn/10 + ckn15/20 = cn + ckn17/20  <= ckn for for k >= 20/3, i.e. for some k = 7.
		 * We can divide in bigger subgroups, then finding median part of each sub-group is slower, but T(n) has a smaller constant factor. 
		 */
	}
	
	public static int[] mediansOfSubgroups(int n, int[] a) {
		int[] A = new int[(n+4)/5];
		int k = 0;
		for (int i = 0; i < n; i+=5) {
			int left = i, right = Math.min(left+4, n);
			sortGroup(a, left, right);
			int medianIndex = (right-left)/2 + left;
			A[k] = a[medianIndex];
			k++;
		}
		return A;
	}
	public static void sortGroup(int[] a, int left, int right) {
		boolean ok = false;
		while (!ok) {
			ok = true;
			for (int i = left; i < right-1; i++) {
				if (a[i] > a[i+1]) {
					swap(a, i, i+1);
					ok = false;
				}
				
			}
		}
	}
	public static int partitionHoare(int[] a, int l, int r, int p) {
		// Find pivot and swap it to the right place
		// Have left and right pointer. Find a suitable pair (i, j) with a[i] > p && a[j] <= p to swap with
		int i = l;
		int j = r-1;
		/* Nice trick: choosing a pivot, swapping it with the right-most element and then performing swaps
		 * Code only for the case, when pivot is the right-most element is relevant. One can choose actually any pivot, but then do not forget it to swap with the right-most
		 * Same trick applied in Lomuto's partition
		 */
		int pivotIndex = findIndex(a.length, a, p);
		swap(a, pivotIndex, r);
		
		do {
			while (i < r && a[i] <= p) {
				i++;
			}
			while (j > l && a[j] > p) {
				j--;
			}
			if (i < j) {
				swap(a, i, j);
			}
		} while (i < j);
		swap(a, i, r); //always swap with the right, it is the index of the pivot because we swapped at the beginning.
		return i;
	}
	public static int findIndex(int n, int[] a, int p) {
		for (int i = 0; i < n; i++) {
			if (a[i] == p) {
				return i;
			}
		}
		return -1;
	}
	public static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
