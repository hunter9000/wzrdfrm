package avalon.model.pathing.node;



public class GridNode<P extends Travelable> extends Node<P> {
	
	public GridNode(int x, int y, P payload) {
		super(x, y, payload);
	}
}
