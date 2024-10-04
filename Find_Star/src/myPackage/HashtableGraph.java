package myPackage;

import java.util.Hashtable;
import java.util.HashSet;

public class HashtableGraph extends Graph {
	private Hashtable<Integer, HashSet<Integer>> graph;
	public HashtableGraph() {
		graph = new Hashtable<Integer, HashSet<Integer>>();
	}
	public HashtableGraph(Hashtable<Integer, HashSet<Integer>> ht) {
		graph = ht;
	}
	public void addEdge(int a, int b) {
		if(graph.contains(Integer.valueOf(a))) {
			graph.get(a).add(b);
		} else {
			graph.put(a, new HashSet<Integer>());
			graph.get(a).add(b);
		}
	}
	public boolean knows(int a, int b) {
		if (graph.containsKey(a)) {
			return graph.get(a).contains(b);
		}
		return false;
	}
	public int size() {
		return graph.size();
	}
	@Override
	public void swap(int i, int j) {
		// TODO Auto-generated method stub
		HashSet<Integer> iNeighbours = graph.getOrDefault(i, new HashSet<Integer>());
		HashSet<Integer> jNeighbours = graph.getOrDefault(j, new HashSet<Integer>());
		graph.put(i, jNeighbours);
		graph.put(j, iNeighbours);
	}
	@Override
	public int get(int i) {
		// TODO Auto-generated method stub
		return i;
	}
	public HashSet<Integer> getNeighbours(int n) {
		return graph.getOrDefault(n, new HashSet<Integer>());
	}
}

