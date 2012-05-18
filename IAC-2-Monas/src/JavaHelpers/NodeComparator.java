package JavaHelpers;

import java.util.Comparator;

import IAC.Node;

public class NodeComparator implements Comparator<Node> {
	
	@Override
	public int compare(Node a, Node b) {
		return a.getLabel().compareTo(b.getLabel()) ;
	}

}
