package avalon.model.pathing.graph;


import avalon.model.pathing.node.Travelable;
import avalon.model.pathing.AStar;
import avalon.model.pathing.node.Node;

public class Edge<P extends Travelable> {
	public final Node<P> nodeA;
	public final Node<P> nodeB;
	public final double weight;

	public final double distance;

	public Edge(Node<P> nodeA, Node<P> nodeB, double weight) {
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.weight = weight;
		distance = AStar.getLinearDistance(nodeA, nodeB) * weight;
	}

}