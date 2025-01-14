import java.util.Scanner;

public class LC712_MinimumASCIIDeleteSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		sc.close();
		LC712_MinimumASCIIDeleteSum program = new LC712_MinimumASCIIDeleteSum();
		System.out.println(program.minimumDeleteSum(s, t));
	}

	public int minimumDeleteSum(String s1, String s2) {
		char[] s = getCharArray(s1);
		char[] t = getCharArray(s2);
		int n = s1.length();
		int m = s2.length();
		/*
		 * deleteDist[i][j] - minimal ASCII sum to change substring s[1..i] to t[1..j]
		 */
		int[][] deleteDist = new int[n+1][m+1];
		deleteDist[0][0] = 0;
		for (int i = 1; i <= n; i++) {
			deleteDist[i][0] = s[i] + deleteDist[i-1][0];
		}
		for (int j = 1; j <= m; j++) {
			deleteDist[0][j] = t[j] + deleteDist[0][j-1];
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				/*
				 * Either take s[i] and deleteDist[i-1][j] or t[j] and deleteDist[i][j-1]
				 */
				deleteDist[i][j] = Math.min(s[i] + deleteDist[i-1][j], t[j] + deleteDist[i][j-1]); 
				if (s[i] == t[j]) {
					deleteDist[i][j] = Math.min(deleteDist[i][j], deleteDist[i-1][j-1]);
				} 
			}
		}
		return deleteDist[n][m];
	}
	public int asciiSum(String s) {
		char[] c = s.toCharArray();
		int ans = 0;
		for (char ch : c) {
			ans += ch;
		}
		return ans;
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
