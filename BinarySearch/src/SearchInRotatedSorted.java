import java.util.Scanner;
public class SearchInRotatedSorted {
	/*
	 * Finds an element in a (possibly) rotated sorted array.
	 * There are two methods. In the 1st one duplicates are not allowed. Worst-Case run time is O(log n)
	 * In the 2nd one duplicates are allowed. Worst-Case run time is O(n/2) = O(n), when for example all elements are equal.
	 * 
	 * If element `target` is not found, -1 is returned
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		int target = sc.nextInt();
		sc.close();
		int ind = searchNoDuplicates(nums, target);
		System.out.println(ind);
	}
	public static int searchNoDuplicates(int[] nums, int target) {
		int n = nums.length;
		int l = 0, r = n;
		if (nums[0] <= nums[n-1]) {
			while (r - l > 1) {
				int m = l + (r-l)/2;
				if (nums[m] > target) {
					r = m;
				}
				else {
					l = m;
				}
			}
			if (nums[l] == target) {
				return l;
			}
			return -1;
		}
		
		int k = findPivot(nums);
		if (target < nums[0]) { // should be searched in the first line
			l = k+1;
		}
		else {
			r = k+1; // should be searched in the second line
		}
		// after these if's, interval [l,r) is sorted
		while (r - l > 1) {
			int m = l + (r-l)/2;
			if (nums[m] <= target) {
				l = m;
			}
			else {
				r = m;
			}
		}
		if (nums[l] == target) {
			return l;
		}
		return -1;
	}
	public static int findPivot(int[] nums) {
		int n = nums.length;
		int l = 0, r = n;
		while (r - l > 1) {
			int m = l + (r-l)/2;
			if (nums[m] >= nums[l]) {
				l = m;
			}
			else {
				r = m;
			}
		}
		return l;
	}
	// Worst-case run time is O(n). This is when for example all elements are equal
	public static int searchWithDuplicates(int[] nums, int target) {
		int n = nums.length;
		int l = 0, r = n-1;
		while (r - l >= 0) {
			int m = l + (r-l)/2;
			if (nums[m] == target) {
				return m;
			}
			else if (nums[m] == nums[l] && nums[m] == nums[r]) {
				l++;
				r--;
			}
			else if (nums[m] >= nums[l]) { // left sorted
				if (nums[m] >= target && nums[l] <= target) {
					r = m - 1;
				}
				else {
					l = m + 1;
				}
			}
			else {
				if (nums[m] <= target && nums[r] >= target) {
					l = m + 1;
				}
				else {
					r = m - 1;
				}
			}
		}
		return -1;
	}
}
