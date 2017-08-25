package avalon.model.pathing.graph;


import avalon.model.pathing.Traveler;
import avalon.model.pathing.node.Node;
import avalon.model.pathing.node.Travelable;
import avalon.model.pathing.node.WeightedGridNode;
import avalon.model.pathing.node.GridNode;

import java.util.ArrayList;
import java.util.List;

/** Converts Node's coords to ints */
public abstract class BaseGridGraph<P extends Travelable> implements Graph<P> {

	protected final int initCols, initRows;
	protected final GridNode<P>[][] nodes;
	protected final List<Node<P>> nodeList = new ArrayList<>();

	public BaseGridGraph(int initCols, int initRows) {
		this.initCols = initCols;
		this.initRows = initRows;
		nodes = new WeightedGridNode[initCols][initRows];
	}

	public void addNode(GridNode<P> node) {
		nodeList.add(node);
		nodes[(int)node.x][(int)node.y] = node;
	}

	@Override
	public List<Node<P>> getNodeSet() {
		return nodeList;
	}

	@Override
	public double getDistance(Node<P> nodeA, Node<P> nodeB, Traveler travelerInfo) {
		return travelerInfo.visit(nodeB.payload);
	}

	protected boolean inBounds(int col, int row) {
		return col >= 0 && col < initCols && row >= 0 && row < initRows;
	}

	protected void checkAndAdd(int x, int y, List<Node<P>> neighbors) {
		if (inBounds(x, y) && nodes[x][y] != null && nodes[x][y].payload.isPassable()) {	// if neighbor square is in bounds, there's a node there, and the weight is not negative
			neighbors.add(nodes[x][y]);
		}
	}

}
