import java.util.ArrayList;
import java.util.Scanner;

import dataStructures.Edge;
import dataStructures.Pair;
import dataStructures.UnionFind;
public class TrackModernization {
	/*
	 * Problem description:
	 * Given: a train network with tracks and their lengths, you need to find the minimal cost of track modernization s.t. when modernized, all cities could be connected by a network of modern tracks.
	 * Modernization costs themselves are negligible, only maintenance costs are important. For each modern track of length l to maintain costs l * proportionalityConstant (constant is given)
	 * Each modern track comes with a tracker. Every tracker's yearly maintenance cost is k.
	 * 
	 *   Solution: find minimal cost for modernization.
	 * Additional problem: what if some tracks have already been modernized. Calculate the yearly cost then.
	 * Solution: if some tracks are marked as modernized, set their cost to 0. Then run Kruskal's, it will choose some of those modernized tracks, maybe all of them, but not necessarily because they might build a cycle.
	 * 
Test:
2 2 0 1.0
0 10 1
0 2 1
=> 2.0

 4 4 2 1.0
 0 10 1 
 0 10 2
 1 1 3
 1 1 0
 => 18.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		double proportionalityConst = sc.nextDouble(); // constant which describes how more expensive modernized tracks are per km 
		ArrayList<ArrayList<Pair>> g = new ArrayList<>();
		ArrayList<Edge> gEdges = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int w = sc.nextInt(); // length of the track
			int b = sc.nextInt();
			g.get(a).add(new Pair(b, w));
			g.get(b).add(new Pair(a, w));
			gEdges.add(new Edge(a, w, b));
		}
		sc.close();
		ArrayList<Edge> mst = MST.Kruskal(0, n, gEdges);
		double sum = mstSum(n, mst, k, proportionalityConst);
		printMst(mst, sum);
	
	}
	public static void printMst(ArrayList<Edge> mst, double sum) {
		
		System.out.println("Total sum: " + sum);
		System.out.println("MST: ");
		for (Edge e : mst) {
			System.out.println(e);
		}
	}
	public static double mstSum (int n, ArrayList<Edge> mst, int k, double proportionalityConst) {
		double ans = 0;
		for (Edge e : mst) {
			ans += e.w;
		}
		ans *= proportionalityConst;
		ans += (n-1)*k;
		return ans;
 	}
	public ArrayList<Edge> kruskal(int n, ArrayList<Edge> edges) {
		edges.sort(null);
		UnionFind unionFind = new UnionFind(n);
		ArrayList<Edge> mst = new ArrayList<>();
		for (Edge e : edges) {
			int a = e.a;
			int b = e.b;
			if (unionFind.same(a, b)) continue;
			else {
				mst.add(e);
				unionFind.union(a, b);
			}
		}
		return mst;
	}
	

}
