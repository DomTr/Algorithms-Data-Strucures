import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataStructures.Edge;
/*
 * possible tests:
 * /*
	 2 2
	 0 10 1
	 0 2  1
	 =>
	 Total 2 
	 0 2 1
	 
	 3 3
	 0 10 1
	 1 1 2
	 1 2 0
	 =>
	 Total 3
	 0 2 1
	 1 1 2
	 
	 4 4
	 0 10 1
	 0 10 2
	 1 1 3
	 1 1 0
	 =>
	 Total 12
	 0 1 1
	 1 1 3
	 0 10 2 
*/
class MSTTest {
	public boolean compareMSTs(ArrayList<Edge> mst1, ArrayList<Edge> mst2) {
		if (mst1.size() != mst2.size()) return false;
		int n = mst1.size();
		for (int i = 0; i < n-1; i++) {
			
		}
		return true;
	}
	public boolean sumEqual(int expectedSum, int gotSum) {
		return expectedSum == gotSum;
	}
	public int getMSTSum(ArrayList<Edge> mst) {
		int ans = 0;
		for (Edge e : mst) {
			ans += e.w;
		}
		return ans;
	}
	@Test
	void test() {
		ArrayList<Edge> edges = new ArrayList<>();
		int n = 2;
		edges.add(new Edge(0, 10, 1));
		edges.add(new Edge(0, 2, 1));
		ArrayList<Edge> actualMst = MST.Kruskal(0, n, edges);
		ArrayList<Edge> expected = new ArrayList<>();
		expected.add(new Edge(0, 2, 1));
		int actualSum = getMSTSum(actualMst);
		int expectedSum = 2;
		assertTrue(sumEqual(expectedSum, actualSum));
	}
	

}
