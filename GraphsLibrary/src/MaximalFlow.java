import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
public class MaximalFlow {
	/*
	 * Max-flow using Ford-Fulkerson method of searching for augmenting paths.
	 * Complexity: O(EF), where E - number of edges, F - maximal flow.
	 * If capacities were not to be integral, but rational, the algorithm will terminate as well, but the complexity is not bounded.
	 * In the case of irrational capacities, algorithm may never terminate and may not even converge to the maximal flow.
	 */
	public int n;
	public ArrayList<ArrayList<Integer>> g;
	public int[][] cap;
	public boolean[][] edgeExists;
	public int[][] flow;
	public boolean flowComputed;
	
	// constructor, initialization
	public MaximalFlow(int n) {
		this.n = n;
		cap = new int[n][n];
		flow = new int[n][n];
		g = new ArrayList<ArrayList<Integer>>();
		edgeExists = new boolean[n][n];
		for (int i = 0; i < n; i++) g.add(new ArrayList<>());
	}
	int calcMaxFlow(int s, int t) {
        if (flowComputed) {
            reset();
        }
        flowComputed = true;
        int i, pathFlow;
        int maxFlow = 0;
        int[] parent = new int[n];
        while(findAugmentingPath(parent, s, t)) {
        	pathFlow = Integer.MAX_VALUE;
        	for (i = t; i != s; i = parent[i]) {
        		int p = parent[i];
        		pathFlow = Math.min(pathFlow, cap[p][i] - flow[p][i]);
        	}
        	for (i = t; i != s; i = parent[i]) {
        		int p = parent[i];
        		flow[p][i] += pathFlow;
        		flow[i][p] -= pathFlow;
        	}
        	maxFlow += pathFlow;
        }
        return maxFlow;
	}
	
	// s - source, t - target
	private boolean findAugmentingPath(int parent[], int s, int t) {
		LinkedList<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n];
		Arrays.fill(visited, false);
		
		q.add(s);
		visited[s] = true;
		while(!q.isEmpty()) {
			int x = q.poll();
			for (int y : g.get(x)) {
				if(!visited[y] && cap[x][y] > flow[x][y]) {
					parent[y] = x;
					visited[y] = true;
					q.add(y);
					if (y == t) return true;
				}
			}
		}
		return false;
		
	}
	/*===== HELPER FUNCTIONS ========*/
	public int getCapacity(int u, int v) {
		return cap[u][v];
	}
	public int getFlowBetweenUV(int u, int v) {
        return flow[u][v];
    }
	public void addEdge(int u, int v, int c) {
        if (c == 0) {
            return;
        }
        if (edgeExists[u][v] == false) { 
            edgeExists[u][v] = true; // important for making residual graph
            edgeExists[v][u] = true; // important for making residual graph
            g.get(u).add(v);
            g.get(v).add(u);
        }
        cap[u][v] += c;
    }
	private void reset() {
        for (int[] flowRow: flow) {
            Arrays.fill(flowRow, 0);
        }
    }
}
