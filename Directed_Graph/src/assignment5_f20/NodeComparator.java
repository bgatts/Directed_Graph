package assignment5_f20;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		if(o1.getStartDist()<o2.getStartDist()) {
			return -1;
		}
		else if(o1.getStartDist()>o2.getStartDist()) {
			return 1;
		}
		return 0;
	}
	

}
