package myPackage;

public abstract class Graph {
	public abstract void addEdge(int a, int b);
	public abstract boolean knows(int a, int b);
	public abstract int size();
	public abstract void swap(int i, int j);
	public abstract int get(int i);
}

