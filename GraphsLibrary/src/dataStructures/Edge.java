package dataStructures;

public class Edge implements Comparable<Edge>{
	public int a;
	public int b;
	public int w;
	public Edge(int a, int w, int b) {
		this.a = a;
		this.b = b;
		this.w = w;
	}
	@Override
	public int compareTo(Edge o) {
		return w - o.w;
	}
	@Override
	public String toString() {
		return a + " " + w + " " + b;
	}
}
