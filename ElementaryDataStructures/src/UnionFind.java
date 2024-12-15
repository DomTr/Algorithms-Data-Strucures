
public class UnionFind {
	private int[] parent;

	public UnionFind(int n) {
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
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
		if (a != b) {
			parent[b] = a;
		}
	}

}
