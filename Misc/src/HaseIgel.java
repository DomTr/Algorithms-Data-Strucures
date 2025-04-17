import java.util.Scanner;

public class HaseIgel {
	/*
	 * Hese-Igel algorithm
	 * Problem description: Given an array a[1...n], where a[i] is some element in {1,...,n-1}, find in O(n) i!=j with a[i]=a[j] without changing a[] and with O(1) memory.
	 * Because of memory, frequency arrays and maps are too expensive.
	 * 
	 * Elements have to be in {1,...,n-1} because of Pigeon Hole principle.
	 * Main idea of the solution is t model things as graph
	 * Example:
	 * a[] = 1 3 2 5 4 5 => 4, 6
	 * a[] = 1 7 6 5 4 3 2 1 => 1 8
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a[] = new int[n+1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		int hase = a[a[n]], igel = a[n], i = 1;
		while (hase != igel) {
			igel = a[igel]; hase = a[a[hase]];
			i++;
		}
		hase = n;
		int j = n;
		// Finding i and j. Now Igel and Hase run with the same speed
		while (hase != igel) {
			i = igel;j = hase;
			igel = a[igel]; hase=a[hase];
		}
		System.out.printf("i= %d, j= %d, duplicate=%d\n", i, j, a[i]);
	}
}
