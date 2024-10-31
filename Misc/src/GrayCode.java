import java.util.Scanner;
public class GrayCode {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		String[] codes = grayCode(n);
		for (int i = 0; i < codes.length; i++) {
			System.out.println(codes[i]);
		}
	}
	/* Generates a sequence of binary strings of length 2^n. They all differ from each other by Hamming distance 1 
	 * It works recursively. First it generates a sequence of strings of length 2^(n-1) with Hamming distance 1. We take two such lists A and B. We append in the beginning to every string of A 0
	 * Reverse list B, append 1 to every string.
	 * Concatenation of A and B is the answer 
	 */
	static String[] grayCode(int n) {
		if (n == 1) {
			String[] strings = new String[2];
			strings[0] = "0";
			strings[1] = "1";
			return strings;
		}
		else {
			String[] a = grayCode(n-1);
			String[] b = new String[(int)Math.pow(2, n-1)];
			for (int i = 0; i < a.length; i++) {
				b[i] = "1" + a[a.length-1-i];
				a[a.length-1-i] = "0" + a[a.length-1-i];
			}
			String[] c = new String[(int)Math.pow(2, n)];
			int k = 0;
			for (int i = 0; i < a.length; i++, k++) {
				c[k] = a[i];
			}
			for (int i = 0; i < b.length; i++, k++) {
				c[k] = b[i];
			}
			return c;
		}
	}

}
