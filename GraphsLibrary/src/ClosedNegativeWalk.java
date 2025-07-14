import java.util.ArrayList;
import java.util.Scanner;

public class ClosedNegativeWalk {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    ArrayList<ArrayList<Integer>> E = new ArrayList<ArrayList<Integer>>(n);
    ArrayList<ArrayList<Integer>> W = new ArrayList<ArrayList<Integer>>(n);
    for (int i = 0; i < n; i++) { // initialization with empty arrays
      E.add(new ArrayList<Integer>());
      W.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < m; i++) { // read edges
      int u = sc.nextInt();
      int v = sc.nextInt();
      int w = sc.nextInt();
      E.get(u).add(v);
      W.get(u).add(w);
    }
    sc.close();
    System.out.println(minCost(n, m, E, W));
  }

  /*
   * Required knowledge: FloydWarshall
   */
  public static int minCost(int n, int m, ArrayList<ArrayList<Integer>> E,
                            ArrayList<ArrayList<Integer>> W) {
    /*
     * Strategy: Do Floyd-Warshall check, whether for some v d[v][v] < 0, if so
     * there already is a negative cycle => return 0 Otherwise: int
     * minWalkLength = Integer.MAX_VALUE; // mininmaler Weg Länge for i = 0 ...
     * n-1 for j = 0 ... n-1 if i == j continue else: minWalkLength =
     * Math.min(minWalkLength, d[i][j]
     * + d[j][i])
     */
    long[][] dist = new long[n][n];
    long[][] weights = new long[n][n];
    initialise(n, dist, weights, E, W);
    FloydWarshall(n, dist, weights);
    boolean hasNegCycle = hasNegativeCycle(n, dist);
    if (hasNegCycle) {
      return 0;
    }
    long minCost = getMinCost(n, dist);
    return (int)minCost;
  }

  public static void initialise(int n, long[][] dist, long[][] weights,
                                ArrayList<ArrayList<Integer>> E,
                                ArrayList<ArrayList<Integer>> W) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j)
          continue; // if it dist[i][i] = Integer.MAX_VALUE, there will always
                    // be a cycle registered
        dist[i][j] = Integer.MAX_VALUE;
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < E.get(i).size(); j++) {
        int u = i, v = E.get(i).get(j);
        int w = W.get(i).get(j);
        dist[u][v] = w;
        weights[i][j] = w;
      }
    }
  }

  public static void FloydWarshall(int n, long[][] dist, long[][] weights) {
    for (int useUntil = 0; useUntil < n; useUntil++) {
      for (int u = 0; u < n; u++) {
        for (int v = 0; v < n; v++) {
          if (dist[u][useUntil] == Integer.MAX_VALUE ||
              dist[useUntil][v] == Integer.MAX_VALUE) {
            continue;
          }
          if (dist[u][v] > dist[u][useUntil] + dist[useUntil][v]) {
            dist[u][v] = dist[u][useUntil] + dist[useUntil][v];
          }
        }
      }
    }
  }

  public static boolean hasNegativeCycle(int n, long[][] dist) {
    boolean hasNegCycle = false;
    for (int i = 0; i < n; i++) {
      if (dist[i][i] < 0) {
        return true;
      }
    }
    return hasNegCycle;
  }

  public static long getMinCost(int n, long[][] dist) {
    long minCost = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j)
          continue; // no self-loops since paths of length 1 are not allowed.
                    // Self-edges are also not allowed
        long length = dist[i][j] + dist[j][i];
        if (length < 0)
          continue;
        minCost = Math.min(minCost, length);
      }
    }
    return (int)minCost + 1; // +1 because otherwise, there will simply be a
                             // walk with non-negative weight
  }
}
