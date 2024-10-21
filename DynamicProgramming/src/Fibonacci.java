import java.util.Scanner;
public class Fibonacci {
	/*
	 * Finds n-th Fibonacci number in O(n)
	 * Memory complexity: O(n) for fibonacciMemoized, O(1) for fibonacciConst
	 */
	static final int MOD = 1000000000+7; // 1e9+7 is prime
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter n: (n<=1e6) preferably");
		int n = sc.nextInt();
		sc.close();
		int ans = fibonacciConst(n);
		System.out.println(ans);
	}
	public static int fibonacciMemoized(int n) {
		if (n <= 0) {
			return 0;
		}
		int[] fib = new int[n+1];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i <= n; i++) {
			fib[i] = (fib[i-1] + fib[i-2])%MOD;
		}
		return fib[n];
	}
	public static int fibonacciConst(int n) {
		int a = 0;
		int b = 1;
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int c = 1;
		for (int i = 2; i <= n; i++) {
			c = (a + b) % MOD;
			a = b;
			b = c;
		}
		return c;
	}
	

}
