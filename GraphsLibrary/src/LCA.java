import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA {
    static ArrayList<ArrayList<Integer>> g;
    static int[][] up;

    static int[] depth;
    static int[] tin;
    static int[] tout;
    static int n, l;
    static int timer = 0;
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        int q = sc.nextInt();
        l = (int) Math.ceil(Math.log(n)/Math.log(2));
        preprocess();
        for (int i = 0; i < n-1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g.get(a).add(b);
            g.get(b).add(a);
        }
        StringBuilder sb = new StringBuilder();
        dfs(1, 1);
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            sb.append(distance(u, v)).append("\n");
        }
        System.out.println(sb);
    }
    static void preprocess() {
        g = new ArrayList<>();
        up = new int[n+1][l+1];
        depth = new int[n+1];
        depth[1] = 0;
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        tin = new int[n+1];
        tout = new int[n+1];
    }
    

    static void dfs(int v, int p) {
        tin[v] = ++timer;
        up[v][0] = p;
        for (int i = 1; i <= l; i++) {
            int ancestor = up[v][i - 1];
            if (ancestor != 0) {
                up[v][i] = up[ancestor][i - 1];
            }
        }
        for (int u : g.get(v)) {
            if (u != p) {
                depth[u] = depth[v] + 1;
                dfs(u, v);
            }
        }
        tout[v] = ++timer;
    }
    static boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }
    static int lca(int u, int v) {
        if (isAncestor(u, v)) return u;
        if (isAncestor(v, u)) return v;
        for (int i = l; i >= 0;--i) {
            if(!isAncestor(up[u][i], v)) {
                u = up[u][i];
            }
        }
        return up[u][0];
    }
    public static int distance(int u, int v) {
        int lca = lca(u, v);
        return depth[u] + depth[v] - 2*depth[lca];
    }
    public static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public String next() {
            while(st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
