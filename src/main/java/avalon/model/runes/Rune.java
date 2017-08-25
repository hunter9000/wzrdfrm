package avalon.model.runes;


import avalon.model.ability.Ability;
import avalon.model.ability.AbilityHandler;
import avalon.model.ability.handlers.*;

public class Rune {

	enum Base {
		Fehu(new FehuAbilityHandler()),		// increased crit chance buff
		Uruz(new UruzAbilityHandler()),		// increased melee attacks buff (pierce/bash/slash)
		Raidho(new RaidhoAbilityHandler()),		// teleport (movement buff?)
		Wunjo(new WunjoAbilityHandler()),		// healing
		Hagalaz(new HagalazAbilityHandler()),	// elemental storm
		Jera(new JeraAbilityHandler());		// increased harvesting buff

		public final AbilityHandler abilityHandler;
		private Base(AbilityHandler abilityHandler) {
			this.abilityHandler = abilityHandler;
		}
	}

	enum TypeMod {
		Taurisaz,	// thorn or giant - destruction + defense
		Ansuz,		// inspiration - insight, wisdom
		Kenaz,		// light, vision, fire/transformation, torch, guidance
		Gebo,		// gift, sacrifice
		Nauthiz,	// need, self-reliance, endurance
		Isa,		// ice
		Eihwaz,		// strength, reliability, dependability, endurance
		Perthro,	// uncertain meaning, mystery, occult
		Algiz,		// protection, sheild
	}

	enum SizeMod {
		Sowilo,		// sun, health, success
		Tiwaz,		// sky, honor, justice, friendship, sacrifice
		Berkano,	// birth, regeneration
		Ehwaz,		// horses, movement, progress
		Mannaz,		// mankind, society
		Lagaz,		// water, growth, healing
		Ingwaz,		// earth, internal strength
		Dagaz,		// day, awakening
		Othala,		// inherit
		Blank
	}

	// given the 3 mods, make an activated ability object.
	public static Ability create(Base base, TypeMod typeMod, SizeMod sizeMod) {
		AbilityHandler handler = base.abilityHandler;

		return new Ability(Ability.AbilityTargetType.SELF, Ability.AbilityAreaType.SINGLE_TARGET, "a", 1, handler);
	}

//	new Ability(ENEMY, single, "Melee", new MeleeAttackAbilityHandler(myWeapon));
//	new Ability(ENEMY, mySpell.getArea(), "fireball", new SpellAttackAbilityHandler(mySpell));


	// user activates an ability
//	activateAbility(Ability ability, Point location) {
//		if (ability.getTargetType() == SELF) {
//			ability.getAbilityHandler().handleSelfTargetAbility(me);
//		}
//		else if (ability.getTargetType() == GROUND) {
//			for (getGroundAtPoint(point)) {		// get all the tiles in the targeted area,
//				ability.getAbilityHandler().handleGroundTargetAbility();
//			}
//		}
//		else if (ability.getTargetType() == ENEMY) {
//			for (getEnemiesAtPoint(point)) { 		// get all the enemies in the targetted area
//				ability.getAbilityHandler().handleEnemyTargetAbility();
//			}
//		}
//	}


}