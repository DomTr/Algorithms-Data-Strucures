/*
 * Fixed exchange rates between n currencies are given. Aij means how much j you will receive for one unit of currency i.
 * You start with initial currency init.
 * Is it possible to accumulate more than init amount of currency?
 */
import java.util.ArrayList;
import java.util.Scanner;
public class ExchangeValue {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<ArrayList<Pair>> g = new ArrayList<>();
		int n = sc.nextInt();
		int k = sc.nextInt();
		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
		}
		g.add(new ArrayList<>());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n;j++) {
				double w = -Math.log(sc.nextInt()); // adjusting weights I. Point
				g.get((i+1)).add(new Pair(j+1, w));
			}
		}
		sc.close();
		double[] answer = bellmanFord(1, n, g, n);
		if (answer == null) {
			System.out.println("It is possible to get more than initial amount of money because there is a negative cycle of length " + n + " containing currency 1");
		} else {
			System.out.println("It is not possible to get more than initial amount of money because there is no negative cycle of length " + n + " containing currency 1");
		}
		
		double[] answer2 = bellmanFord(1, n, g, k);
		if (answer2 == null) {
			System.out.println("It is possible to get more than initial amount of money because there is a negative cycle of length " + k + " containing currency 1");
		} else {
			System.out.println("It is not possible to get more than initial amount of money because there is no negative cycle of length " + k + " containing currency 1");
		}
	}
	public static double[] bellmanFord(int start, int n, ArrayList<ArrayList<Pair>> g, int iterations) {
		ArrayList<ArrayList<Pair>> parentList = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			parentList.add(new ArrayList<>());
		}
		for (int i = 1; i <= n; i++) {
			for (Pair p : g.get(i)) {
				parentList.get(p.a).add(new Pair(i, p.b)); // not necessary, graph is fully connected. Every node is parent to another node
			}
		}
		double[] distance = new double[n+1];
		int[] predecessor = new int[n+1];
		for (int i = 0; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
			predecessor[i] = -1;
		}
		for (int i = 0; i < iterations-1; i++) {
			for (int u = 1; u <= n; u++) {
				for (Pair p : parentList.get(u)) {
					int par = p.a;
					double w = p.b;
					if (distance[par] != Integer.MAX_VALUE && distance[par] + w < distance[u]) {
						distance[u] = distance[par] + w;
						predecessor[u] = par;
					}
				}
			}
		}
		for (int u = 1; u <= n; u++) {
			for (Pair p : parentList.get(u)) {
				int par = p.a;
				double w = p.b;
				if (distance[par] != Integer.MAX_VALUE && distance[par] + w < distance[u]) { // u == 1 condition added in order to make sure that 1 is in a negative cycle
					if (u == 1) return null;
					else {
						distance[0] = -1;
						return distance;
					}
				} 
			}
		}
		return distance;
	}
	static class Pair {
		int a;
		double b;
		public Pair(int a, double b) {
			this.a = a;
			this.b = b;
		}
	}
}
