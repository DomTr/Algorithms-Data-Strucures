import java.util.Scanner;

public class SlowFibonacci {
	
	public static long calc(int n) {
		if (n <= 0) return 0;
		if (n == 1) return 1;
		return calc(n-1) + calc(n-2);
	}
	public static void main(String[] args) {
		/*
		 * Slow Fibonacci:
		 * Runtime: about O(2^n). In between 2^(n/2) and 2^n
		 * Memory:  about O(2^n). In between 2^(n/2) and 2^n
		 */
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.printf("Fibonacci number %d is: %d", n, calc(n));
		sc.close();
	}

}
