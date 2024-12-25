// Leetcode problem
public class TargetSum {

	public static void main(String[] args) {
		// int[] nums = new int[] {1};
		//int[] nums = new int[] {1,1,1,1,1};
		int[] nums = new int[] {1000};
		int target = 1000;
		int ans = findTargetSumWays(nums, target);
		System.out.println(ans);
	}

	public static int findTargetSumWays(int[] nums, int target) {
		int N = nums.length;
		nums = getNewNums(nums);
		//dp[i][j] - number of ways to get sum j using elements from 1..i
		int zero = 3000;
		int[][] dp = new int[N+1][7000];//1500 is 0. Now we work with positive numbers
		
		dp[0][zero] = 1;
		for (int i = 1; i <= N; i++) {
			for (int sum = -1001; sum <= 1400; sum++) {
				// either add newNums[i] or subtract newNums[i]
				dp[i][sum+zero] = dp[i-1][sum+zero-nums[i]] + dp[i-1][sum+zero+nums[i]];
			}
		}
		return dp[N][target+zero];
	}
	public static int[] getNewNums(int[] nums) {
		int N = nums.length;
		int[] newNums = new int[N+1];
		for (int i = 1; i <= N; i++) {
			newNums[i] = nums[i-1];
		}
		return newNums;
	}

}
