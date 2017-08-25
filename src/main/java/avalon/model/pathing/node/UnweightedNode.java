package avalon.model.pathing.node;



public class UnweightedNode<P extends Travelable> extends Node<P> {

	/** node will be "blocked" if enterweight is negative */
	public UnweightedNode(double x, double y, P payload) {
		super(x, y, payload);
	}
}
