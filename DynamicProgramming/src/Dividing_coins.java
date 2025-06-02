import java.util.ArrayList;
import java.util.Scanner;

public class Dividing_coins {
	/*
	 * Problem 
UVA: 562 - Dividing coins
Given a bag with a maximum of 100 coins, determine the most fair division between two persons.
This means that the difference between the amount each person obtains should be minimised. The
value of a coin varies from 1 cent to 500 cents. It’s not allowed to split a single coin. 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt(), totalSum = 0;
		ArrayList<Integer> weights = new ArrayList<>();
		weights.add(0);
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			weights.add(x);
			totalSum+=x;
		}
		sc.close();
		int closest = knapsack(m, weights, totalSum/2);
		System.out.println(totalSum - 2*closest);
	}

	public static int knapsack(int m, ArrayList<Integer> weights, int limit) {
		int[][] dp = new int[m + 1][limit + 1];
		for (int i = 0; i <= limit; i++) {
			dp[0][i] = 0;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 0; j <= limit; j++) {
				if (j - weights.get(i) >= 0) {
					dp[i][j] = Math.max(dp[i-1][j], weights.get(i) + dp[i-1][j- weights.get(i)]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[m][limit];
	}
	/*
	 * #include <iostream> #include <vector>
	 * 
	 * using namespace std;
	 * 
	 * int knapsack(int m, vector<int>& weights, int limit) { int dp[m+1][limit+1];
	 * for (int i = 0; i <= limit; i++) { dp[0][i] = 0; } for (int i = 1; i <= m;
	 * i++) { for (int j = 0; j <= limit; j++) { if (j - weights[i] >= 0) { dp[i][j]
	 * = max(dp[i-1][j], dp[i-1][j-weights[i]] + weights[i]); } else { dp[i][j] =
	 * dp[i-1][j]; } } } return dp[m][limit]; }
	 * 
	 * int main() { int t; cin >> t; while(t--) { int total_sum = 0; int m; cin >>
	 * m; vector<int> v(m+1); for (int i = 1; i <= m; i++) { cin >> v[i]; total_sum
	 * += v[i]; } int r = knapsack(m, v, total_sum/2); //cout << r << " "; r =
	 * total_sum - 2*r; cout << r << '\n'; } }
	 * 
	 * 
	 * 
	 * 
	 */
}
