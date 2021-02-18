package assignment5_f20;

public class Node{
	public long idNum;
	public String label;
	public long distance;
	public String prev = null;
	public boolean handled = false;
	
	Node(long id, String name){
		idNum = id;
		label = name;
		distance = 0;
	}

	public long getID() {
		return idNum;
	}

	public String getName() {
		return label;
	}
	
	public void setDistance(long x) {
		distance = x;
	}
	
	public long getDistance() {
		return distance;
	}
	
	public void setPrev(String label) {
		prev = label;
	}
	
	public String getPrev() {
		return prev;
	}
	
	public void setHandled(boolean bool) {
		handled = bool;
	}
	
	public boolean getHandled() {
		return handled;
	}
}
