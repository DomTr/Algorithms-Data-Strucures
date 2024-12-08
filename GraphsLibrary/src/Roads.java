import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Roads {
	/*
	 6 7 2
	 0 1 2 1
	 1 2 3 2
	 2 3 3 0
	 3 4 0 2
	 2 4 3 1
	 4 5 1 5
	 5 1 8 8
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int T = sc.nextInt();
		ArrayList<ArrayList<Integer>> E = new ArrayList<ArrayList<Integer>>(n);
	    ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>(n);
	    ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>(n);
	    for (int i = 0; i < n; i++) { // initialization with empty arrays
	        E.add(new ArrayList<Integer>());
	        A.add(new ArrayList<Integer>());
	        B.add(new ArrayList<Integer>());
	      }
	      for (int i = 0; i < m; i++) { // read edges
	        int u = sc.nextInt();
	        int v = sc.nextInt();
	        int a = sc.nextInt();
	        int b = sc.nextInt();
	        E.get(u).add(v);
	        E.get(v).add(u);
	        A.get(u).add(a);
	        A.get(v).add(a);
	        B.get(u).add(b);
	        B.get(v).add(b);
	      }
	      sc.close();
	      System.out.println(minTime2(n, m, T, E, A, B));
	}
	static class Tuple implements Comparable<Tuple> {
	    int v;
	    int tire;
	    int value;
	    
	    public Tuple(int v, int tire, int value) {
	      this.v = v;
	      this.tire = tire;
	      this.value = value;
	    }
	    
	    // In case you want to sort a custom class you need to have this method
	    @Override public int compareTo(Tuple other) {
	      // in case this is > than other, return a positive number,
	      // in case this is equal to other, return 0,
	      // else return a negative number
	      return this.value - other.value;
	    }
	}
	// Another solution. Two levels of nodes. One level has nodes 0,...,n-1 and summer roads, the other has n,...,2n-1 and winter roads.
    // The only way to get from one level to another is to go from node i to n+i with tires T
	public static int minTime2(int n, int m, int T, ArrayList<ArrayList<Integer>> E, ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B) {
		PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
		ArrayList<ArrayList<Tuple>> g = new ArrayList<>();
		int[] pred = new int[2*n];
		pred[0] = -1;
		for (int i = 0; i < 2*n; i++) {
			g.add(new ArrayList<>());
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < E.get(i).size(); j++) {
				int u = E.get(i).get(j);
				int summerWeight = A.get(i).get(j);
				int winterWeight = B.get(i).get(j);
				g.get(i).add(new Tuple(u, 0, summerWeight));
				g.get(u).add(new Tuple(i, 0, summerWeight));
				g.get(n+i).add(new Tuple(n+u, 1, winterWeight));
				g.get(n+u).add(new Tuple(n+i, 1, winterWeight));
			}
		}
		for (int i = 0; i < n; i++) {
			g.get(i).add(new Tuple(n+i, 1, T));
			g.get(n+i).add(new Tuple(i, 0, T));
		}
		boolean[] isSettled = new boolean[2*n];
		int settledCounter = 0;
		int[]d = new int[2*n];
		for (int i = 0; i < 2*n; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		d[0] = 0;
		pq.add(new Tuple(0, 1, 0));
		while (settledCounter < 2*n) {
			if (pq.isEmpty()) break;
			Tuple t = pq.poll();
			int u = t.v;
			// int type = t.tire;
			if (isSettled[u]) continue;
			
			isSettled[u] = true;
			settledCounter++;
			for (Tuple edge : g.get(u)) {
				int destination = edge.v;
				int weight = edge.value;
				if (!isSettled[destination]) {
					if (d[u] + weight < d[destination]) {
						d[destination] = d[u] + weight;
						pq.add(new Tuple(destination, -1, d[destination]));
						pred[destination] = u;
					}
				}
			}
		}
		//System.out.println(d[n-1] + " " + d[2*n-1]);
		return Math.min(d[n-1], d[2*n-1]);
	}
	public static int minTime(int n, int m, int T, ArrayList<ArrayList<Integer>> E, ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B) {
		// If you want to define a priority queue, it can be done as follows, where X is the data type of
		// the entries of the priority queue:
		// Another idea: dp[v][2]
		// dp[v][0] - shortest time if v is reached with summer tires
		// dp[v][1] - shortest time if v is reached with winter tires
		// answer is  Math.min(dp[n-1][0], dp[n-1][1]) - either n-1 reached with summer or winter tires
		PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
		boolean[][] isSettled = new boolean[n][2];
		int settledCounter = 0;
		long[][] dp = new long[n][2];
		for (int i = 0; i < n; i++) {
			dp[i][0] = Integer.MAX_VALUE;
			dp[i][1] = Integer.MAX_VALUE;
		}
		
		dp[0][0] = 0;
		dp[0][1] = T;
		pq.add(new Tuple(0, 0, 0));
		pq.add(new Tuple(0, 1, T));
		
		while (settledCounter < 2*n) {
			if (pq.isEmpty()) break;
			Tuple t = pq.poll();
			int u = t.v;
			int type = t.tire;
			if (isSettled[u][type]) continue;
		
			isSettled[u][type] = true;
			settledCounter++;
		for (int i = 0; i < E.get(u).size(); i++) {
			int v = E.get(u).get(i);
			if (!isSettled[v][0]) {
				long summerCost = A.get(u).get(i);
				
				long summerNoChange = summerCost + dp[u][0]; // reach u with summer, do not change tires
				long summerWithChange = dp[u][1] + T + summerCost; // reach u with winter tires, change to summer and go to v via summer road
				
				// take care of overflows:
				if (summerNoChange < 0) summerNoChange = Integer.MAX_VALUE;
				if (summerWithChange < 0) summerWithChange = Integer.MAX_VALUE;
				
				if (summerNoChange < dp[v][0]) {
					dp[v][0] = summerNoChange;
					pq.add(new Tuple(v, 0, (int)dp[v][0]));
				}
				if (summerWithChange < dp[v][0]) {
					dp[v][0] = summerWithChange;
					pq.add(new Tuple(v, 0, (int) dp[v][0]));
				}
			}
			if (!isSettled[v][1]) {
				long winterCost = B.get(u).get(i);
				long winterNoChange = winterCost + dp[u][1]; // reach u with winter, do not change tires
				long winterWithChange =  dp[u][0] + T + winterCost; // reach u with summer tires, change to winter and go to v via winter road
				
				// take care of overflows
				if (winterNoChange < 0) winterNoChange = Integer.MAX_VALUE;
				if (winterWithChange < 0) winterWithChange = Integer.MAX_VALUE;
				
				if (winterNoChange < dp[v][1]) {
					dp[v][1] = winterNoChange;
					pq.add(new Tuple(v, 1, (int) dp[v][1]));
				}
				if (winterWithChange < dp[v][1]) {
					dp[v][1] = winterWithChange;
					pq.add(new Tuple(v, 1, (int) dp[v][1]));
				}
			}
			}
		}
		//for (int i = 0; i < n; i++) {
		//System.out.println(i + ": " + dp[i][0] + " " + dp[i][1]);
		//}
		
		return (int)Math.min(dp[n-1][0], dp[n-1][1]);
		
	}

}
