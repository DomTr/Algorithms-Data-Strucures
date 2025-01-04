import java.util.Scanner;

public class RemovalGame {
	// 4 4 5 1 3 ACC first try!
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] x = new long[n+1];
		for (int i = 1; i <= n; i++) {
			x[i] = sc.nextLong();
		}
		sc.close();
		System.out.println(solve(n, x));
	}
	public static long solve(int n, long[] x) {
		/*
		 *  Maximum possible score for the first player in the state dp[i][j], i.e. when there are x[i...j] coins left
		 *  It is an upper-diagonal matrix
		 */
		long[][] dp = new long[n+1][n+1]; 
		int rest = n % 2; // depending on the rest, the base cases will differ. If it's even, then diagonal entries are 0, otherwise it's x[i] because the first player will be then the last one to make the move.
		for (int i = 0; i <= n; i++) {
			dp[i][i] = rest == 0 ? 0 : x[i]; // when the state is (i, i), maximal winnable sum is 0
		}
		for (int j = 2; j <= n; j++) {
			for (int i = 1; i + j - 1 <= n; i++) {
				int row = i;
				int column = i + j - 1;
				if ((column - row + 1) % 2 == rest) { // player 1 turn, if there is an even amount of numbers.
					dp[row][column] = Math.max(dp[row+1][column] + x[row], dp[row][column-1] + x[column]);
				} else {
					dp[row][column] = Math.min(dp[row+1][column], dp[row][column-1]);
				}
			}
		}
		return dp[1][n];
	}

}
