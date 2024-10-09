import java.util.Scanner;
public class NumberOfPrefixesWithExactlyIOnes {

	public static void main(String[] args) {
		// Input: binary string
		// Output: T[i] - number of prefixes that have exactly i ones inside
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int n = s.length();
		int[] index = new int[n+1];
		int[] T = new int[n+1];
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '1') {
				index[cnt] = i-1;
				cnt++;
			}
		}
		for (int i = cnt; i <= n; i++) {
			index[i] = n-1;
		}
		T[0] = s.charAt(0) == '0' ? 1 : 0;
		for (int i = 1; i <= n; i++) {
			T[i] = index[i] - index[i-1];
		}
		for (int i = 0; i <= n; i++) {
			System.out.println(T[i]);
		}
		sc.close();
	}

}
