import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	int n;
	int m;
	ArrayList<ArrayList<Integer>> g;
	ArrayList<ArrayList<Integer>> dirG; // needed, when graph is directed.
	ArrayList<ArrayList<Integer>> revG; // needed, when graph is directed.
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
	boolean[][] adjMatr;
	boolean[] visited;
	ArrayList<Integer> sol;
	Deque<Integer> cache;
	public EulerTour(int n, int m, ArrayList<ArrayList<Integer>> g, ArrayList<ArrayList<Integer>> dirG, ArrayList<ArrayList<Integer>> revG, 
			boolean[][] adjMatr) {
		this.n = n;
		this.m = m;
		this.g = g;
		this.dirG = dirG;
		this.revG = revG;
		this.adjMatr = adjMatr;
		this.visited = new boolean[n];
		sol = new ArrayList<>();
		cache = new ArrayDeque<>();
	}
	// TODO: this will enable more efficient testing
	public static EulerTour readEulerTour(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Output each line from the file
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential IOExceptions
        }
		return null;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<ArrayList<Integer>> g = new ArrayList<>();
		boolean[][] adjMatr = new boolean[n][n];
		ArrayList<ArrayList<Integer>> dirG = new ArrayList<>();
		ArrayList<ArrayList<Integer>> revG = new ArrayList<>();
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
		EulerTour euler = new EulerTour(n, m, g, dirG, revG, adjMatr);
		boolean hasEulerianWalk = euler.hasDirectedEulerianWalk();
		System.out.println(hasEulerianWalk);
		// solve();
		//System.out.println(sol.toString());
	}

	public EulerTour(ArrayList<ArrayList<Integer>> g, ArrayList<ArrayList<Integer>> dirG,
			ArrayList<ArrayList<Integer>> revG, int n, int m) {
		
	}
//	public static boolean hasDirectedEulerianWalk(ArrayList<ArrayList<Integer>> g, ArrayList<ArrayList<Integer>> dirG,
//			ArrayList<ArrayList<Integer>> revG, int n, int m) {
//		// check if g is connected (when directions are ignored)
//		// check whether there is at most one vertex v s.t. inDeg(i) - outDeg(i) > 0 and
//		// at most one such v s.t. outDeg(i) - inDeg(i) > 0
//		// and if there is at least one v s.t. |inDeg(i) - outDeg(i)| >= 2, then it is
//		// impossible
//		
//		return isConnected() && checkInOutDeg();
//
//	}
	public boolean hasDirectedEulerianWalk() {
		// check if g is connected (when directions are ignored)
		// check whether there is at most one vertex v s.t. inDeg(i) - outDeg(i) > 0 and
		// at most one such v s.t. outDeg(i) - inDeg(i) > 0
		// and if there is at least one v s.t. |inDeg(i) - outDeg(i)| >= 2, then it is
		// impossible
		return isConnected() && checkInOutDeg();

	}

	public boolean checkInOutDeg() {
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

	public void solve() {
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

	public ArrayDeque<Integer> exploreFast(int v, ArrayDeque<Integer> acc) {
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

	public void exploreSlow(int v) {
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

	public ArrayList<Integer> euler_tour2(int start) {
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

	public boolean evenDegree() {
		boolean allEven = g.stream().map(m -> m.size()).filter(x -> x % 2 == 1).findAny().orElse(-1) == -1;
		return allEven;
	}

	public boolean isConnected() {
		visited = new boolean[n];
		dfs(0);
		for (int i = 0; i < n; i++) {
			if (visited[i] == false)
				return false;
		}
		return true;
	}

	public void dfs(int v) {
		visited[v] = true;
		for (int u : g.get(v)) {
			if (!visited[u]) {
				dfs(u);
			}
		}
	}

}
