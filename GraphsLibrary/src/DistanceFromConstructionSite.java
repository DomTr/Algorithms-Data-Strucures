import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DistanceFromConstructionSite {
	/*
	 * Given a connected graph we have to make the graph Eulerian by building bridges between cities. Find a vertex v which is furthest away from a node which is a part from a construction site.
	 * Idea: Graph is Eulerian iff. it is connected and all of its degrees are divisible by two. Mark the edges which are divisible by two, create a new ghost node which is connected to construcitons nodes
	 * Then run bfs from it.
	 * Another idea: don't create a ghost node, add the construction site nodes to the queue directly and the continue BFS from there.
	 */
	public static int getMinDistance3(int n, ArrayList<Integer>[] edges) {
		// n = number of nodes
		// for 0 <= i < n - 1, edges[i] is an array of the neighbors of node i
		ArrayList<Integer> curr = new ArrayList<>();
		boolean[] blocked = new boolean[n];
		int r = 0;
		for (int i = 0; i < n; i++) {
			if (edges[i].size() % 2 != 0) {
				blocked[i] = true;
				curr.add(i);
				r++;
			}
		}

		int a = 0;
		while (r != n) {
			a++;
			System.out.println(curr);
			System.out.println(Arrays.toString(blocked));
			ArrayList<Integer> next = new ArrayList<>();
			for (int u : curr) {
				for (int v : edges[u]) {
					if (!blocked[v]) {
						blocked[v] = true;
						r++;
						next.add(v);
					}
				}
			}

			curr.clear();
			curr = next;
		}
		System.out.println(curr);
		System.out.println(Arrays.toString(blocked));

		return a;
	}

	public static int getMinDistanceMine(int n, ArrayList<Integer>[] edges) {
		// n = number of nodes
		// for 0 <= i < n - 1, edges[i] is an array of the neighbors of node i
		LinkedList<Integer> q = new LinkedList<>();
		int[] dist = new int[n];
		fill(n, dist, -1);

		for (int i = 0; i < n; i++) {
			if (edges[i].size() % 2 == 1) {
				q.add(i);
				dist[i] = 0;
			}
		}

		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : edges[u]) {
				if (dist[v] == -1) {
					dist[v] = dist[u] + 1;
					q.add(v);
				}
			}
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, dist[i]);
		}
		return max;
	}

	public static void fill(int n, int[] a, int c) {
		for (int i = 0; i < n; i++) {
			a[i] = c;
		}
	}
}
