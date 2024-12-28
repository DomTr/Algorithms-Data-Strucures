import java.util.Arrays;

public class LC204_CountPrimes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int countPrimes(int n) {
		boolean[] sieve = erasthotenesSieve(n);
		int cnt = 0;
		for (int i = 2; i < n; i++) {
			cnt = cnt + (sieve[i] ? 1 : 0);
		}
		return cnt;
	}
	public boolean[] erasthotenesSieve(int n) {
		boolean[] sieve = new boolean[Math.max(n+1, 10)];
		Arrays.fill(sieve, true);
		sieve[0] = false;
		sieve[1] = false;
		for (int i = 2; i <= n; i++) {
			if (sieve[i]) {
				for (int j = 2*i; j <= n; j+=i) {
					sieve[j] = false;
				}
			}
		}
		return sieve;
	}
}
