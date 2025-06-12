import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DecodeFromInversion {
	/*
	 * Formal description of the problem: given an array, where a[i] - no. of j < i s.t. p[j] > p[i], find the initial permutation p.
	 * This is the other side of the problem: Number of Inversions
	 */
	public static void main(String[] args) {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[n];
        int[] res = new int[n];
        Arrays.fill(arr, 1);
        SegmentTree s = new SegmentTree(arr);
        for (int i = n-1; i >= 0; i--) {
            int ans = s.query(a[i]);
            s.modify(ans, 0);
            res[i] = ans + 1;
        }
        for (int i = 0; i < n; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }
	public static class SegmentTree {
        int size;
        int[] data;
        public SegmentTree(int[] a) {
            size = 1;
            while(size < a.length) size*=2;
            data = new int[2*size];
            for (int i = 0; i < a.length; i++) {
                data[i+size] = 1;
            }
            build();
        }
        public int f(int a, int b) {
            return a + b;
        }
        public void build() {
            for (int i = size-1; i > 0; i--) {
                data[i] = f(data[2*i], data[2*i+1]);
            }
        }
 
        public int query(int k) {
            int idx = 1;
            while(idx < size) {
                if (data[2*idx+1] > k) {
                    idx = 2*idx+1;
                } else {
                    k -= data[2*idx+1];
                    idx = 2*idx;
                }
            }
            return idx - size;
        }
        public void modify(int idx, int val) {
            idx+=size;
            data[idx] = val;
            idx/=2;
            for(;idx > 0; idx/=2) {
                data[idx] = f(data[2*idx], data[2*idx+1]);
            }
        }
 
    }
    static class FastReader {
        StringTokenizer s;
        BufferedReader b;
        public FastReader() {
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
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
