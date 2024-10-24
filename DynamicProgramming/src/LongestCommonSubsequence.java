import java.util.Scanner;
public class LongestCommonSubsequence {
	// Returns Longest Common Subsequence
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		sc.close();
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();
		int m = a.length;
		int n = b.length;
		int[][] L = new int[m+1][n+1];
		for (int i = 0; i <= m; i++) {
			L[i][0] = 0;
		}
		for (int j = 0; j <= n; j++) {
			L[0][j] = 0;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a[i-1] == b[j-1]) {
					L[i][j] = Math.max(L[i-1][j-1] + 1, Math.max(L[i][j-1], L[i-1][j]));
				}
				else {
					L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
				}
			}
		}
		System.out.println(L[m][n]);
	}

}
