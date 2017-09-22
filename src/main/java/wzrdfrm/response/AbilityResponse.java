package wzrdfrm.response;

import wzrdfrm.model.classes.AbilityType;

public class AbilityResponse {

    public AbilityType abilityType;
    public int level;

    public AbilityResponse(AbilityType abilityType, int level) {
        this.abilityType = abilityType;
        this.level = level;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }
    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}
