import java.util.Scanner;
public class TowersOfHanoi {
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		// Prints the number of required moves
		System.out.println((int)Math.pow(2, n)-1);
		solve(1, 2, 3, n);
	}
	static void move(int a, int b) {
		System.out.println(a + " " + b);
	}
	static void solve(int a, int b, int c, int n) {
		if (n == 1) {
			cnt++;
			move(a, c);
			return;
		}
		else {
			solve(a, c, b, n-1);
			move(a, c);
			cnt++;
			solve(b, a, c, n-1);
		}
		return;
	}

}
