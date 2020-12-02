package assignment5_f20;



public class Edge implements EdgeInterface{
	
	long idNum;
	String sourceNode;
	String destNode;
	long weight = 1;
	String edgeLabel= null;
	
	public Edge(long idNum, String sourceNode, String destNode, long weight, String edgeLabel) {
		this.idNum = idNum;
		this.sourceNode = sourceNode;
		this.destNode = destNode;
		this.weight= weight;
		this.edgeLabel = edgeLabel;
	}

	@Override
	public long getidNum() {
		return idNum;
	}

	@Override
	public String getSourceNode() {
		return sourceNode;
	}

	@Override
	public String getDestinationNode() {
		return destNode;
	}

	@Override
	public long getWeight() {
		return weight;
	}

	@Override
	public String getEdgeLabel() {
		return edgeLabel;
	}

}
