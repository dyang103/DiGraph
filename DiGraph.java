package assignment5_f20;

import java.util.*;

public class DiGraph implements DiGraphInterface {

	  // in here go all your data and methods for the graph

	  public DiGraph ( ) { // default constructor
	    // explicitly include this
	    // we need to have the default constructor
	    // if you then write others, this one will still be there
	  }
	  
	// rest of your code to implement the various operations
	
	public HashMap<String, Node> nodes = new HashMap<>();
	public HashMap<Node, HashSet<Edge>> outEdges = new HashMap<>();
	public HashSet<Long> idNums = new HashSet<>();

	public long counter = 0;

	@Override
	public boolean addNode(long idNum, String label) {
		// TODO Auto-generated method stub
		if (idNum < 0 || label == null || nodes.containsKey(label)) {
			return false;
		}
		
		if (!idNums.contains(idNum)) {
			counter = idNum;
		}
		
		Node node = new Node(counter, label);
		nodes.put(label, node);
		idNums.add(counter);
		outEdges.put(node, new HashSet<Edge>());

		
		counter++;
		
		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		// TODO Auto-generated method stub
		if (idNum < 0 || !nodes.containsKey(sLabel) || !nodes.containsKey(dLabel)) {
			return false;
		}
		
		long w = 1;
		w = weight;
		
		if (!idNums.contains(idNum)) {
			counter = idNum;
		}
		
		Edge edge = new Edge(counter, sLabel, dLabel, w, eLabel);
		counter++;
		
		for (Edge e : outEdges.get(nodes.get(sLabel))) {
			if (e.getDLabel().compareTo(dLabel) == 0) {
				return false;
			}
		}
		
		outEdges.get(nodes.get(sLabel)).add(edge);	
		
		return true;
	}

	@Override
	public boolean delNode(String label) {
		// TODO Auto-generated method stub
		
		if (!nodes.containsKey(label)) {
			return false;
		}
		
		outEdges.remove(nodes.get(label));
		nodes.remove(label);
		
		for (Node n : nodes.values()) {
			Edge d = null;
			for (Edge e : outEdges.get(n)) {
				if (e.getDLabel().compareTo(label) == 0) {
					d = e;
				}
			}
			if (d != null) {
				outEdges.get(n).remove(d);
			}
		}
		
		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// TODO Auto-generated method stub
		
		if (!nodes.containsKey(sLabel) || !nodes.containsKey(dLabel)) {
			return false;
		}
		
		Edge del = null;
		
		for (Edge e : outEdges.get(nodes.get(sLabel))) {
			if (e.getDLabel().compareTo(dLabel) == 0) {
				del = e;
			}
		}
		
		if (del != null) {
			outEdges.get(nodes.get(sLabel)).remove(del);
			return true;
		}
		
		return false;
	}

	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return (long) nodes.size();
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		long num = 0;
		
		for (Node node : outEdges.keySet()) {
			num += outEdges.get(node).size();
		}
		
		return num;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> pq = new PriorityQueue<>(nodes.size(), new Comparator<Node> () {
			
			public int compare(Node n1, Node n2) {
				return (int) n1.getDistance() - (int) n2.getDistance();
			}
		});
		
		ShortestPathInfo[] info = new ShortestPathInfo[nodes.size()];
		HashSet<String> labels = new HashSet<>();
		
		for (Node n : nodes.values()) {
			n.setDistance((long) Integer.MAX_VALUE);
			n.setHandled(false);
		}
		
		Node first = nodes.get(label);
		first.setDistance(0);
		pq.add(first);
		
		int index = 0;
		
		while (!pq.isEmpty()) {
			Node min = pq.peek();
			pq.remove(min);
			
			if (min.getHandled()) {
				continue;
			}
			min.setHandled(true);
			ShortestPathInfo howFar = new ShortestPathInfo(min.getName(), min.getDistance());
			info[index] = howFar;
			index++;
			labels.add(howFar.getDest());
			for (Edge e : outEdges.get(min)) {
				long wait = e.getWeight() + min.getDistance();
				if (wait < nodes.get(e.getDLabel()).getDistance()) {
					nodes.get(e.getDLabel()).setDistance(wait);
					nodes.get(e.getDLabel()).setPrev(e.getSLabel());
				}
				pq.add(nodes.get(e.getDLabel()));
			}
			
		}
		
		for (Node n : nodes.values()) {
			if (!labels.contains(n.getName())) {
				ShortestPathInfo p = new ShortestPathInfo(n.getName(), -1);
				info[index] = p;
				index++;
			}
		}	
		
//		for (int i = 0; i < info.length; i++) {
//			System.out.print(info[i].getDest());
//			System.out.print("  " + info[i].getTotalWeight() + "\n");
//		}
		
		
		return info;
	}
	  
}