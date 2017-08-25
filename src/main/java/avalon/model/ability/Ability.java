package avalon.model.ability;

// The ability gets used, and applied to all the entities or ground tiles in it's range.
public class Ability {
	public enum AbilityTargetType {
		ENEMY,
		SELF,
		GROUND
	}
	public enum AbilityAreaType {
		SINGLE_TARGET,
		BURST_1,
		burst_2
	}


	AbilityTargetType targetType;		// type of target, enemy, self, buff, ground
	AbilityAreaType area;		// area- single target, area (with radius), none (for buff)
	String name;
	int range;
	AbilityHandler abilityHandler;		// class that we delegate actually applying the effect of the ability to. we tell it what targets to hitting

	public Ability(AbilityTargetType targetType, AbilityAreaType area, String name, int range, AbilityHandler abilityHandler) {
		this.targetType = targetType;
		this.area = area;
		this.name = name;
		this.range = range;
		this.abilityHandler = abilityHandler;
	}


}