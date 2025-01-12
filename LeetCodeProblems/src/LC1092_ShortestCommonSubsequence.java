// darkart pabandyt issprest, sunkus buvo
public class LC1092_ShortestCommonSubsequence {
	public String shortestCommonSupersequence(String str1, String str2) {
		int n = str1.length();
		int m = str2.length();
		char[] s = normalize(str1);
		char[] t = normalize(str2);
		int[][] lcs = findLCS(str1, str2);
		StringBuilder sb = new StringBuilder();
		int i = n, j = m;
		for (; i > 0 && j > 0;) {
			if (s[i] == t[j]) {
				sb.append(s[i]);
				i--;
				j--;
			} else {
				if (lcs[i][j - 1] > lcs[i - 1][j]) { // left > top
					sb.append(t[j]);
					j--;
				} else { // top >= left
					sb.append(s[i]);
					i--;
				}
			}
		}
		while (i > 0) {
			sb.append(s[i]);
			i--;
		}
		while (j > 0) {
			sb.append(t[j]);
			j--;
		}
		return sb.reverse().toString();
	}

	public int[][] findLCS(String str1, String str2) {
		char[] s = normalize(str1);
		char[] t = normalize(str2);
		int n = str1.length();
		int m = str2.length();
		int[][] lcs = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			lcs[i][0] = 0;
		}
		for (int j = 0; j <= m; j++) {
			lcs[0][j] = 0;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s[i] == t[j]) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}
		return lcs;
	}

	public char[] normalize(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		char[] c = new char[n + 1];
		for (int i = 0; i < n; i++) {
			c[i + 1] = s[i];
		}
		return c;
	}
}
