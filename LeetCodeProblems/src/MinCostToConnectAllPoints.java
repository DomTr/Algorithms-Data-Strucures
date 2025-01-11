import java.util.ArrayList;
import java.util.Collections;

public class MinCostToConnectAllPoints {
	public int minCostConnectPoints(int[][] points) {
		int n = points.length;
		ArrayList<Edge> edges = getEdges(points);
		Collections.sort(edges);
		UnionFind uf = new UnionFind(n);
		int sum = 0;
		for (int i = 0; i < edges.size(); i++) {
			if (uf.sameComponent(edges.get(i).a, edges.get(i).b))
				continue;
			sum += edges.get(i).w;
			uf.union(edges.get(i).a, edges.get(i).b);
		}
		// System.out.println(edges);
		return sum;
	}

	public ArrayList<Edge> getEdges(int[][] points) {
		int n = points.length;
		ArrayList<Edge> edges = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				int w = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
				edges.add(new Edge(i, j, w));
			}
		}
		return edges;
	}

	class UnionFind {
		int[] par;
		int[] rank;

		public UnionFind(int n) {
			par = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				par[i] = i;
				rank[i] = 0;
			}
		}

		public int find(int a) {
			if (a == par[a])
				return a;
			return find(par[a]); // that's important
		}

		public boolean sameComponent(int a, int b) {
			return find(a) == find(b);
		}

		public void union(int a, int b) {
			int pA = find(a);
			int pB = find(b);
			if (rank[pA] > rank[pB]) {
				par[pB] = pA;
			} else if (rank[pB] > rank[pA]) {
				par[pA] = pB;
			} else {
				par[pB] = pA;
				rank[pA]++;
			}
		}
	}

	class Edge implements Comparable<Edge> {
		int a;
		int b;
		int w;

		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		public int compareTo(Edge o) {
			return w - o.w;
		}

		public String toString() {
			return a + " " + b + " " + w;
		}
	}
}
