import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
public class TravelInfrastructure {
	/*
	 * 
4 4
100
1
3
1
0 1 2
1 2 4
0 2 3
2 3 5

4 4
100
100
100
100
0 1 2
1 2 4
0 2 3
2 3 5
=> 10
Done!



4 4
100
1
3
1
0 1 2
1 2 4
0 2 3
2 3 5
=> 7

4 3
1
1
1
500
0 1 2
1 2 3
0 2 2
=> 503

4 0
5
10
15
20
=> 50

	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
	      int m = sc.nextInt();
	      // Read in the airport costs
	      int[] A = new int [n];
	      for (int i = 0; i < n; i++){
	        A[i] = sc.nextInt();
	      }
	      // Read in the road cost w to connect cities u and v 
	      Edge[] E = new Edge[m];
	      for (int i = 0; i < m; i++){
	        int u = sc.nextInt();
	        int v = sc.nextInt();
	        int w = sc.nextInt();
	        E[i] = new Edge(u, v, w);
	      }
	      sc.close();
	      System.out.println(minCost(n, m, A, E));
	}
	
	public static int minCost(int n, int m, int [] A, Edge [] E) {
	    // TODO: implement
	    // Additional city called 'AIR'. For the first example 0 we have (0, AIR, 100), (1, AIR, 1), (2, AIR, 3), (3, AIR, 1)
	    // Then minimal spanning tree on the graph.
	    // Write own disjoint union
	    // Write own merge-sort - no need, Arrays are imported, so it's enough to sort stuff.
	    // Possible problem: AIR doesn't need to be connected with every other node. It's fine if other nodes are connected by roads
		Edge[] newEdges = new Edge[m+n];
	    copyRange(newEdges, E);
	    for (int i = 0; i < n; i++) {
	      newEdges[m+i] = new Edge(i, n, A[i]); // n - AIR node
	    }
	    Arrays.sort(newEdges);
	    MyUnionFind myUnionFind = new MyUnionFind(n+1); // additional AIR node
	    int totalSum = 0;
	    int lastI = 0;
	    for (int i = 0; i < newEdges.length && myUnionFind.biggestComponent < n; i++) {
	      int u = newEdges[i].u;
	      int v = newEdges[i].v;
	      int w = newEdges[i].w;
	      if(!myUnionFind.same(u, v)) {
	        totalSum += w;
	        myUnionFind.union(u, v);
	      }
	      lastI = i;
	    }
	    if (myUnionFind.same(0, n)) { // comparing to 0 is enough
	      // AIR node must have been added, so it remains to search for the last edge which would add the last node
	      for (int i = lastI; i < newEdges.length && myUnionFind.biggestComponent < n + 1; i++) {
	        int u = newEdges[i].u;
	        int v = newEdges[i].v;
	        int w = newEdges[i].w;
	        if(!myUnionFind.same(u, v)) {
	          totalSum += w;
	          myUnionFind.union(u, v);
	          break;
	        }
	      }
	    }
	    return totalSum;
	  }
	  public static void copyRange(Edge[] a, Edge[] b) {
	    for (int i = 0; i < b.length; i++) {
	      a[i] = b[i];
	    }
	  }
	  
	  public static class MyUnionFind {
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
	      public boolean same(int u, int v){
	        return parent[u] == parent[v];
	      }
	      public void union(int u, int v) {
	        if (members.get(parent[u]).size() > members.get(parent[v]).size()) {
	          int t = u;
	          u = v;
	          v = t;
	        }
	        for (int x : members.get(parent[u])) {
	          parent[x] = parent[v];
	          members.get(parent[v]).add(x);
	        }
	        biggestComponent = Math.max(biggestComponent, members.get(parent[v]).size());
	      }
	  }

	  
	    static class Edge implements Comparable<Edge> {
	    public int u;
	    public int v;
	    public int w;
	    
	    public Edge (int u, int v, int w) {
	      this.u = u;
	      this.v = v;
	      this.w = w;
	    }
	    @Override
	    public int compareTo(Edge other) {
	      return w - other.w;
	    }
	  }
	  
	   
	  // You can use the following class we define for you. This defines "Tuple"
	  // objects that can be sorted according to the compareTo function that you
	  // define. This allows you, for example, to use Java Arrays.sort to sort a
	  // collection. Of course, in the current form the class is empty,
	  // you need to add variables, etc.
	  static class Tuple implements Comparable<Tuple> {
	    int a;
	    int b;
	    int w;
	    public Tuple(int a, int b, int w) {
	      this.a = a;
	      this.b = b;
	      this.w = w;
	    }
	    
	    // In case you want to sort a custom class you need to have this method
	    @Override public int compareTo(Tuple other) {
	      // in case this is > than other, return a positive number,
	      // otherwise a negative number
	      return w - other.w;
	    }
	  }
}
