package wzrdfrm.manager;

import wzrdfrm.model.classes.CharClass;
import wzrdfrm.model.classes.ClassLevel;
import wzrdfrm.model.farm.Farm;

public class ClassLevelManager {

    private static final int MAX_LEVEL = 10;

    private Farm farm;

    public ClassLevelManager(Farm farm) {
        this.farm = farm;
    }

    public void addXPToCurrentClass(Integer xp) {
        CharClass charClass = this.farm.getCurrCharClass();

        if (charClass.getCurrentLevel() == MAX_LEVEL) {
            return;     // nothing to do, already maxed
        }

        Integer newXP = charClass.getCurrentXP() + xp;
        ClassLevel currentClassLevel = charClass.getCharClassDefinition().getClassLevels().get(charClass.getCurrentLevel());
        if (newXP >= currentClassLevel.getXpRequired()) {       // level up!
            newXP -= currentClassLevel.getXpRequired();     // remove this level's xp req from the new value
            charClass.setCurrentLevel(charClass.getCurrentLevel() + 1);
        }

        charClass.setCurrentXP(newXP);
    }

}
