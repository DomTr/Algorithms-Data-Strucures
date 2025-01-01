import java.util.Scanner;

public class LC_516_LongestPalindromicSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		sc.close();
		System.out.println(longestPalindromeSubseq(s));
	}
	public static int longestPalindromeSubseq(String s) {
        char[] seq1 = convertToCharArray(s);
        char[] seq2 = getReversedCharArray(s); // LCS with reversed subsequence
        int n = seq1.length-1;
        // dp[i][j] - longestPalindromicSubseq length between substrings seq1[1...i] and seq2[1...j].
        /* runtime: O(n*n)
         * ANSWER: dp[n][n]
        */
        int[][] dp = new int[n+1][n+1];
        // BASE CASE:
        for (int i = 0; i <= n; i++) {
        	dp[0][i] = 0;
        }
        for (int i = 0; i <= n; i++) {
        	dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
        	for (int j = 1; j <= n; j++) {
        		dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        		if (seq1[i] == seq2[j]) {
        			dp[i][j] = Math.max(dp[i][j], 1 + dp[i-1][j-1]);
        		}
        	}
        }
        return dp[n][n];
    }
    public static char[] convertToCharArray(String s) {
        char[] c = s.toCharArray();
        char[] ans = new char[c.length+1];
        for (int i = 0; i < c.length; i++) {
            ans[i+1] = c[i];
        }
        return ans;
    }
    public static char[] getReversedCharArray(String s) {
    	char[] c = s.toCharArray();
    	int n = c.length;
    	char[] ans = new char[n+1];
    	for (int i = n-1, k = 1; i >= 0; i--, k++) {
    		ans[k] = c[i];
    	}
    	return ans;
    }
}
