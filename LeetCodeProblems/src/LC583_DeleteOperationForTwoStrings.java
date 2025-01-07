import java.util.Scanner;

public class LC583_DeleteOperationForTwoStrings {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		sc.close();
		LC583_DeleteOperationForTwoStrings program = new LC583_DeleteOperationForTwoStrings();
		System.out.println(program.minDistance2(s, t));
	}

	public int minDistance(String word1, String word2) {
		char[] s = getCharArray(word1);
		char[] t = getCharArray(word2);
		int n = word1.length();
		int m = word2.length();
		int[][] dp = new int[n+1][m+1]; // dp[i][j] - minimal number of deletions required to make strings s[1...i] t[1...j] equal
		// BASE:
		for (int i = 0; i <= n; i++) {
			dp[i][0] = i; 
		}
		for (int j = 0; j <= m; j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (word1.subSequence(0, i).equals(word2.subSequence(0, j))) {
					dp[i][j] = 0;
				} else {
					if (s[i] == t[j]) {
						dp[i][j] = dp[i-1][j-1];
					} else {
						dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
					}
				}
 			}
		}
		return dp[n][m];
	}
	public int minDistance2(String word1, String word2) {
		char[] s = getCharArray(word1);
		char[] t = getCharArray(word2);
		int n = word1.length();
		int m = word2.length();
		int[][] lcs = new int[n+1][m+1]; // dp[i][j] - minimal number of deletions required to make strings s[1...i] t[1...j] equal
		for (int i = 0; i <= n; i++) {
			lcs[i][0] = 0;
		}
		for (int j = 0; j <= m; j++) {
			lcs[0][j] = 0;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				if (s[i] == t[j]) {
					lcs[i][j] = 1 + lcs[i-1][j-1];
				}
			}
		}
		return n + m - 2 * lcs[n][m];
	}
	public char[] getCharArray(String s) {
		char[] c = s.toCharArray();
		int n = c.length;
		char[] ans = new char[n+1];
		for (int i = 0; i < n; i++) {
			ans[i+1] = c[i];
		}
		return ans;
	}

}
