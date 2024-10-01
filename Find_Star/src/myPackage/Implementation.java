package myPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Implementation {
	public static void main(String[] args) {
		FastScanner scanner = new FastScanner();
		
		Graph g = new HashtableGraph();
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		for (int i = 0; i < m; i++) {
			int a, b;
			a = scanner.nextInt();
			b = scanner.nextInt();
			g.addEdge(a, b);
		}
		int[] p = new int[n+1];
		p[0] = -1;
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
		int ps = findAStar(g, n, p);
		if (ps == -1) {
			System.out.println("No star");
		} else {
			System.out.printf("Star is %s", p[ps]);
		}
	}
	
	public static int findAStar(Graph g, int n, int[] p) {
		if(n == 1) {
			return 1;
		}
		else if (n == 2) {
			if (g.knows(p[2], p[1]) && !g.knows(p[1], p[2])) {
				return 1;
			} else if (g.knows(p[1], p[2]) && !g.knows(p[2], p[1])) {
				return 2;
			}
			else return -1;
		}
		if (g.knows(p[n-1], p[n])) {
			int tmp = p[n-1];
			p[n-1] = p[n];
			p[n] = tmp; 
		}
		int ps = findAStar(g, n-1, p);
		if (ps != -1 && g.knows(p[n], p[ps]) && !g.knows(p[ps], p[n])) {
			return ps;
		}
		return -1;
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
