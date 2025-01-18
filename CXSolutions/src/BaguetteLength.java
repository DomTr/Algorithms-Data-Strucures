import java.util.HashMap;
import java.util.Scanner;

public class BaguetteLength {
	/*
	 * Problem: given a baguette of length L, an array of possible cuts of baguette l[0...n-1], and the prices for each baguette, find the most optimal cut
	 * i.e. the cut that if the cut parts were sold, they would bring the biggest profit
	 * Problem is similar to knapsack except that the same item can be taken many times. This makes the runtime longer. i.e. potentially O(nL^2) because if l = [1,1,1,1,...,1], then the innter while loop runs L times. 
	 * The first runs n times, the second L, the third also L. In total: nL^2
	 * @param	n - length of the array
	 * @param	L - length of the baguette
	 * @param	l - possible cuts array of size n. (1-indexed). Positive array
	 * @param	p - profit array for each cut l[i]. Positive array
	 */
	/*
	 3 121
	 60 26 20
	 25 14 10
	 => 62
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int L = sc.nextInt();
		int[] l = new int[n+1];
		int[] p = new int[n+1];
		for (int i = 1; i <= n; i++) {
			l[i] = sc.nextInt();
		}
		for (int i = 1; i <= n; i++) {
			p[i] = sc.nextInt();
		}
		sc.close();
		HashMap<Integer, Integer> sol = solution(n, L, l, p);
		System.out.println(getSum(sol));
		printSolution(sol);
	}
	
	static int getSum(HashMap<Integer, Integer> a) {
		int sum = 0;
		for (var entry : a.entrySet()) {
			sum += entry.getKey()*entry.getValue();
		}
		return sum;
	}
	static void printSolution(HashMap<Integer, Integer> sol) {
		StringBuilder sb = new StringBuilder();
		sol.forEach((key, value) -> {
			sb.append("price: " + key + ", amount: " + value + "\n");
		});
		System.out.println(sb.toString());
	}
	static HashMap<Integer, Integer> solution(int n, int L, int[]l, int[]p) {
		int[] dp = new int[L+1];//dp[0...L] = {0} base cases
		// dp[x] - maximum profit if at most length x of the baguette is used.
		for (int i = 1; i <= n; i++) {
			for (int x = 0; x <= L; x++) {
				int k = 1;
				while (x - k * l[i] >= 0) {
					dp[x] = Math.max(dp[x], dp[x-k*l[i]] + k*p[i]);
					k++;
				}
			}
		}
		// System.out.println(dp[L]);
		// decoding the solution, i.e. finding all l[i] that were used for optimal answer
		int[] setAmount = new int[n+1];
		for (int g = L; g >= 0; g--) {
			for (int i = 1; i <= n; i++) {
				int k = g/l[i];
				while (k >= 1 && dp[g-k * l[i]] + k*p[i] != dp[g]) {
					k--;
				}
				if (k <= 0) continue;
				g = g - k*l[i];
				setAmount[i] = k;
			}
		}
		HashMap<Integer, Integer> ans = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			if (setAmount[i] > 0) {
				ans.put(p[i], setAmount[i]);
			}
		}
		return ans;
	}
}
