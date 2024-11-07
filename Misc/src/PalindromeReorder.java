import java.util.Arrays;
import java.util.Scanner;
public class PalindromeReorder {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		sc.close();
		char[] c = s.toCharArray();
		Arrays.sort(c);
		boolean foundOdd = false;
		char oddChar = 0;
		int oddCharCnt = 0;
		int n = c.length;
		for (int i = 0; i < n-1; i++) {
			int j = i;
			int cnt = 1;
			while(j < n-1 && c[j] == c[j+1]) {
				cnt++;
				j++;
			}
			i = j;
			if (cnt % 2 == 1 && foundOdd) {
				System.out.println("NO SOLUTION");
				return;
			}
			else if (cnt % 2 == 1){
				foundOdd = true;
				oddChar = c[j];
				oddCharCnt = cnt;
			}
		}
		if (n % 2 == 0 && foundOdd) {
			System.out.println("NO SOLUTION");
			return;
		}
		else {
			char[] ans = new char[n];
			char[] aux = new char[n-oddCharCnt];
			for (int i = 0, k = 0; i < n; i++) {
				if (c[i] == oddChar) continue;
				aux[k] = c[i];
				k++;
			}
			for (int i = 0; i < (aux.length+1)/2; i++) {
				if (i % 2 == 0) {
					ans[i] = aux[i];
					ans[n-1-i] = aux[i+1];
				}
				else {
					ans[i] = aux[i+1];
					ans[n-1-i] = aux[i];
				}
			}
			for (int i = aux.length; i < aux.length+oddCharCnt; i++) {
				ans[i] = oddChar;
			}
			for (int i = 0; i < n; i++) {
				System.out.print(ans[i]);
			}
		}
		
	}

}
