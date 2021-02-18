package assignment5_f20;

public class Edge{
	public long idNum;
	public String sLabel;
	public String dLabel;
	public long weight;
	public String eLabel;
	
	
	public Edge(long id, String s, String d, long w, String e){	
		idNum = id;
		sLabel = s;
		dLabel = d;
		weight = w;
		eLabel = e;
	}

	public long getID() {
		return idNum;
	}

	public long getWeight() {
		return weight;
	}

	public String getSLabel() {
		return sLabel;
	}
	
	public String getDLabel() {
		return dLabel;
	}
	
	public String getELabel() {
		return eLabel;
	}
	
	

}
