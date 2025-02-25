import java.util.ArrayList;
import java.util.Scanner;

public class EulerTour {
	/*
	 * Given a graph finds an Euler-tour or returns that there is none. 
	 */
	static ArrayList<ArrayList<Integer>> g;
	static int n;
	static int m;
	static boolean[] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		g = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			int a, b;
			a = sc.nextInt();
			b = sc.nextInt();
			g.get(a).add(b);
			g.get(b).add(a);
		}
		sc.close();
		ArrayList<Integer> sol = solve();
		System.out.println(sol.toString());
	}
	public static ArrayList<Integer> solve() {
		ArrayList<Integer> sol = new ArrayList<>();
		if (n == 1) {
			sol.add(0);
			return sol;
		}
		boolean exists = evenDegree() && isConnected();
		if (!exists) return sol;
		
		return sol;
	}
	public static boolean evenDegree() {
		boolean allEven = g.stream()
				.map(m -> m.size())
				.filter(x -> x%2 == 1)
				.findAny().orElse(-1) == -1;
		return allEven;
	}
	public static boolean isConnected() {
		visited = new boolean[n];
		dfs(0);
		for (int i = 0; i < n; i++) {
			if (visited[i] == false) return false;
		}
		return true;
	}
	public static void dfs(int v) {
		visited[v] = true;
		for (int u : g.get(v)) {
			if (!visited[u]) {
				dfs(u);
			}
		}
	}

}
