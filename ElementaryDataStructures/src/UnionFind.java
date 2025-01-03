
public class UnionFind {
	private int[] parent;
	private int[] rank;
	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public void makeSet(int u) {
		parent[u] = u;
	}

	public int findSet(int u) {
		if (parent[u] == u) {
			return u;
		} else {
			parent[u] = findSet(parent[u]);
			return parent[u];
		}
	}

	void unionSets(int u, int v) {
		int a = findSet(u);
		int b = findSet(v);
		if (rank[a] < rank[b]) {
			parent[a] = parent[b];
		} else if (rank[a] > rank[b]) {
			parent[b] = parent[a];
		} else {
			parent[b] = a;
			rank[a]++;
		}
	}

}
