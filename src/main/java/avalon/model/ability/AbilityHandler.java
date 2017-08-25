package avalon.model.ability;


import avalon.model.CellEntity;
import avalon.model.dungeons.DungeonCell;

//
public abstract class AbilityHandler {
	// handles the ability hitting the given target, either by doing damage, applying a buff/debuff
	public void handleEnemyTargetAbility(CellEntity target) { }
	public void handleGroundTargetAbility(DungeonCell groundTarget) { }		// applies a buff to the ground?
	public void handleSelfTargetAbility(CellEntity me) { }
}





