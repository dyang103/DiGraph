package assignment5_f20;

import java.util.*;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
      exTest();
      buildonce(1000000, 2, 3);
    }
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "f");
      d.addNode(3, "s");
      d.addNode(7, "t");
      d.addNode(0, "fo");
      d.addNode(4, "fi");
      d.addNode(6, "si");
      d.addEdge(0, "f", "s", 0, null);
      d.addEdge(1, "f", "si", 0, null);
      d.addEdge(2, "s", "t", 0, null);
      d.addEdge(3, "fo", "fi", 5, null);
      d.addEdge(4, "fi", "si", 3, null);
      d.delNode("fi");
      d.shortestPath("fo");
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
    }
    
    public static void buildonce(int num_nodes, int num_edge_per_node, int seed) {;
	Random r = new Random(seed);
	DiGraph d = new DiGraph();
	long start = System.currentTimeMillis();
	d.addNode(0, "0");
	d.addNode(1, "1");
	int eid = 0;
	for(int i = 2; i < num_nodes; i ++) {
		d.addNode(i, Integer.toString(i));
		for(int j =0; j < num_edge_per_node; j ++)
		d.addEdge(eid++, Integer.toString(i), Integer.toString(r.nextInt(i)), r.nextInt(20), null);
	}
	long built = System.currentTimeMillis();
	System.out.println("numNodes: "+d.numNodes());
	System.out.println("numEdges: "+d.numEdges());
	System.out.println("Building took: " + (built - start));
	long startPath = System.currentTimeMillis();
	d.shortestPath(Integer.toString(r.nextInt(num_nodes)));
	long endPath = System.currentTimeMillis();
	System.out.println("Found paths in: " + (endPath-startPath));
	long sdel = System.currentTimeMillis();
//	for (int i = 0; i < num_nodes; i++) {
//		d.delNode(Integer.toString(i));
//	}
//	long edel = System.currentTimeMillis();
//	System.out.println("Destroying took: " + (edel-sdel));

}
}