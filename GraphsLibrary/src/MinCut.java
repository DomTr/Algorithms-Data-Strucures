import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MinCut {
  public static void main(String[] args) {
    FastScanner sc = new FastScanner();
    int n = sc.nextInt();
    int m = sc.nextInt();
    int s = sc.nextInt();
    int t = sc.nextInt();
    MaximalFlow.Graph g = new Graph(n);
    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      int c = sc.nextInt();
      g.addEdge(u, v, c);
    }
    int flow = g.computeMaximumFlow(s, t);
    ArrayList<Integer> U = new ArrayList<>();
    LinkedList<Integer> q = new LinkedList<>();
    q.add(s);
    boolean[] visited = new boolean[n];
    while (!q.isEmpty()) {
      int u = q.poll();
      if (visited[u])
        continue;
      U.add(u);
      visited[u] = true;
      for (int v : g.graph.get(u)) {
        if (!visited[v] && g.getCapacity(u, v) - g.getFlow(u, v) > 0) {
          q.add(v);
        }
      }
    }
    System.out.println(flow);
    // printArr(g.flow);
    System.out.println(U.size());
    for (int u : U) {
      System.out.println(u);
    }
  }
  public static void printArr(int[][] a) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++) {
        System.out.print(a[i][j] + " ");
      }
      System.out.println();
    }
  }
  public record Edge(int u, int v, int c) {}
  static class FastScanner {
    StringTokenizer s;
    BufferedReader b;
    public FastScanner() {
      b = new BufferedReader(new InputStreamReader(System.in));
    }
    public String next() {
      while (s == null || !s.hasMoreTokens()) {
        try {
          s = new StringTokenizer(b.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return s.nextToken();
    }
    public int nextInt() { return Integer.parseInt(next()); }
  }
}
