// Finds all subsets of a set

import java.util.ArrayList;
import java.util.Scanner;

public class FindAllSubsets {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		var v = generate(a, n-1);
		System.out.println(v.toString());
	}
	public static ArrayList<ArrayList<Integer>> generate(int[] a, int n) {
		if (n == -1) { // -1, not 0 because array a is 0-indexed
			ArrayList<Integer> empty = new ArrayList<Integer>();
			ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
			ans.add(empty);
			return ans;
		} else {
			ArrayList<ArrayList<Integer>> lst = generate(a, n-1);
			ArrayList<ArrayList<Integer>> cpy = deepCopy(lst);
			for (var l : cpy) {
				l.add(a[n]);
			}
			lst.addAll(cpy);
			return lst;
		}
	}
	public static ArrayList<ArrayList<Integer>> deepCopy(ArrayList<ArrayList<Integer>> a) {
		ArrayList<ArrayList<Integer>> cpy = new ArrayList<>();
		for (ArrayList<Integer> lst : a) {
			cpy.add(new ArrayList<>(lst));
		}
		return cpy;
	}
}
