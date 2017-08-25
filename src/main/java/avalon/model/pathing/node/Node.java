package avalon.model.pathing.node;


public class Node<P extends Travelable> implements Comparable<Node<P>> {
	public final double x, y;
	public final P payload;

	public double fValue;

	public Node(double x, double y, P payload) {
		this.x = x;
		this.y = y;
		this.payload = payload;
	}

	@Override
	public int compareTo(Node<P> other) {
		if (this.fValue == other.fValue) {
			return 0;
		}
		if (this.fValue < other.fValue) {
			return -1;
		}
		return 1;
	}
}
