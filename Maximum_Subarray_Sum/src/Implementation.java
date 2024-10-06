import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Implementation {

	public static void main(String[] args) {
		// MSS problem, complexity: O(n)
		FastScanner sc = new FastScanner();
		int n = sc.nextInt();
		int[] a = new int[n];
		long[] randMaxima = new long[n];
		long ans = 0;
		int from = -1, to = -1;
		a = sc.readArray(n);
		randMaxima[0] = a[0];
		if (a[0] >= 0)
			from = 0;
		for (int i = 1; i < n; i++) {
			if (a[i] > randMaxima[i - 1] + a[i] && a[i] >= 0) {
				from = i;
			}
			randMaxima[i] = Math.max(randMaxima[i - 1] + a[i], a[i]);
			if (randMaxima[i] >= ans) {
				ans = randMaxima[i];
				to = i;
			}
		}
		if (to == -1) {
			System.out.println("All entries in the array are negative. Maximum subarray sum is 0.");
		} else {
			System.out.printf("Maximal subarray sum is %d from %d to %d (0-indexed, both inclusive)", ans, from, to);
		}
	}

	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		String next() {
			while (!st.hasMoreTokens())
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}

		long nextLong() {
			return Long.parseLong(next());
		}
	}
}
