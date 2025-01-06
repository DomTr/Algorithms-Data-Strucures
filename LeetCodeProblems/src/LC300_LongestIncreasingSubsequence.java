import java.util.Arrays;
import java.util.Scanner;

public class LC300_LongestIncreasingSubsequence {
	/*
	 * test: 8 10 9 2 5 3 7 101 18
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		sc.close();
		LC300_LongestIncreasingSubsequence program = new LC300_LongestIncreasingSubsequence();
		System.out.println(program.lengthOfLIS(nums));
	}

	public int lengthOfLIS(int[] nums) {
		int n = nums.length;
		nums = normalizeArray(nums);
		/*
		 * LIS[i] - minimal number that a LIS of length i ends
		 */
		int[] lis = new int[n+1];
		Arrays.fill(lis, 20000); // 10000 is the smallest possible value according to the problem statement
		lis[0] = -10001; // BASE
		for (int i = 1; i <= n; i++) {
			int index = findIndex(n, lis, nums[i]);
			if (lis[index+1] > nums[i]) lis[index+1] = nums[i];
		}
		for (int i = n; i >= 0; i--) {
			if (lis[i] < 20000) return i;
		}
		return 0;
	}
	// IMPORTANT: we can use binary search because lis is sorted!
	public int findIndex(int n, int[] lis, int x) { // finds right-most index i, s.t. lis[i] < x
		int l = 0, r = n+1; // invariant: lis[l] < x && lis[r] >= x
		while (r - l > 1) {
			int m = l + (r-l)/2;
			if (lis[m] < x) {
				l = m;
			} else  {
				r = m;
			}
		}
		return l;
	}
	public int[] normalizeArray(int[] arr) {
		int n = arr.length;
		int[] ans = new int[n+1];
		for (int i = 0; i < n; i++) {
			ans[i+1] = arr[i]; 
		}
		return ans;
	}
}
