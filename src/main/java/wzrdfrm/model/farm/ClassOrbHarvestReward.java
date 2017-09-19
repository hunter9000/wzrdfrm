package wzrdfrm.model.farm;

public class ClassOrbHarvestReward implements HarvestReward {

    private Integer numClassOrbs;

    public ClassOrbHarvestReward(Integer numClassOrbs) {
        this.numClassOrbs = numClassOrbs;
    }

    @Override
    public String getName() {
        return "Class Unlock Orbs";
    }

    @Override
    public Integer getQuantity() {
        return this.numClassOrbs;
    }
}
