package avalon.model.pathing;


import avalon.model.dungeons.DungeonCell;
import avalon.model.dungeons.GroundType;
import avalon.model.pathing.graph.GridGraph;
import avalon.model.pathing.node.GridNode;
import avalon.model.pathing.node.Node;

import java.util.List;

public class Main {

	public static void main(String/*...*/ args) {
		GridGraph<DungeonCell> graph = new GridGraph<>(5, 5);
		graph.addNode(new GridNode<DungeonCell>(0, 0, new DungeonCell(null, 0, 0, GroundType.DIRT)));
		graph.addNode(new GridNode<DungeonCell>(0, 1, new DungeonCell(null, 0, 1, GroundType.DIRT)));
		graph.addNode(new GridNode<DungeonCell>(1, 0, new DungeonCell(null, 1, 0, GroundType.DIRT)));
		graph.addNode(new GridNode<DungeonCell>(1, 1, new DungeonCell(null, 1, 1, GroundType.DIRT)));
		graph.addNode(new GridNode<DungeonCell>(2, 1, new DungeonCell(null, 2, 1, GroundType.DIRT)));
		graph.addNode(new GridNode<DungeonCell>(3, 1, new DungeonCell(null, 3, 1, GroundType.DIRT)));
		graph.addNode(new GridNode<DungeonCell>(3, 2, new DungeonCell(null, 3, 2, GroundType.DIRT)));
		graph.addNode(new GridNode<DungeonCell>(3, 3, new DungeonCell(null, 3, 3, GroundType.DIRT)));

		List<Node<DungeonCell>> nodeList = graph.getNodeSet();
		//List<Node<String>> neighbors = graph.getNeighbors(nodeList.get(0));
		//double dist = graph.getDistance(nodeList.get(0), nodeList.get(1));

		AStar<DungeonCell> as = new AStar<DungeonCell>(graph, nodeList.get(0), nodeList.get(7), new Traveler());

//		DirectedGraph<String> graph = new UndirectedGraph<>();
//		graph.addNode(new UnweightedNode<String>(0, 0, "a"));
//		graph.addNode(new UnweightedNode<String>(2, 3.5, "b"));
//		graph.addNode(new UnweightedNode<String>(1.75, 1.25, "c"));
//		graph.addNode(new UnweightedNode<String>(.5, 2.5, "d"));
//		graph.addNode(new UnweightedNode<String>(3, 4, "e"));
//
//		List<Node<String>> nodeList = graph.getNodeSet();
//		graph.addEdge(nodeList.get(0), nodeList.get(2), 1.7);
//		graph.addEdge(nodeList.get(0), nodeList.get(3), 2.5);
//		graph.addEdge(nodeList.get(2), nodeList.get(1), 1);
//		graph.addEdge(nodeList.get(3), nodeList.get(1), 1);
//		graph.addEdge(nodeList.get(4), nodeList.get(1), 1);
//
//		AStar<String> as = new AStar<String>(graph, nodeList.get(0), nodeList.get(4));

		List<Node<DungeonCell>> path = as.run();
		System.out.println("path found " + (path != null));
		AStar.printPath(path);
		System.out.println("distance: " + as.getTotalPathDistance());
	}

}
