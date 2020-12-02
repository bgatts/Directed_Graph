package assignment5_f20;

import java.util.ArrayList;

public class Node implements NodeInterface{
	long idNum;
	String label;
	ArrayList<Edge> edges;
	ArrayList<Edge> startEdges;
	ArrayList<Edge> endEdges;
	long startDist = 10000000;
	Node lastVisited= null;


	
	public Node(long idNum, String label){
		this.idNum = idNum;
		this.label = label;
		this.edges = new ArrayList<Edge>();
		this.startEdges = new ArrayList<Edge>();
		this.endEdges = new ArrayList<Edge>();

	}

	@Override
	public long getidNum() {
		return idNum;
	}

	@Override
	public String getLabel() {
		return label;
	}
	
	public ArrayList<Edge> getConnectedEdges(){
		return edges;
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	@Override
	public void addStartEdge(Edge edge) {
		startEdges.add(edge);
		
	}

	@Override
	public void addEndEdge(Edge edge) {
		endEdges.add(edge);
	}

	@Override
	public ArrayList<Edge> getStartEdges() {
		// TODO Auto-generated method stub
		return startEdges;
	}

	@Override
	public ArrayList<Edge> getEndEdges() {
		return endEdges;
	}

	@Override
	public void setStartDist(long dist) {
		startDist = dist;
		
	}

	@Override
	public long getStartDist() {
		return startDist;
	}

	@Override
	public void setLastVisted(Node node) {
		lastVisited = node;
		
	}

	@Override
	public Node getLastVisited() {
		return lastVisited;
	}
	
	

}
