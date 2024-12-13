import java.util.ArrayList;

public class MyUnionFind {
	private int[] parent;
	public int biggestComponent;
	private ArrayList<ArrayList<Integer>> members;

	public MyUnionFind(int n) {
		parent = new int[n];
		members = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			members.add(new ArrayList<>());
		}
		for (int i = 0; i < n; i++) {
			makeSet(i);
		}
		biggestComponent = 1;
	}

	public void makeSet(int v) {
		parent[v] = v;
		members.get(v).add(v);
	}

	public boolean same(int u, int v) {
		return parent[u] == parent[v];
	}

	// Complexity: O(min(|ZHK[u]|, |ZHK[v]|)), where ZHK[i] is the connected
	// component
	// If it is done on the whole set, the amortized complexity is O(n log n)
	// if ZHK'(u) is the new connected component, |ZHK'[u]| = |ZHK[u]| + |ZHK[v]| >=
	// |ZHK[u]| + |ZHK[u]| = 2|ZHK[u]|
	public void union(int u, int v) {
		if (members.get(parent[u]).size() > members.get(parent[v]).size()) {
			int t = u;
			u = v;
			v = t;
		}
		// merge the smaller set to the bigger one. |ZHK[u]| >= |ZHK[v]|
		for (int x : members.get(u)) {
			parent[x] = parent[v];
			members.get(parent[v]).add(x);
		}
		biggestComponent = Math.max(biggestComponent, members.get(parent[v]).size());

	}


	public static void main(String[] args) {
		int[] a = new int[] { 0, 1, 2 };
		int n = 3;
		MyUnionFind myUnionFind = new MyUnionFind(n);
		for (int i = 0; i < n; i++) {
			myUnionFind.makeSet(a[i]);
		}
		System.out.println("First union: a[0] a[1]");
		myUnionFind.union(a[0], a[1]);
		System.out.println(myUnionFind.same(a[0], a[1]));
		System.out.println(myUnionFind.same(a[1], a[0]));
		System.out.println(myUnionFind.same(a[0], a[2]));
		System.out.println(myUnionFind.same(a[2], a[1]));
		System.out.println(myUnionFind.same(a[2], a[2]));
		System.out.println("Second union: a[0], a[2]");
		myUnionFind.union(a[0], a[2]);
		System.out.println(myUnionFind.same(a[0], a[1]));
		System.out.println(myUnionFind.same(a[1], a[0]));
		System.out.println(myUnionFind.same(a[0], a[2]));
		System.out.println(myUnionFind.same(a[2], a[1]));
		System.out.println(myUnionFind.same(a[2], a[2]));
	}
}
