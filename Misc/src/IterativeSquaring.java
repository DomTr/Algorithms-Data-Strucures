import java.util.Scanner;
public class IterativeSquaring {
	
	public static double calc(double a, int n) {
		if (n < 0) {
			return calc(1/a, -n);
		}
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return a;
		}
		double tmp = calc(a, n/2);
		if (n%2 == 0) {
			return tmp*tmp;
		}
		else {
			return a*tmp*tmp;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please write a: ");
		double a = sc.nextDouble();
		System.out.println("Please writen n: ");
		int n = sc.nextInt();
		System.out.printf("Your answer is %f", calc(a, n));
		sc.close();
	}

}
