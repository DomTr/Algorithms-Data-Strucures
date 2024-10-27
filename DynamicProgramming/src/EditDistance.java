import java.util.Scanner;
public class EditDistance {
	/* Edit distance - minimal number of operations needed to make two strings the same
	 * Allowed operations:
	 * 1. Insertion of a character at any place
	 * 2. Deletion of a character at any place
	 * 3. Swapping of a character at any place
	 * Later: decoding the operations of EDI
	 * Runtime: O(m * n)
	 * Memory: O(m * n). However, if we do not require to decode the sequence, we can save only the last previously generated line. The same technique can be applied as in the LCS problem.
	 */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter two strings:");
		String s = sc.next();
		String t = sc.next();
		sc.close();
		int ans = solve(s, t);
		System.out.println("The answer is: " + ans);
	}
	static int solve(String s, String t) {
		char[] a = oneIndexedCharArray(s);
		char[] b = oneIndexedCharArray(t);
		int m = a.length-1; // length of string s is a.length - 1
		int n = b.length-1; // length of string t is b.length - 1
		
		// EDI[i][j] tells how many operations are needed to change a[1...i] to b[1...j].
		int[][] EDI = new int[m+1][n+1];
		// BASE: to change EDI[i][j]
		for (int j = 0; j <= n; j++) {
			EDI[0][j] = j;
		}
		for (int i = 0; i <= m; i++) {
			EDI[i][0] = i;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				/*
				 * 3 Cases:
				 * 1. In optimal answer a[i] will be deleted -> do it now -> After the deletion a[1...i-1] will need to be matched with b[1...j] -> 1 + EDI[i-1][j]
				 * 2. In optimal answer a[i] will not be deleted, but it will be at some position 1...j-1 at b
				 *  -> we will need to add at some point b[j] -> do it now. -> 1 + EDI[i][j-1]. Match a[1...i] with b[1...j-1] because b[j] is now at the end of a[1...i]
				 *  -> After adding there will still be strings a[1...i] and b[1...j-1] left to be matched. -> 1 + EDI[i][j-1]
				 * 3. a[i] will not be deleted and it will be at position b[j]. 
				 * 	If a[i] == b[j], then no change is required. -> afterwards match a[1...i-1] with b[1...j-1] -> EDI[i-1][j-1]
				 * 	If a[i] != b[j], then you need to swap a[i] to b[j] -> afterwards match a[1...i-1] with b[1...j-1] -> 1 + EDI[i-1][j-1]
				 */
				int firstOpt = 1 + EDI[i-1][j];
				int secondOpt = 1 + EDI[i][j-1];
				int thirdOpt = a[i] == b[j] ? EDI[i-1][j-1] : 1 + EDI[i-1][j-1];
				EDI[i][j] = Math.min(firstOpt, Math.min(secondOpt, thirdOpt));
			}
		}
		return EDI[m][n];
	}
	static char[] oneIndexedCharArray(String s) {
		char[] a = s.toCharArray();
		char[] ans = new char[a.length+1];
		for (int i = 0; i < a.length; i++) {
			ans[i+1] = a[i];
		}
		ans[0] = 0;
		return ans;
	}
}
