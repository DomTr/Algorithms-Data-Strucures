import java.util.Scanner;
public class LogarithmicFibonacci {

	static long[][] init = new long[2][2];
	public static long[][] multiply (long[][] a, long[][] b) {
		long[][] res = new long[2][2];
		res[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
		res[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
		res[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
		res[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
		return res;
	}
	public static long[][] fastFib(int n) {
		if (n == 0) return new long[2][2];
		if (n == 1) return init;
		if (n == 2) return multiply(init, init);
		if (n%2 == 0) {
			long[][] tmp = fastFib(n/2);
			return multiply(tmp, tmp);
		}
		else {
			long[][] tmp = fastFib((n-1)/2);
			return multiply(init, multiply(tmp, tmp));
		}
	}
	public static void main(String[] args) {
		/* F_0 = 0
		 * F_1 = 1
		 * F_n = F_(n-1) + F_(n-2)
		 * Biggest Fibonacci number that passes into long is 92nd, 7540113804746346429
		 * Runtime: O(logn)
		 * Memory:  O(logn)
		 */
		init[0][0] = 1; 
		init[0][1] = 1; 
		init[1][0] = 1; 
		init[1][1] = 0;
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[][] matr;
		matr = fastFib(n);
		System.out.println(matr[1][0]);
		sc.close();
	}

}
