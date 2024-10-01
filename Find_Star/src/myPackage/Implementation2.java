package myPackage;

public class Implementation2 {
	/*
	 * 1. Determine each node's out-degree
	 * 2. If there are at least two nodes with out-degree 0, then there is no star
	 * 3. Let's say only node A has degree 0. We check if every other node is connected with A.
	 * 
	 * OR
	 * 1. Determine each node's out-degree and in-degree. If there is only one node with in-deg n-1 and out-deg 0, it is the star.
	 * Otherwise no star. CAREFUL: if edges were written multiple times, an edge has to be counted only once in in-deg and out-deg.
	 * Running time of program: O(N+M), running time algorithm: O(N) if adjacency matrix is used, O(NlogN) if HashSet is used.
	 */
}
