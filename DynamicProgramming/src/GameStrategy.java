import java.util.Scanner;

/*
 * Problem description:
 * You're given an array of n integers called coins, n is even. Game is played by two players. One player chooses whether to take the element from the beginning or the end.
 * Find maximal sum that can definitely be achieved by the first player.
 * 
 * Solution: dp[i][j] 1<= i <= j <= n tells maximum sum the first player can collect from coins[i...j].
 * Test:
 * 4 12 4 6 10 => 18 14
 * 4 1 2 1 2 => 4 2
 * 6 1 1 1 1 4 4 => 6 6
 * If a machine was given a state with coins[i..j], then it can decide what's the maximum sum first player will definitely win
 */
public class GameStrategy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		int answerFirst = solveFirst(n, a);
		int answerSecond = solveSecond(n, a);
		System.out.println(answerFirst + " " + answerSecond);
	}

	public static int solveFirst(int n, int[] coins) {
		int[][] dp = new int[n+1][n+1]; // represents the maximum value the first player can collect from the remaining coins (i, . . . , j).
		// Base cases: diagonal entries
		for (int i = 1; i <= n; i++) {
			dp[i][i] = 0;
		}
		for (int j = 2; j <= n; j++) {
			for (int delta = 1; delta <= n - j + 1; delta++) {
				if ((delta + j + delta - 1) % 2 == 1) { // player 1 turn
					dp[delta][j+delta-1] = Math.max(coins[delta] + dp[delta+1][j+delta-1], coins[j+delta-1]+ dp[delta][j+delta-2]);
				} else { 
					// player 2 turn
					/*
					 * Explanation: player 2 has 2 choices. First one is to take coins[delta], 2nd is taking coins[j+delta-1]
					 * In 1st case, player 1 will have dp[delta+1][j+delta-1] sum, in the 2nd dp[delta][j+delta-2]
					 * These values are known, player two will take delta or j+delta-1 element depending on which dp value is smaller, i.e. in which case will player 1 be able to achieve a smaller sum
					 * The objective of player 2 is to minimize player 1 sum.
					 */
					dp[delta][j+delta-1] = Math.min(dp[delta+1][j+delta-1], dp[delta][j+delta-2]);
				}
			}
		}
		return dp[1][n];
	}
	// trivial solution: totalSum - solve(n, coins)
	public static int solveSecond(int n, int[] coins) {
		int[][] dp = new int[n+1][n+1]; // represents the maximum value the 2nd player can collect from the remaining coins (i, . . . , j).
		// Base cases: diagonal entries
		/*
		for (int i = 1; i <= n; i++) {
			dp[i][i] = 0;
			// if (i+1 <= n) dp[i][i+1] = 0;
		}
		*/
		// This one works
		/*
		for (int j = 2; j <= n; j++) {
			for (int delta = 1; delta <= n - j + 1; delta++) {
				if ((delta + j + delta - 1) % 2 == 1) { // player 1 turn
					dp[delta][j+delta-1] = Math.min(coins[delta] + dp[delta+1][j+delta-1], coins[j+delta-1]+ dp[delta][j+delta-2]);
				} else { // player 2 turn
					dp[delta][j+delta-1] = Math.max(dp[delta+1][j+delta-1], dp[delta][j+delta-2]);
				}
			}
		}
		*/
		// BASE CASE WAS WRONG: if there is one coin left, it is 2nd player's turn, so they will just take that coin.
		/*
		 * The rest of the solution is analogue to the solveFirst one
		 */
		for (int i = 1; i <= n; i++) {
			dp[i][i] = coins[i];
		}
		for (int j = 2; j <= n; j++) {
			for (int delta = 1; delta <= n - j + 1; delta++) {
				if ((delta + j + delta - 1) % 2 == 0) { // player 2 turn
					dp[delta][j+delta-1] = Math.max(coins[delta] + dp[delta+1][j+delta-1], coins[j+delta-1]+ dp[delta][j+delta-2]);
				} else { 
					// player 1 turn
					/*
					 */
					dp[delta][j+delta-1] = Math.min(dp[delta+1][j+delta-1], dp[delta][j+delta-2]);
				}
			}
		}
		return dp[1][n];
	}

}
