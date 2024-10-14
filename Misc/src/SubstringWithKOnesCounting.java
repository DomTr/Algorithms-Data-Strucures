import java.util.Scanner;
public class SubstringWithKOnesCounting {
	/*
	 *  If a n-bit bit string S and an integer 0 <= k <= n is given, compute the number of nonempty substrings of S with exactly k ones.
	 *  n >= 2
	 *  For example, S = 0110 and k = 2, then the answer is 4. 011, 11, 110, 0110
	 *  Solution of this problem follows divide and conquer approach.
	 *  Runtime: O(nlogn)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input string: ");
		String S = sc.next();
		System.out.println("Input number of ones in a substring");
		int k = sc.nextInt();
		sc.close();
		long ans = countSubstrings(S, k);
		System.out.println(ans);
	}
	public static long countSubstrings(String S, int k) {
		if (k > S.length()) {
			return 0;
		}
		return countSubstrings(S, k, 0, S.length()-1);
	}
	public static long countSubstrings(String S, int k, int i, int j) {
		if (i == j) {
			if (k == 0 && S.charAt(i) == '0') return 1;
			if (k == 1 && S.charAt(i) == '1') return 1;
			else return 0;
		}
		else {
			int m = i + (j-i)/2;
			long left = countSubstrings(S, k, i, m);
			long right = countSubstrings(S, k, m+1, j);
			long middle = spanning(S.substring(i, j+1), ((j-i)/2), k);//(j-i)/2 and not m in order to normalize the middle element
			return left+right+middle;
		}
	}
	public static long spanning(String S, int m, int k) {
		int[] T1 = suffixTable(S.substring(0, m+1));
		int[] T2 = prefixTable(S.substring(m+1, S.length()));
		
		long total = 0;
		for (int i = 0; i <= k; i++) {
			int a, b;
			if (i < T1.length) {
				a = T1[i];
			}
			else {
				a = 0;
			}
			if (k - i >= 0 && (k - i) < T2.length) {
				b = T2[k-i];
			}
			else {
				b = 0;
			}
			total = total + a * b;
		}
		return total;
	}
	public static int[] prefixTable(String S) {
		int[] T = new int[S.length()+1];
		int counter = 0;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '1') {
				counter = counter + 1;
			}
			T[counter] = T[counter] + 1;
		}
		return T;
	}
	public static int[] suffixTable(String S) {
		int[] T = new int[S.length()+1];
		int counter = 0;
		for (int i = S.length() - 1; i >= 0; i--) {
			if (S.charAt(i) == '1') {
				counter = counter + 1;
			}
			T[counter] = T[counter] + 1;
		}
		return T;
	}
}
