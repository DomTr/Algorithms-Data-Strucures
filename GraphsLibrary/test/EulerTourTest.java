import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class EulerTourTest {
	
	@Test
	void test() {
		ArrayList<ArrayList<Integer>> G = new ArrayList<>();
		ArrayList<ArrayList<Integer>> dirG = new ArrayList<>();
		ArrayList<ArrayList<Integer>> revG = new ArrayList<>();
		int n = 4;
		int m = 4;
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<>());
			dirG.add(new ArrayList<>());
			revG.add(new ArrayList<>());
		}
		G.get(0).add(1);
		G.get(1).add(0);
		dirG.get(0).add(1);
		revG.get(1).add(0);
		
		G.get(1).add(2);
		G.get(2).add(1);
		dirG.get(1).add(2);
		revG.get(2).add(1);
		
		G.get(2).add(0);
		G.get(0).add(2);
		dirG.get(2).add(0);
		revG.get(0).add(2);
		
		G.get(2).add(3);
		G.get(3).add(2);
		dirG.get(2).add(3);
		revG.get(3).add(2);
		
		EulerTour euler = new EulerTour(G, dirG, revG, n, m);
		euler.g = G;
		euler.dirG = dirG;
		euler.revG = revG;
		euler.n = n;
		euler.m = m;
		boolean verdict = euler.hasDirectedEulerianWalk();
		assertTrue(verdict);
	}
	@Test
	void test2() {
		ArrayList<ArrayList<Integer>> G = new ArrayList<>();
		ArrayList<ArrayList<Integer>> dirG = new ArrayList<>();
		ArrayList<ArrayList<Integer>> revG = new ArrayList<>();
		int n = 4;
		int m = 4;
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<>());
			dirG.add(new ArrayList<>());
			revG.add(new ArrayList<>());
		}
		G.get(0).add(1);
		G.get(1).add(0);
		dirG.get(0).add(1);
		revG.get(1).add(0);
		
		G.get(1).add(0);
		G.get(0).add(1);
		dirG.get(1).add(0);
		revG.get(0).add(1);
		
		G.get(2).add(3);
		G.get(3).add(2);
		dirG.get(2).add(3);
		revG.get(3).add(2);
		
		G.get(3).add(2);
		G.get(2).add(3);
		dirG.get(3).add(2);
		revG.get(2).add(3);
		
		EulerTour euler = new EulerTour(G, dirG, revG, n, m);
		euler.g = G;
		euler.dirG = dirG;
		euler.revG = revG;
		euler.n = n;
		euler.m = m;
		boolean verdict = euler.hasDirectedEulerianWalk();
		assertFalse(verdict);
	}
	@Test
	void test3() {
		ArrayList<ArrayList<Integer>> G = new ArrayList<>();
		ArrayList<ArrayList<Integer>> dirG = new ArrayList<>();
		ArrayList<ArrayList<Integer>> revG = new ArrayList<>();
		int n = 5;
		int m = 4;
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<>());
			dirG.add(new ArrayList<>());
			revG.add(new ArrayList<>());
		}
		G.get(0).add(1);
		G.get(1).add(0);
		dirG.get(0).add(1);
		revG.get(1).add(0);
		
		G.get(1).add(2);
		G.get(2).add(1);
		dirG.get(1).add(2);
		revG.get(2).add(1);
		
		G.get(2).add(3);
		G.get(3).add(2);
		dirG.get(2).add(3);
		revG.get(3).add(2);
		
		G.get(2).add(4);
		G.get(4).add(2);
		dirG.get(2).add(4);
		revG.get(4).add(2);
		
		EulerTour euler = new EulerTour(G, dirG, revG, n, m);
		euler.g = G;
		euler.dirG = dirG;
		euler.revG = revG;
		euler.n = n;
		euler.m = m;
		boolean verdict = euler.hasDirectedEulerianWalk();
		assertFalse(verdict);
	}
}
