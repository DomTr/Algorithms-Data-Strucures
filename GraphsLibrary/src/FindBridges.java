import java.util.ArrayList;
import java.util.Scanner;
public class FindBridges {
/*
3 2
1 2
2 0
=> 2

3 3
0 1
1 2
2 0
=> 0

6 7
0 1
1 2
2 0
1 3
3 4
4 5
5 3
=> 1

 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<ArrayList<Integer>> E = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			E.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			E.get(x).add(y);
			E.get(y).add(x);
		}
		sc.close();
		int ans = bottleneck(n, m, E);
		System.out.println(ans);
	}
	// returns bottleneck nodes
	public static int bottleneck(int n, int m, ArrayList<ArrayList<Integer>> E) {
		int[] low = new int[n];
		int[] pre = new int[n];
		for (int i = 0; i < n; i++) {
			low[i] = n;
			pre[i] = -1;
		}
		int[] ans = dfs(0, -1, E, low, pre, 0);
		return ans[0];
	}
	/*
	 * 1. An articulation point is a node whose removal increases the number of connected components in the graph.
	 * 2. Similarly, a bridge is an edge whose removal increases the number of connected components in the graph.
	 * 3. A leaf is a node with only one edge (in other words a node whose degree is one).
	 * 
	 * pre[v] - dfs index
	 * low[v] - smallest dfs index reachable from v
	 */
	 public static int[] dfs(int v, int parent, ArrayList<ArrayList<Integer>> E, int[] low, int[] pre, int T) {
	    int bridges = 0;
	    int articulationPoints = 0; // can also calculate articulation points.
	    pre[v] = T;
	    low[v] = T;
	    T++;
	    for (int u : E.get(v)) {
	      if (u == parent) continue; // continue, man will nicht dfs von parent wieder aufrufen und man will den low[v] auf dem parent setzen
	      if (pre[u] != -1) {
	        low[v] = Math.min(low[v], low[u]);
	        continue; // continue weil man nicht wieder zum Knoten u gehen will. (pre[u] != -1 => u wurde schon gesehen)
	      }
	      int[] res = dfs(u, v, E, low, pre, T);
	      bridges += res[0]; // if articulation points are required, then articulation points += dfs(u, v, E, low, pre, T);
	      articulationPoints += res[1];
	      low[v] = Math.min(low[v], low[u]);
	      if (pre[v] < low[u]) { // wir haben eine Brücke gefunden. Es gibt keinen zweiten Weg von v nach u. Die Kante ist deshalb nötig um den Graph zsmhgd. zu erhalten.
	        bridges++;
	      }
	      if (pre[v] <= low[u]) {
	    	  articulationPoints++;
	      }
	    }
	    if (parent == -1 && E.get(v).size() > 1) {
	    	articulationPoints++;
	    }
	    return new int[] {bridges, articulationPoints};
	  }
	
}
