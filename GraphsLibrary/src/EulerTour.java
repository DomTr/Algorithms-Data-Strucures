import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class EulerTour {
	/*
	 * Given a graph finds an Euler-tour or returns that there is none.
	 */
	static ArrayList<ArrayList<Integer>> g;

	static ArrayList<ArrayList<Integer>> dirG; // needed, when graph is directed.
	static ArrayList<ArrayList<Integer>> revG; // needed, when graph is directed.
	/*
	 * 
	 * 4 4 
	 * 0 1 
	 * 1 2 
	 * 2 0 
	 * 2 3 hasDirectedEulerianWalk() => true
	 * 
	 * 4 4 0 1 1 0 2 3 3 2 hasDirectedEulerianWalk() => false
	 * 
	 * 5 4 0 1 1 2 2 3 2 4
	 * 
	 * hasDirectedEulerianWalk() => false
	 */
	static boolean[][] adjMatr;
	static int n;
	static int m;
	static boolean[] visited;
	static ArrayList<Integer> sol;
	static Deque<Integer> cache = new ArrayDeque<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		g = new ArrayList<>();
		adjMatr = new boolean[n][n];
		dirG = new ArrayList<>();
		revG = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
			dirG.add(new ArrayList<>());
			revG.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			int a, b;
			a = sc.nextInt();
			b = sc.nextInt();
			g.get(a).add(b);
			g.get(b).add(a);
			adjMatr[a][b] = true;
			adjMatr[b][a] = true;

			dirG.get(a).add(b);
			revG.get(b).add(a);
		}
		sc.close();
		boolean hasEulerianWalk = hasDirectedEulerianWalk();
		System.out.println(hasEulerianWalk);
		// solve();
		// System.out.println(sol.toString());
	}

	public EulerTour(ArrayList<ArrayList<Integer>> g, ArrayList<ArrayList<Integer>> dirG,
			ArrayList<ArrayList<Integer>> revG, int n, int m) {
		
	}
	public static boolean hasDirectedEulerianWalk(ArrayList<ArrayList<Integer>> g, ArrayList<ArrayList<Integer>> dirG,
			ArrayList<ArrayList<Integer>> revG, int n, int m) {
		// check if g is connected (when directions are ignored)
		// check whether there is at most one vertex v s.t. inDeg(i) - outDeg(i) > 0 and
		// at most one such v s.t. outDeg(i) - inDeg(i) > 0
		// and if there is at least one v s.t. |inDeg(i) - outDeg(i)| >= 2, then it is
		// impossible
		
		return isConnected() && checkInOutDeg();

	}
	public static boolean hasDirectedEulerianWalk() {
		// check if g is connected (when directions are ignored)
		// check whether there is at most one vertex v s.t. inDeg(i) - outDeg(i) > 0 and
		// at most one such v s.t. outDeg(i) - inDeg(i) > 0
		// and if there is at least one v s.t. |inDeg(i) - outDeg(i)| >= 2, then it is
		// impossible
		return isConnected() && checkInOutDeg();

	}

	public static boolean checkInOutDeg() {
		int cntMoreIn = 0, cntMoreOut = 0, inDeg, outDeg;
		for (int i = 0; i < n; i++) {
			inDeg = revG.get(i).size();
			outDeg = dirG.get(i).size();
			if (Math.abs(outDeg - inDeg) >= 2)
				return false;
			if (outDeg > inDeg)
				cntMoreOut++;
			if (inDeg > outDeg)
				cntMoreIn++;
		}
		return cntMoreIn <= 1 && cntMoreOut <= 1;
	}

	public static void solve() {
		sol = new ArrayList<>();
		if (n == 1) {
			sol.add(0);
			return;
		}
		boolean exists = evenDegree() && isConnected();
		if (!exists)
			return;
		visited = new boolean[n];

	}

	public static ArrayDeque<Integer> exploreFast(int v, ArrayDeque<Integer> acc) {
		visited[v] = true;
		acc.add(v);
		for (int u : g.get(v)) {
			if (visited[u]) {
				acc.add(u);
				return acc;
			}
		}
		return acc;
	}

	public static void exploreSlow(int v) {
		LinkedList<Integer> lst = new LinkedList<>();
		lst.add(v);
		while (!lst.isEmpty()) {
			visited[v] = true;
			sol.add(v);
			for (int u : g.get(v)) {
				if (!visited[u]) {
					ArrayDeque<Integer> deque = exploreFast(v, new ArrayDeque<>());
					lst.addAll(0, deque);
				}
			}
		}

	}

	public static ArrayList<Integer> euler_tour2(int start) {
		Stack<Integer> st = new Stack<>();
		ArrayList<Integer> ans = new ArrayList<>();
		st.add(start);
		ans.add(start);
		while (!st.isEmpty()) {
			int i;
			int v = st.peek();
			for (i = 0; i < n; i++) {
				if (adjMatr[i][v]) {
					break;
				}
			}
			if (i == n) { // nothing was found
				ans.add(v);
				st.pop();
			} else {
				adjMatr[i][v] = false;
				adjMatr[v][i] = false;
				st.push(i);
			}
		}
		return ans;
	}

	public static boolean evenDegree() {
		boolean allEven = g.stream().map(m -> m.size()).filter(x -> x % 2 == 1).findAny().orElse(-1) == -1;
		return allEven;
	}

	public static boolean isConnected() {
		visited = new boolean[n];
		dfs(0);
		for (int i = 0; i < n; i++) {
			if (visited[i] == false)
				return false;
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
