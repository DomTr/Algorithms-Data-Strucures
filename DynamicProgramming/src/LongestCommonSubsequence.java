import java.util.Arrays;
import java.util.Scanner;
public class LongestCommonSubsequence {
	// Returns Longest Common Subsequence
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		sc.close();
		int m = s.length(), n = t.length();
		int[][] L = firstSolution(s, t);
		int ans =	L[m][n];
		
		System.out.println(ans);
		System.out.println(findSequence(L, s));
		System.out.println(findSol(s, t));
	}
	// Uses a DP table. O(n*m), memory: O(n*m) 
	public static int[][] firstSolution(String s, String t) {
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
		return L;
	}
	// Reconstructs the sequence from the DP table
	public static String findSequence(int[][] L, String s) {
		int i = L.length-1, j = L[0].length-1;
		String ans = "";
		while (L[i][j] > 0) {
			if (i-1 >= 0 && L[i-1][j] == L[i][j]) {
				i--;
			}
			else if (j-1 >= 0 && L[i][j-1] == L[i][j]){
				j--;
			}
			else if (i-1 >= 0 && j-1 >= 0 && L[i][j] == L[i-1][j-1]) {
				i--;
				j--;
			}
			else if (i-1 >= 0 && j-1 >= 0 && L[i][j] > L[i-1][j-1]) {
				ans = s.charAt(i-1) + ans; // i - 1 because strings are zero indexed 
				i--;
				j--;
			}
			else {
				break;
			}
		}
		return ans;
	}
	// Uses only two rows instead of one. 
	public static int findSol(String s, String t) {
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();
		int m = a.length;
		int n = b.length;
		if (n > m) {
			swapArrays(a, b);
			swap(n, m);
		}
		int[] lastLine = new int[n+1];
		int[] currLine = new int[n+1];
		Arrays.fill(lastLine, 0);
		for (int i = 1; i <= m; i++) {
			currLine = new int[n+1];
			Arrays.fill(currLine, 0);
			for (int j = 1; j <= n; j++) {
				if (a[i-1] == b[j-1]) {
					currLine[j] = lastLine[j-1] + 1; // it's always worth taking. The difference between neighbors is at most 1.
				}
				else {
					currLine[j] = Math.max(currLine[j-1], lastLine[j]);
				}
			}
			lastLine = currLine;
		}
		return currLine[n];
	}
	public static void swapArrays(char[] a, char[] b) {
		char[] tmp = a;
		a = b;
		b = tmp;
	}
	public static void swap(int a, int b) {
		int tmp = a;
		a = b;
		b = tmp;
	}
}
