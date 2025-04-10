
public class UnionFind {
	private int[] parent;
	private int[] rank;
	private int[] smallestPar;
	private int[] biggestPar;
	private int[] size;

	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		smallestPar = new int[n];
		biggestPar = new int[n];
		size = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			smallestPar[i] = i;
			biggestPar[i] = i;
			size[i] = 1;
			rank[i] = 0;
		}
	}

	public void makeSet(int u) {
		parent[u] = u;
	}

	public int findSet(int x) {
        if (biggestPar[x] == x) return x;
        else {
            int p = findSet(biggestPar[x]);
            biggestPar[x] = p;
            return p;
        }
    }

	public void union(int x, int y) {

		int parX = findSet(x), parY = findSet(y);
		if (parX == parY)
			return;
		if (parX > parY) {
			size[parX] += size[parY];
			biggestPar[parY] = parX;
			smallestPar[parX] = Math.min(smallestPar[parX], smallestPar[parY]);
		} else {
			size[parY] += size[parX];
			biggestPar[parX] = parY;
			smallestPar[parY] = Math.min(smallestPar[parX], smallestPar[parY]);
		}
	}

	public int getSize(int x) {
		return size[findSet(x)];
	}

	public int getMin(int x) {
		return smallestPar[findSet(x)];
	}

	public int getMax(int x) {
		return biggestPar[findSet(x)];
	}
}
