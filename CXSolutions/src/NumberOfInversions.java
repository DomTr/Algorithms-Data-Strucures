import java.util.Scanner;

public class NumberOfInversions {
	/*
	 * Formal description of the problem:
	 * Given a permutation of n elements, find for each i the number of j s.t. j < i and p_j > p_i
	 * Approach: use segment trees, on an array with set/not set variable. 
	 * When processing a[i], check the sum to its right. This is exactly the number of j s.t. j < i and p_j > p_i
	 * Then set segmentTree data at index a[i] to 1, meaning that a[i] value was seen. 
	 */
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      int[] a = new int[n];
      for (int i = 0; i < n; i++) a[i] = sc.nextInt() - 1;
      sc.close();
      SegmentTree s = new SegmentTree(a);
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < n; i++) {
          sb.append(s.query(a[i]+1, n-1)).append(" ");
          s.modify(a[i],1);
      }
      System.out.println(sb);
  }
  public static class SegmentTree {
      int size;
      int[] entries;
      public SegmentTree(int[] a) {
          size = a.length;
          entries = new int[2*size];
      }
      public int func(int a, int b) {
          return a + b;
      }
      public int query(int l, int r) {
          l = l + size;
          r = r + (size+1);
          int res = 0;
          while (l < r) {
              if ((l % 2) == 1) {
                  res = func(res, entries[l]);
                  l++;
              }
              if((r % 2) == 1) {
                  r--;
                  res = func(res, entries[r]);
              }
              l/=2;
              r/=2;
          }
          return res;
      }
      private void modify(int idx, int x){
          idx= idx + size;
          entries[idx] = x;
          idx/=2;
          for(;idx > 0; idx/=2) {
              entries[idx] = func(entries[2*idx], entries[2*idx+1]);
          }
      }
  }
}
