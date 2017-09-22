package wzrdfrm.response;

import wzrdfrm.model.farm.Farm;

import java.util.List;

public class FarmResponse {

    public Farm farm;
    public List<AbilityResponse> currentClassAbilities;


    public Farm getFarm() {
        return farm;
    }
    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public List<AbilityResponse> getCurrentClassAbilities() {
        return currentClassAbilities;
    }
    public void setCurrentClassAbilities(List<AbilityResponse> currentClassAbilities) {
        this.currentClassAbilities = currentClassAbilities;
    }
}
