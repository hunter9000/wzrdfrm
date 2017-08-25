package avalon.model.dungeons;

public enum GroundType {
    DIRT(1.0),
    GRASS(1.0),
    ICE(1.0),
    LAVA(1.0);

    public final double enterWeight;        // the cost for pathing associated with entering a square with this terrain type
    private GroundType(double enterWeight) {
        this.enterWeight = enterWeight;
    }
}
