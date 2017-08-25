package avalon.model.pathing.graph;


import avalon.model.pathing.node.Node;
import avalon.model.pathing.node.Travelable;

import java.util.ArrayList;
import java.util.List;

/** Tops and bottoms of hexes are vertical, so horizontal rows are in a straight line. Columns are jagged */
public class VerticalHexGridGraph<P extends Travelable> extends BaseGridGraph<P> {

	public VerticalHexGridGraph(int initCols, int initRows) {
		super(initCols, initRows);
	}

	@Override
	public List<Node<P>> getNeighbors(Node<P> node) {
		List<Node<P>> neighbors = new ArrayList<>();
		checkAndAdd(((int)node.x)-1, ((int)node.y)-1, neighbors);
		checkAndAdd(((int)node.x), ((int)node.y)-1, neighbors);
		checkAndAdd(((int)node.x)+1, ((int)node.y)-1, neighbors);
		checkAndAdd(((int)node.x)+1, ((int)node.y), neighbors);
		checkAndAdd(((int)node.x), ((int)node.y)+1, neighbors);
		checkAndAdd(((int)node.x)-1, ((int)node.y), neighbors);
		return neighbors;
	}

}
