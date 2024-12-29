import java.util.Arrays;

public class LC_650_2_KeysKeyboard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		System.out.println(minSteps(n));
	}

	public static int minSteps(int n) {
		/*
		 * Gal ir kitoks yra sprendimo budas:
		 * dp[i][j] - minimal number of operations to get i occurences of A with j A's in the clipboard after
		 * dp[0][0] = 0;
		 * dp[0][j] = INF, 0 < j <= n
		 * dp[i][j] = Min(dp[i-k]+dp[k]) 0 <= k <= i
		 * 
		 */
		int[] dp = new int[Math.max(n+1, 3)];
		int[] erasthotenes = new int[n+1];
		Arrays.fill(erasthotenes, -1);
		erasthotenes[1] = 1;	
		for (int i = 2; i <= n; i++) {
			if (erasthotenes[i] == -1) {
				for (int j = 2 * i; j <= n; j+=i) {
					if (erasthotenes[j] == -1) erasthotenes[j] = i;
				}
			}
		}
		dp[0] = 0;
		dp[1] = 0;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			if (erasthotenes[i] == -1) {
				dp[i] = i;
			} else {
				dp[i] = 1 + dp[i/erasthotenes[i]] + erasthotenes[i]-1;
			}
		}
		return dp[n];
	}
	public static int minSteps2 (int n) {
		long[][] dp = new long [Math.max(n+1, 3)][Math.max(n+1, 3)];
		dp[0][0] = 0;
		for (int j = 1; j <= n; j++) {
			dp[0][j] = Integer.MAX_VALUE;
		}
		for (int i = 1; i <= n; i++) {
			for (int k = 1; k <= i; k++) {
				//dp[i][]
			}
		}
		return 0;
	}
	
}
