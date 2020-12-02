package assignment5_f20;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class DiGraph implements DiGraphInterface {
	
	HashMap<Long, Node> nodeIdMap;
	HashMap<String, Node> nodeLabelMap;
	
	
	HashMap<Long, Edge> edgeMap;
	HashMap<String, Edge> edgeStartNode;
	HashMap<String, Edge> edgeEndNode;
	int nodeCounter;


  public DiGraph ( ) { // default constructor
	  this.nodeIdMap = new HashMap<Long, Node>();
	  this.nodeLabelMap= new HashMap<String,Node>();
	  this.edgeMap = new HashMap<Long,Edge>();
	  this.edgeStartNode = new HashMap<String, Edge>();
	  this.edgeEndNode = new HashMap<String, Edge>();
	  this.nodeCounter=0;
  }

@Override
public boolean addNode(long idNum, String label) {
	

	if(idNum < 0) {
		return false;
	}
	if (nodeLabelMap.containsKey(label)) {
		return false;
	}
	
	Node newNode = new Node(idNum, label);
	nodeIdMap.put(idNum, newNode);
	nodeLabelMap.put(label, newNode);
	nodeCounter++;
	return true;
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	if(idNum < 0) {
		return false;
	}
	if(!nodeLabelMap.containsKey(sLabel)) {
		return false;
	}
	if(!nodeLabelMap.containsKey(dLabel)) {
		return false;
	}
	
	if(edgeStartNode.containsKey(sLabel) && edgeEndNode.containsKey(dLabel)) {	//if start and dest nodes are the same
		if(edgeStartNode.get(sLabel).getidNum() ==  edgeEndNode.get(dLabel).getidNum()) {
			return false;
		}
	}
	
	Edge newEdge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
	edgeMap.put(idNum, newEdge);
	Node source = nodeLabelMap.get(newEdge.getSourceNode());			//get the node object of the source node label
	Node dest = nodeLabelMap.get(newEdge.getDestinationNode());			//do the same of the destination node
	//source.addStartEdge(newEdge);
	//dest.addEndEdge(newEdge);
	startAndEnd(source,dest,newEdge);
	

	
	
	edgeStartNode.put(sLabel,newEdge);
	edgeEndNode.put(dLabel,newEdge);
	
	return true;
}

@Override
public boolean delNode(String label) {
	if(!nodeLabelMap.containsKey(label)){
		return false;
	}
	
	ArrayList<Edge> edges =  nodeLabelMap.get(label).getConnectedEdges();
	
	for(int i = 0; i<edges.size();i++) {			//remove edges from each HashMap
		edgeMap.remove(edges.get(i).getidNum());
		edgeStartNode.remove(edges.get(i).getSourceNode());
		edgeEndNode.remove(edges.get(i).getDestinationNode());
	}
	
	Node removeNode = nodeLabelMap.get(label);
	nodeLabelMap.remove(label);			//remove the node itself
	nodeIdMap.remove(removeNode.getidNum());
	
	return true;
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	

  	if(edgeStartNode.containsKey(sLabel) && edgeEndNode.containsKey(dLabel)) {
  		
		ArrayList<Edge> startEdges = nodeLabelMap.get(sLabel).getStartEdges();
		ArrayList<Edge> endEdges = nodeLabelMap.get(dLabel).getEndEdges();
		

		for(int i = 0; i<startEdges.size(); i++) {
			for(int j = 0; j<endEdges.size(); j++) {
				if(startEdges.get(i).equals(endEdges.get(j))) {
					edgeMap.remove(startEdges.get(i).getidNum());
					edgeStartNode.remove(startEdges.get(i).getSourceNode());
					edgeEndNode.remove(startEdges.get(i).getDestinationNode());
					return true;
				}
			}
		}
	
	
	}
	
	return false;
}

@Override
public long numNodes() {
	return nodeIdMap.size();
}

@Override
public long numEdges() {
	return edgeMap.size();
}

@Override
public ShortestPathInfo[] shortestPath(String label) {
	
	int index = 0;
	int counter= 0;
	
	
	Set<String> keys = nodeLabelMap.keySet();
	String[] array = new String[keys.size()];
	keys.toArray(array);
	List<String> unvisited = new LinkedList<String>(Arrays.asList(array));
	ArrayList<Node> visited = new ArrayList<>();

	
	int nodeNum = (int) numNodes();
	ShortestPathInfo[] shortestPath = new ShortestPathInfo[nodeNum];	
	Node currNode = nodeLabelMap.get(label);		//get start node
	currNode.setStartDist(0);						//load dist to self
	currNode.setLastVisted(currNode);				//set start's last visited to self
	
	PriorityQueue<Node> distances = new PriorityQueue<>(nodeNum, new NodeComparator());				//setup priority que
	distances.add(currNode);					//put root in queue
	
	
	for(int j = 0; j<nodeNum;j++) {
	//while(!distances.isEmpty()) {	
		
		Node shortestNode = distances.peek();		//get head node

		if(distances.isEmpty()) {
			break;
		}
		distances.remove();							//remove from pq


		System.out.println(shortestNode.getLabel());
		
		//if(shortestNode.getStartEdges().size()>0) {
			for(int i = 0; i<shortestNode.getStartEdges().size(); i++) {
				Node neigbor = nodeLabelMap.get(shortestNode.getStartEdges().get(i).getDestinationNode());		//load distance to start node
				long prevDist = shortestNode.getStartDist();				//get start dist for start node
				long totDist = shortestNode.getStartEdges().get(i).getWeight() + prevDist;		//add weight of edge to start node distance to start
				if(totDist<neigbor.getStartDist()) {			//if less than what is already set reset it
					neigbor.setStartDist(totDist);
				}
				
				//System.out.println(shortestNode.getLabel());
				//System.out.println(neigbor.getLabel());
				if(!visited.contains(shortestNode) && !distances.contains(neigbor) && !neigbor.equals(currNode)) {
					distances.add(neigbor);						//add all neigbors to pq
				}
			}
			long distFromStart = shortestNode.getStartDist();
			ShortestPathInfo add = new ShortestPathInfo(shortestNode.getLabel(), distFromStart);


			if(!visited.contains(shortestNode)) {
				shortestPath[j] = add;
				System.out.println(add);
				visited.add(shortestNode);
				unvisited.remove(shortestNode.getLabel());
				counter++;
				index++;
				//System.out.println(index);
			}	
		//}
		if(index==20000) {
			System.out.println("hi");
			ShortestPathInfo empty = new ShortestPathInfo(shortestNode.getLabel(), -1);
			shortestPath[j] = empty;
			visited.add(shortestNode);
			unvisited.remove(shortestNode.getLabel());

		}

		
	
	}
	
	while(!unvisited.isEmpty()) {
		ShortestPathInfo add = new ShortestPathInfo(unvisited.get(0), -1);
		unvisited.remove(0);
		shortestPath[counter] = add;
		counter++;
		}
		//System.out.print(unvisited.get(i));
	
	return shortestPath;

	
	
}
  
  private void startAndEnd(Node source, Node dest, Edge edge) {
	  source.addStartEdge(edge);
	  dest.addEndEdge(edge);
	  source.addEdge(edge);											//add new edge to source node
	  dest.addEdge(edge);												//do the same for dest node
  }
}