package wzrdfrm.model.farm;

public class XPHarvestReward implements HarvestReward {

    private Integer numXPOrbs;

    public XPHarvestReward(Integer numXPOrbs) {
        this.numXPOrbs = numXPOrbs;
    }

    @Override
    public String getName() {
        return "Experience";
    }

    @Override
    public Integer getQuantity() {
        return this.numXPOrbs;
    }
}
