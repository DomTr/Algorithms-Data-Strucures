
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import dataStructures.UnionFind;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import dataStructures.Pair;
import dataStructures.Edge;
public class MST {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<ArrayList<Pair>> g = new ArrayList<>();
		ArrayList<Edge> gEdges = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int w = sc.nextInt();
			int b = sc.nextInt();
			g.get(a).add(new Pair(b, w));
			g.get(b).add(new Pair(a, w));
			gEdges.add(new Edge(a, w, b));
		}
		sc.close();
		ArrayList<Edge> mst = Kruskal(0, n, gEdges);
		print(mst);
	}
	public static void print(ArrayList<Edge> mst) {
		long total = 0;
		StringBuilder sb = new StringBuilder();
		for (Edge e : mst) {
			total += e.w;
			sb.append(e.a + " " + e.w + " " + e.b + "\n");
		}
		System.out.println("Total " + total);
		System.out.print(sb.toString());
	}
	/*
	 * Prim's algorithm: complexity O((n+m)log n)
	 * Similar to Djikstra's algorithm, differs in that way that if the cost of to a vertex is smaller than the current smallest cost to that vertex, we take it into priority queue
	 * An array of Edges: a w b is given as an answer.
	 *  
	 */
	public static ArrayList<Edge> Prim (int start, int n, ArrayList<ArrayList<Pair>> g){
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		HashSet<Integer> settled = new HashSet<>();
		ArrayList<Edge> mst = new ArrayList<>();
		int[] distances = new int[n];
		for (int i = 0; i < n; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		distances[start] = 0;
		pq.add(new Edge(-1, 0, start));
		while (settled.size() < n) {
			if (pq.isEmpty()) {
				break;
			}
			Edge e = pq.poll();
			int v = e.b;
			if (settled.contains(v)) continue;
			
			if (e.a != -1) {
				mst.add(e);
			}
			
			settled.add(v);
			for (Pair p : g.get(v)) {
				int to = p.a;
				int cost = p.b;
				if (cost < distances[to]) { // main difference from Djikstra's algorithm
					distances[to] = cost;
					pq.add(new Edge(v, cost, to));
				}
			}
		}
		return mst;
	}
	
	// implementation from Internet: https://medium.com/@arst-dev/algorithms-in-c-bor%C5%AFvkas-algorithm-c82239ef3f0c
	public static ArrayList<Edge> Boruvka(int start, int n, ArrayList<Edge> edges) {
		ArrayList<Edge> mst = new ArrayList<>(); 
		Subset[] subsets = new Subset[n];
		Edge[] cheapest = new Edge[n];
		for (int i = 0; i < n; i++) {
			subsets[i] = new Subset(i, 0);
			cheapest[i] = null;
		}
		
		int currTrees = n;
		// int totalWeight = 0;
		while (currTrees > 1) {
			for (int i = 0; i < n; i++) 
				cheapest[i] = null;
			for (Edge edge : edges) {
				int set1 = Find(subsets, edge.a);
				int set2 = Find(subsets, edge.b);
				if (set1 == set2) {
					continue; // they are in the same component
				}
				// if this edge is cheaper than the current cheapest edge in set1, update cheapest[set1]
				if (cheapest[set1] == null || cheapest[set1].w > edge.w) {
					cheapest[set1] = edge;
				} 
				if (cheapest[set2] == null || cheapest[set2].w > edge.w) {
					cheapest[set2] = edge;
				}
			}
			// for each vertex check, if the cheapest edge can be added to MST
			for (int i = 0; i < n; i++) {
				Edge e = cheapest[i];
				if (e != null) {
					int set1 = Find(subsets, e.a);
					int set2 = Find(subsets, e.b);
					
					if (set1 != set2) {
						mst.add(e);
						// totalWeight += e.w;
						Union(subsets, set1, set2);
						currTrees--;
					}
				}
			}
		}
		return mst;
	}
	public static ArrayList<Edge> Kruskal(int start, int n, ArrayList<Edge> edges) {
		edges.sort(new Comparator<Edge> () {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o1.w - o2.w;
			}
			
		});;
		UnionFind unionFind = new UnionFind(n);
		ArrayList<Edge> mst = new ArrayList<Edge>();
		int total = 0;
		for (Edge e : edges) {
			int a = e.a;
			int b = e.b;
			int w = e.w;
			if (unionFind.same(a, b)) continue;
			mst.add(e);
			unionFind.union(a, b);
			total += w;
		}
		return mst;
	}
	public static class Subset {
		public int root;
		public int weight;
		public Subset(int root, int weight) {
			this.root = root;
			this.weight = weight;
		}
	}
	private static int Find(Subset[] subsets, int i) {
		if (subsets[i].root != i) {
			subsets[i].root = Find(subsets, subsets[i].root);
		}
		return subsets[i].root;
	}
	private static void Union (Subset[] subsets, int x, int y) {
		int xroot = Find(subsets, x);
		int yroot = Find(subsets, y);
		if (subsets[xroot].weight < subsets[yroot].weight)
            subsets[xroot].root = yroot;
        else if (subsets[xroot].weight > subsets[yroot].weight)
            subsets[yroot].root = xroot;
        else
        {
            // If ranks are the same, make one as root and increment its rank by one
            subsets[yroot].root = xroot;
            subsets[xroot].weight++;
        }
	}
}
