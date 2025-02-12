import java.util.Arrays;

public class AirTransport {

	public static int minCost(int n, int m, int[] A, Edge[] E) {
		// The idea is to use Kruskal's algorithm to construct an MST.
		// The weight of the edge joining the dummy city to every other city
		// is precisely the cost of our airport.
		// We observe here that just running MST naively does not suffice for this
		// problem.
		// The final cost is the minimum of road cost and the road + airport cost.
		// Notice that since the airport node is virtual, it can create cases when the
		// MST might be obtained by just the airport edges and not the road edges but
		// that would
		// not be correct for the cost since MST only looks at things globally. But we
		// might have
		// a better solution locally with the roads itself. Example is airport cost for
		// 2 cities of 3 and
		// road cost of 4. In this case the cost is 6 if we naively run MST but if we
		// run
		// minimum of road and airport+road we get the right answer of 4.
		// Also notice that sometimes the number of roads might be less than n - 1. In
		// this
		// case the graph will be disconnected with only road edges. So we have to run
		// road+airport to get the right answer since airport resolve disconnectivity.
		// Also observe that in the case where airport + road is cheaper than just road,
		// we
		// can be sure the solution is correct by virtue of Kruskal's. Kruskal wouldn't
		// have
		// picked an airport edge before a "cheaper" road edge since it looks at things
		// after
		// sorting. Therefore, we are at least going to pay as much as we pay simply
		// using roads
		// by using the airport node.

		Tuple[] road_edges = new Tuple[m];
		for (int i = 0; i < m; i++) {
			road_edges[i] = new Tuple(E[i].u, E[i].v, E[i].w);
		}
		Arrays.sort(road_edges);

		// Initialize cost.
		int road_cost = 0;
		int cost = 0;

		// Run Kruskal's algorithm.
		int total = 0;
		int cur = 0;
		int road_count = 0;

		if (m > 0) {
			UnionFind road_uf = new UnionFind(n);
			// Max number of edges -- min (m, n - 1)
			while (total < n - 1 && cur < m) {
				Tuple edge = road_edges[cur];
				if (road_uf.find(edge.u) != road_uf.find(edge.v)) {
					road_cost += edge.w;
					total++;
					road_uf.union(edge.u, edge.v);
					road_count++;
				}
				cur++;
			}
			if (road_count < n - 1) {
				road_cost = Integer.MAX_VALUE;
			}
		} else {
			road_cost = Integer.MAX_VALUE;
		}

		Edge[] E_final = new Edge[m + n];
		for (int i = 0; i < n; i++) {
			int u = i;
			int v = n;
			int w = A[i];
			E_final[i] = new Edge(u, v, w);
		}

		for (int i = n; i < n + m; i++) {
			E_final[i] = E[i - n];
		}

		Tuple[] edges = new Tuple[m + n];
		for (int i = 0; i < m + n; i++) {
			edges[i] = new Tuple(E_final[i].u, E_final[i].v, E_final[i].w);
		}

		Arrays.sort(edges);

		UnionFind uf = new UnionFind(n + 1);
		total = 0;
		cur = 0;

		while (total < n) {
			Tuple edge = edges[cur];
			if (uf.find(edge.u) != uf.find(edge.v)) {
				cost += edge.w;
				total++;
				uf.union(edge.u, edge.v);
			}	
			cur++;
		}
		return Math.min(cost, road_cost);
	}

	static class Edge {
		public int u;
		public int v;
		public int w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	static class Tuple implements Comparable<Tuple> {
		int u;
		int v;
		int w;

		public Tuple() {

		}

		public Tuple(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Tuple other) {
			return this.w - other.w;
		}
	}

	static class UnionFind {
		public int[] parent;
		public int[] rank;

		public UnionFind(int n) {
			parent = new int[n];
			rank = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
				rank[i] = 0;
			}
		}

		public int find(int v) {
			if (parent[v] == v) {
				return v;
			}
			return find(parent[v]);
		}

		public void union(int u, int v) {
			int uroot = find(u);
			int vroot = find(v);

			if (rank[uroot] < rank[vroot]) {
				parent[uroot] = vroot;
			} else if (rank[uroot] > rank[vroot]) {
				parent[vroot] = uroot;
			} else {
				parent[vroot] = uroot;
				rank[uroot]++; // labai protinga optimizacija. Nereikia visu elementu kopijuoti, uztenka
								// zinoti, kiek uroot ir vroot buvo sugunti su tokio pat dydzio elementais
			}
		}
	}
}
