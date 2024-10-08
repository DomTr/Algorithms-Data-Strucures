import java.util.Scanner;
public class LinearFibonacci {
	public static long calc(int n) {
		long a = 0;
		long b = 1;
		if (n <= 0) return 0;
		if (n == 1) return 1;
		for (int i = 2; i <= n; i++) {
			long tmp = a + b;
			a = b;
			b = tmp;
		}
		return b;
	}
	public static void main(String[] args) {
		/*
		 * Linear Fibonacci
		 * Runtime: O(n)
		 * Memory: O(1)
		 */
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.printf("Fibonacci number %d is: %d", n, calc(n));
		sc.close();
	}

}
