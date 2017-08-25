package avalon.model.pathing.node;



public class WeightedGridNode<P extends Travelable> extends GridNode<P> {
	public final double enterWeight;

	/** node will be "blocked" if enterweight is negative */
	public WeightedGridNode(int x, int y, P payload, double enterWeight) {
		super(x, y, payload);
		this.enterWeight = enterWeight;
	}
}
