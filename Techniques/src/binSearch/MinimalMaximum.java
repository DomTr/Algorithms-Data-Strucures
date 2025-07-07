package binSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinimalMaximum {
    static ArrayList<ArrayList<Pair>> g;
    static int n;
    static int[] depth;
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        depth = new int[n+1];
        g = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }
        int maxC = 0;
        for (int i = 0; i < m; i++) {
            int a, b, c;
            a = sc.nextInt(); b = sc.nextInt(); c = sc.nextInt();
            g.get(a).add(new Pair(b, c));
            maxC = Math.max(maxC, c);
        }
        LinkedList<Integer> ans = binSearch(d, maxC);
        if (ans == null) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans.size() - 1);
        printList(ans);

    }

    public static LinkedList<Integer> binSearch(int d, int maxC) {
        int l = -1, r = maxC+1;
        LinkedList<Integer> acc = new LinkedList<>();
        LinkedList<Integer> tmp;
        while (r - l > 1) {
            int m = l + (r-l)/2;
            tmp = bfs(1, d, m);
            if (tmp != null) {
                r = m;
                acc =new LinkedList<>(tmp);
            } else l = m;
        }
        return acc;
    }
    public static LinkedList<Integer> bfs(int u, int d, int prediction) {
        int[] par = new int[n+1];
        int[] distance = new int[n+1];
        Arrays.fill(par, -1);
        Arrays.fill(distance, -1);
        Queue<Integer> q = new LinkedList<>();
        LinkedList<Integer> st = new LinkedList<>();
        q.add(u);
        distance[u] = 0;
        while(!q.isEmpty()) {
            int el = q.poll();
            if (distance[el] >= d) continue;
            for (var v : g.get(el)) {
                if (distance[v.x] == -1 && v.y <= prediction) {
                    distance[v.x] = distance[el] + 1;
                    q.add(v.x);
                    par[v.x] = el;
                }
            }
        }
        if (par[n] != -1) {
            int curr = n;
            while (curr != 1) {
                st.addFirst(curr);
                curr = par[curr];
            }
            st.addFirst(u);
            return st;
        } else return null;
    }
    public static LinkedList<Integer> bfs2(int start, int d, int maxEdgeCost) {
        int[] parent = new int[n + 1];
        int[] depth = new int[n + 1];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        depth[start] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == n) break;

            for (Pair p : g.get(u)) {
                int v = p.x;
                int cost = p.y;
                if (cost <= maxEdgeCost && parent[v] == -1) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    if (depth[v] <= d)
                        q.add(v);
                }
            }
        }

        if (parent[n] == -1) return null;

        LinkedList<Integer> path = new LinkedList<>();
        int cur = n;
        while (cur != -1) {
            path.addFirst(cur);
            cur = parent[cur];
        }
        return path;
    }

    public static void printList(LinkedList<Integer> lst) {
        StringBuilder sb = new StringBuilder();
        for (var v : lst) {
            sb.append(v).append(" ");
        }
        System.out.println(sb);
    }
    public record Pair (int x, int y){ }
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
