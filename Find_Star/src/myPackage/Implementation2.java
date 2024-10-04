package myPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Implementation2 {
	/*
	 * N - number of nodes
	 * M - number of edges
	 * 1. Determine each node's out-degree
	 * 2. If there are at least two nodes with out-degree 0, then there is no star
	 * 3. Let's say only node A has degree 0. We check if every other node is connected with A.
	 * 
	 * OR
	 * 1. Determine each node's out-degree and in-degree. If there is only one node with in-deg n-1 and out-deg 0, it is the star.
	 * Otherwise no star. CAREFUL: if edges were written multiple times, an edge has to be counted only once in in-deg and out-deg.
	 * Running time of program: O(N+M), if adjacency matrix is used, O(M+NlogN) if HashSet is used.
	 */
	public static void main(String[] args) {
		FastScanner scanner = new FastScanner();
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		HashtableGraph g = new HashtableGraph();
		for (int i = 0; i < m; i++) {
			int a, b;
			a = scanner.nextInt();
			b = scanner.nextInt();
			g.addEdge(a, b);
		}
		int i = findAStar(g, n);
		if (i == -1) {
			System.out.println("There is no star in graph");
		} else {
			System.out.printf("The star is %d\n", i);
		}
	}
	public static int findAStar(HashtableGraph g, int n) {
		int star = -1;
		for (int i = 1; i <= n; i++) {
			int out = g.getNeighbours(i).size();
			if (out == 0 && star == -1) {
				star = i;
			} else if (out == 0 && star != -1) {
				return -1; //two stars cannot exist
			}
		}
		for (int i = 1; i <= n; i++) {
			if (i != star && !g.knows(i, star)) {
				return -1;
			}
		}
		return star;
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
	}
}
