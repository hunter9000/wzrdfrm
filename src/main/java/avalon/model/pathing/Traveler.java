package avalon.model.pathing;


import avalon.model.pathing.node.Travelable;

public class Traveler {

	/** This traveler determines how much it costs to enter the given travelable point. */
	public double visit(Travelable travelable) {
		return travelable.getEnterWeight();
	}
}