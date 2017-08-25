package avalon.model.pathing.graph;


import avalon.model.pathing.Traveler;
import avalon.model.pathing.node.Travelable;
import avalon.model.pathing.node.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Converts Node's coords to ints */
public class DirectedGraph<P extends Travelable> implements Graph<P> {
	private final List<Node<P>> nodeList = new ArrayList<>();
	private final Map<Node<P>, Map<Node<P>, Edge<P>>> edges = new HashMap<>();

	@Override
	public List<Node<P>> getNodeSet() {
		return nodeList;
	}
	@Override
	public List<Node<P>> getNeighbors(Node<P> node) {
		Map<Node<P>, Edge<P>> e = edges.get(node);
		List<Node<P>> list = new ArrayList<>();
		list.addAll(e.keySet());
		return list;
	}
	@Override
	public double getDistance(Node<P> nodeA, Node<P> nodeB, Traveler travelerInfo) {
		// find the edge, get it's length
		Map<Node<P>, Edge<P>> neighbors = edges.get(nodeA);
		Edge<P> e = neighbors.get(nodeB);
		return e.distance;
	}

	// doesn't check for duplicate added nodes
	public void addNode(Node<P> node) {
		nodeList.add(node);		// add node to list
		Map<Node<P>, Edge<P>> newEdges = new HashMap<>();
		edges.put(node, newEdges);
	}

	// doesn't check that the nodes have been added already
	public void addEdge(Node<P> nodeA, Node<P> nodeB, double weight) {
		Map<Node<P>, Edge<P>> nodeAEdges = edges.get(nodeA);
		if (nodeAEdges != null) {		// haven't added an edge to this one yet
			nodeAEdges.put(nodeB, new Edge<P>(nodeA, nodeB, weight));
		}
		else {
			// error
		}
	}
}