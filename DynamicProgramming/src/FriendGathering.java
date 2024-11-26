import java.util.Scanner;
public class FriendGathering {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] A = new int[n+1];
		for (int i = 1; i <= n; i++) {
			A[i] = sc.nextInt();
		}
		int[] B = new int[n+1];
		for (int i = 1; i <= n; i++) {
			B[i] = sc.nextInt();
		}
		sc.close();
		System.out.println(maxFriends(n, k, A, B));
	}
	public static int maxFriends(int n, int k, int[] A, int[] B) {
		int[][][]dp = new int[n+1][n+1][k+1];
	      // Base case
	      for (int c = 0; c <= k; c++) {
	        dp[0][0][c] = 0;
	      }
	      for (int j = 1; j <= n; j++) {
	    	  for (int c = 0; c <= k; c++) {
	    		  dp[0][j][c] = Integer.MAX_VALUE;
	    	  }
	      }
	      for (int i = 1; i <= n; i++) {
	        for (int j = 1; j <= i; j++) {
	          for (int c = 0; c <= k; c++) {
	              //int current = dp[i][j][c];
	              int iNotTaken = j < i ? dp[i-1][j][c] : Integer.MAX_VALUE;
	              int iTaken = c - A[i] >= 0 ? dp[i-1][j-1][c-A[i]] + B[i] : Integer.MAX_VALUE; // kazkodel negative 3 3
	              if (iTaken < 0) iTaken = Integer.MAX_VALUE;
	              dp[i][j][c] = Math.min(iNotTaken, iTaken);
	              // int a = dp[i][j][c];
	              // int b = a - 9;
	          }
	        }
	      }
	      int maximum = 0;
	      for (int i = 0; i <= n; i++) {
	        for (int j = 0; j <= i; j++) {
	          if (dp[i][j][k] <= k && dp[i][j][k] >= 0 && maximum < j) {
	            maximum = j;
	          }
	        }
	      }
	      return maximum;
	}
	public static int maxFriends2(int n, int k, int[] A, int[] B) {
	    // dp[i][a][b] - maxFriends from 0...i who can be invited and do not drink more than a cups of coffee and b cups of tea in total
		// complexity: O(n k^2)
	    int[][] prev = new int[k+1][k+1];
	    int[][] curr = new int[k+1][k+1];
	    // Base case
	    for (int a = A[0]; a <= k; a++) {
	      for (int b = B[0]; b <= k; b++) {
	        prev[a][b] = 1;
	      }
	    }
	    for (int i = 1; i < n; i++) {
	      for (int a = 0; a <= k; a++) {
	        for (int b = 0; b <= k; b++) {
	          if (a - A[i] >= 0 && b - B[i] >= 0) {
	            curr[a][b] = Math.max(prev[a][b], 1+prev[a-A[i]][b-B[i]]);
	          } else { // case, when no friend can be added
	            curr[a][b] = prev[a][b];
	          }
	        }
	      }
	      prev = curr;
	      curr = new int[k+1][k+1];
	    }
	    return prev[k][k];
	  }
	public static int minimum(int a, int b, int c) {
		return Math.min(a,  Math.min(b, c));
	}
}
