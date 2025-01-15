import java.util.Arrays;
import java.util.Scanner;

public class LC1626_TeamWithNoConflicts {
	/*
	 * Maybe it's possible to do it in another way, not sure.
5
1 3 5 10 15
1 2 3 4 5
=> 34
4
4 5 6 5
2 1 2 1

4
1 2 3 5
8 9 10 1
=> 6
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] scores = new int[n];
		int[] ages = new int[n];
		for (int i = 0; i < n; i++) {
			scores[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			ages[i] = sc.nextInt();
		}
		sc.close();
		LC1626_TeamWithNoConflicts program = new LC1626_TeamWithNoConflicts();
		System.out.println(program.bestTeamScore(scores, ages));
	}

	public int bestTeamScore(int[] scores, int[] ages) {
		/*
		 * Sort scores according to decreasing order of ages.
		 * Find longest non-decreasing sequence of scores k.
		 * Go from i = 1...k and sum up lnds[i]
		 */
		int n = scores.length;
		Player[] players = new Player[n];
		for (int i = 0; i < n; i++) {
			players[i] = new Player(scores[i], ages[i]);
		}
		Arrays.sort(players);
		players = normalizeArray(players);
		players[1].dpVal = players[1].score;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				if (players[i].age > players[j].age && players[i].score < players[j].score) continue;
				else {
					players[i].dpVal = Math.max(players[i].dpVal, players[i].score + players[j].dpVal);
				}
			}
		}
		int maximum = 0;
		for (int i = 1; i <= n; i++) {
			maximum = Math.max(maximum, players[i].dpVal);
		}
		return maximum;
	}
	public int findIndex(int n, int[] lnds, int score) { // finds right-most index which is bigger-or-equal than score
		int l = 0, r = n+1; // invariant: lnds[l] >= score, lnds[r] < score
		while (r - l > 1) {
			int m = l + (r-l)/2;
			if (lnds[m] >= score) {
				l = m;
			} else {
				r = m;
			}
		}
		return l;
	}
	public Player[] normalizeArray(Player[] array) {
		int n = array.length;
		Player[] a = new Player[n+1];
		for (int i = 0; i < n; i++) {
			a[i+1] = array[i];
		}
		return a;
	}
	class Player implements Comparable<Player> {
		int score;
		int age;
		int dpVal;
		public Player(int score, int age) {
			this.score = score;
			this.age = age;
			dpVal = score;
		}
		@Override
		public int compareTo(LC1626_TeamWithNoConflicts.Player o) {
			if (age != o.age) return (age - o.age);
			return (score - o.score);
		}
	}
}
