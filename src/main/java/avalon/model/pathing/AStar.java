package avalon.model.pathing;


import avalon.model.pathing.node.Travelable;
import avalon.model.pathing.graph.Graph;
import avalon.model.pathing.node.Node;

import java.util.*;

public class AStar<P extends Travelable> {

	private final Set<Node<P>> closedSet = new HashSet<>();
	private final Queue<Node<P>> openSet = new PriorityQueue<>();
	private final Map<Node<P>, Node<P>> cameFrom = new HashMap<>();		// tracks the path from start to here. follow the values from here back to start
	private final Map<Node<P>, Double> gScore = new HashMap<>();		// actual distance from start to here
	private final Map<Node<P>, Double> fScore = new HashMap<>();		// gScore plus heuristic

	private final Graph<P> nodeGraph;
	private final Node<P> start;
	private final Node<P> goal;
	private final Traveler travelerInfo;

	public AStar(Graph<P> nodeGraph, Node<P> start, Node<P> goal, Traveler travelerInfo) {
		this.nodeGraph = nodeGraph;
		this.start = start;
		this.goal = goal;
		this.travelerInfo = travelerInfo;
		// populate all g and f scores with max value
		for (Node<P> node : nodeGraph.getNodeSet()) {
			gScore.put(node, Double.valueOf(Double.POSITIVE_INFINITY));
			fScore.put(node, Double.valueOf(Double.POSITIVE_INFINITY));
		}
		// populate start's f and g
		gScore.put(start, Double.valueOf(0.0));
		fScore.put(start, Double.valueOf(heuristic(start, goal)) );
		openSet.add(start);
	}

	public final List<Node<P>> run() {
		while (!openSet.isEmpty()) {
			Node<P> current = openSet.poll();		// get node with lowest f
			if (current.equals(goal)) {
				// hooray!
				return getPathFromStartToGoal();
			}
			closedSet.add(current);		// put this one in the closed set, we've processed it
			for (Node<P> neighbor : nodeGraph.getNeighbors(current)) {
				if (closedSet.contains(neighbor)) {
					continue;		// we've already processed this neighbor, skip it
				}
				double tentativeG = gScore.get(current).doubleValue() + nodeGraph.getDistance(current, neighbor, travelerInfo);
				if (!openSet.contains(neighbor)) {		// if we're not going to consider this node, we will now
					openSet.offer(neighbor);
				}
				else if (tentativeG >= gScore.get(neighbor).doubleValue()) {		// if we were going to consider this node, check if this current path is better than the existing one
					continue;		// this is not a better path, skip it
				}
		        // This path is the best until now. overwrite the existing g and f with this
		        cameFrom.put(neighbor, current);		// store how we got to this node
		        gScore.put(neighbor, Double.valueOf(tentativeG));
		        fScore.put(neighbor, Double.valueOf(gScore.get(neighbor).doubleValue() + heuristic(neighbor, goal)) );
			}
		}
		return null;
	}

	public final double getTotalPathDistance() {
		return gScore.get(goal).doubleValue();
	}

	// linear distance, override to change implementation
	protected double heuristic(Node<P> start, Node<P> goal) {
		return AStar.getLinearDistance(start, goal);
	}

	private List<Node<P>> getPathFromStartToGoal() {
		List<Node<P>> path = new LinkedList<>();
		Node<P> next = goal;		// backtrack from the goal to the start
		do {
			path.add(0, next);		// add to the front of the list so it starts at the start
			next = cameFrom.get(next);
		} while (next != null);
		if (!path.get(0).equals(start)) {
			return null;			// error, we somehow didn't end up at the start
		}
		return path;
	}

	public static <P extends Travelable> double getLinearDistance(Node<P> nodeA, Node<P> nodeB) {
		return Math.sqrt(Math.pow(nodeA.x + nodeB.x, 2) + Math.pow(nodeA.y + nodeB.y, 2));
	}

	public static <P extends Travelable> void printPath(List<Node<P>> path) {
		if (path != null) {
			for (Node<P> node : path) {
				System.out.println(node.x + " " + node.y);
			}
		}
	}

}
