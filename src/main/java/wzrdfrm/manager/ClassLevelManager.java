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
        ClassLevel nextClassLevel = getNextClassLevel(charClass);
        if (newXP >= nextClassLevel.getXpRequired()) {       // level up!
            newXP -= nextClassLevel.getXpRequired();     // remove this level's xp req from the new value
            charClass.setCurrentLevel(charClass.getCurrentLevel() + 1);
        }

        charClass.setCurrentXP(newXP);
    }

    /** Calculates and sets the xp needed to level for the current class only. */
    public void setCurrentClassXpLevels() {
        setXpNeededToLevel(farm.getCurrCharClass());
    }

    /** Calculates and sets the xp needed to level for all the classes. */
    public void setAllClassXpLevels(Iterable<CharClass> charClasses) {
        for (CharClass charClass : charClasses) {
            setXpNeededToLevel(charClass);
        }
    }

    private void setXpNeededToLevel(CharClass charClass) {
        if (charClass.getCurrentLevel() != MAX_LEVEL) {
            Integer xpNeeded = getNextClassLevel(charClass).getXpRequired();
            charClass.setXpNeededToLevel(xpNeeded);
        }
    }

    private ClassLevel getNextClassLevel(CharClass charClass) {
        return charClass.getCharClassDefinition().getClassLevels().get(charClass.getCurrentLevel() + 1);
    }
}
