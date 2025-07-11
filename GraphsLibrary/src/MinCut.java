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
        Graph g = new Graph(n);
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
        while(!q.isEmpty()) {
            int u = q.poll();
            if(visited[u]) continue;
            U.add(u);
            visited[u] = true;
            for (int v : g.graph.get(u)) {
                if (!visited[v] && g.getCapacity(u, v) - g.getFlow(u, v) > 0) {
                    q.add(v);
                }
            }
        }
        System.out.println(flow);
        //printArr(g.flow);
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
    public static class Graph {
        public int numVer;
        public ArrayList<ArrayList<Integer>> graph;
        public int[][] capacity;
        public boolean[][] edge_exists;
        public int[][] flow;
        public boolean flow_computed = false;

        public Graph(int n) {
            numVer = n;
            capacity = new int[numVer][numVer];
            edge_exists = new boolean[numVer][numVer];
            flow = new int[numVer][numVer];

            graph = new ArrayList<>();
            for (int i = 0; i < numVer; i++)
                graph.add(new ArrayList<>());
        }
        public int getCapacity(int u, int v) {
            return capacity[u][v];
        }
        public int getFlow(int u, int v) {
            return flow[u][v];
        }
        public void addEdge(int u, int v, int c) {
            if (c == 0) {
                return;
            }
            if (!edge_exists[u][v]) {
                edge_exists[u][v] = true;
                edge_exists[v][u] = true;
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            capacity[u][v] += c;
        }

        public int computeMaximumFlow(int source, int sink) {
            if (flow_computed) {
                resetFlows();
            }
            flow_computed = true;
            int i, flowOnPath;
            int[] prevVertexOnPath = new int[numVer];
            int maxFlow = 0;
            while (augmentingPathExists(prevVertexOnPath, source, sink)) {
                flowOnPath = Integer.MAX_VALUE;
                for (i = sink; i != source; i = prevVertexOnPath[i]) {
                    int p = prevVertexOnPath[i];
                    flowOnPath = Math.min(flowOnPath, capacity[p][i] - flow[p][i]);
                }
                for (i = sink; i != source; i = prevVertexOnPath[i]) {
                    int p = prevVertexOnPath[i];
                    flow[p][i] += flowOnPath;
                    flow[i][p] -= flowOnPath;
                }
                maxFlow += flowOnPath;
            }

            return maxFlow;
        }

        private boolean augmentingPathExists(int prevVertexOnPath[],
                                             int source, int sink) {
            LinkedList<Integer> q = new LinkedList<Integer>();
            boolean[] visited = new boolean[numVer];

            for (int i = 0; i < numVer; i++) visited[i] = false;
            q.add(source);
            visited[source] = true;

            while (!q.isEmpty()) {
                int v = q.poll();
                for (int w : graph.get(v))
                    if (!visited[w] && capacity[v][w] > flow[v][w]) {
                        visited[w] = true;
                        prevVertexOnPath[w] = v;
                        q.add(w);
                        if (w == sink) return true;
                    }
            }
            return false;
        }
        private void resetFlows() {
            for (int[] flowRow: flow) {
                Arrays.fill(flowRow, 0);
            }
        }
    }
    static class FastScanner{
        StringTokenizer s;
        BufferedReader b;
        public FastScanner(){
            b = new BufferedReader(new InputStreamReader(System.in));
        }
        public String next() {
            while (s== null || !s.hasMoreTokens()) {
                try {
                    s = new StringTokenizer(b.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return s.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
