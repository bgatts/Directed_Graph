package assignment5_f20;

import java.util.ArrayList;

public interface NodeInterface {
	
	long getidNum();
	String getLabel();
	ArrayList<Edge> getConnectedEdges();
	void addEdge(Edge edge);
	void addStartEdge(Edge edge);
	void addEndEdge(Edge edge);
	ArrayList<Edge> getStartEdges();
	ArrayList<Edge> getEndEdges();
	void setStartDist(long dist);
	long getStartDist();
	void setLastVisted(Node node);
	Node getLastVisited();



}
