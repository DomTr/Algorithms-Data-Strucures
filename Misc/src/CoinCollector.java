import java.util.Scanner;
public class CoinCollector {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, T;
		n = sc.nextInt();
		T = sc.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = sc.nextInt();
		}
		sc.close();
		int ans = coinCollect(n, A, T);
		System.out.println(ans);
	}
	public static boolean check(int n, int[] A, int T, long x) {
	    long cnt = 0;
	    for (int i = 0; i < n; i++) {
	      cnt = cnt + (A[i]+x-1)/x;
	    }
	    return cnt <= T;
	  }
	  public static int coinCollect(int n, int[] A, int T) {
	   long sum = 0;
	   for (int i = 0; i < n; i++) {
	     sum += A[i];
	   }
	   long l = 0;
	   long r = sum;
	   while (r - l > 1) {
	     long m = l + (r-l)/2;
	     if (check(n, A, T, m)) {
	       r = m;
	     }
	     else {
	       l = m;
	     }
	   }
	   return (int)r;
	  }
}
