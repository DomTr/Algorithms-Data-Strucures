import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class TreeDistances1 {
	static int n;
	static ArrayList<ArrayList<Integer>> g;
	/*
	 * 
5
1 2
1 3
3 4
3 5

=> 2 3 2 3 3 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		g = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i = 0; i < n-1; i++) {
			int a, b;
			a = sc.nextInt();
			b = sc.nextInt();
			g.get(a).add(b);
			g.get(b).add(a);
		}
		sc.close();
		int mx1 = furthestNode(1, new int[n+1])[0];
		int[] distLeft = new int[n+1];
		int mx2 = furthestNode(mx1, distLeft)[0];
		int[] distRight = new int[n+1];
		furthestNode(mx2, distRight);
		int[] maximumDist = new int[n+1];
		for (int i = 1; i <= n; i++) {
			maximumDist[i] = Math.max(distLeft[i], distRight[i]);
		}
		System.out.println(cnvt(n, maximumDist));
	}
	public static String cnvt(int n, int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(a[i] + " ");
		}
		return sb.toString();
	}
	public static int[] furthestNode(int start, int[] dist) {
		Stack<Integer> st = new Stack<>();
		boolean[] visited = new boolean[n+1];
		Arrays.fill(dist, -1);
		dist[start] = 0;
		st.add(start);
		while(!st.isEmpty()) {
			int u = st.pop();
			if (visited[u]) continue;
			visited[u] = true;
			for (int v : g.get(u)) {
				if (!visited[v]) {
					dist[v] = dist[u] + 1;
					st.add(v);
				}
			}
		}
		int mx = start;
		for (int i = 1; i <= n; i++) {
			if (dist[mx] < dist[i]) {
				mx = i;
			}
		}
		return new int[] {mx, dist[mx]};
	}

}
